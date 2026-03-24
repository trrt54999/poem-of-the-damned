package com.midnightdraft.poemofthedamned.domain.engine;

public sealed interface EngineResponse permits ChoiceResult, DialogueResult, TransitionResult{

}
