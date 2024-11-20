package org.coursework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventConfiguration {
    @JsonProperty("eventName")
    private String eventName;

    @JsonProperty("eventDate")
    private String eventDate;

    @JsonProperty("totalTickets")
    private int totalTickets;

    @JsonProperty("maxCapacity")
    private int maxCapacity;

    @JsonProperty("ticketReleaseRate")
    private int ticketReleaseRate;

    @JsonProperty("customerRetrievalRate")
    private int customerRetrievalRate;

    public EventConfiguration() {
    }

    public EventConfiguration(String eventName, String eventDate, int totalTickets, int maxCapacity, int ticketReleaseRate, int customerRetrievalRate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.totalTickets = totalTickets;
        this.maxCapacity = maxCapacity;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public String toString() {
        return "EventConfiguration{" +
                "eventName='" + eventName + '\'' +
                ", eventDate=" + eventDate +
                ", totalTickets=" + totalTickets +
                ", maxCapacity=" + maxCapacity +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                '}';
    }
}
