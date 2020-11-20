/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.services;

import org.una.aerointerfaz.utils.ConexionForexApi;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author farle_000
 */
public class DivisaServiceImplementation implements IDivisaService{
    private final String url = "https://www.freeforexapi.com/api/live?pairs=";
        
    @Override
      public Object getAll(String s) throws InterruptedException, ExecutionException, IOException {
        return ConexionForexApi.ListFromConnectionDivisa(url+s);
    }
    
    public static DivisaServiceImplementation getInstance() {
        return DivisaServiceHolder.INSTANCE;
    }

    private static class DivisaServiceHolder {
        private static final DivisaServiceImplementation INSTANCE = new DivisaServiceImplementation();
    }
}
