package com.example.notetakingapi.model;

public class Note {

    private int noteId;
    private final String noteTitle;
    private final long createdAt;
    private final String noteText;

    public Note(int noteId, String noteTitle, long createdAt, String noteText) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.createdAt = createdAt;
        this.noteText = noteText;
    }

    public Note(String noteTitle, long createdAt, String noteText) {
        this.noteTitle = noteTitle;
        this.createdAt = createdAt;
        this.noteText = noteText;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getNoteText() {
        return noteText;
    }
}
