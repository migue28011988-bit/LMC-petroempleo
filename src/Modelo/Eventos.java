/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author PC
 */
@Entity
public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String fecha;
    private String hora;
    private String descripcion;
    
    public Eventos(){}
    
    public void setId(Integer var){this.id= var;}
    public void setFecha(String var){this.fecha= var;}
    public void setHora(String var){this.hora= var;}
    public void setDesc(String var){this.descripcion= var;}
    
    public Integer getId(){return id;}
    public String getFecha(){
        String fechaFormateada="";
        //<editor-fold defaultstate="collapsed" desc="formatear fecha como en: Domingo, 28 de Febrero del 2026">
        LocalDate fechaLocal= LocalDate.parse(fecha);
        switch(fechaLocal.getDayOfWeek()){
            case DayOfWeek.MONDAY -> fechaFormateada+= "Lunes, ";
            case DayOfWeek.TUESDAY -> fechaFormateada+= "Martes, ";
            case DayOfWeek.WEDNESDAY -> fechaFormateada+= "Miércoles, ";
            case DayOfWeek.THURSDAY -> fechaFormateada+= "Jueves, ";
            case DayOfWeek.FRIDAY -> fechaFormateada+= "Viernes, ";
            case DayOfWeek.SATURDAY -> fechaFormateada+= "Sábado, ";
            case DayOfWeek.SUNDAY -> fechaFormateada+= "Domingo, ";
        }
        fechaFormateada+= fechaLocal.getDayOfMonth() + " de ";
        switch(fechaLocal.getMonth()){
            case Month.JANUARY -> fechaFormateada+= "Enero";
            case Month.FEBRUARY -> fechaFormateada+= "Febrero";
            case Month.MARCH -> fechaFormateada+= "Marzo";
            case Month.APRIL -> fechaFormateada+= "Abril";
            case Month.MAY -> fechaFormateada+= "Mayo";
            case Month.JUNE -> fechaFormateada+= "Junio";
            case Month.JULY -> fechaFormateada+= "Julio";
            case Month.AUGUST -> fechaFormateada+= "Agosto";
            case Month.SEPTEMBER -> fechaFormateada+= "Septiembre";
            case Month.OCTOBER -> fechaFormateada+= "Octubre";
            case Month.NOVEMBER -> fechaFormateada+= "Noviembre";
            case Month.DECEMBER -> fechaFormateada+= "Diciembre";
        }
        fechaFormateada+= " del " + fechaLocal.getYear();
        //</editor-fold>
        return fechaFormateada;
    }
    public String getHora(){return hora;}
    public String getDesc(){return descripcion;}
}
