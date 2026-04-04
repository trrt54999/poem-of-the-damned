package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.engine.ChoiceResult;
import com.midnightdraft.poemofthedamned.domain.engine.DialogueResult;
import com.midnightdraft.poemofthedamned.domain.engine.DialogueStep;
import com.midnightdraft.poemofthedamned.domain.engine.EngineResponse;
import com.midnightdraft.poemofthedamned.domain.engine.GameState;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.engine.TransitionResult;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdvanceDialogueUseCase {

  private final GameStateMachine gameStateMachine;

  public AdvanceDialogueUseCase(GameStateMachine gameStateMachine) {
    this.gameStateMachine = gameStateMachine;
  }

  public EngineResponse execute() {
    Optional<DialogueStep> step = gameStateMachine.continueScene();

    if (step.isPresent()) {
      return new DialogueResult(step.get());
    }

    if (gameStateMachine.getCurrentState() == GameState.WAITING_FOR_CHOICE) {
      log.debug("Waiting for player choice");
      return new ChoiceResult();
    }
    log.debug("No more dialogues, transitioning scene");
    return new TransitionResult(gameStateMachine.getCurrentScene().getTransitionType());
  }
}
