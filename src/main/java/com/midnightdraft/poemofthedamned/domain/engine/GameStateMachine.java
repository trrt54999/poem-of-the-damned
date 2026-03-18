package com.midnightdraft.poemofthedamned.domain.engine;

import com.midnightdraft.poemofthedamned.domain.model.Dialogue;
import com.midnightdraft.poemofthedamned.domain.model.GameCharacter;
import com.midnightdraft.poemofthedamned.domain.model.GameCharacterSprite;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.domain.model.SaveFlag;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.NONE)
public class GameStateMachine {

  @Getter
  private GameState currentState;
  @Getter
  private GameScene currentScene;
  @Getter
  private String currentMusicPath;

  private List<Dialogue> dialogues;
  private List<SaveFlag> activeFlags;
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

  public DialogueStep continueScene(){
    Dialogue dialogue = dialogues.get(index++);

    if(index >= dialogues.size()) {
      currentState = GameState.WAITING_FOR_CHOICE;
    }

    if (dialogue.getMusicPath() != null) {
      this.currentMusicPath = dialogue.getMusicPath();
    }

    return new DialogueStep(
        Optional.ofNullable(dialogue.getGameCharacter())
            .map(GameCharacter::getName),
        Optional.ofNullable(dialogue.getGameCharacterSprite())
            .map(GameCharacterSprite::getSpritePath),
        Optional.ofNullable(dialogue.getMusicPath()),
        Optional.ofNullable(dialogue.getSpritePosition()),
        dialogue.getText(),
        currentScene.getBackgroundPath());
  }
}
