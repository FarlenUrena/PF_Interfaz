/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.una.aerointerfaz.utils.FlowController;

/**
 * FXML Controller class
 *
 * @author erikg
 */
public class AdministracionParametroGeneralViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnActualizar;
    @FXML
    private JFXButton btnNuevo;

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
        FlowController.getInstance().goViewInWindowModal("ParametroGeneralView", this.getStage(), Boolean.FALSE);
    }

    @Override
    public void initialize() {
       
    }
    
}
