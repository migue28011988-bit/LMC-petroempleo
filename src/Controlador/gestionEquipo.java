/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.ComputadoraDAO;
import DAO.DispREDDAO;
import DAO.ExistenciaXYearDAO;
import DAO.TelfMovilDAO;
import DAO.TelfVOIPDAO;
import Modelo.Cambios;
import Modelo.Computadora;
import Modelo.DispRED;
import Modelo.Incidencias;
import Modelo.Logins;
import Modelo.TelfMovil;
import Modelo.TelfVOIP;
import Modelo.hardware;
import Modelo.software;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author PC
 */
public class gestionEquipo {
    
    public gestionEquipo(){
        DaoCompu= new ComputadoraDAO();
        DaoMovil= new TelfMovilDAO();
        DaoVOIP= new TelfVOIPDAO();
        DaoDispRED= new DispREDDAO();
        cambios= false;
        cambiosNuevos= new LinkedList<>();
    }
    
    public List<Object[]> resumenComputadoras(String busqueda){
        List<Computadora> computadoras= DaoCompu.getTodas();
        List<Object[]> filas= new LinkedList<>();
        int ID= 1;
        //<editor-fold defaultstate="collapsed" desc="Filtrado y obtención de datos">
        for(Computadora compu: computadoras){
            Object cpu, ram, SO, usuario;
            cpu= ram= "";
            SO= compu.getSO().getNombre()+" "+compu.getSO().getEdicion()+" "+compu.getSO().getVersion();
            usuario= compu.getUsuario().getNombre();
            for(hardware elem: compu.getComponentes()){
                if(elem.getTipo().equals("CPU")) cpu= elem.getMarca() + " " + elem.getModelo();
                else if(elem.getTipo().equals("RAM")) ram= elem.getMarca() + " " + elem.getModelo() + " "+ elem.getCapac();
            }
            //aqui se realiza un filtrado de equipos segun el valor de "búsqueda"
            //si un equipo tiene algunos de sus atributos (variables String de abajo)
            //con el mismo valor sin case sensitive que "busqueda" se queda sino se ignora
            String nombre= compu.getNombreRED().toLowerCase();
            String tipo= compu.getTipo().toLowerCase();
            String estado= compu.getEstado().toLowerCase();
            String sistemaOp= SO.toString().toLowerCase();
            String departamento= compu.getDepart().toLowerCase();
            String user= usuario.toString().toLowerCase();
            String micro= cpu.toString().toLowerCase();
            String memoria= ram.toString().toLowerCase();
            if(busqueda != null){
                busqueda= busqueda.toLowerCase();
                if(!nombre.contains(busqueda) && !tipo.contains(busqueda) && !estado.contains(busqueda) && 
                        !(compu.getIP()).contains(busqueda) && !sistemaOp.contains(busqueda) && 
                        !departamento.contains(busqueda) && !user.contains(busqueda) && !micro.contains(busqueda) && 
                        !memoria.contains(busqueda) && !(compu.getNumSello().toString()).contains(busqueda))
                    continue;
            }
            //aqui termina el filtrado
            //se crean las filas por cada equipo filtrado
            Object[] fila= new Object[]{ID++, "no", compu.getNombreRED(), compu.getTipo(), compu.getEstado(), compu.getIP(), 
                SO, compu.getDepart(), usuario, cpu, ram, compu.getNumSello()};
            filas.add(fila);
        }
        //</editor-fold>
        return filas;
    }
    
