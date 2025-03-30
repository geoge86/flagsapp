package gr.ihu.flags;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoViewHolder extends RecyclerView.ViewHolder {
    TextView flagcountry;
    TextView flagcontinent;


    public PhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        flagcountry=itemView.findViewById(R.id.countryflag);
        flagcontinent = itemView.findViewById(R.id.continentflag);
    }
}
