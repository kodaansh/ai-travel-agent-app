package com.example.ai_travel_agent_app.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class TravelAgentTools {

	private final Map<String, String> bookings = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(1000);

    // ---------------- Flight Booking Tools ----------------

    @Tool(description = "Book a flight for the given email and destination. Requires both 'email' and 'destination' as input. Returns a booking ID.")
    public String bookFlight(String email, String destination) {
        String bookingId = "BKG-" + counter.getAndIncrement();
        bookings.put(bookingId, destination + " for " + email);
        return "Booking successful! Booking ID: " + bookingId;
    }

    @Tool(description = "Cancel a booking using the booking ID")
    public String cancelBooking(String bookingId) {
        if (bookings.remove(bookingId) != null) {
            return "Booking " + bookingId + " cancelled successfully!";
        }
        return "No booking found with ID " + bookingId;
    }

    @Tool(description = "Get all bookings for a given email")
    public List<String> getAllBookings(String email) {
        List<String> list = bookings.entrySet().stream()
                .filter(e -> e.getValue().endsWith(email))
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.toList());
        return list.isEmpty() ? List.of("No bookings found for " + email) : list;
    }

    // ---------------- Travel Info Tools ----------------

    @Tool(description = "Get the current weather conditions for a city")
    public String getWeather(String city) {
        // Simulated weather info; in real-world, call a weather API here
        Map<String, String> sampleWeather = Map.of(
                "Paris", "Sunny 28°C",
                "London", "Cloudy 20°C",
                "New York", "Rainy 18°C",
                "Tokyo", "Clear 25°C"
        );

        String weather = sampleWeather.get(city);
        return (weather != null)
                ? "Weather in " + city + ": " + weather
                : "Sorry, weather data is not available for " + city;
    }

    @Tool(description = "Get top 3 hotel suggestions for a city")
    public List<String> getHotelSuggestions(String city) {
        // Simulated hotel suggestions
        Map<String, List<String>> hotels = new HashMap<>();
        hotels.put("Paris", List.of("Hotel Le Meurice", "Four Seasons George V", "Hotel Lutetia"));
        hotels.put("London", List.of("The Savoy", "The Ritz London", "The Langham"));
        hotels.put("New York", List.of("The Plaza", "The Peninsula", "Mandarin Oriental"));
        hotels.put("Tokyo", List.of("Park Hyatt", "The Ritz-Carlton Tokyo", "Palace Hotel Tokyo"));

        List<String> cityHotels = hotels.get(city);
        return (cityHotels != null)
                ? cityHotels
                : List.of("Sorry, no hotel suggestions available for " + city);
    }

}