    /*se registran los cambios de hardware y software
    evidentemente solo registra los cambios de equipos ya registrados en la BD, no de los nuevos
    solo para las computadoras*/
    private void registroCambios(Map<String, List<Object>> equipoNew){
        //aqui se realiza el registro de cambios nuevos si el equipo se está actualizando
        //si se está creando por primera vez se sale de la función sin realizar ningún cambio
        //<editor-fold defaultstate="collapsed" desc="si hubo cambios los registra">
        String actualIP= equipoNew.get("IP").get(0).toString() + "." + equipoNew.get("IP").get(1).toString() + "." +
                equipoNew.get("IP").get(2).toString() + "." + equipoNew.get("IP").get(3).toString();
        String IP;
        try{
            IP= equipoNew.remove("antiguaIP").getFirst().toString();
        }
        catch(NullPointerException e){
            IP= actualIP;
        }
        String antiguaIP= IP;
        Map<String, List<Object>> equipoAnterior; //representa al equipo guardado en la BD
        equipoAnterior= obtenerComputadora(antiguaIP);
        if(equipoAnterior == null) return;
        Map<String, List<Object>> equipoActual= new HashMap<>(); //representa al equipo nuevo
        AtomicInteger cantCambios= new AtomicInteger(1);
        equipoNew.forEach((clave, valor)-> {
            if(clave.contains("cambio")) cantCambios.getAndIncrement();
            equipoActual.put(clave, valor);
        });
        equipoAnterior.forEach((clave, valorViejo)->{
            //se ignoran todas las entradas que sean cambios o logins
            if(clave.contains("cambio") || clave.contains("login") || clave.equals("ID"))
                return;
            String componenteAnterior= "";
            for(Object propiedad : valorViejo){
                if(!clave.equals("IP") && valorViejo.size() > 1 && propiedad == valorViejo.getLast())
                    break;
                componenteAnterior+= propiedad.toString() + "; ";
            }
            componenteAnterior= componenteAnterior.substring(0, componenteAnterior.length() - 2);
            //para las apps desinstaladas
            if(clave.contains("app")){
                boolean existe= false;
                for(Map.Entry<String, List<Object>> entrada : equipoActual.entrySet())
                    if(entrada.getKey().contains("app") && 
                            entrada.getValue().getFirst().equals(valorViejo.getFirst())){
                        equipoActual.remove(entrada.getKey());
                        existe= true;
                        break;
                    }
                if(!existe){
                    List<Object> cambio= new LinkedList<>(List.of("Desinstalación de software",
                            componenteAnterior, "nulo", LocalDate.now().toString(), 
                            LocalTime.now().toString().split("\\.")[0]));
                    cambio.add(null); //como el cambio es nuevo, su id es nulo
                    equipoNew.put("cambio" + cantCambios.getAndIncrement(), cambio);
                    cambios= true;
                    cambiosNuevos.add("El usuario de la computadora con el nombre " + 
                            equipoNew.get("Nombre del equipo (RED)").getFirst()
                            + " desintaló un software");
                }
                return;
            }
            //para revisar si hay cambios en las incidencias (modificación de datos o eliminación)
            else if(clave.contains("incidencia")){
                boolean existe= false;
                for(Map.Entry<String, List<Object>> entrada : equipoActual.entrySet())
                    if(entrada.getKey().contains("incidencia") && 
                            entrada.getValue().get(0).equals(valorViejo.get(0)) && 
                            entrada.getValue().get(1).equals(valorViejo.get(1)) &&
                            entrada.getValue().get(2).equals(valorViejo.get(2))){
                        equipoActual.remove(entrada.getKey());
                        existe= true;
                        break;
                    }
                if(!existe) cambios= true;
                return;
            }
            //para los componentes hardware desinstalados
            String componenteActual= "";
            try{
                //si aqui se obtiene null quiere decir que algun componente hardware se desinstaló
                List<Object> valorNuevo= equipoActual.remove(clave);
                for(Object propiedad : valorNuevo){
                    if(!clave.equals("IP") && valorNuevo.size() > 1 && propiedad == valorNuevo.getLast())
                        break;
                    componenteActual+= propiedad.toString() + "; ";
                }
                componenteActual= componenteActual.substring(0, componenteActual.length() - 2);
            }
            catch(NullPointerException e){
                List<Object> cambio= new LinkedList<>(List.of("Desinstalación de " + clave,
                        componenteAnterior, "nulo", LocalDate.now().toString(), 
                        LocalTime.now().toString().split("\\.")[0]));
                cambio.add(null); //como el cambio es nuevo, su id es nulo
                equipoNew.put("cambio" + cantCambios.getAndIncrement(), cambio);
                cambios= true;
                cambiosNuevos.add("El usuario de la computadora con el nombre " + 
                        equipoNew.get("Nombre del equipo (RED)").getFirst()
                        + " desintaló el/la " + clave);
                return;
            }
            //cuando hay un cambio de hardware o propiedad del equipo como el ip, ... etc
            //si dos propiedades son distintas se realiza el registro
            if(!componenteAnterior.equals(componenteActual)){
                if(clave.equals("IP")){
                    componenteAnterior= antiguaIP;
                    componenteActual= actualIP;
                }
                List<Object> cambio= new LinkedList<>(List.of("Cambio de " + clave,
                        componenteAnterior, componenteActual, LocalDate.now().toString(),
                        LocalTime.now().toString().split("\\.")[0]));
                cambio.add(null); //como el cambio es nuevo, su id es nulo
                equipoNew.put("cambio" + cantCambios.getAndIncrement(), cambio);
                cambios= true;
                List<String> prop= new ArrayList<>(List.of("Nombre del equipo (RED)",
                        "IP", "No. Sello", "No. Llavero", "Departamento", "Estado",
                        "Tipo", "Usuario", "Sistema Operativo"));
                if(!prop.contains(clave))
                    cambiosNuevos.add("El usuario de la computadora con el nombre " + 
                            equipoNew.get("Nombre del equipo (RED)").getFirst()
                            + " cambió el/la " + clave);
            }
        });
        equipoActual.forEach((clave, valorNuevo)->{
            //se ignoran todas las entradas que sean cambios o logins
            if(clave.contains("cambio") || clave.contains("login") || clave.equals("ID"))
                return;
            String componenteActual= "";
            for(Object propiedad : valorNuevo){
                if(valorNuevo.size() > 1 && propiedad == valorNuevo.getLast())
                    break;
                componenteActual+= propiedad.toString() + "; ";
            }
            componenteActual= componenteActual.substring(0, componenteActual.length() - 2);
            //para las apps instaladas
            if(clave.contains("app")){
                List<Object> cambio= new LinkedList<>(List.of("Instalación de software",
                        "nulo", componenteActual, LocalDate.now().toString(),
                        LocalTime.now().toString().split("\\.")[0]));
                cambio.add(null); //como el cambio es nuevo, su id es nulo
                equipoNew.put("cambio" + cantCambios.getAndIncrement(), cambio);
                cambios= true;
                cambiosNuevos.add("El usuario de la computadora con el nombre " + 
                        equipoNew.get("Nombre del equipo (RED)").getFirst()
                        + " intaló un software");
            }
            //para revisar si hay cambios en las incidencias (nuevas incidencias)
            else if(clave.contains("incidencia")){
                cambios= true;
            }
            //para los componentes hardware instalados
            else{
                List<Object> cambio= new LinkedList<>(List.of("Instalación de " + clave,
                        "nulo", componenteActual, LocalDate.now().toString(),
                        LocalTime.now().toString().split("\\.")[0]));
                cambio.add(null); //como el cambio es nuevo, su id es nulo
                equipoNew.put("cambio" + cantCambios.getAndIncrement(), cambio);
                cambios= true;
                cambiosNuevos.add("El usuario de la computadora con el nombre " + 
                        equipoNew.get("Nombre del equipo (RED)").getFirst()
                        + " intaló un/a " + clave);
            }
        });
        //</editor-fold>
    }
            
