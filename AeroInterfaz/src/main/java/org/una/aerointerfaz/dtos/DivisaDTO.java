/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.aerointerfaz.utils.Monedas;

/**
 *
 * @author farle_000
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class DivisaDTO {
    
    private  Monedas rates;
    private String code;
}
