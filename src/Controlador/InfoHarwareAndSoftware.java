/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Tools.HDDandSSDManufactures;
import Tools.JEDECManufacturers;
import Tools.MonitorManufacturerDB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.*;
import oshi.util.ExecutingCommand;
/**
 *
 * @author PC
 */
public class InfoHarwareAndSoftware {
    
    public InfoHarwareAndSoftware(){
        sistema= new SystemInfo();
        controladorHardware= sistema.getHardware();
        controladorSoftware= sistema.getOperatingSystem();
    }
    
    public String getNombreEquipo(){
        NetworkParams nombre= controladorSoftware.getNetworkParams();
        return nombre.getHostName();
    }
    
    public String getTipo(){
        if(controladorHardware.getComputerSystem().getModel().toLowerCase().contains("laptop"))
            return "Laptop";
        else return "PC";
    }
    
    public String getIpActiva(){
        List<NetworkIF> net= controladorHardware.getNetworkIFs();
        for(NetworkIF elem:net){
            try{
                if(elem.queryNetworkInterface().isLoopback() || elem.queryNetworkInterface().isVirtual())
                    continue;
                for(String ip: elem.getIPv4addr()){
                    if(!ip.startsWith("127.") && !ip.startsWith("169.254.") &&
                            !ip.startsWith("0.0.0.0") && !ip.startsWith("224."))
                        return ip;
                }
            }
            catch(SocketException e){
                System.out.println(e.getMessage()); 
            }
        }
        return "0.0.0.0";
    }
    
    public List<Object> getSO(){
        String nombre= controladorSoftware.getFamily() + " " +
                controladorSoftware.getVersionInfo().getVersion();
        String edicion= controladorSoftware.getVersionInfo().getCodeName();
        String version= controladorSoftware.getVersionInfo().getBuildNumber();
        return new LinkedList<>(List.of(nombre, edicion, version));
    }
    
    public List<Object> getCPU(){
        CentralProcessor cpu= controladorHardware.getProcessor();
        String Nombre= cpu.getProcessorIdentifier().getName();
        String Marca, Modelo;
        if(Nombre.toLowerCase().contains("intel"))
            Marca= "INTEL";
        else Marca= "AMD";
        int index= Nombre.indexOf(" ");
        Modelo= Nombre.substring(index + 1);
        return new LinkedList<>(List.of(Marca, Modelo));
    }
    
    public List<Object> getRam(){
        GlobalMemory ram= controladorHardware.getMemory();
        String Fabricante= ram.getPhysicalMemory().getFirst().getManufacturer();
        String Modelo= ram.getPhysicalMemory().getFirst().getPartNumber().trim();
        String Marca= JEDECManufacturers.identifyByPartNumber(Modelo);
        if(Marca == null)
            Marca= JEDECManufacturers.getManufacturerName(Fabricante);
        Long tam= ram.getTotal()/1024/1024/1024;
        if(tam < 2) tam= (long)2;
        else if(tam > 2 && tam < 4) tam= (long)4;
        else if(tam > 4 && tam < 8) tam= (long)8;
        else if(tam > 8 && tam < 16) tam= (long)16;
        else if(tam > 16 && tam < 32) tam= (long)32;
        else if(tam > 32 && tam < 64) tam= (long)64;
        String Capacidad= tam + " GB";
        return new LinkedList<>(List.of(Marca, Modelo, Capacidad));
    }
    
    public List<Object> getMotherboard(){
        Baseboard MB= controladorHardware.getComputerSystem().getBaseboard();
        String Marca= MB.getManufacturer();
        String Modelo= MB.getModel();
        String NoSerie= MB.getSerialNumber();
        for(String valor : List.of("unknown", "O.E.M", "None", "", "Default string"))
            if(NoSerie.toLowerCase().contains(valor)) NoSerie= "No disponible";
        return new LinkedList<>(List.of(Marca, Modelo, NoSerie));
    }
    
