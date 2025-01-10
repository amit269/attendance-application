package com.developer.AttendanceApp.Service;

import com.developer.AttendanceApp.Entity.Employee;
import com.developer.AttendanceApp.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
@Component
public class ProfileService {

    @Value("${upload.directory}")
    private String uploadDir;

    @Autowired
    EmployeeRepo employeeRepo;
    public String updateProfilePicture(String username, MultipartFile file) {
        try {
            // Find the user by username
            Employee user = employeeRepo.findByname(username);


            // Save the file to the server
            String fileName = username + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.write(filePath, file.getBytes());

            // Update the profile picture path in the database
            String newProfilePicturePath = "/images/" + fileName;

            user.setProfilePicture(newProfilePicturePath);
            employeeRepo.save(user);

            return newProfilePicturePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Employee profilePicture(String name ){
        return  employeeRepo.findByname(name);
    }
}
