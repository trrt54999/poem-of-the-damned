package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.engine.DialogueStep;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import java.util.Optional;

public class AdvanceDialogueUseCase {

  private final GameStateMachine gameStateMachine;

  public AdvanceDialogueUseCase(GameStateMachine gameStateMachine){
    this.gameStateMachine = gameStateMachine;
  }

  public Optional<DialogueStep> execute(){
    return gameStateMachine.continueScene();
  }
}
