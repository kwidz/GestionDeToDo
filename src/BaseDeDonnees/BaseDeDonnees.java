package BaseDeDonnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kwidz on 07/09/14.
 */
public class BaseDeDonnees extends SQLiteOpenHelper {

    /*
     * Constructor
     */
    
    public BaseDeDonnees(Context context) {
        super(context,"ToDo" , null, 1);
    }

    /*
    * If not exists, it creates a new table
    */

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table Todo(id_Todo INTEGER PRIMARY KEY, title TEXT, description TEXT, dateTodo smalldatetime)";
        db.execSQL(CREATE_TABLE);
        String INSERT_TABLE = "Insert into Todo(title,description,dateTodo) VALUES('réveil','pour me réveiller le matin','2014-05-06-07-00-00')";
        db.execSQL(INSERT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}