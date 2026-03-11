/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author PC
 */

@Entity
public class Cambios {
    
    @Id   
    @GeneratedValue(strategy= GenerationType.AUTO)  
    private Integer id;
    
    private String componenteActual;
    private String componenteAnterior;
    private String fecha;
    private String hora;
    //si es una instalación, desinstalación o reemplazo
    private String tipo;
    
    public Cambios(){}
    
    public Integer getId(){return id;}
    public String getFecha(){return this.fecha;}
    public String getHora(){return this.hora;}
    public String getComponenteActual(){return this.componenteActual;}
    public String getComponenteAnterior(){return this.componenteAnterior;}
    public String getTipo(){return this.tipo;}
    
    public void setId(Integer var){this.id= var;}
    public void setFecha(String var){this.fecha= var;}
    public void setHora(String var){this.hora= var;}
    public void setComponenteActual(String var){this.componenteActual= var;}
    public void setComponenteAnterior(String var){this.componenteAnterior= var;}
    public void setTipo(String var){this.tipo= var;}
}
