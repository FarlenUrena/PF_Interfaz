/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.utils;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.una.aerointerfaz.App;

/**
 *
 * @author thony
 */
public class VolverPantalla {

    public void volverPantallaPrincipal(String ventana, ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(App.class.getResource(ventana + ".fxml"));
        Scene pantallaPrincipal = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(pantallaPrincipal);
        window.show();
    }
}