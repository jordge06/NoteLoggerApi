package com.example.notetakingapi.dao;

import com.example.notetakingapi.exception.MyException;
import com.example.notetakingapi.model.Note;

import java.util.List;

public interface NoteDao {

    int insertNote(String title, String note, long createdAt) throws MyException;

    int deleteNote(int id) throws MyException;

    int updateNote(Note note, int id) throws MyException;

    List<Note> getAllNotes() throws MyException;

    Note getNoteById(int id) throws MyException;
}
