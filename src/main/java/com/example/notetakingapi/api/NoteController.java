package com.example.notetakingapi.api;

import com.example.notetakingapi.model.Note;
import com.example.notetakingapi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addNote(@RequestBody Map<String, Object> userMap) {
        String title = (String) userMap.get("title");
        String text = (String) userMap.get("text");
        String createdAt = (String) userMap.get("createdAt");
        noteService.insertNote(title, text, Long.parseLong(createdAt));
        Map<String, String> map = new HashMap<>();
        map.put("message", "Note Added Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getNotes() {
        return new ResponseEntity<>(noteService.getNotes(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteNote(@PathVariable int id) {
        noteService.deleteNote(id);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Note Deleted Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateNote(@RequestBody Map<String, Object> userMap,
                                                          @PathVariable int id) {
        String title = (String) userMap.get("title");
        String text = (String) userMap.get("text");
        String createdAt = (String) userMap.get("createdAt");
        noteService.updateNote(new Note(title, Long.parseLong(createdAt), text),id);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Note Updated Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
