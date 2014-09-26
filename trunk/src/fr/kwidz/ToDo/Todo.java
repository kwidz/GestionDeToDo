package fr.kwidz.ToDo;

import java.util.StringTokenizer;

/**
 * Created by kwidz on 07/09/14.
 * Cette classe permet de représenter un Todo,
 * ce qui permetra plus facilement de
 * l'inclure dans la base de donnée et de le représenter pour modifications ou suppression
 **/
public class Todo {

    /**
     * atributs
     */

    private String date,titre, description;
    private int id;
    private int mois, annee, jourDuMois,minute, heure;
    private boolean pm;
    private String listeEmails;

    /**
     * Constructeur
     * @param date
     * @param titre
     * @param description
     * @param listeEmails
     */

    public Todo(String date,String titre, String description, String listeEmails){
        this.date=date;
        this.titre=titre;
        this.description=description;
        this.listeEmails = listeEmails;
        setElementsDeDate();
    }

    /**
     * Constructeur avec identifiant
     * @param date
     * @param titre
     * @param description
     * @param id
     * @param listeEmails
     */

    public Todo(String date,String titre, String description, int id, String listeEmails){
        this.date=date;
        this.titre=titre;
        this.description=description;
        this.id=id;
        this.listeEmails=listeEmails;

        setElementsDeDate();
    }

    /**
     * découpe la date en plusieurs variables pour un accès direct au jours, mois, année, heure et minutes
     * sans avoir de découpage à faire
     */

    private void setElementsDeDate(){
        StringTokenizer decoupeurDeChaine = new StringTokenizer(this.date," ");
        String petiteDate = new String(decoupeurDeChaine.nextToken().toString());
        System.out.println("################# " + petiteDate);
        String heureString = new String(decoupeurDeChaine.nextToken().toString());
        System.out.println("################# " + heureString);
        decoupeurDeChaine = new StringTokenizer(petiteDate,"-");
        annee = Integer.parseInt(decoupeurDeChaine.nextElement().toString());
        mois = Integer.parseInt(decoupeurDeChaine.nextElement().toString());
        jourDuMois = Integer.parseInt(decoupeurDeChaine.nextElement().toString());
        decoupeurDeChaine = new StringTokenizer(heureString,":");
        int heuretmp = Integer.parseInt(decoupeurDeChaine.nextElement().toString());
        if (postMeridiem(heuretmp)){
            this.heure=heuretmp - 12;
            pm = true;
        }
        else{
            pm =false;
        }
        minute = Integer.parseInt(decoupeurDeChaine.nextElement().toString());


    }

    /**
     * Permet de savoir si une heure est située dans l'après-midi ou le matin
     * @param heure
     * @return
     */

    private boolean postMeridiem(int heure){
        if (heure>12)
            return true;
        return false;
    }

    /*
     * Getters and setters de la classe
     */

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMois() {return mois; }

    public int getAnnee() {
        return annee;
    }

    public int getJourDuMois() {return jourDuMois;}

    public int getMinute() {return minute; }

    public int getHeure() {return heure; }

    public boolean isPm() {return pm; }

    public String getListeEmails() {return listeEmails; }

    public void setListeEmails(String listeEmails) {this.listeEmails = listeEmails; }

    /**
     * Methode toString
     * @return
     */

    @Override
    public String toString() {
        return date+";"+titre+";"+description+":"+id;
    }

}
