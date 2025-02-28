package com.tad.contest.controller;

import com.tad.contest.dto.object.ChangeStatusRequestDTO;
import com.tad.contest.dto.object.ContestRequestDTO;
import com.tad.contest.dto.object.ContestResponseDTO;
import com.tad.contest.dto.wrapper.WrapperContestResponse;
import com.tad.contest.dto.wrapper.WrapperContestsName;
import com.tad.contest.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/problem")
public class ContestController {

    @Autowired
    private ContestService contestService;

    @GetMapping("names")
    public ResponseEntity<List<String>> getContestsName() {
        WrapperContestsName response = contestService.getContestsName();

        return new ResponseEntity<List<String>>(
                response.contestsName(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContestResponseDTO> getContestById(@PathVariable UUID id) {
        WrapperContestResponse response = contestService.getContestById(id);

        return new ResponseEntity<ContestResponseDTO>(
                response.response(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @PostMapping("/")
    public ResponseEntity<ContestResponseDTO> createContest(@RequestBody ContestRequestDTO contest) {
        WrapperContestResponse response = contestService.addContest(contest);

        return new ResponseEntity<ContestResponseDTO>(
                response.response(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @PutMapping("/")
    public ResponseEntity<ContestResponseDTO> updateContest(@RequestBody ContestRequestDTO contest) {
        WrapperContestResponse response = contestService.updateContest(contest);

        return new ResponseEntity<ContestResponseDTO>(
                response.response(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContestResponseDTO> deleteContest(@PathVariable UUID id) {
        WrapperContestResponse response = contestService.deleteContest(id);

        return new ResponseEntity<ContestResponseDTO>(
                response.response(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @PutMapping("/{status}")
    public ResponseEntity updateStatus(@RequestBody ChangeStatusRequestDTO dto) {
        WrapperContestResponse response = contestService.updateStatus(dto.contestId(), dto.status());

        return new ResponseEntity<>(HttpStatus.valueOf(response.status().getCode()));
    }
}
