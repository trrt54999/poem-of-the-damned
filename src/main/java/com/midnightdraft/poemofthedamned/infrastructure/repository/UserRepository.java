package com.midnightdraft.poemofthedamned.infrastructure.repository;

import com.midnightdraft.poemofthedamned.domain.model.User;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

  Optional<User> findByName(String username);
  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email);
}
