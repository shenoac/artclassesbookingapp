package com.mycompany.booking.service;

import com.mycompany.booking.dto.BookingDTO;
import com.mycompany.booking.model.Booking;
import com.mycompany.common.dto.UserBasicDTO;
import com.mycompany.common.model.UserBasic;
import com.mycompany.common.util.JwtUtil;
import com.mycompany.common.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import io.jsonwebtoken.Claims;
import java.util.stream.Collectors;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BookingService {

    private final UserRepository userRepository;  // Declare UserRepository

    @Inject
    public BookingService(UserRepository userRepository) { // Inject UserRepository
        this.userRepository = userRepository;
    }
    @Transactional
    public Response createBooking(BookingDTO bookingDTO, String token) {
        try {
            Claims claims = JwtUtil.validateToken(token);
            String userEmail = claims.getSubject();

            if (userEmail == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Unauthorized: Invalid JWT token")
                        .build();
            }

            // Use the UserRepository to find the User by email
            UserBasicDTO studentDTO = userRepository.findUserByEmail(userEmail);

            if (studentDTO == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Student not found")
                        .build();
            }

            // Create a new booking
            Booking newBooking = new Booking();
            newBooking.classId = bookingDTO.getClassId();


            UserBasic user = UserBasic.findById(studentDTO.getId());  // Find the user by ID
            newBooking.setUser(user);  // Set the user reference


            newBooking.bookingDate = bookingDTO.getBookingDate();
            newBooking.status = bookingDTO.getStatus() != null ? bookingDTO.getStatus() : "confirmed";
            newBooking.paymentStatus = bookingDTO.getPaymentStatus() != null ? bookingDTO.getPaymentStatus() : "pending";

            newBooking.persist();
            return Response.status(Response.Status.CREATED)
                    .entity(new BookingDTO(newBooking.id, newBooking.classId, newBooking.getUser().id,
                            newBooking.bookingDate, newBooking.status, newBooking.paymentStatus))
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating booking: " + e.getMessage())
                    .build();
        }
    }

// Update a booking
    @Transactional
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Booking existingBooking = Booking.findById(id);

        if (existingBooking != null) {
            existingBooking.bookingDate = bookingDTO.getBookingDate();
            existingBooking.status = bookingDTO.getStatus();
            existingBooking.paymentStatus = bookingDTO.getPaymentStatus();
            existingBooking.persist();

            return new BookingDTO(existingBooking.id, existingBooking.classId,
                    existingBooking.getUser().id,  // Fetch the student (User) and its ID
                    existingBooking.bookingDate, existingBooking.status, existingBooking.paymentStatus);
        }
        return null;  // Return null if the booking is not found
    }

    // Delete a booking
    @Transactional
    public boolean deleteBooking(Long id) {
        Booking existingBooking = Booking.findById(id);
        if (existingBooking != null) {
            existingBooking.delete();
            return true;
        }
        return false;  // Return false if the booking was not found
    }

    // New getBookings method
    @Transactional
    public List<BookingDTO> getBookings() {
        // Find all bookings from the database
        List<Booking> bookings = Booking.listAll();

        // Convert each Booking entity into a BookingDTO
        return bookings.stream()
                .map(booking -> new BookingDTO(
                        booking.id,
                        booking.classId,
                        booking.getUser().id,
                        booking.bookingDate,
                        booking.status,
                        booking.paymentStatus))
                .collect(Collectors.toList());
    }

}
