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
public class HoraMarcajeViewController extends Controller implements Initializable {
    
    @FXML
    private JFXTextField lbNombre;
    @FXML
    private JFXTextField lbCedula;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private JFXButton btnMarcarEntrada;
    @FXML
    private JFXButton btnMarcarSalida;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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