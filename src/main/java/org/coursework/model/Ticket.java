package org.coursework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Ticket {
    @JsonProperty("ticketId")
    private String ticketId;
    @JsonProperty("vendor")
    private Vendor vendor;
    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("purchasedAt")
    private String purchasedAt;

    public Ticket() {
    }

    public Ticket(String ticketId, Vendor vendor, Customer customer, LocalDateTime createdAt, LocalDateTime purchasedAt) {
        this.ticketId = ticketId;
        this.vendor = vendor;
        this.customer = customer;
        this.createdAt = createdAt.toString();
        this.purchasedAt = purchasedAt.toString();
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPurchasedAt() {
        return purchasedAt;
    }

    public void setPurchasedAt(String purchasedAt) {
        this.purchasedAt = purchasedAt;
    }
}
