/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author thony
 */
public class AlertaViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField textFieldNombre;
    @FXML
    private JFXTextField textFieldCedula;
    @FXML
    private JFXCheckBox cbEstado;
    @FXML
    private ToggleGroup tggGrpReceptor;
    @FXML
    private JFXComboBox<?> cbxAreaTrabajo;
    @FXML
    private JFXComboBox<?> cbxEmpleado;
    @FXML
    private JFXTextField txtAsunto;
    @FXML
    private JFXTextArea txtArDescipcion;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXButton btnEnviar;

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
    private void onActionButtonSalir(ActionEvent event) {
    }

    @FXML
    private void onActionButtonEnviar(ActionEvent event) {
    }

    
}
