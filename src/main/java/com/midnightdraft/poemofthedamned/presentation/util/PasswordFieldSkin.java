package com.midnightdraft.poemofthedamned.presentation.util;

import javafx.scene.control.PasswordField;
import javafx.scene.control.skin.TextFieldSkin;

public final class PasswordFieldSkin extends TextFieldSkin {

  private boolean masked = true;

  private static final char MASK = '\u2022';

  public PasswordFieldSkin(PasswordField field) {
    super(field);
  }

  @Override
  protected String maskText(String text) {
    return masked ? String.valueOf(MASK).repeat(text.length()) : text;
  }

  public void setMasked(boolean masked) {
    this.masked = masked;
    PasswordField field = (PasswordField) getSkinnable();
    int caret = field.getCaretPosition();
    String current = field.getText();
    field.setText("");
    field.setText(current);
    field.positionCaret(caret);
  }
}