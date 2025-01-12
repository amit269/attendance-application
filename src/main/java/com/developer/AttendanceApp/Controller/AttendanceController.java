package com.developer.AttendanceApp.Controller;
import com.developer.AttendanceApp.Entity.Attendance;
import com.developer.AttendanceApp.Entity.Employee;
import com.developer.AttendanceApp.Repository.AttendanceRepo;
import com.developer.AttendanceApp.Repository.EmployeeRepo;
import com.developer.AttendanceApp.Service.AttendanceLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
@RestController
@RequestMapping("/Attendance")
public class AttendanceController {
    @Autowired
    private AttendanceLoginService attendanceLoginService;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private AttendanceRepo attendanceRepo;

    // login  api
    @PostMapping("/markIn")
    public ResponseEntity<?> loginTiming(@RequestBody Attendance attendance) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // get the name from the authenticated
        String name = authentication.getName();

        // here get the specific employee detail
        Employee employee = employeeRepo.findByname(name);

        // here setting the reference in the attendance table of the employee table
        attendance.setEmployee(employee);
        //

        try {
            String s = attendanceLoginService.saveLoginDetail(employee, attendance);
            return new ResponseEntity<>(s + "\n" + attendance.getMarkInTime(), HttpStatus.OK);
        }
        catch( Exception e){
            return new ResponseEntity<>("Got Some Error \n"+e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
  
    @PostMapping("/markOut")
    public ResponseEntity<?> markOut(@RequestBody Attendance attendance) {
        // Check if the employee exists
        Authentication authentications = SecurityContextHolder.getContext().getAuthentication();
        String name = authentications.getName();
        Employee employee = employeeRepo.findByname(name);

        if (authentications.isAuthenticated()) {
            ResponseEntity<?> loginDetail = attendanceLoginService.getLoginDetail(employee, attendance);

            return new ResponseEntity<>(loginDetail,HttpStatus.OK);
        }
        return new ResponseEntity<>("no user found" ,HttpStatus.NOT_FOUND);
    }


}
