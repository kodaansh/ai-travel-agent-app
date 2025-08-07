package com.example.ai_travel_agent_app.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.example.ai_travel_agent_app.model.Booking;

@Component
public class BookingStore {
	
	private final Map<String, Booking> bookings = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(1000);

    public Booking createBooking(String email, String destination, String summary) {
        String bookingId = "BKG-" + counter.getAndIncrement();
        Booking booking = new Booking(bookingId, email, destination, summary);
        bookings.put(bookingId, booking);
        return booking;
    }

    public boolean cancelBooking(String id) { return bookings.remove(id) != null; }

    public Collection<Booking> getByEmail(String email) {
        List<Booking> list = new ArrayList<>();
        for (Booking b : bookings.values()) {
            if (b.getEmail().equalsIgnoreCase(email)) list.add(b);
        }
        return list;
    }

	public Collection<Booking> getAllBookings() {
		// TODO Auto-generated method stub
		return bookings.values();
	}

}
