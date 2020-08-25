package com.memoire.studentnote.classes;

public class Etudier {
    private String idEleve;
    private String idClasse;
    private String idEcole;
    private int annee;

    public Etudier(String idEleve, String idClasse, String idEcole, int annee) {
        this.idEleve = idEleve;
        this.idClasse = idClasse;
        this.idEcole = idEcole;
        this.annee = annee;
    }

    public String getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(String idEleve) {
        this.idEleve = idEleve;
    }

    public String getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(String idClasse) {
        this.idClasse = idClasse;
    }

    public String getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(String idEcole) {
        this.idEcole = idEcole;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
}
