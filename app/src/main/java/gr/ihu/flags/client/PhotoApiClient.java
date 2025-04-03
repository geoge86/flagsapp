package gr.ihu.flags.client;

import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

import gr.ihu.flags.R;
import gr.ihu.flags.model.Photo;

public class PhotoApiClient {

public MutableLiveData<List<Photo>> getAllPhotos(){
    MutableLiveData<List< Photo>> photoList= new MutableLiveData<>();
    photoList.postValue(Arrays.asList(
            new Photo("Algeria", R.drawable.algeria,"Africa"),
            new Photo("Belgium", R.drawable.belgium, "Europe"),
            new Photo("China", R.drawable.china, "Asia"),
            new Photo("Cyprus", R.drawable.cyprus, "Europe"),
            new Photo("Egypt", R.drawable.egypt, "Africa"),
            new Photo("France", R.drawable.france, "Europe"),
            new Photo("Germany", R.drawable.germany, "Europe"),
            new Photo("Israel", R.drawable.israel, "Asia"),
            new Photo("Portugal", R.drawable.portugal, "Europe"),
            new Photo("Spain", R.drawable.spain, "Europe"),
            new Photo("Sweden", R.drawable.sweden, "Europe"),
            new Photo("USA", R.drawable.usa, "America")

    ));
    return photoList;
    }


}
