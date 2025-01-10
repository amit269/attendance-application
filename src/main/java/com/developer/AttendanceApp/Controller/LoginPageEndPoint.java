package com.developer.AttendanceApp.Controller;
import com.developer.AttendanceApp.Entity.Employee;
import com.developer.AttendanceApp.Repository.EmployeeRepo;
import com.developer.AttendanceApp.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/User")
public class LoginPageEndPoint {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    EmployeeRepo employeeRepo;
    @GetMapping("/get")
    public ResponseEntity<?> getEmployee(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        Employee employee = employeeRepo.findByname(name);
        if(employee != null){
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("invalid username or password",HttpStatus.NOT_FOUND);

    }



}
