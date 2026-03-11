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
public class TelfMovil {
    
    @Id   
    @GeneratedValue(strategy= GenerationType.AUTO)  
    private Integer id;
    
    @Column(unique = true, nullable = false)
    private String numeroTelf;
    
    private String email;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(unique = true, nullable = false)
    private final Usuario usuario;
    
    private Integer PIN;
    private Integer PUK;
    private String marca;
    private String modelo;
    private String numSerie;
    
    public TelfMovil(){
        usuario= new Usuario();
    }
    
    public Integer getId(){return this.id;}
    public String getNumTelf(){return numeroTelf;}
    public String getEmail(){return email;}
    public Usuario getUsuario(){return this.usuario;}
    public Integer getPIN(){return this.PIN;}
    public Integer getPUK(){return this.PUK;}
    public String getMarca(){return this.marca;}
    public String getModelo(){return this.modelo;}
    public String getNumSerie(){return this.numSerie;}
    
    public void setId(Integer var){this.id= var;}
    public void setNumTelf(String var){this.numeroTelf= var;}
    public void setEmail(String var){this.email= var;}
    public void setUsuario(String nombre, String cargo){
        this.usuario.setNombre(nombre);
        this.usuario.setCargo(cargo);
    }
    public void setPIN(Integer var){this.PIN= var;}
    public void setPUK(Integer var){this.PUK= var;}
    public void setMarca(String var){this.marca= var;}
    public void setModelo(String var){this.modelo= var;}
    public void setNumSerie(String var){this.numSerie= var;}
}
