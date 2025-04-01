package gr.ihu.flags;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import gr.ihu.flags.model.Photo;

public class MainActivity extends AppCompatActivity {

    List<Photo> photoList = Arrays.asList(
            new Photo("Algeria", R.drawable.algeria,"Africa"),
            new Photo("Belgium", R.drawable.belgium, "Europe"),
            new Photo("China", R.drawable.china, "Asia"),
            new Photo("Cyprus", R.drawable.cyprus, "Europe"),
            new Photo("Egypt", R.drawable.egypt, "Africa"),
            new Photo("Portugal", R.drawable.portugal, "Europe")
    );
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.photoRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        PhotoRecyclerAdapter photoRecyclerAdapter = new PhotoRecyclerAdapter(photoList,
                findViewById(R.id.flagimage));
        recyclerView.setAdapter(photoRecyclerAdapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (savedInstanceState!=null) {
            Log.d("MainActivity","Hey I am recovering from destroy!");
            Parcelable savedReviewerState = savedInstanceState.getParcelable("recycler_state");
            PhotoRecyclerAdapter adapter = (PhotoRecyclerAdapter)recyclerView.getAdapter();
            int lastClickedPosition = savedInstanceState.getInt("last_position_clicked");
            recyclerView.getLayoutManager().onRestoreInstanceState(savedReviewerState);
            adapter.setLastClickedPosition(lastClickedPosition);

        } else {
            recyclerView.scrollToPosition(0);
        }
        Log.d("MainActivity","I am onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","I am onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","I am onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","I am onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","I am onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","I am onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("MainActivity", "I am onSaveInstanceState");
        if (recyclerView !=null ){
            Parcelable my_layout_state = recyclerView.getLayoutManager().onSaveInstanceState();
            PhotoRecyclerAdapter adapter = (PhotoRecyclerAdapter)recyclerView.getAdapter();
            outState.putParcelable("recycler_state", my_layout_state);
            outState.putInt("last_position_clicked",adapter.getLastClickedPosition());
        }
    }
}