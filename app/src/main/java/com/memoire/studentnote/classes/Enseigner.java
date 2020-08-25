package com.memoire.studentnote.classes;

public class Enseigner {
    private String idEnseignant;
    private String idMatiere;
    private String idEcole;


    public Enseigner(String idEnseignant, String idMatiere, String idEcole) {
        this.idEnseignant = idEnseignant;
        this.idMatiere = idMatiere;
        this.idEcole = idEcole;
    }

    public String getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(String idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(String idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(String idEcole) {
        this.idEcole = idEcole;
    }
}
