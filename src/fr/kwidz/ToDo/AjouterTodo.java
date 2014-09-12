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
 * Created by kwidz on 08/09/14.
 */
public class AjouterTodo extends Activity {
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoutertodo);

        Button valider= (Button) findViewById(R.id.validerAjouter);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText titre = (EditText)findViewById(R.id.titre);
                EditText description = (EditText)findViewById(R.id.description);
                DatePicker date = (DatePicker) findViewById(R.id.date);
                TimePicker heure;
                heure = (TimePicker) findViewById(R.id.timePicker);
                ajouterTodo(titre.getText().toString(), description.getText().toString(),
                        "" + date.getYear() + "-" + (date.getMonth() + 1) + "-" + date.getDayOfMonth() + " " + heure.getCurrentHour() + ":" + heure.getCurrentMinute()+":00");
            }
        });
    }

    private void ajouterTodo(String titre, String desc, String date) {
        TodoDao todoDao = new TodoDao(this);
        Todo todo = new Todo(date, titre,desc);
        todoDao.insertInto(todo);
        AlertDialog alertDialog = new AlertDialog.Builder(
                this).create();
        alertDialog.setTitle("Le todo à été ajouté !");
        //alertDialog.setMessage("ho shit !");
        alertDialog.setMessage(titre+"\n"+desc+"\n"+date);
        alertDialog.setIcon(android.R.drawable.btn_star);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("\n#############################\nclicked\n#######################");
                retour();
            }
        });
        alertDialog.show();
    }
    private void retour(){
        Intent nav = new Intent(this, Home.class);
        this.finish();
        startActivity(nav);
    }
}