package com.example.notetakingapi.dao;

import com.example.notetakingapi.exception.MyException;
import com.example.notetakingapi.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
@Transactional
public class NoteRepository implements NoteDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_QUERY = "INSERT INTO note_table(note_id, title, " +
            "note_created, note_text) VALUES (NEXTVAL('note_table_seq'), ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM note_table";
    private static final String SELECT_QUERY = "SELECT * FROM note_table WHERE note_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM note_table WHERE note_id = ?";
    private static final String UPDATE_QUERY = "UPDATE note_table " +
            "SET title = ?, note_created = ?, note_text = ? " +
            "WHERE note_id = ?";

    @Override
    public int insertNote(String title, String note, long createdAt) throws MyException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        INSERT_QUERY,
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, title);
                ps.setLong(2, createdAt);
                ps.setString(3, note);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("note_id");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MyException("Failed to Add Note");
        }
    }

    @Override
    public int deleteNote(int id) throws MyException {
        try {
            int status = jdbcTemplate.update(DELETE_QUERY, id);
            if (status == 1) {
                System.out.println("Note Deleted");
            } else System.out.println("Note Delete Failed");
            return status;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MyException("Note not found for id :" + id);
        }
    }

    @Override
    public int updateNote(Note note, int id) throws MyException {
        try {
            return jdbcTemplate.update(UPDATE_QUERY, note.getNoteTitle(), note.getCreatedAt(), note.getNoteText(), id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MyException("Note not found for this id :" + id);
        }
    }

    @Override
    public List<Note> getAllNotes() throws MyException {
        try {
            return jdbcTemplate.query(SELECT_ALL_QUERY, (resultSet, i) -> {
                final int id = Integer.parseInt(resultSet.getString("note_id"));
                final String title = resultSet.getString("title");
                final String text = resultSet.getString("note_text");
                final long createdAt = Long.parseLong(resultSet.getString("note_created"));
                return new Note(id, title, createdAt, text);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MyException("Can't Fetch List");
        }
    }

    @Override
    public Note getNoteById(int id) throws MyException {
        try {
            return jdbcTemplate.queryForObject(SELECT_QUERY, (resultSet, i) -> {
                final int noteId = Integer.parseInt(resultSet.getString("note_id"));
                final String title = resultSet.getString("title");
                final String text = resultSet.getString("note_text");
                final long createdAt = Long.parseLong(resultSet.getString("note_created"));
                return new Note(noteId, title, createdAt, text);
            }, id);
        } catch (Exception e) {
            throw new MyException("Note not found for this id :" + id);
        }
    }


//    @Override
//    public List<Person> getAllPerson() {
//        final String selectAll = "SELECT id, name FROM person";
//        //return jdbcTemplate.query(selectAll, new PersonMapper());
//        return jdbcTemplate.query(selectAll, (resultSet, i) -> {
//            final UUID id = UUID.fromString(resultSet.getString("id"));
//            final String name = resultSet.getString("name");
//            return new Person(id, name);
//        });
//    }

}
