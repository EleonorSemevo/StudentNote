package com.memoire.studentnote.classes;

public class Enseigner {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int idEnseignant;
    private int idMatiere;
    private int idEcole;
    private int idClasse;



    public Enseigner(int id, int idEnseignant, int idMatiere, int idEcole, int idClasse) {
        this.id=id;
        this.idEnseignant = idEnseignant;
        this.idMatiere = idMatiere;
        this.idEcole = idEcole;
        this.idClasse = idClasse;
    }

    public int getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public int getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(int idEcole) {
        this.idEcole = idEcole;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }
}
