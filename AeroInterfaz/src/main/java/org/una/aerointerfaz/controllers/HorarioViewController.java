/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
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
public class HorarioViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField textFieldID;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField textFieldNombre;
    @FXML
    private JFXCheckBox cbEstado;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnCrear;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXTextField textFieldCodigo;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXComboBox<?> cbxDiaEntrada;
    @FXML
    private JFXTimePicker tpHoraEntrada;
    @FXML
    private JFXComboBox<?> cbxDiaSalida;
    @FXML
    private JFXTimePicker tpHoraSalida;

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

    @FXML
    private void OnActionDiaEntrada(ActionEvent event) {
    }

    @FXML
    private void OnActionHoraEntrada(ActionEvent event) {
    }

    @FXML
    private void OnActionDiaSalida(ActionEvent event) {
    }

    @FXML
    private void OnActionHoraSalida(ActionEvent event) {
    }
    
}
