package org.demo.cdi.event;

import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationStartupEvent {
    private Stage primaryStage;

    public ApplicationStartupEvent(Stage primaryStage) {

        this.primaryStage = primaryStage;
    }
}
