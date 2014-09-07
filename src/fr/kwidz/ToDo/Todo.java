package fr.kwidz.ToDo;

/**
 * Created by kwidz on 07/09/14.
 * Cette classe permet de représenter un Todo,
 * ce qui permetra plus facilement de
 * l'inclure dans la base de donnée et de le représenter pour modifications ou suppression
 **/
public class Todo {
    private String jour, mois, annee,titre, description;
    private int id, minutes, heure;

    public Todo(String jour, String mois,String annee, int minutes, int heures,String titre, String description){
        this.jour = jour;
        this. mois = mois;
        this.annee=annee;
        this.minutes=minutes;
        this.heure=heures;
        this.titre=titre;
        this.description=description;
    }

    public Todo(String jour, String mois,String annee, int minutes, int heures,String titre, String description, int id){
        this.jour = jour;
        this. mois = mois;
        this.annee=annee;
        this.minutes=minutes;
        this.heure=heures;
        this.titre=titre;
        this.description=description;
        this.id=id;
    }


    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "jour='" + jour + '\'' +
                ", mois='" + mois + '\'' +
                ", annee='" + annee + '\'' +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", minutes=" + minutes +
                ", heure=" + heure +
                '}';
    }
}
