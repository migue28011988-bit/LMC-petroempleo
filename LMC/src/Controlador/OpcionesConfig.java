/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.AdministradorDAO;
import Modelo.Administrador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author PC
 */
public class OpcionesConfig {
    
    private final CuentasAdmin admins;
    private final SSLCertExport lanConn;
    private final postgreServerAdmin postgreAdmin;
    private boolean cambios;

    public OpcionesConfig(){
        lanConn= new SSLCertExport();
        postgreAdmin= new postgreServerAdmin();
        admins= new CuentasAdmin();
        cambios= false;
    }
    
    /**
     * clase para la administración de las cuentas
     */
    public class CuentasAdmin {
        
        public static final int GET_CUENTAS_DB= 0;
        public static final int ADD_CUENTA= 1;
        public static final int ERASE_CUENTA= 2;
        public static final int UPDATE_CUENTA= 3;
        public static final int UPDATE_DB= 4;
        private final List<Object[]> cuentas;
        private final AdministradorDAO DAO;
        
        /**
         * Este constructor realiza la conexión al servidor de postgreSQL 
         * con la configuración establecida en el archivo local "config.cfg"
         */
        private CuentasAdmin(){
            DAO= new AdministradorDAO();
            cuentas= new LinkedList<>();
        }
        
        public void adminCuentas(int operacion, Object[] cuenta){
            //<editor-fold defaultstate="collapsed" desc="aqui se implementan todas las operaciones referentes a la administración de las cuentas">
            switch (operacion) {
                case GET_CUENTAS_DB -> {
                    cuentas.clear();
                    for(Administrador admin : DAO.getTodos())
                        cuentas.add(new Object[]{admin.getId(), admin.getAdmin(), 
                            "Protegida", admin.getSuper()});
                }
                case ADD_CUENTA -> {
                    cuentas.add(cuenta);
                    cambios= true;
                }
                case ERASE_CUENTA -> {
                    cuentas.removeIf((Admin)->{
                        return Admin[1].equals(cuenta[0]);
                    });
                    cambios= true;
                }
                case UPDATE_CUENTA -> {
                    cuentas.forEach((Admin)->{
                        //cuenta[0] es el nombre anterior y cuenta[1] es el nombre modificado
                        if(Admin[1].equals(cuenta[0])){
                            cambios= true;
                            Admin[1]= cuenta[1];
                            Admin[2]= cuenta[2];
                            Admin[3]= cuenta[3];
                        }   
                    });
                }
                case UPDATE_DB -> {
                    List<Administrador> admins= new LinkedList<>();
                    for(Object[] cuentaAdm : cuentas){
                        Administrador admin= new Administrador();
                        admin.setId(cuentaAdm[0] == null? null : (int)cuentaAdm[0]);
                        admin.setAdmin(cuentaAdm[1].toString());
                        /*codifica la contraseña para más seguridad, en la BD no se verá
                        la contraseña real solo una cadena incoherente con letras, números y
                        carácteres especiales*/
                        if(!cuentaAdm[2].toString().equals("Protegida")){
                            String passwordHash= BCrypt.hashpw(cuentaAdm[2].toString(), BCrypt.gensalt());
                            admin.setPassword(passwordHash);
                        }
                        /*o mantiene la contraseña igual a la BD, esto pasa si 
                        la contraseña no ha sido cambiada en el formulario
                        correspondiente*/
                        else {
                            String password= DAO.getAdmin(cuentaAdm[1].toString()).getPassword();
                            admin.setPassword(password);
                        }
                        admin.setSuper((boolean)cuentaAdm[3]);
                        admins.add(admin);
                    }
                    DAO.updateAndCreateAndDelete(admins);
                }
            }
            //</editor-fold>
        }
        
        public List<Object[]> getCuentas(){return this.cuentas;}
        
        public void borrarTodasLasCuentas(){
            cuentas.clear();
            adminCuentas(UPDATE_DB, null);
        }
    
        public boolean checkAdminName(String name){
            for(Object[] cuenta : cuentas){
                if(cuenta[1].toString().equals(name))
                    return true;
            }
            return false;
        }
    
        public void setSuperAdmin(String AdminName, boolean superAdmin){
            for(Object[] cuenta : cuentas){
                if(cuenta[1].toString().equals(AdminName))
                    cuenta[3]= superAdmin;
            }
        }
    }
    
