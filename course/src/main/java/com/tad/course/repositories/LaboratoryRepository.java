package com.tad.course.repositories;

import com.tad.course.entities.Laboratory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface LaboratoryRepository
        extends CrudRepository<Laboratory, UUID> {
}
