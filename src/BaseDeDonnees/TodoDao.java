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
        Cursor cursor = bdd.getReadableDatabase().rawQuery("Select * from Todo",null);
        cursor.moveToFirst();
        do
        {
            int id = cursor.getInt(4);
            String titre = cursor.getString(1);
            String description = cursor.getString(2);
            String jour = cursor.getString(3);
            String mois = cursor.getString(3);
            String annee = cursor.getString(3);
            int heure = cursor.getInt(4);
            int minute = cursor.getInt(5);
            list.add(new Todo(jour,mois,annee,minute,heure,titre,description,id));

        }
        while (cursor.moveToNext());
        System.out.println("\n################################################\n"+list+"\n##############################################");
        return  list;
    }
}
