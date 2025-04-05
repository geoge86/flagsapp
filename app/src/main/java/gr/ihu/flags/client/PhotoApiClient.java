package gr.ihu.flags.client;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import gr.ihu.flags.R;
import gr.ihu.flags.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PhotoApiClient {

    private static final String BASE_URL="http://10.0.2.2:9000";

    private final PhotoApi photoApi;
    public PhotoApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()
               )).build();

        photoApi = retrofit.create(PhotoApi.class);
    }
public void getAllPhotos(MutableLiveData<List<Photo>> photoList) {
    Call<List<Photo>> allPhotos = photoApi.getAllPhotos();

    allPhotos.enqueue(new Callback<List<Photo>>() {
        @Override
        public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {
            if (response.body() != null) {
                photoList.postValue(response.body());
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable throwable) {
            photoList.postValue(Collections.emptyList());
        }
    });
}


}
