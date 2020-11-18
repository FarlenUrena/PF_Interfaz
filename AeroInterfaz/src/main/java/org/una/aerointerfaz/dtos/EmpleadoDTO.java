/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author farle_000
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class EmpleadoDTO {
 
    private Long id; 
    private String nombreCompleto;   
    private String cedula; 
    private boolean estado;
    private String passwordEncriptado;
    private Date fechaRegistro; 
    private Date fechaModificacion; 
    private boolean jefe;
    private boolean usuario;
    private AreaTrabajoDTO areaTrabajo; 
    private HorarioDTO horario;
    private RolDTO rol;
}