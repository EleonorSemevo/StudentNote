package com.memoire.studentnote.classes;

public class Enfant {
    private int idEleve;
    private int idEcole;
    private int idclasse;
    private String nomPrenom;
    private String classe;
    private String ecole;

    public Enfant(int idEleve, int idEcole, int idclasse, String nomPrenom, String classe, String ecole) {
        this.idEleve = idEleve;
        this.idEcole = idEcole;
        this.idclasse = idclasse;
        this.nomPrenom = nomPrenom;
        this.classe = classe;
        this.ecole = ecole;
    }

    public int getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(int idEleve) {
        this.idEleve = idEleve;
    }

    public int getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(int idEcole) {
        this.idEcole = idEcole;
    }

    public int getIdclasse() {
        return idclasse;
    }

    public void setIdclasse(int idclasse) {
        this.idclasse = idclasse;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }
}
