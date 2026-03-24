package com.midnightdraft.poemofthedamned.domain.provider;

public sealed interface VisualKey extends ResourceKey permits
    ResourceCatalog.GameCharacters,
    ResourceCatalog.Ui,
    ResourceCatalog.Backgrounds {}
