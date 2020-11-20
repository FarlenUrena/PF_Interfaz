/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.services;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.una.aerointerfaz.utils.ConexionForexApi;

/**
 *
 * @author farle_000
 */
public interface IDivisaService {
   public Object getAll(String s) throws InterruptedException, ExecutionException, IOException;
}
