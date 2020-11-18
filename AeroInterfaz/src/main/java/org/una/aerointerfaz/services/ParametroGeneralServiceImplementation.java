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
import org.una.aerointerfaz.dtos.ParametroGeneralDTO;
import org.una.aerointerfaz.utils.Conexion;
import org.una.aerointerfaz.utils.Respuesta;

/**
 *
 * @author erikg
 */

public class ParametroGeneralServiceImplementation implements IParametroGeneralService {
   public Respuesta CrearParametroGeneral(ParametroGeneralDTO parametroGeneral) {
        try {
            Conexion request = new Conexion("para/");
            request.post(parametroGeneral);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo crear el parametroGeneral: " + request.getMensajeRespuesta());
            }
            ParametroGeneralDTO parametroGeneralDto = (ParametroGeneralDTO) request.readEntity(ParametroGeneralDTO.class);

            return new Respuesta(true, "ParametroGeneral", parametroGeneralDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }

    public Respuesta ActualizarParametroGeneral(ParametroGeneralDTO parametroGeneral, Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Conexion request = new Conexion("parametrosGenerales/", "/{id}", parametros);
            request.put(parametroGeneral);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo actualizar el parametroGeneral: " + request.getMensajeRespuesta());
            }
            ParametroGeneralDTO parametroGeneralDto = (ParametroGeneralDTO) request.readEntity(ParametroGeneralDTO.class);
            return new Respuesta(true, "ParametroGeneral", parametroGeneralDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }

    }

    public Respuesta ObtenerParametroGeneral() {
        try {
            Conexion request = new Conexion("parametrosGenerales/");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo obtener los parametrosGenerales: " + request.getMensajeRespuesta());
            }
            List<ParametroGeneralDTO> parametrosGenerales = (List<ParametroGeneralDTO>) request.readEntity(new GenericType<List<ParametroGeneralDTO>>() {
            });
            return new Respuesta(true, "ParametrosGenerales", parametrosGenerales);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }
}