package org.coursework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TicketsResponse {
    @JsonProperty("tickets")
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
