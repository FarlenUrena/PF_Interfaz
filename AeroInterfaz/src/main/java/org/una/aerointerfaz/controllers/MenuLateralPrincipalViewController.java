/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.una.aerointerfaz.utils.FlowController;

/**
 * FXML Controller class
 *
 * @author farle_000
 */
public class MenuLateralPrincipalViewController extends Controller implements Initializable {

    @FXML
    private JFXButton btnAlertas;
    @FXML
    private JFXButton btnAreasTrabajo;
    @FXML
    private JFXButton btnEmpleados;
    @FXML
    private JFXButton btnHorarios;
    @FXML
    private JFXButton btnParametros;
    @FXML
    private JFXButton btnRoles;
    @FXML
    private JFXButton btnCerrarSesión;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionButtonAlertas(ActionEvent event) {
        FlowController.getInstance().goView("AdministracionAlertaView");
    }

    @FXML
    private void onActionButtonAreasTrabajo(ActionEvent event) {
        FlowController.getInstance().goView("AdministracionAreaTrabajoView");
    }

    @FXML
    private void onActionButtonEmpleados(ActionEvent event) {
        FlowController.getInstance().goView("AdministracionEmpleadoView");
    }

    @FXML
    private void onActionButtonHorarios(ActionEvent event) {
        FlowController.getInstance().goView("AdministracionHorarioView");
    }


    @FXML
    private void onActionButtonParametros(ActionEvent event) {
        FlowController.getInstance().goView("AdministracionParametroGeneralView");
    }

    @FXML
    private void onActionButtonRoles(ActionEvent event) {
        FlowController.getInstance().goView("AdministracionRolView");
    }

    @FXML
    private void onActionButtonCerrarSesión(ActionEvent event) {
        FlowController.getInstance().goView("LoginView");
    }

    @Override
    public void initialize() {
    
    }
}