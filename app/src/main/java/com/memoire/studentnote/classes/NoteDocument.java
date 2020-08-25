package com.memoire.studentnote.classes;

public class NoteDocument {
    private String idMatiere;
    private String idEcole;
    private String idClass;
    private String idDocument;
    private String type;
    private String dateComposition;
    private String location;
    private int numeroComposition;

    public NoteDocument(String idMatiere, String idEcole, String idClass, String idDocument, String type, String dateComposition, String location, int numeroComposition) {
        this.idMatiere = idMatiere;
        this.idEcole = idEcole;
        this.idClass = idClass;
        this.idDocument = idDocument;
        this.type = type;
        this.dateComposition = dateComposition;
        this.location = location;
        this.numeroComposition = numeroComposition;
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

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(String idDocument) {
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
