/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.services;

import java.util.HashMap;
import java.util.Map;
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
            Conexion request = new Conexion("http://localhost:8099/parametroGenerales/");
            request.post(parametroGeneral);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo crear el parámetro: " + request.getMensajeRespuesta());
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
            Conexion request = new Conexion("http://localhost:8099/parametroGenerales", "/{id}", parametros);
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
}