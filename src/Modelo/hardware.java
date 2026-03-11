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
public class hardware {
    
    @Id   
    @GeneratedValue(strategy=GenerationType.AUTO)  
    private Integer id;
    
    private String tipo;
    private String marca;
    private String modelo;
    private String numSerie;
    private Integer numInventario;
    private String capacidad;
    
    public hardware(){}
    
    public Integer getId(){return id;}
    public String getTipo(){return this.tipo;}
    public String getMarca(){return this.marca;}
    public String getModelo(){return this.modelo;}
    public String getNumSerie(){return this.numSerie;}
    public Integer getNumInv(){return this.numInventario;}
    public String getCapac(){return this.capacidad;}
    
    public void setId(Integer var){this.id= var;}
    public void setTipo(String var){this.tipo= var;}
    public void setMarca(String var){this.marca= var;}
    public void setModelo(String var){this.modelo= var;}
    public void setNumSerie(String var){this.numSerie= var;}
    public void setNumInv(Integer var){this.numInventario= var;}
    public void setCapac(String var){this.capacidad= var;}
}
