package org.coursework.controller;

import org.coursework.model.EventConfiguration;
import org.coursework.model.EventConfigurationResponse;
import org.coursework.model.Ticket;
import org.coursework.service.CustomerService;
import org.coursework.service.EventTicketingService;
import org.coursework.utils.InputValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventTicketingController {
    private static final DateTimeFormatter DATABASE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final EventTicketingService eventTicketingService;
    private final CustomerService customerService ;

    public EventTicketingController(EventTicketingService eventTicketingService, CustomerService customerService) {
        this.eventTicketingService = eventTicketingService;
        this.customerService = customerService;

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
            String eventName = InputValidator.validateTextField("Event Name");

            // Event Date Validation
            System.out.println("Enter event date (format: yyyy/MM/dd): ");
            LocalDateTime eventDate = InputValidator.validateLocalDate();
            String formattedEventDate = eventDate.format(DATABASE_DATE_FORMATTER);

            System.out.println("Enter max capacity: ");
            int maxCapacity = InputValidator.validateMaxCapacity();

            System.out.println("Enter ticket release rate (tickets per release): ");
            int ticketReleaseRate = InputValidator.validateNumberField("Ticket Release Rate");

            System.out.println("Enter customer retrieval rate (retrieval per release): ");
            int customerRetrievalRate = InputValidator.validateNumberField("Customer Retrieval Rate");

            EventConfiguration eventConfiguration = new EventConfiguration(eventName, formattedEventDate, maxCapacity, ticketReleaseRate, customerRetrievalRate);

            System.out.println("Creating event configuration...");
            eventTicketingService.createEventConfiguration(eventConfiguration);
            System.out.printf("""
                    Event name: %s
                    Event date: %s
                    Max capacity: %d
                    Ticket release rate: %d
                    Customer retrieval rate: %d
                    """, eventName, formattedEventDate, maxCapacity, ticketReleaseRate, customerRetrievalRate);
            System.out.println("Event configuration created successfully");

        } catch (Exception e) {
            System.out.println("Failed to create event configuration: " + e.getMessage());
        }
    }

    //update Event Configuration
    public void updateEventConfiguration() {
        try {
            // Event Name Validation
            String eventName = InputValidator.validateTextField("Event Name");

            // Event Date Validation
            System.out.println("Enter event date (format: yyyy/MM/dd): ");
            LocalDateTime eventDate = InputValidator.validateLocalDate();
            String formattedEventDate = eventDate.format(DATABASE_DATE_FORMATTER);

            System.out.println("Enter max capacity: ");
            int maxCapacity = InputValidator.validateMaxCapacity();

            System.out.println("Enter ticket release rate (tickets per release): ");
            int ticketReleaseRate = InputValidator.validateNumberField("Ticket Release Rate");

            System.out.println("Enter customer retrieval rate (retrieval per release): ");
            int customerRetrievalRate = InputValidator.validateNumberField("Customer Retrieval Rate");

            EventConfiguration eventConfiguration = new EventConfiguration(eventName, formattedEventDate, maxCapacity, ticketReleaseRate, customerRetrievalRate);

            System.out.println("Updating event configuration...");
            eventTicketingService.updateEventConfiguration(eventConfiguration);
            System.out.println("Event configuration updated successfully");
            System.out.printf("""
                    Event name: %s
                    Event date: %s
                    Max capacity: %d
                    Ticket release rate: %d
                    Customer retrieval rate: %d
                    """, eventName, formattedEventDate, maxCapacity, ticketReleaseRate, customerRetrievalRate);

        } catch (Exception e) {
            System.out.println("Failed to update event configuration: " + e.getMessage());
        }
    }

    //findTotalTicketsPurchasedByCustomer
    public void findTotalTicketsPurchasedByCustomer() {
        try {
            String name = InputValidator.validateTextField("Customer Name");
            List<Ticket> tickets = eventTicketingService.findTotalTicketsPurchasedByCustomer(name);
            System.out.println("*** Total Tickets Purchased by Customer ***" + "\n");
            tickets.forEach(ticket -> {
                System.out.printf("""
                        Ticket ID: %s
                        Customer Name: %s
                        Vendor Name: %s
                        %n""", ticket.getTicketId(), ticket.getCustomer().getName(), ticket.getVendor().getName());
            });
            System.out.println("Total tickets purchased by customer found successfully");
        } catch (Exception e) {
            System.out.println("Failed to find total tickets purchased by customer: " + e.getMessage());
        }
    }

    //findTotalTicketsSoldByVendor
    public void findTotalTicketsSoldByVendor() {
        try {
            String name = InputValidator.validateTextField("Vendor Name");
            List<Ticket> tickets = eventTicketingService.findTotalTicketsSoldByVendor(name);
            tickets.forEach(ticket -> {
                System.out.printf("""
                        Ticket ID: %s
                        Customer Name: %s
                        Vendor Name: %s
                        %n""", ticket.getTicketId(), ticket.getCustomer().getName(), ticket.getVendor().getName());
            });
            System.out.println("Total tickets sold by vendor found successfully");
        } catch (Exception e) {
            System.out.println("Failed to find total tickets sold by vendor: " + e.getMessage());
        }
    }

    //view all tickets
    public void viewAllTickets() {
        try {
            List<Ticket> tickets = eventTicketingService.viewAllTickets();
            System.out.println("*** All Tickets ***" + "\n");
            tickets.forEach(ticket -> {
                System.out.printf("""
                        Ticket ID: %s
                        Customer Name: %s
                        Vendor Name: %s
                        %n""", ticket.getTicketId(), ticket.getCustomer().getName(), ticket.getVendor().getName());
            });
            System.out.println("All tickets viewed successfully");
        } catch (Exception e) {
            System.out.println("Failed to view all tickets: " + e.getMessage());
        }
    }

    //view ticket pool status
    public void viewTicketPoolStatus() {
        try {
            EventConfigurationResponse eventConfigurationResponse = eventTicketingService.viewTicketPoolStatus();
            System.out.println("Ticket pool status viewed successfully");
            System.out.println("*** Ticket Pool Status ***" + "\n");
            System.out.printf(
                    """
                    Configured: %s
                    Event Name: %s
                    Max Capacity: %d
                    Ticket Release Rate: %d
                    Customer Retrieval Rate: %d
                    available Tickets: %d
                    """
                    , eventConfigurationResponse.isConfigured(), eventConfigurationResponse.getEventName(), eventConfigurationResponse.getMaxCapacity(), eventConfigurationResponse.getTicketReleaseRate(), eventConfigurationResponse.getCustomerRetrievalRate(), eventConfigurationResponse.getAvailableTickets()
            );
            System.out.println("_".repeat(50));
        } catch (Exception e) {
            System.out.println("Failed to view ticket pool status: " + e.getMessage());
        }
    }

    //delete ticket for a customer by going though the all the avilvle tickets ID
    public void deleteTicketForCustomer() {
        try {
            String customerName = InputValidator.validateTextField("Customer Name");
            List<Ticket> tickets = eventTicketingService.findTotalTicketsPurchasedByCustomer(customerName);
            System.out.println("*** All Tickets for Customer ***" + "\n");
            tickets.forEach(ticket -> {
                System.out.printf("""
                        Ticket ID: %s
                        Customer Name: %s
                        Vendor Name: %s
                        %n""", ticket.getTicketId(), ticket.getCustomer().getName(), ticket.getVendor().getName());
            });
            System.out.println("Enter ticket ID to delete: ");
            String ticketId = InputValidator.validateMongoDBId();
            eventTicketingService.deleteTicket(ticketId);
            System.out.println("Ticket deleted successfully");
        } catch (Exception e) {
            System.out.println("Failed to delete ticket for customer: " + e.getMessage());
        }
    }

    // view available tickets
    public void viewTotalTicketsAvailable() {
        try {
            EventConfigurationResponse eventConfigurationResponse = eventTicketingService.viewTicketPoolStatus();
            System.out.println("*** Available Tickets ***" + "\n");
            System.out.printf("Available Tickets: %d%n", eventConfigurationResponse.getAvailableTickets());
            System.out.println("Available tickets viewed successfully");
        } catch (Exception e) {
            System.out.println("Failed to view available tickets: " + e.getMessage());
        }
    }
}
