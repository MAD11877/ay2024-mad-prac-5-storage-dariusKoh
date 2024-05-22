package sg.edu.np.mad.madpractical5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView smallImage;
    ImageView bigImage;
    TextView name;
    TextView description;

    public UserViewHolder(View itemView) {
        super(itemView);

        // Locations of image, name and description found in custom_activity_list.xml
        smallImage = itemView.findViewById(R.id.tvSmallImage);
        bigImage = itemView.findViewById(R.id.tvLargeImage);
        name = itemView.findViewById(R.id.tvName);
        description = itemView.findViewById(R.id.tvDescription);
    }
}
