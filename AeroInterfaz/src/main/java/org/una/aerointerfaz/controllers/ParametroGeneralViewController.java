/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import static org.glassfish.jersey.internal.inject.Bindings.service;
import org.una.aerointerfaz.dtos.EmpleadoDTO;
import org.una.aerointerfaz.dtos.ParametroGeneralDTO;
import org.una.aerointerfaz.services.EmpleadoServiceImplementation;
import org.una.aerointerfaz.services.ParametroGeneralServiceImplementation;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

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
    private JFXTextField textFieldValor;
    @FXML
    private JFXCheckBox cbEstado;
    @FXML
    private JFXButton btnCrear;
    @FXML
    private JFXTextArea txtArDescripcion;
    
    private List<Node> requeridos = new ArrayList<>();
    
    private final ParametroGeneralServiceImplementation service = new ParametroGeneralServiceImplementation();
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
        limpiarCampos();
    }
    
    @FXML
    private void onActionButtonCrear(ActionEvent event) {
    try {
            if (validacionFinal()) {
                ParametroGeneralDTO parametroGeneral = new ParametroGeneralDTO();
                nuevoParametroGeneral(parametroGeneral);
                Respuesta respuesta = service.CrearParametroGeneral(parametroGeneral);
                if (respuesta.getEstado()) {
                    new Mensaje().show(Alert.AlertType.INFORMATION, "Administrando parámetros generales", "Parámetro general creado con éxito.");

                } else {
                    new Mensaje().show(Alert.AlertType.ERROR, "Administrando parámetros generales", "Error al crear el parámetro general.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ParametroGeneralViewController.class.getName()).log(Level.SEVERE, "Error creando el parámetro general", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Crear parámetro general", getStage(), "Ocurrió un error al crear el parámetro general.");
        }
    }
    

    @FXML
    private void onActionButtonBuscar(ActionEvent event) {
        
    }
    
    
    
    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXTextField && ((JFXTextField) node).getText().isEmpty()) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && ((JFXPasswordField) node).getText().isBlank()) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "campos requeridos o con problemas de formato[" + invalidos + "].";
        }
    }
    
    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(textFieldNombre, textFieldValor, txtArDescripcion));
    }
    
    private boolean validacionFinal() {
        indicarRequeridos();
        if (validarRequeridos() == "") {
            System.out.println(validarRequeridos());
            return true;
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "Error", "Ocurrió un error: " + validarRequeridos() + " Verifica los campos e inténtalo nuevamente.");
            return false;
        }
    }
    
    private void nuevoParametroGeneral(ParametroGeneralDTO parametroGeneral) {
        parametroGeneral.setNombre(textFieldNombre.getText());
        parametroGeneral.setDescripcion(txtArDescripcion.getText());
        parametroGeneral.setValor(textFieldValor.getText());    
    }
    
    private void limpiarCampos() {
        textFieldID.setText("");
        textFieldNombre.setText("");
        textFieldValor.setText("");
        txtArDescripcion.setText("");
        cbEstado.setSelected(true);
    }

    @FXML
    private void onActionEstado(ActionEvent event) {
    }

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonCerrar(ActionEvent event) {
        this.getStage().close();
    }
}