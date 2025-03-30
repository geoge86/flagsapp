package gr.ihu.flags;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gr.ihu.flags.model.photo;

public class photorecycleradapter extends RecyclerView.Adapter <fotoviewholder>{

    private final ImageView imageview;
    List<photo> photoList;

    public photorecycleradapter (List<photo> photoList, ImageView imageView){
        this.photoList = photoList;
        this.imageview = imageView;
    }

    @NonNull
    @Override
    public fotoviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoview = inflater.inflate(R.layout.photorecyclerlayout, parent, false);
        return new fotoviewholder(photoview);
    }

    @Override
    public void onBindViewHolder(@NonNull fotoviewholder holder, int position) {
        photo photo = photoList.get(position);
        TextView textView = holder.flagcountry;
        textView.setText(photo.getName());
        textView.setOnClickListener(View -> imageview.setImageResource(photo.getId()));
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
