package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;

public class PredefinedActivity {
    private String name;
    private double cost;
    private boolean isSelected;

    public PredefinedActivity(String name, double cost) {
        this.name = name;
        this.cost = cost;
        this.isSelected = false;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
