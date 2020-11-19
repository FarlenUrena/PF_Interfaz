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
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.control.ToggleGroup;
import org.una.aerointerfaz.services.AreaTrabajoServiceImplementation;
import org.una.aerointerfaz.services.EmpleadoServiceImplementation;
import org.una.aerointerfaz.dtos.EmpleadoDTO;
import org.una.aerointerfaz.dtos.AlertaDTO;
import org.una.aerointerfaz.dtos.AreaTrabajoDTO;
import org.una.aerointerfaz.services.AlertaServiceImplementation;
import org.una.aerointerfaz.utils.AppContext;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

/**
 * FXML Controller class
 *
 * @author thony
 */
public class AlertaViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField textFieldEmisor;
    @FXML
    private JFXRadioButton rbAreaTrabajo;
    @FXML
    private JFXRadioButton tbEmpleado;
    @FXML
    private JFXTextField textFieldReceptor;
    @FXML
    private JFXTextField textFieldAsunto;
    @FXML
    private JFXTextArea txtArMensaje;
    @FXML
    private JFXCheckBox cbEstado;
    @FXML
    private ToggleGroup tggGrpReceptor;
    @FXML
    private JFXComboBox<AreaTrabajoDTO> cbxAreaTrabajo;
    @FXML
    private JFXComboBox<EmpleadoDTO> cbxEmpleado;
    @FXML
    private JFXButton btnEnviar;

    private List<Node> requeridos = new ArrayList<>();

    private final EmpleadoServiceImplementation serviceEmpleado = new EmpleadoServiceImplementation();
    private final AreaTrabajoServiceImplementation serviceAreaTrabajo = new AreaTrabajoServiceImplementation();
    private final AlertaServiceImplementation serviceAlerta = new AlertaServiceImplementation();

    ArrayList<AreaTrabajoDTO> areasTrabajos = new ArrayList();
    ArrayList<EmpleadoDTO> empleados = new ArrayList();
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
        cargarDatos();
        // cbxAreaTrabajo.setOnAction(e->System.out.println("Action nueva seleccion:"+cbxAreaTrabajo.getValue()));
    }

    @FXML
    private void onActionButtonEnviar(ActionEvent event) {
        try {
            if (validacionFinal()) {
                AlertaDTO alerta = new AlertaDTO();
                nuevaAlerta(alerta);
                Respuesta respuesta = serviceAlerta.CrearAlerta(alerta);
                System.out.println(respuesta.getMensajeInterno());
                if (respuesta.getEstado()) {
                    new Mensaje().show(Alert.AlertType.INFORMATION, "Administrando alertas", "Alerta enviada con éxito.");
                    limpiarCampos();
                } else {
                    new Mensaje().show(Alert.AlertType.ERROR, "Administrando alertas", "Error al enviar la alerta.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoViewController.class.getName()).log(Level.SEVERE, "Error enviando la alerta", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Enviar alerta", getStage(), "Ocurrió un error al enviar la alerta.");
        }
    }

 
    private void nuevaAlerta(AlertaDTO alerta) {
        alerta.setEmisor(textFieldEmisor.getText());
        alerta.setReceptor(textFieldReceptor.getText());
        alerta.setEstado(cbEstado.isSelected());
        alerta.setAsunto(textFieldAsunto.getText());
        alerta.setMensaje(txtArMensaje.getText());
        if (rbAreaTrabajo.isSelected() && tbEmpleado.isSelected()) {
            alerta.setReceptor(cbxEmpleado.getValue().getNombreCompleto());
        } else {
            alerta.setReceptor(cbxAreaTrabajo.getValue().getNombre());
        }
    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(textFieldEmisor, textFieldReceptor, textFieldAsunto, txtArMensaje)); //falta
        // validarEmpleado();
    }
    
    private void limpiarCampos() {
        textFieldEmisor.setText("");
        // textFieldAsunto.setText("");
        txtArMensaje.setText("");
        textFieldAsunto.setText("");
        cbEstado.setSelected(true);
        // validarEmpleado();
        // cbxEmpleado.setValue(null);
        cbxAreaTrabajo.setValue(null);
        textFieldReceptor.setText("");
    }

    private void cargarDatos() {
        textFieldEmisor.setEditable(false);
        textFieldEmisor.setText(AppContext.getInstance().get("NombreEmpleado").toString());
        cbxAreaTrabajo.getItems().clear();
        // cbxEmpleado.getItems().clear();
        // cargarEmpleados();
        cargarAreasTrabajo();
        cbxAreaTrabajo.valueProperty().addListener((o) -> {
            textFieldReceptor.setText(cbxAreaTrabajo.getValue().getNombre());
        });
        // cbxEmpleado.getItems().addAll(empleados);
        cbxAreaTrabajo.getItems().addAll(areasTrabajos);
    }

    private void cargarEmpleados() {
        Respuesta respuesta = serviceEmpleado.ObtenerEmpleados();
        if (respuesta.getEstado()) {
            empleados.addAll((List<EmpleadoDTO>) respuesta.getResultado("Empleados"));
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "Administrando Empleados", "Error al obtener los empleados.");
        }
    }

    private void cargarAreasTrabajo() {
        Respuesta respuesta = serviceAreaTrabajo.ObtenerAreaTrabajo();
        if (respuesta.getEstado()) {
            areasTrabajos.clear();
            areasTrabajos.addAll((List<AreaTrabajoDTO>) respuesta.getResultado("AreasTrabajos"));
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "Administrando áreas de trabajo", "Error al obtener las áreas de trabajo.");
        }
    }

    void validarEmpleado() {
        // if(tggGrpReceptor.getSelectedToggle() == tbEmpleado){
        // requeridos.addAll(Arrays.asList(cbxEmpleado));
        // cbxEmpleado.setDisable(false);
        // }else{
        // requeridos.removeAll(Arrays.asList(cbxEmpleado));
        // cbxEmpleado.validate();
        // cbxEmpleado.setDisable(true);
        // cbxEmpleado.setValue(null);
        // }
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

    @FXML
    private void onActionButtonCerrar(ActionEvent event) {
        this.getStage().close();
    }
}