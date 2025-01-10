package com.developer.AttendanceApp.Repository;
import com.developer.AttendanceApp.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public interface EmployeeRepo extends JpaRepository<Employee ,Long> {


    Employee findByname(String userName);
}