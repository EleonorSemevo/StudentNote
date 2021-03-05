package com.memoire.studentnote.essaies;


import com.memoire.studentnote.classes.Note;

public class Notes {

    private Note row;
    private String section;
    private boolean isRow;

    public static Notes createRow(Note row) {
        Notes ret = new Notes();
        ret.row = row;
        ret.isRow = true;
        return ret;
    }

    public static Notes createSection(Note section) {
        Notes ret = new Notes();
        ret.section = section.getDescription();
        ret.isRow = false;
        return ret;
    }

    public Note getRow() {
        return row;
    }

    public String getSection() {
        return section;
    }

    public boolean isRow() {
        return isRow;
    }

}