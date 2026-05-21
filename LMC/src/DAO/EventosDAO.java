/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Eventos;
import jakarta.persistence.NoResultException;
import jakarta.persistence.RollbackException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author PC
 */
public class EventosDAO {
    
    public List<Object[]> getTodos(){
        List<Object[]> eventos= new LinkedList<>();
        try(Session session= hibernateSessionFactory.getSessionFactory().openSession()){
            List<Eventos> events= session.createQuery("from Eventos e order by e.fecha, e.hora", Eventos.class).getResultList();
            for(Eventos event : events)
                eventos.add(new Object[]{event.getFecha(),event.getHora(),event.getDesc()});
        }
        return eventos;
    }
    
    public int borrarTodos(){
        Session session= hibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx= session.beginTransaction();
        int estado;
        try{
            List<Eventos> events= session.createQuery("from Eventos e", Eventos.class).getResultList();
            if(events.isEmpty())
                throw new Exception();
            for(Eventos event : events)
                session.remove(event);
            tx.commit();
            estado= 1;
        }catch(RollbackException e){
            if(tx != null) tx.rollback();
            estado= -1;
        }catch(Exception e){
            estado= 0;
        }
        finally{
            session.close();
        }
        return estado;
    }
    
    public int borrarUno(Object[] evento){
        Session session= hibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx= session.beginTransaction();
        int estado;
        try{
            Eventos event= session.createQuery("from Eventos e where e.fecha in :evento "+
                    "and e.hora in :evento and e.descripcion in :evento", 
                    Eventos.class).setParameterList("evento", evento).getSingleResult();
            session.remove(event);
            tx.commit();
            estado= 1;
        }catch(RollbackException e){
            if(tx != null) tx.rollback();
            estado= -1;
        }catch(NoResultException e){
            System.out.println(e.getMessage());
            estado= 0;
        }
        finally{
            session.close();
        }
        return estado;
    }
    
    public static void agregarUno(String descripcion){
        Session session= hibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx= session.beginTransaction();
        try{
            Eventos nuevo= new Eventos();
            nuevo.setDesc(descripcion);
            nuevo.setFecha(LocalDate.now().toString());
            nuevo.setHora(LocalTime.now().toString().split("\\.")[0]);
            session.persist(nuevo);
            tx.commit();
        }catch(RollbackException e){
            if(tx != null) tx.rollback();
        }
        finally{
            session.close();
        }
    }
}
