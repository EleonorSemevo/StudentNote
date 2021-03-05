package com.memoire.studentnote.classes;

public class Information {
    private int id;
    private String description;
    private String chemin;
    private String  datePublication;
    private int idEcole;
    private int idAuteur;
    private String ecole;
    private String auteur;

    public Information(int id, String description, String chemin, String datePublication, int idEcole, int idAuteur) {
        this.id = id;
        this.description = description;
        this.chemin = chemin;
        this.datePublication = datePublication;
        this.idEcole = idEcole;
        this.idAuteur = idAuteur;
    }

    public Information(int id, String description, String chemin, String datePublication, int idEcole, int idAuteur, String ecole, String auteur) {
        this.id = id;
        this.description = description;
        this.chemin = chemin;
        this.datePublication = datePublication;
        this.idEcole = idEcole;
        this.idAuteur = idAuteur;
        this.ecole = ecole;
        this.auteur = auteur;
    }


    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }



    public int getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(int idEcole) {
        this.idEcole = idEcole;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
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
