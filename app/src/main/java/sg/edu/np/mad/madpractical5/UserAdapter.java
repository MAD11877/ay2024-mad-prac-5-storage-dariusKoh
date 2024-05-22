package sg.edu.np.mad.madpractical5;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private ArrayList<User> listObjects;

    public UserAdapter(ArrayList<User> listObjects, ListActivity activity) {
        this.listObjects = listObjects;
    }

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(UserViewHolder holder, int position) {
        User listItems = listObjects.get(position);
        holder.name.setText(listItems.getName());
        holder.description.setText(listItems.getDescription());

        holder.smallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Profile");
                builder.setMessage("MADness");
                builder.setCancelable(true);

                builder.setPositiveButton("Close", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){

                    }
                });
                builder.setNegativeButton("View", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
//                        Bundle extras = new Bundle();
//                        for (User i:listObjects) {
//                            if (i.getId() == listItems.getId()) {
//                                extras.putString("name", i.getName());
//                                extras.putString("description", i.getDescription());
//                                extras.putInt("id", i.getId());
//                                extras.putBoolean("followed", i.getFollowed());
//                                break;
//                            }
//                        }
//                        Intent intentMainActivity = new Intent(v.getContext(), MainActivity.class);
//                        intentMainActivity.putExtras(extras);
//                        v.getContext().startActivity(intentMainActivity);
                        Intent intentMainActivity = new Intent(v.getContext(), MainActivity.class);

                        intentMainActivity.putExtra("id", listItems.getId());
                        intentMainActivity.putExtra("name", listItems.getName());
                        intentMainActivity.putExtra("description", listItems.getDescription());
                        intentMainActivity.putExtra("followed", listItems.getFollowed());

                        v.getContext().startActivity(intentMainActivity);
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        if (listItems.name.substring(listItems.name.length() - 1).equals("7")) {
            holder.bigImage.setVisibility(View.VISIBLE);
        }
    }

    public int getItemCount() { return listObjects.size(); }
}