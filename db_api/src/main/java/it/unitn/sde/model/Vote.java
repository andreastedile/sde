package it.unitn.sde.model;

import javax.validation.constraints.NotNull;

public class Vote {
    @NotNull
    private String option;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
