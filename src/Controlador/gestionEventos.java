/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.EventosDAO;
import java.util.List;

/**
 *
 * @author PC
 */
public class gestionEventos {
    
    public gestionEventos(){
        DaoEventos= new EventosDAO();
    }
    
    public List<Object[]> obtenerEventos(){
        return DaoEventos.getTodos();
    }
    
    public static void agregarEvento(String descripcion){
        EventosDAO.agregarUno(descripcion);
    }
    
    public String borrarEvento(Object[] evento){
        String fecha= evento[0].toString().split(" ")[5] + "-";
        String mes= evento[0].toString().split(" ")[3];
        //<editor-fold defaultstate="collapsed" desc="cambiar mes de una palabra a un número como de "enero" a "01"">
        switch(mes){
            case "Enero" -> fecha+= "01-";
            case "Febrero" -> fecha+= "02-";
            case "Marzo" -> fecha+= "03-";
            case "Abril" -> fecha+= "04-";
            case "Mayo" -> fecha+= "05-";
            case "Junio" -> fecha+= "06-";
            case "Julio" -> fecha+= "07-";
            case "Agosto" -> fecha+= "08-";
            case "Septiembre" -> fecha+= "09-";
            case "Octubre" -> fecha+= "10-";
            case "Noviembre" -> fecha+= "11-";
            case "Diciembre" -> fecha+= "12-";
        }
        //</editor-fold>
        fecha+= evento[0].toString().split(" ")[1];
        evento[0]= fecha;
        int estado= DaoEventos.borrarUno(evento);
        return switch (estado) {
            case 1 -> "Evento borrado";
            case -1 -> "Hubo un error y no se pudo borrar el evento";
            default -> "No se encontró el evento a borrar";
        };
    }
    
    public String borrarAll(){
        int estado= DaoEventos.borrarTodos();
        return switch (estado) {
            case 1 -> "Todos los eventos borrados";
            case -1 -> "Hubo un error y no se pudo borrar el historial de evento";
            default -> "No se puede borrar el historial de eventos porque está vacio";
        };
    }
    
    private final EventosDAO DaoEventos;
}
