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
import org.una.aerointerfaz.dtos.EmpleadoDTO;
import org.una.aerointerfaz.dtos.RolDTO;
import org.una.aerointerfaz.utils.Conexion;
import org.una.aerointerfaz.utils.Respuesta;

/**
 *
 * @author erikg
 */
public class EmpleadoServiceImplementation implements IEmpleadoService {

    public Respuesta CrearEmpleado(EmpleadoDTO empleado) {
        try {
            Conexion request = new Conexion("empleados/");
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
            Conexion request = new Conexion("empleados/", "/{id}", parametros);
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

        public Respuesta ObtenerEmpleados() {
       try{
            Conexion request = new Conexion("empleados/");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo obtener los empleados: "+request.getMensajeRespuesta()); 
            }
            List<EmpleadoDTO> empleados = (List<EmpleadoDTO>) request.readEntity(new GenericType<List<EmpleadoDTO>>(){});
            return new Respuesta(true, "Empleados", empleados);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "Error al comunicarse con el servidor");
        }
    
    
    }
}