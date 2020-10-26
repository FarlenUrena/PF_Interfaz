/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.una.aerointerfaz.utils.FlowController;
import org.una.aerointerfaz.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author farle_000
 */
public class LoginViewController extends Controller implements Initializable  {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtCedula;
    @FXML
    private JFXPasswordField psswPassword;
    @FXML
    private JFXButton btnConsultar;
    @FXML
    private JFXButton btnIngresar;
    @FXML
    private JFXTextField textFieldMonto;
    @FXML
    private ChoiceBox<?> cbDivisaOrigen;
    @FXML
    private ChoiceBox<?> cbDivisaDestino;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void onActionButtonConsultar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonIngresar(ActionEvent event) {
        try{
        if(txtCedula.getText()== null || txtCedula.getText().isEmpty()){
        new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de Usuario", (Stage) txtCedula.getScene().getWindow(), "Es necesario digitar una cedula para ingresar al sistema.");
        } else if (psswPassword.getText() == null || psswPassword.getText().isEmpty()){
        new Mensaje().showModal(Alert.AlertType.ERROR, "Validación Incorrecta", (Stage) psswPassword.getScene().getWindow(), "Es necesario digitar una contraseña para ingresar al sistema.");
        }else{
        FlowController.getInstance().goMain();
        ((Stage) btnIngresar.getScene().getWindow()).close();
        }
        
        } catch (Exception ex){
            
        }
    }

    @Override
    public void initialize() {
    
    }
    
}
