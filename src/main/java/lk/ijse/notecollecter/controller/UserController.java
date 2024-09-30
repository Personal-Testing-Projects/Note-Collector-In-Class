package lk.ijse.notecollecter.controller;

import lk.ijse.notecollecter.Exception.DataPersistException;
import lk.ijse.notecollecter.customStatusCodes.SelectedUserErrorStatus;
import lk.ijse.notecollecter.dto.UserStatus;
import lk.ijse.notecollecter.dto.impl.UserDTO;
import lk.ijse.notecollecter.service.UserService;
import lk.ijse.notecollecter.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json")
    public ResponseEntity<Void> saveUser(
            @RequestPart("firstName") String firstName,
            @RequestPart ("lastName") String lastName,
            @RequestPart ("email") String email,
            @RequestPart ("password") String password,
            //@RequestPart ("profilePic") String profilePic
            @RequestPart ("profilePic") MultipartFile profilePic
    ) {
        System.out.println("Raw pro pic" + profilePic);

        //profilepic -> Base64
        //String base64ProPic = AppUtil.profilrPicToBase64(profilePic);
        String base64ProPic = "";
        try {
            byte [] bytesProPic = profilePic.getBytes();
            base64ProPic = AppUtil.profilrPicToBase64(bytesProPic);

            //UserId generate
            String userId = AppUtil.generateUserId();

            //Build the project
            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setUserId(userId);
            buildUserDTO.setFirstName(firstName);
            buildUserDTO.setLastName(lastName);
            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(password);
            buildUserDTO.setProfilePic(base64ProPic);

            userService.saveUser(buildUserDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStatus getSelectedUser(@PathVariable("userId") String userId) {
        String regexForUserID = "^USER-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        var regexMatcher = regexPattern.matcher(userId);
        if(!regexMatcher.matches()){
            return new SelectedUserErrorStatus(1,"User ID is not valid");
        }
        return userService.getUser(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateUser(@RequestPart("firstName") String firstName,
                           @RequestPart ("lastName") String lastName,
                           @RequestPart ("email") String email,
                           @RequestPart ("password") String password,
                           @RequestPart ("profilePic") MultipartFile profilePic,
                           @PathVariable("userId") String userId) {

        System.out.println("Raw pro pic" + profilePic);

        //profilepic -> Base64
        //String base64ProPic = AppUtil.profilrPicToBase64(profilePic);
        String base64ProPic = "";
        try {
            byte [] bytesProPic = profilePic.getBytes();
            base64ProPic = AppUtil.profilrPicToBase64(bytesProPic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Build the project
        UserDTO buildUserDTO = new UserDTO();
        buildUserDTO.setUserId(userId);
        buildUserDTO.setFirstName(firstName);
        buildUserDTO.setLastName(lastName);
        buildUserDTO.setEmail(email);
        buildUserDTO.setPassword(password);
        buildUserDTO.setProfilePic(base64ProPic);

        userService.updateUser(userId, buildUserDTO);
    }
}
