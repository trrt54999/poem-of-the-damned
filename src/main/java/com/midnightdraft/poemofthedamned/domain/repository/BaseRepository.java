package com.midnightdraft.poemofthedamned.domain.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

  void delete(T entity);
  T save(T entity);
  T update(T entity);
  Optional<T> findById(Long id);
  List<T> findAll();
}
