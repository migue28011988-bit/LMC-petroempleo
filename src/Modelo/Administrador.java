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
public class Administrador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(unique = true, nullable = false)
    private String Administrador;
    
    private String Password;
    
    @Column(nullable = false)
    private boolean superAdmin;
    
    public Administrador(){superAdmin= false;}
    
    public String getAdmin(){return Administrador;}
    public String getPassword(){return Password;}
    public Integer getId(){return id;}
    public boolean getSuper(){return superAdmin;}
    
    public void setId(Integer var){this.id= var;}
    public void setAdmin(String var){this.Administrador= var;}
    public void setPassword(String var){this.Password= var;}
    public void setSuper(boolean var){this.superAdmin= var;}
}
