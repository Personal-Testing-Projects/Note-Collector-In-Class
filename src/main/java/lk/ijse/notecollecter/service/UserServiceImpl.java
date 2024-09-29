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
        return List.of();
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
    }
}
