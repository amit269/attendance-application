package com.developer.AttendanceApp.Controller;

import com.developer.AttendanceApp.Utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LogoutController {

    @Autowired
    private JwtUtil jwtUtil;  // If using JWT for authentication

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        System.out.println("log out successfully\n \n \n \n \n");
    SecurityContextHolder.getContext().getAuthentication();
        String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Extract token

            // Optionally invalidate or blacklist the token (no need for database storage)
            jwtUtil.blacklistToken(token);
            return new ResponseEntity<>("Logout successful", HttpStatus.OK);
        }

        return new ResponseEntity<>("Unable to logout ",HttpStatus.BAD_REQUEST);
    }
}
