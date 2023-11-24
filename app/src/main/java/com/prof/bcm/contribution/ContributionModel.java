package com.prof.bcm.contribution;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

public class ContributionModel {
    private String month;
    private List<Contributor> contributorList;
    private double total;

    public ContributionModel() {
    }

    public ContributionModel(String month, List<Contributor> contributorList) {
        this.month = month;
        this.contributorList = contributorList;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<Contributor> getContributorList() {
        return contributorList;
    }

    public void setContributorList(List<Contributor> contributorList) {
        this.contributorList = contributorList;
    }

    public double getTotal() {
        total = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            contributorList.forEach(contributor -> {
                total += contributor.getAmountContributed();
            });
        }
        return total;
    }

}
