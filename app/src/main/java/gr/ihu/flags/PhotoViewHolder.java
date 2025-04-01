package gr.ihu.flags;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;
public class PhotoViewHolder extends RecyclerView.ViewHolder {

    TextView animalName;
    TextView shortDescription;
    public PhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        animalName = itemView.findViewById(R.id.animalName);
        shortDescription = itemView.findViewById(R.id.textView2);
    }
}
