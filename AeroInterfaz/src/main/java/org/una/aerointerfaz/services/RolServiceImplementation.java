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
import org.una.aerointerfaz.dtos.RolDTO;
import org.una.aerointerfaz.utils.Conexion;
import org.una.aerointerfaz.utils.Respuesta;

/**
 *
 * @author farle_000
 */
public class RolServiceImplementation implements IRolService {

    public Respuesta CrearRol(RolDTO rol) {
        try {
            Conexion request = new Conexion("roles/");
            request.post(rol);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo crear el rol: " + request.getMensajeRespuesta());
            }
            RolDTO rolDto = (RolDTO) request.readEntity(RolDTO.class);

            return new Respuesta(true, "Rol", rolDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }

    public Respuesta ActualizarRol(RolDTO rol, Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Conexion request = new Conexion("roles", "/{id}", parametros);
            request.put(rol);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo actualizar el rol: " + request.getMensajeRespuesta());
            }
            RolDTO rolDto = (RolDTO) request.readEntity(RolDTO.class);
            return new Respuesta(true, "Rol", rolDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }

    public Respuesta ObtenerRoles() {
       try{
            Conexion request = new Conexion("roles/");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo obtener los roles: "+request.getMensajeRespuesta()); 
            }
            List<RolDTO> roles = (List<RolDTO>) request.readEntity(new GenericType<List<RolDTO>>(){});
            return new Respuesta(true, "Roles", roles);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    
    
    }
}