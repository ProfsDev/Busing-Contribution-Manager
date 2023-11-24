package com.prof.bcm.edit_contribution;

import com.prof.bcm.contribution.Contributor;

public interface OnUpdateContributionListener {
    void onUpdate(EditContributionModel editContributionModel, double newAmount);
}
