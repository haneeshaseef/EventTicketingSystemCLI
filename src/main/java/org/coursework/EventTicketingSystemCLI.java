package org.coursework;

import org.coursework.controller.CustomerController;
import org.coursework.controller.EventTicketingController;
import org.coursework.controller.VendorController;
import org.coursework.service.CustomerService;
import org.coursework.service.EventTicketingService;
import org.coursework.service.VendorService;
import org.coursework.utils.InputValidator;

public class EventTicketingSystemCLI {
    private static final EventTicketingController eventTicketingController = new EventTicketingController(new EventTicketingService());
    private static final VendorController vendorController = new VendorController(new VendorService());
    private static final CustomerController customerController = new CustomerController(new CustomerService());

    public static void main(String[] args) {
        //print welcome message
        System.out.println("=".repeat(50));
        System.out.println("*** Welcome to the Event Ticketing System! ***");
        System.out.println("=".repeat(50) + "\n");
        while (true) {
            //print menu
            System.out.println("-".repeat(50));
            System.out.println("*** Main Menu ***");
            System.out.println("-".repeat(50));
            System.out.println("""
                    1. Event Configuration and Status
                    2. Ticket Management
                    3. Vendor Management
                    4. Customer Management
                    0. Exit System
                    """);
            System.out.println("-".repeat(50));
            System.out.print("Please enter your option:");

            //get user input
            int option = InputValidator.validateOptionNumberInput();

            //switch case for user input
            switch (option) {
                case 1 -> displayEventConfigurationAndStatusMenu();
                case 2 -> displayTicketManagementMenu();
                case 3 -> displayVendorManagementMenu();
                case 4 -> displayCustomerManagementMenu();
                case 0 -> {
                    System.out.println("Exiting System...");
                    InputValidator.closeScanner();
                    System.out.println("=".repeat(50));
                    System.out.println("*** Thank you for using the Event Ticketing System! ***");
                    System.out.println("=".repeat(50));
                    System.exit(0);
                }
                default -> System.out.println("Invalid Option. Please try again.");
            }
        }


    }

    //display event configuration and status
    private static void displayEventConfigurationAndStatusMenu() {
        while (true) {
            System.out.println("-".repeat(50));
            System.out.println("*** Event Configuration and Status Menu ***");
            System.out.println("-".repeat(50));
            System.out.println("""
                    a. Check Event Configuration
                    b. Create Event Configuration
                    c. Update Event Configuration
                    d. View Ticket Pool Status
                    m. Back to Main Menu
                    """);
            System.out.println("-".repeat(50));
            System.out.print(" Please select an option(Please enter M to return to the main menu): ");

            String option = InputValidator.validateOptionAlphabetInput("abcdm");

            switch (option) {
                case "a" -> {
                    System.out.println("Checking Event Configuration...");
                    eventTicketingController.checkEventConfiguration();

                }
                case "b" -> {
                    System.out.println("Creating Event Configuration...");
                    eventTicketingController.createEventConfiguration();
                }
                case "c" -> System.out.println("Updating Event Configuration...");
                case "d" -> System.out.println("Viewing Ticket Pool Status...");
                case "m" -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid Option. Please try again.");
            }
        }
    }

    //display ticket management menu
    private static void displayTicketManagementMenu() {
        while (true) {
            System.out.println("_".repeat(50));
            System.out.println("*** Ticket Management Menu ***");
            System.out.println("_".repeat(50) + "\n");
            System.out.println("""
                    a. View total tickets available
                    b. View total tickets sold
                    c. find total tickets purchased by a customer
                    d. find total tickets sold by a vendor
                    e. View all Ticket Status
                    f. Delete a Ticket for a Customer
                    m. Back to Main Menu
                    """);
            System.out.println("_".repeat(50));
            System.out.print("Please select an option(Please enter M to return to the main menu): ");

            String option = InputValidator.validateOptionAlphabetInput("abcdefgm");

            switch (option) {
                case "a" -> {
                    System.out.println("Viewing total tickets available...");
                    eventTicketingController.viewTotalTicketsAvailable();
                }
                case "b" -> {
                    System.out.println("Viewing total tickets sold...");
                    eventTicketingController.viewTotalTicketsSold();
                }
                case "c" -> {
                    System.out.println("Finding total tickets purchased by a customer...");
                    eventTicketingController.findTotalTicketsPurchasedByCustomer();
                }
                case "d" -> {
                    System.out.println("Finding total tickets sold by a vendor...");
                    eventTicketingController.findTotalTicketsSoldByVendor();
                }
                case "e" -> {
                    System.out.println("Viewing all Ticket Status...");
                    eventTicketingController.viewAllTicketStatus();
                }
                case "f" -> {
                    System.out.println("Deleting a Ticket for a Customer...");
                    eventTicketingController.deleteTicketForCustomer();
                }
                case "m" -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid Option. Please try again.");
            }
        }
    }

