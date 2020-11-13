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
public class HorarioDTO {

    private Long id;
    private Date diaEntrada;
    private Date diaSalida;
    private String horaEntrada;
    private String horaSalida;
}