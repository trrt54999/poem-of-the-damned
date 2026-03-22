package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.engine.DialogueStep;
import com.midnightdraft.poemofthedamned.domain.engine.GameState;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.domain.repository.GameSceneRepository;
import java.util.Optional;

public class AdvanceDialogueUseCase {

  private final GameStateMachine gameStateMachine;
  private final StartSceneUseCase startSceneUseCase;
  private final GameSceneRepository gameSceneRepository;

  public AdvanceDialogueUseCase(GameStateMachine gameStateMachine,
      StartSceneUseCase startSceneUseCase, GameSceneRepository gameSceneRepository){
    this.gameStateMachine = gameStateMachine;
    this.startSceneUseCase = startSceneUseCase;
    this.gameSceneRepository = gameSceneRepository;
  }

  public Optional<DialogueStep> execute(){
    Optional<DialogueStep> step = gameStateMachine.continueScene();

    if (step.isEmpty() && gameStateMachine.getCurrentState() == GameState.TRANSITION) {
      Long nextSceneId = gameStateMachine.getCurrentScene().getNextScene().getId();
      GameScene nextScene = gameSceneRepository.findById(nextSceneId).orElseThrow();
      startSceneUseCase.execute(nextScene);
      return gameStateMachine.continueScene();
    }

    return step;
  }
}
