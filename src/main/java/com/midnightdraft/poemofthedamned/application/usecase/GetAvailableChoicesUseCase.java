package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.model.Choice;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.domain.repository.ChoiceRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAvailableChoicesUseCase {

  private final GameStateMachine gameStateMachine;
  private final ChoiceRepository choiceRepository;

  public GetAvailableChoicesUseCase(GameStateMachine gameStateMachine,
      ChoiceRepository choiceRepository) {
    this.gameStateMachine = gameStateMachine;
    this.choiceRepository = choiceRepository;
  }

  public List<Choice> execute() {
    GameScene currentScene = gameStateMachine.getCurrentScene();
    List<Choice> choices = choiceRepository.findBySceneId(currentScene.getId());
    List<Choice> filtered = choices.stream().filter(choice -> {
      if (choice.getRequiredFlag() == null) {
        return true;
      }
      String currentValue = gameStateMachine.getFlagValue(choice.getRequiredFlag().getName());
      return currentValue != null && currentValue.equals(choice.getRequiredFlagValue());
    }).toList();

    log.debug("Available choices for scene '{}': {}/{}",
        currentScene.getTitle(), filtered.size(), choices.size());
    return filtered;
  }
}
