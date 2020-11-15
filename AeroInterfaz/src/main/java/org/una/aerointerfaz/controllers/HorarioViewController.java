/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
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
import org.una.aerointerfaz.services.HorarioServiceImplementation;
import org.una.aerointerfaz.dtos.HorarioDTO;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

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
    private JFXTextField textFieldHoraEntrada;
    @FXML
    private JFXTextField textFieldHoraSalida;
    @FXML
    private JFXButton btnCrear;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnCambiarEstado;
    @FXML
    private JFXButton btnSalir;

    private List<Node> requeridos = new ArrayList<>();

    private final HorarioServiceImplementation service = new HorarioServiceImplementation();

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
                HorarioDTO horario = new HorarioDTO();
                nuevoHorario(horario);
                Respuesta respuesta = service.CrearHorario(horario);
                if (respuesta.getEstado()) {
                    new Mensaje().show(Alert.AlertType.INFORMATION, "Administrando horarios", "Horario creado con éxito.");

                } else {
                    new Mensaje().show(Alert.AlertType.ERROR, "Administrando horarios", "Error al crear el horario.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(HorarioViewController.class.getName()).log(Level.SEVERE, "Error creando el horario", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Crear horario", getStage(), "Ocurrió un error al crear el horario.");
        }
    }

    @FXML
    private void onActionButtonBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonModificar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonCambiarEstado(ActionEvent event) {
    }

    @FXML
    private void onActionButtonSalir(ActionEvent event) {
    }

    private void nuevoHorario(HorarioDTO horario) {
        horario.setHoraEntrada(textFieldHoraEntrada.getText());
        // horario.setDiaEntrada(datePickerDiaEntrada.());
        horario.setHoraSalida(textFieldHoraSalida.getText());
        // horario.setDiaSalida(datePickerDiaSalida.());
    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(textFieldHoraEntrada, textFieldHoraSalida));
    }
    
    private void limpiarCampos() {
        textFieldHoraEntrada.setText("");
        // datePickerDiaSalida.();
        textFieldHoraSalida.setText("");
        // datePickerDiaSalida.();
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