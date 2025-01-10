package com.developer.AttendanceApp.Entity;
//import com.developer.AttendanceApp.Converter.DurationConverter;
import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "attendance_login")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false,updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "mark_in_time")
    private  LocalDateTime markInTime;

    @Column(name = "mark_out_time")
    private LocalDateTime markOutTime;

    @Column(name = "record_date")
    private LocalDate recordDate;
    @Column(name = "total_working_hours")
//    @Convert(converter = DurationConverter.class)

    private String totalWorkingHours;

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public String getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public void setTotalWorkingHours(String totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    public LocalDateTime getMarkInTime() {
        return markInTime;
    }

    public void setMarkInTime(LocalDateTime markInTime) {
        this.markInTime = markInTime;
    }

    public LocalDateTime getMarkOutTime() {
        return markOutTime;
    }

    public void setMarkOutTime(LocalDateTime markOutTime) {
        this.markOutTime = markOutTime;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