    public String registrarComputadora(Map<String, List<Object>> equipoNew, String operacion){
        String actualIP= equipoNew.get("IP").get(0).toString() + "." + equipoNew.get("IP").get(1).toString() + "." +
                    equipoNew.get("IP").get(2).toString() + "." + equipoNew.get("IP").get(3).toString();
        String antiguaIP;
        try{
            antiguaIP= equipoNew.get("antiguaIP").getFirst().toString();
        }
        catch(NullPointerException e){
            /*si es el registro de un nuevo equipo y no la actualización de uno existente
            entonces esta excepción se lanza y antiguaIP será igual a actualIP*/
            antiguaIP= actualIP;
        }
        registroCambios(equipoNew); //aqui se realiza o no el registro de cambios
        Computadora nuevo= new Computadora();
        List<Object> datos;
        //<editor-fold defaultstate="collapsed" desc="asignación de datos al equipo">
        try{
            datos= equipoNew.remove("ID");
            nuevo.setId((int)Double.parseDouble(datos.getFirst().toString()));
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        if(equipoNew.containsKey("Estado")){
           datos= equipoNew.remove("Estado");
           nuevo.setEstado(datos.getFirst().toString());
        }
        else nuevo.setEstado("Activo");
        datos= equipoNew.remove("Nombre del equipo (RED)");
        nuevo.setNombreRED(datos.getFirst().toString());
        datos= equipoNew.remove("Tipo");
        nuevo.setTipo(datos.getFirst().toString());
        datos= equipoNew.remove("IP");
        nuevo.setIP(datos.get(0).toString()+"."+datos.get(1).toString()+"."+
                datos.get(2).toString()+"."+datos.get(3).toString());
        datos= equipoNew.remove("Usuario");
        nuevo.setUsuario(datos.get(0).toString(), datos.get(1).toString(), 
                datos.get(2) == null? null : (int)datos.get(2));
        datos= equipoNew.remove("Departamento");
        nuevo.setDepart(datos.getFirst().toString());
        datos= equipoNew.remove("No. Sello");
        nuevo.setNumSello((int)Double.parseDouble(datos.getFirst().toString()));
        datos= equipoNew.remove("No. Llavero");
        nuevo.setNumLlavero((int)Double.parseDouble(datos.getFirst().toString()));
        datos= equipoNew.remove("Sistema Operativo");
        nuevo.setSO(datos.get(0).toString(), datos.get(1).toString(), datos.get(2).toString(), 
                datos.get(3) == null? null : (int)datos.get(3));
        //agrega aqui solo las aplicaciones, logins, cambios e incidencias
        Iterator<Map.Entry<String, List<Object>>> it= equipoNew.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, List<Object>> entrada= it.next();
            String clave= entrada.getKey();
            datos= equipoNew.get(clave);
            if(clave.contains("app")){
                nuevo.setAplicacion(datos.get(0).toString(), datos.get(1).toString(),
                        datos.get(2).toString(), datos.get(3).toString(), 
                        datos.get(4) == null? null : (int)datos.get(4));
                it.remove();
            }
            else if(clave.contains("login")){
                nuevo.setLogin(datos.get(0).toString(), datos.get(1).toString(),
                        datos.get(2).toString(), datos.get(3).toString(), 
                        datos.get(4) == null? null : (int)datos.get(4));
                it.remove();
            }
            else if(clave.contains("cambio")){
               nuevo.setCambio(datos.get(0).toString(), datos.get(1).toString(),
                        datos.get(2).toString(), datos.get(3).toString(), 
                        datos.get(4).toString(), 
                        datos.get(5) == null? null : (int)datos.get(5));
                it.remove(); 
            }
            else if(clave.contains("incidencia")){
               nuevo.setIncidencia(datos.get(0).toString(), datos.get(1).toString(), 
                       datos.get(2).toString(), 
                       datos.get(3) == null? null : (int)datos.get(3));
                it.remove(); 
            }
        }
        //agrega aqui solo los componentes hardware y periféricos
        equipoNew.forEach((key, value)->{
            String param1; //Marca
            String param2; //Modelo o Nombre en el caso del CPU
            String param3; //No. Serie
            Integer param4; //No. Inventario
            String param5; //Capacidad en el caso de la RAM y el Disco Duro
            Integer param6; //El id del equipo
            param1= value.get(0).toString();
            param2= value.get(1).toString();
            //agrega el no. serie a todos los componentes que no son ni "CPU" ni "RAM"
            if(!key.equals("CPU") && !key.equals("RAM")) param3= value.get(2).toString();
            else param3= null;
            /*agrega el no. inventario a todos los componentes que sean: UPS,
            fotocopiadoras, escaneres, impresoras, chasis, monitores*/
            String[] perifericosConInv= new String[]{"UPS", "Fotocopiadora", "Scanner", "Impresora", "Chasis", "Monitor"};
            param4= null;
            for(String periferico : perifericosConInv)
                if(key.contains(periferico)){
                    param4= Integer.valueOf(value.get(3).toString().split("\\.")[0]);
                    break;
                }
            //solo se agrega la capacidad a los componentes "RAM" y "Disco Duro"
            if(key.contains("Disco Duro")) param5= value.get(3).toString();
            else if(key.equals("RAM")) param5= value.get(2).toString();
            else param5= null;
            param6= value.getLast() == null? null : (int)value.getLast();
            nuevo.setComponente(key, param1, param2, param3, param4, param5, param6);
        });
        //</editor-fold>
        String estado= "";
        if(operacion.equals("agregar")) estado= DaoCompu.agregar(nuevo);
        else if(operacion.equals("actualizar")) estado= DaoCompu.actualizar(nuevo);
        Map<String, List<Object>> equipoRegistrado= estado.contains("No se puede actualizar") ||
                estado.contains("Ya existe el equipo en la BD")? obtenerComputadora(antiguaIP) : obtenerComputadora(actualIP);
        if(equipoRegistrado != null){
            equipoNew.clear();
            equipoRegistrado.forEach((clave, valor)-> equipoNew.put(clave, valor));
        }
        //esto es para el registro de existencias por año
        if(estado.equals("El equipo se registró correctamente en la BD"))
            ExistenciaXYearDAO.actualizarExistencia(Computadora.class, ExistenciaXYearDAO.agregar);
        return estado;
    }
    public String borrarComputadora(String IP){
        String estado= DaoCompu.eliminarPorIp(IP);
        //esto es para el registro de existencias por año
        if(estado.equals("Equipo eliminado"))
            ExistenciaXYearDAO.actualizarExistencia(Computadora.class, ExistenciaXYearDAO.sustraer);
        return estado;
    }
    public Map<String, List<Object>> obtenerComputadora(Object idOrIP){
        Map<String, List<Object>> compu= new HashMap<>();
        Computadora existente;
        existente= DaoCompu.getUno(idOrIP);
        if(existente == null) return null;
        //<editor-fold defaultstate="collapsed" desc="obtener datos del equipo encontrado">
        compu.put("ID", new LinkedList<>(List.of(existente.getId())));
        compu.put("Estado", new LinkedList<>(List.of(existente.getEstado())));
        compu.put("Nombre del equipo (RED)", new LinkedList<>(List.of(existente.getNombreRED())));
        compu.put("Tipo", new LinkedList<>(List.of(existente.getTipo())));
        List<Object> datosIp= new LinkedList<>(); //para obtener los números del String del IP
        datosIp.add(Integer.valueOf(existente.getIP().split("\\.")[0]));
        datosIp.add(Integer.valueOf(existente.getIP().split("\\.")[1]));
        datosIp.add(Integer.valueOf(existente.getIP().split("\\.")[2]));
        datosIp.add(Integer.valueOf(existente.getIP().split("\\.")[3]));
        compu.put("IP", datosIp);
        compu.put("Usuario", new LinkedList<>(List.of(
                existente.getUsuario().getNombre(), existente.getUsuario().getCargo(), 
                existente.getUsuario().getId()
        )));
        compu.put("Departamento", new LinkedList<>(List.of(existente.getDepart())));
        compu.put("Sistema Operativo", new LinkedList<>(List.of(
                existente.getSO().getNombre(), existente.getSO().getEdicion(), 
                existente.getSO().getVersion(), existente.getSO().getId()
        )));
        compu.put("No. Sello", new LinkedList<>(List.of(existente.getNumSello())));
        compu.put("No. Llavero", new LinkedList<>(List.of(existente.getNumLlavero())));
        //aqui se obtiene el hardware
        existente.getComponentes().forEach((componente)->{
            List<Object> datos= new LinkedList<>();
            Object atributo;
            datos.add(componente.getMarca());
            datos.add(componente.getModelo());
            atributo= componente.getNumSerie();
            if(atributo != null) datos.add(atributo);
            atributo= componente.getNumInv();
            if(atributo != null) datos.add(atributo);
            atributo= componente.getCapac();
            if(atributo != null) datos.add(atributo);
            datos.add(componente.getId());
            compu.put(componente.getTipo(), datos);
        });
        //aqui se obtienen las apps instaladas
        for(int i= 0; i < existente.getAplicaciones().size(); i++){
            List<Object> datos= new LinkedList<>();
            software app= existente.getAplicaciones().get(i);
            datos.add(app.getNombre());
            datos.add(app.getVer());
            datos.add(app.getFecha());
            datos.add(app.getSize());
            datos.add(app.getId());
            compu.put("app" + (i + 1), datos);
        }
        //aqui se obtienen los cambios realizados
        for(int i= 0; i < existente.getCambios().size(); i++){
            List<Object> datos= new LinkedList<>();
            Cambios cambio= existente.getCambios().get(i);
            datos.add(cambio.getTipo());
            datos.add(cambio.getComponenteAnterior());
            datos.add(cambio.getComponenteActual());
            datos.add(cambio.getFecha());
            datos.add(cambio.getHora());
            datos.add(cambio.getId());
            compu.put("cambio" + (i + 1), datos);
        }
        //aqui se obtienen los logins realizados
        for(int i= 0; i < existente.getLogins().size(); i++){
            List<Object> datos= new LinkedList<>();
            Logins login= existente.getLogins().get(i);
            datos.add(login.getCuenta());
            datos.add(login.getFecha());
            datos.add(login.getHora());
            datos.add(login.getTiempoActivo());
            datos.add(login.getId());
            compu.put("login" + (i + 1), datos);
        }
        //aqui se obtienen las incidencias registradas
        for(int i= 0; i < existente.getIncidencias().size(); i++){
            List<Object> datos= new LinkedList<>();
            Incidencias incidencia= existente.getIncidencias().get(i);
            datos.add(incidencia.getDesc());
            datos.add(incidencia.getFecha());
            datos.add(incidencia.getHora());
            datos.add(incidencia.getId());
            compu.put("incidencia" + (i + 1), datos);
        }
        //</editor-fold>
        return compu;
    }
    public boolean estaRegistrada(int ID){
        return DaoCompu.isInside(ID);
    }
    
