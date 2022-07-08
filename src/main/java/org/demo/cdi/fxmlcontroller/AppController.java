package org.demo.cdi.fxmlcontroller;

import javafx.fxml.FXML;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController extends AbstractFxController {

    @FXML
    private Text welcomeText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @PostConstruct
    public void onStartup(@Observes final ContainerInitialized event) {
        this.getStage().show();
    }

    public void setWelcomeText(String welcomeText) {
        this.welcomeText.setText(welcomeText);
        this.welcomeText.setFill(Paint.valueOf("red"));
    }
}
