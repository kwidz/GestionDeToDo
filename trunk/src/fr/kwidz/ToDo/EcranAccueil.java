package fr.kwidz.ToDo;

import BaseDeDonnees.TodoDao;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.*;

/**
 * Classe principale (c'est la première activity qui s'ouvre dans l'application)
 * Created by kwidz on 20/09/14.
 */

public class EcranAccueil extends Activity{

    private TodoDao todoDao;
    private Button bouton;


    /**
     * Fonction de création de l'activity
     * Cette Activity contient une liste des todos
     * Si on clique sur un todo, on arrive dans une activité de
     * modification du todo
     * Si on effectue un appui long sur un todo, on a une fenêtre
     * qui nous demande si on veut le supprimer
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        todoDao = new TodoDao(this);
        remplirListe();
        bouton = (Button) findViewById(R.id.boutonajouter);

        //écouteur d'évenement du bouton "bouton"

        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterTodo(v);
            }
        });
        ListView listView=(ListView) findViewById(R.id.listeTodos);

        //try/catch pour éviter de faire planter l'application lors du démarage de l'activité
        //sans creer de todo

        try {
            Bundle extras = this.getIntent().getExtras();
            String[] destinataires = new String[100];
            StringTokenizer delimiteur = new StringTokenizer(extras.get("listeEmail").toString(),";/");
            int i = 0;

            //on mets les plusieurs adresses email dans un tableau pour envoyer des mails à plusieurs personnes a la fois

            while(delimiteur.hasMoreTokens()){
                destinataires[i]=delimiteur.nextToken();
                i++;
            }

            //envoi de l'email

            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));

            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, extras.get("listeEmail").toString());
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Notification application todo");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Bonjour vous avez été identifié dans la tache suivante :\n"
                    + extras.get("titre").toString()
                    + "\nqui consiste en :\n"
                    + extras.get("description").toString() + "\n le :\n"
                    + extras.get("heure").toString());
            startActivity(Intent.createChooser(emailIntent, "Envoi email"));
        }
        catch(Exception e){
            System.out.print("premier passage");
        }


    }

    /**
     * Fonction qui remplit le la liste de todo
     */

    public void remplirListe(){

        // On vérifie si il y a des todos

        if (todoDao.todoOuPas()) {

            SimpleAdapter adapter = new SimpleAdapter(
                    this,
                    todoDao.listerTodos(),
                    android.R.layout.two_line_list_item,
                    new String[]{"titre", "description", "date"},
                    new int[]{android.R.id.text1, android.R.id.text2, android.R.id.text2}
            );

            //on remplis la vue

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

    /**
     * Fonction pour aller à l'Activité ajouterTodo
     * @param v
     */
    public void ajouterTodo(View v){
        Intent naviguerAjouter = new Intent(this,AjouterTodo.class);
        startActivity(naviguerAjouter);
    }



}


