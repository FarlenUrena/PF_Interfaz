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
import org.una.aerointerfaz.dtos.HorarioDTO;
import org.una.aerointerfaz.utils.Conexion;
import org.una.aerointerfaz.utils.Respuesta;

/**
 *
 * @author erikg
 */
public class HorarioServiceImplementation implements IHorarioService {

    public Respuesta CrearHorario(HorarioDTO horario) {
        try {
            Conexion request = new Conexion("http://localhost:8099/horarios/");
            request.post(horario);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo crear el horario: " + request.getMensajeRespuesta());
            }
            HorarioDTO horarioDto = (HorarioDTO) request.readEntity(HorarioDTO.class);

            return new Respuesta(true, "Horario", horarioDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }

    public Respuesta ActualizarHorario(HorarioDTO horario, Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Conexion request = new Conexion("http://localhost:8099/horarios", "/{id}", parametros);
            request.put(horario);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo actualizar el horario: " + request.getMensajeRespuesta());
            }
            HorarioDTO horarioDto = (HorarioDTO) request.readEntity(HorarioDTO.class);
            return new Respuesta(true, "Horario", horarioDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }
    
    public Respuesta ObtenerHorario() {
        try {
            Conexion request = new Conexion("horarios/");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo obtener los horarios: " + request.getMensajeRespuesta());
            }
            List<HorarioDTO> horarios = (List<HorarioDTO>) request.readEntity(new GenericType<List<HorarioDTO>>() {
            });
            return new Respuesta(true, "Horarios", horarios);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }
}