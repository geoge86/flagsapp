package gr.ihu.flags;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import gr.ihu.flags.model.Photo;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private ActivityResultLauncher<Intent> activityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.photoRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);

        MainViewModel model = new ViewModelProvider(this).get(MainViewModel.class);
        model.getPhotos().observe(this, photoList-> {
            PhotoRecyclerAdapter photoRecyclerAdapter = new PhotoRecyclerAdapter(photoList,
                    findViewById(R.id.flagimage));
            recyclerView.setAdapter(photoRecyclerAdapter);
        });

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
            Objects.requireNonNull(recyclerView.getLayoutManager()).onRestoreInstanceState(savedReviewerState);
            if (adapter!=null) {
                adapter.setLastClickedPosition(lastClickedPosition);
            }

        } else {
            recyclerView.scrollToPosition(0);
        }
        activityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        model.updatePhotos();
                    }
                }
        );
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

    public void onCreateButton(View view) {
        Intent intent = new Intent(this,CreateActivity.class);
        activityLauncher.launch(intent);
    }
}