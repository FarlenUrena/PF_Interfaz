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
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import javafx.scene.control.Alert.AlertType;
import org.una.aerointerfaz.dtos.EmpleadoDTO;
import org.una.aerointerfaz.dtos.RolDTO;
import org.una.aerointerfaz.dtos.AreaTrabajoDTO;
import org.una.aerointerfaz.services.AreaTrabajoServiceImplementation;
import org.una.aerointerfaz.services.EmpleadoServiceImplementation;
import org.una.aerointerfaz.services.RolServiceImplementation;
import org.una.aerointerfaz.utils.FlowController;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;
import org.una.aerointerfaz.utils.VolverPantalla;

/**
 * FXML Controller class
 *
 * @author thony
 */
public class EmpleadoViewController extends Controller implements Initializable {

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
    private JFXCheckBox cbEsJefe;
    @FXML
    private JFXCheckBox cbEsUsuario;
    @FXML
    private JFXPasswordField jfxPassword;
    @FXML
    private JFXComboBox<AreaTrabajoDTO> cbAreaDeTrabajo;
    @FXML
    private JFXComboBox<RolDTO> cbRol;
    @FXML
    private JFXButton btnCrear;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnCrearHorario;
    @FXML
    private JFXButton btnSalir;

    private List<Node> requeridos = new ArrayList<>();

    private final EmpleadoServiceImplementation service = new EmpleadoServiceImplementation();
    private final AreaTrabajoServiceImplementation serviceAreaTrabajo = new AreaTrabajoServiceImplementation();
    private final RolServiceImplementation serviceRol = new RolServiceImplementation();
    
    VolverPantalla volverPantalla = new VolverPantalla();

    ArrayList<AreaTrabajoDTO> areasTrabajos = new ArrayList();
    ArrayList<RolDTO> roles = new ArrayList();
    @FXML
    private JFXButton btnInactivar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @Override
    public void initialize() {
        limpiarCampos();
        cargarDatos();
    }
    
