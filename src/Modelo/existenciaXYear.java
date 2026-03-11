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
public class existenciaXYear {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    private Integer year;
    
    private Integer cantCompu;
    private Integer cantTelfVOIP;
    private Integer cantTelfMovil;
    private Integer cantDispRED;
    private Integer total;
    
    public existenciaXYear(){
        cantCompu= 0;
        cantDispRED= 0;
        cantTelfMovil= 0;
        cantTelfVOIP= 0;
        total= 0;
    }
    
    public void addCantCompu(){cantCompu++;}
    public void addCantTelfVOIP(){cantTelfVOIP++;}
    public void addCantTelfMovil(){cantTelfMovil++;}
    public void addCantDispRED(){cantDispRED++;}
    public void addTotal(){total++;}
    
    public void removeCantCompu(){if(cantCompu > 0) cantCompu--;}
    public void removeCantTelfVOIP(){if(cantTelfVOIP > 0) cantTelfVOIP--;}
    public void removeCantTelfMovil(){if(cantTelfMovil > 0) cantTelfMovil--;}
    public void removeCantDispRED(){if(cantDispRED > 0) cantDispRED--;}
    public void removeTotal(){if(total > 0) total--;}
    
    public Integer getId(){return id;}
    public Integer getYear(){return year;}
    public Integer getCantCompu(){return cantCompu;}
    public Integer getCantTelfVOIP(){return cantTelfVOIP;}
    public Integer getCantTelfMovil(){return cantTelfMovil;}
    public Integer getCantDispRED(){return cantDispRED;}
    public Integer getTotal(){return total;}
    
    public void setId(Integer var){id= var;}
    public void setYear(Integer var){year= var;}
    public void setCantCompu(Integer var){cantCompu= var;}
    public void setCantTelfVOIP(Integer var){cantTelfVOIP= var;}
    public void setCantTelfMovil(Integer var){cantTelfMovil= var;}
    public void setCantDispRED(Integer var){cantDispRED= var;}
    public void setTotal(Integer var){total= var;}
}
