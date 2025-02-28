package com.tad.problem.controller;

import com.tad.problem.dto.ListProblemName;
import com.tad.problem.dto.ProblemRequest;
import com.tad.problem.dto.ProblemResponse;
import com.tad.problem.dto.ProblemsResponse;
import com.tad.problem.model.Problem;
import com.tad.problem.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    @GetMapping("/names")
    public ResponseEntity<ListProblemName> getAllProblems() {
        ListProblemName names = problemService.getListProblemName();

        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Problem>> getProblem() {
        List<Problem> problems = problemService.getAllProblems();
        return new ResponseEntity<>(problems, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Problem> createProblem(@RequestBody ProblemRequest problem) {
        ProblemResponse response = problemService.addProblem(problem);
        return new ResponseEntity<>(
                response.problem(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @PutMapping("/")
    public ResponseEntity<Problem> updateProblem(@RequestBody ProblemRequest problem) {
        ProblemResponse response = problemService.updateProblem(problem);
        return new ResponseEntity<>(
                response.problem(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Problem> deleteProblem(@PathVariable UUID Id) {
        ProblemResponse response = problemService.deleteProblemById(Id);
        return new ResponseEntity<>(
                response.problem(),
                HttpStatus.valueOf(response.status().getCode()));
    }
}
