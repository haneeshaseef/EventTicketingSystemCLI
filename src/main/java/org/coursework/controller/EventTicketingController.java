package org.coursework.controller;

import org.coursework.model.EventConfiguration;
import org.coursework.model.Vendor;
import org.coursework.service.EventTicketingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;;

public class EventTicketingController {

    private static final Logger log = LoggerFactory.getLogger(EventTicketingController.class);
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter DATABASE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    final EventTicketingService eventTicketingService;
    Scanner scanner = new Scanner(System.in);

    public EventTicketingController(EventTicketingService eventTicketingService) {
        this.eventTicketingService = eventTicketingService;
    }

    //check Event Configuration
    public void checkEventConfiguration() {
        try {
            EventConfiguration eventConfiguration = eventTicketingService.checkEventConfiguration();
            System.out.println("*** Event Configuration ***" +"\n");
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
            log.error("Error checking event configuration: {}", e.getMessage(), e);
        }
    }

    //create Event Configuration
    public void createEventConfiguration() {
        try {
            // Event Name Validation
            System.out.println("Enter event name: ");
            String eventName = validateAndGetName();

            // Event Date Validation
            System.out.println("Enter event date (format: yyyy/MM/dd): "); // Updated prompt
            LocalDateTime eventDate = validateAndGetEventDate();
            String formattedEventDate = eventDate.format(DATABASE_DATE_FORMATTER); // Format for storage

            // Rest of the validation remains same
            System.out.println("Enter total tickets: ");
            int totalTickets = validateAndGetTotalTickets();

            System.out.println("Enter max capacity: ");
            int maxCapacity = validateAndGetMaxCapacity(totalTickets);

            System.out.println("Enter ticket release rate (tickets per minute): ");
            int ticketReleaseRate = validateAndGetRate("ticket release");

            System.out.println("Enter customer retrieval rate (customers per minute): ");
            int customerRetrievalRate = validateAndGetRate("customer retrieval");

            EventConfiguration eventConfiguration = new EventConfiguration(
                    eventName,
                    formattedEventDate,
                    totalTickets,
                    maxCapacity,
                    ticketReleaseRate,
                    customerRetrievalRate
            );

            System.out.println("Creating event configuration...");
            eventTicketingService.createEventConfiguration(eventConfiguration);
            System.out.println("Event configuration created successfully");

        } catch (Exception e) {
            log.error("Error creating event configuration: {}", e.getMessage(), e);
            System.out.println("Failed to create event configuration: " + e.getMessage());
        }
    }


    //Vendor Management
    public void viewAllVendors() {
        try {
            List<Vendor> vendors = eventTicketingService.getAllVendors();
            System.out.println("*** All Vendors ***" +"\n");
            vendors.forEach(vendor -> System.out.printf("Vendor ID: %s, Name: %s%n",
                    vendor.getParticipantId(), vendor.getName()));
        } catch (Exception e) {
            log.error("Error getting all vendors: {}", e.getMessage(), e);
        }
    }
    public void viewAllActiveVendors() {
        try {
            List<Vendor> activeVendors = eventTicketingService.getActiveVendors();
            System.out.println("*** Active Vendors ***" +"\n");
            activeVendors.forEach(vendor -> System.out.printf("Vendor ID: %s, Name: %s%n",
                    vendor.getParticipantId(), vendor.getName()));
        } catch (Exception e) {
            log.error("Error getting active vendors: {}", e.getMessage(), e);
        }
    }

    //find vendor by name
    public void findVendorByName() {
        try {
            System.out.println("Enter vendor name: ");
            String vendorName = scanner.nextLine().trim();
            Vendor vendor = eventTicketingService.findVendorByName(vendorName);
            System.out.printf("Vendor ID: %s, Name: %s%n", vendor.getParticipantId(), vendor.getName());
        } catch (Exception e) {
            log.error("Error finding vendor by name: {}", e.getMessage(), e);
            System.out.println("Failed to find vendor by name: " + e.getMessage());
        }
    }

    public void registerNewVendor() {
        try {
            System.out.println("Enter vendor name: ");
            String vendorName = validateAndGetName();

            System.out.println("Enter vendor email: ");
            String vendorEmail = validateEmail();

            System.out.println("Enter tickets per release: ");
            int ticketsPerRelease = validateAndGetRate("tickets per release");

            System.out.println("Enter ticket release interval (minutes): ");
            int ticketReleaseInterval = validateAndGetRate("ticket release interval");

            System.out.println("Enter total tickets to sell: ");
            int ticketsToSell = validateAndGetTotalTickets();

            Vendor vendor = new Vendor(vendorName, vendorEmail, ticketsPerRelease, ticketReleaseInterval, ticketsToSell);

            System.out.println("Registering new vendor...");
            eventTicketingService.registerNewVendor(vendor);
            System.out.println("Vendor registered successfully");

        } catch (Exception e) {
            log.error("Error registering new vendor: {}", e.getMessage(), e);
            System.out.println("Failed to register new vendor: " + e.getMessage());
        }
    }