    public String[] obtenerUltimoLogin(Map<String, List<Object>> compu, int cantLogins){
        //aquí se obtiene la última sesión comparando todos los logins por fecha y hora
        String[] ultimoLogin= new String[4];
        //implementación de la cola de prioridad para obtener el último login o sesión
        Queue<List<Object>> colaActualidad= new PriorityQueue<>(cantLogins, new java.util.Comparator<List<Object>>(){
            @Override
            public int compare(List<Object> o1, List<Object> o2) {
                //<editor-fold defaultstate="collapsed" desc="comparación: siempre pone en la cabeza de la cola el objeto con la mayor Fecha y Hora">
                //primero compara por Fecha
                int anno1= Integer.parseInt(o1.get(1).toString().split(" ")[5]);
                int anno2= Integer.parseInt(o2.get(1).toString().split(" ")[5]);
                if(anno1 > anno2) return -1;
                else if(anno1 < anno2) return 1;
                int mes01= 0, mes02= 0;
                String mes1= o1.get(1).toString().split(" ")[3].toLowerCase();
                String mes2= o2.get(1).toString().split(" ")[3].toLowerCase();
                switch(mes1){
                    case "enero"-> mes01= 1;
                    case "febrero"-> mes01= 2;
                    case "marzo"-> mes01= 3;
                    case "abril"-> mes01= 4;
                    case "mayo"-> mes01= 5;
                    case "junio"-> mes01= 6;
                    case "julio"-> mes01= 7;
                    case "agosto"-> mes01= 8;
                    case "septiembre"-> mes01= 9;
                    case "octubre"-> mes01= 10;
                    case "noviembre"-> mes01= 11;
                    case "diciembre"-> mes01= 12; 
                }
                switch(mes2){
                    case "enero"-> mes02= 1;
                    case "febrero"-> mes02= 2;
                    case "marzo"-> mes02= 3;
                    case "abril"-> mes02= 4;
                    case "mayo"-> mes02= 5;
                    case "junio"-> mes02= 6;
                    case "julio"-> mes02= 7;
                    case "agosto"-> mes02= 8;
                    case "septiembre"-> mes02= 9;
                    case "octubre"-> mes02= 10;
                    case "noviembre"-> mes02= 11;
                    case "diciembre"-> mes02= 12; 
                }
                if(mes01 > mes02) return -1;
                else if(mes01 < mes02) return 1;
                int dia1= Integer.parseInt(o1.get(1).toString().split(" ")[1]);
                int dia2= Integer.parseInt(o2.get(1).toString().split(" ")[1]);
                if(dia1 > dia2) return -1;
                else if(dia1 < dia2) return 1;
                //ahora compara por Hora
                int hora1= Integer.parseInt(o1.get(2).toString().split(":")[0]);
                int hora2= Integer.parseInt(o2.get(2).toString().split(":")[0]);
                if(hora1 > hora2) return -1;
                else if(hora1 < hora2) return 1;
                int minutos1= Integer.parseInt(o1.get(2).toString().split(":")[1]);
                int minutos2= Integer.parseInt(o2.get(2).toString().split(":")[1]);
                if(minutos1 > minutos2) return -1;
                else if(minutos1 < minutos2) return 1;
                int segundos1= Integer.parseInt(o1.get(2).toString().split(":")[2]);
                int segundos2= Integer.parseInt(o2.get(2).toString().split(":")[2]);
                if(segundos1 > segundos2) return -1;
                else if(segundos1 < segundos2) return 1;
                //</editor-fold>
                //devuelve cero si son iguales y entonces la cola de prioridad se comporta como FIFO
                return 0;
            }
        });
        //se introducen todos los logins a la cola
        compu.forEach((clave, valor)->{
            if(clave.contains("login")){
                colaActualidad.add(valor);
            }
        });
        //se obtiene el login o la sesion más actual
        List<Object> loginMasActual= colaActualidad.peek();
        for(int i= 0; i < 4; i++)
            ultimoLogin[i]= loginMasActual.get(i).toString();
        return ultimoLogin;
    }
    
