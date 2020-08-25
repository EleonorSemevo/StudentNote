package com.memoire.studentnote.classes;

public class Ecole {
    private String id;
    private String nom;
    private String ville;
    private String quartier;
    private String type;
    private String code;

    public Ecole(String id, String nom, String ville, String quartier, String type, String code) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.quartier = quartier;
        this.type = type;
        this.code = code;
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
