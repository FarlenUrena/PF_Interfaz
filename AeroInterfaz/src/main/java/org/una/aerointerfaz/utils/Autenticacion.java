/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerointerfaz.utils;

import org.una.aerointerfaz.dtos.EmpleadoDTO;
import org.una.aerointerfaz.dtos.RolDTO;


public class Autenticacion {
    private EmpleadoDTO user;
    private RolDTO rol;
    private String token;
    private static Autenticacion INSTANCE = null;
    
    public Autenticacion(){}
    
    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (AppContext.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Autenticacion();
                }
            }
        }
    }
    
    public static Autenticacion getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }
    
    public EmpleadoDTO getUsuario(){
        return this.user;
    }
    
    public RolDTO getRol(){
        return this.rol;
    }
    
    public String getToken(){
        return this.token;
    }
    
    public void setData(EmpleadoDTO user, RolDTO rol, String token){
        this.user = user;
        this.rol = rol;
        this.token = "bearer "+token;
    }
    
    public Boolean isValid(){
        return user.isEstado() ;
    }
    
    public Boolean isEstado(){
        return user.isEstado();
    }
    
    public Boolean isRol(String rolName){
        if(rol == null)
            return false;
        return rol.getNombre().equals(rolName.toUpperCase());
    }
}
