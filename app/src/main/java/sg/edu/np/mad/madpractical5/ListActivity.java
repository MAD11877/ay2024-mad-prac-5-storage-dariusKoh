package sg.edu.np.mad.madpractical5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DBHandler handler = new DBHandler(this,null,null,1);
        handler.createUsers();

//        ArrayList<User> userArrayList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            int randomInt = new Random().nextInt(1000000);
//            boolean randomFollowedBool = new Random().nextBoolean();
//
//            User newUser = new User("John Doe", "MAD Developer", 1, false);
//            newUser.setName("Name " + randomInt);
//            newUser.setDescription("Description " + randomInt);
//            newUser.setId(randomInt);
//            newUser.setFollowed(randomFollowedBool);
//
//            userArrayList.add(newUser);
//        }

        RecyclerView recyclerView = findViewById(R.id.RecyclerView1);
        //UserAdapter mAdapter = new UserAdapter(userArrayList, this);
        UserAdapter mAdapter = new UserAdapter(handler.getUsers(), this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}