    public List<List<Object>> getDiscoDuro(){
        List<HWDiskStore> DiscosDuros= controladorHardware.getDiskStores();
        List<List<Object>> datosHD= new LinkedList<>();
        for(HWDiskStore HD : DiscosDuros){
            String tipo= HD.getPartitions().getFirst().getName();
            if(tipo.contains("GPT") || tipo.contains("MBR") || tipo.contains("Installable File System")){
                String Modelo= HD.getModel();
                String Marca= HDDandSSDManufactures.getManufacturerName(Modelo);
                List<String> marcas= new LinkedList<>(List.of("toshiba", "samsung", "crucial", "kingston", "sandisk", 
                        "adata", "corsair", "sabrent", "team", "gibabyte", "intel"));
                if(marcas.contains(Modelo.toLowerCase().split(" ")[0]) || !Modelo.split(" ")[0].matches(".*[0-9].*"))
                    Modelo= Modelo.split("\\(")[0].split(Modelo.split(" ")[0])[1].trim();
                else Modelo= Modelo.split("\\(")[0].trim();
                String NoSerie= HD.getSerial().trim();
                if(NoSerie.equals("")) NoSerie= "No disponible";
                Long tam= HD.getSize()/1024/1024/1024;
                if(tam < 120) tam= (long)120;
                else if(tam > 120 && tam < 240) tam= (long)240;
                else if(tam > 240 && tam < 320) tam= (long)320;
                else if(tam > 320 && tam < 500) tam= (long)500;
                else if(tam > 500 && tam < 1000) tam= (long)1000;
                else if(tam > 1000 && tam < 2000) tam= (long)2000;
                else if(tam > 2000 && tam < 3000) tam= (long)4000;
                else if(tam > 3000 && tam < 6000) tam= (long)6000;
                else if(tam > 6000 && tam < 8000) tam= (long)8000;
                String Capacidad;
                if((tam / 1000) >= 1) Capacidad= (tam / 1000) + " TB";
                else Capacidad= tam + " GB";
                datosHD.add(new LinkedList<>(List.of(Marca, Modelo, NoSerie, Capacidad)));
            }
        }
        return datosHD;
    }
    
    public List<Object> getLectorDVD(){
        String os= controladorSoftware.getFamily().toLowerCase();
        String Marca= "", Modelo= "", NoSerie= "";
        List<String> command;
        if(os.contains("win")){
            command= ExecutingCommand.runNative("powershell Get-WmiObject Win32_CDROMDrive "+
                    "| Select-Object Name, SerialNumber | Format-List");
            if(command.isEmpty())
                return null;
            int it= 0;
            for(String linea:command){
                if(!linea.isBlank()){
                    if(it == 2) it= 0;
                    if(it == 0){
                        String name= linea.split(":")[1].substring(1);
                        Marca= name.split(" ")[0];
                        Modelo= name.substring(Marca.length()+1);
                    }
                    else NoSerie= linea.split(":")[1].substring(1);
                    it++;
                }
            }
        }
        else if(os.contains("lin")){
            command= ExecutingCommand.runNative("for device in $(ls /dev/sr* 2>/dev/null); do"+
                    " udevadm info --query=all --name=$device | grep -E 'ID_VENDOR|ID_MODEL|ID_SERIAL'; done");
            if(command.isEmpty())
                return null;
            //continuar aqui para Linux
        }
        //si la marca tiene las siguientes abreviaciones se cambia al nombre de la marca completo
        if(Marca.equals("HL-DT-ST")) Marca= "Hitachi-LG Data Storage";
        else if(Marca.contains("TSST")) Marca= "Toshiba-Samsung Storage Tech.";
        else if(Marca.equals("PLDS")) Marca= "Pioneer Lite-On Digital Solutions";
        else if(Marca.equals("MATSHITA")) Marca= "Panasonic";
        else if(Marca.equals("Optiarc")) Marca= "Sony-NEC Optiarc";
        else if(Marca.equals("Lite-On")) Marca= "Lite-On IT Corp.";
        if(NoSerie.isBlank()) NoSerie= "No disponible";
        return new LinkedList<>(List.of(Marca, Modelo, NoSerie));
    }
    
