package com.midnightdraft.poemofthedamned.domain.engine;

import com.midnightdraft.poemofthedamned.App;
import com.midnightdraft.poemofthedamned.domain.model.ChoiceEffect;
import com.midnightdraft.poemofthedamned.domain.model.Dialogue;
import com.midnightdraft.poemofthedamned.domain.model.GameCharacter;
import com.midnightdraft.poemofthedamned.domain.model.GameCharacterSprite;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter(AccessLevel.NONE)
@Slf4j
public class GameStateMachine {

  @Getter
  private GameState currentState;
  @Getter
  private GameScene currentScene;
  @Getter
  private String currentMusicPath;
  @Getter
  private String currentAmbientPath;
  private List<Dialogue> dialogues;
  private int index = 0;

  private GameScene queuedTransitionScene;

  private final Map<String, String> activeFlags = new HashMap<>();

  private GameStateMachine() {
  }

  private static class GameStateMachineHolder {

    private static final GameStateMachine HOLDER_INSTANCE = new GameStateMachine();
  }

  public static GameStateMachine getInstance() {
    return GameStateMachineHolder.HOLDER_INSTANCE;
  }

  public void loadGameScene(GameScene gameScene, List<Dialogue> dialogues) {
    this.currentScene = gameScene;
    this.dialogues = dialogues;
    this.index = 0;
    this.currentState = GameState.PLAYING_DIALOGUE;
    this.currentMusicPath = gameScene.getSoundtrackPath();
    this.currentAmbientPath = gameScene.getAmbientPath();
  }

  public Optional<DialogueStep> continueScene() {
    if (currentState.equals(GameState.WAITING_FOR_CHOICE)) {
      return Optional.empty();
    }

    if (index >= dialogues.size()) {
      GameScene nextScene = currentScene.getNextScene();

      if (nextScene == null) {
        log.debug("No next scene found, switching to WAITING_FOR_CHOICE");
        currentState = GameState.WAITING_FOR_CHOICE;
        return Optional.empty();
      } else {
        log.debug("Dialogues exhausted, switching to TRANSITION");
        currentState = GameState.TRANSITION;
      }
      return Optional.empty();
    }

    boolean isFirstDialogue = (index == 0);

    Dialogue dialogue = dialogues.get(index++);

    String musicToPlay = dialogue.getMusicPath();
    String ambientToPlay = dialogue.getAmbientPath();

    if (isFirstDialogue && musicToPlay == null) {
      musicToPlay = currentScene.getSoundtrackPath();
    }

    if (isFirstDialogue && ambientToPlay == null) {
      ambientToPlay = currentScene.getAmbientPath();
    }

    if (musicToPlay != null) {
      this.currentMusicPath = musicToPlay;
    }

    if (ambientToPlay != null) {
      this.currentAmbientPath = ambientToPlay;
    }

    String lang = App.currentLang;

    String translatedText = dialogue.getTranslations()
        .getOrDefault(lang, dialogue.getDefaultText());
    Optional<String> translatedName = Optional.ofNullable(dialogue.getGameCharacter())
        .map(c -> c.getTranslationNames().getOrDefault(lang, c.getName()));

    return Optional.of(new DialogueStep(translatedName,
        Optional.ofNullable(dialogue.getGameCharacterSprite())
            .map(GameCharacterSprite::getSpritePath), Optional.ofNullable(musicToPlay),
        Optional.ofNullable(ambientToPlay), Optional.ofNullable(dialogue.getSpritePosition()),
        translatedText, currentScene.getBackgroundPath()));
  }

  public void queueSceneTransition(GameScene targetScene) {
    this.queuedTransitionScene = targetScene;
  }

  public Optional<GameScene> consumeQueuedScene() {
    GameScene scene = this.queuedTransitionScene;
    this.queuedTransitionScene = null;
    return Optional.ofNullable(scene);
  }

  public void resumeFromChoice() {
    this.currentState = GameState.PLAYING_DIALOGUE;
  }

  public String getFlagValue(String flagName) {
    return activeFlags.get(flagName);
  }

  public void applyEffect(ChoiceEffect effect) {
    log.info("Applying flag effect: '{}' = '{}'", effect.getFlag().getName(), effect.getNewValue());
    activeFlags.put(effect.getFlag().getName(), effect.getNewValue());
  }

  public Map<String, String> getActiveFlags() {
    return Collections.unmodifiableMap(activeFlags);
  }
}
