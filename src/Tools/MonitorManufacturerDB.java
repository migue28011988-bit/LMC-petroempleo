/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;

import java.util.HashMap;
import java.util.Map;

/**
 * Base de datos de fabricantes de monitores por ID EDID
 * Contiene los 100 fabricantes más comunes
 */
public class MonitorManufacturerDB {
    
    private static final Map<String, String> MANUFACTURER_DB = new HashMap<>();
    
    static {
        // Inicializar la base de datos con los 100 fabricantes más comunes
        initializeManufacturerDB();
    }
    
    /**
     * Obtiene el nombre del fabricante a partir del código EDID
     * 
     * @param edidCode Código EDID de 3 caracteres (ej: "GSM", "DEL", "SAM")
     * @return Nombre del fabricante o mensaje indicando que es desconocido
     */
    public static String getManufacturerName(String edidCode) {
        if (edidCode == null || edidCode.trim().isEmpty()) {
            return "Código EDID inválido";
        }
        String code = edidCode.trim().toUpperCase();
        return MANUFACTURER_DB.getOrDefault(code, "No disponible");
    }
    
    /**
     * Verifica si un código EDID existe en la base de datos
     * 
     * @param edidCode Código EDID a verificar
     * @return true si el código existe, false en caso contrario
     */
    public static boolean isKnownManufacturer(String edidCode) {
        if (edidCode == null) return false;
        return MANUFACTURER_DB.containsKey(edidCode.trim().toUpperCase());
    }
    
    /**
     * Obtiene la cantidad de fabricantes en la base de datos
     * 
     * @return Número total de fabricantes registrados
     */
    public static int getManufacturerCount() {
        return MANUFACTURER_DB.size();
    }
    
    /**
     * Lista todos los códigos EDID registrados
     * 
     * @return Array con todos los códigos EDID disponibles
     */
    public static String[] getAllManufacturerCodes() {
        return MANUFACTURER_DB.keySet().toArray(new String[0]);
    }
    
