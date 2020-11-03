/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.services;

import org.una.aerointerfaz.utils.Respuesta;
import org.una.aerointerfaz.dtos.AuthenticationRequest;
import org.una.aerointerfaz.dtos.AuthenticationResponse;
import org.una.aerointerfaz.utils.AppContext;
import org.una.aerointerfaz.utils.Autenticacion;
import org.una.aerointerfaz.utils.Conexion;

/**
 *
 * @author farle_000
 */
<<<<<<< HEAD
public class AutenticacionServiceImplementation implements IAutenticacionSerivice {

    @Override
    public Respuesta Ingresar(String ced, String password) {
        try {
=======

public class AutenticacionServiceImplementation implements IAutenticacionSerivice{

    @Override
    public Respuesta Ingresar(String ced, String password) {
     System.out.println(":");
        try{
<<<<<<< Updated upstream
=======
>>>>>>> bc3fe1edc338ea5c07a5a6a0cc18c4d6178ef12d
>>>>>>> Stashed changes
            AuthenticationRequest authetication = new AuthenticationRequest(ced, password);
            Conexion request = new Conexion("autenticacion/login");
           
            request.post(authetication);
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "Iniciar Sesion: " + request.getMensajeRespuesta());
=======
>>>>>>> Stashed changes
            System.out.println(":C");
            if(request.isError()){
             System.out.println("C:");
                return new Respuesta(false, request.getError(), "Iniciar Sesion: "+request.getMensajeRespuesta());
            
<<<<<<< Updated upstream
=======
>>>>>>> bc3fe1edc338ea5c07a5a6a0cc18c4d6178ef12d
>>>>>>> Stashed changes
            }
            AuthenticationResponse usuario = (AuthenticationResponse) request.readEntity(AuthenticationResponse.class);
            Autenticacion.getInstance().setData(usuario.getEmpleado(), usuario.getRol(), usuario.getJwt());
            AppContext.getInstance().set("RolEmpleado", usuario.getRol().getNombre());
            AppContext.getInstance().set("NombreEmpleado", usuario.getEmpleado().getNombreCompleto());  
             System.out.println(":|");
            return new Respuesta(true, "Usuario", usuario);
<<<<<<< HEAD
        } catch (Exception ex) {
=======
        }catch(Exception ex){
            System.out.println(":|:");
<<<<<<< Updated upstream
=======
>>>>>>> bc3fe1edc338ea5c07a5a6a0cc18c4d6178ef12d
>>>>>>> Stashed changes
            return new Respuesta(false, ex.toString(), "Iniciar Sesion: Error al comunicarse con el servidor");
        }
    }
}
