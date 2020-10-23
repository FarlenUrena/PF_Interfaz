/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.services;

import org.una.aerointerfaz.utils.Respuesta;
import org.una.aerointerfaz.dtos.AuthenticationRequest;
import org.una.aerointerfaz.dtos.AuthenticationResponse;
import org.una.aerointerfaz.utils.Autenticacion;

/**
 *
 * @author farle_000
 */

import org.una.aerointerfaz.utils.Conexion;
public class AutenticacionServiceImplementation implements IAutenticacionSerivice{

    @Override
    public Respuesta Ingresar(String ced, String password) {
     try{
            AuthenticationRequest authetication = new AuthenticationRequest(ced, password);
            Conexion request = new Conexion("autenticacion/login");
            request.post(authetication);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Iniciar Sesion: "+request.getMensajeRespuesta());
            }
            AuthenticationResponse usuario = (AuthenticationResponse) request.readEntity(AuthenticationResponse.class);
            Autenticacion.getInstance().setData(usuario.getEmpleado(), usuario.getRol(), usuario.getJwt());
            return new Respuesta(true, "Usuario", usuario);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "Iniciar Sesion: Error al comunicarse con el servidor");
        }
    }
    }