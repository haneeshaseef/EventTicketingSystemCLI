package org.coursework.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.coursework.model.EventConfiguration;
import org.coursework.model.Ticket;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class EventTicketingService {
    private static final String BASE_URL = "http://localhost:8080/api/ticket-pool";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Event Configuration Management
    public EventConfiguration checkEventConfiguration() {
        try {
            String url = BASE_URL + "/configuration";
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), EventConfiguration.class);
        } catch (ConnectException e) {
            throw new RuntimeException("Failed to connect to the server: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to check event configuration: " + e.getMessage());
        }
    }

    public void createEventConfiguration(EventConfiguration eventConfiguration) {
        try {
            String url = BASE_URL + "/configuration";
            String jsonBody = objectMapper.writeValueAsString(eventConfiguration);

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(jsonBody)).build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (ConnectException e) {
            throw new RuntimeException("Failed to connect to the server: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create event configuration: " + e.getMessage());
        }
    }

    public void updateEventConfiguration(EventConfiguration eventConfiguration) {
        try {
            String jsonBody = objectMapper.writeValueAsString(eventConfiguration);

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL)).header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(jsonBody)).build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (ConnectException e) {
            throw new RuntimeException("Failed to connect to the server: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to update event configuration: " + e.getMessage());
        }
    }

    //viewTotalTicketsAvailable
    public int viewTotalTicketsAvailable() {
        try {
            String url = BASE_URL + "/availableTickets";
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return Integer.parseInt(response.body());
        } catch (ConnectException e) {
            throw new RuntimeException("Failed to connect to the server: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get total tickets available: " + e.getMessage());
        }
    }

    //findTotalTicketsPurchasedByCustomer
    public int findTotalTicketsPurchasedByCustomer(String customerName) {
        try {
            String url = BASE_URL + "/" + customerName + "/totalTicketsPurchased";
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return Integer.parseInt(response.body());
        } catch (ConnectException e) {
            throw new RuntimeException("Failed to connect to the server: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to find total tickets purchased by customer: " + e.getMessage());
        }
    }

    //findTotalTicketsSoldByVendor
    public int findTotalTicketsSoldByVendor(String vendorName) {
        try {
            String url = BASE_URL + "/" + vendorName + "/totalTicketsSold";
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return Integer.parseInt(response.body());
        } catch (ConnectException e) {
            throw new RuntimeException("Failed to connect to the server: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to find total tickets sold by vendor: " + e.getMessage());
        }
    }

    //view all tickets
    public List<Ticket> viewAllTickets() {
        try {
            String url = BASE_URL + "/tickets";
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(), new TypeReference<List<Ticket>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to view all tickets: " + e.getMessage());
        }
    }

    //deleteTicketForCustomer
    public void deleteTicketForCustomer(String customerName) {
        try {
            String url = BASE_URL + "/" + customerName + "/deleteTicket";
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).DELETE().build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (ConnectException e) {
            throw new RuntimeException("Failed to connect to the server: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete ticket for customer: " + e.getMessage());
        }
    }

    //deleteTicket
    public void deleteTicket(String ticketId) {
        try {
            String url = BASE_URL + "/" + ticketId;
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).DELETE().build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (ConnectException e) {
            throw new RuntimeException("Failed to connect to the server: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete ticket: " + e.getMessage());
        }
    }
}