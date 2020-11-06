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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.una.aerointerfaz.utils.FlowController;
import org.una.aerointerfaz.dtos.AreaTrabajoDTO;
import org.una.aerointerfaz.dtos.EmpleadoDTO;
import org.una.aerointerfaz.services.AreaTrabajoServiceImplementation;
import org.una.aerointerfaz.services.EmpleadoServiceImplementation;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

/**
 * FXML Controller class
 *
 * @author erikg
 */
public class AdministracionAreaTrabajoViewController extends Controller implements Initializable {
    @FXML
    private TableView<AreaTrabajoDTO> tvAreasTrabajos;
    @FXML
    private TableColumn<AreaTrabajoDTO, Long> tcId;
    @FXML
    private TableColumn<AreaTrabajoDTO, String> tcNombre;
    @FXML
    private TableColumn<AreaTrabajoDTO, Boolean> tcEstado;
    @FXML
    private TableColumn<AreaTrabajoDTO, String> tcCodigo;
    @FXML
    private TableColumn<AreaTrabajoDTO, String> tcDescripcion;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnActualizar;
    @FXML
    private JFXButton btnNuevo;

    
    final ObservableList<AreaTrabajoDTO> areasTrabajos = FXCollections.observableArrayList();
    
    private final AreaTrabajoServiceImplementation serviceAreaTrabajo = new AreaTrabajoServiceImplementation();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionButtonBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonActualizar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("AreaTrabajoView", this.getStage(), Boolean.FALSE);
    }

    @Override
    public void initialize() {
        
        cargarAreasTrabajos();
    }
    
    
    private void cargarAreasTrabajos(){
       
        Respuesta respuesta = serviceAreaTrabajo.ObtenerAreasTrabajo();
            if (respuesta.getEstado()) {
                areasTrabajos.removeAll(areasTrabajos);
                tvAreasTrabajos.getItems().clear();
                
                areasTrabajos.addAll((List<AreaTrabajoDTO>)respuesta.getResultado("AreasTrabajos"));
                
                tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
                tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
                tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
                tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
//                tcFRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
//                tcFModificacion.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
                
                tvAreasTrabajos.refresh();
                tvAreasTrabajos.getItems().addAll(areasTrabajos);
                }else {
            new Mensaje().show(Alert.AlertType.ERROR, "Administrando empleados", "Error al obtener los empleados.");
            }
   } 
}
