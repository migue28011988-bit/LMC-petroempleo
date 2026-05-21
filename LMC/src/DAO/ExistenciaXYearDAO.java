/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.existenciaXYear;
import jakarta.persistence.RollbackException;
import java.util.List;
import java.util.NoSuchElementException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * La tabla creada con esta clase contiene las cantidades de equipos en existencia
 * por los últimos 5 años, cada vez que se agrega o elimina un equipo de cualquier tipo
 * se agrega o sustrae esa cantidad en el año actual.
 * Es necesario para crear el reporte de gráficos de barras que representa dicha existencia
 * 
 * @author PC
 */
public class ExistenciaXYearDAO {
    
    public static final int neutro= 0;
    public static final int agregar= 1;
    public static final int sustraer= -1;
    
    public static void actualizarExistencia(Class<?> tipoEquipo, int op){
        //<editor-fold defaultstate="collapsed" desc="cada vez se se agrega o elimina un equipo en la BD se actualizan estas cantidades en la tabla de existencias">
        Session session= hibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx= session.beginTransaction();
        //se ordena por año para tener el último año al final de la lista
        Query<existenciaXYear> consulta= session.createQuery("from existenciaXYear e order by e.year",
                existenciaXYear.class);
        
        try{
            //se obtiene la lista con todos los años
            List<existenciaXYear> existenciaAll= consulta.getResultList();
            
            //se obtiene las cantidades del último año
            existenciaXYear ultimaExistencia= existenciaAll.getLast();
            
            //se obtiene el año de la fecha actual
            Integer yearActual= Integer.valueOf(java.time.LocalDate.now().toString().split("-")[0]);
            
            //se obtiene el año de la última existencia
            Integer ultimoYear= ultimaExistencia.getYear();
            
            //se obtiene la cantidad de años (tuplas) guardadas en la tabla de la BD que debe ser como máximo 5
            long cantYears= existenciaAll.size();
            
            /*si el año actual es igual al último año de la BD 
            significa que a ese año si se le pueden actualizar sus cantidades,
            solo al año actual se le pueden actualizar sus cantidades, no a los años
            anteriores porque se supone que ya pasaron*/
            if(yearActual.equals(ultimoYear)){
                if(op == 1) agregarCantidad(tipoEquipo, ultimaExistencia);
                else if(op == -1) sustraerCantidad(tipoEquipo, ultimaExistencia);
                session.merge(ultimaExistencia);
            }
            /*si el año actual no es igual al último año de la BD
            se le van agregando años sucesivos hasta llegar al actual
            el cuál si se le pueden actualizar sus cantidades*/
            else{
                /*se obtiene la diferencia entre el año actual y el último año
                de la BD y ese es exactamente la cantidad de años que se agregan
                a la BD terminando en el año actual como último año agregado*/
                int cantYearsIntermedio= yearActual - ultimoYear;
                for(int i= 0; i < cantYearsIntermedio; i++){
                    if(existenciaAll.isEmpty()){
                        consulta= session.createQuery("from existenciaXYear e order by e.year",
                                existenciaXYear.class);
                        existenciaAll= consulta.getResultList();
                    }
                
                    /*si la cantidad de años guardados en la BD es 5 y como se va a agregar uno nuevo
                    entonces se elimina el primer año porque solo debe existir 5 años en la BD*/
                    if(cantYears == 5){
                        //se obtiene las cantidades del primer año
                        existenciaXYear primeraExistencia= existenciaAll.removeFirst();
                        session.remove(primeraExistencia);
                    }
                    else if(cantYears < 5)
                        cantYears++;
                
                    //se crea un nuevo año
                    existenciaXYear nuevaExistencia= new existenciaXYear();
                    nuevaExistencia.setYear(++ultimoYear);
                
                    /*siempre el último año tiene las últimas cantidades de equipos en existencia,
                    por eso se toman esas cantidades y se le agregan al año nuevo
                    y entonces se actualiza el  nuevo año con las cantidades actuales reales*/
                    nuevaExistencia.setCantCompu(ultimaExistencia.getCantCompu());
                    nuevaExistencia.setCantDispRED(ultimaExistencia.getCantDispRED());
                    nuevaExistencia.setCantTelfMovil(ultimaExistencia.getCantTelfMovil());
                    nuevaExistencia.setCantTelfVOIP(ultimaExistencia.getCantTelfVOIP());
                    nuevaExistencia.setTotal(ultimaExistencia.getTotal());
                
                    if(ultimoYear.equals(yearActual)){
                        if(op == 1) agregarCantidad(tipoEquipo, nuevaExistencia);
                        else if(op == -1) sustraerCantidad(tipoEquipo, nuevaExistencia);
                    }
                
                    session.persist(nuevaExistencia);
                }
            }
            tx.commit();
        }catch(RollbackException e){
            if(tx != null) tx.rollback();
        }catch(NoSuchElementException e){
            try{
                //se obtiene el año de la fecha actual
                Integer yearActual= Integer.valueOf(java.time.LocalDate.now().toString().split("-")[0]);
                
                //se crea un nuevo año
                existenciaXYear nuevaExistencia= new existenciaXYear();
                nuevaExistencia.setYear(yearActual);
                
                if(op == 1) agregarCantidad(tipoEquipo, nuevaExistencia);
                else if(op == -1) sustraerCantidad(tipoEquipo, nuevaExistencia);
                
                session.persist(nuevaExistencia);
                
                tx.commit();
            }catch(RollbackException ex){
                if(tx != null) tx.rollback();
            }
        }
        finally{
            session.close();
        }
        //</editor-fold>
    }
    
    private static void agregarCantidad(Class<?> tipoEquipo, existenciaXYear Existencia){
        if(tipoEquipo.equals(Modelo.Computadora.class))
            Existencia.addCantCompu();
        else if(tipoEquipo.equals(Modelo.DispRED.class))
            Existencia.addCantDispRED();
        else if(tipoEquipo.equals(Modelo.TelfMovil.class))
            Existencia.addCantTelfMovil();
        else if(tipoEquipo.equals(Modelo.TelfVOIP.class))
            Existencia.addCantTelfVOIP();
        Existencia.addTotal();
    }
    
    private static void sustraerCantidad(Class<?> tipoEquipo, existenciaXYear Existencia){
        if(tipoEquipo.equals(Modelo.Computadora.class))
            Existencia.removeCantCompu();
        else if(tipoEquipo.equals(Modelo.DispRED.class))
            Existencia.removeCantDispRED();
        else if(tipoEquipo.equals(Modelo.TelfMovil.class))
            Existencia.removeCantTelfMovil();
        else if(tipoEquipo.equals(Modelo.TelfVOIP.class))
            Existencia.removeCantTelfVOIP();
        Existencia.removeTotal();
    }
    
}
