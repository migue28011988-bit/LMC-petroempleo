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
public class DispRED {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(unique = true, nullable = false)
    private String ip;
    
    private String tipo;
    private String marca;
    private String modelo;
    private String numSerie;
    
    public DispRED(){}
    
    public Integer getId(){return this.id;}
    public String getIP(){return this.ip;}
    public String getTipo(){return this.tipo;}
    public String getMarca(){return this.marca;}
    public String getModelo(){return this.modelo;}
    public String getNumSerie(){return this.numSerie;}
    
    public void setId(Integer var){this.id= var;}
    public void setIP(String var){this.ip= var;}
    public void setTipo(String var){this.tipo= var;}
    public void setMarca(String var){this.marca= var;}
    public void setModelo(String var){this.modelo= var;}
    public void setNumSerie(String var){this.numSerie= var;}
}
