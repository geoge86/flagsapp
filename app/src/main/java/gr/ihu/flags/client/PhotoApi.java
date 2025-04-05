package gr.ihu.flags.client;

import java.util.List;

import gr.ihu.flags.model.Photo;
import retrofit2.Call;
import retrofit2.http.GET;


    public interface PhotoApi {

        @GET("/photos")
        Call<List<Photo>> getAllPhotos();

    }

