package com.tad.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BaseRepository<T, UUID> extends JpaRepository< T, UUID> {

    @Override
    List<T> findAllById(Iterable<UUID> uuids);
}