    public List<List<Object>> getMonitor(){
        List<Display> monitores= controladorHardware.getDisplays();
        List<List<Object>> datosMonitores= new LinkedList<>();
        for(Display monitor : monitores){
            byte[] edid= monitor.getEdid();
            if (edid == null)
                datosMonitores.add(new LinkedList<>(List.of("Desconocido", "No disponible", "No disponible", 1)));
            int id= ((edid[8] & 0xff) << 8) | (edid[9] & 0xff);
            char c1= (char)(((id >> 10) & 0x1f) + '@');
            char c2= (char)(((id >> 5) & 0x1f) + '@');
            char c3= (char)((id & 0x1f) + '@');
            String Marca= ""+c1+c2+c3;
            int model= (edid[10] & 0xff) | ((edid[11] & 0xff) << 8);
            String Modelo= Marca + String.format("%04X", model);
            int serial= (edid[12] & 0xff) | ((edid[13] & 0xff) << 8) 
                    | ((edid[14] & 0xff) << 16) | ((edid[15] & 0xff) << 24);
            String NoSerie= (serial == 0)?"No disponible":Integer.toString(serial);
            Marca= MonitorManufacturerDB.getManufacturerName(Marca);
            datosMonitores.add(new LinkedList<>(List.of(Marca, Modelo, NoSerie, 1)));
        }
        return datosMonitores;
    }
    
    public List<List<Object>> getApps(){
        List<List<Object>> apps= new LinkedList<>();
        //<editor-fold defaultstate="collapsed" desc="Para obtener las apps instaladas">
        String os= controladorSoftware.getFamily().toLowerCase();
        List<String> command= new LinkedList<>();
        if(os.contains("win")){
            command= ExecutingCommand.runNative("powershell Get-ChildItem HKLM:\\Software\\Microsoft\\Windows\\"+
                    "CurrentVersion\\Uninstall, HKLM:\\Software\\Wow6432Node\\Microsoft\\Windows\\"+
                    "CurrentVersion\\Uninstall, HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Uninstall,"+
                    " HKCU:\\Software\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall | \n" +
                    "ForEach-Object{\n" +
                    "    $props= Get-ItemProperty $_.PSPath -ErrorAction SilentlyContinue\n" +
                    "    if($props.DisplayName){\n" +
                    "        [PSCustomObject]@{\n" +
                    "            Name = $props.Displayname\n" +
                    "            Version = $props.DisplayVersion\n" +
                    "            InstallDate = $props.InstallDate\n" +
                    "            Size = $props.EstimatedSize\n" +
                    "        }\n" +
                    "    }\n" +
                    "} | Sort-Object Name | Format-List");
        }
        else if(os.contains("lin")){
            //command= ExecutingCommand.runNative();
        }
        if(command.isEmpty())
           return null;
        List<Object> app= new LinkedList<>();
        int it= 0;
        for(String linea:command){
            if(!linea.isBlank()){
                if(it == 4){
                    it= 0;
                    apps.add(app);
                    app= new LinkedList<>();
                }
                String dato= linea.split(":")[1].substring(1);
                if(dato.isBlank()) app.add("No disponible");
                else {
                    if(it == 2)
                        dato= dato.substring(0, 4)+"-"+dato.substring(4, 6)+"-"+dato.substring(6);
                    else if(it == 3){
                        int tam= Integer.parseInt(dato);
                        tam /= 1024;
                        dato= (tam > 0)?(tam + " MB"):(dato + " KB");
                    }
                    app.add(dato);
                }
                it++;
            }
        }
        //</editor-fold>
        return apps;
    }
    
