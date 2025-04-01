package gr.ihu.flags;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;
public class PhotoViewHolder extends RecyclerView.ViewHolder {

    TextView flagcountry;
    TextView flagcontinent;
    public PhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        flagcountry = itemView.findViewById(R.id.flagcountry);
        flagcontinent = itemView.findViewById(R.id.flagcontinent);
    }
}
