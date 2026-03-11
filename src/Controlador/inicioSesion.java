/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.AdministradorDAO;

/**
 *
 * @author PC
 */
public class inicioSesion {
    
    private final AdministradorDAO DAO;
    
    public inicioSesion(){
        DAO= new AdministradorDAO();
    }
    
    public boolean esAdmin(String nombre, String password){
        return DAO.isAdmin(nombre, password);
    }
    public boolean esSuperAdmin(String nombre, String password){
        return DAO.isSuperAdmin(nombre, password);
    }
    public boolean estaVacio(){
        return DAO.isEmpty();
    }
}