    @FXML
    private void onActionButtonCrear(ActionEvent event) {
        try {
            if (validacionFinal()) {
                EmpleadoDTO empleado = new EmpleadoDTO();
                nuevoEmpeleado(empleado);
                Respuesta respuesta = service.CrearEmpleado(empleado);
                if (respuesta.getEstado()) {
                    new Mensaje().show(Alert.AlertType.INFORMATION, "Administrando empleados", "Empleado creado con éxito.");

                } else {
                    new Mensaje().show(Alert.AlertType.ERROR, "Administrando empleados", "Error al crear el empleado.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoViewController.class.getName()).log(Level.SEVERE, "Error creando el empleado", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Crear empleado", getStage(), "Ocurrió un error al crear el empleado.");
        }
    }
    
    @FXML
    private void onActionButtonCrearHorario(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("HorarioView",this.getStage(),false);
    }
    
    @FXML
    private void onActionButtonBuscar(ActionEvent event) {
         try {
             if(!textFieldID.getText().isBlank()){
                 
                Respuesta respuesta = service.ObtenerEmpleado(Long.parseLong(textFieldID.getText()));
                if (respuesta.getEstado()) {
                    EmpleadoDTO empleado = new EmpleadoDTO() ;
                    empleado = ((EmpleadoDTO) respuesta.getResultado("Empleado"));
                    System.out.println(empleado);
                    cargarEmpleado(empleado); 
                    new Mensaje().show(Alert.AlertType.INFORMATION, "Cargando empleado", "Empleado cargado con éxito.");
                    
                } else {
                    new Mensaje().show(Alert.AlertType.ERROR, "Cargando empleados", "Error al cargar el empleado.");
                }
        }else{
        new Mensaje().show(Alert.AlertType.ERROR, "Cargando empleado", "Ingresa el id del empleado a cargar.");
             }
         } catch (Exception ex) {
            Logger.getLogger(EmpleadoViewController.class.getName()).log(Level.SEVERE, "Error cargando el empleado", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar empleado", getStage(), "Ocurrió un error al cargar el empleado.");
        }
        
        
    }
    
    @FXML
    private void onActionButtonModificar(ActionEvent event) {
    }
    
    private void onActionButtonNuevo(ActionEvent event) {
        limpiarCampos();
    }
    
    
    @FXML
    private void onActionEsUsuario(ActionEvent event) {
        validarUsuario();
    }
    
    @FXML
    private void onActionButtonInactivar(ActionEvent event) {
        
    }
    
    @FXML
    private void onActionButtonSalir(ActionEvent event) {
    }
    
    private void nuevoEmpeleado(EmpleadoDTO empleado) {
        empleado.setNombreCompleto(textFieldNombre.getText());
        empleado.setCedula(textFieldCedula.getText());
        empleado.setEstado(cbEstado.isSelected());
        
        if(cbEsJefe.isSelected()){
           empleado.setJefe(true);
        }else{ empleado.setJefe(false); }
        if(cbEsUsuario.isSelected()){
        empleado.setUsuario(true);
        }else{ empleado.setUsuario(false); }
        
        empleado.setPasswordEncriptado(jfxPassword.getText());
        empleado.setRol(cbRol.getValue());
        empleado.setAreaTrabajo(cbAreaDeTrabajo.getValue());
        System.out.println(empleado);
    }
    
    private void cargarEmpleado(EmpleadoDTO empleado){
        
      
        System.out.println(empleado);
        
        textFieldNombre.setText(empleado.getNombreCompleto());
        textFieldCedula.setText(empleado.getCedula());
       
        cargarCbx(empleado.isEstado(),empleado.isJefe(),empleado.isUsuario(),empleado.getRol(),empleado.getAreaTrabajo());        
        
        
        jfxPassword.setText(empleado.getPasswordEncriptado());
        //jfxPassword.setDisable(true);
       
        
        
    }

    private void cargarCbx(boolean isEstado, boolean isJefe, boolean isUsuario, RolDTO rol,AreaTrabajoDTO areaTrabajo){
        cbEstado.setSelected(isEstado);
        cbEsJefe.setSelected(isJefe);
        cbEsUsuario.setSelected(isUsuario);
        cbRol.setValue(rol);
        cbAreaDeTrabajo.setValue(areaTrabajo);
        
        validarUsuario();
        
    }
    
    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(textFieldNombre, textFieldCedula, cbAreaDeTrabajo));
        validarUsuario();
    }

    private void limpiarCampos() {
        textFieldID.setText("");
        textFieldNombre.setText("");
        textFieldCedula.setText("");
        cbEstado.setSelected(true);
        cbEsJefe.setSelected(false);
        cbEsUsuario.setSelected(false);
        validarUsuario();
        jfxPassword.setText("");
        cbRol.setValue(null);
        cbAreaDeTrabajo.setValue(null);
    }

    private void cargarDatos() {
        cbRol.getItems().clear();
        cbAreaDeTrabajo.getItems().clear();
        cargarRoles();
        cargarAreasTrabajo();
        cbRol.getItems().addAll(roles);
        cbAreaDeTrabajo.getItems().addAll(areasTrabajos);
    }

    private void cargarRoles() {
        Respuesta respuesta = serviceRol.ObtenerRoles();
        if (respuesta.getEstado()) {
            roles.clear();
            roles.addAll((List<RolDTO>) respuesta.getResultado("Roles"));
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "Administrando roles", "Error al obtener los roles.");
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
    
    void validarUsuario() {
        if (cbEsUsuario.isSelected()) {
            requeridos.addAll(Arrays.asList(jfxPassword, cbRol));
            jfxPassword.setDisable(false);
            cbRol.setDisable(false);

        } else {
            requeridos.removeAll(Arrays.asList(jfxPassword, cbRol));
            jfxPassword.validate();
            cbRol.validate();
            jfxPassword.clear();
            jfxPassword.setDisable(true);
            cbRol.setValue(null);
            cbRol.setDisable(true);
        }
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
            new Mensaje().show(AlertType.ERROR, "Error", "Ocurrió un error: " + validarRequeridos() + " Verifica los campos e inténtalo nuevamente.");
            return false;
        }
    }
}