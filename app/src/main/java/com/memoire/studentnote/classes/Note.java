package com.memoire.studentnote.classes;

public class Note {
    private String idMatiere;
    private String idEleve;
    private String idClasse;
    private String idEcole;
    private String type;
    private String dateComposition;
    private String description;

    public Note(String idMatiere, String idEleve, String idClasse, String idEcole, String type, String dateComposition, String description) {
        this.idMatiere = idMatiere;
        this.idEleve = idEleve;
        this.idClasse = idClasse;
        this.idEcole = idEcole;
        this.type = type;
        this.dateComposition = dateComposition;
        this.description = description;
    }

    public String getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(String idMatiere) {
        this.idMatiere = idMatiere;
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
