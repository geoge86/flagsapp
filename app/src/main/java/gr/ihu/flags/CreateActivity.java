package gr.ihu.flags;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import gr.ihu.flags.model.Photo;
import gr.ihu.flags.util.BitmapUtil;

public class CreateActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Bundle extras = result.getData().getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    ImageView imageView = findViewById(R.id.imageCreateView);
                    imageView.setImageBitmap(imageBitmap);
                }
            });

    ActivityResultLauncher<Intent> photoCollectionResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    if (result.getData()!=null) {
                        Uri uri = result.getData().getData();
                        ImageView imageView = findViewById(R.id.imageCreateView);
                        imageView.setImageURI(uri);
                    }

                }
            });
    private String type;
    private CreateViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        type = "mammal";

    }

    @Override
    protected void onStart() {
        super.onStart();
        model = new ViewModelProvider(this).get(CreateViewModel.class);
        ImageView imageView = findViewById(R.id.imageCreateView);
        imageView.setImageResource(android.R.color.transparent);

        model.getPhoto().observe(this, x -> {
            if (x != null) {
                EditText textView = findViewById(R.id.nameCreateTextView);
                textView.setText(x.getName());
                Bitmap bmp = BitmapFactory.decodeByteArray(x.getData(), 0, x.
                        getData().length);
                imageView.setImageBitmap(bmp);
                Toast.makeText(this, "Image got updated", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        });
        model.errorMessages().observe(this, x ->
                Toast.makeText(this, x, Toast.LENGTH_SHORT).show());
    }
    public void onCameraCapture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraActivityResultLauncher.launch(intent);
    }

    public void onAddButton(View view) {
        // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
        EditText textView = findViewById(R.id.nameCreateTextView);
        ImageView imageView = findViewById(R.id.imageCreateView);

        if (!(imageView.getDrawable() instanceof BitmapDrawable)) {
            Toast.makeText(this, "Please take a picture and give a name", Toast.LENGTH_SHORT).show();
            return;
        }

        Photo photo = new Photo();

        photo.setName(textView.getText().toString());
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        photo.setData(BitmapUtil.bitmap2byteArray(bitmap));
        photo.setType("Mammal");
        photo.setDescription("no description yet");
        photo.setFilename(photo.getName() + ".jpeg");

        if (photo.getName().isEmpty() || photo.getData().length == 0) {
            Toast.makeText(this, "Please take a picture and give a name", Toast.LENGTH_SHORT).show();
        } else {
            model.setPhoto(photo);
        }
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onViewGallery(View view) {
        // Read from phone's album
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        photoCollectionResultLauncher.launch(intent);
    }
}