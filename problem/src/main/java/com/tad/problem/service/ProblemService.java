package com.tad.problem.service;

import com.tad.problem.dto.object.ListProblemName;
import com.tad.problem.dto.request.ListProblemsRequest;
import com.tad.problem.dto.request.ProblemRequest;
import com.tad.problem.dto.response.ProblemResponse;
import com.tad.problem.dto.response.ProblemsResponse;
import com.tad.problem.mapper.ProblemMapper;
import com.tad.problem.model.Problem;
import com.tad.problem.model.enums.TransactionStatus;
import com.tad.problem.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.tad.problem.ProblemApplication.logger;

@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    @CacheEvict(value = "Problem", key = "#id")
    public ProblemResponse getProblemById(UUID id) {
        try {
            Problem problem = problemRepository.findById(id).orElse(null);

            return new ProblemResponse(problem, TransactionStatus.SUCCESS);
        } catch (Exception e) {
            return new ProblemResponse(null, TransactionStatus.INTERNAL_ERROR);
        }
    }

    @CacheEvict(value = "List<Problem>", key = "#ids.problemIDList()")
    public ProblemsResponse getProblemByListId(ListProblemsRequest ids) {
        try {
            Query query = new Query(Criteria.where("_id").in(ids.problemIDList()));

            List<Problem> problems = mongoTemplate.find(query, Problem.class);

            return new ProblemsResponse(TransactionStatus.SUCCESS, problems);
        } catch (Exception e ) {
            return new ProblemsResponse(TransactionStatus.INTERNAL_ERROR, null);
        }
    }

    public ListProblemName getListProblemName() {
        try {
            List<String> nameList = problemRepository.findAllNames();

            return new ListProblemName(nameList);
        } catch (Exception e) {
            return null;
        }
    }


    public ProblemResponse addProblem(ProblemRequest problem) {
        try{
            Problem problemToAdd = ProblemMapper.toProblem(problem);

            problemToAdd.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            problemToAdd.setId(UUID.randomUUID());

            Problem savedProblem = problemRepository.save(problemToAdd);

            return new ProblemResponse(savedProblem, TransactionStatus.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage());

            return new ProblemResponse(null, TransactionStatus.INTERNAL_ERROR);
        }
    }

    public ProblemResponse deleteProblemById(UUID id) {
        try {
            problemRepository.deleteById(id);

            return new ProblemResponse(null, TransactionStatus.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage());

            return new ProblemResponse(null, TransactionStatus.INTERNAL_ERROR);
        }
    }

    public ProblemResponse updateProblem(ProblemRequest problem) {
        try {
            Problem problemToUpdate = problemRepository.findProblemByAuthorAndTitle(problem.author(), problem.title());

            if (problemToUpdate == null) {
                return new ProblemResponse(null, TransactionStatus.NOT_FOUND);
            }

            problemToUpdate.setDifficulty(problem.difficulty());
            problemToUpdate.setDescription(problem.description());
            problemToUpdate.setTags(problem.tags());

            Problem savedProblem = problemRepository.save(problemToUpdate);

            return new ProblemResponse(savedProblem, TransactionStatus.SUCCESS);
        } catch (Exception e) {
            return new ProblemResponse(null, TransactionStatus.INTERNAL_ERROR);
        }
    }
}
