/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.una.aerointerfaz.services.HorarioServiceImplementation;
import org.una.aerointerfaz.dtos.HorarioDTO;
import org.una.aerointerfaz.utils.Mensaje;
import org.una.aerointerfaz.utils.Respuesta;

/**
 * FXML Controller class
 *
 * @author thony
 */
public class HorarioViewController extends Controller implements Initializable {

    @FXML
    private JFXButton btnCrear;
    @FXML
    private JFXComboBox<String> cbxDiaEntrada;
    @FXML
    private JFXComboBox<String> cbxDiaSalida;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXComboBox<String> cbxHoraEntrada;
    @FXML
    private JFXComboBox<String> cbxMinutoEntrada;
    @FXML
    private JFXComboBox<String> cbxHoraSalida;
    @FXML
    private JFXComboBox<String> cbxMinutoSalida;
    @FXML
    private Label lblHoraEntrada;
    @FXML
    private Label lblHoraSalida;
    @FXML
    private VBox vBoxEntrada;
    
    private List<Node> requeridos = new ArrayList<>();

    private final HorarioServiceImplementation service = new HorarioServiceImplementation();
    
    ArrayList<String> dias = new ArrayList();
    ArrayList<String> horas = new ArrayList();
    ArrayList<String> minutos = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        System.out.println(tpHoraEntrada.getValue());
    cargarDatos();
    }

    @Override
    public void initialize() {
        limpiarCampos();
//        cargarDatos();
//        vBoxEntrada.getChildren().add(timePicker);
    }
    
    @FXML
    private void onActionButtonCrear(ActionEvent event) {
        try {
            if (validacionFinal()) {
                HorarioDTO horario = new HorarioDTO();
                nuevoHorario(horario);System.out.println(horario);
                Respuesta respuesta = service.CrearHorario(horario);
                System.out.println(respuesta.getResultado("Horario"));
                System.out.println(respuesta.getEstado());
                if (respuesta.getEstado()) {
                    new Mensaje().show(Alert.AlertType.INFORMATION, "Administrando horarios", "Horario creado con éxito.");
                } else {
                    new Mensaje().show(Alert.AlertType.ERROR, "Administrando horarios", "Error al crear el horario.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(HorarioViewController.class.getName()).log(Level.SEVERE, "Error creando el horario", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Crear horario", getStage(), "Ocurrió un error al crear el horario.");
        }
    }
    private void cargarHoras(){
       for(int i = 0; i < 60;i++){
           if(i<24){
           horas.add(String.valueOf(i));
           }
           minutos.add(String.valueOf(i));
       }
        
       for(String i : horas){
           if(Integer.valueOf(i)/10 >= 1){
           cbxHoraEntrada.getItems().add(String.valueOf(i));
           cbxHoraSalida.getItems().add(String.valueOf(i));
           }else{
           cbxHoraEntrada.getItems().add("0"+String.valueOf(i));
           cbxHoraSalida.getItems().add("0"+String.valueOf(i));
       } }
       for(String i : minutos){
           if(Integer.valueOf(i)/10 >= 1){
           cbxMinutoEntrada.getItems().add(String.valueOf(i));
           cbxMinutoSalida.getItems().add(String.valueOf(i));
           }else{
           cbxMinutoEntrada.getItems().add("0"+String.valueOf(i));
           cbxMinutoSalida.getItems().add("0"+String.valueOf(i));
           }
       }
       
    }


    private void cargarDatos(){
    cargarDias();
    cargarHoras();
    }
    
    private void cargarDias(){
        if(dias.isEmpty()){
    String Lunes = "Lunes",Martes = "Martes",Miercoles = "Miércoles", Jueves = "Jueves",
            Viernes = "Viernes",Sabado = "Sábado", Domingo = "Domingo";
    dias.add(Lunes);dias.add(Martes);dias.add(Miercoles);dias.add(Jueves);
    dias.add(Viernes);dias.add(Sabado);dias.add(Domingo);
    }
    cbxDiaEntrada.getItems().addAll(dias);
    cbxDiaSalida.getItems().addAll(dias);
    }
    

    private void nuevoHorario(HorarioDTO horario) {
        
        horario.setHoraEntrada(cbxHoraEntrada.getValue()+":"+cbxMinutoEntrada.getValue());
        horario.setDiaEntrada(cbxDiaEntrada.getValue());
        
        horario.setHoraSalida(cbxHoraSalida.getValue()+":"+cbxMinutoSalida.getValue());
        horario.setDiaSalida(cbxDiaEntrada.getValue());
    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(cbxDiaEntrada,cbxDiaSalida));
    }
    
    private void limpiarCampos() {
        if(cbxDiaEntrada.getValue() == null || cbxDiaSalida.getValue() == null){
        cbxDiaEntrada.setValue(null);
        cbxDiaSalida.setValue(null);
        
        
        cbxHoraEntrada.setValue(null);
        cbxHoraSalida.setValue(null);
        
        cbxMinutoEntrada.setValue(null);
        cbxMinutoSalida.setValue(null);
        }
        
    }
    
    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXTextField && ((JFXTextField) node).getText().isEmpty()) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && ((JFXPasswordField) node).getText().isBlank()) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            /*}else if (node instanceof JFXTimePicker && ((JFXTimePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXTimePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((JFXTimePicker) node).getAccessibleText();
                }
                validos = false;
            */}  else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "campos requeridos o con problemas de formato[" + invalidos + "].";
        }
    }

    private boolean validacionFinal() {
        indicarRequeridos();
        if (validarRequeridos() == "") {
            System.out.println(validarRequeridos());
            return true;
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "Error", "Ocurrió un error: " + validarRequeridos() + " Verifica los campos e inténtalo nuevamente.");
            return false;
        }
    }

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonCerrar(ActionEvent event) {
        this.getStage().close();
    }
    
//    private void inicializarTimePicker();
    
}