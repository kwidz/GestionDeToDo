package fr.kwidz.ToDo;

import BaseDeDonnees.TodoDao;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * Activité de modification du todo
 * Created by kwidz on 09/09/14.
 */
public class ModifierTodo extends Activity {
    TodoDao todoDao = new TodoDao(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifiertodo);

        Button valider= (Button) findViewById(R.id.validerModifier);
        Bundle extras = this.getIntent().getExtras();
        String id_Todo = extras.getString("idTodo");
        final EditText titre = (EditText)findViewById(R.id.titreM);
        final EditText description = (EditText)findViewById(R.id.descriptionM);
        final DatePicker date = (DatePicker) findViewById(R.id.dateM);
        final TimePicker heure;



        heure = (TimePicker) findViewById(R.id.timePickerM);
        System.out.println("#######################"+id_Todo);
        final Todo todo = todoDao.SelectionerLesTodos(id_Todo);
        titre.setText(todo.getTitre());
        description.setText(todo.getDescription());

        //ecouteur d'événement sur le bouton ajouter

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                modifierTodo(titre.getText().toString(), description.getText().toString(),
                        "" + date.getYear() + "-" + (date.getMonth() + 1) + "-" + date.getDayOfMonth() + " " + heure.getCurrentHour() + ":" + heure.getCurrentMinute()+":00",todo.getId());
            }
        });
    }

    /**
     * Fonction de modification du todo
     *
     * @param titre titre du todo
     * @param desc description du todo
     * @param date date du todo
     * @param id identifiant du todo
     */

    private void modifierTodo(String titre, String desc, String date, int id) {

        Todo todo = new Todo(date, titre, desc, id, "hahah");
        todoDao.update(todo);
        AlertDialog boiteDeDialogue = new AlertDialog.Builder(
                this).create();
        boiteDeDialogue.setTitle("Le todo à été Modifié !");
        //boiteDeDialogue.setMessage("ho shit !");
        boiteDeDialogue.setMessage(titre + "\n" + desc + "\n" + date);
        boiteDeDialogue.setIcon(android.R.drawable.btn_star);
        boiteDeDialogue.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("\n#############################\nclicked\n#######################");
                retour();
            }
        });
        boiteDeDialogue.show();
    }

    /**
     *Fonction pour retourner a l'activité principale
     */

    private void retour(){
        Intent naviguerAccueil = new Intent(this, EcranAccueil.class);
        this.finish();
        startActivity(naviguerAccueil);
    }
}