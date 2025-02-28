package com.tad.contest.model;

import com.tad.contest.model.enums.ContestStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Document(collection = "contest")
@Getter
@Setter
@NoArgsConstructor
public class Contest {
    @Id
    private UUID id;

    @Field(name = "user_id")
    private UUID userId;

    @Field(name = "title")
    private String title;

    @Field(name = "author")
    private String author;

    @Field(name = "start_time")
    private Timestamp startTime;

    @Field(name = "end_time")
    private Timestamp endTime;

    @Field(name = "create_at")
    private Timestamp createdAt;

    @Field(name = "status")
    private ContestStatus status;

    private List<Long> problems;
}
