package com.tad.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository< T, ID> {

    @Override
    List<T> findAllById(Iterable<ID> uuids);
}
