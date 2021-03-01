package com.memoire.studentnote.classes;

import android.os.Parcel;

import androidx.annotation.NonNull;

public class Etudier implements android.os.Parcelable{

    private int id;
    private int idEleve;
    private int idClasse;
    private int idEcole;
    private int annee;


    public Etudier(int id, int idEleve, int idClasse, int idEcole, int annee) {
        this.id = id;
        this.idEleve = idEleve;
        this.idClasse = idClasse;
        this.idEcole = idEcole;
        this.annee = annee;
    }

    protected Etudier(Parcel in) {
        id = in.readInt();
        idEleve = in.readInt();
        idClasse = in.readInt();
        idEcole = in.readInt();
        annee = in.readInt();
    }

    public static final Creator<Etudier> CREATOR = new Creator<Etudier>() {
        @Override
        public Etudier createFromParcel(Parcel in) {
            return new Etudier(in);
        }

        @Override
        public Etudier[] newArray(int size) {
            return new Etudier[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    private String getCompareKey()
    {
        return   id + "|"+ idEleve + "|" + idClasse +"|" +idEcole +"|" + annee ;
    }

    @NonNull
    @Override
    public String toString() {
        return  getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(idEleve);
        dest.writeInt(idClasse);
        dest.writeInt(idEcole);
        dest.writeInt(annee);
    }
}