    public List<Object> getAntivirus(){
        List<Object> antiVirus= new LinkedList<>();
        String os= controladorSoftware.getFamily().toLowerCase();
        List<String> command= new LinkedList<>();
        //<editor-fold defaultstate="collapsed" desc="Para obtener el antivirus instalado con su estado de protección y firmas">
        if(os.contains("win")){
            command= ExecutingCommand.runNative("powershell [Flags()] enum ProductState {\n" + 
                    "    Off         = 0x0000\n" +
                    "    On          = 0x1000   # Protección en tiempo real activada\n" +
                    "    Snoozed     = 0x2000   # Temporalmente desactivada\n" +
                    "    Expired     = 0x3000   # Suscripción o producto caducado\n" +
                    "}\n" +
                    "\n" +
                    "[Flags()] enum SignatureStatus {\n" +
                    "    UpToDate    = 0x00     # Firmas actualizadas\n" +
                    "    OutOfDate   = 0x10     # Firmas desactualizadas\n" +
                    "}\n" +
                    "\n" +
                    "[Flags()] enum ProductOwner {\n" +
                    "    NonMs       = 0x000    # De terceros\n" +
                    "    Windows     = 0x100    # De Microsoft (Defender, MSE)\n" +
                    "}\n" +
                    "\n" +
                    "[Flags()] enum ProductFlags {\n" +
                    "    SignatureStatus = 0x00F0   # Máscara para el estado de firmas\n" +
                    "    ProductOwner    = 0x0F00   # Máscara para el propietario\n" +
                    "    ProductState    = 0xF000   # Máscara para el estado del producto\n" +
                    "}\n" +
                    "\n" +
                    "$avProducts = Get-CimInstance -Namespace \"root/SecurityCenter2\" -ClassName \"AntivirusProduct\" -ErrorAction SilentlyContinue\n" +
                    "\n" +
                    "if (-not $avProducts) {\n" +
                    "    Write-Host \"No se pudo obtener información de antivirus o no hay ninguno instalado.\" -ForegroundColor Yellow\n" +
                    "    exit\n" +
                    "}\n" +
                    "\n" +
                    "Write-Host \"=== Análisis de Antivirus Instalados ===\" -ForegroundColor Cyan\n" +
                    "foreach ($product in $avProducts) {\n" +
                    "    Write-Host \"`n Producto: $($product.displayName)\" -ForegroundColor Green\n" +
                    "\n" +
                    "    [UInt32]$state = $product.productState\n" +
                    "\n" +
                    "    $currentState = [ProductState]($state -band [ProductFlags]::ProductState)\n" +
                    "    $signatureStatus = [SignatureStatus]($state -band [ProductFlags]::SignatureStatus)\n" +
                    "    $owner = [ProductOwner]($state -band [ProductFlags]::ProductOwner)\n" +
                    "\n" +
                    "    Write-Host \"Estado de Proteccion: $currentState\"\n" +
                    "    Write-Host \"Estado de Firmas: $signatureStatus\"\n" +
                    "    Write-Host \"Propietario: $owner\"\n" +
                    "}");
        }
        else if(os.contains("lin")){
            //command= ExecutingCommand.runNative();
        }
        //</editor-fold>
        if(command.isEmpty())
           return null;
        for(String linea:command){
            if(linea.contains("Producto")){
                if(!antiVirus.isEmpty()) break;
                String nombreAntivirus= linea.split(":")[1].trim();
                antiVirus.add(nombreAntivirus);
            }
            else if(linea.contains("Estado de Proteccion")){
                String estado= linea.split(":")[1].trim();
                switch(estado){
                    case "On" -> antiVirus.add("La protección en tiempo real está ACTIVADA");
                    case "Snoozed" -> antiVirus.add("La protección está temporalmente POSPUESTA");
                    case "Expired" -> antiVirus.add("El antivirus ha CADUCADO");
                    default -> antiVirus.add("La protección en tiempo real está DESACTIVADA");
                }
            }
            else if(linea.contains("Estado de Firmas")){
                String estado= linea.split(":")[1].trim();
                switch(estado){
                    case "UpToDate" -> antiVirus.add("Las firmas de virus están ACTUALIZADAS");
                    default -> antiVirus.add("Las firmas de virus están DESACTUALIZADAS");
                }
            }
        }
        return antiVirus;
    }
     
    public List<Object> getBocinas(){
        Mixer.Info[] mixerInfos= AudioSystem.getMixerInfo();
        String Marca, Modelo;
        for(Mixer.Info bocina: mixerInfos){
            String name= bocina.getName().toLowerCase();
            if(name.contains("altavo") && !name.contains("port")){
                Marca= bocina.getName();
                Modelo= (bocina.getVersion().equals("Unknown Version"))?"No disponible":bocina.getVersion();
                return new LinkedList<>(List.of(Marca, Modelo, "No disponible"));
            }
        }
        return null;
    }
    
    public List<Object> getTeclado(){
        List<UsbDevice> devices= controladorHardware.getUsbDevices(false);
        String Marca, Modelo, NoSerie;
        for(UsbDevice teclado : devices){
            String name= teclado.getName().toLowerCase();
            if(name.contains("teclado") || name.contains("keyboard")){ 
                Marca= teclado.getVendor();
                Modelo= teclado.getName();
                NoSerie= (teclado.getSerialNumber().isBlank())?"No disponible":teclado.getSerialNumber();
                return new LinkedList<>(List.of(Marca, Modelo, NoSerie));
            }
        }
        ComputerSystem equipo= controladorHardware.getComputerSystem();
        Marca= "Teclado integrado "+equipo.getManufacturer();
        Modelo= "No disponible";
        NoSerie= "No disponible";
        return new LinkedList<>(List.of(Marca, Modelo, NoSerie));
    }
     
