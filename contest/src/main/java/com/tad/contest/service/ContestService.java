package com.tad.contest.service;

import com.tad.contest.dto.object.ContestRequestDTO;
import com.tad.contest.dto.wrapper.WrapperContestResponse;
import com.tad.contest.dto.wrapper.WrapperContestsName;
import com.tad.contest.mapper.ContestMapper;
import com.tad.contest.model.Contest;
import com.tad.contest.model.enums.ContestStatus;
import com.tad.contest.model.enums.TransactionStatus;
import com.tad.contest.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ContestService {
    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public WrapperContestsName getContestsName() {
        try {
            List<String> contestName = contestRepository.findAllNames();

            return new WrapperContestsName(TransactionStatus.SUCCESS, contestName);
        } catch (Exception e) {
            return new WrapperContestsName(TransactionStatus.INTERNAL_ERROR, null);
        }
    }

    public WrapperContestResponse getContestById(UUID id) {
        try {
            Contest contest = contestRepository.findById(id).get();

            return new WrapperContestResponse(
                    TransactionStatus.SUCCESS,
                    ContestMapper.toContestResponseDTO(contest));
        } catch (Exception e) {
            return new WrapperContestResponse(TransactionStatus.INTERNAL_ERROR, null);
        }
    }

    public WrapperContestResponse addContest(ContestRequestDTO contest) {
        try {
            Contest contestToAdd = ContestMapper.toContest(contest);

            contestToAdd.setCreatedAt(Timestamp.valueOf(String.valueOf(Instant.now())));
            contestToAdd.setStatus(ContestStatus.ON_REVIEW);

            Contest contestSaved = contestRepository.save(contestToAdd);

            return new WrapperContestResponse(
                    TransactionStatus.SUCCESS,
                    ContestMapper.toContestResponseDTO(contestSaved));
        } catch (Exception e) {
            return new WrapperContestResponse(TransactionStatus.INTERNAL_ERROR, null);
        }
    }

    public WrapperContestResponse updateContest(ContestRequestDTO contest) {
        try {
            Contest contestToUpdate = contestRepository
                    .findContestByUserIdAndStartTime(
                        contest.userId(),
                        contest.startTime())
                    .orElse(null);

            if(contestToUpdate == null) {
                return new WrapperContestResponse(TransactionStatus.NOT_FOUND, null);
            }

            if(contestToUpdate.getStatus() == ContestStatus.FINISHED) {
                return new WrapperContestResponse(TransactionStatus.BAD_REQUEST, null);
            }

            contestToUpdate.setStatus(ContestStatus.ON_REVIEW);
            contestToUpdate.setProblems(contest.problem());
            contestToUpdate.setStartTime(contest.startTime());
            contestToUpdate.setEndTime(contest.endTime());

            Contest contestSaved = contestRepository.save(contestToUpdate);

            return new WrapperContestResponse(
                    TransactionStatus.SUCCESS,
                    ContestMapper.toContestResponseDTO(contestSaved)
            );
        } catch (Exception e) {
            return new WrapperContestResponse(TransactionStatus.INTERNAL_ERROR, null);
        }
    }

    public WrapperContestResponse deleteContest(UUID id) {
        try {
            contestRepository.deleteById(id);

            return new WrapperContestResponse(TransactionStatus.SUCCESS, null);
        } catch (Exception e) {
            return new WrapperContestResponse(TransactionStatus.INTERNAL_ERROR, null);
        }
    }

    public WrapperContestResponse updateStatus(UUID contestId, ContestStatus status) {
        try {
            Query query = new Query(Criteria.where("id").is(contestId));

            Update update = new Update().set("status", status);

            mongoTemplate.updateFirst(query, update, Contest.class);

            return new WrapperContestResponse(TransactionStatus.SUCCESS, null);
        } catch (Exception e) {
            return new WrapperContestResponse(TransactionStatus.INTERNAL_ERROR, null);
        }
    }


}
