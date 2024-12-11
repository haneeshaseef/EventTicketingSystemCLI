package org.coursework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.coursework.model.Ticket;

import java.util.List;

public class TicketsResponse {
    private List<Ticket> tickets;

    @JsonCreator
    public TicketsResponse(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}