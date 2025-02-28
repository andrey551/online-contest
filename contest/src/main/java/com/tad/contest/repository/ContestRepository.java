package com.tad.contest.repository;

import com.tad.contest.model.Contest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContestRepository extends MongoRepository<Contest, UUID> {
}
