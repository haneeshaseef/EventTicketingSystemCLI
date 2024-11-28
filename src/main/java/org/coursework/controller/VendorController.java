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
            System.out.println("*** All Vendors ***" +"\n");
            vendors.forEach(vendor -> System.out.printf("Vendor ID: %s, Name: %s%n",
                    vendor.getParticipantId(), vendor.getName()));
        } catch (Exception e) {
            System.out.println("Failed to get all vendors: " + e.getMessage());
        }
    }
    public void viewAllActiveVendors() {
        try {
            List<Vendor> activeVendors = vendorService.getActiveVendors();
            System.out.println("*** Active Vendors ***" +"\n");
            activeVendors.forEach(vendor -> System.out.printf("Vendor ID: %s, Name: %s%n",
                    vendor.getParticipantId(), vendor.getName()));
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
            System.out.println("*** Vendor Found ***" +"\n");
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
            System.out.println("*** Vendor Registered ***" +"\n");
            System.out.println("*** Vendor Details ***" +"\n");
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



    //deactivate vendor
    public void deactivateVendor() {
        try {
            System.out.println("Enter vendor name: ");
            String vendorName = InputValidator.validateTextField("Vendor Name");
            Vendor vendor = vendorService.findVendorByName(vendorName);
            vendorService.deactivateVendor(vendorName);
            System.out.println("Vendor deactivated successfully");
            System.out.println("*** Vendor Deactivated ***" +"\n");
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
            System.out.println("*** Vendor Reactivated ***" +"\n");
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
