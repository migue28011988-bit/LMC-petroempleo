/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Administrador;
import jakarta.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author PC
 */
public class AdministradorDAO {
    
    public Administrador getAdmin(String nombre){
        Administrador admin;
        Session session= hibernateSessionFactory.getSessionFactory().openSession();
        Query<Administrador> consulta= session.createQuery("from Administrador admin where admin.Administrador= :ADM",
                Administrador.class);
        consulta.setParameter("ADM", nombre);
        try{
            admin= consulta.getSingleResult();
        }
        catch(NoResultException e){
            admin= null;
        }
        finally{
            session.close();
        }
        return admin;
    }
    
    public boolean isEmpty(){
        boolean Is;
        try(Session session = hibernateSessionFactory.getSessionFactory().openSession()){
            Query<Administrador> consulta= session.createQuery("from Administrador admin", Administrador.class);
            long cantAdmin= consulta.getResultCount();
            Is= (cantAdmin == 0);
        }
        return Is;
    }
    
    public List<Administrador> getTodos(){
        List<Administrador> todos= new LinkedList();
        try(Session session = hibernateSessionFactory.getSessionFactory().openSession()) {
            Query<Administrador> consulta= session.createQuery("from Administrador adm", Administrador.class);
            todos= consulta.getResultList();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return todos;
    }
    
    /*actualiza las cuentas de los administradores que existen en la lista "cuentas"
    y crea las que no existen (las que se añadieron a la lista "cuentas"),
    también elimina de la BD las que se hayan eliminado de la lista "cuentas"*/
    public void updateAndCreateAndDelete(List<Administrador> cuentas){
        //obtiene todas las cuentas de administradores de la base de datos
        List<Administrador> todos= getTodos();
        Session session= hibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx= session.beginTransaction();
        try{
            cuentas.forEach((cuenta)->{
                session.merge(cuenta); //actualiza la cuenta si existe o la crea si no existe
                if(cuenta.getId() == null) return;
                for(Administrador admin : todos)
                   if(cuenta.getId().equals(admin.getId())){
                       todos.remove(admin);
                       break;
                    }
            });
            //al final "todos" solo contendrá las cuentas que hayan sido eliminadas de "cuentas"
            //por lo tanto hay que eliminarlas de la base de datos también, usando "todos"
            todos.forEach((cuenta)->{
                session.remove(cuenta);
            });
            tx.commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            if(tx != null) tx.rollback();
        }
        finally{
            session.close();
        }
    }
}
