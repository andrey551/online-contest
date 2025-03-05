package com.tad.problem.mapper;

import com.tad.problem.dto.request.ProblemRequest;
import com.tad.problem.model.Problem;

public class ProblemMapper {
    public static Problem toProblem(ProblemRequest problemRequest) {
        Problem problem = new Problem();
        problem.setAuthor(problemRequest.author());
        problem.setTitle(problemRequest.title());
        problem.setDescription(problemRequest.description());
        problem.setDifficulty(problemRequest.difficulty());
        problem.setTags(problemRequest.tags());
        return problem;
    }
}
