package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.engine.ChoiceResult;
import com.midnightdraft.poemofthedamned.domain.engine.DialogueResult;
import com.midnightdraft.poemofthedamned.domain.engine.DialogueStep;
import com.midnightdraft.poemofthedamned.domain.engine.EngineResponse;
import com.midnightdraft.poemofthedamned.domain.engine.GameState;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.engine.TransitionResult;
import java.util.Optional;

public class AdvanceDialogueUseCase {

  private final GameStateMachine gameStateMachine;

  public AdvanceDialogueUseCase(GameStateMachine gameStateMachine){
    this.gameStateMachine = gameStateMachine;
  }

  public EngineResponse execute(){
    Optional<DialogueStep> step = gameStateMachine.continueScene();

    if (step.isPresent()) {
      return new DialogueResult(step.get());
    }

    if(gameStateMachine.getCurrentState() == GameState.WAITING_FOR_CHOICE){
      return new ChoiceResult();
    }

    return new TransitionResult(gameStateMachine.getCurrentScene().getTransitionType());
  }
}
