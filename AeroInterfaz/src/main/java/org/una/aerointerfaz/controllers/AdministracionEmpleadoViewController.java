/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.una.aerointerfaz.utils.FlowController;
import org.una.aerointerfaz.dtos.EmpleadoDTO;
import org.una.aerointerfaz.dtos.HorarioDTO;
import org.una.aerointerfaz.dtos.RolDTO;
import org.una.aerointerfaz.dtos.AreaTrabajoDTO;
import org.una.aerointerfaz.services.EmpleadoServiceImplementation;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

/**
 * FXML Controller class
 *
 * @author erikg
 */
public class AdministracionEmpleadoViewController extends Controller implements Initializable {
    
    @FXML
    private TableView<EmpleadoDTO> tvEmpleados;
    @FXML
    private TableColumn<EmpleadoDTO, Long> tcId;
    @FXML
    private TableColumn<EmpleadoDTO, String> tcCedula;
    @FXML
    private TableColumn<EmpleadoDTO, String> tcNombre;
    @FXML
    private TableColumn<EmpleadoDTO, Boolean> tcEstado;
    @FXML
    private TableColumn<EmpleadoDTO, Date> tcFRegistro;
    @FXML
    private TableColumn<EmpleadoDTO, Date> tcFModificacion;
    @FXML
    private TableColumn<EmpleadoDTO, String> tcRol;
    @FXML
    private TableColumn<EmpleadoDTO, String> tcHorario;
    @FXML
    private TableColumn<EmpleadoDTO, String> tcAreaTrabajo;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnActualizar;

    final ObservableList<EmpleadoDTO> empleados = FXCollections.observableArrayList();

    private final EmpleadoServiceImplementation serviceEmpleado = new EmpleadoServiceImplementation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void initialize() {
        cargarEmpleados();
    }
    
    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("EmpleadoView", this.getStage(), Boolean.FALSE);
    }
    
    @FXML
    private void onActionButtonBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonActualizar(ActionEvent event) {
    }

    private void cargarEmpleados() {
        
        Respuesta respuesta = serviceEmpleado.ObtenerEmpleados();
        if (respuesta.getEstado()) {
            empleados.removeAll(empleados);
            tvEmpleados.getItems().clear();
            empleados.addAll((List<EmpleadoDTO>) respuesta.getResultado("Empleados"));
            tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
            tcCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
            tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
            tcFRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
            tcFModificacion.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
            tcRol.setCellValueFactory(
                cellData -> {
                   SimpleStringProperty property = new SimpleStringProperty();
                   if(cellData.getValue().getRol() != null){
                    if (cellData.getValue().getRol().getNombre() != null) {
                       property.setValue(cellData.getValue().getRol().getNombre());
                  }}else{
                   property.setValue("-");
                   }
                 return property;
            });	 
            
            tcAreaTrabajo.setCellValueFactory(
                cellData -> {
                   SimpleStringProperty property = new SimpleStringProperty();
                   if(cellData.getValue().getAreaTrabajo() != null){
                    if (cellData.getValue().getAreaTrabajo().getNombre() != null) {
                       property.setValue(cellData.getValue().getAreaTrabajo().getNombre());
                  }}else{
                   property.setValue("-");
                   }
                 return property;
            });	    
            
            tcHorario.setCellValueFactory(
                cellData -> {
                   SimpleStringProperty property = new SimpleStringProperty();
                   if(cellData.getValue().getHorario() != null){
                    if (cellData.getValue().getHorario().getId() != null) {
                       property.setValue(cellData.getValue().getHorario().getDiaEntrada() + "/"
                               + cellData.getValue().getHorario().getDiaSalida());
                  }}else{
                   property.setValue("-");
                   }
                 return property;
            });	        
            
            tvEmpleados.refresh();
            tvEmpleados.getItems().addAll(empleados);
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "Administrando empleados", "Error al obtener los empleados.");
        }
    }
}