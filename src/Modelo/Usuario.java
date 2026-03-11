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
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String nombre;
    private String cargo;
    
    public Integer getId(){return this.id;}
    public String getNombre(){return this.nombre;}
    public String getCargo(){return this.cargo;}
    
    public void setId(Integer var){this.id= var;}
    public void setNombre(String var){this.nombre= var;}
    public void setCargo(String var){this.cargo= var;}
}
