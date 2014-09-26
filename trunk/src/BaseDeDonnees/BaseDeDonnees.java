package BaseDeDonnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe de création de la Base de données
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
        String CREATE_TABLE = "create table Todo(id_Todo INTEGER PRIMARY KEY, title TEXT, description TEXT, dateTodo datetime, listeEmails TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}