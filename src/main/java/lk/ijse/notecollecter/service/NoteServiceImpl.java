package lk.ijse.notecollecter.service;

import lk.ijse.notecollecter.dto.impl.NoteDTO;
import lk.ijse.notecollecter.util.AppUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    @Override
    public NoteDTO saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.generateID());
        return noteDTO;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return null;
    }

    @Override
    public NoteDTO getSelectedNote(String id) {
        return null;
    }

    @Override
    public Boolean deleteNote(String id) {
        return null;
    }

    @Override
    public Boolean updateNote(String id) {
        return null;
    }
}
