package it.unitn.sde.model;

import java.util.HashMap;

public class Result extends HashMap<String, Integer> {
    public String getOption() {
        return this.entrySet().iterator().next().getKey();
    }

    public Integer getVotes() {
        return this.entrySet().iterator().next().getValue();
    }
}
