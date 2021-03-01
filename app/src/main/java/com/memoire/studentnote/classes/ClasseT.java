package com.memoire.studentnote.classes;

public class ClasseT {
    private int id;
    private String nom;
    private Ecole ecole;


    public ClasseT(int id, String nom, Ecole ecole) {
        this.id = id;
        this.nom = nom;

        this.ecole = ecole;
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

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }
}
