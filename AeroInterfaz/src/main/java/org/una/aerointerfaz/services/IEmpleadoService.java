/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.services;

import org.una.aerointerfaz.dtos.EmpleadoDTO;
import org.una.aerointerfaz.utils.Respuesta;

/**
 *
 * @author erikg
 */
public interface IEmpleadoService {  
    public Respuesta ObtenerEmpleados();
    public Respuesta CrearEmpleado(EmpleadoDTO empleado);
    public Respuesta ActualizarEmpleado(EmpleadoDTO empleado, Long id);

    
   
}
