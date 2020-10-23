package org.una.aerointerfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import org.una.aerointerfaz.utils.FlowController;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
       FlowController.getInstance().InitializeFlow(stage, null);
       stage.setTitle("Aeropuerto UNA");   
       FlowController.getInstance().goViewInWindow("LoginView");
    }

    public static void main(String[] args) {
        launch();
    }

}