package com.developer.AttendanceApp.Repository;

import com.developer.AttendanceApp.Entity.Attendance;
import com.developer.AttendanceApp.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public interface AttendanceRepo extends JpaRepository<Attendance,Long> {


    Optional<Attendance> findByEmployeeAndMarkOutTimeIsNull(Employee employee);
  Optional<Attendance>  findByEmployeeAndRecordDate(Employee employee, LocalDate localDate);
}
