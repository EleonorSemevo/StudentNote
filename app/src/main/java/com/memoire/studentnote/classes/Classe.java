package com.memoire.studentnote.classes;

public class Classe {
    private String id;
    private String nom;
    private String idEcole;


    public Classe(String id, String nom, String idEcole) {
        this.id = id;
        this.nom = nom;
        this.idEcole = idEcole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(String idEcole) {
        this.idEcole = idEcole;
    }
}
