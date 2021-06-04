package com.crowleyworks.endpoints;

import java.util.concurrent.atomic.AtomicLong;

import com.crowleyworks.services.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

	private final AtomicLong counter = new AtomicLong();

	@Autowired
	BookingService bookingService;

	@GetMapping("/bookings")
	public Booking getBooking(@RequestParam(value = "customerName", defaultValue = "anonymous") String name) {
		bookingService.sayStuff();
		try {
			Booking x = bookingService.get(1);
			System.out.println("Booking: " + x.toString());
			return x;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new Booking(counter.incrementAndGet(), 99, name, "No comments");
	}
}