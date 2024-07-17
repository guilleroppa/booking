package com.example.booking.controller;

import com.example.booking.model.Booking;
import com.example.booking.service.BookingService;
import com.example.booking.service.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private DiscountService discountService;

    @PostMapping
    public ResponseEntity<Booking> crearReserva(@RequestBody Booking booking) {
        try {
        if (discountService.validateDiscount(booking.getUserId(), booking.getHouseId(), booking.getDiscountCode())) {
            Booking newBooking = bookingService.saveBooking(booking);
            log.info("Reserva creada exitosamente para el usuario {}", booking.getUserId());
            return ResponseEntity.ok(newBooking);
        } else {
            log.error("Codigo de descuento invalido para el usuario {}", booking.getUserId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        } catch (Exception e) {
            log.error("Error al crear la reserva", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

