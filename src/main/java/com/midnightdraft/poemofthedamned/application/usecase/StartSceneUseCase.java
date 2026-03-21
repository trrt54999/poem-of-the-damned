package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.model.Dialogue;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.domain.repository.DialogueRepository;
import java.util.List;

public class StartSceneUseCase {

  private final DialogueRepository dialogueRepository;
  private final GameStateMachine gameStateMachine;

  public StartSceneUseCase(DialogueRepository dialogueRepository, GameStateMachine gameStateMachine){
    this.dialogueRepository = dialogueRepository;
    this.gameStateMachine = gameStateMachine;
  }

  public void execute(GameScene gameScene){
    List<Dialogue> dialogues = dialogueRepository.findBySceneIdOrderByOrderIndex(gameScene.getId());
    gameStateMachine.loadGameScene(gameScene, dialogues);
  }
}
