/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
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
import org.una.aerointerfaz.utils.FlowController;
import org.una.aerointerfaz.dtos.AreaTrabajoDTO;
import org.una.aerointerfaz.services.AreaTrabajoServiceImplementation;
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
    private TableColumn<AreaTrabajoDTO, String> tcEstado;
    @FXML
    private TableColumn<AreaTrabajoDTO, String> tcCodigo;
    @FXML
    private TableColumn<AreaTrabajoDTO, String> tcDescripcion;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnActualizar;
    @FXML
    private JFXButton btnInactivar;

    final ObservableList<AreaTrabajoDTO> areasTrabajos = FXCollections.observableArrayList();

    private final AreaTrabajoServiceImplementation serviceAreaTrabajo = new AreaTrabajoServiceImplementation();

    private SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {
        cargarAreasTrabajos();
    }

    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("AreaTrabajoView", this.getStage(), Boolean.FALSE);
    }

    @FXML
    private void onActionButtonBuscar(ActionEvent event) {
        
    }

    @FXML
    private void onActionButtonActualizar(ActionEvent event) {
        cargarAreasTrabajos();
    }

    private void cargarAreasTrabajos() {

        Respuesta respuesta = serviceAreaTrabajo.ObtenerAreaTrabajo();
        if (respuesta.getEstado()) {
            areasTrabajos.removeAll(areasTrabajos);
            tvAreasTrabajos.getItems().clear();
            areasTrabajos.addAll((List<AreaTrabajoDTO>) respuesta.getResultado("AreasTrabajos"));
            tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tcEstado.setCellValueFactory(cellData -> {
                   SimpleStringProperty property = new SimpleStringProperty();
                   
                    if (cellData.getValue().isEstado() == true) {
                       property.setValue( "Activa" );
                  }else{
                   property.setValue("Inactiva");
                   }
                 return property;
            });
            tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
                    
                    /*cellData -> {
                   SimpleStringProperty property = new SimpleStringProperty();
                   
                    if (cellData.getValue().getFechaModificacion() == null) {
                       property.setValue( "-" );
                  }else{
                   property.setValue(new SimpleObjectProperty(form.format(cellData.getValue().getFechaRegistro())).toString());
                   }
                 return property;
            });*/
            tvAreasTrabajos.refresh();
            tvAreasTrabajos.getItems().addAll(areasTrabajos);
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "Administrando áreas de trabajo", "Error al obtener los áreas de trabajo.");
        }
    }

    @FXML
    private void onActionButtonInactivar(ActionEvent event) {
    }
}
