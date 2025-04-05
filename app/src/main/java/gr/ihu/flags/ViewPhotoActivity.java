package gr.ihu.flags;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import gr.ihu.flags.model.Photo;

public class ViewPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity2_view_image);

        Intent intent = getIntent();
        if (intent!=null) {
            Photo photo = (Photo) intent.getSerializableExtra("photo");

            ImageView imageView = findViewById(R.id.activityflagimage);
            Bitmap bitmap = BitmapFactory.decodeByteArray(photo.getData(), 0, photo.getData().length);
            imageView.setImageBitmap(bitmap);

            TextView textView = findViewById(R.id.activityflagcontinent);
            textView.setText(photo.getType());
            TextView textView2 = findViewById(R.id.activityflagcountry);
            textView2.setText(photo.getName());

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}