/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author thony
 */
public class EmpleadoViewController implements Initializable {

    @FXML
    private JFXTextField textFieldID;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField textFieldNombre;
    @FXML
    private JFXTextField textFieldCedula;
    @FXML
    private JFXDatePicker dpFechaRegistro;
    @FXML
    private JFXDatePicker dpFechaModificacion;
    @FXML
    private JFXCheckBox cbEstado;
    @FXML
    private JFXCheckBox cbEsJefe;
    @FXML
    private JFXCheckBox cbEsUsuario;
    @FXML
    private JFXPasswordField jfxPassword;
    @FXML
    private ComboBox<?> cbRol;
    @FXML
    private ComboBox<?> cbAreaDeTrabajo;
    @FXML
    private JFXButton btnHorario;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnCrear;
    @FXML
    private JFXButton btnNuevo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
