package com.midnightdraft.poemofthedamned.infrastructure.repository;

import com.midnightdraft.poemofthedamned.domain.model.Flag;
import java.util.Optional;

public interface FlagRepository extends BaseRepository<Flag>{

  Optional<Flag> findByName(String name);
}
