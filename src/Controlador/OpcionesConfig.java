/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.AdministradorDAO;
import Modelo.Administrador;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author PC
 */
public class OpcionesConfig {

    public OpcionesConfig() {
        DAO= new AdministradorDAO();
        cuentas= new LinkedList<>();
        cambios= false;
    }
    
    public boolean getCambios(){return cambios;}
    
    public void resetCambios(){cambios= false;}
    
    public void adminCuentas(int operacion, Object[] cuenta){
        switch (operacion) {
            case GET_CUENTAS -> {
                cuentas.clear();
                for(Administrador admin : DAO.getTodos())
                    cuentas.add(new Object[]{admin.getId(), admin.getAdmin(), admin.getPassword(), admin.getSuper()});
            }
            case ADD_CUENTA -> {
                cuentas.add(cuenta);
                cambios= true;
            }
            case ERASE_CUENTA -> {
                cuentas.removeIf((Admin)->{
                    return Admin[1].equals(cuenta[0]);
                });
                cambios= true;
            }
            case UPDATE_CUENTA -> {
                cuentas.forEach((Admin)->{
                    if(!Admin[1].equals(cuenta[1]) || !Admin[1].equals(cuenta[1]) ||
                            !Admin[1].equals(cuenta[1]))
                        cambios= true;
                    //cuenta[0] es el nombre anterior y cuenta[1] es el nombre modificado
                    if(Admin[1].equals(cuenta[0])){
                        Admin[1]= cuenta[1];
                        Admin[2]= cuenta[2];
                        Admin[3]= cuenta[3];
                    }   
                });
            }
            case UPDATE_CUENTAS -> {
                List<Administrador> admins= new LinkedList<>();
                for(Object[] cuentaAdm : cuentas){
                    Administrador admin= new Administrador();
                    admin.setId(cuentaAdm[0] == null? null : (int)cuentaAdm[0]);
                    admin.setAdmin(cuentaAdm[1].toString());
                    admin.setPassword(cuentaAdm[2].toString());
                    admin.setSuper((boolean)cuentaAdm[3]);
                    admins.add(admin);
                }
                DAO.updateAndCreateAndDelete(admins);
            }
        }
    }
    
    public List<Object[]> getCuentas(){return this.cuentas;}
    
    public boolean checkAdminName(String name){
        for(Object[] cuenta : cuentas){
            if(cuenta[1].toString().equals(name))
                return true;
        }
        return false;
    }
    
    public String getPasswordDe(String AdminName){
        for(Object[] cuenta : cuentas){
            if(cuenta[1].toString().equals(AdminName))
                return cuenta[2].toString();
        }
        return null;
    }
    
    public void aplicarTema(String tema){
        LFActual= tema;
        save();
    }
    
    public String getTema(){
        load();
        return LFActual;
    }
    
    public void save(){
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream("config.cfg"))){
            out.writeObject(LFActual);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void load(){
        try(ObjectInputStream in= new ObjectInputStream(new FileInputStream("config.cfg"))){
            LFActual= (String)in.readObject();
        }catch(IOException | ClassNotFoundException e){
            LFActual= "Nimbus (Por defecto)";
        }
    }
    
    public void Reset(){
        LFActual= "Nimbus (Por defecto)";
        save();
    }
    
    /**
     * Carga el tema guardado en el archivo de configuración
     * al iniciar la app por primera vez.
     * 
     * El tema cargado se devuelve y 
     * se usará para establecer el LookAndFeel correspondiente
     * 
     * @return el nombre del tema cargado
     */
    public static String cargarTema(){
        String temaCargado;
        try(ObjectInputStream in= new ObjectInputStream(new FileInputStream("config.cfg"))){
            temaCargado= (String)in.readObject();
        }catch(IOException | ClassNotFoundException e){
            temaCargado= "Nimbus (Por defecto)";
        }
        return temaCargado;
    }
    
    public static final int GET_CUENTAS= 0;
    public static final int ADD_CUENTA= 1;
    public static final int ERASE_CUENTA= 2;
    public static final int UPDATE_CUENTA= 3;
    public static final int UPDATE_CUENTAS= 4;
    private final List<Object[]> cuentas;
    private final AdministradorDAO DAO;
    private String LFActual;
    private boolean cambios;
}
