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
public class TelfVOIPDAO {
    
    private final SessionFactory factory;
    
    public TelfVOIPDAO(){
        StandardServiceRegistry ssr= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta= new MetadataSources(ssr).getMetadataBuilder().build();
        factory= meta.getSessionFactoryBuilder().build();
    }
    
    public String agregarOActualizar(TelfVOIP equipo){
        String estado= "";
        String clase= "";
        String attr= "código";
        for(int i= 0; i < 2; i++){
            //<editor-fold defaultstate="collapsed" desc="Agregar el equipo a la BD o  actualizarlo, se verifica por código y después por IP">
            boolean existe;
            Session session= factory.openSession();
            Transaction tx= session.beginTransaction();
            TelfVOIP persist= new TelfVOIP();
            Query<TelfVOIP> consulta;
            if(i == 0){
                consulta= session.createQuery("from TelfVOIP e where e.codigo=:code", TelfVOIP.class);
                consulta.setParameter("code", equipo.getCodigo());
            }
            else {
                consulta= session.createQuery("from TelfVOIP e where e.IP=:ip", TelfVOIP.class);
                consulta.setParameter("ip", equipo.getIP());
            }       
            try{
                persist= consulta.getSingleResult();
                clase= persist.getClass().getName();
                existe= true;
            }catch(NoResultException e){
                if(i == 0) existe= false;
                else{
                    try{
                        Computadora persist2= session.createQuery("from Computadora e where e.IP=:IP", Computadora.class).
                                setParameter("IP", equipo.getIP()).getSingleResult();
                        clase= persist2.getClass().getName();
                        existe= true;
                    }catch(NoResultException n){
                        try{
                            DispRED persist3= session.createQuery("from DispRED e where e.ip=:IP", DispRED.class).
                                    setParameter("IP", equipo.getIP()).getSingleResult();
                            clase= persist3.getClass().getName();
                            existe= true;
                        }catch(NoResultException v){
                            existe= false;
                        }
                    }
                }
            }
            try{
                if(equipo.getId() == null) {
                    if(existe){
                        estado= "No se puede registrar, equipo encontrado en la BD con el mismo " + attr;
                        if(i == 0) attr= "terminar";
                    }
                    else if(i == 1){
                        session.persist(equipo);
                        estado= "Equipo correctamente registrado en la Base de datos";
                    }
                }
                else {
                    if(existe && (!equipo.getClass().getName().equals(clase) ||
                            !equipo.getId().equals(persist.getId()))){
                        estado= "No se puede actualizar, hay otro equipo encontrado en la BD con el mismo " + attr;
                        if(i == 0) attr= "terminar";
                    }
                    else if(i == 1){
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
            if(attr.equals("terminar")) break;
            attr= "IP";
            //</editor-fold>
        }
        return estado;
    }
    
    public List<TelfVOIP> getTodos(){
        List<TelfVOIP> todos;
        try(Session session= factory.openSession()){
            Query<TelfVOIP> consulta= session.createQuery("from TelfVOIP e left join e.usuario", TelfVOIP.class);
            todos= consulta.getResultList();
        }
        return todos;
    }
    
    public String eliminarUno(Integer codigo){
        Session session= factory.openSession();
        Transaction tx= session.beginTransaction();
        TelfVOIP persist;
        String estado= "";
        Query<TelfVOIP> consulta= session.createQuery("from TelfVOIP e where e.codigo=:code", TelfVOIP.class);
        consulta.setParameter("code", codigo);
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
    
    public TelfVOIP getUno(Integer codigo){
        Session session= factory.openSession();
        Transaction tx= session.beginTransaction();
        TelfVOIP voip= null;
        Query<TelfVOIP> consulta= session.createQuery("from TelfVOIP e where e.codigo=:code", TelfVOIP.class);
        consulta.setParameter("code", codigo);
        try{
            voip= consulta.getSingleResult();
            tx.commit();
        }catch(NoResultException e){
            System.out.println(e.getMessage());
        }catch(RollbackException e){
            if(tx != null) tx.rollback();
        }
        finally{
            session.close();
        }
        return voip;
    }
}
