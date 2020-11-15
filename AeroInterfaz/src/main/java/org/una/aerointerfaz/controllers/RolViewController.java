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
import org.una.aerointerfaz.dtos.RolDTO;
import org.una.aerointerfaz.services.RolServiceImplementation;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

/**
 * FXML Controller class
 *
 * @author thony
 */
public class RolViewController extends Controller implements Initializable {

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
    private JFXButton btnCrear;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXTextArea txtArDescipcion;
    @FXML
    private JFXButton btnSalir;
    
    private List<Node> requeridos = new ArrayList<>();
    
    private final RolServiceImplementation service = new RolServiceImplementation();
    @FXML
    private JFXButton btnInactivar;

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
    private void onActionButtonBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonModificar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonCrear(ActionEvent event) {
        try {
            if (validacionFinal()) {
                RolDTO rol = new RolDTO();
                nuevoRol(rol);
                Respuesta respuesta = service.CrearRol(rol);
                if (respuesta.getEstado()) {
                    new Mensaje().show(Alert.AlertType.INFORMATION, "Administrando roles", "Rol añadido con éxito.");

                } else {
                    new Mensaje().show(Alert.AlertType.ERROR, "Administrando roles", "Error al crear el rol.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoViewController.class.getName()).log(Level.SEVERE, "Error creando el rol", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Crear rol", getStage(), "Ocurrió un error al crear el rol.");
        }
    }

    @FXML
    private void onActionButtonInactivar(ActionEvent event) {
        
    }
    
    @FXML
    private void onActionButtonSalir(ActionEvent event) {
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
        requeridos.addAll(Arrays.asList(textFieldNombre, textFieldCedula, txtArDescipcion));
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

    private void nuevoRol(RolDTO rol) {
        rol.setNombre(textFieldNombre.getText());
        rol.setCodigo(textFieldCedula.getText());
        rol.setDescripcion(txtArDescipcion.getText());
    }

    private void limpiarCampos() {
        textFieldNombre.setText("");
        textFieldCedula.setText("");
        txtArDescipcion.setText("");
    }
}