/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author farle_000
 */
public class DashboardPrincipalViewController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private JFXDrawer drawerPrincipal;
    @FXML
    private JFXHamburger hbgPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     seleccionarPantalla();
    }    
    private void seleccionarPantalla() {
        try {
            VBox vbox = FXMLLoader.load(getClass().getResource("/org/una/aerointerfaz/views/MenuLateralPrincipalView.fxml"));
////         vbox.setPrefSize(drawer.getWidth(), drawer.getHeight());
//         drawer.setPrefSize(vbox.getWidth(), vbox.getHeight());
//            vbox.setStyle("-fx-background-color:#009999;");
drawerPrincipal.setSidePane(vbox);
//           drawer.getParent().setStyle("-fx-background-color:#009999;");
HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hbgPrincipal);
transition.setRate(-1);
hbgPrincipal.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
    transition.setRate(transition.getRate() * -1);
    
    transition.play();
    if(drawerPrincipal.isOpened()){
        drawerPrincipal.close();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(0.5),
                ae -> root.setLeft(null)));
        timeline.play();
    }
    else{
        drawerPrincipal.open();
        root.setLeft(drawerPrincipal);
    }
        });
        } catch (IOException ex) {
            Logger.getLogger(DashboardPrincipalViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      }
}
