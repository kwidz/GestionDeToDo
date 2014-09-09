package fr.kwidz.ToDo;

/**
 * Created by kwidz on 07/09/14.
 * Cette classe permet de représenter un Todo,
 * ce qui permetra plus facilement de
 * l'inclure dans la base de donnée et de le représenter pour modifications ou suppression
 **/
public class Todo {
    private String date,titre, description;
    private int id;

    public Todo(String date,String titre, String description){
        this.date=date;
        this.titre=titre;
        this.description=description;
    }

    public Todo(String date,String titre, String description, int id){
        this.date=date;
        this.titre=titre;
        this.description=description;
        this.id=id;
    }

    public String getDate() {
        return date;
    }

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

    @Override
    public String toString() {
        return "Todo{" +
                "date='" + date + '\'' +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
