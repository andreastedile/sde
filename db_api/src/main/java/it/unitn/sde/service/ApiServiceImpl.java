package it.unitn.sde.service;

import it.unitn.sde.model.Result;
import it.unitn.sde.repository.ApiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private ApiRepo apiRepo;

    @Override
    public Boolean addVote(String option) {
        return apiRepo.addVote(option);
    }

    @Override
    public List<Result> getVotes() {
        return apiRepo.getVotes();
    }

    @Override
    public Boolean exists(String option) {
        return apiRepo.exists(option);
    }
}
