package com.midnightdraft.poemofthedamned.domain.provider;

public sealed interface AudioKey extends ResourceKey permits
    ResourceCatalog.AudioBgm,
    ResourceCatalog.AudioSfx {}
