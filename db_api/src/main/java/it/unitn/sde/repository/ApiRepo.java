package it.unitn.sde.repository;

import it.unitn.sde.model.Result;

import java.util.List;

public interface ApiRepo {
    Boolean addVote(String option);
    Boolean deleteVote(String option);

    Boolean exists(String option);

    List<Result> getVotes();
}