    /*compara los datos de los equipos que se van a actualizar con sus contrapartes en la BD
    para verificar si realmente se modificaron sus datos
    esto es para los equipos que no son computadoras*/
    public void verificarCambios(Map<String, String> equipoNew, int tipo){
        Map<String, String> equipoDB;
        switch(tipo){
            case 1 -> equipoDB= obtenerMovil(equipoNew.remove("NumTelfAntiguo"));
            case 2 -> equipoDB= obtenerVOIP(Integer.valueOf(equipoNew.remove("CódigoAntiguo")));
            case 3 -> equipoDB= obtenerDispRed(equipoNew.remove("IPAntigua"));
            default -> equipoDB= new HashMap<>();
        }
        if(equipoDB.isEmpty()) return;
        equipoDB.forEach((clave, valor)->{
            if(!valor.equals(equipoNew.get(clave)))
                cambios= true;
        });
    }
    
    public String registrarMovil(Map<String, String> equipoNew){
        verificarCambios(equipoNew, 1);
        String estado;
        TelfMovil nuevo= new TelfMovil();
        String datos;
        //<editor-fold defaultstate="collapsed" desc="asignación de datos al equipo">
        datos= equipoNew.get("ID");
        if(datos != null) nuevo.setId(Integer.valueOf(datos));
        datos= equipoNew.get("NumTelf");
        nuevo.setNumTelf(datos);
        datos= equipoNew.get("email");
        nuevo.setEmail(datos);
        nuevo.setUsuario(equipoNew.get("NombreUsuario"), equipoNew.get("CargoUsuario"));
        datos= equipoNew.get("PIN");
        nuevo.setPIN(Integer.valueOf(datos));
        datos= equipoNew.get("PUK");
        nuevo.setPUK(Integer.valueOf(datos));
        datos= equipoNew.get("Marca");
        nuevo.setMarca(datos);
        datos= equipoNew.get("Modelo");
        nuevo.setModelo(datos);
        datos= equipoNew.get("NumSerie");
        nuevo.setNumSerie(datos);
        //</editor-fold>
        estado= DaoMovil.agregarOActualizar(nuevo);
        //esto es para el registro de existencias por año
        if(estado.equals("Equipo correctamente registrado en la Base de datos"))
            ExistenciaXYearDAO.actualizarExistencia(TelfMovil.class, ExistenciaXYearDAO.agregar);
        return estado;
    }
    
