/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.google.gson.Gson;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;



/**
 *
 * @author PC
 */
public class ServidorLAN{
    
    private final ExecutorService poolClientes;
    private ServerSocket server;
    private final Gson gson;
    private final List<manejadorCliente> clientesActivos;
    private Map<String, List<Object>> compuLocal;
    private Map<String, String> sensoresLocal;
    private List<String[]> loginsLocal;
    private List<Object> antivirusLocal;
    private int ID;
    private boolean inicio;
    private final InfoHarwareAndSoftware info;
    
    public ServidorLAN(){
        poolClientes= Executors.newCachedThreadPool();
        gson= new Gson();
        clientesActivos= new CopyOnWriteArrayList<>();
        inicio= false;
        info= new InfoHarwareAndSoftware();
    }
    
    public void start(int PORT){
        try{
            server= new ServerSocket(PORT);
            inicio= true;
            new Thread(()-> {
                try{
                    while(true){
                        Socket clienteSocket= server.accept();
                        manejadorCliente cliente= new manejadorCliente(clienteSocket);
                        clientesActivos.add(cliente);
                        poolClientes.execute(cliente);
                    }
                }
                catch(IOException | RejectedExecutionException e){
                    System.out.println("Servidor parado");
                }
                finally{
                    stop();
                }
            }).start();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void stop(){
        inicio= false;
        try{
            if(server != null) server.close();
            poolClientes.shutdown();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void enviarDatoAUno(String ip, Object dato){
        String localIP= info.getIpActiva();
        if(ip.equals(localIP)){
            operacionesCompuLocal(dato);
            return;
        }
        for(manejadorCliente cliente : clientesActivos){
            if(cliente.getIP().equals(ip))
                cliente.enviarDataOut(dato);
            break;
        }
    }
    
    private void enviarDatoATodos(Object dato){
        operacionesCompuLocal(dato);
        for(manejadorCliente cliente : clientesActivos){
            cliente.enviarDataOut(dato);
        }
    }
    
    //realiza varias operaciones en la computadora local como obtener info de hardware y software, guardar ID, etc...
    private void operacionesCompuLocal(Object dato){
        if(dato.toString().equals("equipo")){
            compuLocal= getInfoCompuLocal();
            try(DataInputStream fileIn= new DataInputStream(new FileInputStream("ID.bin"))){
                compuLocal.put("ID", List.of(fileIn.readInt()));
            }
            catch(IOException e){
                compuLocal.put("ID", List.of(-1));
            }
        }
        else if(dato.toString().contains("guardar ID")){
            try (DataOutputStream fileOut = new DataOutputStream(new FileOutputStream("ID.bin"))) {
                ID= Integer.parseInt(dato.toString().split(":")[1]);
                fileOut.writeInt(ID);
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
        else if(dato.toString().equals("enviar ID")){
            try(DataInputStream fileIn= new DataInputStream(new FileInputStream("ID.bin"))){
                ID= fileIn.readInt();
            }
            catch(IOException e){
                ID= -1;
            }
        }
        else if(dato.toString().equals("eliminar ID")){
            try{
                Files.deleteIfExists(Paths.get("ID.bin"));
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
        else if(dato.toString().equals("sensorInfo")){
            //obtener sensores
            sensoresLocal= info.getSensors();
        }
        else if(dato.toString().equals("soloLogins")){
            //obtener solo los logins activos del equipo
            loginsLocal= info.getSessionLogins();
        }
        else if(dato.toString().equals("antivirusInfo")){
            //obtener la info del antivirus (solo de uno)
            antivirusLocal= info.getAntivirus();
        }
    }
    
    private List<Map<String, List<Object>>> getComputadoras(){
        List<Map<String, List<Object>>> computadoras= new LinkedList<>();
        computadoras.add(compuLocal);
        for(manejadorCliente cliente : clientesActivos)
            computadoras.add(cliente.getCompu());
        return computadoras;
    }
    
    private Map<String, List<Object>> getEquipo(String ip){
        String localIP= info.getIpActiva();
        if(ip.equals(localIP)){
            compuLocal= getInfoCompuLocal();
            return compuLocal;
        }
        for(manejadorCliente cliente : clientesActivos){
            if(cliente.getIP().equals(ip))
                return cliente.getCompu();
        }
        return null;
    }
    
    public List<Object[]> ScanLAN(){
        List<Object[]> listaHostsActivos= new LinkedList<>();
        enviarDatoATodos("nombre");
        enviarDatoATodos("enviar ID");
        String nameRED= info.getNombreEquipo();
        String ip= info.getIpActiva();
        listaHostsActivos.add(new Object[]{nameRED, ip, ID});
        for(manejadorCliente cliente : clientesActivos){
            nameRED= cliente.getHostName();
            ip= cliente.getIP();
            ID= cliente.getID();
            listaHostsActivos.add(new Object[]{nameRED, ip, ID});
        }
        return listaHostsActivos;
    }
    
    public void registrarTodos(String nombreAdmin){
        gestionEquipo controladorCompu= new gestionEquipo();
        enviarDatoATodos("equipo");
        for(Map<String, List<Object>> compu : getComputadoras()){
            String ip= compu.get("IP").get(0).toString()+ "." + compu.get("IP").get(1).toString() + "." + 
                    compu.get("IP").get(2).toString() + "." + compu.get("IP").get(3).toString();
            int ID= (int)Double.parseDouble(compu.remove("ID").getFirst().toString());
            if(!controladorCompu.estaRegistrada(ID)){
                String estado= controladorCompu.registrarComputadora(compu, "agregar");
                if(estado.equals("El equipo se registró correctamente en la BD")){
                    compu= controladorCompu.obtenerComputadora(ip);
                    enviarDatoAUno(ip, "guardar ID:" + compu.get("ID").get(0).toString());
                    gestionEventos.agregarEvento("Responsable: " + nombreAdmin + "\n" + 
                            "Lugar: Sistema\n" + "El administrador agregó una nueva computadora " + 
                            "con el nombre " + compu.get("Nombre del equipo (RED)").getFirst());
                }
            }
        }
    }
    
    public void actualizarTodos(){
        //<editor-fold defaultstate="collapsed" desc="actualiza los datos de todos los equipos almacenados en la BD">
        gestionEquipo controladorCompu= new gestionEquipo();
        enviarDatoATodos("equipo");
        List<Map<String, List<Object>>> computadoras= getComputadoras();
        for(Map<String, List<Object>> compu : computadoras){
            int ID= (int)Double.parseDouble(compu.get("ID").getFirst().toString());
            if(controladorCompu.estaRegistrada(ID)){
                /*"fixCompu" obtiene los datos del equipo almacenados en la BD,
                esto es necesario para mantener los datos que se mencionan a acontinuación iguales que en la BD,
                son datos que no se van a actualizar,
                también sirve para obtener los id del hardware y SO para el registro*/
                Map<String, List<Object>> fixCompu= controladorCompu.obtenerComputadora(ID);
                compu.put("Tipo", fixCompu.get("Tipo"));
                compu.put("Departamento", fixCompu.get("Departamento"));
                compu.put("No. Sello", fixCompu.get("No. Sello"));
                compu.put("No. Llavero", fixCompu.get("No. Llavero"));
                compu.put("Estado", fixCompu.get("Estado"));
                compu.put("Usuario", fixCompu.get("Usuario"));
                compu.get("Sistema Operativo").add(fixCompu.get("Sistema Operativo").getLast()); //id de la BD del SO
                compu.get("CPU").add(fixCompu.get("CPU").getLast()); //id de la BD del CPU
                /*si la marca de la RAM obtenida mediante OSHI es "No disponible"
                , se mantiene la marca igual que en la BD*/
                String marcaRam= compu.get("RAM").getFirst().toString();
                if(marcaRam.contains("No disponible"))
                   compu.get("RAM").set(0, fixCompu.get("RAM").getFirst()); 
                compu.get("RAM").add(fixCompu.get("RAM").getLast()); //id de la BD de la RAM
                /*si el no. serie de la motherboard obtenido mediante OSHI es "No disponible"
                , se mantiene el no. serie igual que en la BD*/
                String noSerieBoard= compu.get("Motherboard").get(2).toString();
                if(noSerieBoard.equals("No disponible"))
                   compu.get("Motherboard").set(2, fixCompu.get("Motherboard").get(2));
                compu.get("Motherboard").add(fixCompu.get("Motherboard").getLast()); //id de la BD de la Motherboard
                compu.put("Fuente", fixCompu.get("Fuente"));
                compu.put("Teclado", fixCompu.get("Teclado"));
                compu.put("Mouse", fixCompu.get("Mouse"));
                compu.put("Bocinas", fixCompu.get("Bocinas"));
                compu.put("Chasis", fixCompu.get("Chasis"));
                for(String clave : new String[]{"UPS", "Lector 3 1/2", "Impresora", "Scanner", "Fotocopiadora"}){
                    List<Object> periferico= fixCompu.get(clave);
                    if(periferico != null) compu.put(clave, periferico);
                }
                /*si el no. serie del lector CD/DVD (solo si el lector CD/DVD existe) obtenido
                mediante OSHI es "No disponible", se mantiene el no. serie igual que en la BD si este existiera*/
                List<Object> CD_DVD= compu.get("Lector CD/DVD");
                if(CD_DVD != null){
                   String noSerie_CD_DVD= CD_DVD.get(2).toString();
                   if(fixCompu.get("Lector CD/DVD") != null){
                       if(noSerie_CD_DVD.equals("No disponible"))
                           compu.get("Lector CD/DVD").set(2, fixCompu.get("Lector CD/DVD").get(2)); 
                        compu.get("Lector CD/DVD").add(fixCompu.get("Lector CD/DVD").getLast()); //id de la BD del Lector CD/DVD
                   }
                   else
                       compu.get("Lector CD/DVD").add(null); //id en null para el registro
                }
                /*si la marca del o los monitores obtenidos mediante OSHI es "No disponible"
                o es igual al de la BD se mantienen los datos iguales que en la BD*/
                compu.forEach((clave, valor)->{
                    if(clave.contains("Monitor")){
                        String marcaMonitor= compu.get(clave).getFirst().toString();
                        if(fixCompu.get(clave) != null && 
                                (marcaMonitor.equals("No disponible") || 
                                marcaMonitor.equals(fixCompu.get(clave).getFirst().toString())))
                            compu.put(clave, fixCompu.get(clave));
                        else compu.get(clave).add(null);
                    }
                    else if(clave.contains("Disco Duro")){
                        String marcaDisco= compu.get(clave).getFirst().toString();
                        if(fixCompu.get(clave) != null && 
                                (marcaDisco.equals("No disponible") || 
                                marcaDisco.equals(fixCompu.get(clave).getFirst().toString())))
                            compu.put(clave, fixCompu.get(clave));
                        else compu.get(clave).add(null);
                    }
                });
                //aqui termina la asignación de los datos que no se van a actualizar
                
                /*se cuenta cant de apps de compu y se asigna null como id a cada una,
                esto es necesario para las 2 operaciones siguientes*/
                AtomicInteger index= new AtomicInteger(1);
                compu.forEach((clave, valor)->{
                    if(clave.contains("app")){
                        index.getAndIncrement();
                        valor.add(null);
                    }
                });
                /*operación 1: asigna a "compu" todos los datos incluido el id de todas las apps iguales de la BD
                (esto garantiza que se mantengan los atributos modificados en el sistema de las apps y
                que se excluyan apps desintaladas, también se mantiene el id en null de las apps nuevas instaladas)
                y todos los cambios de "fixCompu"*/
                Iterator<Map.Entry<String, List<Object>>> it= fixCompu.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<String, List<Object>> entrada= it.next();
                    if(entrada.getKey().contains("app")){
                        for(Map.Entry<String, List<Object>> entrada2 : compu.entrySet()){
                            if(entrada2.getKey().contains("app") && entrada2.getValue().getFirst().toString().
                                    equals(entrada.getValue().getFirst().toString())){
                                entrada2.getValue().set(1, entrada.getValue().get(1));
                                entrada2.getValue().set(2, entrada.getValue().get(2));
                                entrada2.getValue().set(3, entrada.getValue().get(3));
                                entrada2.getValue().set(4, entrada.getValue().get(4));
                                it.remove();
                                break;
                            }
                        }
                    }
                    else if(entrada.getKey().contains("cambio"))
                        compu.put(entrada.getKey(), entrada.getValue());
                }
                /*operación 2: se asigna a compu las apps extras (apps agregadas en el sistema manualmente)
                que existen en fixCompu a partir de las ya contadas si estas fueron agregadas en la aplicación o sistema*/
                fixCompu.forEach((clave, valor)->{
                    if(clave.contains("app") && valor.getFirst().toString().contains("[a.e.a]")) 
                        compu.put("app" + index.getAndIncrement(), valor);
                });
                
                //agrega a "compu" las sesiones de "fixCompu" manteniendo las ya obtenidas en "compu" si no son iguales
                index.set(1); //sirve para agregar los logins anteriores a partir de los nuevos ya existentes
                compu.forEach((clave, valor)->{
                    if(clave.contains("login")){
                        index.getAndIncrement();
                        valor.add(null); //poner su id en null
                    }
                });
                fixCompu.forEach((clave, valor)->{
                    if(clave.contains("login")){
                        boolean existe= false;
                        for(Map.Entry<String, List<Object>> entrada : compu.entrySet()){
                            if(entrada.getKey().contains("login") && entrada.getValue().get(0).equals(valor.get(0)) && 
                                    entrada.getValue().get(1).equals(valor.get(1)) && 
                                    entrada.getValue().get(2).equals(valor.get(2))){
                                valor.add(entrada.getValue().get(4)); //solo asigna el id de la BD
                                existe= true;
                                break;
                            }
                        }
                        if(!existe) compu.put("login" + index.getAndIncrement(), valor);
                    }
                });
                controladorCompu.registrarComputadora(compu, "actualizar");
                for(String desc : controladorCompu.getCambiosNuevos()){
                    gestionEventos.agregarEvento("Responsable: " + compu.get("Usuario").get(0) + 
                            " (" + compu.get("Usuario").get(1) + ")\n" + "Lugar: " + 
                            compu.get("Departamento").getFirst() + "\n" + desc);
                }
                controladorCompu.resetCambiosNuevos();
            }
            /*elimina el archivo local ID.bin si el equipo no está registrado y el ID es mayor que 0,
            que el ID sea mayor que cero significa que está registrado en la BD o que en algún momento lo estuvo*/
            else if(ID > 0){
                String ip= compu.get("IP").get(0).toString()+ "." + compu.get("IP").get(1).toString() + "." + 
                        compu.get("IP").get(2).toString() + "." + compu.get("IP").get(3).toString();
                enviarDatoAUno(ip, "eliminar ID");
            }
        }
        //</editor-fold>
    }
    
    public Map<String, String> getSensoresDeUno(String IP){
        enviarDatoAUno(IP, "sensorInfo");
        String localIP= info.getIpActiva();
        if(IP.equals(localIP)){
            return sensoresLocal;
        }
        for(manejadorCliente cliente : clientesActivos){
            if(cliente.getIP().equals(IP))
                return cliente.getSensores();
        }
        return null;
    }
    
    public String getTiempoActivaLoginDeUno(String ip, String[] ultimoLogin){
        enviarDatoAUno(ip, "soloLogins");
        List<String[]> logins= new LinkedList<>();
        String localIP= info.getIpActiva();
        if(ip.equals(localIP)){
            logins= loginsLocal;
        }
        for(manejadorCliente cliente : clientesActivos){
            if(cliente.getIP().equals(ip)){
                logins= cliente.getLogins();
                break;
            }
        }
        for(String[] login : logins){
            if(login[0].equals(ultimoLogin[0]) && login[1].equals(ultimoLogin[1]) && login[2].equals(ultimoLogin[2])){
                return login[3];
            }
        }
        return null;
    }
    
    public List<Object> getAntivirusDeUno(String IP){
        enviarDatoAUno(IP, "antivirusInfo");
        String localIP= info.getIpActiva();
        if(IP.equals(localIP)){
            return antivirusLocal;
        }
        for(manejadorCliente cliente : clientesActivos){
            if(cliente.getIP().equals(IP))
                return cliente.getAntivirus();
        }
        return null;
    }
    
    public boolean estaIniciado(){
        return inicio;
    }
    
    //obtener la info de la computadora Local
    private Map<String, List<Object>> getInfoCompuLocal(){
        List<Object> datos= new LinkedList<>();
        Map<String, List<Object>> compu= new HashMap<>();
        //<editor-fold defaultstate="collapsed" desc="obtención de datos de la computadora local">
        datos.add(info.getNombreEquipo());
        compu.put("Nombre del equipo (RED)", datos);
        datos= new LinkedList<>();
        datos.add(info.getTipo());
        compu.put("Tipo", datos);
        datos= new LinkedList<>();
        String ip= info.getIpActiva();
        datos.add(Integer.valueOf(ip.split("\\.")[0]));
        datos.add(Integer.valueOf(ip.split("\\.")[1]));
        datos.add(Integer.valueOf(ip.split("\\.")[2]));
        datos.add(Integer.valueOf(ip.split("\\.")[3]));
        compu.put("IP", datos);
        datos= new LinkedList<>(List.of("No disponible", "No disponible"));
        datos.add(null);
        compu.put("Usuario", datos);
        compu.put("Departamento", List.of("No disponible"));
        compu.put("No. Sello", List.of(1));
        compu.put("No. Llavero", List.of(1));
        datos= info.getSO();
        datos.add(null);
        compu.put("Sistema Operativo", datos);
        datos= info.getCPU();
        datos.add(null);
        compu.put("CPU", datos);
        datos= info.getRam();
        datos.add(null);
        compu.put("RAM", datos);
        datos= info.getMotherboard();
        datos.add(null);
        compu.put("Motherboard", datos);
        
        //obtener todos los discos duros si hay más de uno
        List<List<Object>> discos= info.getDiscoDuro();
        for(int i= 0; i < discos.size(); i++){
            datos= discos.get(i);
            datos.add(null);
            compu.put("Disco Duro " + (i + 1), datos);
        }
        
        datos= new LinkedList<>(List.of("No disponible", "No disponible", "No disponible"));
        datos.add(null);
        compu.put("Fuente", datos);
        datos= info.getTeclado();
        datos.add(null);
        compu.put("Teclado", datos);
        datos= info.getMouse();
        datos.add(null);
        compu.put("Mouse", datos);
        datos= info.getBocinas();
        datos.add(null);
        compu.put("Bocinas", datos);
        datos= new LinkedList<>(List.of("No disponible", "No disponible", "No disponible", 1));
        datos.add(null);
        compu.put("Chasis", datos);
        
        //obtener todos los monitores si hay más de uno
        List<List<Object>> monitores= info.getMonitor();
        for(int i= 0; i < monitores.size(); i++){
            datos= monitores.get(i);
            datos.add(null);
            compu.put("Monitor " + (i + 1), datos);
        }
        
        if((datos= info.getLectorDVD()) != null){
            datos.add(null);
            compu.put("Lector CD/DVD", datos);
        }
        
        //obtener apps instaladas
        List<List<Object>> apps= info.getApps();
        for(int i= 0; i < apps.size(); i++){
            datos= apps.get(i);
            datos.add(null);
            compu.put("app" + (i + 1), datos);
        }
        
        //obtener las sesiones activas con sus datos como:
        //nombre de la sesion o cuenta, fecha, hora de inicio y tiempo trancurrido desde el inicio
        List<String[]> logins= info.getSessionLogins();
        for(int i= 0; i < logins.size(); i++){
            datos= new LinkedList<>(Arrays.asList((Object[])logins.get(i)));
            datos.add(null);
            compu.put("login" + (i + 1), datos);
        }
        //</editor-fold>
        return compu;
    }
    
    private class manejadorCliente implements Runnable{
        
        private final Socket clienteSocket;
        private BufferedReader in;
        private PrintWriter out;
        private final ConcurrentMap<String, List<Object>> compu;
        private final ConcurrentMap<String, String> sensores;
        private final Queue<String[]> logins;
        private final Queue<Object> antiVirus;
        private volatile int ID;
        private volatile String soloNombre;
        private volatile boolean running;
        private final BlockingQueue<Object> colaConcurrente;
        private final Object block= new Object(); //para bloquear este hilo si sensores, compu o logins están vacíos, o desbloquearlo cuando no
        
        public manejadorCliente(Socket clienteSocket){
            this.clienteSocket= clienteSocket;
            compu= new ConcurrentHashMap<>();
            sensores= new ConcurrentHashMap<>();
            logins= new ConcurrentLinkedQueue<>();
            antiVirus= new ConcurrentLinkedQueue<>();
            ID= 0;
            soloNombre= "";
            running= true;
            colaConcurrente= new LinkedBlockingQueue<>();
        }
        
        public Map<String, List<Object>> getCompu(){
            if(compu.isEmpty()){
                try{
                    synchronized (block) {
                        block.wait();
                    }
                }
                catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
            return compu;
        }
        
        public String getIP(){return clienteSocket.getInetAddress().getHostAddress();}
        
        public String getHostName(){
            if(soloNombre.equals("")){
                try{
                    synchronized (block) {
                        block.wait();
                    }
                }
                catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
            return soloNombre;
        }
        
        public Map<String, String> getSensores(){
            if(sensores.isEmpty()){
                try{
                    synchronized (block) {
                        block.wait();
                    }
                }
                catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
            return sensores;
        }
        
        public int getID(){return ID;}
        
        public List<String[]> getLogins(){
            List<String[]> sesiones= new LinkedList<>();
            if(logins.isEmpty()){
                try{
                    synchronized (block) {
                        block.wait();
                    }
                }
                catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
            while(!logins.isEmpty()){
                sesiones.add(logins.poll());
            }
            return sesiones;
        }
        
        public List<Object> getAntivirus(){
            List<Object> antivirus= new LinkedList<>();
            if(antiVirus.isEmpty()){
                try{
                    synchronized (block) {
                        block.wait();
                    }
                }
                catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
            while(!antiVirus.isEmpty()){
                antivirus.add(antiVirus.poll());
            }
            return antivirus;
        }
        
        @Override
        public void run(){
            try{
                in= new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                out= new PrintWriter(clienteSocket.getOutputStream(), true);
                //hilo para enviar datos
                new Thread(()->{
                    while(running){
                        try{
                           Object JsonData= colaConcurrente.take(); 
                           String JsonDatos= gson.toJson(JsonData);
                           out.println(JsonDatos);
                        }
                        catch(InterruptedException e){
                            break;
                        }
                    }
                }).start();
                String JsonDatos;
                while((JsonDatos= in.readLine()) != null){
                    //aqui se reciben los datos y se procesan
                    if(JsonDatos.toLowerCase().contains("equipo")){
                        Map<String, List<Object>> tempo= gson.fromJson(JsonDatos, Map.class);
                        compu.clear();
                        tempo.forEach((clave, valor)->{
                            compu.put(clave, valor);
                        });
                        List<Object> valores= new LinkedList<>();
                        valores.add(compu.get("IP").get(0).toString().split("\\.")[0]);
                        valores.add(compu.get("IP").get(1).toString().split("\\.")[0]);
                        valores.add(compu.get("IP").get(2).toString().split("\\.")[0]);
                        valores.add(compu.get("IP").get(3).toString().split("\\.")[0]);
                        compu.put("IP", valores);
                    }
                    else if(JsonDatos.toLowerCase().contains("temp")){
                        Map<String, String> tempo= gson.fromJson(JsonDatos, Map.class);
                        sensores.clear();
                        tempo.forEach((clave, valor)->{
                            sensores.put(clave, valor);
                        });
                    }
                    else if(JsonDatos.toLowerCase().contains("login")){
                        Map<String, List<String>> tempo= gson.fromJson(JsonDatos, Map.class);
                        tempo.forEach((clave, valor)->{
                            logins.add(new String[]{valor.get(0), valor.get(1), valor.get(2), valor.get(3)});
                        });
                    }
                    else if(JsonDatos.toLowerCase().contains("name")){
                        Map<String, String> tempo= gson.fromJson(JsonDatos, Map.class);
                        soloNombre= tempo.get("name");
                    }
                    else if(JsonDatos.toLowerCase().contains("antivirus")){
                        Map<String, List<Object>> tempo= gson.fromJson(JsonDatos, Map.class);
                        for(int i= 0; i < 3; i++)
                            antiVirus.add(tempo.get("Antivirus").get(i));
                    }
                    else ID= Integer.parseInt(JsonDatos);
                    synchronized (block) {
                        block.notify();
                    }
                }
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
            finally{
                try{
                    clientesActivos.remove(this);
                    running= false;
                    if(in != null) in.close();
                    if(out != null) out.close();
                    if(clienteSocket != null) clienteSocket.close();
                }
                catch(IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        
        public void enviarDataOut(Object Data){
            colaConcurrente.add(Data);
        }
    }
}
