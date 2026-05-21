/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.AdministradorDAO;
import Modelo.Administrador;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author PC
 */
public class inicioSesion {
    
    private final AdministradorDAO DAO;
    private Administrador admin;
    
    /**
     * Este constructor realiza la conexión al servidor de postgreSQL 
     * con la configuración establecida en el archivo local "config.cfg"
    */
    public inicioSesion(){
        DAO= new AdministradorDAO();
    }
    
    /**
     * Verifica la cuenta del administrador usando un nombre y una contraseña dada.
     * @param nombre nombre de la cuenta.
     * @param password contraseña de la cuenta.
     * @return verdadero si se encuentra una cuenta con el mismo nombre y contraseña
     * que los parámetros dados, de lo contrario devuelve falso.
     */
    public boolean esAdmin(String nombre, String password){
        /*si admin es null significa que no se encontró
        el admin por el nombre*/
        admin= DAO.getAdmin(nombre);
        if(admin != null){
            //verifica la contraseña
            String passwordHash= admin.getPassword();
            return BCrypt.checkpw(password, passwordHash);
        }
        return false;
    }
    
    /**
     * Verifica si el administrador es super administrador,
     * este método se debe ejecutar después que esAdmin() para saber realmente si 
     * el administrador es un super administrador, de lo contrario esSuperAdmin()
     * devolverá siempre falso.
     * @return verdadero si el administrador es un super administrador.
     */
    public boolean esSuperAdmin(){
        return admin.getSuper();
    }
    public boolean estaVacio(){
        return DAO.isEmpty();
    }
}
