package fr.kwidz.ToDo;

import BaseDeDonnees.TodoDao;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;

/**
 * Activité d'ajout d'un todo
 * Created by kwidz on 08/09/14.
 */
public class AjouterTodo extends Activity {

    /**
     * Creation de l'Activité d'ajout d'un nouveau todo
     * @param savedInstanceState
     */

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
                EditText listeEmails = (EditText)findViewById(R.id.listeEmails);
                ajouterTodo(titre.getText().toString(), description.getText().toString(),
                        "" + date.getYear() + "-" + (date.getMonth() + 1) + "-" + date.getDayOfMonth() + " " + heure.getCurrentHour() + ":" + heure.getCurrentMinute()+":00",description.getText().toString());
            }
        });
    }

    /**
     * Fonction permetant d'ajouter un todo
     * @param titre titre du todo a ajouter
     * @param desc description du todo a ajouter
     * @param date date du todo a ajouter au format aaaa-mm-jj hh:mm
     * @param listeEmails liste d'emails pour les contacts a ajouter
     */

    private void ajouterTodo(String titre, String desc, String date, String listeEmails) {
        TodoDao todoDao = new TodoDao(this);
        final Todo todo = new Todo(date, titre,desc, listeEmails);
        todoDao.insererUnTodo(todo);
        activerAlarme(todo);
        AlertDialog boiteDeDialogue = new AlertDialog.Builder(
                this).create();
        boiteDeDialogue.setTitle("Le todo à été ajouté !");
        boiteDeDialogue.setMessage(titre + "\n" + desc + "\n" + date);
        boiteDeDialogue.setIcon(android.R.drawable.btn_star);
        boiteDeDialogue.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                retour(todo);
            }
        });

        boiteDeDialogue.show();

    }

    /**
     * Fonction qui permet de retourner à l'écran principal
     * @param todo on passe en argument le todo ajouté afin de permettre d'envoyer un email
     */

    private void retour(Todo todo){
        Intent naviguerAccueil = new Intent(this, EcranAccueil.class);
        naviguerAccueil.putExtra("titre", todo.getTitre());
        naviguerAccueil.putExtra("description", todo.getDescription());
        String pmAm;
        if(todo.isPm())
            pmAm=new String("Pm");
        else
            pmAm=new String("Am");
        naviguerAccueil.putExtra("heure", todo.getAnnee() + "-" + todo.getMois() + "-" + todo.getJourDuMois() + " à " + todo.getHeure() + ":" + todo.getMinute() +" "+ pmAm);
        naviguerAccueil.putExtra("listeEmail",todo.getListeEmails());
        this.finish();
        startActivity(naviguerAccueil);
    }

    /**
     * Fonction permettant d'activer l'alarme
     * @param todo todo pour lequel on veut activer l'alarme
     */

    private void activerAlarme(Todo todo) {
        Intent alarmerUtil = new Intent(this, AlarmerUtilisateur.class);
        alarmerUtil.putExtra("titre", todo.getTitre());
        PendingIntent intentionAlarmerUtilisateur
                = PendingIntent.getActivities(this, 1, new Intent[]{alarmerUtil},
                FLAG_CANCEL_CURRENT);
        Calendar calendrier = instancierLeCalendrier(todo);
        System.out.println("alarme activée");


        AlarmManager gestionaireAlarme = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);

        long decalage = Calendar.getInstance().getTimeInMillis() - System.currentTimeMillis();
        long moment = calendrier.getTimeInMillis();
        //long tempsEnMilli = 10000;
        Log.e("Time Class ", " temps de decalage " + (decalage));
        Log.e("Time Class ", " temps avant l'alarme en Millisecondes "+((moment)));
        gestionaireAlarme.set(AlarmManager.RTC_WAKEUP, moment + decalage, intentionAlarmerUtilisateur);
    }

    /**
     * Fonction permettant de retourner un Calendar instancié a la date du todo passé en parametre
     * @param todo todo pour lequel on veut instancier un calendrier
     * @return Calendar : calendrier retourné par la fonction
     */

    private Calendar instancierLeCalendrier(Todo todo){
        Calendar calendrier = Calendar.getInstance();
        System.out.println(todo.getAnnee()+"-"+todo.getMois()+"-"+todo.getJourDuMois()+"-"+todo.getHeure()+"-"+todo.getMinute()+"  :  "+todo.isPm());
        calendrier.set(Calendar.MONTH, todo.getMois()-1);
        calendrier.set(Calendar.YEAR, todo.getAnnee());
        calendrier.set(Calendar.DAY_OF_MONTH, todo.getJourDuMois());

        calendrier.set(Calendar.HOUR_OF_DAY, todo.getHeure());
        calendrier.set(Calendar.MINUTE, todo.getMinute());
        calendrier.set(Calendar.SECOND, 0);
        if(todo.isPm())
            calendrier.set(Calendar.AM_PM,Calendar.PM);
        else
            calendrier.set(Calendar.AM_PM,Calendar.AM);
        return calendrier;
    }
}