/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.utils;

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
public class Monedas {
    
    private CurrencyValue USDEUR;
    private CurrencyValue USDGBP;
    private CurrencyValue USDAUD;
    private CurrencyValue USDNZD;
    private CurrencyValue USDCAD;
    private CurrencyValue USDCHF;
    private CurrencyValue USDJPY;
    private CurrencyValue USDCRC;   
}
