package lk.ijse.notecollecter.service;

import lk.ijse.notecollecter.dao.UserDAO;
import lk.ijse.notecollecter.dto.impl.UserDTO;

import java.util.List;

public class UserServiceImpl implements  UserService{
    private UserDAO userDAO;
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        userDAO.save(userDTO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDTO getUser(String userId) {
        return null;
    }

    @Override
    public boolean deleteUser(String userId) {
        return false;
    }

    @Override
    public boolean updateUser(String userId, UserDTO userDTO) {
        return false;
    }
}
