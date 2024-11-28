package org.coursework.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.coursework.model.Vendor;
import org.coursework.model.VendorResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class VendorService {
    private final String BASE_URL = "http://localhost:8080/api/vendors";;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Vendor> getAllVendors() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL+"/all"))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            VendorResponse vendorResponse = objectMapper.readValue(response.body(), VendorResponse.class);

            // Convert the Map values to a List
            return new ArrayList<>(vendorResponse.getVendors().values());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all vendors: " + e.getMessage());
        }
    }

    public List<Vendor> getActiveVendors() {
        try {
            String url = BASE_URL + "/active";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            VendorResponse vendorResponse = objectMapper.readValue(response.body(), VendorResponse.class);

            // Convert the Map values to a List
            return new ArrayList<>(vendorResponse.getVendors().values());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all vendors: " + e.getMessage());
        }
    }

    public void registerNewVendor(Vendor vendor) {
        try {
            String jsonBody = objectMapper.writeValueAsString(vendor);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to register vendor: " + e.getMessage());
        }
    }

    public void deactivateVendor(String vendorName) {
        try {
            String url = BASE_URL +"/"+ vendorName + "/deactivate";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Vendor deactivated successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to deactivate vendor: " + e.getMessage());
        }
    }

    public Vendor findVendorByName(String vendorName) {
        try {
            String url = BASE_URL + "/name/" + vendorName;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), Vendor.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find vendor: " + e.getMessage());
        }
    }

    public void reactivateVendor(String vendorName) {
        try {
            String url = BASE_URL + "/" + vendorName + "/reactivate";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Vendor reactivated successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to reactivate vendor: " + e.getMessage());
        }
    }

    public void deleteVendor(String vendorName) {
        try {
            String url = BASE_URL + "/" + vendorName;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .DELETE()
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Vendor deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete vendor: " + e.getMessage());
        }
    }
}
