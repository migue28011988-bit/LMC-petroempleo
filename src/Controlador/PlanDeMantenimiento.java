/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.MantenimientoDAO;
import Modelo.Mantenimiento;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author PC
 */
public class PlanDeMantenimiento {
    
    public PlanDeMantenimiento(){
        DaoMantenimiento= new MantenimientoDAO();
        planMantenimiento= new LinkedList<>();
    }
    
    /**
    * Obtiene el plan realizado que está guardado en la Base de Datos
     * @param busqueda - contiene la cadena buscada para filtrar resultados
     * @return Lista con todos los equipos y la planificación de su mantenimiento
    */
    public List<Object[]> obtenerPlan(String busqueda){
        List<Mantenimiento> planDB= DaoMantenimiento.getPlan();
        planMantenimiento.clear();
        for(Mantenimiento equipo : planDB){
            if(busqueda != null){
                busqueda= busqueda.toLowerCase();
                if(!equipo.getEquipo().toLowerCase().contains(busqueda) && 
                        !equipo.getNumInv().toString().contains(busqueda) && 
                        !equipo.getArea().toLowerCase().contains(busqueda))
                    continue;
            }
            planMantenimiento.add(new Object[]{equipo.getEquipo(), equipo.getNumInv(), equipo.getArea(), 
                equipo.getPrimerMantenimiento().equals("General")? "X" : "", 
                equipo.getPrimerMantenimiento().equals("Parcial")? "X" : "", 
                equipo.getPrimeraFechaPlan(), equipo.getPrimeraFechaReal(), 
                equipo.getSegundoMantenimiento().equals("General")? "X" : "", 
                equipo.getSegundoMantenimiento().equals("Parcial")? "X" : "", 
                equipo.getSegundaFechaPlan(), equipo.getSegundaFechaReal(), 
                equipo.getCump()});
        }
        return planMantenimiento;
    }
    
    /**
     * Crea un nuevo plan de mantenimiento y lo actualiza con datos de la vista,
     * si no hay datos solo lo crea
     * @return devuelve el estado de la actualización o creación
    */
    public String actualizarPlan(){
        DaoMantenimiento.borrarPlan();
        DaoMantenimiento.createPlan();
        List<Mantenimiento> planDB= DaoMantenimiento.getPlan();
        if(planDB.isEmpty()) return "No hay equipos registrados en la BD para el Plan de mantenimiento";
        else if(planMantenimiento.isEmpty()) return "Plan de mantenimiento creado";
        for(Object[] equipo : planMantenimiento){
            for(Mantenimiento e : planDB){
                if(equipo[0].toString().equals(e.getEquipo()) && 
                        (Integer.valueOf(equipo[1].toString())).equals(e.getNumInv()) && 
                        equipo[2].toString().equals(e.getArea())){
                    String tipoMant= "";
                    if(equipo[3].toString().equals("X")) tipoMant= "General";
                    else if(equipo[4].toString().equals("X")) tipoMant= "Parcial";
                    e.setPrimerMantenimiento(tipoMant);
                    e.setPrimeraFechaPlan(equipo[5].toString());
                    e.setPrimeraFechaReal(equipo[6].toString());
                    tipoMant= "";
                    if(equipo[7].toString().equals("X")) tipoMant= "General";
                    else if(equipo[8].toString().equals("X")) tipoMant= "Parcial";
                    e.setSegundoMantenimiento(tipoMant);
                    e.setSegundaFechaPlan(equipo[9].toString());
                    e.setSegundaFechaReal(equipo[10].toString());
                    if(!e.getPrimerMantenimiento().equals("") && !e.getSegundoMantenimiento().equals(""))
                        e.setCump(true);
                    else e.setCump(false);
                    break;
                }
            }
        }
        DaoMantenimiento.actualizarPlan(planDB);
        return "Plan actualizado";
    }
    
    /**
     * Borra todo el Plan de la Base de Datos
     * @return devuelve el estado de la eliminación
    */
    public String borrarPlan(){
        if(DaoMantenimiento.borrarPlan()) 
            return "Plan de mantenimiento eliminado";
        else return "No existe ningún plan para eliminar";
    }
    
    /**
     * Calcula y obtiene el índice de cumplimiento del mantenimiento realizado
     * @return el índice de cumplimiento con valor -1, 0, 3 o 5
     */
    public Integer obtenerIndiceCump(){
        Integer indice;
        int parte= 0;
        int total= planMantenimiento.size();
        if(total == 0) return -1;
        for(Object[] equipo : planMantenimiento)
            if((boolean)equipo[11] == true) parte++;
        double porCiento= parte * 100 / total;
        if(porCiento == 0) indice= -1;
        else if(porCiento <= 80) indice= 0;
        else if(porCiento >= 81 && porCiento <= 99) indice= 3;
        else indice= 5;
        return indice;
    }
    
    /**
     * calcula el total de todos los mantenimientos realizados a todos los equipos
     * @return el total como un equipo más
     */
    public Object[] obtenerTotal(){
        if(planMantenimiento.isEmpty()) return null;
        Object[] Total= new Object[11];
        List<String> areas= new LinkedList<>();
        int[] cant= new int[]{0, 0, 0, 0, 0};
        for(Object[] equipo : planMantenimiento){
            cant[0]+= 1;
            if(!areas.contains(equipo[2].toString())) areas.add(equipo[2].toString());
            if(equipo[3].equals("X")) cant[1]+= 1;
            if(equipo[4].equals("X")) cant[2]+= 1;
            if(equipo[7].equals("X")) cant[3]+= 1;
            if(equipo[8].equals("X")) cant[4]+= 1;
        }
        Total[0]= "Total";
        Total[1]= cant[0];
        Total[2]= areas.size();
        Total[3]= cant[1];
        Total[4]= cant[2];
        Total[5]= "Plan";
        Total[6]= "Real";
        Total[7]= cant[3];
        Total[8]= cant[4];
        Total[9]= "Plan";
        Total[10]= "Real";
        return Total;
    }
    
    private final MantenimientoDAO DaoMantenimiento;
    private final List<Object[]> planMantenimiento;
}
