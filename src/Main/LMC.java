/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Controlador.OpcionesConfig;
import Vista.Inicio;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class LMC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*carga el archivo de configuración para determinar cual LookAndFeel se va a establecer,
        si no se encuentra el archivo se establece Nimbus como LookAndFeel*/
        String Laf= OpcionesConfig.cargarTema();
        Inicio.setLaf(Laf);
        LMC app= new LMC();
        SwingUtilities.invokeLater(()-> {
            app.iniciar();
        });
    }
    
    public LMC(){
        inicio= new Inicio();
    }
    
    public void iniciar(){
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
        //inicio.autenticar();
    }
    
    private final Inicio inicio;
    
}
