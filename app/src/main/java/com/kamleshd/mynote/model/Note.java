package com.kamleshd.mynote.model;

public class Note {
    private int Id;
    private String Text_Note;

    public Note(int id, String text_Note) {
        Id = id;
        Text_Note = text_Note;
    }

    public Note(String text_Note) {
        Text_Note = text_Note;
    }

    public Note() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getText_Note() {
        return Text_Note;
    }

    public void setText_Note(String text_Note) {
        Text_Note = text_Note;
    }
}
