package fr.kwidz.ToDo;

import BaseDeDonnees.TodoDao;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.*;

public class EcranAccueil extends Activity{

    private TodoDao todoDao;
    private Button bouton;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        todoDao = new TodoDao(this);
        remplirListe();
        bouton = (Button) findViewById(R.id.boutonajouter);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterTodo(v);
            }
        });
        ListView listView=(ListView) findViewById(R.id.listeTodos);


    }

    public void remplirListe(){

        if (todoDao.todoOuPas()) {

            SimpleAdapter adapter = new SimpleAdapter(
                    this,
                    todoDao.listerTodos(),
                    android.R.layout.two_line_list_item,
                    new String[]{"titre", "description", "date"},
                    new int[]{android.R.id.text1, android.R.id.text2, android.R.id.text2}
            );

            final ListView listeVueTodos = (ListView) findViewById(R.id.listeTodos);
            listeVueTodos.setAdapter(adapter);
            listeVueTodos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder petiteFenetre = new AlertDialog.Builder(EcranAccueil.this);
                    petiteFenetre.setTitle("Que voulez vous faire ?");
                    final HashMap<String, String> todo = (HashMap<String, String>) listeVueTodos.getItemAtPosition((int) id);
                    petiteFenetre.setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ;
                        }
                    });
                    petiteFenetre.setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            todoDao.supprimerUnTodo(todo.get("id"));
                            Intent refresh = new Intent(EcranAccueil.this, EcranAccueil.class);
                            EcranAccueil.this.finish();
                            startActivity(refresh);
                        }
                    });
                    petiteFenetre.show();
                    return true;
                }
            });
            listeVueTodos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HashMap<String, String> todo = (HashMap<String, String>) listeVueTodos.getItemAtPosition((int) id);
                    Intent goToModifierTodo = new Intent(EcranAccueil.this, ModifierTodo.class);
                    goToModifierTodo.putExtra("idTodo", todo.get("id"));
                    startActivity(goToModifierTodo);
                }
            });
        }

    }

    public void ajouterTodo(View v){
        Intent naviguerAjouter = new Intent(this,AjouterTodo.class);
        startActivity(naviguerAjouter);
    }



}


