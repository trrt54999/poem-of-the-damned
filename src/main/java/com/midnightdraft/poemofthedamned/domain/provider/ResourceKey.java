package com.midnightdraft.poemofthedamned.domain.provider;

public sealed interface ResourceKey permits AudioKey, VisualKey, ConfigKey
{
  String name();
}


