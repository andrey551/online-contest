package com.tad.contest.repository;

import com.tad.contest.model.Contest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContestRepository extends MongoRepository<Contest, UUID> {

    Optional<Contest> findContestByUserIdAndStartTime(UUID userId, Timestamp startTime);

    void deleteContestByUserIdAndStartTime(UUID userId, Timestamp startTime);

    @Query(value = "{}", fields = "{ 'title' : 1, '_id' : 0 }")
    List<String> findAllNames();
}
