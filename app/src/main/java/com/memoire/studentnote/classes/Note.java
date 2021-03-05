package com.memoire.studentnote.classes;

public class Note {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private int idMatiere;
    private int idEleve;
    private int idClasse;
    private int idEcole;

    private String type;
    private String dateComposition;
    private String description;
    private int anneeScolaire;

    private double note;

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }



    public int getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(int anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }



    public Note(int idMatiere, int idEleve, int idClasse, int idEcole, String type,
                String dateComposition, String description, int anneeScolaire, double note) {
        this.idMatiere = idMatiere;
        this.idEleve = idEleve;
        this.idClasse = idClasse;
        this.idEcole = idEcole;
        this.type = type;
        this.dateComposition = dateComposition;
        this.description = description;
        this.anneeScolaire = anneeScolaire;
        this.note = note;
    }

    public Note(int id, int idMatiere, int idEleve, int idClasse, int idEcole, String type,
                String dateComposition, String description, int anneeScolaire, double note) {
        this.idMatiere = idMatiere;
        this.idEleve = idEleve;
        this.idClasse = idClasse;
        this.idEcole = idEcole;
        this.type = type;
        this.dateComposition = dateComposition;
        this.description = description;
        this.anneeScolaire = anneeScolaire;
        this.note = note;
        this.id = id;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public int getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(int idEleve) {
        this.idEleve = idEleve;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public int getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(int idEcole) {
        this.idEcole = idEcole;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateComposition() {
        return dateComposition;
    }

    public void setDateComposition(String dateComposition) {
        this.dateComposition = dateComposition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
