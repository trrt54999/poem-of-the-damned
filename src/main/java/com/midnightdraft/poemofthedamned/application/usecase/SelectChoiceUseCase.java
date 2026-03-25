package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.engine.DialogueStep;
import com.midnightdraft.poemofthedamned.domain.engine.EngineResponse;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.engine.TransitionResult;
import com.midnightdraft.poemofthedamned.domain.model.Choice;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.domain.repository.ChoiceRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SelectChoiceUseCase {

  private final GameStateMachine gameStateMachine;
  private final ChoiceRepository choiceRepository;
  private final StartSceneUseCase startSceneUseCase;
  private final AdvanceDialogueUseCase advanceDialogueUseCase;

  public EngineResponse execute(Long choiceId) {
    Optional<Choice> choiceOpt = choiceRepository.findById(choiceId);

    if(choiceOpt.isEmpty()) {
     return advanceDialogueUseCase.execute();
    }

    Choice choice = choiceOpt.get();

    choice.getChoiceEffects().forEach(gameStateMachine::applyEffect);

    GameScene nextScene = choice.getNextGameScene();

    if(nextScene != null){
      gameStateMachine.queueSceneTransition(nextScene);
      return new TransitionResult(gameStateMachine.getCurrentScene().getTransitionType());
    }

    gameStateMachine.resumeFromChoice();
    log.error("Unknown choice id!");
    return advanceDialogueUseCase.execute();
  }
}
