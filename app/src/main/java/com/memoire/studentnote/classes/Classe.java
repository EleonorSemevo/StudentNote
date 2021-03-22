package com.memoire.studentnote.classes;

import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Classe implements android.os.Parcelable{
    private int id;
    private String nom;
    private int idEcole;


    public Classe(int id, String nom, int idEcole) {
        this.id = id;
        this.nom = nom;
        this.idEcole = idEcole;
    }

    public Classe()
    {}

    protected Classe(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        idEcole = in.readInt();
    }

    public static final Creator<Classe> CREATOR = new Creator<Classe>() {
        @Override
        public Classe createFromParcel(Parcel in) {
            return new Classe(in);
        }

        @Override
        public Classe[] newArray(int size) {
            return new Classe[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(int idEcole) {
        this.idEcole = idEcole;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
        dest.writeInt(idEcole);
    }

    private String getCompareKey()
    {
        return  "id: "+ id + " | nom: "+ nom + " | idEcole: " + idEcole;
    }

    @NonNull
    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass()!=obj.getClass())
            return false;
        Classe that = (Classe) obj;

        return getCompareKey().equals(that.getCompareKey());
    }
}
