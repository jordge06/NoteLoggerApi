package com.example.notetakingapi.dao;

import com.example.notetakingapi.MyException;
import com.example.notetakingapi.model.Note;

import java.util.List;

public interface NoteDao {

    int insertNote(String title, String note, long createdAt) throws MyException;

    int deleteNote(int id) throws MyException;

    int updateNote(Note note, int id) throws MyException;

    List<Note> getNotes() throws MyException;
}
