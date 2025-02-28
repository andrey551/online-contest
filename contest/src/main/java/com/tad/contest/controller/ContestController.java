package com.tad.contest.controller;

import com.tad.contest.model.Contest;
import com.tad.contest.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/problem")
public class ContestController {

    @Autowired
    private ContestService contestService;

    public List<Contest> getContests() {
        return contestService.getContests();
    }

    public Contest getContestById(UUID id) {
        return contestService.getContestById(id);
    }

    public Contest createContest(Contest contest) {
        return contestService.saveContest(contest);
    }

    public Contest updateContest(Contest contest) {
        return contestService.saveContest(contest);
    }

//    public void deleteContest(UUID id) {
//        contestService.deleteContest();
//    }
}