    public List<Object> getMouse(){
        List<UsbDevice> devices= controladorHardware.getUsbDevices(false);
        String Marca, Modelo, NoSerie;
        for(UsbDevice mouse : devices){
            String name= mouse.getName().toLowerCase();
            if(name.contains("mouse") || name.contains("ratón")){ 
                Marca= mouse.getVendor();
                Modelo= mouse.getName();
                NoSerie= (mouse.getSerialNumber().isBlank())?"No disponible":mouse.getSerialNumber();
                return new LinkedList<>(List.of(Marca, Modelo, NoSerie));
            }
        }
        ComputerSystem equipo= controladorHardware.getComputerSystem();
        Marca= "MousePath integrado "+equipo.getManufacturer();
        Modelo= "No disponible";
        NoSerie= "No disponible";
        return new LinkedList<>(List.of(Marca, Modelo, NoSerie));
    }
     
    private String getTiempoActivo(long tiempo){
        tiempo/= 1000;
        long dias= tiempo / 86400;
        long horas= tiempo / 3600 - dias * 24;
        long minutos= tiempo / 60 - dias * 1440 - horas * 60;
        long segundos= tiempo - minutos * 60 - horas * 3600 - dias * 86400;
        String horasUnDigito= ((horas / 10) == 0)? "0" + horas : ((Object)horas).toString();
        String minutosUnDigito= ((minutos / 10) == 0)? "0" + minutos : ((Object)minutos).toString();
        String segundosUnDigito= ((segundos / 10) == 0)? "0" + segundos : ((Object)segundos).toString();
        String tiempoActivo= dias + ":" + horasUnDigito + ":" + minutosUnDigito + ":" + segundosUnDigito;
        return tiempoActivo;
    }
     
    public List<String[]> getSessionLogins(){
        List<String[]> logins= new LinkedList<>();
        for(OSSession session : controladorSoftware.getSessions()){
            long loginTime;
            if((loginTime= session.getLoginTime()) != 0){
                String cuenta= session.getUserName();
                String fechaAll= new Date(loginTime).toString();
                String fecha= "";
                //<editor-fold defaultstate="collapsed" desc="asignación de la fecha en formato como en: Domingo, 24 de Agosto del 2025">
                switch (fechaAll.split(" ")[0].toLowerCase()) {
                    case "sun" -> fecha= "Domingo";
                    case "mon" -> fecha= "Lunes";
                    case "tue" -> fecha= "Martes";
                    case "wed" -> fecha= "Miércoles";
                    case "thu" -> fecha= "Jueves";
                    case "fri" -> fecha= "Viernes";
                    case "sat" -> fecha= "Sábado";
                }
                fecha+= ", " + fechaAll.split(" ")[2] + " de ";
                switch (fechaAll.split(" ")[1].toLowerCase()) {
                    case "jan" -> fecha+= "Enero";
                    case "feb" -> fecha+= "Febrero";
                    case "mar" -> fecha+= "Marzo";
                    case "apr" -> fecha+= "Abril";
                    case "may" -> fecha+= "Mayo";
                    case "jun" -> fecha+= "Junio";
                    case "jul" -> fecha+= "Julio";
                    case "aug" -> fecha+= "Agosto";
                    case "sep" -> fecha+= "Septiembre";
                    case "oct" -> fecha+= "Octubre";
                    case "nov" -> fecha+= "Noviembre";
                    case "dec" -> fecha+= "Diciembre";
                }
                fecha+= " del " + fechaAll.split(" ")[5];
                //</editor-fold>
                String hora= fechaAll.split(" ")[3];
                long ahora= new Date().getTime();
                String tiempoActiva= getTiempoActivo(ahora - loginTime);
                logins.add(new String[]{cuenta, fecha, hora, tiempoActiva});
            }
        }
        return logins;
    }
     
