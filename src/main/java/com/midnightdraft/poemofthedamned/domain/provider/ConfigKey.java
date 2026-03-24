package com.midnightdraft.poemofthedamned.domain.provider;

public sealed interface ConfigKey extends ResourceKey permits
    ResourceCatalog.Css,
    ResourceCatalog.Fonts,
    ResourceCatalog.Fxml {}
