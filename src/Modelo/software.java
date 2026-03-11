/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import jakarta.persistence.*;

/**
 *
 * @author PC
 */

@Entity
public class software {
    
    @Id   
    @GeneratedValue(strategy=GenerationType.AUTO)  
    private Integer id;
    
    private String fecha;
    private String nombre;
    private String version;
    private String size;
    
    public software(){}
    
    public Integer getId(){return this.id;}
    public String getFecha(){return this.fecha;}
    public String getNombre(){return this.nombre;}
    public String getVer(){return this.version;}
    public String getSize(){return this.size;}
    
    public void setId(Integer var){this.id= var;}
    public void setFecha(String var){this.fecha= var;}
    public void setNombre(String var){this.nombre= var;}
    public void setVer(String var){this.version= var;}
    public void setSize(String var){this.size= var;}
}
