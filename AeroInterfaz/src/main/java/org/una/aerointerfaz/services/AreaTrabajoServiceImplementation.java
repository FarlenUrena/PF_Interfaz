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
import org.una.aerointerfaz.dtos.AreaTrabajoDTO;
import org.una.aerointerfaz.utils.Conexion;
import org.una.aerointerfaz.utils.Respuesta;

/**
 *
 * @author farle_000
 */
public class AreaTrabajoServiceImplementation implements IAreaTrabajoService {

    public Respuesta CrearAreaTrabajo(AreaTrabajoDTO areaTrabajo) {
        try {
            Conexion request = new Conexion("areasTrabajos/");
            request.post(areaTrabajo);
            if (request.isError()) { 
                System.out.println(request.getMensajeRespuesta());
                return new Respuesta(false, request.getError(), "No se pudo crear el área de trabajo: " + request.getMensajeRespuesta());
               
            }
            AreaTrabajoDTO areaTrabajoDto = (AreaTrabajoDTO) request.readEntity(AreaTrabajoDTO.class);

            return new Respuesta(true, "AreaTrabajo", areaTrabajoDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }

    public Respuesta ActualizarAreaTrabajo(AreaTrabajoDTO areaTrabajo, Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Conexion request = new Conexion("areasTrabajos/", "/{id}", parametros);
            request.put(areaTrabajo);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo actualizar el àrea de trabajo: " + request.getMensajeRespuesta());
            }
            AreaTrabajoDTO areaTrabajoDto = (AreaTrabajoDTO) request.readEntity(AreaTrabajoDTO.class);
            return new Respuesta(true, "AreaTrabajo", areaTrabajoDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }

    public Respuesta ObtenerAreaTrabajo() {
     try{
            Conexion request = new Conexion("areasTrabajos/");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo obtener las áreas de trabajo: "+request.getMensajeRespuesta()); 
            }
            List<AreaTrabajoDTO> areasTrabajos = (List<AreaTrabajoDTO>) request.readEntity(new GenericType<List<AreaTrabajoDTO>>(){});
            return new Respuesta(true, "AreasTrabajos", areasTrabajos);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }
}