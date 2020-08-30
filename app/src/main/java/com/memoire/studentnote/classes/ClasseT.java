package com.memoire.studentnote.classes;

public class ClasseT {
    private String id;
    private String nom;
    private Ecole ecole;


    public ClasseT(String id, String nom, Ecole ecole) {
        this.id = id;
        this.nom = nom;

        this.ecole = ecole;
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

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }
}
