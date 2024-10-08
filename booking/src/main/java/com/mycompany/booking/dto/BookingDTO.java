package com.mycompany.booking.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BookingDTO {

    public Long id;  // Optional since it's generated
    @NotNull
    public Long classId;
    @NotNull
    public Long studentId;
    @NotNull
    public LocalDateTime bookingDate;
    @NotNull
    public String status;
    @NotNull
    public String paymentStatus;

    // Constructors, getters, and setters
    public BookingDTO() {
    }

    public BookingDTO(Long id, Long classId, Long studentId, LocalDateTime bookingDate, String status, String paymentStatus) {
        this.id = id;
        this.classId = classId;
        this.studentId = studentId;
        this.bookingDate = bookingDate;
        this.status = status;
        this.paymentStatus = paymentStatus;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

