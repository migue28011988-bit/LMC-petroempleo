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
public class Incidencias {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    
    private String Fecha;
    private String Hora;
    private String Descripcion;
    
    public Incidencias(){}
    
    public Integer getId(){return this.id;}
    public String getFecha(){return this.Fecha;}
    public String getHora(){return this.Hora;}
    public String getDesc(){return this.Descripcion;}
    
    public void setId(Integer var){this.id= var;}
    public void setFecha(String var){this.Fecha= var;}
    public void setHora(String var){this.Hora= var;}
    public void setDesc(String var){this.Descripcion= var;}
}
