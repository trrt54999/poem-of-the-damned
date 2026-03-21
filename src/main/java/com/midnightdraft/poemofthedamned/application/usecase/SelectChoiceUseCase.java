package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.engine.DialogueStep;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.model.Choice;
import java.util.Optional;

public class SelectChoiceUseCase {

  private final GameStateMachine gameStateMachine;
  private final StartSceneUseCase startSceneUseCase;

  public SelectChoiceUseCase(GameStateMachine gameStateMachine, StartSceneUseCase startSceneUseCase){
    this.gameStateMachine = gameStateMachine;
    this.startSceneUseCase = startSceneUseCase;
  }

  public Optional<DialogueStep> execute(Choice choice) {
    choice.getChoiceEffects().forEach(gameStateMachine::applyEffect);
    startSceneUseCase.execute(choice.getNextGameScene());
    return gameStateMachine.continueScene();
  }
}
