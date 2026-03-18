package com.midnightdraft.poemofthedamned.application.impl;

import com.midnightdraft.poemofthedamned.application.service.DialogueService;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.model.Dialogue;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.domain.repository.DialogueRepository;
import java.util.List;

public class DialogueServiceImpl implements DialogueService {

  private final DialogueRepository dialogueRepository;
  private final GameStateMachine gameStateMachine;

  public DialogueServiceImpl(DialogueRepository dialogueRepository, GameStateMachine gameStateMachine){
    this.dialogueRepository = dialogueRepository;
    this.gameStateMachine = gameStateMachine;
  }

  // scenario: new game, load game, new game level(like if you do choice)
  @Override
  public void startScene(GameScene gameScene){
    List<Dialogue> dialogues = dialogueRepository.findBySceneIdOrderByOrderIndex(gameScene.getId());
    gameStateMachine.loadGameScene(gameScene, dialogues);
  }
}
