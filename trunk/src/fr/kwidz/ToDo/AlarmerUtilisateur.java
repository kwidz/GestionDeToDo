package fr.kwidz.ToDo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

/**
 * Activité qui se lance au moment de l'alarme
 * Created by kwidz on 20/09/14.
 */
public class AlarmerUtilisateur extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmerutilisateur);
        Bundle extras = this.getIntent().getExtras();
        String titre = extras.getString("titre");
        TextView texteAlarme = (TextView)findViewById(R.id.texteAlarme);
        texteAlarme.setText("Il est maintenant temps d'effectuer la tâche : \n"+titre+"\n\n");

    }
}