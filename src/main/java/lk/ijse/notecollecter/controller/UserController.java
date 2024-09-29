package lk.ijse.notecollecter.controller;

import lk.ijse.notecollecter.dto.impl.UserDTO;
import lk.ijse.notecollecter.util.AppUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json")
    public UserDTO saveUser(
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
        } catch (IOException e) {
            e.printStackTrace();
        }


        //UserId generate
        String userId = AppUtil.generateUserId();

/*
        //Todo: Build the object*/
        UserDTO buildUserDTO = new UserDTO();
        buildUserDTO.setUserId(userId);
        buildUserDTO.setFirstName(firstName);
        buildUserDTO.setLastName(lastName);
        buildUserDTO.setEmail(email);
        buildUserDTO.setPassword(password);
        buildUserDTO.setProfilePic(base64ProPic);

        return buildUserDTO;
    }
}
