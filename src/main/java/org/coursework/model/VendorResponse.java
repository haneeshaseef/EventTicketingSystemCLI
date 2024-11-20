package org.coursework.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class VendorResponse {
    @JsonProperty("count")
    private int count;

    @JsonProperty("vendors")
    private Map<String, Vendor> vendors;

    // Getters and setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Map<String, Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(Map<String, Vendor> vendors) {
        this.vendors = vendors;
    }
}

