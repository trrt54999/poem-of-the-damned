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
}
