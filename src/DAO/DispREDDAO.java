/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Computadora;
import Modelo.DispRED;
import Modelo.TelfVOIP;
import jakarta.persistence.NoResultException;
import jakarta.persistence.RollbackException;
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
public class DispREDDAO {
    
    private final SessionFactory factory;
    
    public DispREDDAO(){
        StandardServiceRegistry ssr= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta= new MetadataSources(ssr).getMetadataBuilder().build();
        factory= meta.getSessionFactoryBuilder().build();
    }
    
    public String agregarOActualizar(DispRED equipo){
        String estado= "";
        String clase= "";
        //<editor-fold defaultstate="collapsed" desc="Agregar el equipo a la BD o  actualizarlo">
        boolean existe;
        Session session= factory.openSession();
        Transaction tx= session.beginTransaction();
        DispRED persist= new DispRED();
        Query<DispRED> consulta= session.createQuery("from DispRED e where e.ip=:IP", DispRED.class);
        consulta.setParameter("IP", equipo.getIP());
        try{
            persist= consulta.getSingleResult();
            clase= persist.getClass().getName();
            existe= true;
        }catch(NoResultException e){
            try{
                Computadora persist2= session.createQuery("from Computadora e where e.IP=:IP", Computadora.class).
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
            if(equipo.getId() == null) {
                if(existe)
                    estado= "No se puede registrar, equipo encontrado en la BD con el mismo IP";
                else{
                    session.persist(equipo);
                    estado= "Equipo correctamente registrado en la Base de datos";
                }
            }
            else {
                if(existe && (!equipo.getClass().getName().equals(clase)
                        || !equipo.getId().equals(persist.getId())))
                    estado= "No se puede actualizar, hay otro equipo encontrado en la BD con el mismo IP";
                else{
                    session.merge(equipo);
                    estado= "Equipo actualizado";
                }
            }
            tx.commit();
        }catch(RollbackException e){
            if(tx != null) tx.rollback();
        }catch(NoResultException e){
            System.out.println(e.getMessage());
        }
        finally{
            session.close();
        }
        //</editor-fold>
        return estado;
    }
    
    public List<DispRED> getTodos(){
        List<DispRED> todos;
        try(Session session= factory.openSession()){
            Query<DispRED> consulta= session.createQuery("from DispRED e", DispRED.class);
            todos= consulta.getResultList();
        }
        return todos;
    }
    
    public String eliminarUno(String ip){
        Session session= factory.openSession();
        Transaction tx= session.beginTransaction();
        DispRED persist;
        String estado= "";
        Query<DispRED> consulta= session.createQuery("from DispRED e where e.ip=:IP", DispRED.class);
        consulta.setParameter("IP", ip);
        try{
            persist= consulta.getSingleResult();
            session.remove(persist);
            estado= "Equipo eliminado de la BD";
            tx.commit();
        }catch(NoResultException e){
            System.out.println(e.getMessage());
        }catch(RollbackException e){
            if(tx != null) tx.rollback();
        }
        finally{
            session.close();
        }
        return estado;
    }
    
    public DispRED getUno(String ip){
        Session session= factory.openSession();
        Transaction tx= session.beginTransaction();
        DispRED equipo= null;
        Query<DispRED> consulta= session.createQuery("from DispRED e where e.ip=:IP", DispRED.class);
        consulta.setParameter("IP", ip);
        try{
            equipo= consulta.getSingleResult();
            tx.commit();
        }catch(NoResultException e){
            System.out.println(e.getMessage());
        }catch(RollbackException e){
            if(tx != null) tx.rollback();
        }
        finally{
            session.close();
        }
        return equipo;
    }
}
