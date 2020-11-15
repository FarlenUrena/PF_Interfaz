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
import org.una.aerointerfaz.dtos.AreaTrabajoDTO;
import org.una.aerointerfaz.dtos.HorarioDTO;
import org.una.aerointerfaz.services.HorarioServiceImplementation;
import org.una.aerointerfaz.utils.FlowController;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

/**
 * FXML Controller class
 *
 * @author erikg
 */
public class AdministracionHorarioViewController extends Controller implements Initializable {
    
    @FXML
    private TableView<HorarioDTO> tvHorarios;   
    @FXML
    private TableColumn<HorarioDTO, Long> tcID;
    @FXML
    private TableColumn<HorarioDTO, Date> tcDiaEntrada;
    @FXML
    private TableColumn<HorarioDTO, Date> tcDiaSalida;
    @FXML
    private TableColumn<HorarioDTO, String> tcHoraEntrada;
    @FXML
    private TableColumn<HorarioDTO, String> tcHoraSalida;
    @FXML
    private JFXTextField txtId; 
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnActualizar;
    
    final ObservableList<HorarioDTO> horarios = FXCollections.observableArrayList();

    private final HorarioServiceImplementation serviceHorario = new HorarioServiceImplementation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void initialize() {
        cargarHorarios();
    }
    
    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("HorarioView", this.getStage(), Boolean.FALSE);
    }
    
    @FXML
    private void onActionButtonBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonActualizar(ActionEvent event) {
    }
    
    private void cargarHorarios() {

        Respuesta respuesta = serviceHorario.ObtenerHorario();
        if (respuesta.getEstado()) {
            horarios.removeAll(horarios);
            tvHorarios.getItems().clear();
            horarios.addAll((List<HorarioDTO>) respuesta.getResultado("Horarios"));
            tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcDiaEntrada.setCellValueFactory(new PropertyValueFactory<>("día entrada"));
            tcDiaSalida.setCellValueFactory(new PropertyValueFactory<>("día salida"));
            tcHoraEntrada.setCellValueFactory(new PropertyValueFactory<>("hora entrada"));
            tcHoraSalida.setCellValueFactory(new PropertyValueFactory<>("hora salida"));
            tvHorarios.refresh();
            tvHorarios.getItems().addAll(horarios);
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "Administrando horarios", "Error al obtener los horarios.");
        }
    }
}