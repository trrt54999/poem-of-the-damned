package com.midnightdraft.poemofthedamned.domain.engine;

import java.util.Optional;

public record DialogueStep (
    Optional<String> characterName,
    Optional<String> characterSpritePath,
    Optional<String> musicPath,
    Optional<SpritePosition> spritePosition,
    String text,
    String backgroundPath) {}
