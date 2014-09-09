package BaseDeDonnees;

import android.content.Context;
import android.database.Cursor;
import fr.kwidz.ToDo.Todo;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by kwidz on 07/09/14.
 */
public class TodoDao {
    /**
     * Atributs
     */
    private BaseDeDonnees bdd;

    /*
     * Constructor
     * It creates an instance of BDD to create the Data base
     */

    public TodoDao(Context contexte){

        bdd = new BaseDeDonnees(contexte);
    }

    public ArrayList<Todo> getTodos(){
        ArrayList<Todo> list = new ArrayList<Todo>();
        Cursor cursor = bdd.getReadableDatabase().rawQuery("Select * from Todo Order By dateTodo",null);
        cursor.moveToFirst();
        do
        {
            int id = cursor.getInt(0);
            String titre = cursor.getString(1);
            String description = cursor.getString(2);
            String date = cursor.getString(3);

            list.add(new Todo(date,titre,description,id));

        }
        while (cursor.moveToNext());
        System.out.println("\n################################################\n"+list+"\n##############################################");
        return  list;
    }
}
