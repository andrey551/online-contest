package com.tad.contest.service;

import com.tad.contest.model.Contest;
import com.tad.contest.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContestService {
    @Autowired
    private ContestRepository contestRepository;

    public List<Contest> getContests() {
        return contestRepository.findAll();
    }

    public Contest getContestById(UUID id) {
        return contestRepository.findById(id).get();
    }

    public Contest saveContest(Contest contest) {
        return contestRepository.save(contest);
    }

    public void deleteContest(Contest contest) {
        contestRepository.delete(contest);
    }
}
