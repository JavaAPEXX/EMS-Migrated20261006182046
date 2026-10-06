```java
package com.employee.system.service;

import com.employee.system.dto.AttendanceDTO;
import com.employee.system.entity.Attendance;
import com.employee.system.entity.Employee;
import com.employee.system.repository.AttendanceRepository;
import com.employee.system.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private AttendanceService attendanceService;

    private Employee employee;
    private Attendance attendance;
    private AttendanceDTO attendanceDTO;
    private LocalDate today;
    private LocalDate startDate;
    private LocalDate endDate;

    @BeforeEach
    void setUp() {
        today = LocalDate.now();
        startDate = today.minusDays(30);
        endDate = today;

        employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");

        attendance = new Attendance();
        attendance.setId(100L);
        attendance.setEmployee(employee);
        attendance.setAttendanceDate(today);
        attendance.setStatus("PRESENT");
        attendance.setCheckInTime(LocalTime.of(9, 0));
        attendance.setCheckOutTime(LocalTime.of(17, 0));
        attendance.setRemarks("On time");
        attendance.setCreatedAt(LocalDateTime.now());
        attendance.setUpdatedAt(LocalDateTime.now());

        attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(100L);
        attendanceDTO.setEmployeeId(1L);
        attendanceDTO.setEmployeeName("John Doe");
        attendanceDTO.setAttendanceDate(today);
        attendanceDTO.setStatus("PRESENT");
        attendanceDTO.setCheckInTime(LocalTime.of(9, 0));
        attendanceDTO.setCheckOutTime(LocalTime.of(17, 0));
        attendanceDTO.setRemarks("On time");
        attendanceDTO.setCreatedAt(LocalDateTime.now());
        attendanceDTO.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    @DisplayName("Given valid attendance DTO, when marking attendance, then return saved attendance DTO")
    void givenValidAttendanceDTO_whenMarkAttendance_thenReturnSavedAttendanceDTO() {
        // Arrange
        AttendanceDTO inputDTO = new AttendanceDTO();
        inputDTO.setEmployeeId(1L);
        inputDTO.setAttendanceDate(today);
        inputDTO.setStatus("PRESENT");
        inputDTO.setCheckInTime(LocalTime.of(9, 0));
        inputDTO.setCheckOutTime(LocalTime.of(17, 0));
        inputDTO.setRemarks("On time");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(attendanceRepository.save(any(Attendance.class))).thenAnswer(invocation -> {
            Attendance att = invocation.getArgument(0);
            att.setId(100L);
            att.setCreatedAt(LocalDateTime.now());
            att.setUpdatedAt(LocalDateTime.now());
            return att;
        });

        // Act
        AttendanceDTO result = attendanceService.markAttendance(inputDTO);

        // Assert
        assertNotNull(result