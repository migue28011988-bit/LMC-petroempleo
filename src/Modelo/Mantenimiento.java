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
public class Mantenimiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String equipo;
    private Integer numInventario;
    private String primerMantenimiento;
    private String primeraFechaPlan;
    private String primeraFechaReal;
    private String segundoMantenimiento;
    private String segundaFechaPlan;
    private String segundaFechaReal;
    private String area;
    private boolean cumplimiento;
    
    public Mantenimiento(){}
    
    public Integer getId(){return id;}
    public Integer getNumInv(){return numInventario;}
    public String getEquipo(){return equipo;}
    public String getPrimerMantenimiento(){return primerMantenimiento;}
    public String getPrimeraFechaPlan(){return primeraFechaPlan;}
    public String getPrimeraFechaReal(){return primeraFechaReal;}
    public String getSegundoMantenimiento(){return segundoMantenimiento;}
    public String getSegundaFechaPlan(){return segundaFechaPlan;}
    public String getSegundaFechaReal(){return segundaFechaReal;}
    public String getArea(){return area;}
    public boolean getCump(){return cumplimiento;}
    
    public void setId(Integer var){this.id= var;}
    public void setNumInv(Integer var){this.numInventario= var;}
    public void setEquipo(String var){this.equipo= var;}
    public void setPrimerMantenimiento(String var){this.primerMantenimiento= var;}
    public void setPrimeraFechaPlan(String var){this.primeraFechaPlan= var;}
    public void setPrimeraFechaReal(String var){this.primeraFechaReal= var;}
    public void setSegundoMantenimiento(String var){this.segundoMantenimiento= var;}
    public void setSegundaFechaPlan(String var){this.segundaFechaPlan= var;}
    public void setSegundaFechaReal(String var){this.segundaFechaReal= var;}
    public void setArea(String var){this.area= var;}
    public void setCump(boolean var){this.cumplimiento= var;}
}
