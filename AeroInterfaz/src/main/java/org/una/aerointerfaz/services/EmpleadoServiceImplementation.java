/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.services;

import java.util.HashMap;
import java.util.Map;
import org.una.aerointerfaz.dtos.EmpleadoDTO;
import org.una.aerointerfaz.utils.Conexion;
import org.una.aerointerfaz.utils.Respuesta;

/**
 *
 * @author erikg
 */
public class EmpleadoServiceImplementation implements IEmpleadoService {

    public Respuesta CrearEmpleado(EmpleadoDTO empleado) {
        try {
            Conexion request = new Conexion("http://localhost:8099/empleados/");
            request.post(empleado);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo crear el empleado: " + request.getMensajeRespuesta());
            }
            EmpleadoDTO empleadoDto = (EmpleadoDTO) request.readEntity(EmpleadoDTO.class);

            return new Respuesta(true, "Empleado", empleadoDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }
    
    public Respuesta ActualizarEmpleado(EmpleadoDTO empleado, Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Conexion request = new Conexion("http://localhost:8099/empleados", "/{id}", parametros);
            request.put(empleado);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo actualizar el empleado: " + request.getMensajeRespuesta());
            }
            EmpleadoDTO empleadoDto = (EmpleadoDTO) request.readEntity(EmpleadoDTO.class);
            return new Respuesta(true, "Empleado", empleadoDto);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    }

    @Override
    public Respuesta create(EmpleadoDTO empleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
