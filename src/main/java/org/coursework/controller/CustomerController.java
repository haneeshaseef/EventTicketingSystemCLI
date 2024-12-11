package org.coursework.controller;

import org.coursework.model.Customer;
import org.coursework.model.Ticket;
import org.coursework.service.CustomerService;
import org.coursework.utils.InputValidator;

import java.util.List;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //view all customers
    public void viewAllCustomers() {
        try {
            List<Customer> customers = customerService.viewAllCustomers();
            System.out.println("*** All Customers ***" + "\n");
            customers.forEach(customer -> System.out.printf("Customer ID: %s, Name: %s%n", customer.getParticipantId(), customer.getName()));
            System.out.println("All customers viewed successfully");
            System.out.println("-".repeat(50));
        } catch (Exception e) {
            System.out.println("Failed to get all customers: " + e.getMessage());
        }
    }

    //view all active customers
    public void viewAllActiveCustomers() {
        try {
            List<Customer> activeCustomers = customerService.viewActiveCustomers();
            System.out.println("*** Active Customers ***" + "\n");
            activeCustomers.forEach(customer -> System.out.printf("Customer ID: %s, Name: %s%n", customer.getParticipantId(), customer.getName()));
            System.out.println("All active customers viewed successfully");
            System.out.println("-".repeat(50));
        } catch (Exception e) {
            System.out.println("Failed to get all active customers: " + e.getMessage());
        }
    }

    //register a new customer
    public void registerNewCustomer() {
        try {
            String name = InputValidator.validateTextField("Customer Name");
            System.out.println("Enter customer email: ");
            String email = InputValidator.validateEmail();
            System.out.println("Enter customer password: ");
            String password = InputValidator.validatePassword();
            System.out.println("Enter the tickets to purchase: ");
            int ticketToPurchase = InputValidator.validateNumberField("Tickets to Purchase");
            System.out.println("Enter the ticket Retrieval Interval(in seconds): ");
            int totalTicketsPerRetrieval = InputValidator.validateNumberField("Ticket Retrieval Interval");
            Customer customer = new Customer(name, email, password,ticketToPurchase, totalTicketsPerRetrieval);
            customerService.registerNewCustomer(customer);
            System.out.println("New customer registered successfully");
            System.out.println("*** Customer Details ***" + "\n");
            System.out.printf("""
                    Customer Name: %s
                    Customer Email: %s
                    Tickets to Purchase: %d
                    Ticket Retrieval Interval: %d
                    %n""", customer.getName(), customer.getEmail(), customer.getTicketsToPurchase(), customer.getTicketRetrievalInterval());
            System.out.println("-".repeat(50));
        } catch (Exception e) {
            System.out.println("Failed to register new customer: " + e.getMessage());
        }
    }

    //Update a customer
    public void updateCustomer() {
        try {
            System.out.println("Enter customer name to update: ");
            String name = InputValidator.validateTextField("Customer Name");
            Customer customer = customerService.findCustomerByName(name);
            if (customer == null) {
                System.out.println("No customer found with the name: " + name);
                return;
            }
            System.out.println("What would you like to update?");
            System.out.println("""
                    1. Name
                    2. Email
                    3. Tickets to Purchase
                    4. Ticket Retrieval Interval
                    """);
            int choice = InputValidator.validateOptionNumberInput();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter new name: ");
                    String newName = InputValidator.validateTextField("Customer Name");
                    customer.setName(newName);
                }
                case 2 -> {
                    System.out.println("Enter new email: ");
                    String newEmail = InputValidator.validateEmail();
                    customer.setEmail(newEmail);
                }
                case 3 -> {
                    System.out.println("Enter new tickets to purchase: ");
                    int newTicketsToPurchase = InputValidator.validateNumberField("Tickets to Purchase");
                    customer.setTicketsToPurchase(newTicketsToPurchase);
                }
                case 4 -> {
                    System.out.println("Enter new ticket retrieval interval: ");
                    int newTicketRetrievalInterval = InputValidator.validateNumberField("Ticket Retrieval Interval");
                    customer.setTicketRetrievalInterval(newTicketRetrievalInterval);
                }
                default -> System.out.println("Invalid choice");
            }
            customerService.updateCustomer(customer);
            System.out.println("Customer updated successfully");
            System.out.println("*** Updated Customer Details ***" + "\n");
            System.out.printf("""
                    Customer ID: %s
                    Customer Name: %s
                    Customer Email: %s
                    Tickets to Purchase: %d
                    Ticket Retrieval Interval: %d
                    %n""", customer.getParticipantId(), customer.getName(), customer.getEmail(), customer.getTicketsToPurchase(), customer.getTicketRetrievalInterval());
            System.out.println("-".repeat(50));
        } catch (Exception e) {
            System.out.println("Failed to update customer: " + e.getMessage());
        }
    }

    //deactivate a customer
    public void deactivateCustomer() {
        try {
            String name = InputValidator.validateTextField("Customer Name");
            Customer customer = customerService.findCustomerByName(name);
            customerService.deactivateCustomer(name);
            System.out.println("Customer deactivated successfully");
            System.out.println("*** Customer Deactivated ***" + "\n");
            System.out.println("*** Customer Details ***" + "\n");
            System.out.printf("""
                    Customer ID: %s
                    Customer Name: %s
                    Customer Email: %s
                    Tickets to Purchase: %d
                    Ticket Retrieval Interval: %d
                    Customer Status: %s
                    %n""", customer.getParticipantId(), customer.getName(), customer.getEmail(), customer.getTicketsToPurchase(), customer.getTicketRetrievalInterval(), customer.isActive() ? "Active" : "Inactive");
            System.out.println("-".repeat(50));
        } catch (Exception e) {
            System.out.println("Failed to deactivate customer: " + e.getMessage());
        }
    }

    //reactivate a customer
    public void reactivateCustomer() {
        try {
            String name = InputValidator.validateTextField("Customer Name");
            Customer customer = customerService.findCustomerByName(name);
            customerService.reactivateCustomer(name);
            System.out.println("Customer reactivated successfully");
            System.out.println("*** Customer Reactivated ***" + "\n");
            System.out.println("*** Customer Details ***" + "\n");
            System.out.printf("""
                    Customer ID: %s
                    Customer Name: %s
                    Customer Email: %s
                    Tickets to Purchase: %d
                    Ticket Retrieval Interval: %d
                    Customer Status: %s
                    %n""", customer.getParticipantId(), customer.getName(), customer.getEmail(), customer.getTicketsToPurchase(), customer.getTicketRetrievalInterval(), customer.isActive() ? "Active" : "Inactive");
            System.out.println("-".repeat(50));
        } catch (Exception e) {
            System.out.println("Failed to reactivate customer: " + e.getMessage());
        }
    }

    //find a customer by name
    public void findCustomerByName() {
        try {
            String name = InputValidator.validateTextField("Customer Name");
            Customer customer = customerService.findCustomerByName(name);
            if (customer == null) {
                System.out.println("No customer found with the name: " + name);
                return;
            }
            System.out.println("Customer found successfully");
            System.out.println("*** Customer Details ***" + "\n");
            System.out.printf("""
                    Customer ID: %s
                    Customer Name: %s
                    Customer Email: %s
                    Tickets to Purchase: %d
                    Ticket Retrieval Interval: %d
                    Customer Status: %s
                    %n""", customer.getParticipantId(), customer.getName(), customer.getEmail(), customer.getTicketsToPurchase(), customer.getTicketRetrievalInterval(), customer.isActive() ? "Active" : "Inactive");
            System.out.println("-".repeat(50));
        } catch (Exception e) {
            System.out.println("Failed to find customer: " + e.getMessage());
        }
    }

    //delete a customer
    public void deleteCustomer() {
        try {
            String customerName = InputValidator.validateTextField("Customer Name");
           customerService.deleteCustomer(customerName);
            System.out.printf("Customer %s deleted successfully%n", customerName);
            System.out.println("-".repeat(50));
        } catch (Exception e) {
            System.out.println("Failed to delete customer: " + e.getMessage());
        }
    }
}
