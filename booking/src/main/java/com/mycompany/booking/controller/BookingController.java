package com.mycompany.booking.controller;

import com.mycompany.booking.service.BookingService;
import com.mycompany.booking.dto.BookingDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import jakarta.ws.rs.HeaderParam;

@Path("/api/bookings")  // Base path for booking-related API endpoints
@Produces(MediaType.APPLICATION_JSON)  // Response type
@Consumes(MediaType.APPLICATION_JSON)  // Request type
public class BookingController {

    @Inject
    BookingService bookingService;

    @GET
    public Response getBookings() {
        try {
            // Fetch a List of BookingDTOs from the service
            List<BookingDTO> bookings = bookingService.getBookings();
            return Response.ok(bookings).build();  // Return the list in the response
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error fetching bookings: " + e.getMessage())
                    .build();
        }
    }


    @POST
    public Response createBooking(BookingDTO bookingDTO, @HeaderParam("Authorization") String authorizationHeader) {
        try {
            // Extract the token from the Authorization header (remove "Bearer " prefix)
            String token = authorizationHeader != null ? authorizationHeader.substring("Bearer ".length()) : null;

            // Call the service to create the booking
            return bookingService.createBooking(bookingDTO, token);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating booking: " + e.getMessage())
                    .build();
        }
    }


    @PUT
    @Path("/{id}")
    public Response updateBooking(@PathParam("id") Long id, BookingDTO bookingDTO) {
        try {
            BookingDTO updatedBooking = bookingService.updateBooking(id, bookingDTO);
            if (updatedBooking == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Booking not found")
                        .build();
            }
            return Response.ok(updatedBooking).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating booking: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBooking(@PathParam("id") Long id) {
        try {
            boolean deleted = bookingService.deleteBooking(id);
            if (deleted) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Booking not found")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting booking: " + e.getMessage())
                    .build();
        }
    }
}
