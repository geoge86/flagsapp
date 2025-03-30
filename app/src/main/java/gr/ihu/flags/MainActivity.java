package gr.ihu.flags;

import android.os.Bundle;
import android.view.View;
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

import gr.ihu.flags.model.photo;

public class MainActivity extends AppCompatActivity {

    List<photo> photoList = Arrays.asList(
            new photo("Algeria", R.drawable.algeria,"Africa"),
            new photo("Belgium", R.drawable.belgium, "Europe"),
            new photo("China", R.drawable.china, "Asia"),
            new photo("Cyprus", R.drawable.cyprus, "Europe"),
            new photo("Egypt", R.drawable.egypt, "Africa"),
            new photo("Portugal", R.drawable.portugal, "Europe")

    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.photorecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        PhotoRecyclerAdapter photorecycleradapter = new PhotoRecyclerAdapter(photoList, findViewById(R.id.imageView));
        recyclerView.setAdapter(photorecycleradapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onclickhandler(View view) {
        ImageView flagimage =  findViewById(R.id.imageView);
        if (flagimage.getVisibility() == View.VISIBLE) {
            flagimage.setVisibility(View.INVISIBLE);
        } else {
            flagimage.setVisibility(View.VISIBLE);
        }
    }

}