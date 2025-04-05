package gr.ihu.flags;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

import gr.ihu.flags.client.PhotoApiClient;
import gr.ihu.flags.model.Photo;

public class MainViewModel extends ViewModel {

    MutableLiveData<List<Photo>> photoList;
    PhotoApiClient photoApiClient = new PhotoApiClient();
    public LiveData<List<Photo>> getPhotos() {
       if (photoList == null) {
           this.photoList = new MutableLiveData<>();
           this.photoList = photoApiClient.getAllPhotos(photoList);
    }
           return this.photoList;
    }

}
