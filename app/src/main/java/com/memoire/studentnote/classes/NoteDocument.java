package com.memoire.studentnote.classes;

public class NoteDocument {
    private int idMatiere;
    private int idEcole;
    private int idClass;
    private int idDocument;
    private String type;
    private String dateComposition;
    private String location;
    private int numeroComposition;

    public NoteDocument(int idMatiere, int idEcole, int idClass, int idDocument, String type, String dateComposition, String location, int numeroComposition) {
        this.idMatiere = idMatiere;
        this.idEcole = idEcole;
        this.idClass = idClass;
        this.idDocument = idDocument;
        this.type = type;
        this.dateComposition = dateComposition;
        this.location = location;
        this.numeroComposition = numeroComposition;
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

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumeroComposition() {
        return numeroComposition;
    }

    public void setNumeroComposition(int numeroComposition) {
        this.numeroComposition = numeroComposition;
    }
}
