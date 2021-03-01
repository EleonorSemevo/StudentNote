package com.memoire.studentnote.classes;

public class Classe {
    private int id;
    private String nom;
    private int idEcole;


    public Classe(int id, String nom, int idEcole) {
        this.id = id;
        this.nom = nom;
        this.idEcole = idEcole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(int idEcole) {
        this.idEcole = idEcole;
    }
}
