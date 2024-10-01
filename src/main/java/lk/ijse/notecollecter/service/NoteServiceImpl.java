package lk.ijse.notecollecter.service;

import jakarta.transaction.Transactional;
import lk.ijse.notecollecter.Exception.DataPersistException;
import lk.ijse.notecollecter.dao.NoteDAO;
import lk.ijse.notecollecter.dto.impl.NoteDTO;
import lk.ijse.notecollecter.entity.impl.NoteEntity;
import lk.ijse.notecollecter.entity.impl.UserEntity;
import lk.ijse.notecollecter.util.AppUtil;
import lk.ijse.notecollecter.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements NoteService{
    @Autowired
    private NoteDAO noteDAO;

    @Autowired
    private Mapping noteMapping;

    @Override
    public void saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.generateID());
        System.out.println("Note DTO before save : "+noteDTO);
        NoteEntity savedNote = noteMapping.toNoteEntity(noteDTO);
        System.out.println("Note Entity after map : "+savedNote);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(noteDTO.getUserId());
        savedNote.setUserEntity(userEntity);
                noteDAO.save(savedNote);
        System.out.println("Note Entity after save : " +savedNote);
        if(savedNote == null){
            throw new DataPersistException("Note not saved");
        }
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
