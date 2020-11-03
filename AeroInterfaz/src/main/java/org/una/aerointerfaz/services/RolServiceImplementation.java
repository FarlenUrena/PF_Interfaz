/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.services;

import java.util.HashMap;
import java.util.Map;
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
            Conexion request = new Conexion("http://localhost:8099/roles/");
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
            Conexion request = new Conexion("http://localhost:8099/rols", "/{id}", parametros);
            request.put(rol);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo actualizar el àrea de trabajo: " + request.getMensajeRespuesta());
            }
            RolDTO rolDto = (RolDTO) request.readEntity(RolDTO.class);
            return new Respuesta(true, "Rol", rolDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }
}