    /**
     * clase para guardar o cargar un archivo local con datos como el tema 
     * visual, la url de la conexión a la base de datos, etc
     */
    public static class LocalConfig implements Serializable{
        
        private String LFActual; //LookAndFeel guardado
        //Tiempo de espera en la Actualización Automática del servidor
        private Integer tiempoEspera;
        //Lista que contiene los ip de las pc que se van a excluir de la Actualización Automática
        private List<String> exclusiones;
        private String urlBD; //URL para la conexión a la Base de Datos
        private String[] credencialesBD; //usuario y contraseña para la conexión a la Base de Datos
        
        private static LocalConfig instance;
        
        private LocalConfig(){
            this.LFActual= "Nimbus (Por defecto)";
            this.tiempoEspera= 15000;
            this.exclusiones= new LinkedList<>();
            this.urlBD= "jdbc:postgresql://localhost:5432/postgres";
            this.credencialesBD= new String[]{"postgres", "postgresConnect"};
        }
        
        private synchronized static void inicializar(){
            if(instance == null){
                instance= load();
                if(instance == null){
                    instance= new LocalConfig();
                    save();
                }
            }
        }
        
        public synchronized static void aplicarTema(String tema){
            inicializar();
            instance.LFActual= tema;
            save();
        }
    
        public synchronized static String getTema(){
            inicializar();
            return instance.LFActual;
        }
    
        public synchronized static void setTiempoEspera(Integer time){
            inicializar();
            instance.tiempoEspera= time;
            save();
        }
    
        public synchronized static Integer getTiempoEspera(){
            inicializar();
            return instance.tiempoEspera;
        }
    
        public synchronized static void setExclusiones(List<String> lista){
            inicializar();
            instance.exclusiones= lista;
            save();
        }
    
        public synchronized static List<String> getExclusiones(){
            inicializar();
            return instance.exclusiones;
        }
    
        public synchronized static void setURL(String url){
            inicializar();
            instance.urlBD= url;
            save();
        }
    
        public synchronized static String getURL(){
            inicializar();
            return instance.urlBD;
        }
    
        public synchronized static void setCredenciales(String[] credenciales){
            inicializar();
            instance.credencialesBD= credenciales;
            save();
        }
    
        public synchronized static String[] getCredenciales(){
            inicializar();
            return instance.credencialesBD;
        }
       
        private synchronized static void save(){
            try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream("config.cfg"))){
                out.writeObject(instance);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    
        private synchronized static LocalConfig load(){
            try(ObjectInputStream in= new ObjectInputStream(new FileInputStream("config.cfg"))){
                return (LocalConfig)in.readObject();
            }catch(IOException | ClassNotFoundException e){
                return null;
            }
        }
    
        public synchronized static void Reset(){
            instance= new LocalConfig();
            save();
        }
    }
    
    /**
     * Clase para realizar operaciones en el servidor postgreSQL como:
     * -crear o borrar usuario.
     * -crear, borrar, renombrar una base de datos asi como exportar
     * y restaurar los datos de una base de datos.
     */
    public class postgreServerAdmin{
        
        /*Obtiene una conexión a la base de datos por defecto 
        (para comandos administrativos) usando la configuración guardada en
        el archivo local "config.cfg"*/
        private Connection getAdminConnection() throws SQLException {
            String url = LocalConfig.getURL();
            String adminUser= LocalConfig.getCredenciales()[0];
            String adminPassword= LocalConfig.getCredenciales()[1];
            return DriverManager.getConnection(url, adminUser, adminPassword);
        }

        // Ejecuta una sentencia SQL DDL/DML (CREATE, ALTER, DROP, etc.) sin resultados
        private void executeUpdate(String sql) throws SQLException {
            try (Connection conn = getAdminConnection(); 
                    Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
            }
        }
        
        //ejecuta una sentencia SQL con resultados que se devuelve en una lista
        private List<String> executeQuery(String sql, String columnName) throws SQLException{
            List<String> lista= new LinkedList<>();
            try (Connection conn = getAdminConnection(); 
                    Statement stmt = conn.createStatement();
                    ResultSet resultados= stmt.executeQuery(sql)) {
                while(resultados.next())
                    lista.add(resultados.getString(columnName));
            }
            return lista;
        }
        
