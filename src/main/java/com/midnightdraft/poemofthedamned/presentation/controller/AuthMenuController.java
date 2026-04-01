package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fonts;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Ui;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthMenuController {

  @FXML
  private StackPane rootPane;
  @FXML
  private VBox authCard;
  @FXML
  private VBox emailGroup;
  @FXML
  private VBox usernameGroup;
  @FXML
  private VBox passwordGroup;
  @FXML
  private VBox confirmPasswordGroup;
  @FXML
  private HBox tabSwitcher;
  @FXML
  private HBox orDivider;
  @FXML
  private ToggleGroup authToggleGroup;
  @FXML
  private ToggleButton loginTab;
  @FXML
  private ToggleButton registrationTab;
  @FXML
  private Button actionButton;
  @FXML
  private Button googleButton;
  @FXML
  private ImageView loginTabIcon;
  @FXML
  private ImageView registrationTabIcon;
  @FXML
  private ImageView usernameIcon;
  @FXML
  private ImageView emailIcon;
  @FXML
  private ImageView passwordIcon;
  @FXML
  private ImageView confirmPasswordIcon;
  @FXML
  private ImageView googleIcon;
  @FXML
  private TextField usernameInput;
  @FXML
  private TextField emailInput;
  @FXML
  private TextField passwordInput;
  @FXML
  private TextField confirmPasswordInput;

  private final StringBuilder passwordValue = new StringBuilder();
  private final StringBuilder confirmPasswordValue = new StringBuilder();
  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  @FXML
  public void initialize() {
    loadResources();
    setupAuthCard();
    setupIcons();
    setupFocusHighlight(usernameInput);
    setupFocusHighlight(emailInput);
    setupFocusHighlight(passwordInput);
    setupFocusHighlight(confirmPasswordInput);
    setupPasswordMasking(passwordInput, passwordValue);
    setupPasswordMasking(confirmPasswordInput, confirmPasswordValue);

    usernameGroup.setPrefHeight(0.0);
    usernameGroup.setMinHeight(0.0);
    usernameGroup.setOpacity(0);
    usernameGroup.setVisible(false);
    confirmPasswordGroup.setVisible(false);
    confirmPasswordGroup.setPrefHeight(0.0);
    confirmPasswordGroup.setMinHeight(0.0);
    confirmPasswordGroup.setOpacity(0);

    rootPane.styleProperty().bind(Bindings.createStringBinding(() -> {
      double calculatedSize = rootPane.getHeight() * 0.016;
      double clampedSize = Math.clamp(calculatedSize, 12, 32);

      return String.format("-fx-font-size: %.1fpx;", clampedSize);
    }, rootPane.heightProperty()));
  }

  private void loadResources() {
    String css = resourceProvider.getUrl(Css.AUTH_MENU).toExternalForm();
    rootPane.getStylesheets().add(css);
    Font.loadFont(resourceProvider.getUrl(Fonts.INTER_MEDIUM).toExternalForm(), 20);
  }

  private void setupAuthCard() {
    // 0.47 to 45 or 50
    authCard.maxWidthProperty().bind(Bindings.min(rootPane.widthProperty().multiply(0.40), 800)
        // rootPane.widthProperty().multiply(0.47),
        //     rootPane.heightProperty().multiply(0.80)
    );

    authCard.maxHeightProperty().set(Region.USE_PREF_SIZE);

    authToggleGroup.selectedToggleProperty().addListener((_, oldToggle, newToggle) -> {
      if (newToggle == null) {
        oldToggle.setSelected(true);
      } else if (newToggle == loginTab) {
        playLoginTransition();
        actionButton.setText("Login");
      } else if (newToggle == registrationTab) {
        playRegistrationTransition();
        actionButton.setText("Create account");
      }
    });
    loginTab.setSelected(true);
  }

  private void playLoginTransition() {
    double startHeight = usernameGroup.getHeight();

    usernameGroup.setPrefHeight(startHeight);
    usernameGroup.setMinHeight(startHeight);
    confirmPasswordGroup.setPrefHeight(startHeight);
    confirmPasswordGroup.setMinHeight(startHeight);

    Timeline timeline = new Timeline(
        new KeyFrame(Duration.millis(200),
            new KeyValue(usernameGroup.prefHeightProperty(), 0.0),
            new KeyValue(usernameGroup.minHeightProperty(), 0.0),
            new KeyValue(usernameGroup.opacityProperty(), 0.0),

            new KeyValue(confirmPasswordGroup.prefHeightProperty(), 0.0),
            new KeyValue(confirmPasswordGroup.minHeightProperty(), 0.0),
            new KeyValue(confirmPasswordGroup.opacityProperty(), 0.0)
        )
    );

    timeline.setOnFinished(_ -> {
      usernameGroup.setVisible(false);
      confirmPasswordGroup.setVisible(false);
    });

    timeline.play();
  }

  private void playRegistrationTransition() {
    usernameGroup.setPrefHeight(Region.USE_COMPUTED_SIZE);
    usernameGroup.setMinHeight(Region.USE_COMPUTED_SIZE);

    double targetHeight = usernameGroup.prefHeight(-1);

    usernameGroup.setPrefHeight(0.0);
    usernameGroup.setMinHeight(0.0);
    confirmPasswordGroup.setPrefHeight(0.0);
    confirmPasswordGroup.setMinHeight(0.0);

    usernameGroup.setVisible(true);
    confirmPasswordGroup.setVisible(true);

    Timeline timeline = new Timeline(
        new KeyFrame(Duration.millis(200),
            new KeyValue(usernameGroup.prefHeightProperty(), targetHeight),
            new KeyValue(usernameGroup.minHeightProperty(), targetHeight),
            new KeyValue(usernameGroup.opacityProperty(), 1.0),

            new KeyValue(confirmPasswordGroup.prefHeightProperty(), targetHeight),
            new KeyValue(confirmPasswordGroup.minHeightProperty(), targetHeight),
            new KeyValue(confirmPasswordGroup.opacityProperty(), 1.0)
        )
    );

    timeline.setOnFinished(_ -> {
      usernameGroup.setPrefHeight(Region.USE_COMPUTED_SIZE);
      usernameGroup.setMinHeight(Region.USE_COMPUTED_SIZE);
      confirmPasswordGroup.setPrefHeight(Region.USE_COMPUTED_SIZE);
      confirmPasswordGroup.setMinHeight(Region.USE_COMPUTED_SIZE);
    });

    timeline.play();
  }

  private void setupFocusHighlight(TextField field) {
    HBox container = (HBox) field.getParent();
    field.focusedProperty().addListener((_, _, focused) -> {
      container.getStyleClass().remove("active-field");
      if (Boolean.TRUE.equals(focused)) {
        container.getStyleClass().add("active-field");
      }
    });
  }

  private void setupIcons() {
    double defMultiply = 0.022;

    // Login Tab
    loginTabIcon.setImage(new Image(resourceProvider.getUrl(Ui.LOGIN_TAB_ICON).toExternalForm()));
    loginTabIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.028));
    loginTabIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(0.028));

    // Registration Tab
    registrationTabIcon.setImage(
        new Image(resourceProvider.getUrl(Ui.REGISTRATION_TAB_ICON).toExternalForm()));
    registrationTabIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.028));
    registrationTabIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(0.028));

    // Username Input
    usernameIcon.setImage(new Image(resourceProvider.getUrl(Ui.USERNAME_ICON).toExternalForm()));
    usernameIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(defMultiply));
    usernameIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(defMultiply));

    // Email Input
    emailIcon.setImage(new Image(resourceProvider.getUrl(Ui.EMAIL_ICON).toExternalForm()));
    emailIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(defMultiply));
    emailIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(defMultiply));

    // Password Input
    passwordIcon.setImage(new Image(resourceProvider.getUrl(Ui.PASSWORD_ICON).toExternalForm()));
    passwordIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(defMultiply));
    passwordIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(defMultiply));

    // Confirm Password Input
    confirmPasswordIcon.setImage(
        new Image(resourceProvider.getUrl(Ui.CONFIRM_PASSWORD_ICON).toExternalForm()));
    confirmPasswordIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(defMultiply));
    confirmPasswordIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(defMultiply));

    // Google Button
    googleIcon.setImage(new Image(resourceProvider.getUrl(Ui.GOOGLE_ICON).toExternalForm()));
    googleIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.025));
    googleIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(0.025));
  }

  private void setupPasswordMasking(TextField field, StringBuilder password) {
    field.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
      KeyCombination copy = new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN);
      KeyCombination cut = new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN);
      if (copy.match(event) || cut.match(event)) {
        event.consume();
      }
    });

    field.setContextMenu(new ContextMenu());

    field.setTextFormatter(new TextFormatter<>(change -> {
      int start = change.getRangeStart();
      int end = change.getRangeEnd();
      String inserted = change.getText();

      if (end > start) {
        password.delete(start, end);
      }
      if (!inserted.isEmpty()) {
        password.insert(start, inserted);
      }

      change.setText("•".repeat(inserted.length()));
      return change;
    }));
  }
}
