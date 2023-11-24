package com.prof.bcm.edit_contribution;

public class EditContributionModel {
    private String id;
    private String name;
    private double amountContributed;

    public EditContributionModel() {
    }

    public EditContributionModel(String id, String name, double amountContributed) {
        this.id = id;
        this.name = name;
        this.amountContributed = amountContributed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmountContributed() {
        return amountContributed;
    }

    public void setAmountContributed(double amountContributed) {
        this.amountContributed = amountContributed;
    }

    @Override
    public String toString() {
        return "EditContributionModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", amountContributed=" + amountContributed +
                '}';
    }
}
