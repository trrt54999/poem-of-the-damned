package com.midnightdraft.poemofthedamned.presentation.util;

import com.midnightdraft.poemofthedamned.App;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BindLocalTime {

  public Timeline setupCurrentTime(Label timeLabel, Locale locale) {
    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
        .withLocale(locale);
    timeLabel.setText(LocalTime.now().format(formatter));

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), _ ->
        timeLabel.setText(LocalTime.now().format(formatter))));

    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();

    return timeline;
  }
}
