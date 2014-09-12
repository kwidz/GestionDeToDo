package BaseDeDonnees;

import android.content.Context;
import android.database.Cursor;
import fr.kwidz.ToDo.Todo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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

        bdd = new BaseDeDonnees(contexte) ;
    }

    public ArrayList listerTodos(){
        ArrayList<HashMap<String,String>> listeTodos = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> hash;
        Cursor cursor = bdd.getReadableDatabase().rawQuery("Select * from Todo where dateTodo>=(select (DATETIME('now'))) Order By dateTodo",null);
        cursor.moveToFirst();
        do {
            hash = new HashMap<String, String>();
            hash.put("titre",cursor.getString(1));
            hash.put("description",cursor.getString(2));

            hash.put("date",cursor.getString(3));
            hash.put("id",cursor.getString(0));
            listeTodos.add(hash);

        }while(cursor.moveToNext());
        return listeTodos   ;

    }

    public boolean todoOuPas(){
        Cursor cursor = bdd.getReadableDatabase().rawQuery( "select (DATETIME('now'))",null);
        cursor.moveToFirst();
        System.out.println("##################################### "+cursor.getString(0));
        cursor = bdd.getReadableDatabase().rawQuery("Select count(*),dateTodo from Todo where (select (DATETIME('now'))) < dateTodo",null);
        cursor.moveToFirst();
        System.out.println(cursor.getString(0)+"##################################### "+cursor.getString(1));
        if (cursor.getInt(0)>0)
            return true;
        return false;
    }

    public Todo selectTodo(String id){
        String SQL = new String("Select * from Todo where id_Todo="+id);
        System.out.println("########################\n"+SQL);
        Cursor cursor = bdd.getReadableDatabase().rawQuery(SQL,null);
        cursor.moveToFirst();
        System.out.println("##################"+cursor.getString(3)+cursor.getString(1)+cursor.getString(2));
        return new Todo(cursor.getString(3),cursor.getString(1),cursor.getString(2),(cursor.getInt(0)));
    }

    public void insertInto(Todo todo){
        String INSERT_INTO = new String("Insert into Todo(title,description,dateTodo) VALUES('"+todo.getTitre()+"','"+todo.getDescription()+"','"+todo.getDate()+"')");
        bdd.getWritableDatabase().execSQL(INSERT_INTO);

    }
    public void delete(String id){
        String DELETE_FROM = new String("Delete from Todo where id_Todo ="+id);
        bdd.getWritableDatabase().execSQL(DELETE_FROM);
    }
    public void update(Todo todo){
        String UPDATE = new String("UPDATE Todo " +
                "SET title = '"+todo.getTitre()+"',description = '" +todo.getDescription()+ "', dateTodo = '"+todo.getDate()+"'"+
                " WHERE id_Todo="+Integer.toString(todo.getId()));
        System.out.println("#######################################\n"+UPDATE);
        bdd.getWritableDatabase().execSQL(UPDATE);


    }
}
