package com.midnightdraft.poemofthedamned.domain;

import java.io.Serializable;

public interface Identifiable<T extends Serializable> {

  T getId();
}
