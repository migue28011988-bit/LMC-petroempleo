/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//para SSL
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 *
 * @author PC
 */
public class ClienteLAN {
    
    private SSLSocket cliente;
    private BufferedReader in;
    private PrintWriter out;
    private final Gson gson;
    private boolean conectado;
    //campos para SSL
    private SSLSocketFactory sslFactory;
    private boolean sslConfigurado = false;
    
    public ClienteLAN(){
        gson= new Gson();
        conectado= false;
    }

        /**
     * Configura el truststore del cliente para confiar en el certificado del servidor.
     * @param rutaTrustStore Ruta del archivo JKS que contiene el certificado público del servidor (o la CA).
     * @param contrasena     Contraseña del truststore.
     */
    public void configurarTrustStore(String rutaTrustStore, String contrasena) {
        try {
            // Cargar el truststore
            KeyStore trustStore = KeyStore.getInstance("PKCS12");
            try (InputStream tsStream = new FileInputStream(rutaTrustStore)) {
                trustStore.load(tsStream, contrasena.toCharArray());
            }

            // Inicializar TrustManagerFactory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(trustStore);

            // Crear SSLContext con los TrustManagers (sin KeyManager, el cliente no necesita clave privada)
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            sslFactory = sslContext.getSocketFactory();
            sslConfigurado = true;
        } catch (IOException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new RuntimeException("Error al configurar truststore: " + e.getMessage(), e);
        }
    }
    
    /**
     * Conecta al servidor usando SSL.
     * @param ip             ip del servidor
     * @param PORT           Puerto del servidor.
     */
    public void connect(String ip, int PORT){
        if (!sslConfigurado) {
            throw new IllegalStateException("Se debe llamar a configurarTrustStore() antes de connect()");
        }
        try{
            // Crear SSLSocket conectado al servidor
            cliente = (SSLSocket) sslFactory.createSocket(ip, PORT);

            // Iniciar el handshake SSL explícitamente para capturar errores tempranos
            cliente.startHandshake();
            
            in= new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            out= new PrintWriter(cliente.getOutputStream(), true);
            conectado= true;
            
            //hilo para recibir y enviar datos
            new Thread(()->{
                String JsonData;
                try{
                    while((JsonData= in.readLine()) != null){
                        Object entrada= gson.fromJson(JsonData, Object.class);
                        if(entrada.toString().equals("equipo"))
                            enviarInfoEquipo();
                        //envia solo el nombre del equipo
                        else if(entrada.toString().equals("nombre"))
                            enviarSoloNombre();
                        else if(entrada.toString().contains("guardar ID")){
                            try (DataOutputStream fileOut = new DataOutputStream(new FileOutputStream("ID.bin"))) {
                                int ID= Integer.parseInt(entrada.toString().split(":")[1]);
                                fileOut.writeInt(ID);
                            }
                        }
                        //envia el ID
                        else if(entrada.toString().equals("enviar ID"))
                            enviarID();
                        //elimina el archivo local
                        else if(entrada.toString().equals("eliminar ID")){
                            try{
                                Files.deleteIfExists(Paths.get("ID.bin"));
                            }
                            catch(IOException e){
                                System.out.println(e.getMessage());
                            }
                        }
                        //envia info de los sensores de voltaje y temperatura
                        else if(entrada.toString().equals("sensorInfo")){
                            enviarSensores();
                        }
                        //envia solo los logins o sesiones activas del equipo
                        else if(entrada.toString().equals("soloLogins")){
                            enviarLogins();
                        }
                        //envia la info del antivirus instalado (solo uno)
                        else if(entrada.toString().equals("antivirusInfo")){
                            enviarInfoAntivirus();
                        }
                        System.out.println(JsonData);
                    }
                }
                catch(IOException e){
                    System.out.println(e.getMessage());
                }
                finally{
                    disconect();
                }
            }).start();
        }
        catch(IOException e){
            System.out.println("Error de conexión SSL: " + e.getMessage());
        }
    }
    
    public void disconect(){
        conectado= false;
        try{
            if(in != null) in.close();
            if(out != null) out.close();
            if(cliente != null) cliente.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    //obtener info del equipo y archivo "ID.bin" del disco duro y enviar la información al servidor
    private void enviarInfoEquipo(){
        Map<String, List<Object>> equipoInfo= getInfoEquipoLocal();
        try(DataInputStream fileIn= new DataInputStream(new FileInputStream("ID.bin"))){
            equipoInfo.put("ID", List.of(fileIn.readInt()));
        }
        catch(IOException e){
            equipoInfo.put("ID", List.of(-1));
        }
        String JsonData= gson.toJson(equipoInfo);
        out.println(JsonData);
    }
    
    private void enviarID(){
        try(DataInputStream fileIn= new DataInputStream(new FileInputStream("ID.bin"))){
            int ID= fileIn.readInt();
            out.println(ID);
        }
        catch(IOException e){
            int ID= -1;
            out.println(ID); 
        }
    }
    
    private void enviarSensores(){
        InfoHarwareAndSoftware info= new InfoHarwareAndSoftware();
        //obtener sensores
        Map<String, String> sensores= info.getSensors();
        String JsonData= gson.toJson(sensores);
        out.println(JsonData);
    }
    
    private void enviarLogins(){
        InfoHarwareAndSoftware info= new InfoHarwareAndSoftware();
        //obtener solo los logins activos del equipo
        List<String[]> logins= info.getSessionLogins();
        Map<String, List<String>> equipoLogins= new HashMap<>();
        for(int i= 0; i < logins.size(); i++)
            equipoLogins.put("login"+(i+1), Arrays.asList(logins.get(i)));
        String JsonData= gson.toJson(equipoLogins);
        out.println(JsonData);
    }
    
    //obtener la info de la computadora Local
    private Map<String, List<Object>> getInfoEquipoLocal(){
        InfoHarwareAndSoftware info= new InfoHarwareAndSoftware();
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
    
    private void enviarSoloNombre(){
        InfoHarwareAndSoftware info= new InfoHarwareAndSoftware();
        Map<String, String> nombre= new HashMap<>();
        nombre.put("name", info.getNombreEquipo());
        String JsonData= gson.toJson(nombre);
        out.println(JsonData);
    }
    
    private void enviarInfoAntivirus(){
        InfoHarwareAndSoftware info= new InfoHarwareAndSoftware();
        Map<String, List<Object>> antiVirus= new HashMap<>();
        antiVirus.put("Antivirus", info.getAntivirus());
        String JsonData= gson.toJson(antiVirus);
        out.println(JsonData);
    }
    
    public boolean estaConectado(){return conectado;}
}
