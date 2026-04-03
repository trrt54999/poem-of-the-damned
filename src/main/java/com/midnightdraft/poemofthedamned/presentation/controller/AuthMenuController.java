package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.application.exception.InvalidCredentialsException;
import com.midnightdraft.poemofthedamned.application.exception.UserAlreadyExistsException;
import com.midnightdraft.poemofthedamned.application.usecase.LoginUseCase;
import com.midnightdraft.poemofthedamned.application.usecase.RegistrationUseCase;
import com.midnightdraft.poemofthedamned.domain.model.User;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fonts;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Ui;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.domain.repository.UserRepository;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.repository.impl.UserRepositoryImpl;
import com.midnightdraft.poemofthedamned.presentation.AuthValidator;
import com.midnightdraft.poemofthedamned.presentation.util.PasswordFieldSkin;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
  private ToggleButton passwordEyeBtn;
  @FXML
  private ToggleButton confirmPasswordEyeBtn;
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
  private ImageView passwordEyeIcon;
  @FXML
  private ImageView confirmPasswordEyeIcon;
  @FXML
  private TextField usernameInput;
  @FXML
  private TextField emailInput;
  @FXML
  private PasswordField passwordInput;
  @FXML
  private PasswordField confirmPasswordInput;
  @FXML
  private Label authError;
  @FXML
  private Label usernameError;
  @FXML
  private Label emailError;
  @FXML
  private Label passwordError;
  @FXML
  private Label confirmPasswordError;

  private PasswordFieldSkin passwordSkin;
  private PasswordFieldSkin confirmPasswordSkin;

  private final UserRepository userRepository = new UserRepositoryImpl();
  private final RegistrationUseCase registrationUseCase = new RegistrationUseCase(userRepository);
  private final LoginUseCase loginUseCase = new LoginUseCase(userRepository);
  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  @FXML
  public void initialize() {
    loadResources();
    setupAuthCard();
    setupPasswordFields();
    setupIcons();
    setupFieldValidation(usernameInput, usernameGroup, usernameError,
        AuthValidator::validateUsername);
    setupFieldValidation(emailInput, emailGroup, emailError, AuthValidator::validateEmail);
    setupFieldValidation(passwordInput, passwordGroup, passwordError,
        AuthValidator::validatePassword);
    setupFieldValidation(confirmPasswordInput, confirmPasswordGroup, confirmPasswordError,
        text -> AuthValidator.validateConfirmPassword(passwordInput.getText(), text));

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

  private void toggleValidationState(VBox container, Label errorLabel, String error) {
    container.getStyleClass().removeAll("field-error", "field-success");
    if (error != null) {
      container.getStyleClass().add("field-error");
      errorLabel.setText("•  " + error);
      errorLabel.setVisible(true);
      errorLabel.setManaged(true);
    } else {
      container.getStyleClass().add("field-success");
      errorLabel.setVisible(false);
      errorLabel.setManaged(false);
    }
  }

  private void showAuthError(String message) {
    authError.setText(message);
    authError.setVisible(true);
    authError.setManaged(true);
  }

  private void handleRegistration() {
    String username = usernameInput.getText().strip();
    String email = emailInput.getText().strip();
    String password = passwordInput.getText();
    String confirmPassword = confirmPasswordInput.getText();

    String usernameValidation = AuthValidator.validateUsername(username).orElse(null);
    String emailValidation = AuthValidator.validateEmail(email).orElse(null);
    String passwordValidation = AuthValidator.validatePassword(password).orElse(null);
    String confirmPasswordValidation = AuthValidator.validateConfirmPassword(password,
        confirmPassword).orElse(null);

    toggleValidationState(usernameGroup, usernameError, usernameValidation);
    toggleValidationState(emailGroup, emailError, emailValidation);
    toggleValidationState(passwordGroup, passwordError, passwordValidation);
    toggleValidationState(confirmPasswordGroup, confirmPasswordError, confirmPasswordValidation);

    if (Stream.of(usernameValidation, emailValidation, passwordValidation,
        confirmPasswordValidation).anyMatch(java.util.Objects::nonNull)) {
      return;
    }

    actionButton.setDisable(true);
    Task<User> task = new Task<>() {
      @Override
      protected User call() {
        return registrationUseCase.execute(username, email, password);
      }
    };

    task.setOnSucceeded(_ -> {
      actionButton.setDisable(false);
      // todo load main menu
    });

    task.setOnFailed(_ -> {
      Throwable ex = task.getException();
      if (ex instanceof UserAlreadyExistsException) {
        showAuthError("User already exists!");
        actionButton.setDisable(false);
      }
    });

    Thread taskThread = new Thread(task);
    taskThread.setDaemon(true);
    taskThread.start();
  }

  private void handleLogin() {
    String email = emailInput.getText().strip();
    String password = passwordInput.getText();

    String emailValidation = AuthValidator.validateEmail(email).orElse(null);
    String passwordValidation = AuthValidator.validatePassword(password).orElse(null);

    toggleValidationState(emailGroup, emailError, emailValidation);
    toggleValidationState(passwordGroup, passwordError, passwordValidation);

    if (Stream.of(emailValidation, passwordValidation).anyMatch(java.util.Objects::nonNull)) {
      return;
    }

    actionButton.setDisable(true);
    Task<User> task = new Task<>() {
      @Override
      protected User call() {
        return loginUseCase.execute(email, password);
      }
    };

    task.setOnSucceeded(_ -> {
      actionButton.setDisable(false);
      // todo load main menu
    });

    task.setOnFailed(_ -> {
      Throwable ex = task.getException();
      if (ex instanceof InvalidCredentialsException) {
        showAuthError("Invalid email or password!");
        actionButton.setDisable(false);
      }
    });

    Thread taskThread = new Thread(task);
    taskThread.setDaemon(true);
    taskThread.start();
  }

  private void loadResources() {
    String css = resourceProvider.getUrl(Css.AUTH_MENU).toExternalForm();
    rootPane.getStylesheets().add(css);
    Font.loadFont(resourceProvider.getUrl(Fonts.INTER_MEDIUM).toExternalForm(), 20);
  }

  private void setupAuthCard() {
    authCard.maxWidthProperty().bind(Bindings.min(rootPane.widthProperty().multiply(0.40), 800));
    authCard.maxHeightProperty().set(Region.USE_PREF_SIZE);

    authToggleGroup.selectedToggleProperty().addListener((_, oldToggle, newToggle) -> {
      if (newToggle == null) {
        oldToggle.setSelected(true);
      } else if (newToggle == loginTab) {
        playLoginTransition();
        actionButton.setText("Login");
        actionButton.setOnAction(_ -> handleLogin());
        authError.setVisible(false);
      } else if (newToggle == registrationTab) {
        playRegistrationTransition();
        actionButton.setText("Create account");
        actionButton.setOnAction(_ -> handleRegistration());
        authError.setVisible(false);
      }
    });
    loginTab.setSelected(true);
  }

  private void setupPasswordFields() {
    passwordInput.setContextMenu(new ContextMenu());
    confirmPasswordInput.setContextMenu(new ContextMenu());

    passwordSkin = new PasswordFieldSkin(passwordInput);
    passwordInput.setSkin(passwordSkin);

    confirmPasswordSkin = new PasswordFieldSkin(confirmPasswordInput);
    confirmPasswordInput.setSkin(confirmPasswordSkin);
  }

  private void playLoginTransition() {
    double startHeight = usernameGroup.getHeight();

    usernameGroup.setPrefHeight(startHeight);
    usernameGroup.setMinHeight(startHeight);
    confirmPasswordGroup.setPrefHeight(startHeight);
    confirmPasswordGroup.setMinHeight(startHeight);

    Timeline timeline = new Timeline(
        new KeyFrame(Duration.millis(200), new KeyValue(usernameGroup.prefHeightProperty(), 0.0),
            new KeyValue(usernameGroup.minHeightProperty(), 0.0),
            new KeyValue(usernameGroup.opacityProperty(), 0.0),
            new KeyValue(confirmPasswordGroup.prefHeightProperty(), 0.0),
            new KeyValue(confirmPasswordGroup.minHeightProperty(), 0.0),
            new KeyValue(confirmPasswordGroup.opacityProperty(), 0.0)));

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

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200),
        new KeyValue(usernameGroup.prefHeightProperty(), targetHeight),
        new KeyValue(usernameGroup.minHeightProperty(), targetHeight),
        new KeyValue(usernameGroup.opacityProperty(), 1.0),
        new KeyValue(confirmPasswordGroup.prefHeightProperty(), targetHeight),
        new KeyValue(confirmPasswordGroup.minHeightProperty(), targetHeight),
        new KeyValue(confirmPasswordGroup.opacityProperty(), 1.0)));

    timeline.setOnFinished(_ -> {
      usernameGroup.setPrefHeight(Region.USE_COMPUTED_SIZE);
      usernameGroup.setMinHeight(Region.USE_COMPUTED_SIZE);
      confirmPasswordGroup.setPrefHeight(Region.USE_COMPUTED_SIZE);
      confirmPasswordGroup.setMinHeight(Region.USE_COMPUTED_SIZE);
    });

    timeline.play();
  }

  private void setupFieldValidation(TextField field, VBox group, Label errorLabel,
      Function<String, Optional<String>> validator) {

    field.focusedProperty().addListener((_, _, focused) -> {
      HBox container = (HBox) field.getParent();
      container.getStyleClass().remove("active-field");
      if (focused) {
        container.getStyleClass().add("active-field");
      } else {
        toggleValidationState(group, errorLabel, validator.apply(field.getText()).orElse(null));
      }
    });

    field.textProperty().addListener((_, _, _) -> toggleValidationState(group, errorLabel,
        validator.apply(field.getText()).orElse(null)));
  }

  private void setupEyeToggle(ToggleButton btn, ImageView icon, PasswordFieldSkin skin,
      double multiply) {
    icon.setImage(new Image(resourceProvider.getUrl(Ui.EYE_ON_ICON).toExternalForm()));
    icon.fitHeightProperty().bind(rootPane.heightProperty().multiply(multiply));
    icon.fitWidthProperty().bind(rootPane.heightProperty().multiply(multiply));

    btn.selectedProperty().addListener((_, _, isSelected) -> {
      skin.setMasked(!isSelected);
      icon.setImage(new Image(
          resourceProvider.getUrl(isSelected ? Ui.EYE_OFF_ICON : Ui.EYE_ON_ICON).toExternalForm()));
    });
  }

  private void setupIcons() {
    double defMultiply = 0.022;

    loginTabIcon.setImage(new Image(resourceProvider.getUrl(Ui.LOGIN_TAB_ICON).toExternalForm()));
    loginTabIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.028));
    loginTabIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(0.028));

    registrationTabIcon.setImage(
        new Image(resourceProvider.getUrl(Ui.REGISTRATION_TAB_ICON).toExternalForm()));
    registrationTabIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.028));
    registrationTabIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(0.028));

    usernameIcon.setImage(new Image(resourceProvider.getUrl(Ui.USERNAME_ICON).toExternalForm()));
    usernameIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(defMultiply));
    usernameIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(defMultiply));

    emailIcon.setImage(new Image(resourceProvider.getUrl(Ui.EMAIL_ICON).toExternalForm()));
    emailIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(defMultiply));
    emailIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(defMultiply));

    passwordIcon.setImage(new Image(resourceProvider.getUrl(Ui.PASSWORD_ICON).toExternalForm()));
    passwordIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(defMultiply));
    passwordIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(defMultiply));

    confirmPasswordIcon.setImage(
        new Image(resourceProvider.getUrl(Ui.CONFIRM_PASSWORD_ICON).toExternalForm()));
    confirmPasswordIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(defMultiply));
    confirmPasswordIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(defMultiply));

    googleIcon.setImage(new Image(resourceProvider.getUrl(Ui.GOOGLE_ICON).toExternalForm()));
    googleIcon.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.025));
    googleIcon.fitWidthProperty().bind(rootPane.heightProperty().multiply(0.025));

    setupEyeToggle(passwordEyeBtn, passwordEyeIcon, passwordSkin, defMultiply);
    setupEyeToggle(confirmPasswordEyeBtn, confirmPasswordEyeIcon, confirmPasswordSkin, defMultiply);
  }
}