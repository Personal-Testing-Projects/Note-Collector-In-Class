package lk.ijse.notecollecter.controller;

import lk.ijse.notecollecter.Exception.DataPersistException;
import lk.ijse.notecollecter.dto.impl.NoteDTO;
import lk.ijse.notecollecter.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    public NoteDTO getSelectedNotes() {
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveNote(@RequestBody NoteDTO noteDTO) {
        System.out.println("Note save");
        System.out.println(noteDTO);
        try {
            noteService.saveNote(noteDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }/*catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
    }

    @GetMapping(produces = "application/json")
    public List<NoteDTO> getAllNotes() {
        return noteService.getAllNotes();
    }

    public void deleteNote(String noteId) {
    }

    public void updateNote(String noteId, NoteDTO noteDTO) {
    }

}