    public List<Object[]> resumenMoviles(String busqueda){
        List<TelfMovil> moviles= DaoMovil.getTodos();
        List<Object[]> filas= new LinkedList<>();
        int ID= 1;
        //<editor-fold defaultstate="collapsed" desc="Filtrado y obtención de datos">
        for(TelfMovil movil : moviles){
            String numTelf= movil.getNumTelf();
            String email= movil.getEmail();
            String usuario= movil.getUsuario().getNombre();
            Integer pin= movil.getPIN();
            String marca= movil.getMarca();
            //aqui se realiza un filtrado de equipos segun el valor de "búsqueda"
            //si un equipo tiene algunos de sus atributos (variables String de arriba)
            //con el mismo valor sin case sensitive que "busqueda" se queda sino se ignora
            if(busqueda != null){
                busqueda= busqueda.toLowerCase();
                if(!numTelf.toLowerCase().contains(busqueda) && !email.toLowerCase().contains(busqueda) &&
                        !usuario.toLowerCase().contains(busqueda) && !pin.toString().toLowerCase().contains(busqueda) &&
                        !marca.toLowerCase().contains(busqueda))
                    continue;
            }
            //aqui termina el filtrado
            //se crean las filas por cada equipo filtrado
            Object[] fila= new Object[]{ID++, numTelf, email, usuario, pin, marca};
            filas.add(fila);
        }
        //</editor-fold>
        return filas;
    }
    
