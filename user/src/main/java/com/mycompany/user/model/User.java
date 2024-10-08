package com.mycompany.user.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import java.util.List;
import com.mycompany.booking.model.Booking;

@Entity
@Table(name = "ecommerceapp_user")
@UserDefinition
public class User extends PanacheEntity {

    @Username
    @NotNull
    @Email
    @Column(unique = true)
    public String email;

    @NotNull
    @Password
    public String password;

    @Roles
    public String role;

    @NotNull
    public String name;

    @NotNull
    public boolean activated = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public static void add(String email, String password, String role, String name) {
        User user = new User();
        user.email = email;
        user.password = BcryptUtil.bcryptHash(password);
        user.role = role;
        user.name = name;
        user.persist();
    }

    public String getRole() {
        return role;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
