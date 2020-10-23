/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.services;

import java.util.Optional;
import org.una.aerointerfaz.utils.Respuesta;

/**
 *
 * @author farle_000
 */

public interface IAutenticacionSerivice {
    public Respuesta Ingresar(String ced,String password);
}
