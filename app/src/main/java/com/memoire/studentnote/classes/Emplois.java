package com.memoire.studentnote.classes;

public class Emplois {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int idEcole;
    private int idClasse;
    private int idMatiere;
    private String jour;
    private String heure_debut;
    private String heure_fin;
    private int anneScolaire;

    public Emplois(int id, int idEcole, int idClasse, int idMatiere, String jour, String heure_debut, String heure_fin, int anneScolaire) {
        this.idEcole = idEcole;
        this.idClasse = idClasse;
        this.idMatiere = idMatiere;
        this.jour = jour;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.anneScolaire = anneScolaire;
        this.id = id;
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

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(String heure_debut) {
        this.heure_debut = heure_debut;
    }

    public String getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(String heure_fin) {
        this.heure_fin = heure_fin;
    }

    public int getAnneScolaire() {
        return anneScolaire;
    }

    public void setAnneScolaire(int anneScolaire) {
        this.anneScolaire = anneScolaire;
    }
}
