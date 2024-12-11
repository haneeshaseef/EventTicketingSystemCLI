package org.coursework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {
    @JsonProperty("participantId")
    private String participantId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("ticketsToPurchase")
    private int ticketsToPurchase;

    @JsonProperty("ticketRetrievalInterval")
    private long ticketRetrievalInterval;

    @JsonProperty("totalTicketsPurchased")
    private int totalTicketsPurchased;

    @JsonProperty("active")
    private boolean active;

    public Customer() {
    }

    public Customer(String name, String email, String password, int ticketsToPurchase, long ticketRetrievalInterval) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.ticketsToPurchase = ticketsToPurchase;
        this.ticketRetrievalInterval = ticketRetrievalInterval;
        this.totalTicketsPurchased = 0;
        this.active = false;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTicketsToPurchase() {
        return ticketsToPurchase;
    }

    public void setTicketsToPurchase(int ticketsToPurchase) {
        this.ticketsToPurchase = ticketsToPurchase;
    }

    public long getTicketRetrievalInterval() {
        return ticketRetrievalInterval;
    }

    public void setTicketRetrievalInterval(long ticketRetrievalInterval) {
        this.ticketRetrievalInterval = ticketRetrievalInterval;
    }

    public int getTotalTicketsPurchased() {
        return totalTicketsPurchased;
    }

    public void setTotalTicketsPurchased(int totalTicketsPurchased) {
        this.totalTicketsPurchased = totalTicketsPurchased;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