    public String borrarMovil(String numTelf){
        String estado= DaoMovil.eliminarUno(numTelf);
        //esto es para el registro de existencias por año
        if(estado.equals("Equipo eliminado de la BD"))
            ExistenciaXYearDAO.actualizarExistencia(TelfMovil.class, ExistenciaXYearDAO.sustraer);
        return estado;
    }
    
    public Map<String, String> obtenerMovil(String numTelf){
        TelfMovil movil= DaoMovil.getUno(numTelf);
        Map<String, String> equipo= new HashMap<>();
        if(movil == null) return null;
        //<editor-fold defaultstate="collapsed" desc="obtener datos del móvil encontrado">
        equipo.put("ID", movil.getId().toString());
        equipo.put("NumTelf", movil.getNumTelf());
        equipo.put("email", movil.getEmail());
        equipo.put("NombreUsuario", movil.getUsuario().getNombre());
        equipo.put("CargoUsuario", movil.getUsuario().getCargo());
        equipo.put("PIN", movil.getPIN().toString());
        equipo.put("PUK", movil.getPUK().toString());
        equipo.put("Marca", movil.getMarca());
        equipo.put("Modelo", movil.getModelo());
        equipo.put("NumSerie", movil.getNumSerie());
        //</editor-fold>
        return equipo;
    }
    
    public String registrarVOIP(Map<String, String> equipoNew){
        verificarCambios(equipoNew, 2);
        String estado;
        TelfVOIP nuevo= new TelfVOIP();
        String datos;
        //<editor-fold defaultstate="collapsed" desc="asignación de datos al equipo">
        datos= equipoNew.get("ID");
        if(datos != null) nuevo.setId(Integer.valueOf(datos));
        datos= equipoNew.get("Código");
        nuevo.setCodigo(Integer.valueOf(datos));
        datos= equipoNew.get("NumInventario");
        nuevo.setNumInv(Integer.valueOf(datos));
        datos= equipoNew.get("IP");
        nuevo.setIP(datos);
        datos= equipoNew.get("Departamento");
        nuevo.setUsuario(equipoNew.get("NombreUsuario"), equipoNew.get("CargoUsuario"));
        nuevo.setDepart(datos);
        datos= equipoNew.get("Marca");
        nuevo.setMarca(datos);
        datos= equipoNew.get("Modelo");
        nuevo.setModelo(datos);
        datos= equipoNew.get("NumSerie");
        nuevo.setNumSerie(datos);
        //</editor-fold>
        estado= DaoVOIP.agregarOActualizar(nuevo);
        if(estado.equals("Equipo correctamente registrado en la Base de datos"))
            ExistenciaXYearDAO.actualizarExistencia(TelfVOIP.class, ExistenciaXYearDAO.agregar);
        return estado;
    }
    
    public List<Object[]> resumenVOIP(String busqueda){
        List<TelfVOIP> VOIPS= DaoVOIP.getTodos();
        List<Object[]> filas= new LinkedList<>();
        int ID= 1;
        //<editor-fold defaultstate="collapsed" desc="Filtrado y obtención de datos">
        for(TelfVOIP voip : VOIPS){
            Integer codigo= voip.getCodigo();
            String ip= voip.getIP();
            String usuario= voip.getUsuario().getNombre();
            String departamento= voip.getDepart();
            Integer numInv= voip.getNumInv();
            //aqui se realiza un filtrado de equipos segun el valor de "búsqueda"
            //si un equipo tiene algunos de sus atributos (variables de arriba)
            //con el mismo valor sin case sensitive que "busqueda" se queda sino se ignora
            if(busqueda != null){
                busqueda= busqueda.toLowerCase();
                if(!codigo.toString().toLowerCase().contains(busqueda) && !ip.toLowerCase().contains(busqueda) &&
                        !usuario.toLowerCase().contains(busqueda) && !departamento.toLowerCase().contains(busqueda) &&
                        !numInv.toString().toLowerCase().contains(busqueda))
                    continue;
            }
            //aqui termina el filtrado
            //se crean las filas por cada equipo filtrado
            Object[] fila= new Object[]{ID++, codigo, ip, usuario, departamento, numInv};
            filas.add(fila);
        }
        //</editor-fold>
        return filas;
    }
    
    public String borrarVOIP(Integer codigo){
        String estado= DaoVOIP.eliminarUno(codigo);
        if(estado.equals("Equipo eliminado de la BD"))
            ExistenciaXYearDAO.actualizarExistencia(TelfVOIP.class, ExistenciaXYearDAO.sustraer);
        return estado;
    }
    
