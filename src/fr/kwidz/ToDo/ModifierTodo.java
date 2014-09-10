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
        String id = extras.getString("idTodo");
        final EditText titre = (EditText)findViewById(R.id.titreM);
        final EditText description = (EditText)findViewById(R.id.descriptionM);
        final DatePicker date = (DatePicker) findViewById(R.id.dateM);
        final TimePicker heure;



        heure = (TimePicker) findViewById(R.id.timePickerM);
        System.out.println("#######################"+id);
        final Todo todo = todoDao.selectTodo(id);
        titre.setText(todo.getTitre());
        description.setText(todo.getDescription());
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                modifierTodo(titre.getText().toString(), description.getText().toString(),
                        "" + date.getYear() + "-" + (date.getMonth() + 1) + "-" + date.getDayOfMonth() + "-" + heure.getCurrentHour() + "-" + heure.getCurrentMinute(),todo.getId());
            }
        });
    }
    private void modifierTodo(String titre, String desc, String date, int id) {

        Todo todo = new Todo(date, titre, desc, id);
        todoDao.update(todo);
        AlertDialog alertDialog = new AlertDialog.Builder(
                this).create();
        alertDialog.setTitle("Le todo à été Modifié !");
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
        startActivity(nav);
    }
}