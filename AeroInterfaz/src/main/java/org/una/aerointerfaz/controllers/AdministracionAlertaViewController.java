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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.una.aerointerfaz.dtos.AlertaDTO;
import org.una.aerointerfaz.services.AlertaServiceImplementation;
import org.una.aerointerfaz.utils.FlowController;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

/**
 * FXML Controller class
 *
 * @author erikg
 */
public class AdministracionAlertaViewController extends Controller implements Initializable {

    @FXML
    private TableView<AlertaDTO> tvAlertas;
    @FXML
    private TableColumn<AlertaDTO, Long> tcId;
    @FXML
    private TableColumn<AlertaDTO, String> tcEmisor;
    @FXML
    private TableColumn<AlertaDTO, String> tcReceptor;
    @FXML
    private TableColumn<AlertaDTO, String> tcMensaje;
    @FXML
    private TableColumn<AlertaDTO, String> tcAsunto;
    @FXML
    private TableColumn<AlertaDTO, String> tcFRegistro;
    @FXML
    private TableColumn<AlertaDTO, String> tcFModificacion;
    @FXML
    private TableColumn<AlertaDTO, String> tcFLectura;
    @FXML
    private TableColumn<AlertaDTO, String> tcEstado;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnActualizar;

    final ObservableList<AlertaDTO> alertas = FXCollections.observableArrayList();

    private final AlertaServiceImplementation serviceAlerta = new AlertaServiceImplementation();

    private SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
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
        cargarAlertas();
    }

    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("AlertaView", this.getStage(), Boolean.FALSE);
    }

    @FXML
    private void onActionButtonBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonActualizar(ActionEvent event) {
        cargarAlertas();
    }
    
    private void cargarAlertas() {

        Respuesta respuesta = serviceAlerta.ObtenerAlertas();
        if (respuesta.getEstado()) {
            alertas.removeAll(alertas);
            tvAlertas.getItems().clear();
            alertas.addAll((List<AlertaDTO>) respuesta.getResultado("Alertas"));
            tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcEmisor.setCellValueFactory(new PropertyValueFactory<>("emisor"));
            tcReceptor.setCellValueFactory(new PropertyValueFactory<>("receptor"));
            tcMensaje.setCellValueFactory(new PropertyValueFactory<>("mensaje"));
            tcAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
            tcEstado.setCellValueFactory(cellData -> {
                   SimpleStringProperty property = new SimpleStringProperty();
                   
                    if (cellData.getValue().isEstado() == true) {
                       property.setValue( "Activa" );
                  }else{
                   property.setValue("Inactiva");
                   }
                 return property;
            });
            tcFRegistro.setCellValueFactory((param) -> new SimpleObjectProperty(form.format(param.getValue().getFechaRegistro())));
            tcFModificacion.setCellValueFactory(param -> new SimpleObjectProperty(form.format(param.getValue().getFechaModificacion())));
            tcFLectura.setCellValueFactory(cellData -> {
                   SimpleObjectProperty property = new SimpleObjectProperty();
                   
                    if (cellData.getValue().getFechaLectura() != null) {
                       property.setValue(form.format(cellData.getValue().getFechaLectura()));
                  }else{
                   property.setValue("-");
                   }
                 return property;
            });
            
            
            tvAlertas.refresh();
            tvAlertas.getItems().addAll(alertas);
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "Administrando alertas", "Error al obtener los alertas.");
        }
    }

    @FXML
    private void onActionButtonInactivar(ActionEvent event) {
    }

}
