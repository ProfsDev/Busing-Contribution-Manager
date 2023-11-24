package com.prof.bcm.contribution;

public class Contributor {
    private String name;
    private Double amountContributed;

    public Contributor() {
    }

    public Contributor(String name, Double amountContributed) {
        this.name = name;
        this.amountContributed = amountContributed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmountContributed() {
        return amountContributed;
    }

    public void setAmountContributed(Double amountContributed) {
        this.amountContributed = amountContributed;
    }
}
