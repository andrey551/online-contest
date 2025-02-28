package com.tad.contest.mapper;

import com.tad.contest.dto.object.ContestRequestDTO;
import com.tad.contest.dto.object.ContestResponseDTO;
import com.tad.contest.model.Contest;

public class ContestMapper {
    public static Contest toContest(ContestRequestDTO contestRequestDTO) {
        Contest contest = new Contest();

        contest.setProblems(contestRequestDTO.problem());
        contest.setTitle(contestRequestDTO.title());
        contest.setStartTime(contestRequestDTO.startTime());
        contest.setEndTime(contestRequestDTO.endTime());

        return contest;
    }

    public static ContestResponseDTO toContestResponseDTO(Contest contest) {
        return new ContestResponseDTO(
                contest.getTitle(),
                contest.getAuthor(),
                contest.getStartTime(),
                contest.getEndTime());
    }
}
