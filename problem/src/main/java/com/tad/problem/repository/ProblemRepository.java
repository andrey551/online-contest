package com.tad.problem.repository;

import com.tad.problem.model.Problem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProblemRepository extends MongoRepository<Problem, UUID> {

    List<Problem> findByTags(String tag);

    Problem findProblemByAuthorAndTitle(String author, String title);

    @Query(value = "{}", fields = "{ 'title' : 1, '_id' : 0 }")
    List<String> findAllNames();
}
