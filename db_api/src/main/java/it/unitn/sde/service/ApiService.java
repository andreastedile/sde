package it.unitn.sde.service;

import it.unitn.sde.model.Result;

import java.util.List;

public interface ApiService {
    Boolean addVote(String option);

    Boolean exists(String option);

    List<Result> getVotes();
}
