package com.midnightdraft.poemofthedamned.domain.engine;

import com.midnightdraft.poemofthedamned.domain.model.Dialogue;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.domain.model.SaveFlag;
import java.util.List;

public class GameStateMachine {

  private GameState currentState;
  private GameScene currentScene;
  private List<Dialogue> dialogues;
  private List<SaveFlag> activeFlags;
  private String currentMusicPath;
  private int index = 0;

  private GameStateMachine(){}

  private static class GameStateMachineHolder{
    private static final GameStateMachine HOLDER_INSTANCE = new GameStateMachine();
  }

  public static GameStateMachine getInstance(){
    return GameStateMachineHolder.HOLDER_INSTANCE;
  }

  public void loadGameScene(GameScene gameScene, List<Dialogue> dialogues){
    this.currentScene = gameScene;
    this.dialogues = dialogues;
    this.index = 0;
  }

  // nazva gavno
  public void next(){

  }
}

