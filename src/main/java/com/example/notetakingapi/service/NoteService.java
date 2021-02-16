package com.example.notetakingapi.service;

import com.example.notetakingapi.dao.NoteDao;
import com.example.notetakingapi.dao.NoteRepository;
import com.example.notetakingapi.exception.MyException;
import com.example.notetakingapi.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public int insertNote(String title, String note, long createdAt) throws MyException {
        return noteRepository.insertNote(title, note, createdAt);
    }

    public int deleteNote(int id) throws MyException {
        return noteRepository.deleteNote(id);
    }

    public int updateNote(Note note, int id) throws MyException {
        return noteRepository.updateNote(note, id);
    }

    public List<Note> getNotes() throws MyException {
        return noteRepository.getAllNotes();
    }

    public Note getNoteById(int id) throws MyException {
        return noteRepository.getNoteById(id);
    }
}
