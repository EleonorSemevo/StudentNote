package com.memoire.studentnote.classes;

public class Information {
    private String id;
    private String description;
    private String chemin;
    private String  datePublication;

    public Information(String id, String description, String chemin, String datePublication) {
        this.id = id;
        this.description = description;
        this.chemin = chemin;
        this.datePublication = datePublication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }
}