    //deactivate vendor
    public void deactivateVendor() {
        try {
            System.out.println("Enter vendor name: ");
            String vendorName = validateAndGetName();
            eventTicketingService.deactivateVendor(vendorName);
            System.out.println("Vendor deactivated successfully");
        } catch (Exception e) {
            log.error("Error deactivating vendor: {}", e.getMessage(), e);
            System.out.println("Failed to deactivate vendor: " + e.getMessage());
        }
    }

    //reactivate vendor
    public void reactivateVendor() {
        try {
            System.out.println("Enter vendor name: ");
            String vendorName = validateAndGetName();
            eventTicketingService.reactivateVendor(vendorName);
            System.out.println("Vendor reactivated successfully");
        } catch (Exception e) {
            log.error("Error reactivating vendor: {}", e.getMessage(), e);
            System.out.println("Failed to reactivate vendor: " + e.getMessage());
        }
    }

    //delete vendor
    public void deleteVendor() {
        try {
            System.out.println("Enter vendor name: ");
            String vendorName = validateAndGetName();
            eventTicketingService.deleteVendor(vendorName);
            System.out.println("Vendor deleted successfully");
        } catch (Exception e) {
            log.error("Error deleting vendor: {}", e.getMessage(), e);
            System.out.println("Failed to delete vendor: " + e.getMessage());
        }
    }

    //Customer Management

    //Validation methods
    private String validateAndGetName() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Event name cannot be empty. Please enter a valid name: ");
                continue;
            }
            if (input.length() > 100) {
                System.out.println("Event name cannot exceed 100 characters. Please enter a valid name: ");
                continue;
            }
            return input;
        }
    }

    private LocalDateTime validateAndGetEventDate() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                // Parse the date in yyyy/MM/dd format
                LocalDate date = LocalDate.parse(input, INPUT_DATE_FORMATTER);

                // Add default time (e.g., noon) to make it a LocalDateTime
                LocalDateTime dateTime = date.atTime(12, 0); // Sets time to 12:00 PM

                // Validate that the date is in the future
                if (dateTime.isBefore(LocalDateTime.now())) {
                    System.out.println("Event date must be in the future. Please enter a valid date (format: yyyy/MM/dd): ");
                    continue;
                }

                return dateTime;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date in format yyyy/MM/dd (e.g., 2024/12/31): ");
            }
        }
    }


    private int validateAndGetTotalTickets() {
        while (true) {
            try {
                int totalTickets = Integer.parseInt(scanner.nextLine().trim());
                if (totalTickets <= 0) {
                    System.out.println("Total tickets must be greater than 0. Please enter a valid number: ");
                    continue;
                }
                return totalTickets;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for total tickets. Please enter a valid number: ");
            }
        }
    }

    private int validateAndGetMaxCapacity(int totalTickets) {
       while (true) {
           try {
               int maxCapacity = Integer.parseInt(scanner.nextLine().trim());
               if (maxCapacity <= 0) {
                   System.out.println("Max capacity must be greater than 0. Please enter a valid number: ");
                   continue;
               }
               if (maxCapacity < totalTickets) {
                   System.out.println("Max capacity cannot be less than total tickets. Please enter a valid number: ");
                   continue;
               }
               return maxCapacity;
           } catch (NumberFormatException e) {
               System.out.println("Invalid number format for max capacity. Please enter a valid number: ");
           }
       }
    }

    private int validateAndGetRate(String rateType) {
        while (true) {
            try {
                int rate = Integer.parseInt(scanner.nextLine().trim());
                if (rate <= 0) {
                    System.out.printf("%s rate must be greater than 0. Please enter a valid number: ", rateType);
                    continue;
                }
                return rate;
            } catch (NumberFormatException e) {
                System.out.printf("Invalid number format for %s rate. Please enter a valid number: ", rateType);
            }
        }
    }

    private String validateEmail() {
        //Regex pattern for email validation
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        while (true) {
            String email = scanner.nextLine().trim();
            if (!email.matches(emailPattern)) {
                System.out.println("Invalid email format. Please enter a valid email: ");
                continue;
            }
            return email;
        }
    }
}
