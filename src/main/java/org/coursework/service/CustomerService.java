package org.coursework.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.coursework.model.Customer;
import org.coursework.model.CustomerResponse;
import org.coursework.model.Ticket;
import org.coursework.model.TicketsResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private final String BASE_URL = "http://localhost:8080/api/customers";

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    //view all customers
    public List<Customer> viewAllCustomers() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL+"/all"))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            CustomerResponse customerResponse = objectMapper.readValue(response.body(), CustomerResponse.class);

            // Convert the Map values to a List
            return new ArrayList<>(customerResponse.getCustomers().values());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all customers: " + e.getMessage());
        }
    }

    //view all active customers
    public List<Customer> viewActiveCustomers() {
        try {
            String url = BASE_URL + "/active";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            CustomerResponse customerResponse = objectMapper.readValue(response.body(), CustomerResponse.class);

            // Convert the Map values to a List
            return new ArrayList<>(customerResponse.getCustomers().values());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all active customers: " + e.getMessage());
        }
    }

    //register a new customer
    public void registerNewCustomer(Customer customer) {
        try {
            String jsonBody = objectMapper.writeValueAsString(customer);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to register new customer: " + e.getMessage());
        }
    }

    //update a customer
    public void updateCustomer(Customer customer) {
        try {
            String jsonBody = objectMapper.writeValueAsString(customer);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to update customer: " + e.getMessage());
        }
    }

    //find total tickets purchased by a customer
    public List<Ticket> findAllPurchasedTicketsByCustomer(String customerName) {
        try {
            String url = BASE_URL +"/"+ customerName + "/tickets";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            TicketsResponse ticketsResponse = objectMapper.readValue(response.body(), TicketsResponse.class);

            // Ticktesponse is list of ticets
            return ticketsResponse.getTickets();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find total tickets purchased by customer: " + e.getMessage());
        }
    }

    //deactivate a customer
    public void deactivateCustomer(String customerName) {
        try {
            String url = BASE_URL +"/"+ customerName + "/deactivate";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to deactivate customer: " + e.getMessage());
        }
    }

    //reactivate a customer
    public void reactivateCustomer(String customerName) {
        try {
            String url = BASE_URL +"/"+ customerName + "/reactivate";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to reactivate customer: " + e.getMessage());
        }
    }

    //find a customer by name
    public Customer findCustomerByName(String customerName) {
        try {
            String url = BASE_URL +"/name/"+ customerName;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), Customer.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find customer: " + e.getMessage());
        }
    }

    //delete a customer
    public void deleteCustomer(String customerName) {
        try {
            String url = BASE_URL +"/"+ customerName;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .DELETE()
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete customer: " + e.getMessage());
        }
    }
}
