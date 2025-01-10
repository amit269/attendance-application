//package com.developer.AttendanceApp.Service;
//
//import com.developer.AttendanceApp.Entity.Employee;
//import com.developer.AttendanceApp.Entity.LoginPageDetail;
//import com.developer.AttendanceApp.Repository.EmployeeRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class LoginService {
//    @Autowired
//    EmployeeRepo employeeRepo;
//    @Autowired
//    EmployeeService employeeService;
//
//
//    private static  final  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//
//    public Boolean checkLoginUserNameWithDatabase(LoginPageDetail loginPageDetail){
//
//        // here  converting the password into hashing ie encrypting the password
//        loginPageDetail.setPassword(passwordEncoder.encode(loginPageDetail.getPassword()));
//
//
//        String name = loginPageDetail.getName();
//        String password = loginPageDetail.getPassword();
//       Employee employee =  employeeService.getEmployeeByUserName(name);
////        if(name == // here i want to check if this name is present in db or not if present){
//            // then here i want to check password is matches if matches return true// either get back to out
//        // of the if then return false
////        }
//
//        // Check if the employee with the given username exists
//        if (employee!= null) {
//            String pass = employee.getPassword();
//
//            // Check if the provided password matches the stored password
//            if (pass.equals(password)) {
//                return true; // Password matches
//            }
//        }
//
//        return false;
//    }
//
//}

