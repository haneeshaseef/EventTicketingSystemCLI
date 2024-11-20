package org.coursework.service;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.coursework.model.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class EventTicketingService {
    private static final String BASE_URL = "http://localhost:8080/api";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Event Configuration Management
    public EventConfiguration checkEventConfiguration() {
        try {
            String url = BASE_URL + "/ticket-pool/configuration";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), EventConfiguration.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to check event configuration: " + e.getMessage(), e);
        }
    }

    public void createEventConfiguration(EventConfiguration eventConfiguration) {
        try {
            String url = BASE_URL + "/ticket-pool/configuration";
            String jsonBody = objectMapper.writeValueAsString(eventConfiguration);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create event configuration: " + e.getMessage(), e);
        }
    }

    public void updateEventConfiguration(EventConfiguration eventConfiguration) {
        try {
            String url = BASE_URL + "/ticket-pool";
            String jsonBody = objectMapper.writeValueAsString(eventConfiguration);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to update event configuration: " + e.getMessage(), e);
        }
    }

    // Vendor Management
    public List<Vendor> getAllVendors() {
        try {
            String url = BASE_URL + "/vendors";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            VendorResponse vendorResponse = objectMapper.readValue(response.body(), VendorResponse.class);

            // Convert the Map values to a List
            return new ArrayList<>(vendorResponse.getVendors().values());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all vendors: " + e.getMessage(), e);
        }
    }

    public List<Vendor> getActiveVendors() {
        try {
            String url = BASE_URL + "/vendors/active";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            VendorResponse vendorResponse = objectMapper.readValue(response.body(), VendorResponse.class);

            // Convert the Map values to a List
            return new ArrayList<>(vendorResponse.getVendors().values());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all vendors: " + e.getMessage(), e);
        }
    }

    public void registerNewVendor(Vendor vendor) {
        try {
            String url = BASE_URL + "/vendors";
            String jsonBody = objectMapper.writeValueAsString(vendor);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to register vendor: " + e.getMessage(), e);
        }
    }

    public void deactivateVendor(String vendorName) {
        try {
            String url = BASE_URL + "/vendors/" + vendorName + "/deactivate";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Vendor deactivated successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to deactivate vendor: " + e.getMessage(), e);
        }
    }

    public Vendor findVendorByName(String vendorName) {
        try {
            String url = BASE_URL + "/vendors/" + vendorName;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), Vendor.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find vendor: " + e.getMessage(), e);
        }
    }

    public void reactivateVendor(String vendorName) {
        try {
            String url = BASE_URL + "/vendors/" + vendorName + "/reactivate";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Vendor reactivated successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to reactivate vendor: " + e.getMessage(), e);
        }
    }

    public void deleteVendor(String vendorName) {
        try {
            String url = BASE_URL + "/vendors/" + vendorName;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .DELETE()
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Vendor deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete vendor: " + e.getMessage(), e);
        }
    }


    // Customer Management

}