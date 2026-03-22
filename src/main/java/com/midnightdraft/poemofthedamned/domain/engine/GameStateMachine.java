package com.midnightdraft.poemofthedamned.domain.engine;

import com.midnightdraft.poemofthedamned.domain.model.ChoiceEffect;
import com.midnightdraft.poemofthedamned.domain.model.Dialogue;
import com.midnightdraft.poemofthedamned.domain.model.GameCharacter;
import com.midnightdraft.poemofthedamned.domain.model.GameCharacterSprite;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  private int index = 0;

  private final Map<String, String> activeFlags = new HashMap<>();

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
    this.currentState = GameState.PLAYING_DIALOGUE;
    this.currentMusicPath = gameScene.getSoundtrackPath();
  }

  public Optional<DialogueStep> continueScene(){
    if(currentState.equals(GameState.WAITING_FOR_CHOICE)){
      return Optional.empty();
    }

    if(index >= dialogues.size()) {
      GameScene nextScene = currentScene.getNextScene();

      if (nextScene == null) {
        currentState = GameState.WAITING_FOR_CHOICE;
        return Optional.empty();
      } else {
        currentState = GameState.TRANSITION;
      }
      return Optional.empty();
    }

    Dialogue dialogue = dialogues.get(index++);

    String musicToPlay = dialogue.getMusicPath();
    if(index == 1 && musicToPlay == null){
      musicToPlay = currentScene.getSoundtrackPath();
    }
    if(musicToPlay != null){
      this.currentMusicPath = musicToPlay;
    }

    return Optional.of(new DialogueStep(
        Optional.ofNullable(dialogue.getGameCharacter())
            .map(GameCharacter::getName),
        Optional.ofNullable(dialogue.getGameCharacterSprite())
            .map(GameCharacterSprite::getSpritePath),
        Optional.ofNullable(musicToPlay),
        Optional.ofNullable(dialogue.getSpritePosition()),
        dialogue.getText(),
        currentScene.getBackgroundPath()));
  }

  public String getFlagValue(String flagName) {
    return activeFlags.get(flagName);
  }

  public void applyEffect(ChoiceEffect effect) {
    activeFlags.put(effect.getFlag().getName(), effect.getNewValue());
  }

  public Map<String, String> getActiveFlags() {
    return Collections.unmodifiableMap(activeFlags);
  }
}
