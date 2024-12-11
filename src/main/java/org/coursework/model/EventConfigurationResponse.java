package org.coursework.model;

public class EventConfigurationResponse {
    private boolean configured;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int availableTickets;
    private String eventName;
    private int maxCapacity;

    public boolean isConfigured() {
        return configured;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public String getEventName() {
        return eventName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setConfigured(boolean configured) {
        this.configured = configured;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
