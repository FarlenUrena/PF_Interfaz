/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author thony
 */
public class RegistroHoraMarcajeViewController implements Initializable {

    @FXML
    private JFXTextField lbNombre;
    @FXML
    private JFXTextField lbCedula;
    @FXML
    private JFXButton btnMarcarEntrada;
    @FXML
    private JFXButton btnMarcarSalida;
    @FXML
    private JFXButton btnVolver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnActionButtonMarcarEntrada(ActionEvent event) {
    }

    @FXML
    private void OnActionButtonMarcarSalida(ActionEvent event) {
    }

    @FXML
    private void OnActionButtonVolver(ActionEvent event) {
    }
    
}
