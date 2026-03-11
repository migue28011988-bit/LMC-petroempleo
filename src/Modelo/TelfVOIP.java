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
public class TelfVOIP {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)        
    private Integer id;
    
    @Column(unique = true, nullable = false)
    private Integer codigo;
    
    @Column(unique = true, nullable = false)
    private String IP;
    
    private Integer numInventario;
    private String departamento;
    private String marca;
    private String modelo;
    private String numSerie;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(unique = true, nullable = false)
    private final Usuario usuario;
    
    public TelfVOIP(){
        usuario= new Usuario();
    }
    
    public Integer getId(){return this.id;}
    public Integer getCodigo(){return this.codigo;}
    public Integer getNumInv(){return this.numInventario;}
    public String getIP(){return this.IP;}
    public String getDepart(){return this.departamento;}
    public String getMarca(){return this.marca;}
    public String getModelo(){return this.modelo;}
    public String getNumSerie(){return this.numSerie;}
    public Usuario getUsuario(){return this.usuario;}
    
    public void setId(Integer var){this.id= var;}
    public void setCodigo(Integer var){this.codigo= var;}
    public void setNumInv(Integer var){this.numInventario= var;}
    public void setIP(String var){this.IP= var;}
    public void setDepart(String var){this.departamento= var;}
    public void setMarca(String var){this.marca= var;}
    public void setModelo(String var){this.modelo= var;}
    public void setNumSerie(String var){this.numSerie= var;}
    public void setUsuario(String nombre, String cargo){
        this.usuario.setNombre(nombre);
        this.usuario.setCargo(cargo);
    }
}
