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
import org.una.aerointerfaz.services.AreaTrabajoServiceImplementation;
import org.una.aerointerfaz.dtos.AreaTrabajoDTO;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

/**
 * FXML Controller class
 *
 * @author thony
 */
public class AreaTrabajoViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField textFieldId;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXTextField textFieldNombre;
    @FXML
    private JFXTextField textFieldCodigo;
    @FXML
    private JFXCheckBox cbEstado;
    @FXML
    private JFXTextArea txtArDescripcion;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnCrear;
    @FXML
    private JFXButton btnSalir;

    private List<Node> requeridos = new ArrayList<>();

    private final AreaTrabajoServiceImplementation serviceAreaTrabajo = new AreaTrabajoServiceImplementation();

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
                AreaTrabajoDTO areaTrabajo = new AreaTrabajoDTO();
                nuevaAreaTrabajo(areaTrabajo);
                Respuesta respuesta = serviceAreaTrabajo.CrearAreaTrabajo(areaTrabajo);
                if (respuesta.getEstado()) {
                    new Mensaje().show(Alert.AlertType.INFORMATION, "Administrando áreas de trabajo", "Área de trabajo añadida con éxito.");

                } else {
                    new Mensaje().show(Alert.AlertType.ERROR, "Administrando áreas de trabajo", "Error al crear el área de trabajo.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoViewController.class.getName()).log(Level.SEVERE, "Error creando el área de trabajo", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Crear área de trabajo", getStage(), "Ocurrió un error al crear el área de trabajo.");
        }
    }
    
    @FXML
    private void onActionButtonBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonModificar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonSalir(ActionEvent event) {
    }

    private void nuevaAreaTrabajo(AreaTrabajoDTO areaTrabajo) {
        areaTrabajo.setNombre(textFieldNombre.getText());
        areaTrabajo.setCodigo(textFieldCodigo.getText());
        areaTrabajo.setEstado(cbEstado.isSelected());
        areaTrabajo.setDescripcion(txtArDescripcion.getText());
    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(textFieldNombre, textFieldCodigo, txtArDescripcion));
    }

    private void limpiarCampos() {
        textFieldId.setText("");
        textFieldNombre.setText("");
        textFieldCodigo.setText("");
        cbEstado.setSelected(true);
        txtArDescripcion.setText("");
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
}