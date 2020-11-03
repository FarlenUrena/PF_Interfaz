/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
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
public class ParametroGeneralViewController extends Controller implements Initializable  {

    @FXML
    private JFXTextField textFieldID;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField textFieldNombre;
    @FXML
    private JFXTextField textFieldCedula;
    @FXML
    private JFXCheckBox cbEstado;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnCrear;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXTextArea txtArDescipcion;
    @FXML
    private JFXButton btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
        
    }

    @FXML
    private void onActionButtonBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonModificar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonCrear(ActionEvent event) {
    }

    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
    }

    @FXML
    private void onActionButtonSalir(ActionEvent event) {
    }
    
}
