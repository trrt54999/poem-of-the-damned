package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.engine.DialogueStep;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.model.Choice;
import com.midnightdraft.poemofthedamned.domain.repository.ChoiceRepository;
import java.util.Optional;

public class SelectChoiceUseCase {

  private final GameStateMachine gameStateMachine;
  private final ChoiceRepository choiceRepository;
  private final StartSceneUseCase startSceneUseCase;

  public SelectChoiceUseCase(GameStateMachine gameStateMachine, ChoiceRepository choiceRepository, StartSceneUseCase startSceneUseCase){
    this.gameStateMachine = gameStateMachine;
    this.choiceRepository = choiceRepository;
    this.startSceneUseCase = startSceneUseCase;
  }

  public Optional<DialogueStep> execute(Long choiceId) {
    Optional<Choice> choice = choiceRepository.findById(choiceId);
    if (choice.isPresent()) {
      choice.get().getChoiceEffects().forEach(gameStateMachine::applyEffect);
      startSceneUseCase.execute(choice.get().getNextGameScene());
      return gameStateMachine.continueScene();
    }
    return Optional.empty();
  }
}
