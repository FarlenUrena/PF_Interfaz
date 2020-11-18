/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
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
import org.una.aerointerfaz.dtos.ParametroGeneralDTO;
import org.una.aerointerfaz.services.ParametroGeneralServiceImplementation;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

/**
 * FXML Controller class
 *
 * @author erikg
 */
public class AdministracionParametroGeneralViewController extends Controller implements Initializable {

    @FXML
    private TableView<ParametroGeneralDTO> tvParametrosGenerales;
    @FXML
    private TableColumn<ParametroGeneralDTO, Long> tcID;
    @FXML
    private TableColumn<ParametroGeneralDTO, String> tcNombre;
    @FXML
    private TableColumn<ParametroGeneralDTO, String> tcValor;
    @FXML
    private TableColumn<ParametroGeneralDTO, String> tcDescripcion;
    @FXML
    private TableColumn<ParametroGeneralDTO, Date> tcFRegistro;
    @FXML
    private TableColumn<ParametroGeneralDTO, Date> tcFModificacion;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnActualizar;
    @FXML
    private JFXButton btnBuscar;

    final ObservableList<ParametroGeneralDTO> parametrosGenerales = FXCollections.observableArrayList();

    private final ParametroGeneralServiceImplementation serviceParametroGeneral = new ParametroGeneralServiceImplementation();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void initialize() {
        cargarParametrosGenerales();
    }

    @FXML
    private void onActionButtonBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonActualizar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("ParametroGeneralView", this.getStage(), Boolean.FALSE);
    }

    private void cargarParametrosGenerales() {

        Respuesta respuesta = serviceParametroGeneral.ObtenerParametroGeneral();
        if (respuesta.getEstado()) {
            parametrosGenerales.removeAll(parametrosGenerales);
            tvParametrosGenerales.getItems().clear();
            parametrosGenerales.addAll((List<ParametroGeneralDTO>) respuesta.getResultado("ParametrosGenerales"));
            tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
            tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tcFRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
            tcFModificacion.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
            tvParametrosGenerales.refresh();
            tvParametrosGenerales.getItems().addAll(parametrosGenerales);
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "Administrando parámetros generales", "Error al obtener los parámetros generales.");
        }
    }
}