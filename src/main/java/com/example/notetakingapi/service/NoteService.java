package com.example.notetakingapi.service;

import com.example.notetakingapi.dao.NoteDao;
import com.example.notetakingapi.dao.NoteRepository;
import com.example.notetakingapi.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public int insertNote(String title, String note, long createdAt) {
        return noteRepository.insertNote(title, note, createdAt);
    }

    public int deleteNote(int id) {
        return noteRepository.deleteNote(id);
    }

    public int updateNote(Note note, int id) {
        return noteRepository.updateNote(note, id);
    }

    public List<Note> getNotes() {
        return noteRepository.getNotes();
    }
}
