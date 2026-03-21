package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.model.Choice;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.domain.repository.ChoiceRepository;
import java.util.List;

public class GetAvailableChoicesUseCase {

  private final ChoiceRepository choiceRepository;

  public GetAvailableChoicesUseCase(ChoiceRepository choiceRepository){
    this.choiceRepository = choiceRepository;
  }

  public List<Choice> execute(){
    GameScene currentScene = GameStateMachine.getInstance().getCurrentScene();
    List<Choice> choices = choiceRepository.findBySceneId(currentScene.getId());
    GameStateMachine.getInstance().getCurrentScene().get
    return choices;
  }
}
