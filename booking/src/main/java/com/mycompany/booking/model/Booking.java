package com.mycompany.booking.model;

import com.mycompany.common.model.UserBasic;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking extends PanacheEntity {

    @NotNull
    @Column(name = "student_id")
    private UserBasic user;   // Store the student ID instead of the User object

    @NotNull
    @Column(name = "class_id")
    public Long classId;

    @NotNull
    @Column(name = "booking_date")
    public LocalDateTime bookingDate;

    @NotNull
    @Column(name = "status", columnDefinition = "varchar(255) default 'pending'")
    public String status = "pending";

    @NotNull
    @Column(name = "payment_status", columnDefinition = "varchar(255) default 'pending'")
    public String paymentStatus = "pending";

    public UserBasic getUser() {
        return user;
    }

    public void setUser(UserBasic user) {
        this.user = user;
    }

public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
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
