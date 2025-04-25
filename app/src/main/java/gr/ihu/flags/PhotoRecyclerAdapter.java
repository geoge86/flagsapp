package gr.ihu.flags;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import gr.ihu.flags.model.Photo;

public class PhotoRecyclerAdapter extends RecyclerView.Adapter<PhotoViewHolder> {

    private final ImageView imageView;

    List<Photo> photoList;

    private int lastClickedPosition = 0;
    private Context context;

    public PhotoRecyclerAdapter(List<Photo> photoList, ImageView imageView) {
        this.photoList = photoList;
        this.imageView = imageView;
        updateImageView(0,photoList.get(this.lastClickedPosition));

    }
    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.photo_recycler_item, parent, false);

        return new PhotoViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        TextView  textView = holder.CountryName;
        textView.setText(photo.getName());
        TextView shortDescription = holder.shortDescription;
        shortDescription.setText(photo.getDescription().substring(0,4));
        textView.setOnClickListener(view -> updateImageView(Objects.requireNonNull(holder).getAdapterPosition(), photo));
    }

    private void updateImageView(int lastClickedPosition, Photo photo) {
        this.lastClickedPosition = lastClickedPosition;
        Bitmap bitmap = BitmapFactory.decodeByteArray(photo.getData(), 0, photo.getData().length);
        imageView.setImageBitmap(bitmap);
        imageView.setOnClickListener(v-> {
            //Create a new activity showing info about the animal of the picture
            Intent intent = new Intent(context,ViewPhotoActivity.class);
            intent.putExtra("photo", photo);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public int getLastClickedPosition() {
        return this.lastClickedPosition;
    }

    public void setLastClickedPosition(int lastClickedPosition) {
        this.lastClickedPosition = lastClickedPosition;
        updateImageView(lastClickedPosition, photoList.get(this.lastClickedPosition));
    }
}

