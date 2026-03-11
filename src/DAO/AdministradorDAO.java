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
public class AdministradorDAO {
    
    private final SessionFactory factory;
    
    public AdministradorDAO(){
        StandardServiceRegistry ssr= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta= new MetadataSources(ssr).getMetadataBuilder().build();
        factory= meta.getSessionFactoryBuilder().build();
    }
    
    public boolean isAdmin(String nombre, String password){
        boolean Is;
        Session session= factory.openSession();
        Query<Administrador> consulta= session.createQuery("from Administrador admin where admin.Administrador= :ADM", Administrador.class);
        consulta.setParameter("ADM", nombre);
        try{
            Administrador uno= consulta.getSingleResult();
            Is= uno.getPassword().equals(password);
        }
        catch(NoResultException e){
            Is= false;
        }
        finally{
            session.close();
        }
        return Is;
    }
    public boolean isSuperAdmin(String nombre, String password){
        boolean Is= isAdmin(nombre, password);
        if(Is){
            try (Session session = factory.openSession()) {
                Query<Administrador> consulta= session.createQuery("from Administrador admin where admin.Administrador= :ADM", Administrador.class);
                consulta.setParameter("ADM", nombre);
                Administrador uno= consulta.getSingleResult();
                Is= uno.getSuper();
            }
        }
        return Is;
    }
    public boolean isEmpty(){
        boolean Is;
        try(Session session = factory.openSession()){
            Query<Administrador> consulta= session.createQuery("from Administrador admin", Administrador.class);
            long cantAdmin= consulta.getResultCount();
            Is= (cantAdmin == 0);
        }
        return Is;
    }
    public List<Administrador> getTodos(){
        List<Administrador> todos= new LinkedList();
        try(Session session = factory.openSession()) {
            Query<Administrador> consulta= session.createQuery("from Administrador adm", Administrador.class);
            todos= consulta.getResultList();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return todos;
    }
    //actualiza las cuentas de los administradores que existen y crea las que no existen
    public void updateAndCreateAndDelete(List<Administrador> cuentas){
        //obtiene todas las cuentas de administradores de la base de datos
        List<Administrador> todos= getTodos();
        Session session= factory.openSession();
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
