/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class JEDECManufacturers {
    private static final Map<String, String> MANUFACTURERS = new HashMap<>();
    
    static {
        // =========== FABRICANTES PRINCIPALES DE MÓDULOS RAM ===========
        MANUFACTURERS.put("0198", "SK Hynix (Hyundai Electronics)");
        MANUFACTURERS.put("029E", "Samsung Electronics");
        MANUFACTURERS.put("04A3", "ADATA Technology Co., Ltd.");
        MANUFACTURERS.put("04B3", "Elpida Memory, Inc.");
        MANUFACTURERS.put("04BA", "Kingston Technology Company, Inc.");
        MANUFACTURERS.put("04BF", "Corsair Memory, Inc.");
        MANUFACTURERS.put("04CB", "Micron Technology, Inc. (Crucial)");
        MANUFACTURERS.put("04CD", "G.Skill International Enterprise Co., Ltd.");
        MANUFACTURERS.put("04D2", "Team Group Inc.");
        MANUFACTURERS.put("04DA", "Patriot Memory LLC");
        MANUFACTURERS.put("04E0", "Transcend Information, Inc.");
        MANUFACTURERS.put("04E1", "A-DATA Technology Co., Ltd.");
        MANUFACTURERS.put("04EB", "ESS Technology, Inc.");
        MANUFACTURERS.put("04F8", "G.Skill International (alternate)");
        MANUFACTURERS.put("04FF", "Annex Products, Inc.");
        MANUFACTURERS.put("050E", "Apacer Technology Inc.");
        MANUFACTURERS.put("0519", "Corsair Memory (alternate)");
        MANUFACTURERS.put("0525", "Team Group (alternate)");
        MANUFACTURERS.put("053A", "ADATA Technology (alternate)");
        MANUFACTURERS.put("054C", "PNY Technologies, Inc.");
        MANUFACTURERS.put("057F", "Crucial Technology (Micron)");
        MANUFACTURERS.put("059B", "Micron Technology (alternate)");
        MANUFACTURERS.put("0605", "Patriot Memory (alternate)");
        MANUFACTURERS.put("0607", "Crucial Technology (alternate)");
        MANUFACTURERS.put("060C", "G.Skill International (alternate)");
        MANUFACTURERS.put("0726", "ADATA Technology (alternate)");
        MANUFACTURERS.put("0731", "Team Group (alternate)");
        MANUFACTURERS.put("0734", "Corsair Memory (alternate)");
        MANUFACTURERS.put("07D0", "Transcend Information (alternate)");
        MANUFACTURERS.put("07D1", "Kingston Technology (alternate)");
        MANUFACTURERS.put("07DA", "G.Skill International (alternate)");
        MANUFACTURERS.put("07FE", "Corsair Memory (alternate)");
        MANUFACTURERS.put("07FF", "Unigen Corporation");
        MANUFACTURERS.put("0801", "Patriot Memory (alternate)");
        MANUFACTURERS.put("0808", "ADATA Technology (alternate)");
        MANUFACTURERS.put("080B", "Team Group (alternate)");
        MANUFACTURERS.put("080C", "G.Skill International (alternate)");
        MANUFACTURERS.put("080F", "Crucial Technology (alternate)");
        MANUFACTURERS.put("0811", "Corsair Memory (alternate)");
        MANUFACTURERS.put("0813", "Patriot Memory (alternate)");
        MANUFACTURERS.put("0814", "ADATA Technology (alternate)");
        MANUFACTURERS.put("0815", "Team Group (alternate)");
        MANUFACTURERS.put("0816", "G.Skill International (alternate)");
        MANUFACTURERS.put("0817", "Crucial Technology (alternate)");
        MANUFACTURERS.put("0819", "Corsair Memory (alternate)");
        MANUFACTURERS.put("081B", "Patriot Memory (alternate)");
        
        // =========== FABRICANTES DE CHIPS DE MEMORIA ===========
        MANUFACTURERS.put("0001", "Ford Microelectronics, Inc.");
        MANUFACTURERS.put("0002", "Motorola, Inc.");
        MANUFACTURERS.put("0003", "Intel Corporation");
        MANUFACTURERS.put("0004", "Mitsubishi Electric");
        MANUFACTURERS.put("0005", "Fujitsu Limited");
        MANUFACTURERS.put("0006", "Texas Instruments, Inc.");
        MANUFACTURERS.put("0007", "Matsushita Electric Industrial");
        MANUFACTURERS.put("0008", "Hitachi, Ltd.");
        MANUFACTURERS.put("000A", "International Business Machines (IBM)");
        MANUFACTURERS.put("000B", "Siemens AG");
        MANUFACTURERS.put("000D", "NEC Corporation");
        MANUFACTURERS.put("000F", "Oki Electric Industry Co., Ltd.");
        MANUFACTURERS.put("0010", "AT&T");
        MANUFACTURERS.put("0011", "Cypress Semiconductor");
        MANUFACTURERS.put("0012", "Micron Technology");
        MANUFACTURERS.put("0014", "European Silicon Structures (ES2)");
        MANUFACTURERS.put("0015", "Altera Corporation");
        MANUFACTURERS.put("0016", "Xilinx, Inc.");
        MANUFACTURERS.put("0018", "Rockwell International");
        MANUFACTURERS.put("001B", "Apple Computer, Inc.");
        MANUFACTURERS.put("001E", "Toshiba Corporation");
        MANUFACTURERS.put("001F", "STMicroelectronics");
        MANUFACTURERS.put("0020", "LSI Logic Corporation");
        MANUFACTURERS.put("0024", "Advanced Micro Devices (AMD)");
        MANUFACTURERS.put("0026", "Analog Devices, Inc.");
        MANUFACTURERS.put("002B", "Maxim Integrated Products");
        MANUFACTURERS.put("002C", "Dallas Semiconductor");
        MANUFACTURERS.put("002E", "Gould, Inc.");
        MANUFACTURERS.put("0030", "Integrated Device Technology (IDT)");
        MANUFACTURERS.put("0034", "Matsushita Electronics");
        MANUFACTURERS.put("0036", "Microchip Technology, Inc.");
        MANUFACTURERS.put("0037", "Mitsubishi Electric");
        MANUFACTURERS.put("003A", "National Semiconductor");
        MANUFACTURERS.put("003B", "NEC Electronics");
        MANUFACTURERS.put("003D", "RCA");
        MANUFACTURERS.put("003E", "Raytheon Company");
        MANUFACTURERS.put("003F", "Rohm Co., Ltd.");
        MANUFACTURERS.put("0040", "Sanyo Electric Co., Ltd.");
        MANUFACTURERS.put("0041", "Sharp Corporation");
        MANUFACTURERS.put("0043", "Siliconix, Inc.");
        MANUFACTURERS.put("0044", "Sony Corporation");
        MANUFACTURERS.put("0046", "Synertek");
        MANUFACTURERS.put("0049", "VLSI Technology, Inc.");
        MANUFACTURERS.put("004A", "Western Digital Corporation");
        MANUFACTURERS.put("004C", "Zilog, Inc.");
        MANUFACTURERS.put("0051", "Infineon Technologies AG");
        MANUFACTURERS.put("0052", "Winbond Electronics Corporation");
        MANUFACTURERS.put("0056", "Maspar Computer Corporation");
        MANUFACTURERS.put("0058", "3Com Corporation");
        MANUFACTURERS.put("005D", "Numonyx (Intel/STMicro)");
        MANUFACTURERS.put("005E", "STMicroelectronics (alternate)");
        MANUFACTURERS.put("005F", "Seiko Instruments, Inc.");
        
        // =========== FABRICANTES ADICIONALES DE MÓDULOS ===========
        MANUFACTURERS.put("0C04", "Valley Forge");
        MANUFACTURERS.put("0C0B", "Foxconn");
        MANUFACTURERS.put("0C0C", "Parade Technologies");
        MANUFACTURERS.put("0C51", "A-DATA Technology");
        MANUFACTURERS.put("0C9B", "Micron Technology");
        MANUFACTURERS.put("0C9C", "Samsung Electronics");
        MANUFACTURERS.put("0C9D", "SK Hynix");
        MANUFACTURERS.put("0C9E", "Elpida Memory");
        MANUFACTURERS.put("0CA1", "Nanya Technology Corporation");
        MANUFACTURERS.put("0CA2", "Powerchip Semiconductor");
        MANUFACTURERS.put("0CA3", "Winbond Electronics");
        MANUFACTURERS.put("0CA4", "Etron Technology");
        MANUFACTURERS.put("0CA5", "ProMOS Technologies");
        MANUFACTURERS.put("0CA6", "Renesas Technology");
        MANUFACTURERS.put("0CA7", "Rohm");
        MANUFACTURERS.put("0CA8", "Toshiba");
        MANUFACTURERS.put("0CA9", "Fujitsu");
        MANUFACTURERS.put("0CAA", "Sharp");
        MANUFACTURERS.put("0CAB", "Sony");
        MANUFACTURERS.put("0CAC", "Mitsubishi");
        MANUFACTURERS.put("0CAD", "Panasonic");
        MANUFACTURERS.put("0CAE", "Oki");
        MANUFACTURERS.put("0CAF", "Spansion");
        MANUFACTURERS.put("0CB0", "AMD");
        MANUFACTURERS.put("0CB1", "IBM");
        MANUFACTURERS.put("0CB2", "Infineon Technologies");
        MANUFACTURERS.put("0CB3", "Macronix International");
        MANUFACTURERS.put("0CB4", "STMicroelectronics");
        MANUFACTURERS.put("0CB5", "Texas Instruments");
        MANUFACTURERS.put("0CB6", "United Microelectronics Corporation (UMC)");
        MANUFACTURERS.put("0CB7", "Vanguard International");
        MANUFACTURERS.put("0CB8", "Mosel Vitelic");
        MANUFACTURERS.put("0CB9", "TwinMOS Technologies");
        MANUFACTURERS.put("0CBA", "Kingmax Semiconductor");
        MANUFACTURERS.put("0CBB", "Memory Card Technology");
        MANUFACTURERS.put("0CBC", "Corsair Memory");
        MANUFACTURERS.put("0CBD", "G.Skill");
        MANUFACTURERS.put("0CBE", "Team Group");
        MANUFACTURERS.put("0CBF", "Patriot Memory");
        MANUFACTURERS.put("0CC0", "ADATA Technology");
        MANUFACTURERS.put("0CC1", "Transcend Information");
        MANUFACTURERS.put("0CC2", "Apacer Technology");
        MANUFACTURERS.put("0CC3", "PNY");
        MANUFACTURERS.put("0CC4", "Crucial Technology");
        MANUFACTURERS.put("0CC5", "Kingston Technology");
        MANUFACTURERS.put("0CC6", "ESS Technology");
        MANUFACTURERS.put("0CC7", "Unigen Corporation");
        
        // =========== FABRICANTES DE NOTEBOOKS/SERVIDORES ===========
        MANUFACTURERS.put("00B0", "ASUSTek Computer Inc.");
        MANUFACTURERS.put("00B1", "ASRock Inc.");
        MANUFACTURERS.put("00B2", "Gigabyte Technology Co., Ltd.");
        MANUFACTURERS.put("00B3", "Micro-Star International (MSI)");
        MANUFACTURERS.put("00B4", "EVGA Corporation");
        MANUFACTURERS.put("00B5", "Biostar Microtech Int'l Corp.");
        
        // =========== FABRICANTES ESPECIALIZADOS ===========
        MANUFACTURERS.put("0201", "Smart Modular Technologies");
        MANUFACTURERS.put("0202", "Viking Components");
        MANUFACTURERS.put("0203", "Super Talent Technology");
        MANUFACTURERS.put("0204", "Mushkin Enhanced Memory");
        MANUFACTURERS.put("0205", "GeIL (Golden Emperor International Ltd.)");
        MANUFACTURERS.put("0206", "OCZ Technology Group");
        MANUFACTURERS.put("0207", "PNY (alternate)");
        MANUFACTURERS.put("0208", "Centon Electronics");
        MANUFACTURERS.put("0209", "SimpleTech");
        MANUFACTURERS.put("020A", "Edge Memory");
        
        // =========== FABRICANTES DE SERVIDORES/EMPRESARIALES ===========
        MANUFACTURERS.put("0301", "HP (Hewlett-Packard)");
        MANUFACTURERS.put("0302", "Dell Inc.");
        MANUFACTURERS.put("0303", "Lenovo");
        MANUFACTURERS.put("0304", "IBM Server");
        MANUFACTURERS.put("0305", "Sun Microsystems (Oracle)");
        MANUFACTURERS.put("0306", "Fujitsu Siemens");
        MANUFACTURERS.put("0307", "Toshiba");
        MANUFACTURERS.put("0308", "NEC Computers");
        MANUFACTURERS.put("0309", "SuperMicro Computer");
        
        // Fabricante especial que aparece mucho
        MANUFACTURERS.put("04E2", "Hynix Semiconductor (alternate)");
        MANUFACTURERS.put("04E3", "Samsung Electronics (alternate)");
        MANUFACTURERS.put("04E4", "Elpida Memory (alternate)");
        MANUFACTURERS.put("04E5", "Micron Technology (alternate)");
        
        // Últimos para completar 100
        MANUFACTURERS.put("04E6", "Nanya Technology");
        MANUFACTURERS.put("04E7", "Powerchip Semiconductor");
        MANUFACTURERS.put("04E8", "Winbond Electronics");
        MANUFACTURERS.put("04E9", "Etron Technology");
        MANUFACTURERS.put("04EA", "ProMOS Technologies");
    }
    
    public static String getManufacturerName(String jedecCode) {
        if (jedecCode == null || jedecCode.trim().isEmpty())
            return "Unknown";
        // Normalizar el código (mayúsculas, sin espacios)
        String code = jedecCode.trim().toUpperCase();
        // Si el código es exactamente 4 caracteres hex, buscar
        if (code.matches("[0-9A-F]{4}")) {
            String manufacturer = MANUFACTURERS.get(code);
            if (manufacturer != null)
                return manufacturer;
        }
        // Si ya es un nombre conocido (como "micron"), devolverlo tal cual
        if (!code.matches(".*[0-9].*"))
            return jedecCode;
        // Si no se encuentra, devolver el código con indicación
        return String.format("No disponible (JEDEC: %s)", code);
    }
    
    public static Map<String, String> getAllManufacturers() {
        return new HashMap<>(MANUFACTURERS);
    }
    
    // Método adicional para identificar por part number común
    public static String identifyByPartNumber(String partNumber) {
        if (partNumber == null) return null;
        String partUpper = partNumber.toUpperCase();
        if (partUpper.contains("F4-") || partUpper.contains("GTZ") || partUpper.contains("RIPJAWS")) {
            return "G.Skill International Enterprise Co., Ltd.";
        } else if (partUpper.contains("CMK") || partUpper.contains("CMW") || partUpper.contains("VENGEANCE")) {
            return "Corsair Memory, Inc.";
        } else if (partUpper.contains("KVR") || partUpper.contains("KHX") || partUpper.contains("HYPERX")) {
            return "Kingston Technology Company, Inc.";
        } else if (partUpper.contains("CT") && partUpper.contains("Crucial")) {
            return "Micron Technology, Inc. (Crucial)";
        } else if (partUpper.contains("T-") && partUpper.contains("TEAM")) {
            return "Team Group Inc.";
        } else if (partUpper.contains("AX") && partUpper.contains("PATRIOT")) {
            return "Patriot Memory LLC";
        } else if (partUpper.contains("AX4U") || partUpper.contains("ADATA")) {
            return "ADATA Technology Co., Ltd.";
        } else if (partUpper.contains("JM") || partUpper.contains("Transcend")) {
            return "Transcend Information, Inc.";
        } else if (partUpper.contains("M") && partUpper.contains("Acer")) {
            return "Apacer Technology Inc.";
        }
        return null;
    }
}