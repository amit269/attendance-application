package com.developer.AttendanceApp.Service;

import com.developer.AttendanceApp.Entity.Attendance;
import com.developer.AttendanceApp.Entity.Employee;
import com.developer.AttendanceApp.Repository.AttendanceRepo;
import com.developer.AttendanceApp.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AttendanceLoginService {

    @Autowired
    private  AttendanceRepo attendanceRepo;

    @Autowired
    EmployeeRepo employeeRepo;
//    public String  saveLoginDetail(Employee employee,AttendanceTiming localDateTime){
//
//        // here current date is coming
//        LocalDate today = LocalDate.now();
//
//        // here check the existingrecord is their or not
//        Optional<AttendanceTiming> existingRecord = attendanceRepo.findByEmployeeAndRecordDate(employee, today);
//
//        if(existingRecord.isPresent()){
//            AttendanceTiming attendanceTiming = existingRecord.get();
//            if(attendanceTiming.getMarkInTime() != null){
//                return "already logged in "+attendanceTiming.getMarkInTime();
//            }
//            attendanceTiming.setMarkInTime(LocalDateTime.now());
//            return "mark in time recored"+attendanceTiming.getMarkInTime();
//        }
//        AttendanceTiming newAttendanceTiming = (localDateTime != null) ? localDateTime : new AttendanceTiming();
////        AttendanceTiming newAttendanceTiming = new AttendanceTiming();
//        if(newAttendanceTiming.getMarkInTime() == null) {
//            newAttendanceTiming.setMarkInTime(LocalDateTime.now());
//        }
//        // Save the new attendance record
//        attendanceRepo.save(newAttendanceTiming);
//
//        return "Mark-in time recorded: " + newAttendanceTiming.getMarkInTime();
////        localDateTime.setMarkInTime(LocalDateTime.now());
////        attendanceRepo.save(localDateTime);
//    }
//    public ResponseEntity<?> getLoginDetail(AttendanceTiming logout, Employee employee) {
//        logout.setMarkOutTime(LocalDateTime.now());
//        if (logout == null || employee == null) {
//            return new ResponseEntity<>("Invalid input: Logout or Employee cannot be null", HttpStatus.BAD_REQUEST);
//        }
//
//        Optional<AttendanceTiming> attendanceTimingOpt = attendanceRepo.findByEmployeeAndMarkOutTimeIsNull(employee);
//
//        if (attendanceTimingOpt.isPresent()) {
//            AttendanceTiming attendanceTiming = attendanceTimingOpt.get();
//            attendanceTiming.setMarkOutTime(logout.getMarkOutTime());  // Set the mark-out time
//
//            // Calculate total working hours
//            Duration duration = Duration.between(attendanceTiming.getMarkInTime(), attendanceTiming.getMarkOutTime());
//            long hoursWorked = duration.toHours();  // Total hours worked
//            long minutesWorked = duration.toMinutes() % 60;  // Remaining minutes
//
//            attendanceRepo.save(attendanceTiming);  // Save the mark-out time and working hours
//
//            // Return the response with mark-out time and total working hours
//            return new ResponseEntity<>("Logout time: " + attendanceTiming.getMarkOutTime() +
//                    ", Total hours worked: " + hoursWorked + " hours " + minutesWorked + " minutes", HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>("Attendance record not found for the user", HttpStatus.NOT_FOUND);
//    }
public String saveLoginDetail(Employee employee, Attendance localDateTime) {
    // Validate employee object
    if (employee == null) {
        return "Employee cannot be null";
    }

    // Get current date
    LocalDate today = LocalDate.now();

    // Check if an attendance record for the employee and current date already exists
    Optional<Attendance> existingRecord = attendanceRepo.findByEmployeeAndRecordDate(employee, today);

    if (existingRecord.isPresent()) {
        Attendance attendance = existingRecord.get();

        // If mark-in time is already set, return a message
        if (attendance.getMarkInTime() != null) {
            return "Already logged in at: " + attendance.getMarkInTime();
        }

        // Otherwise, set mark-in time and save
        attendance.setMarkInTime(LocalDateTime.now());
        attendanceRepo.save(attendance);
        return "Mark-in time updated: " + attendance.getMarkInTime();
    }

    // Create a new attendance record
    Attendance newAttendance = (localDateTime != null) ? localDateTime : new Attendance();

    // Set mark-in time if not already set
    if (newAttendance.getMarkInTime() == null) {
        newAttendance.setMarkInTime(LocalDateTime.now());
    }

    // Set the record date to today
    newAttendance.setRecordDate(today);

    // Associate the attendance record with the employee
    newAttendance.setEmployee(employee);

    // Save the new attendance record
    attendanceRepo.save(newAttendance);

    return "Mark-in time recorded: " + newAttendance.getMarkInTime();
}





public ResponseEntity<?> getLoginDetail(Employee employee, Attendance logout) {
    // Check if the employee object is null first
    if (employee == null) {
        return new ResponseEntity<>("Employee cannot be null", HttpStatus.BAD_REQUEST);
    }

    // If logout object is null, create a new AttendanceTiming object and set the markOutTime
    if (logout == null) {
        logout = new Attendance();  // Initialize the logout object
        logout.setMarkOutTime(LocalDateTime.now());  // Set the current logout time
    } else {
        // Ensure logout time is set in case it's missing
        logout.setMarkOutTime(LocalDateTime.now());
    }

    // Fetch the attendance record of the employee with an empty mark-out time
    Optional<Attendance> attendanceTimingOpt = attendanceRepo.findByEmployeeAndMarkOutTimeIsNull(employee);

    if (attendanceTimingOpt.isPresent()) {
        Attendance attendance = attendanceTimingOpt.get();

        // Set the mark-out time
        attendance.setMarkOutTime(logout.getMarkOutTime());

        // Calculate the total working hours
        Duration duration = Duration.between(attendance.getMarkInTime(), attendance.getMarkOutTime());
        long hoursWorked = duration.toHours();
        long minutesWorked = duration.toMinutes() % 60;

        String formattedDuration = String.format("%d hours %d minutes", hoursWorked, minutesWorked);

        attendance.setTotalWorkingHours(formattedDuration);

        // Save the updated attendance
        attendanceRepo.save(attendance);

        // Return the response with mark-out time and total working hours
        return new ResponseEntity<>("Logout time: " + attendance.getMarkOutTime() +
                ", Total hours worked: " + hoursWorked + " hours " + minutesWorked + " minutes", HttpStatus.OK);
    }

    return new ResponseEntity<>("Attendance record not found for the user", HttpStatus.NOT_FOUND);
}
}
