/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.TelfMovil;
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
public class TelfMovilDAO {
    
    private final SessionFactory factory;

    public TelfMovilDAO() {
        StandardServiceRegistry ssr= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta= new MetadataSources(ssr).getMetadataBuilder().build();
        factory= meta.getSessionFactoryBuilder().build();
    }
    
    public String agregarOActualizar(TelfMovil equipo){
        String estado= "";
        //<editor-fold defaultstate="collapsed" desc="Agregar el equipo a la BD o  actualizarlo">
        boolean existe;
        Session session= factory.openSession();
        Transaction tx= session.beginTransaction();
        TelfMovil persist= new TelfMovil();
        Query<TelfMovil> consulta= session.createQuery("from TelfMovil e where e.numeroTelf=:numero", TelfMovil.class);
        consulta.setParameter("numero", equipo.getNumTelf());
        try{
            persist= consulta.getSingleResult();
            existe= true;
        }catch(NoResultException e){
            existe= false;
        }
        try{
            if(equipo.getId() == null) {
                if(existe)
                    estado= "No se puede registrar, equipo encontrado en la BD con el mismo número telefónico";
                else{
                    session.persist(equipo);
                    estado= "Equipo correctamente registrado en la Base de datos";
                }
            }
            else {
                if(existe && !equipo.getId().equals(persist.getId()))
                    estado= "No se puede actualizar, hay otro equipo encontrado en la BD con el mismo número telefónico";
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
    
    public List<TelfMovil> getTodos(){
        List<TelfMovil> todos;
        try(Session session= factory.openSession()){
            Query<TelfMovil> consulta= session.createQuery("from TelfMovil e left join e.usuario", TelfMovil.class);
            todos= consulta.getResultList();
        }
        return todos;
    }
    
    public String eliminarUno(String numTelf){
        Session session= factory.openSession();
        Transaction tx= session.beginTransaction();
        TelfMovil persist;
        String estado= "";
        Query<TelfMovil> consulta= session.createQuery("from TelfMovil e where e.numeroTelf=:numero", TelfMovil.class);
        consulta.setParameter("numero", numTelf);
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
    
    public TelfMovil getUno(String numTelf){
        Session session= factory.openSession();
        Transaction tx= session.beginTransaction();
        TelfMovil movil= null;
        Query<TelfMovil> consulta= session.createQuery("from TelfMovil e where e.numeroTelf=:numero", TelfMovil.class);
        consulta.setParameter("numero", numTelf);
        try{
            movil= consulta.getSingleResult();
            tx.commit();
        }catch(NoResultException e){
            System.out.println(e.getMessage());
        }catch(RollbackException e){
            if(tx != null) tx.rollback();
        }
        finally{
            session.close();
        }
        return movil;
    }
}
