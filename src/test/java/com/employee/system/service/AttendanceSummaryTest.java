```java
package com.employee.system.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link AttendanceSummary}.
 * Since AttendanceSummary is a Lombok-generated data class with @Data and @Builder,
 * we test the generated methods: builder, getters, setters, equals, hashCode, toString.
 */
class AttendanceSummaryTest {

    @Test
    @DisplayName("Given valid fields when building AttendanceSummary then all fields are set correctly")
    void givenValidFields_whenBuildingAttendanceSummary_thenAllFieldsAreSetCorrectly() {
        // Arrange
        Long employeeId = 1001L;
        long totalDays = 22L;
        long presentDays = 18L;
        long absentDays = 2L;
        long lateDays = 1L;
        long halfDays = 1L;
        long leaveDays = 0L;

        // Act
        AttendanceSummary summary = AttendanceSummary.builder()
                .employeeId(employeeId)
                .totalDays(totalDays)
                .presentDays(presentDays)
                .absentDays(absentDays)
                .lateDays(lateDays)
                .halfDays(halfDays)
                .leaveDays(leaveDays)
                .build();

        // Assert
        assertNotNull(summary);
        assertEquals(employeeId, summary.getEmployeeId());
        assertEquals(totalDays, summary.getTotalDays());
        assertEquals(presentDays, summary.getPresentDays());
        assertEquals(absentDays, summary.getAbsentDays());
        assertEquals(lateDays, summary.getLateDays());
        assertEquals(halfDays, summary.getHalfDays());
        assertEquals(leaveDays, summary.getLeaveDays());
    }

    @Test
    @DisplayName("Given null employeeId when building AttendanceSummary then employeeId is null")
    void givenNullEmployeeId_whenBuildingAttendanceSummary_thenEmployeeIdIsNull() {
        // Arrange
        long totalDays = 20L;
        long presentDays = 15L;
        long absentDays = 3L;
        long lateDays = 1L;
        long halfDays = 1L;
        long leaveDays = 0L;

        // Act
        AttendanceSummary summary = AttendanceSummary.builder()
                .employeeId(null)
                .totalDays(totalDays)
                .presentDays(presentDays)
                .absentDays(absentDays)
                .lateDays(lateDays)
                .halfDays(halfDays)
                .leaveDays(leaveDays)
                .build();

        // Assert
        assertNotNull(summary);
        assertNull(summary.getEmployeeId());
        assertEquals(totalDays, summary.getTotalDays());
        assertEquals(presentDays, summary.getPresentDays());
        assertEquals(absentDays, summary.getAbsentDays());
        assertEquals(lateDays, summary.getLateDays());
        assertEquals(halfDays, summary.getHalfDays());
        assertEquals(leaveDays, summary.getLeaveDays());
    }

    @Test
    @DisplayName("Given zero values for all numeric fields when building AttendanceSummary then all numeric fields are zero")
    void givenZeroValuesForAllNumericFields_whenBuildingAttendanceSummary_thenAllNumericFieldsAreZero() {
        // Arrange
        Long employeeId = 2001L;

        // Act
        AttendanceSummary summary = AttendanceSummary.builder()
                .employeeId(employeeId)
                .totalDays(0L)
                .presentDays(0L)
                .absentDays(0L)
                .lateDays(0L)
                .halfDays(0L)