package org.coursework.controller;

import org.coursework.model.EventConfiguration;
import org.coursework.model.Vendor;
import org.coursework.service.EventTicketingService;
import org.coursework.utils.InputValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventTicketingController {
    private static final DateTimeFormatter DATABASE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final EventTicketingService eventTicketingService;

    public EventTicketingController(EventTicketingService eventTicketingService) {
        this.eventTicketingService = eventTicketingService;

    }

    //check Event Configuration
    public void checkEventConfiguration() {
        try {
            EventConfiguration eventConfiguration = eventTicketingService.checkEventConfiguration();
            System.out.println("*** Event Configuration ***" + "\n");
            System.out.printf("""
                    Event Name: %s
                    Event Date: %s
                    Total Tickets: %d
                    Max Capacity: %d
                    Ticket Release Rate: %d
                    Customer Retrieval Rate: %d
                    %n""", eventConfiguration.getEventName(), eventConfiguration.getEventDate(), eventConfiguration.getTotalTickets(), eventConfiguration.getMaxCapacity(), eventConfiguration.getTicketReleaseRate(), eventConfiguration.getCustomerRetrievalRate());
            System.out.println("Event configuration checked successfully");
        } catch (Exception e) {
            System.out.println("Failed to check event configuration: " + e.getMessage());
        }
    }

    //create Event Configuration
    public void createEventConfiguration() {
        try {
            // Event Name Validation
            System.out.println("Enter event name: ");
            String eventName = InputValidator.validateTextField("Event Name");

            // Event Date Validation
            System.out.println("Enter event date (format: yyyy/MM/dd): "); // Updated prompt
            LocalDateTime eventDate = InputValidator.validateLocalDate();
            String formattedEventDate = eventDate.format(DATABASE_DATE_FORMATTER); // Format for storage

            // Rest of the validation remains same
            System.out.println("Enter total tickets: ");
            int totalTickets = InputValidator.validateNumberField("Total Tickets");

            System.out.println("Enter max capacity: ");
            int maxCapacity = InputValidator.validateMaxCapacity(totalTickets);

            System.out.println("Enter ticket release rate (tickets per release): ");
            int ticketReleaseRate = InputValidator.validateNumberField("Ticket Release Rate");

            System.out.println("Enter customer retrieval rate (retrieval per release): ");
            int customerRetrievalRate = InputValidator.validateNumberField("Customer Retrieval Rate");

            EventConfiguration eventConfiguration = new EventConfiguration(eventName, formattedEventDate, totalTickets, maxCapacity, ticketReleaseRate, customerRetrievalRate);

            System.out.println("Creating event configuration...");
            eventTicketingService.createEventConfiguration(eventConfiguration);
            System.out.println("Event configuration created successfully");

        } catch (Exception e) {
            System.out.println("Failed to create event configuration: " + e.getMessage());
        }
    }

    //view total tickets available
    public void viewTotalTicketsAvailable() {
        try {
            int totalTicketsAvailable = eventTicketingService.viewTotalTicketsAvailable();
            System.out.println("*** Total Tickets Available ***" + "\n");
            System.out.printf("Total Tickets Available: %d%n", totalTicketsAvailable);
            System.out.println("Total tickets available viewed successfully");
        } catch (Exception e) {
            System.out.println("Failed to view total tickets available: " + e.getMessage());
        }
    }

    //view total tickets sold
    public void viewTotalTicketsSold() {
        try {
            List<Vendor> vendors = (List<Vendor>) eventTicketingService.viewTotalTicketsSold();
            System.out.println("*** Total Tickets Sold ***" + "\n");
            vendors.forEach(vendor -> {
                System.out.printf("""
                        Vendor ID: %s
                        Vendor Name: %s
                        Vendor Email: %s
                        Tickets Per Release: %d
                        Ticket Release Interval: %d
                        Total Tickets to Sell: %d
                        Vendor Status: %s
                        %n""", vendor.getParticipantId(), vendor.getName(), vendor.getEmail(), vendor.getTicketsPerRelease(), vendor.getTicketReleaseInterval(), vendor.getTicketsToSell(), vendor.isActive() ? "Active" : "Inactive");
            });
            System.out.println("Total tickets sold viewed successfully");
        } catch (Exception e) {
            System.out.println("Failed to view total tickets sold: " + e.getMessage());
        }
    }

    //findTotalTicketsPurchasedByCustomer
    public void findTotalTicketsPurchasedByCustomer() {
        try {
            System.out.println("Enter customer name: ");
            String name = InputValidator.validateTextField("Customer Name");
            int totalTicketsPurchased = eventTicketingService.findTotalTicketsPurchasedByCustomer(name);
            System.out.printf("Total tickets purchased by %s: %d%n", name, totalTicketsPurchased);
            System.out.println("Total tickets purchased by customer found successfully");
        } catch (Exception e) {
            System.out.println("Failed to find total tickets purchased by customer: " + e.getMessage());
        }
    }

    //findTotalTicketsSoldByVendor
    public void findTotalTicketsSoldByVendor() {
        try {
            System.out.println("Enter vendor name: ");
            String name = InputValidator.validateTextField("Vendor Name");
            int totalTicketsSold = eventTicketingService.findTotalTicketsSoldByVendor(name);
            System.out.printf("Total tickets sold by %s: %d%n", name, totalTicketsSold);
            System.out.println("Total tickets sold by vendor found successfully");
        } catch (Exception e) {
            System.out.println("Failed to find total tickets sold by vendor: " + e.getMessage());
        }
    }

    //view all tickets status
    public void viewAllTicketStatus() {
        try {
            //TODO: Implement this method
        } catch (Exception e) {
            System.out.println("Failed to view all tickets status: " + e.getMessage());
        }
    }

    //delete ticket for a customer with count
    public void deleteTicketForCustomer() {
        try {
            System.out.println("Enter customer name: ");
            String customerName = InputValidator.validateTextField("Customer Name");
            System.out.println("Enter number of tickets to delete: ");
            int count = InputValidator.validateNumberField("Number of Tickets");
            eventTicketingService.deleteTicketForCustomer(customerName, count);
            System.out.printf("Deleted %d tickets for customer %s%n", count, customerName);
            System.out.println("Tickets deleted successfully");
        } catch (Exception e) {
            System.out.println("Failed to delete ticket for customer: " + e.getMessage());
        }
    }
}
