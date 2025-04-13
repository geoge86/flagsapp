package gr.ihu.flags;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoViewHolder extends RecyclerView.ViewHolder {

    TextView animalName;
    TextView shortDescription;
    public PhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        animalName = itemView.findViewById(R.id.CountryName);
        shortDescription = itemView.findViewById(R.id.textView2);
    }
}

