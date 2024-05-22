package sg.edu.np.mad.madpractical5;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Random;

import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FOLLOWED = "followed";

    public DatabaseHandler(
            Context context, String name,
            SQLiteDatabase.CursorFactory factory,
            int version
    ) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USER + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_FOLLOWED + " INTEGER)";
        db.execSQL(CREATE_USERS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    //  Add a user record
    public void addUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        // Don't need this. ID is auto create and incremented by SQLite
        // values.put(COLUMN_ID, String.valueOf(user.getID()));
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void createUsers() {
        for (int i = 0; i < 20; i++) {
            int randomInt = new Random().nextInt(1000000);
            boolean randomFollowedBool = new Random().nextBoolean();

            User newUser = new User("John Doe", "MAD Developer", 1, false);
            newUser.setName("Name " + randomInt);
            newUser.setDescription("Description " + randomInt);
            newUser.setId(randomInt);
            newUser.setFollowed(randomFollowedBool);

            this.addUser(newUser);
        }
    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_USER;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<User> users = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                int followed = cursor.getInt(3);

                users.add(new User(name, description, id, followed > 0));
                //cursor.moveToNext();
                Log.v("Database Operations", name + " " + id);
            } while (cursor.moveToNext());
        }
        return users;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        db.update(TABLE_USER, values, COLUMN_NAME+"=?", new String[]{user.getName()});

//        String checkFollowed = "false";
//        if (user.getFollowed()) checkFollowed = "true";
//        Log.v("Database Operations", checkFollowed);
    }
}
