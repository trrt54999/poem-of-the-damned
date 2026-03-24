package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.model.Choice;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.domain.repository.ChoiceRepository;
import java.util.List;

public class GetAvailableChoicesUseCase {

  private final GameStateMachine gameStateMachine;
  private final ChoiceRepository choiceRepository;

  public GetAvailableChoicesUseCase(GameStateMachine gameStateMachine,ChoiceRepository choiceRepository){
    this.choiceRepository = choiceRepository;
    this.gameStateMachine = gameStateMachine;
  }

  public List<Choice> execute(){
    GameScene currentScene = gameStateMachine.getCurrentScene();
    List<Choice> choices = choiceRepository.findBySceneId(currentScene.getId());
    return choices.stream().filter(choice -> {
       if(choice.getRequiredFlag() == null){
         return true;
       }
      String currentValue = gameStateMachine.getFlagValue(choice.getRequiredFlag().getName());
      return currentValue != null && currentValue.equals(choice.getRequiredFlagValue());
    }).toList();
  }
}
