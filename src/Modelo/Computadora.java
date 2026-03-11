/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;
import java.util.LinkedList;
import jakarta.persistence.*;

/**
 *
 * @author PC
 */

@Entity
public class Computadora {
    
    @Id   
    @GeneratedValue(strategy= GenerationType.AUTO)  
    private Integer id;
    
    private String tipo; //laptop, desktop o servidor
    private String estado; //activo, en reparación, obsoleto
    private String departamento;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(unique = true, nullable = false)
    private final Usuario usuario;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(unique = true, nullable = false)
    private final sistemaOperativo SO;
    
    private String nombreRed;
    
    @Column(unique = true)
    private String IP;
    
    private Integer numSello;
    private Integer numLlavero;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="equipoId", nullable = false)
    @OrderColumn(name="index")
    private final List<Logins> logins;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="equipoId", nullable = false)
    @OrderColumn(name="index")
    private final List<Cambios> cambios;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="equipoId", nullable = false)  
    @OrderColumn(name="index") 
    private final List<hardware> componentes;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)  
    @JoinColumn(name="equipoId", nullable = false)  
    @OrderColumn(name="index") 
    private final List<software> aplicaciones;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="equipoId", nullable = false)  
    @OrderColumn(name="index")
    private final List<Incidencias> incidencias;
    
    public Computadora(){
        componentes= new LinkedList<>();
        aplicaciones= new LinkedList<>();
        cambios= new LinkedList<>();
        logins= new LinkedList<>();
        incidencias= new LinkedList<>();
        usuario= new Usuario();
        SO= new sistemaOperativo();
    }
    
    public Integer getId(){return this.id;}
    public String getTipo(){return this.tipo;}
    public String getEstado(){return this.estado;}
    public String getDepart(){return this.departamento;}
    public Usuario getUsuario(){return this.usuario;}
    public sistemaOperativo getSO(){return this.SO;}
    public String getNombreRED(){return this.nombreRed;}
    public String getIP(){return this.IP;}
    public Integer getNumSello(){return this.numSello;}
    public Integer getNumLlavero(){return this.numLlavero;}
    public List<hardware> getComponentes(){return this.componentes;}
    public List<software> getAplicaciones(){return this.aplicaciones;}
    public List<Logins> getLogins(){return this.logins;}
    public List<Cambios> getCambios(){return this.cambios;}
    public List<Incidencias> getIncidencias(){return this.incidencias;}
    
    public void setId(Integer var){this.id= var;}
    public void setTipo(String var){this.tipo= var;}
    public void setEstado(String var){this.estado= var;}
    public void setDepart(String var){this.departamento= var;}
    public void setUsuario(String nombre, String cargo, Integer id){
        this.usuario.setNombre(nombre);
        this.usuario.setCargo(cargo);
        this.usuario.setId(id);
    }
    public void setSO(String nombre, String edicion, String version, Integer id){
        SO.setNombre(nombre);
        SO.setEdicion(edicion);
        SO.setVersion(version);
        SO.setId(id);
    }
    public void setNombreRED(String var){this.nombreRed= var;}
    public void setIP(String var){this.IP= var;}
    public void setNumSello(Integer var){this.numSello= var;}
    public void setNumLlavero(Integer var){this.numLlavero= var;}
    public void setComponente(String tipo, String marca, String modelo, String numSerie, 
            Integer numInv, String capacidad, Integer id){
        //<editor-fold defaultstate="collapsed" desc="código">
        hardware componente= new hardware();
        componente.setTipo(tipo);
        componente.setMarca(marca);
        componente.setModelo(modelo);
        componente.setNumSerie(numSerie);
        componente.setNumInv(numInv);
        componente.setCapac(capacidad);
        componente.setId(id);
        this.componentes.add(componente);
        //</editor-fold>
    }
    public void modComponente(String tipo, String propiedad, Object val){
        //<editor-fold defaultstate="collapsed" desc="código">
        this.componentes.forEach((X) -> {
            if (X.getTipo().equals(tipo)){
                switch(propiedad){
                    case "marca" -> X.setMarca((String)val);
                    case "modelo" -> X.setModelo((String)val);
                    case "numSerie" -> X.setNumSerie((String)val);
                    case "numInventario" -> X.setNumInv((Integer)val);
                    case "capacidad" -> {
                        if(tipo.equals("RAM") || tipo.equals("Disco Duro")) X.setCapac((String)val);
                        else System.out.println("el dispositivo no es de almacenamiento");
                    }
                }
            }
        });
        //</editor-fold>
    }
    public void setAplicacion(String nombre, String version, String fecha, String tam, Integer id){
        software aplicacion= new software();
        aplicacion.setNombre(nombre);
        aplicacion.setVer(version);
        aplicacion.setFecha(fecha);
        aplicacion.setSize(tam);
        aplicacion.setId(id);
        this.aplicaciones.add(aplicacion);
    }
    public void modAplicacion(String nombre, String propiedad, Object val){
        //<editor-fold defaultstate="collapsed" desc="código">
        this.aplicaciones.forEach((X)->{
            if(X.getNombre().equals(nombre)){
                switch(propiedad){
                    case "fecha" -> X.setFecha((String)val);
                    case "version" -> X.setVer((String)val);
                }
            }
        });
        //</editor-fold>
    }
    public void setLogin(String cuenta, String fecha, String hora, String tiempoActivo, Integer id){
        Logins user= new Logins();
        user.setFecha(fecha);
        user.setHora(hora);
        user.setCuenta(cuenta);
        user.setTiempoActivo(tiempoActivo);
        user.setId(id);
        logins.add(user);
    }
    public void setCambio(String tipo, String componenteAnterior, String componenteActual, 
            String fecha, String hora, Integer id){
        Cambios cambio= new Cambios();
        cambio.setComponenteActual(componenteActual);
        cambio.setComponenteAnterior(componenteAnterior);
        cambio.setFecha(fecha);
        cambio.setHora(hora);
        cambio.setTipo(tipo);
        cambio.setId(id);
        cambios.add(cambio);
    }
    public void setIncidencia(String descripcion, String fecha, String hora, Integer id){
        Incidencias incidencia= new Incidencias();
        incidencia.setDesc(descripcion);
        incidencia.setFecha(fecha);
        incidencia.setHora(hora);
        incidencia.setId(id);
        incidencias.add(incidencia);
    }
}
