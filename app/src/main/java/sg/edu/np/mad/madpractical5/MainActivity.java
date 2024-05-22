package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHandler handler = new DatabaseHandler(this,null,null,1);

//        Intent recieveIntentAdapter = getIntent();
//        user.setName(recieveIntentAdapter.getExtras().getString("name","Name"));
//        user.setDescription(recieveIntentAdapter.getExtras().getString("description","Description"));
//        user.setFollowed(recieveIntentAdapter.getExtras().getBoolean("followed",false));
//        user.setId(recieveIntentAdapter.getExtras().getInt("id", 1));

        Intent recieveIntentAdapter = getIntent();
        String name = recieveIntentAdapter.getStringExtra("name");
        String description = recieveIntentAdapter.getStringExtra("description");
        boolean followed = recieveIntentAdapter.getBooleanExtra("followed", false);
        int id = recieveIntentAdapter.getIntExtra("id", 0);

        // Initialize a new User object
        User user = new User(name, description, id, followed);

        // Get the TextViews and Button from the Layout
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnFollow = findViewById(R.id.btnFollow);
        Button btnMessage = findViewById(R.id.btnMessage);

        // Set the TextViews with the User's name, description and default button message
        tvName.setText(user.name);
        tvDescription.setText(user.description);
        if (!user.followed){
            user.followed = false;
            btnFollow.setText("Follow");
            Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
        } else {
            user.followed = true;
            btnFollow.setText("Unfollow");
            Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
        }

        // 5.1
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.followed){
                    user.followed = false;
                    btnFollow.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();

                } else {
                    user.followed = true;
                    btnFollow.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                }
                handler.updateUser(user);

            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMessageGroup = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(intentMessageGroup);
            }
        });
    }
}