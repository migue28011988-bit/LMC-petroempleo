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
public class sistemaOperativo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private String nombre;
    private String edicion;
    private String version;

    public sistemaOperativo() {}
    
    public Integer getId(){return this.id;}
    public String getNombre(){return this.nombre;}
    public String getEdicion(){return this.edicion;}
    public String getVersion(){return this.version;}
    
    public void setId(Integer var){this.id= var;}
    public void setNombre(String var){this.nombre= var;}
    public void setEdicion(String var){this.edicion= var;}
    public void setVersion(String var){this.version= var;}
}
