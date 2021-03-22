package com.memoire.studentnote.classes;

import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MesEnfants implements android.os.Parcelable {
    private int id;
    private int idParent;
    private int idEleve;
    private String nom;
    private String prenom;
    private int idEcole;
    private String nomEcole;
    private int  idclasse;
    private String classe;

    //
    private String quartierEcole;
    private String villeEcole;
    public String getVilleEcole() {
        return villeEcole;
    }

    public void setVilleEcole(String villeEcole) {
        this.villeEcole = villeEcole;
    }



    public String getQuartierEcole() {
        return quartierEcole;
    }

    public void setQuartierEcole(String quartierEcole) {
        this.quartierEcole = quartierEcole;
    }




    public MesEnfants(int id, int idParent, int idEleve, String nom, String prenom, int idEcole, String nomEcole, int idclasse, String classe) {
        this.id = id;
        this.idParent = idParent;
        this.idEleve = idEleve;
        this.nom = nom;
        this.prenom = prenom;
        this.idEcole = idEcole;
        this.nomEcole = nomEcole;
        this.idclasse = idclasse;
        this.classe = classe;
    }

    protected MesEnfants(Parcel in) {
        id = in.readInt();
        idParent = in.readInt();
        idEleve = in.readInt();
        nom = in.readString();
        prenom = in.readString();
        idEcole = in.readInt();
        nomEcole = in.readString();
        idclasse = in.readInt();
        classe = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(idParent);
        dest.writeInt(idEleve);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeInt(idEcole);
        dest.writeString(nomEcole);
        dest.writeInt(idclasse);
        dest.writeString(classe);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MesEnfants> CREATOR = new Creator<MesEnfants>() {
        @Override
        public MesEnfants createFromParcel(Parcel in) {
            return new MesEnfants(in);
        }

        @Override
        public MesEnfants[] newArray(int size) {
            return new MesEnfants[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public int getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(int idEleve) {
        this.idEleve = idEleve;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(int idEcole) {
        this.idEcole = idEcole;
    }

    public String getNomEcole() {
        return nomEcole;
    }

    public void setNomEcole(String nomEcole) {
        this.nomEcole = nomEcole;
    }

    public int getIdclasse() {
        return idclasse;
    }

    public void setIdclasse(int idclasse) {
        this.idclasse = idclasse;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }


    private String getCompareKey()
    {
        return   id +"|"+ idParent+"|"+ nom + "|" + prenom +"|" +idEcole +"|" + nomEcole +"|" +
                idclasse + "|" +classe;
    }

    @NonNull
    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        MesEnfants enfants=(MesEnfants) obj;
        if(this.id==enfants.getId()
        && this.idEleve==enfants.getIdEleve())
            return true;
        else
            return false;
    }
}
