package fr.kwidz.ToDo;

import BaseDeDonnees.TodoDao;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.*;

public class Home extends Activity{

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

    public void remplirList(){


        SimpleAdapter adapter = new SimpleAdapter(
                this,
                todoDao.listerTodos(),
                android.R.layout.two_line_list_item,
                new String[]{"titre","description","date"},
                new int[]{android.R.id.text1,android.R.id.text2,android.R.id.text2}
        );

        final ListView listView1=(ListView) findViewById(R.id.listeTodos);
        listView1.setAdapter(adapter);
        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
                dialog.setTitle("Que voulez vous faire ?");
                final HashMap<String,String> todo = (HashMap<String, String>) listView1.getItemAtPosition((int)id);
                dialog.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ;
                    }
                });
                dialog.setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        todoDao.delete(todo.get("id"));
                        Intent refresh = new Intent(Home.this, Home.class);
                        startActivity(refresh);
                    }
                });
                dialog.show();
                return false;
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> todo = (HashMap<String, String>) listView1.getItemAtPosition((int)id);
                Intent goToModifierTodo = new Intent(Home.this, ModifierTodo.class);
                goToModifierTodo.putExtra("idTodo", todo.get("id"));
                startActivity(goToModifierTodo);
            }
        });

    }

    public void ajouterTodo(View v){
        Intent nav = new Intent(this,AjouterTodo.class);
        startActivity(nav);
    }



}