        /**
         * Crea una nueva base de datos con propietario o dueño.
         * @param dbName nombre de la base de datos a crear
         * @param propietario nombre del propietario de esta bd
         * @throws SQLException si falla la creación de la base de datos
         */
        public void crearDatabase(String dbName, String propietario) throws SQLException {
            String sql = String.format("CREATE DATABASE \"%s\" OWNER \"%s\"", dbName, propietario);
            executeUpdate(sql);
        }
        
        /**
         * Renombra una base de datos existente.
         * Nota: No debe haber conexiones activas a la base de datos que se renombra.
         * @param oldName nombre actual
         * @param newName nuevo nombre
         * @throws SQLException si falla el renombramiento de la base de datos
         * especificada
         */
        public void renombrarDatabase(String oldName, String newName) throws SQLException {
            String sql = String.format("ALTER DATABASE \"%s\" RENAME TO \"%s\"", oldName, newName);
            executeUpdate(sql);
        }
        
        /**
         * Elimina (dropea) una base de datos.
         * Nota: No debe haber conexiones activas a la base de datos.
         * @param dbName nombre de la base de datos a eliminar
         * @throws SQLException si falla la eliminación de la base de datos
         * especificada
         */
        public void borrarDatabase(String dbName) throws SQLException {
            String sql = String.format("DROP DATABASE \"%s\"", dbName);
            executeUpdate(sql);
        }
        
        /**
         * Crea un nuevo usuario (rol) con contraseña.
         * @param userName nombre del usuario
         * @param password contraseña
         * @throws SQLException si falla la creación del usuario o rol
         */
        public void crearUsuario(String userName, String password) throws SQLException {
            String sql = String.format("CREATE USER \"%s\" WITH PASSWORD '%s'", userName, password);
            executeUpdate(sql);
        }
        
        /**
         * Elimina un usuario (rol).
         * @param userName nombre del usuario a eliminar
         * @throws SQLException si falla la eliminación del usuario o rol
         */
        public void borrarUsuario(String userName) throws SQLException {
            String sql = String.format("DROP USER \"%s\"", userName);
            executeUpdate(sql);
        }
        
        /**
         * Obtiene una lista de todas las bases de datos existentes.
         * @return la lista con las BD.
         * @throws SQLException si falla la consulta.
         */
        public List<String> getBasesDeDatos() throws SQLException{
            String sql= "SELECT datname FROM pg_database WHERE datistemplate = false ORDER BY datname";
            return executeQuery(sql, "datname");
        }
        
        /**
         * Obtiene una lista de todos los usuarios existentes.
         * @return la lista con los usuarios.
         * @throws SQLException si falla la consulta.
         */
        public List<String> getUsuarios() throws SQLException{
            String sql= "SELECT rolname FROM pg_roles WHERE rolcanlogin = true ORDER BY rolname";
            return executeQuery(sql, "rolname");
        }
        
        /**
         * Cambia la contraseña de un usuario existente.
         * @param userName nombre del usuario
         * @param newPassword nueva contraseña
         * @throws SQLException si el usuario no existe o hay error en la sentencia
        */
        public void changeUserPassword(String userName, String newPassword) throws SQLException {
            String sql = String.format("ALTER USER \"%s\" WITH PASSWORD '%s'", userName, newPassword);
            executeUpdate(sql);
        }
        
