package lk.ijse.notecollecter.controller;

import lk.ijse.notecollecter.dto.impl.NoteDTO;
import lk.ijse.notecollecter.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(consumes = "application/json")
    public NoteDTO savaNote(@RequestBody NoteDTO noteDTO) {
        return noteService.saveNote(noteDTO);
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
