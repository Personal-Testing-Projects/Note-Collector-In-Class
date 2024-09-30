package lk.ijse.notecollecter.service;

import jakarta.transaction.Transactional;
import lk.ijse.notecollecter.dao.UserDAO;
import lk.ijse.notecollecter.dto.impl.UserDTO;
import lk.ijse.notecollecter.entity.impl.UserEntity;
import lk.ijse.notecollecter.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Mapping mapping;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return mapping.toUserDTO(userDAO.save(mapping.toUserEntity(userDTO)));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> allUsers = userDAO.findAll();
        return mapping.asUserDTOList(allUsers);
    }

    @Override
    public UserDTO getUser(String userId) {
        UserEntity selectedUser = userDAO.getReferenceById(userId);
        System.out.println(selectedUser);
        return mapping.toUserDTO(selectedUser);
    }

    @Override
    public void deleteUser(String userId) {
        userDAO.deleteById(userId);
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        Optional <UserEntity> tempUser = userDAO.findById(userId);
        if(tempUser.isPresent()) {
            tempUser.get().setFirstName(userDTO.getFirstName());
            tempUser.get().setLastName(userDTO.getLastName());
            tempUser.get().setEmail(userDTO.getEmail());
            tempUser.get().setPassword(userDTO.getPassword());
            tempUser.get().setProfilePic(userDTO.getProfilePic());
        }
    }
}
