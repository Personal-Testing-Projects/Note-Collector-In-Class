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
    private static List<NoteDTO> noteDTOList = new ArrayList<>();

    public NoteServiceImpl() {
        noteDTOList.add(new NoteDTO("n0001","Java","LearnJava","2024-9-14","1","u0001"));
        noteDTOList.add(new NoteDTO("n0002","Python","LearnPython","2024-9-14","2","u0002"));
        noteDTOList.add(new NoteDTO("n0003","JS","LearnJS","2024-9-14","3","u0003"));
    }

    @Override
    public NoteDTO saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.generateID());
        return noteDTO;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return noteDTOList;
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