    //display customer management menu
    private static void displayCustomerManagementMenu() {
        while (true) {
            System.out.println("_".repeat(50));
            System.out.println("*** Customer Management Menu ***");
            System.out.println("_".repeat(50) + "\n");
            System.out.println("""
                    a. View All Customers
                    b. View All Active Customers
                    c. Register New Customer
                    d. Find Customer by Name
                    e. Find total tickets purchased by a customer
                    f. Deactivate Customer
                    g. Reactivate Customer
                    h. Delete Customer
                    m. Back to Main Menu
                    """);
            System.out.println("_".repeat(50));
            System.out.print("Please select an option(Please enter M to return to the main menu): ");

            String option = InputValidator.validateOptionAlphabetInput("abcdefgm");

            switch (option) {
                case "a" -> {
                    System.out.println("Viewing All Customers...");
                    customerController.viewAllCustomers();
                }
                case "b" -> {
                    System.out.println("Viewing All Active Customers...");
                    customerController.viewAllActiveCustomers();
                }
                case "c" -> {
                    System.out.println("Registering New Customer...");
                    customerController.registerNewCustomer();
                }
                case "d" -> {
                    System.out.println("Finding Customer by Name...");
                    customerController.findCustomerByName();
                }
                case "e" -> {
                    System.out.println("Finding total tickets purchased by a customer...");
                    customerController.findTotalTicketsPurchasedByCustomer();
                }
                case "f" -> {
                    System.out.println("Deactivating Customer...");
                    customerController.deactivateCustomer();
                }
                case "g" -> {
                    System.out.println("Reactivating Customer...");
                    customerController.reactivateCustomer();
                }
                case "h" -> {
                    System.out.println("Deleting Customer...");
                    customerController.deleteCustomer();
                }
                case "m" -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid Option. Please try again.");
            }
        }
    }

    //display vendor management menu
    private static void displayVendorManagementMenu() {
        while (true) {
            System.out.println("_".repeat(50));
            System.out.println("*** Vendor Management Menu ***");
            System.out.println("_".repeat(50) + "\n");
            System.out.println("""
                    a. View All Vendors
                    b. View All Active Vendors
                    c. Register New Vendor
                    d. Find Vendor by Name
                    e. Reactive Vendor
                    f. Deactivate Vendor
                    g. Delete Vendor
                    m. Back to Main Menu
                    """);
            System.out.println("_".repeat(50));
            System.out.print("Please select an option(Please enter M to return to the main menu): ");

            String option = InputValidator.validateOptionAlphabetInput("abcdefgm");

            switch (option) {
                case "a" -> {
                    System.out.println("Viewing All Vendors...");
                    vendorController.viewAllVendors();
                }
                case "b" -> {
                    System.out.println("Viewing All Active Vendors...");
                    vendorController.viewAllActiveVendors();
                }
                case "c" -> {
                    System.out.println("Registering New Vendor...");
                    vendorController.registerNewVendor();
                }
                case "d" -> {
                    System.out.println("Finding Vendor by Name...");
                    vendorController.findVendorByName();
                }
                case "e" -> {
                    System.out.println("Reactivating Vendor...");
                    vendorController.reactivateVendor();
                }
                case "f" -> {
                    System.out.println("Deactivating Vendor...");
                    vendorController.deactivateVendor();
                }
                case "g" -> {
                    System.out.println("Deleting Vendor...");
                    vendorController.deleteVendor();
                }
                case "m" -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid Option. Please try again.");
            }
        }
    }

}