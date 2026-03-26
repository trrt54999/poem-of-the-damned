package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.engine.EngineResponse;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.domain.repository.GameSceneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class CompleteSceneTransitionUseCase {

  private final GameStateMachine gameStateMachine;
  private final GameSceneRepository gameSceneRepository;
  private final StartSceneUseCase startSceneUseCase;
  private final AdvanceDialogueUseCase advanceDialogueUseCase;

  public EngineResponse execute() {
    GameScene currentScene = gameStateMachine.getCurrentScene();
    GameScene targetScene = gameStateMachine.consumeQueuedScene()
        .orElse(currentScene.getNextScene());
    GameScene fullyLoadedScene = gameSceneRepository.findById(targetScene.getId()).orElseThrow();

    log.info("Transition completed. Fetching target Scene: {}", targetScene.getId());
    startSceneUseCase.execute(fullyLoadedScene);

    return advanceDialogueUseCase.execute();
  }
}
