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
public class Logins {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String fecha;
    private String hora;
    private String cuenta;
    private String tiempoActivo;
    
    public Logins(){}
    
    public Integer getId(){return id;}
    public String getFecha(){return this.fecha;}
    public String getHora(){return this.hora;}
    public String getCuenta(){return this.cuenta;}
    public String getTiempoActivo(){return this.tiempoActivo;}
    
    public void setId(Integer var){this.id= var;}
    public void setFecha(String var){this.fecha= var;}
    public void setHora(String var){this.hora= var;}
    public void setCuenta(String var){this.cuenta= var;}
    public void setTiempoActivo(String var){this.tiempoActivo= var;}
}
