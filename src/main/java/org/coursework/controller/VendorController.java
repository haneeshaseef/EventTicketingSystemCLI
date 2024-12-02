package org.coursework.controller;

import org.coursework.model.Vendor;
import org.coursework.service.VendorService;
import org.coursework.utils.InputValidator;

import java.util.List;

public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    public void viewAllVendors() {
        try {
            List<Vendor> vendors = vendorService.getAllVendors();
            System.out.println("*** All Vendors ***" + "\n");
            vendors.forEach(vendor -> System.out.printf("Vendor ID: %s, Name: %s%n", vendor.getParticipantId(), vendor.getName()));
        } catch (Exception e) {
            System.out.println("Failed to get all vendors: " + e.getMessage());
        }
    }

    public void viewAllActiveVendors() {
        try {
            List<Vendor> activeVendors = vendorService.getActiveVendors();
            System.out.println("*** Active Vendors ***" + "\n");
            activeVendors.forEach(vendor -> System.out.printf("Vendor ID: %s, Name: %s%n", vendor.getParticipantId(), vendor.getName()));
        } catch (Exception e) {
            System.out.println("Failed to get all active vendors: " + e.getMessage());
        }
    }

    //find vendor by name
    public void findVendorByName() {
        try {
            System.out.println("Enter vendor name: ");
            String vendorName = InputValidator.validateTextField("Vendor Name");
            Vendor vendor = vendorService.findVendorByName(vendorName);
            System.out.println("*** Vendor Found ***" + "\n");
            System.out.printf("""
                    Vendor ID: %s
                    Vendor Name: %s
                    Vendor Email: %s
                    Tickets Per Release: %d
                    Ticket Release Interval: %d
                    Total Tickets to Sell: %d
                    Vendor Status: %s
                    %n""", vendor.getParticipantId(), vendor.getName(), vendor.getEmail(), vendor.getTicketsPerRelease(), vendor.getTicketReleaseInterval(), vendor.getTicketsToSell(), vendor.isActive() ? "Active" : "Inactive");
        } catch (Exception e) {
            System.out.println("Failed to find vendor by name: " + e.getMessage());
        }
    }

    public void registerNewVendor() {
        try {
            String vendorName = InputValidator.validateTextField("Vendor Name");
            System.out.println("Enter vendor email: ");
            String vendorEmail = InputValidator.validateEmail();
            System.out.println("Enter tickets per release: ");
            int ticketsPerRelease = InputValidator.validateNumberField("Tickets Per Release");

            System.out.println("Enter ticket release interval (seconds): ");
            int ticketReleaseInterval = InputValidator.validateNumberField("Ticket Release Interval");

            System.out.println("Enter total tickets to sell: ");
            int ticketsToSell = InputValidator.validateNumberField("Total Tickets to Sell");

            Vendor vendor = new Vendor(vendorName, vendorEmail, ticketsPerRelease, ticketReleaseInterval, ticketsToSell);

            System.out.println("Registering new vendor...");
            vendorService.registerNewVendor(vendor);
            System.out.println("Vendor registered successfully");
            System.out.println("*** Vendor Registered ***" + "\n");
            System.out.println("*** Vendor Details ***" + "\n");
            System.out.printf("""
                    Vendor ID: %s
                    Vendor Name: %s
                    Vendor Email: %s
                    Tickets Per Release: %d
                    Ticket Release Interval: %d
                    Total Tickets to Sell: %d
                    Vendor Status: %s
                    %n""", vendor.getParticipantId(), vendor.getName(), vendor.getEmail(), vendor.getTicketsPerRelease(), vendor.getTicketReleaseInterval(), vendor.getTicketsToSell(), vendor.isActive() ? "Active" : "Inactive");
        } catch (Exception e) {
            System.out.println("Failed to register new vendor: " + e.getMessage());
        }
    }

    //update vendor
    public void updateVendor() {
        try {
            System.out.println("Enter vendor name to update: ");
            String name = InputValidator.validateTextField("Vendor Name");
            Vendor vendor = vendorService.findVendorByName(name);

            if (vendor == null) {
                System.out.println("Vendor not found: " + name);
                return;
            }

            System.out.println("What would you like to update?");
            System.out.println("""
                    1. Name
                    2. Email
                    3. Tickets to Release
                    4. Ticket Release Interval
                    """);
            int choice = InputValidator.validateOptionNumberInput();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter new name: ");
                    String newName = InputValidator.validateTextField("Vendor Name");
                    vendor.setName(newName);
                }
                case 2 -> {
                    System.out.println("Enter new email: ");
                    String newEmail = InputValidator.validateEmail();
                    vendor.setEmail(newEmail);
                }
                case 3 -> {
                    System.out.println("Enter new tickets to release: ");
                    int newTicketsToRelease = InputValidator.validateNumberField("Tickets to Release");
                    vendor.setTicketsReleased(newTicketsToRelease);
                }
                case 4 -> {
                    System.out.println("Enter new ticket release interval: ");
                    int newTicketReleaseInterval = InputValidator.validateNumberField("Ticket Release Interval");
                    vendor.setTicketReleaseInterval(newTicketReleaseInterval);
                }
                default -> {
                    System.out.println("Invalid choice");
                    return;
                }
            }

            vendorService.updateVendor(vendor);
            System.out.println("Vendor updated successfully");
            System.out.println("*** Updated Vendor Details ***\n");
            System.out.printf("""
                    Vendor ID: %s
                    Vendor Name: %s
                    Vendor Email: %s
                    Tickets to Release: %d
                    Ticket Release Interval: %d
                    %n""", vendor.getParticipantId(), vendor.getName(), vendor.getEmail(), vendor.getTicketsReleased(), vendor.getTicketReleaseInterval());
        } catch (Exception e) {
            System.out.println("Failed to update vendor: " + e.getMessage());
        }
    }

    //deactivate vendor
    public void deactivateVendor() {
        try {
            System.out.println("Enter vendor name: ");
            String vendorName = InputValidator.validateTextField("Vendor Name");
            Vendor vendor = vendorService.findVendorByName(vendorName);
            vendorService.deactivateVendor(vendorName);
            System.out.println("Vendor deactivated successfully");
            System.out.println("*** Vendor Deactivated ***" + "\n");
            System.out.printf("""
                    Vendor ID: %s
                    Vendor Name: %s
                    Vendor Email: %s
                    Tickets Per Release: %d
                    Ticket Release Interval: %d
                    Total Tickets to Sell: %d
                    %n""", vendor.getParticipantId(), vendor.getName(), vendor.getEmail(), vendor.getTicketsPerRelease(), vendor.getTicketReleaseInterval(), vendor.getTicketsToSell());
        } catch (Exception e) {
            System.out.println("Failed to deactivate vendor: " + e.getMessage());
        }
    }

    //reactivate vendor
    public void reactivateVendor() {
        try {
            System.out.println("Enter vendor name: ");
            String vendorName = InputValidator.validateTextField("Vendor Name");
            Vendor vendor = vendorService.findVendorByName(vendorName);
            vendorService.reactivateVendor(vendorName);
            System.out.println("Vendor reactivated successfully");
            System.out.println("*** Vendor Reactivated ***" + "\n");
            System.out.printf("""
                    Vendor ID: %s
                    Vendor Name: %s
                    Vendor Email: %s
                    Tickets Per Release: %d
                    Ticket Release Interval: %d
                    Total Tickets to Sell: %d
                    %n""", vendor.getParticipantId(), vendor.getName(), vendor.getEmail(), vendor.getTicketsPerRelease(), vendor.getTicketReleaseInterval(), vendor.getTicketsToSell());
        } catch (Exception e) {
            System.out.println("Failed to reactivate vendor: " + e.getMessage());
        }
    }

    //delete vendor
    public void deleteVendor() {
        try {
            System.out.println("Enter vendor name: ");
            String vendorName = InputValidator.validateTextField("Vendor Name");
            vendorService.deleteVendor(vendorName);
            System.out.println("Vendor deleted successfully");
        } catch (Exception e) {
            System.out.println("Failed to delete vendor: " + e.getMessage());
        }
    }
}
