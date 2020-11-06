/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.una.aerointerfaz.dtos.AlertaDTO;
import org.una.aerointerfaz.dtos.AlertaDTO;
import org.una.aerointerfaz.utils.Conexion;
import org.una.aerointerfaz.utils.Respuesta;

/**
 *
 * @author thony
 */
public class AlertaServiceImplementation implements IAlertaService {

    public Respuesta CrearAlerta(AlertaDTO alerta) {
        try {
            Conexion request = new Conexion("alertas/");
            request.post(alerta);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo crear la alerta: " + request.getMensajeRespuesta());
            }
            AlertaDTO alertaDto = (AlertaDTO) request.readEntity(AlertaDTO.class);

            return new Respuesta(true, "Alerta", alertaDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }

    public Respuesta ActualizarAlerta(AlertaDTO alerta, Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Conexion request = new Conexion("alertas", "/{id}", parametros);
            request.put(alerta);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo actualizar el àrea de trabajo: " + request.getMensajeRespuesta());
            }
            AlertaDTO alertaDto = (AlertaDTO) request.readEntity(AlertaDTO.class);
            return new Respuesta(true, "Alerta", alertaDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }
        public Respuesta ObtenerAlertas() {
       try{
            Conexion request = new Conexion("alertas/");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo obtener las alertas: "+request.getMensajeRespuesta()); 
            }
            List<AlertaDTO> alertas = (List<AlertaDTO>) request.readEntity(new GenericType<List<AlertaDTO>>(){});
            return new Respuesta(true, "Alertas", alertas);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    
    
    }   
    
}