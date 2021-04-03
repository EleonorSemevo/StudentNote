package com.memoire.studentnote.classes;

import android.os.Parcel;

import androidx.annotation.Nullable;

public class Matiere implements android.os.Parcelable{
    private int id;
    private String nom;


    public Matiere(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Matiere()
    {}

    protected Matiere(Parcel in) {
        id = in.readInt();
        nom = in.readString();
    }

    public static final Creator<Matiere> CREATOR = new Creator<Matiere>() {
        @Override
        public Matiere createFromParcel(Parcel in) {
            return new Matiere(in);
        }

        @Override
        public Matiere[] newArray(int size) {
            return new Matiere[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass()!=obj.getClass())
            return false;
        Matiere that = (Matiere) obj;
        return getCompareKey().equals(that.getCompareKey());
    }

    private String getCompareKey()
    {
        return  "id: "+ id + " | nom: "+ nom ;
    }
}
