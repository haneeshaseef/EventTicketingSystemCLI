package org.coursework;

import org.coursework.controller.EventTicketingController;
import org.coursework.service.EventTicketingService;

import java.util.Scanner;

public class EventTicketingSystemCLI {
    //import scanner
    private static final Scanner scanner = new Scanner(System.in);;
    private static final EventTicketingController eventTicketingController = new EventTicketingController(new EventTicketingService());
    public static void main(String[] args) {
        //print welcome message
        System.out.println("=".repeat(50));
        System.out.println("*** Welcome to the Event Ticketing System! ***");
        System.out.println("=".repeat(50)+"\n");
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
            int option = validateOptionNumberInput();

            //switch case for user input
            switch (option) {
                case 1 -> displayEventConfigurationAndStatusMenu();
                case 2 -> displayTicketManagementMenu();
                case 3 -> displayVendorManagementMenu();
                case 4 -> displayCustomerManagementMenu();
                case 0 -> {
                    System.out.println("Exiting System...");
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

            String option = scanner.next().toLowerCase();

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
        System.out.println("_".repeat(50));
        System.out.println("*** Ticket Management Menu ***");
        System.out.println("_".repeat(50) + "\n");
        System.out.println("""
                    a. View Event Ticket Status
                    b. View Vendor Ticket Status
                    c. View Customer Ticket Status
                    d. Get Ticket by ID
                    e. Delete Ticket
                    m. Back to Main Menu
                    """);
        System.out.println("_".repeat(50));
        System.out.println("Please select an option(Please enter M to return to the main menu): ");

        String option = validateOptionAlphabetInput("abcdem");

        switch (option) {
            case "a" -> {
                System.out.println("Viewing Event Ticket Status...");

            }
            case "b" -> System.out.println("Viewing Vendor Ticket Status...");
            case "c" -> System.out.println("Viewing Customer Ticket Status...");
            case "d" -> System.out.println("Getting Ticket by ID...");
            case "e" -> System.out.println("Deleting Ticket...");
            case "m" -> System.out.println("Returning to Main Menu...");
            default -> System.out.println("Invalid Option. Please try again.");
        }

    }

    //display customer management menu
    private static void displayCustomerManagementMenu() {
        System.out.println("_".repeat(50));
        System.out.println("*** Customer Management Menu ***");
        System.out.println("_".repeat(50) + "\n");
        System.out.println("""
                a. View All Customers
                b. View All Active Customers
                c. Register New Customer
                d. Find Customer by Name
                e. Find Total Tickets Purchased by Customer
                f. Deactivate Customer
                g. Delete Customer
                m. Back to Main Menu
                """);
        System.out.println("_".repeat(50));
        System.out.println("Please select an option(Please enter M to return to the main menu): ");

        String option = scanner.next().toLowerCase();

        switch (option) {
            case "a" -> System.out.println("Viewing All Customers...");
            case "b" -> System.out.println("Viewing All Active Customers...");
            case "c" -> System.out.println("Registering New Customer...");
            case "d" -> System.out.println("Finding Customer by Name...");
            case "e" -> System.out.println("Finding Total Tickets Purchased by Customer...");
            case "f" -> System.out.println("Deactivating Customer...");
            case "g" -> System.out.println("Deleting Customer...");
            case "m" -> System.out.println("Returning to Main Menu...");
            default -> System.out.println("Invalid Option. Please try again.");

        }
    }

    //display vendor management menu
    private static void displayVendorManagementMenu() {
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
        System.out.println("Please select an option(Please enter M to return to the main menu): ");

        String option = validateOptionAlphabetInput("abcdefgm");

        switch (option) {
            case "a" -> {
                System.out.println("Viewing All Vendors...");
                eventTicketingController.viewAllVendors();
            }
            case "b" -> {
                System.out.println("Viewing All Active Vendors...");
                eventTicketingController.viewAllActiveVendors();
            }
            case "c" -> {
                System.out.println("Registering New Vendor...");
                eventTicketingController.registerNewVendor();
            }
            case "d" -> {
                System.out.println("Finding Vendor by Name...");
                eventTicketingController.findVendorByName();
            }
            case "e" -> {
                System.out.println("Reactivating Vendor...");
                eventTicketingController.reactivateVendor();
            }
            case "f" -> {
                System.out.println("Deactivating Vendor...");
                eventTicketingController.deactivateVendor();
            }
            case "g" -> {
                System.out.println("Deleting Vendor...");
                eventTicketingController.deleteVendor();
            }
            case "m" -> System.out.println("Returning to Main Menu...");
            default -> System.out.println("Invalid Option. Please try again.");

        }
    }

    //validate option number input
    private static int validateOptionNumberInput() {
        while (true) {
            try {
               return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }

    //validate option alphabet input
    private static String validateOptionAlphabetInput(String options) {
        while (true) {
            String option = scanner.next().toLowerCase();
            if (options.contains(option)) {
                return option;
            } else {
                System.out.println("Invalid Option. Please check the menu and try again.");
            }
        }
    }



}