    public Map<String, String> obtenerVOIP(Integer codigo){
        TelfVOIP voip= DaoVOIP.getUno(codigo);
        Map<String, String> equipo= new HashMap<>();
        if(voip == null) return null;
        //<editor-fold defaultstate="collapsed" desc="obtener datos del telf voip encontrado">
        equipo.put("ID", voip.getId().toString());
        equipo.put("Código", voip.getCodigo().toString());
        equipo.put("NumInventario", voip.getNumInv().toString());
        equipo.put("IP", voip.getIP());
        equipo.put("Departamento", voip.getDepart());
        equipo.put("NombreUsuario", voip.getUsuario().getNombre());
        equipo.put("CargoUsuario", voip.getUsuario().getCargo());
        equipo.put("Marca", voip.getMarca());
        equipo.put("Modelo", voip.getModelo());
        equipo.put("NumSerie", voip.getNumSerie());
        //</editor-fold>
        return equipo;
    }
    
    public String registrarDispRED(Map<String, String> equipoNew){
        verificarCambios(equipoNew, 3);
        String estado;
        DispRED nuevo= new DispRED();
        String datos;
        //<editor-fold defaultstate="collapsed" desc="asignación de datos al equipo">
        datos= equipoNew.get("ID");
        if(datos != null) nuevo.setId(Integer.valueOf(datos));
        datos= equipoNew.get("IP");
        nuevo.setIP(datos);
        datos= equipoNew.get("Tipo");
        nuevo.setTipo(datos);
        datos= equipoNew.get("Marca");
        nuevo.setMarca(datos);
        datos= equipoNew.get("Modelo");
        nuevo.setModelo(datos);
        datos= equipoNew.get("NumSerie");
        nuevo.setNumSerie(datos);
        //</editor-fold>
        estado= DaoDispRED.agregarOActualizar(nuevo);
        if(estado.equals("Equipo correctamente registrado en la Base de datos"))
            ExistenciaXYearDAO.actualizarExistencia(DispRED.class, ExistenciaXYearDAO.agregar);
        return estado;
    }
    
    public List<Object[]> resumenDispRED(String busqueda){
        List<DispRED> DispAll= DaoDispRED.getTodos();
        List<Object[]> filas= new LinkedList<>();
        int ID= 1;
        //<editor-fold defaultstate="collapsed" desc="Filtrado y obtención de datos">
        for(DispRED disp : DispAll){
            String ip= disp.getIP();
            String tipo= disp.getTipo();
            String marca= disp.getMarca();
            String modelo= disp.getModelo();
            String numSerie= disp.getNumSerie();
            //aqui se realiza un filtrado de equipos segun el valor de "búsqueda"
            //si un equipo tiene algunos de sus atributos (variables String de arriba)
            //con el mismo valor sin case sensitive que "busqueda" se queda sino se ignora
            if(busqueda != null){
                busqueda= busqueda.toLowerCase();
                if(!tipo.toLowerCase().contains(busqueda) && !ip.toLowerCase().contains(busqueda) &&
                        !marca.toLowerCase().contains(busqueda) && !modelo.toLowerCase().contains(busqueda) &&
                        !numSerie.toLowerCase().contains(busqueda))
                    continue;
            }
            //aqui termina el filtrado
            //se crean las filas por cada equipo filtrado
            Object[] fila= new Object[]{ID++, ip, tipo, marca, modelo, numSerie};
            filas.add(fila);
        }
        //</editor-fold>
        return filas;
    }
    
    public String borrarDispRED(String ip){
        String estado= DaoDispRED.eliminarUno(ip);
        if(estado.equals("Equipo eliminado de la BD"))
            ExistenciaXYearDAO.actualizarExistencia(DispRED.class, ExistenciaXYearDAO.sustraer);
        return estado;
    }
    
    public Map<String, String> obtenerDispRed(String ip){
        DispRED disp= DaoDispRED.getUno(ip);
        Map<String, String> equipo= new HashMap<>();
        if(disp == null) return null;
        //<editor-fold defaultstate="collapsed" desc="obtener datos del telf voip encontrado">
        equipo.put("ID", disp.getId().toString());
        equipo.put("IP", disp.getIP());
        equipo.put("Tipo", disp.getTipo());
        equipo.put("Marca", disp.getMarca());
        equipo.put("Modelo", disp.getModelo());
        equipo.put("NumSerie", disp.getNumSerie());
        //</editor-fold>
        return equipo;
    }
    
    public boolean getCambios(){return cambios;}
    
    public void resetCambios(){cambios= false;}
    
    public List<String> getCambiosNuevos(){return cambiosNuevos;}
    
    public void resetCambiosNuevos(){cambiosNuevos= new LinkedList<>();}
    
    private final ComputadoraDAO DaoCompu;
    private final TelfMovilDAO DaoMovil;
    private final TelfVOIPDAO DaoVOIP;
    private final DispREDDAO DaoDispRED;
    /*es necesario para el registro de eventos en el historial de eventos,
    detecta si hubo algún cambio hecho por el administrador en el sistema,
    esta reservado solo para eso,
    se usa en la GUI en las ventanas donde se modifican los equipos*/
    private boolean cambios;
    /*es necesario para el registro de eventos en el historial de eventos,
    detecta si hubo algún cambio hecho por el usuario de alguna computadora,
    esta reservado solo para eso,
    se usa en el servidorLAN en la actualización automática de datos de las pc*/
    private List<String> cambiosNuevos;
}