    public Map<String, String> getSensors(){
        Map<String, String> sensores= new HashMap<>();
        Queue<String> colaMarcasHD= new LinkedList<>();
        Queue<String> colaModelosHD= new LinkedList<>();
        for(List<Object> disco : getDiscoDuro()){
            /*contiene las marcas de todos los discos duros, se usa para identificar al disco duro
            junto con el modelo en las temperaturas que se agregarán al map sensores*/
            colaMarcasHD.add(disco.get(0).toString());
            /*contiene los modelos de todos los discos duros, además de lo anterior también se usa 
            para buscar las temperaturas de los discos duros en los sensores*/
            colaModelosHD.add(disco.get(1).toString().split(" ")[0]);
        }
        String hdTEMP= ""; //sirve para acumular las temp con sus modelos de todos los discos duros
        //<editor-fold defaultstate="collapsed" desc="Obtener valores de temp y volt del cpu y temp del disco duro">
        try{
            String[] command= new String[]{
                "powershell",
                "$dllPath = 'LibreHardwareMonitorLib.dll'\n",
                "Unblock-File -LiteralPath $dllPath -ErrorAction SilentlyContinue\n",
                "try {\n"+
                "    Add-Type -LiteralPath $dllPath\n"+
                "} catch {\n"+
                "    Write-Error 'Error al cargar la DLL: $_'\n"+
                "    exit\n"+
                "}\n",
                "$computer = [LibreHardwareMonitor.Hardware.Computer]::new()\n",
                "$computer.IsCPUEnabled = $true\n",
                "$computer.IsGPUEnabled = $true\n",
                "$computer.IsMemoryEnabled = $true\n",
                "$computer.IsMotherboardEnabled = $true\n",
                "$computer.IsStorageEnabled = $true  # Para discos duros y SSD\n",
                "$computer.Open()\n",
                "$computer.Hardware | ForEach-Object { $_.Update() }\n",
                "$sensorData = foreach ($hardware in $computer.Hardware) {\n"+
                "    foreach ($sensor in $hardware.Sensors) {\n"+
                "        if ($sensor.Value -ne $null) {\n"+
                "            [PSCustomObject]@{\n"+
                "                Hardware = $hardware.Name\n"+
                "                Sensor = $sensor.Name\n"+
                "                Type = $sensor.SensorType\n"+
                "                Value = $sensor.Value\n"+
                "                Unit = switch ($sensor.SensorType) {\n"+
                "                    'Temperature' { '°C' }\n"+
                "                    'Load' { '%' }\n"+
                "                    'Fan' { 'RPM' }\n"+
                "                    'Clock' { 'MHz' }\n"+
                "                    'Power' { 'W' }\n"+
                "                    'Voltage' { 'V' }\n"+
                "                    default { '' }\n"+
                "                }\n"+
                "            }\n"+
                "        }\n"+
                "    }\n"+
                "}\n",
                "$computer.Close()\n",
                "$sensorData | Format-Table -AutoSize\n" 
            };
            ProcessBuilder crear= new ProcessBuilder(command);
            crear.redirectErrorStream(true);
            Process proceso= crear.start();
            BufferedReader lector= new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String line;
            while ((line= lector.readLine()) != null){
                if((line.contains("CPU Package") || line.contains("CPU Cores")) && line.contains("Temperature"))
                    sensores.put("CPU Temp", line.split("Temperature")[1].trim().split(" ")[0] + " ºC");
                else if(line.contains("CPU Core") && line.contains("Voltage")){
                    try{
                        if(sensores.get("CPU Volt") != null) continue;
                    }
                    catch(NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    sensores.put("CPU Volt", line.split("Voltage")[1].trim().split(" ")[0] + " Volt");
                }
                else if(line.contains(colaModelosHD.peek() == null? "" : colaModelosHD.peek()) && line.contains("Temperature")){
                    hdTEMP+= (hdTEMP.equals("")? hdTEMP : "; ") + line.split("Temperature")[2].trim().split(" ")[0] 
                            + " ºC" + " (" + colaMarcasHD.poll() + " " + colaModelosHD.poll() + ")";
                    sensores.put("HD Temp", hdTEMP);
                }
            }
            proceso.waitFor();
        }
        catch(IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
        //</editor-fold>
        return sensores;
    }
     
    private final OperatingSystem controladorSoftware;
    private final HardwareAbstractionLayer controladorHardware;
    private final SystemInfo sistema;
}
