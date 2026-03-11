/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Computadora;
import Modelo.DispRED;
import Modelo.TelfVOIP;
import jakarta.persistence.NoResultException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

/**
 *
 * @author PC
 */
public class ComputadoraDAO{
    
    private final SessionFactory factory;
    
    public ComputadoraDAO(){
        StandardServiceRegistry ssr= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta= new MetadataSources(ssr).getMetadataBuilder().build();
        factory= meta.getSessionFactoryBuilder().build();
    }
    
    public String agregar(Computadora objeto){return operacionMod(objeto, "INSERT");}
    public String actualizar(Computadora objeto){return operacionMod(objeto, "UPDATE");}
    public String eliminarPorIp(String IP){
        Computadora objeto= new Computadora();
        objeto.setIP(IP);
        return operacionMod(objeto, "DELETE");
    }
    public Computadora getUno(Object idOrIP){
        try(Session session= factory.openSession()){
            Query<Computadora> consulta;
            if(idOrIP instanceof String){
                consulta= session.createQuery("from Computadora e left join fetch e.componentes"+
                        " left join e.SO left join e.usuario where e.IP= :ip", Computadora.class);
                consulta.setParameter("ip", idOrIP);
            }
            else{
                consulta= session.createQuery("from Computadora e left join fetch e.componentes"+
                        " left join e.SO left join e.usuario where e.id= :ID", Computadora.class);
                consulta.setParameter("ID", idOrIP);
            }
            Computadora uno= consulta.getSingleResult();
            consulta= session.createQuery("from Computadora e left join fetch e.aplicaciones where e.id= :equipoId", Computadora.class);
            consulta.setParameter("equipoId", uno.getId());
            uno= consulta.getSingleResult();
            consulta= session.createQuery("from Computadora e left join fetch e.logins where e.id= :equipoId", Computadora.class);
            consulta.setParameter("equipoId", uno.getId());
            uno= consulta.getSingleResult();
            consulta= session.createQuery("from Computadora e left join fetch e.cambios where e.id= :equipoId", Computadora.class);
            consulta.setParameter("equipoId", uno.getId());
            uno= consulta.getSingleResult();
            consulta= session.createQuery("from Computadora e left join fetch e.incidencias where e.id= :equipoId", Computadora.class);
            consulta.setParameter("equipoId", uno.getId());
            uno= consulta.getSingleResult();
            return uno;
        }
        catch(NoResultException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean isInside(int ID){
        Session session= factory.openSession();
        boolean inside= false;
        try{
            Query<Computadora> consulta= session.createQuery("from Computadora e where e.id= :ID", Computadora.class);
            consulta.setParameter("ID", ID);
            Computadora uno= consulta.getSingleResult();
            if(uno != null) inside= true;
        }
        catch(NoResultException e){
            inside= false;
        }
        finally{
            session.close();
        }
        return inside;
    }
    
    public List<Computadora> getTodas(){
        Session session= factory.openSession();
        try{
            Query<Computadora> consulta= session.createQuery("from Computadora e left join fetch e.componentes left join e.SO left join e.usuario", Computadora.class);
            List<Computadora> todos= consulta.getResultList();
            consulta= session.createQuery("from Computadora e left join fetch e.aplicaciones where e in :equipos", Computadora.class);
            consulta.setParameterList("equipos", todos);
            todos= consulta.getResultList();
            consulta= session.createQuery("from Computadora e left join fetch e.logins where e in :equipos", Computadora.class);
            consulta.setParameterList("equipos", todos);
            todos= consulta.getResultList();
            consulta= session.createQuery("from Computadora e left join fetch e.cambios where e in :equipos", Computadora.class);
            consulta.setParameterList("equipos", todos);
            todos= consulta.getResultList();
            consulta= session.createQuery("from Computadora e left join fetch e.incidencias where e in :equipos", Computadora.class);
            consulta.setParameterList("equipos", todos);
            todos= consulta.getResultList();
            return todos;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return null;
    }
    
    private String operacionMod(Computadora equipo, String tipo){
        //<editor-fold defaultstate="collapsed" desc="Agregar, Actualizar o Eliminar el equipo">
        boolean existe;
        String estado="";
        String clase= "";
        Computadora persist= new Computadora();
        Session session= factory.openSession();
        Transaction tx= session.beginTransaction();
        Query<Computadora> consulta= session.createQuery("from Computadora e where e.IP= :ip", Computadora.class);
        consulta.setParameter("ip", equipo.getIP());
        try{
            persist= consulta.getSingleResult();
            clase= persist.getClass().getName();
            existe= true;
        }
        catch(NoResultException e){
            try{
                DispRED persist2= session.createQuery("from DispRED e where e.ip=:IP", DispRED.class).
                    setParameter("IP", equipo.getIP()).getSingleResult();
                clase= persist2.getClass().getName();
                existe= true;
            }catch(NoResultException n){
                try{
                    TelfVOIP persist3= session.createQuery("from TelfVOIP e where e.IP=:IP", TelfVOIP.class).
                            setParameter("IP", equipo.getIP()).getSingleResult();
                    clase= persist3.getClass().getName();
                    existe= true;
                }catch(NoResultException v){
                    existe= false;
                }
            }
        }
        try{
            switch(tipo){
                case "INSERT" -> {
                    if(existe) estado= "Ya existe el equipo en la BD \n Se encontró un equipo con el mismo IP";
                    else {
                        session.persist(equipo);
                        estado= "El equipo se registró correctamente en la BD";   
                    }
                }
                case "UPDATE" -> {
                    if(equipo.getId() == null)
                        estado= "No se puede actualizar, equipo no encontrado en la BD";
                    else{  
                        if(existe && (!equipo.getClass().getName().equals(clase)
                                || !persist.getId().equals(equipo.getId())))
                            estado= "No se puede actualizar\n"+
                                    " Ya existe un equipo con el mismo IP en la BD, por favor cambielo";
                        else {
                            session.merge(equipo);
                            estado= "Equipo actualizado";
                        }
                    }
                }
                case "DELETE" -> {
                    if(existe) {
                        session.remove(persist);
                        estado= "Equipo eliminado";
                    }
                    else estado= "No se puede eliminar, equipo no encontrado en la BD";
                }
            }
            tx.commit();
        }
        catch(Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        //</editor-fold>
        return estado;
    }
}
