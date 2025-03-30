package gr.ihu.flags;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class fotoviewholder extends RecyclerView.ViewHolder {
    TextView flagcountry;

    public fotoviewholder(@NonNull View itemView) {
        super(itemView);
        flagcountry=itemView.findViewById(R.id.countryflag);
    }
}
