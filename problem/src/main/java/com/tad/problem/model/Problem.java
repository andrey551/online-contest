package com.tad.problem.model;

import com.tad.problem.model.enums.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Document(collection = "problems")
@Getter
@Setter
@NoArgsConstructor
public class Problem {
    @Id
    private UUID id;

    @Field(name = "title")
    private String title;

    @Field(name = "description")
    private String description;

    @Field(name = "author")
    private String author;

    @Field(name = "difficulty")
    private Integer difficulty;

    @Field(name = "created")
    private Timestamp created;

    @Field(name = "tags")
    private List<Tag> Tags;
}
