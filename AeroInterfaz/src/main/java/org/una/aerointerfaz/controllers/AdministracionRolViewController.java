/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.una.aerointerfaz.dtos.RolDTO;
import org.una.aerointerfaz.services.RolServiceImplementation;
import org.una.aerointerfaz.utils.FlowController;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

/**
 * FXML Controller class
 *
 * @author erikg
 */
public class AdministracionRolViewController extends Controller implements Initializable {
    
    @FXML
    private TableView<RolDTO> tvRoles;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnActualizar;
    @FXML
    private JFXTextField txtId;
    @FXML
    private TableColumn<RolDTO, Long> tcId;
    @FXML
    private TableColumn<RolDTO, String> tcNombre;
    @FXML
    private TableColumn<RolDTO, String> tcEstado;
    @FXML
    private TableColumn<RolDTO, String> tcCodigo;
    @FXML
    private TableColumn<RolDTO, String> tcDescripcion;

    final ObservableList<RolDTO> roles = FXCollections.observableArrayList();

    private final RolServiceImplementation serviceRol = new RolServiceImplementation();
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
        cargarRoles();
    }
    
    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("RolView", this.getStage(), Boolean.FALSE);
    }
    
    @FXML
    private void onActionButtonBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonActualizar(ActionEvent event){
        cargarRoles();
    }

    private void cargarRoles() {

        Respuesta respuesta = serviceRol.ObtenerRoles();
        if (respuesta.getEstado()) {
            roles.removeAll(roles);
            tvRoles.getItems().clear();

            roles.addAll((List<RolDTO>) respuesta.getResultado("Roles"));

            tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tcEstado.setCellValueFactory(cellData -> {
                   SimpleStringProperty property = new SimpleStringProperty();
                   
                    if (cellData.getValue().isEstado() == true) {
                       property.setValue( "Activo" );
                  }else{
                   property.setValue("Inactivo");
                   }
                 return property;
            });

            tvRoles.refresh();
            tvRoles.getItems().addAll(roles);
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "Administrando roles", "Error al obtener los roles.");

        }
    }

    @FXML
    private void onActionButtonInactivar(ActionEvent event) {
    }
}