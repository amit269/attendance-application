package com.developer.AttendanceApp.Service;

import com.developer.AttendanceApp.Entity.Employee;
import com.developer.AttendanceApp.Repository.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class EmployeeService {
 @Autowired
    EmployeeRepo employeeRepo;

    private static  final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // This function to add the employee in the database
    @Transactional
    public void saveEmployee(Employee employee){

        // here password is encrypted
        try{
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employee.setRoles(Arrays.asList("USER"));
            employeeRepo.save(employee);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to save employee", e);
        }

    }

    // this function is used to see the whole detail of the employee in the database
    public List<Employee> getEmployee(){
       return employeeRepo.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id){
        return  employeeRepo.findById(id);
    }
    public void deleteEmployeeBYId(Long id){
         employeeRepo.deleteById(id);

    }
    public Employee getEmployeeByUserName(String name){
       return employeeRepo.findByname(name);
    }
}
