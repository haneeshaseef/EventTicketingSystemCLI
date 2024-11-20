package org.coursework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vendor {
    @JsonProperty("participantId")
    private String participantId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("ticketsPerRelease")  // Changed from ticketPerRelease to match API
    private int ticketsPerRelease;      // Changed variable name to match as well

    @JsonProperty("ticketReleaseInterval")
    private long ticketReleaseInterval;

    @JsonProperty("ticketsToSell")
    private int ticketsToSell;

    @JsonProperty("ticketsReleased")
    private int ticketsReleased;

    @JsonProperty("totalTicketsSold")
    private int totalTicketsSold;

    @JsonProperty("active")
    private boolean active;

    public Vendor() {
    }

    public Vendor(String vendorName, String vendorEmail, int ticketsPerRelease, int ticketReleaseInterval, int ticketsToSell) {
        this.name = vendorName;
        this.email = vendorEmail;
        this.ticketsPerRelease = ticketsPerRelease;
        this.ticketReleaseInterval = ticketReleaseInterval;
        this.ticketsToSell = ticketsToSell;
        this.totalTicketsSold = 0;
        this.ticketsReleased = 0;
        this.active = false;
    }

    public String getParticipantId() {
        return participantId;
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

    public int getTicketsPerRelease() {  // Changed from getTicketPerRelease
        return ticketsPerRelease;
    }

    public void setTicketsPerRelease(int ticketsPerRelease) {  // Changed from setTicketPerRelease
        this.ticketsPerRelease = ticketsPerRelease;
    }

    public long getTicketReleaseInterval() {
        return ticketReleaseInterval;
    }

    public void setTicketReleaseInterval(long ticketReleaseInterval) {
        this.ticketReleaseInterval = ticketReleaseInterval;
    }

    public int getTicketsToSell() {
        return ticketsToSell;
    }

    public void setTicketsToSell(int ticketsToSell) {
        this.ticketsToSell = ticketsToSell;
    }

    public int getTicketsReleased() {
        return ticketsReleased;
    }

    public void setTicketsReleased(int ticketsReleased) {
        this.ticketsReleased = ticketsReleased;
    }

    public int getTotalTicketsSold() {
        return totalTicketsSold;
    }

    public void setTotalTicketsSold(int totalTicketsSold) {
        this.totalTicketsSold = totalTicketsSold;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}