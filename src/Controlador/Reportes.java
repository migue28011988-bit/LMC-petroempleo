/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Inicio;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.pdf.view.save.JRPdfSaveContributor;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.swing.JRViewerController;
import net.sf.jasperreports.swing.JRViewerToolbar;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.save.JRDocxSaveContributor;
import net.sf.jasperreports.view.save.JRHtmlSaveContributor;

/**
 *
 * @author PC
 */
public class Reportes {
    
    private JasperPrint reporte;
    private String nombreAdmin;
    private String nombreReporte;
    
    public void setNombreAdmin(String nombreAdmin){
        this.nombreAdmin= nombreAdmin;
    }
    
    private class modToolbarParaEvento extends JRViewerToolbar{
        
        public modToolbarParaEvento(JRViewerController viewerContext){
            super(viewerContext);
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Controlador.gestionEventos.agregarEvento("Responsable: " + nombreAdmin + "\n" + 
                            "Lugar: Sistema\n" + "El administrador generó y exportó el reporte " + nombreReporte);
                }
            });
        }
        
    }
    
    private class visorReportes extends JRViewer{
        
        public visorReportes(JasperPrint reporte){
            super(reporte);
        }

        @Override
        protected JRViewerToolbar createToolbar() {
            /*crear la barra de herramientas estándar (modificada para registrar 
            un evento en el historial de eventos cueando se exporta un reporte)*/
            modToolbarParaEvento toolbar= new modToolbarParaEvento(viewerContext);
            //Obtener la configuración regional y el paquete de recursos
            Locale locale= viewerContext.getLocale();
            ResourceBundle resBundle= viewerContext.getResourceBundle();
            //crear solo los contribuidores de guardado para pdf, html y docx
            JRSaveContributor pdf= new JRPdfSaveContributor(locale, resBundle);
            JRSaveContributor html= new JRHtmlSaveContributor(locale, resBundle);
            JRSaveContributor docx= new JRDocxSaveContributor(locale, resBundle);
            //establecer los contribuidores deseados en la barra de herramientas
            toolbar.setSaveContributors(new JRSaveContributor[]{pdf, html, docx});
            return toolbar;
        }
    }
    
    public void generar(String nombreReporte){
        this.nombreReporte= nombreReporte;
        try(Connection conexion= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "123")){
            JasperReport compilado= JasperCompileManager.compileReport("Reportes/"+nombreReporte+".jrxml");
            reporte= JasperFillManager.fillReport(compilado, new HashMap<>(), conexion);
            conexion.close();
        }
        catch(JRException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public int viewAndExport(Inicio inicio){
        if(reporte == null) return -1;
        else{
            JFrame view= new JFrame();
            visorReportes visor= new visorReportes(reporte);
            view.getContentPane().add(visor);
            view.setPreferredSize(new Dimension(1024, 768));
            view.pack();
            view.setLocationRelativeTo(null);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setIconImage(new ImageIcon(getClass().getResource("/Iconos/logo.png")).getImage());
            view.setTitle("Vista y Exportación del Reporte");
            view.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    inicio.firePropertyChange("Cerrar visorReportes", 0, 1);
                }
            });
            view.setVisible(true);
            return 1;
        }
    }
    
}
