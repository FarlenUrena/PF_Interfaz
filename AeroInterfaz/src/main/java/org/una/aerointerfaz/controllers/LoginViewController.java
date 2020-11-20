/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.una.aerointerfaz.services.AutenticacionServiceImplementation;
import org.una.aerointerfaz.utils.AppContext;
import org.una.aerointerfaz.utils.Autenticacion;
import org.una.aerointerfaz.utils.FlowController;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

/**
 * FXML Controller class
 *
 * @author farle_000
 */
public class LoginViewController extends Controller implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    private final AutenticacionServiceImplementation service = new AutenticacionServiceImplementation();
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imgViewFondo;
    @FXML
    private JFXRadioButton rbDolarOrigen;
    @FXML
    private ToggleGroup tggOrigen;
    @FXML
    private JFXRadioButton rbLibraOrigen;
    @FXML
    private JFXRadioButton rbYenOrigen;
    @FXML
    private JFXRadioButton rbEurodolarOrigen;
    @FXML
    private JFXRadioButton rbDolarCanadienseOrigen;
    @FXML
    private JFXRadioButton rbFrancoSuizoOrigen;
    @FXML
    private JFXRadioButton rbDolarNeozelandesOrigen;
    @FXML
    private JFXRadioButton rbDolarAustralianoOrigen;
    @FXML
    private JFXRadioButton rbColonOrigen;
    @FXML
    private JFXRadioButton rbDolarDestino;
    @FXML
    private ToggleGroup tggDestino;
    @FXML
    private JFXRadioButton rbLibraDestino;
    @FXML
    private JFXRadioButton rbYenDestino;
    @FXML
    private JFXRadioButton rbEurodolarDestino;
    @FXML
    private JFXRadioButton rbDolarCanadienseDestino;
    @FXML
    private JFXRadioButton rbFrancoSuizoDestino;
    @FXML
    private JFXRadioButton rbDolarNeozelandesDestino;
    @FXML
    private JFXRadioButton rbDolarAustralianoDestino;
    @FXML
    private JFXRadioButton rbColonDestino;
    @FXML
    private JFXButton btnAyuda;
    @FXML
    private Label lblMontoDolar;
    @FXML
    private Label lblMontoLibra;
    @FXML
    private Label lblMontoYen;
    @FXML
    private Label lblMontoEuro;
    @FXML
    private Label lblMontoDolarCanadiense;
    @FXML
    private Label lblMontoFrancoSuizo;
    @FXML
    private Label lblMontoDolarNeozelandes;
    @FXML
    private Label lblMontoDolarAustraliano;
    @FXML
    private Label lblMontoColon;
    @FXML
    private ImageView imgLogoOrigen;
    @FXML
    private Label lblMontoOrigen;
    @FXML
    private Label lblMontoDestino;
    @FXML
    private ImageView imgLogoDestino;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgViewFondo.fitHeightProperty().bind(root.heightProperty());
        imgViewFondo.fitWidthProperty().bind(root.widthProperty()); 
        limpiarCampos();
    }

    @FXML
    private void onActionButtonConsultar(ActionEvent event) {

    }

    @FXML
    private void onActionButtonIngresar(ActionEvent event) {
        if (actionValidated()) {
            Respuesta respuesta = service.Ingresar(txtCedula.getText(), psswPassword.getText());
            if (respuesta.getEstado()) {
                new Mensaje().show(Alert.AlertType.INFORMATION, "Inicio de sesión", "Hola " + AppContext.getInstance().get("NombreEmpleado") + ", bienvenido al sistema.");
                if (Autenticacion.getInstance().isValid()) {
                    FlowController.getInstance().goMain();
                } else if (!Autenticacion.getInstance().isEstado()) {
                    new Mensaje().show(Alert.AlertType.INFORMATION, "Inicio de sesión", "Sus credenciales han sido desactivadas.");
                // }else if(!Autenticacion.getInstance().isAprobado()){
                // Mensaje.show(Alert.AlertType.INFORMATION, "Inicio de sesión", "Su usuario aun no esta aprobado");
                // }else if(Autenticacion.getInstance().isTemporal()){
                // this.closeWindow();
                // FlowController.getInstance().goViewInNoResizableWindow("Restablecer", Boolean.FALSE, StageStyle.DECORATED);
                }
                this.getStage().close();
            } else {
                new Mensaje().show(Alert.AlertType.ERROR, "Inicio de sesión", "Credenciales inválidas.");
            }
        }
    }

    private boolean actionValidated() {
        boolean bandera = false;
        try {
            if (txtCedula.getText() == null || txtCedula.getText().isEmpty()) {
                bandera = false;
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de Usuario", (Stage) txtCedula.getScene().getWindow(), "Es necesario digitar una cedula para ingresar al sistema.");
            } else if (psswPassword.getText() == null || psswPassword.getText().isEmpty()) {
                bandera = false;
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación Incorrecta", (Stage) psswPassword.getScene().getWindow(), "Es necesario digitar una contraseña para ingresar al sistema.");
            } else {
            // FlowController.getInstance().goMain();
            // (Stage) btnIngresar.getScene().getWindow()).close();
                bandera = true;
            }

        } catch (Exception ex) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Validación Incorrecta", (Stage) psswPassword.getScene().getWindow(), "Surgió un error al ingresar al sistema.");
            bandera = false;
        }
        return bandera;
    }

    @Override
    public void initialize() {
        imgViewFondo.fitHeightProperty().bind(root.heightProperty());
        imgViewFondo.fitWidthProperty().bind(root.widthProperty()); 
        limpiarCampos();
    }

    private void limpiarCampos() {
    txtCedula.setText("");
    psswPassword.setText("");
    }
        
    @FXML
    private void onActionButtonAyuda(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("AyudaView", stage, Boolean.FALSE);
    }

    @FXML
    private void onActionDolarOrigen(ActionEvent event) {
    }

    @FXML
    private void onActionLibraOrigen(ActionEvent event) {
    }

    @FXML
    private void onActionYenOrigen(ActionEvent event) {
    }

    @FXML
    private void onActionEuroDolarOrigen(ActionEvent event) {
    }

    @FXML
    private void onActionDolarCanadienseOrigen(ActionEvent event) {
    }

    @FXML
    private void onActionFrancoSuizoOrigen(ActionEvent event) {
    }

    @FXML
    private void onActionDolarNeozelandes(ActionEvent event) {
    }

    @FXML
    private void onActionDolarAutralianoOrigen(ActionEvent event) {
    }

    @FXML
    private void onActionColonOrigen(ActionEvent event) {
    }

    @FXML
    private void onActionDolarDestino(ActionEvent event) {
    }

    @FXML
    private void onActionLibraDestino(ActionEvent event) {
    }

    @FXML
    private void onActionYenDestino(ActionEvent event) {
    }

    @FXML
    private void onActionEurodolarDestino(ActionEvent event) {
    }

    @FXML
    private void onActionDolarCanadienseDestino(ActionEvent event) {
    }

    @FXML
    private void onActionFrancoSuizoDestino(ActionEvent event) {
    }

    @FXML
    private void onActionDolarNeozelandesDestino(ActionEvent event) {
    }

    @FXML
    private void onActionDolarAustralianoDestino(ActionEvent event) {
    }

    @FXML
    private void onActionColonDestino(ActionEvent event) {
    }
}