package com.tad.course.repositories;

import com.tad.course.entities.Laboratory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface LaboratoryRepository
        extends CrudRepository<Laboratory, UUID> {

    Optional<List<Laboratory>> findLaboratoriesByCourseId(UUID courseId);
}
