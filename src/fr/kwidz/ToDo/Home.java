package fr.kwidz.ToDo;

import BaseDeDonnees.TodoDao;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Home extends Activity {

    private TodoDao todoDao;

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
        ListView listView = (ListView) findViewById(R.id.listeTodos);
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }



    private class StableArrayAdapter extends ArrayAdapter {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }
    }
}


