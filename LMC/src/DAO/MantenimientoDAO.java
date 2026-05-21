/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Computadora;
import Modelo.Mantenimiento;
import Modelo.TelfVOIP;
import Modelo.hardware;
import jakarta.persistence.RollbackException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author PC
 */
public class MantenimientoDAO {
    
    public List<Mantenimiento> getPlan(){
        List<Mantenimiento> plan;
        try(Session session = hibernateSessionFactory.getSessionFactory().openSession()){
            plan= session.createQuery("from Mantenimiento p", Mantenimiento.class).getResultList();
        }
        return plan;
    }
    
    private boolean planExist(){
        boolean vacio;
        try(Session session = hibernateSessionFactory.getSessionFactory().openSession()){
            long tam= session.createQuery("from Mantenimiento p", Mantenimiento.class).getResultCount();
            vacio= tam > 0;
        }
        return vacio;
    }
    
    public void createPlan(){
        //<editor-fold defaultstate="collapsed" desc="crea un Plan de Mantenimiento cuando la tabla de la BD esta vacía">
        Session session = hibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx= session.beginTransaction();
        try{
            if(planExist()) return;
            List<Computadora> listaCompu= session.createQuery("from Computadora e left join fetch e.componentes",
                    Computadora.class).getResultList();
            for(Computadora compu : listaCompu){
                for(hardware componente : compu.getComponentes()){
                    if(componente.getTipo().equals("Chasis") || componente.getTipo().contains("Monitor") || 
                            componente.getTipo().equals("UPS") || componente.getTipo().equals("Impresora") || 
                            componente.getTipo().equals("Scanner") || componente.getTipo().equals("Fotocopiadora")){
                        Mantenimiento planEquipo= new Mantenimiento();
                        if(componente.getTipo().equals("Chasis"))
                            planEquipo.setEquipo(compu.getTipo());
                        else if(componente.getTipo().contains("Monitor"))
                            planEquipo.setEquipo("Monitor");
                        else
                            planEquipo.setEquipo(componente.getTipo());
                        planEquipo.setNumInv(componente.getNumInv());
                        planEquipo.setArea(compu.getDepart());
                        planEquipo.setPrimerMantenimiento("");
                        planEquipo.setPrimeraFechaPlan("");
                        planEquipo.setPrimeraFechaReal("");
                        planEquipo.setSegundoMantenimiento("");
                        planEquipo.setSegundaFechaPlan("");
                        planEquipo.setSegundaFechaReal("");
                        planEquipo.setCump(false);
                        session.persist(planEquipo);
                    }
                }
            }
            List<TelfVOIP> listaVOIP= session.createQuery("from TelfVOIP e", TelfVOIP.class).getResultList();
            for(TelfVOIP voip : listaVOIP){
                Mantenimiento planEquipo= new Mantenimiento();
                planEquipo.setEquipo("Teléfono VOIP");
                planEquipo.setNumInv(voip.getNumInv());
                planEquipo.setArea(voip.getDepart());
                planEquipo.setPrimerMantenimiento("");
                planEquipo.setPrimeraFechaPlan("");
                planEquipo.setPrimeraFechaReal("");
                planEquipo.setSegundoMantenimiento("");
                planEquipo.setSegundaFechaPlan("");
                planEquipo.setSegundaFechaReal("");
                planEquipo.setCump(false);
                session.persist(planEquipo);
            }
            tx.commit();
        }catch(RollbackException e){
            if(tx != null) tx.rollback();
        }
        finally{
            session.close();
        }
        //</editor-fold>
    }
    
    public void actualizarPlan(List<Mantenimiento> plan){
        Session session= hibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx= session.beginTransaction();
        try{
            for(Mantenimiento equipo : plan){
                if(equipo.getId() != null)
                    session.merge(equipo);
            }
            tx.commit();
        }catch(RollbackException e){
            if(tx != null) tx.rollback();
        }
        finally{
            session.close();
        }
    }
    
    public boolean borrarPlan(){
        boolean borrado= true;
        Session session= hibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx= session.beginTransaction();
        try{
            List<Mantenimiento> plan= session.createQuery("from Mantenimiento e", Mantenimiento.class).getResultList();
            for(Mantenimiento equipo : plan)
                session.remove(equipo);
            if(plan.isEmpty()) borrado= false;
            tx.commit();
        }catch(RollbackException e){
            if(tx != null) tx.rollback();
        }
        finally{
            session.close();
        }
        return borrado;
    }
}
