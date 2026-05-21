/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;

/**
 *
 * @author PC
 */
public class HDDandSSDManufactures {
    
    //Identificar la marca del HDD o SSD mediante el número del modelo
    public static String getManufacturerName(String modelNumber){
        if(modelNumber.contains("WDS"))
            return "Western Digital SSD";
        else if(modelNumber.contains("WDB") || modelNumber.contains("WDBA"))
            return "Western Digital MyBook";
        else if(modelNumber.contains("WDX") && modelNumber.contains("WDE"))
            return "Western Digital Elements";
        else if(modelNumber.contains("WD") && (modelNumber.contains("Blue") || modelNumber.contains("K")))
            return "Western Digital Blue";
        else if(modelNumber.contains("WD") && modelNumber.contains("B"))
            return "Western Digital Black";
        else if(modelNumber.contains("WD") && modelNumber.contains("R"))
            return "Western Digital Red";
        else if(modelNumber.contains("WD") && modelNumber.contains("G"))
            return "Western Digital Green";
        else if(modelNumber.contains("WD"))
            return "Western Digital";
        else if(modelNumber.contains("ST") && (modelNumber.contains("DM") || modelNumber.contains("NM")))
            return "Seagate Barracuda";
        else if(modelNumber.contains("ST") && (modelNumber.contains("VN") || modelNumber.contains("CL")))
            return "Seagate IronWolf";
        else if(modelNumber.contains("ST") && modelNumber.contains("VN"))
            return "Seagate SkyHawk";
        else if(modelNumber.contains("ST") && (modelNumber.contains("EN") || modelNumber.contains("NX")))
            return "Seagate Exos";
        else if(modelNumber.contains("ST") && (modelNumber.contains("LM") || modelNumber.contains("LX")))
            return "Seagate Laptop";
        else if(modelNumber.contains("ST") && modelNumber.contains("FM"))
            return "Seagate Nytro SSD";
        else if(modelNumber.contains("ST"))
            return "Seagate";
        else if(modelNumber.contains("DT"))
            return "Toshiba Desktop";
        else if(modelNumber.contains("MQ") || modelNumber.contains("MK"))
            return "Toshiba Laptop";
        else if(modelNumber.contains("MG") || modelNumber.contains("AL"))
            return "Toshiba Enterprise";
        else if(modelNumber.contains("TH") || modelNumber.contains("KS"))
            return "Toshiba SSD";
        else if(modelNumber.toLowerCase().contains("toshiba"))
            return "Toshiba";
        else if(modelNumber.contains("MZ"))
            return "Samsung SSD";
        else if(modelNumber.toLowerCase().contains("samsung"))
            return "Samsung";
        else if(modelNumber.contains("CT") || modelNumber.contains("CP"))
            return "Crucial SSD";
        else if(modelNumber.toLowerCase().contains("crucial"))
            return "Crucial";
        else if(modelNumber.contains("SA") || modelNumber.contains("SF"))
            return "Kingston SSD";
        else if(modelNumber.toLowerCase().contains("kingston"))
            return "Kingston";
        else if(modelNumber.contains("SD"))
            return "Sandisk SSD";
        else if(modelNumber.toLowerCase().contains("sandisk") && modelNumber.contains("WDBA"))
            return "Sandisk Extreme Portable";
        else if(modelNumber.toLowerCase().contains("sandisk"))
            return "Sandisk";
        else if(modelNumber.contains("AS") || modelNumber.contains("AX"))
            return "ADATA SSD";
        else if(modelNumber.toLowerCase().contains("adata"))
            return "ADATA";
        else if(modelNumber.contains("CSSD") || modelNumber.contains("CM"))
            return "Corsair SSD";
        else if(modelNumber.toLowerCase().contains("corsair"))
            return "Corsair";
        else if(modelNumber.contains("SB"))
            return "Sabrent SSD";
        else if(modelNumber.toLowerCase().contains("sabrent"))
            return "Sabrent";
        else if(modelNumber.contains("TM8") || modelNumber.contains("T"))
            return "TeamGroup SSD";
        else if(modelNumber.toLowerCase().contains("team"))
            return "TeamGroup";
        else if(modelNumber.contains("GP"))
            return "Gibabyte SSD";
        else if(modelNumber.toLowerCase().contains("gibabyte"))
            return "Gibabyte";
        else if(modelNumber.toLowerCase().contains("intel"))
            return "Intel SSD";
        else if(!modelNumber.split(" ")[0].matches(".*[0-9].*"))
            return modelNumber.split(" ")[0];
        else return "No disponible";
    }
}