    /**
     * Inicializa la base de datos con los 100 fabricantes más comunes
     */
    private static void initializeManufacturerDB() {
        // 1-50: Principales fabricantes globales
        MANUFACTURER_DB.put("SAM", "Samsung");
        MANUFACTURER_DB.put("DEL", "Dell");
        MANUFACTURER_DB.put("LEN", "Lenovo");
        MANUFACTURER_DB.put("HWP", "Hewlett-Packard (HP)");
        MANUFACTURER_DB.put("ACI", "Acer");
        MANUFACTURER_DB.put("AUO", "AU Optronics");
        MANUFACTURER_DB.put("CMO", "Chi Mei Optoelectronics (Innolux)");
        MANUFACTURER_DB.put("SEC", "Samsung (alternativo)");
        MANUFACTURER_DB.put("GSM", "LG Electronics (Goldstar)");
        MANUFACTURER_DB.put("BNQ", "BenQ");
        MANUFACTURER_DB.put("AOC", "AOC International");
        MANUFACTURER_DB.put("PHL", "Philips");
        MANUFACTURER_DB.put("NEC", "NEC Display Solutions");
        MANUFACTURER_DB.put("VSC", "ViewSonic");
        MANUFACTURER_DB.put("SNY", "Sony");
        MANUFACTURER_DB.put("LGD", "LG Display");
        MANUFACTURER_DB.put("CPT", "Chunghwa Picture Tubes");
        MANUFACTURER_DB.put("IVM", "Iiyama");
        MANUFACTURER_DB.put("EIZ", "EIZO");
        MANUFACTURER_DB.put("MS_", "Micro-Star International (MSI)");
        MANUFACTURER_DB.put("MSI", "MSI (alternativo)");
        MANUFACTURER_DB.put("TOS", "Toshiba");
        MANUFACTURER_DB.put("ASU", "ASUSTeK Computer");
        MANUFACTURER_DB.put("HIT", "Hitachi");
        MANUFACTURER_DB.put("SHI", "Sharp");
        MANUFACTURER_DB.put("PGS", "Princeton Graphic Systems");
        MANUFACTURER_DB.put("CTX", "CTX International");
        MANUFACTURER_DB.put("MAG", "MAG InnoVision");
        MANUFACTURER_DB.put("BOE", "BOE Technology Group");
        MANUFACTURER_DB.put("INL", "Innolux");
        MANUFACTURER_DB.put("SDC", "Samsung Display");
        MANUFACTURER_DB.put("SHP", "Sharp (alternativo)");
        MANUFACTURER_DB.put("APP", "Apple");
        MANUFACTURER_DB.put("GCS", "GCS GmbH");
        MANUFACTURER_DB.put("ADI", "Analog Devices Inc.");
        MANUFACTURER_DB.put("IVO", "I-O Data Device");
        MANUFACTURER_DB.put("MED", "Medion");
        MANUFACTURER_DB.put("ELO", "Elo Touch Solutions");
        MANUFACTURER_DB.put("TSB", "Toshiba America");
        MANUFACTURER_DB.put("WST", "Westinghouse Digital");
        MANUFACTURER_DB.put("YSN", "Y-Sync Inc.");
        MANUFACTURER_DB.put("PIO", "Pioneer");
        MANUFACTURER_DB.put("VZE", "Vestel Elektronik");
        MANUFACTURER_DB.put("CLA", "Claas & Partner");
        MANUFACTURER_DB.put("DEC", "Digital Equipment Corporation");
        MANUFACTURER_DB.put("MEL", "Mitsubishi Electric");
        MANUFACTURER_DB.put("NVD", "NVIDIA");
        MANUFACTURER_DB.put("RHT", "RightHand Technologies");
        MANUFACTURER_DB.put("SII", "Silicon Image");
        MANUFACTURER_DB.put("VIT", "Vity");
        
        // 51-100: Fabricantes adicionales y OEMs
        MANUFACTURER_DB.put("DDC", "Digital Data Communications");
        MANUFACTURER_DB.put("HKC", "HKC");
        MANUFACTURER_DB.put("HSD", "HannStar Display");
        MANUFACTURER_DB.put("HSL", "Hansol");
        MANUFACTURER_DB.put("AMW", "AMW");
        MANUFACTURER_DB.put("BMM", "BMM");
        MANUFACTURER_DB.put("CHA", "Chaplet Systems");
        MANUFACTURER_DB.put("CMN", "Chimei Innolux");
        MANUFACTURER_DB.put("CPL", "Compal Electronics");
        MANUFACTURER_DB.put("CTC", "CTC");
        MANUFACTURER_DB.put("CVT", "Conrac GmbH");
        MANUFACTURER_DB.put("DWE", "Daewoo");
        MANUFACTURER_DB.put("ECS", "Elitegroup Computer Systems");
        MANUFACTURER_DB.put("EMC", "EMC");
        MANUFACTURER_DB.put("ENC", "Enclosure Services");
        MANUFACTURER_DB.put("EPI", "Envision Peripherals");
        MANUFACTURER_DB.put("FUS", "Fujitsu Siemens");
        MANUFACTURER_DB.put("GPV", "GPV");
        MANUFACTURER_DB.put("GWY", "Gateway");
        MANUFACTURER_DB.put("HEI", "Hei");
        MANUFACTURER_DB.put("HTC", "HTC");
        MANUFACTURER_DB.put("HWD", "Howard");
        MANUFACTURER_DB.put("IBM", "IBM");
        MANUFACTURER_DB.put("ICD", "ICD");
        MANUFACTURER_DB.put("ICL", "Fujitsu ICL");
        MANUFACTURER_DB.put("INT", "Intergraph");
        MANUFACTURER_DB.put("JVC", "JVC");
        MANUFACTURER_DB.put("KDS", "KDS");
        MANUFACTURER_DB.put("KFC", "Kolin");
        MANUFACTURER_DB.put("LKM", "LKM");
        MANUFACTURER_DB.put("LPL", "LG.Philips LCD");
        MANUFACTURER_DB.put("LTN", "Lite-On");
        MANUFACTURER_DB.put("MEG", "Megapixel");
        MANUFACTURER_DB.put("MEI", "Methode Electronics");
        MANUFACTURER_DB.put("MIC", "Mitsubishi");
        MANUFACTURER_DB.put("MIR", "Mirai");
        MANUFACTURER_DB.put("MTC", "Mitac");
        MANUFACTURER_DB.put("NAN", "NANAO");
        MANUFACTURER_DB.put("NOK", "Nokia");
        MANUFACTURER_DB.put("OQI", "Optiquest");
        MANUFACTURER_DB.put("PAN", "Panasonic");
        MANUFACTURER_DB.put("PBN", "Packard Bell");
        MANUFACTURER_DB.put("PLL", "AOC (Plessey)");
        MANUFACTURER_DB.put("PNP", "Plug and Play (genérico)");
        MANUFACTURER_DB.put("PRT", "Princeton");
        MANUFACTURER_DB.put("PTS", "Plain Tree Systems");
        MANUFACTURER_DB.put("QDS", "Quanta Display");
        MANUFACTURER_DB.put("REL", "Relisys");
        MANUFACTURER_DB.put("RTK", "Realtek");
        MANUFACTURER_DB.put("SDI", "Samsung (SDI)");
        MANUFACTURER_DB.put("SGI", "Silicon Graphics");
        MANUFACTURER_DB.put("SMC", "Standard Microsystems");
        MANUFACTURER_DB.put("SPT", "Sceptre");
        MANUFACTURER_DB.put("STN", "Samtron");
        MANUFACTURER_DB.put("TAT", "Tatung");
        MANUFACTURER_DB.put("TKV", "Teknika");
        MANUFACTURER_DB.put("TRI", "Trigem");
        MANUFACTURER_DB.put("UNM", "Unisys");
        MANUFACTURER_DB.put("WTC", "Wang");
        MANUFACTURER_DB.put("XXX", "No registrado (genérico)");
        MANUFACTURER_DB.put("ZEN", "Zenith");
    }
}
