package lk.ijse.notecollecter.customStatusCodes;

import lk.ijse.notecollecter.dto.NoteStatus;
import lk.ijse.notecollecter.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedUserAndNoteErrorStatus implements UserStatus, NoteStatus {
    private int statusCode;
    private String statusMessage;
}
