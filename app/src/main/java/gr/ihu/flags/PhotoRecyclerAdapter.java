package gr.ihu.flags;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gr.ihu.flags.model.photo;

public class PhotoRecyclerAdapter extends RecyclerView.Adapter <PhotoViewHolder>{

    private final ImageView imageview;
    List<photo> photoList;

    public PhotoRecyclerAdapter(List<photo> photoList, ImageView imageView){
        this.photoList = photoList;
        this.imageview = imageView;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoview = inflater.inflate(R.layout.photorecyclerlayout, parent, false);
        return new PhotoViewHolder(photoview);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        photo photo = photoList.get(position);
        TextView textView = holder.flagcountry;
        TextView flagcontinent = holder.flagcontinent;
        flagcontinent.setText(photo.getContinent());
        textView.setText(photo.getName());
        textView.setOnClickListener(View -> imageview.setImageResource(photo.getId()));
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