        /**
         * Realiza un backup completo de una base de datos usando pg_dump.
         * @param dbName   nombre de la base de datos a respaldar
         * @param filePath ruta del archivo de salida (ej: "C:/backups/miDB.sql")
         * @throws Exception si falla la ejecución del comando
         */
        public void backupDatabase(String dbName, String filePath) throws Exception {
            // Construir comando: pg_dump --host=... --port=... --username=... --dbname=... --file=...
            String url = LocalConfig.getURL();
            String host= url.split("/")[2].split(":")[0];
            String port= url.split("/")[2].split(":")[1];
            String adminUser= LocalConfig.getCredenciales()[0];
            String adminPassword= LocalConfig.getCredenciales()[1];
            List<String> command = new ArrayList<>();
            command.add("pg_dump");
            command.add("--host=" + host);
            command.add("--port=" + String.valueOf(port));
            command.add("--username=" + adminUser);
            command.add("--dbname=" + dbName);
            command.add("--file=" + filePath);
            command.add("--format=custom");   // SQL plano
            command.add("--no-owner");        // evita problemas de permisos al restaurar
            command.add("--no-privileges");

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.environment().put("PGPASSWORD", adminPassword); // autenticación por variable de entorno
            pb.redirectErrorStream(true); // mezcla stdout y stderr

            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                // Leer el error
                StringBuilder error = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        error.append(line).append("\n");
                    }
                }
                throw new Exception("pg_dump falló con código " + exitCode + ": \n" + error);
            }
        }
        
        /**
         * Restaura una base de datos desde un archivo de backup (creado con backupDatabase).
         * Primero crea una BD temporal a la cual se restaurará el backup,
         * se obtiene el propietario de la BD original y se elimina, se asigna el propietario
         * obtenido a la BD temporal y se cambia el nombre al nombre de la BD original
         * tomando su lugar.
         * Nota: El archivo debe ser un SQL custom generado por pg_dump.
         * @param dbName   nombre de la base de datos (se recreará)
         * @param backupFilePath ruta del archivo .sql de backup
         * @throws Exception si falla algún paso
         */
        public void restoreDatabase(String dbName, String backupFilePath) throws Exception {
            
            // 0. obtiene el usuario y contraseña
            String[] credenciales= LocalConfig.getCredenciales();
            String adminUser= credenciales[0];
            
            // 1. Generar nombre único para la base de datos temporal
            String tempDbName = "restored_db";

            // 2. Crear la BD temporal con propietario = adminUser (quien ejecuta el restore)
            crearDatabase(tempDbName, adminUser);

            try {
                // 3. Restaurar el backup en la BD temporal
                try{
                    restoreBackupToDatabase(backupFilePath, tempDbName, credenciales);
                }catch(Exception e){
                    /*si falla la restauración se elimina la BD tempDbName y 
                    se lanza la excepción*/
                    borrarDatabase(tempDbName);
                    throw new Exception("No se pudo restaurar la base de datos. Causa: "
                            + e.getMessage());
                }
                        
                // 4. Obtener el propietario de la BD original
                String originalOwner = null;
                try{
                    originalOwner = getPropietarioDatabase(dbName);
                }catch(SQLException e){
                    System.out.println("No se pudó obtener el propietario");
                }

                // 5. Eliminar la BD original (puede fallar si hay conexiones activas o la BD no existe, etc.)
                try {
                    borrarDatabase(dbName);
                } catch (SQLException e) {
                    // Si falla la eliminación, no tocamos la temporal y lanzamos excepción
                    throw new Exception("No se pudo eliminar la base de datos original '" + dbName 
                            + "'. La BD temporal '" + tempDbName + "' se conserva. Causa: " + e.getMessage(), e);
                }

                // 6. Si la BD original tiene propietario, asignar ese propietario a la temporal
                if (originalOwner != null && !originalOwner.equals(adminUser)) {
                    cambiarPropietarioDatabase(tempDbName, originalOwner);
                }

                // 7. Renombrar la BD temporal al nombre original
                renombrarDatabase(tempDbName, dbName);

            } catch (Exception e) {
                throw e; // relanzar la excepción que ocurra
            }
        }
        
        /*se restaura los datos del backup a una base de datos específica.
        nota: se requiere que la BD esté vacia para evitar confusión con tablas
        existentes*/
        private void restoreBackupToDatabase(String backupFilePath, String targetDbName,
                String[] credenciales) throws Exception {
            //Obtener host, puerto y usuario
            String url = LocalConfig.getURL();
            String host= url.split("/")[2].split(":")[0];
            String port= url.split("/")[2].split(":")[1];
            String adminUser= credenciales[0];
            String adminPassword= credenciales[1];
            
            //Restaurar usando pg_restore
            List<String> command = new ArrayList<>();
            command.add("pg_restore");
            command.add("--host=" + host);
            command.add("--port=" + String.valueOf(port));
            command.add("--username=" + adminUser);
            command.add("--dbname=" + targetDbName);
            // Nota: NO se usa --no-owner ni --no-privileges porque el backup ya no contiene esa información
            command.add(backupFilePath);

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.environment().put("PGPASSWORD", adminPassword);
            pb.redirectErrorStream(true);

            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                StringBuilder error = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        error.append(line).append("\n");
                    }
                }
                throw new Exception("psql falló con código " + exitCode + ": " + error);
            }
        }
        
        // Obtiene el propietario de una base de datos (nombre del rol)
        private String getPropietarioDatabase(String dbName) throws SQLException {
            String sql = "SELECT rolname FROM pg_roles r LEFT JOIN pg_database d ON d.datdba = r.oid WHERE d.datname = ?";
            try (Connection conn = getAdminConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dbName);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("rolname");
                    } else {
                        return null; // Base de datos no existe o no tiene propietario
                    }
                }
            }
        }
        
        //cambia el propietario de una base de datos
        private void cambiarPropietarioDatabase(String dbName, String newOwner) throws SQLException {
            String sql = String.format("ALTER DATABASE \"%s\" OWNER TO \"%s\"", dbName, newOwner);
            executeUpdate(sql);
        }
    }
    
    /**
     * clase para crear un archivo keystore para el servidor y certificado 
     * público para los clientes, esto es para establecer la conexión SSL 
     * entre computadoras
     */
    public class SSLCertExport{
        /**
         * Genera un nuevo archivo KeyStore para establecer la conexión SSL.
         * @param validez la cantidad de días establecida por el administrador
         * @throws RuntimeException lanza excepción si hay un error de algún tipo
         * @return el estado del proceso (éxito o fallo)
         */
        public String generarKeyStore(Integer validez){
            String ip= new InfoHarwareAndSoftware().getIpActiva();
            List<String> command= new ArrayList<>();
            command.add("keytool");
            command.add("-genkeypair");
            command.add("-alias");
            command.add("certificadoLMC");
            command.add("-keyalg");
            command.add("RSA");
            command.add("-keysize");
            command.add("2048");
            command.add("-validity");
            command.add(String.valueOf(validez));
            command.add("-keystore");
            command.add("servidor.keystore");
            command.add("-storetype");
            command.add("pkcs12");
            command.add("-storepass");
            command.add("Seraphim#1");
            command.add("-keypass");
            command.add("Seraphim#1");
            command.add("-dname");
            command.add("CN=" + ip + ", OU=petroempleo, O=cupet, L=vedado, S=habana, C=cu");
            ProcessBuilder build= new ProcessBuilder(command);
            build.redirectErrorStream(true);
            try{
                Process process= build.start();
                int exitCode= process.waitFor();
                if(exitCode == 0)
                    return "éxito";
                else return "fallo";
            }catch(IOException | InterruptedException e){
                throw new RuntimeException(e.getMessage(), e);
            }
        }
   
        /**
         * Exporta el certificado público contenido dentro del archivo keystore generado,
         * el certificado exportado debe ser distribuido a los clientes ya que este se usa
         * para que los clientes se puedan autentificar con el servidor y se establezca la
         * conexión TCP segura.
         * @param archivoDestino la dirección del archivo donde se guardará el certificado
         * @throws RuntimeException lanza excepción si hay un error de algún tipo
         * @return el estado del proceso (éxito o fallo)
         */
        public String exportarCertificado(File archivoDestino){
            List<String> command= new ArrayList<>();
            command.add("keytool");
            command.add("-exportcert");
            command.add("-alias");
            command.add("certificadoLMC");
            command.add("-keystore");
            command.add("servidor.keystore");
            command.add("-storepass");
            command.add("Seraphim#1");
            command.add("-file");
            command.add(archivoDestino.getAbsolutePath());
            ProcessBuilder build= new ProcessBuilder(command);
            build.redirectErrorStream(true);
            try{
                Process process= build.start();
                int exitCode= process.waitFor();
                if(exitCode == 0)
                    return "éxito";
                else return "fallo";
            }catch(IOException | InterruptedException e){
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
    
    public boolean getCambios(){return cambios;}
    
    public void resetCambios(){cambios= false;}
    
    public CuentasAdmin cuentasService(){
        return admins;
    }
    
    public SSLCertExport SSLCertService(){
        return lanConn;
    }
   
    public postgreServerAdmin postgreServerService(){
        return postgreAdmin;
    }
    
}
