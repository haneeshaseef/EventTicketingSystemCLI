package org.coursework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CustomerResponse {
    @JsonProperty("count")
    private int count;

    @JsonProperty("customers")
    private Map<String, Customer> customers;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Map<String, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Map<String, Customer> customers) {
        this.customers = customers;
    }
}
