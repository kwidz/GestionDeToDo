package fr.kwidz.ToDo;

import BaseDeDonnees.TodoDao;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Home extends Activity implements AdapterView.OnItemLongClickListener {

    private TodoDao todoDao;
    private Button button;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        todoDao = new TodoDao(this);
        afficherTodos();
        remplirList();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterTodo(v);
            }
        });
        ListView listView=(ListView) findViewById(R.id.listeTodos);


    }

    public void afficherTodos(){
        todoDao.getTodos();

    }

    public void remplirList(){
        ArrayList<Todo> l = todoDao.getTodos();
        ArrayList<String> list = new ArrayList<String>();
        Iterator itr = l.listIterator();

        while (itr.hasNext())
            list.add(itr.next().toString());
        System.out.println(list);

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        System.out.println(adapter);
        ListView listView1=(ListView) findViewById(R.id.listeTodos);
        listView1.setAdapter(adapter);
        listView1.setOnItemLongClickListener(this);
    }

    public void ajouterTodo(View v){
        Intent nav = new Intent(this,AjouterTodo.class);
        startActivity(nav);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Que voulez vous faire ?");
        dialog.setPositiveButton("Modifier",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ;
            }
        });
        dialog.setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ;
            }
        });
        dialog.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ;
            }
        });
        dialog.show();
        return false;
    }



    private class StableArrayAdapter extends ArrayAdapter {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }
    }
}


