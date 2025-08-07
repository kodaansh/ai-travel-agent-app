package com.example.ai_travel_agent_app.model;

public class Booking {
	
	private final String bookingId;
    private final String email;
    private final String destination;
    private final String summary;

    public Booking(String bookingId, String email, String destination, String summary) {
        this.bookingId = bookingId;
        this.email = email;
        this.destination = destination;
        this.summary = summary;
    }

    public String getBookingId() { return bookingId; }
    public String getEmail() { return email; }
    public String getDestination() { return destination; }
    public String getSummary() { return summary; }

}
