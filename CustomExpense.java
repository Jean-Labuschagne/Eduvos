package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;

public class CustomExpense {
    private int id;
    private int tripId;
    private String description;
    private double cost;

    public CustomExpense() {}

    public CustomExpense(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTripId() { return tripId; }
    public void setTripId(int tripId) { this.tripId = tripId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
}
