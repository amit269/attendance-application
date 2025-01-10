package com.developer.AttendanceApp.Controller;


import com.developer.AttendanceApp.Entity.Employee;
import com.developer.AttendanceApp.Entity.LoginPageDetail;
import com.developer.AttendanceApp.Service.EmployeeService;
import com.developer.AttendanceApp.Service.UserDetailsServiceImpl;
import com.developer.AttendanceApp.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.*;

@RestController
@RequestMapping("/Employee")
public class EmployeeRegisterController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;


   private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


   // To create new user in database
    @PostMapping("/signup")
    public ResponseEntity<?> setEmployee(@RequestBody Employee employeeDetail){
        try{
            employeeService.saveEmployee(employeeDetail);
            return new ResponseEntity<>(employeeDetail, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // this is for login if the user is present in the database
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginPageDetail loginPageDetail){

        try {
            // here username and password get authenticated
            Authentication authenticate = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(
                            loginPageDetail.getName()
                    , loginPageDetail.getPassword()));
            String name = authenticate.getName();
//            UserDetails userDetails = userDetailsService.loadUserByUsername(loginPageDetail.getName());
            String jwtToken = jwtUtil.generateToken(name);

            return new ResponseEntity<>(jwtToken,HttpStatus.CREATED);
        }
        catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid username or password",HttpStatus.UNAUTHORIZED);
        } catch (DisabledException e) {
            return new ResponseEntity<>("User account is disabled",HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred during login",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }










    @GetMapping

    // no need to create this will be use from the admin where admin can see all the employee detail
    public ResponseEntity<?> getEmployee(){
        List<Employee> employee = employeeService.getEmployee();
        if(employee != null && !employee.isEmpty()){
         return new ResponseEntity<>(employee,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
//
//
//    @GetMapping("/id/{myId}")
//    public ResponseEntity<Employee> getEmployeeById(@PathVariable long myId){
//
//        Optional<Employee> employeeById = employeeService.getEmployeeById(myId);
//        if(employeeById.isPresent()){
//            return new ResponseEntity<Employee>(employeeById.get(),HttpStatus.OK);
//
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?>    deleteEmployeeByID(@PathVariable  long myId){
        try {
            employeeService.deleteEmployeeBYId(myId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
//
//
//
 @PutMapping("/id/{myId}")
 public ResponseEntity<?> updateEmployeeById(@PathVariable long myId, @RequestBody Employee updateEmployee){
     Employee oldEmployee = employeeService.getEmployeeById(myId).orElse(null);
     try {
         if(oldEmployee != null){
             oldEmployee.setEmail(updateEmployee.getEmail() != null && !updateEmployee.getEmail().equals("")?
                     updateEmployee.getEmail(): oldEmployee.getEmail());
             oldEmployee.setAddress(updateEmployee.getAddress() != null && !updateEmployee.getAddress().equals("")
                     ?updateEmployee.getAddress():oldEmployee.getAddress());
         }
         employeeService.saveEmployee(oldEmployee);
         return new ResponseEntity<>(oldEmployee,HttpStatus.OK);
     }catch (Exception e){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

 }
}
