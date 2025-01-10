package com.developer.AttendanceApp.Controller;
import com.developer.AttendanceApp.Entity.Employee;
import com.developer.AttendanceApp.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/profile-picture")
public class ProfileController {


    @Autowired
    ProfileService profileService;


    @GetMapping("/get-profile")
    public ResponseEntity<?> getProfilePicture(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        Employee getEmployeeByName = profileService.profilePicture(name);
        String profilePicture = getEmployeeByName.getProfilePicture();
//
//        byte[] imageBytes = getEmployeeByName.getProfilePictureBytes(); // Assuming this returns the image as a byte array
//        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//        return new ResponseEntity<>(base64Image, HttpStatus.OK);


        if(profilePicture != null){
            return new ResponseEntity<>(profilePicture,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Image is found in  the database",HttpStatus.NOT_FOUND);

    }
    @PostMapping("/upload-picture")
    public ResponseEntity<?> updateProfilePicture(@RequestParam("file") MultipartFile file) {

        try {

            // Get the currently authenticated user's username from the Principal object
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();



            // Call the service layer to handle the update logic
//            String updatedFilePath = profileService.updateProfilePicture(username, file);
            String updatedFilePath = profileService.updateProfilePicture(name, file);


            if (updatedFilePath != null) {
                return ResponseEntity.ok(Map.of(
                    "message", "Profile picture updated successfully",
                    "profilePicture", updatedFilePath
                ));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("message", "Failed to update profile picture"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "An error occurred while updating the profile picture"));
        }
    }
}
