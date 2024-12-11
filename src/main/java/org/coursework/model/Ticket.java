package org.coursework.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import java.time.LocalDateTime;
import java.util.List;

public class Ticket {
    @JsonProperty("ticketId")
    private String ticketId;

    @JsonProperty("vendor")
    private Vendor vendor;

    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("createdAt")
    @JsonDeserialize(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @JsonProperty("purchasedAt")
    @JsonDeserialize(converter = LocalDateTimeConverter.class)
    private LocalDateTime purchasedAt;

    //Ref: https://www.baeldung.com/jackson-custom-serialization
    // Custom deserializer for LocalDateTime
    public static class LocalDateTimeConverter extends StdConverter<List<Integer>, LocalDateTime> {
        @Override
        public LocalDateTime convert(List<Integer> value) {
            if (value == null || value.size() < 6) {
                return null;
            }
            return LocalDateTime.of(
                    value.get(0),   // year
                    value.get(1),   // month
                    value.get(2),   // day
                    value.get(3),   // hour
                    value.get(4),   // minute
                    value.get(5),   // second
                    value.size() > 6 ? value.get(6) : 0  // nanosecond
            );
        }
    }

    public Ticket() {
    }

    public Ticket(String ticketId, Vendor vendor, Customer customer, LocalDateTime createdAt, LocalDateTime purchasedAt) {
        this.ticketId = ticketId;
        this.vendor = vendor;
        this.customer = customer;
        this.createdAt = createdAt;
        this.purchasedAt = purchasedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getPurchasedAt() {
        return purchasedAt;
    }

    public void setPurchasedAt(LocalDateTime purchasedAt) {
        this.purchasedAt = purchasedAt;
    }
}

