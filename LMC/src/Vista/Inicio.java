/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.OpcionesConfig;
import Controlador.PlanDeMantenimiento;
import Controlador.Reportes;
import Controlador.ServidorLAN;
import Controlador.gestionEquipo;
import Controlador.gestionEventos;
import Controlador.inicioSesion;
import DAO.hibernateSessionFactory;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;

/**
 *
 * @author PC
 */
public final class Inicio extends javax.swing.JFrame {

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        checkConnection();
        Thread hiloSplash= mostrarSplashScreen();
        try{
            controladorEq= new gestionEquipo();
            controladorMant= new PlanDeMantenimiento();
            controladorEventos= new gestionEventos();
        }catch(Exception e){
            /*esta excepción se lanza cuando el usuario definido en la 
            configuración no es propietario de la BD definida también en
            la configuración y por lo tanto no tiene permisos para manipularla*/
            cerrarSplashScreen(hiloSplash);
            JOptionPane.showMessageDialog(null, """
                                                El usuario no es propietario de esta BD,
                                                por lo tanto no tiene permiso para manipularla de ninguna forma.""",
                    "Error", JOptionPane.ERROR_MESSAGE);
            OpcionesConfig.LocalConfig.setCredenciales(new String[]{"123", "45"});
            System.exit(0);
        }
        server= new ServidorLAN();
        reportesAdmin= new Reportes();
        jTree1.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        jLabel27.setText("BD: " + OpcionesConfig.LocalConfig.getURL().split("/")[3]);
        actualizarListaEquipos("Computadoras");
        actualizarListaEquipos("Moviles");
        actualizarListaEquipos("VOIP");
        actualizarListaEquipos("DispRED");
        actualizarPlanMantenimiento();
        pintarIconosDelArbolDeFunciones();
        pintarValorOnlineDeTabla();
        pintarValorRegistroDeTabla();
        pintarValorGeneradoDeTabla();
        resizeTablaReportes();
        cargarIconosBParaTemasN();
        cerrarSplashScreen(hiloSplash);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        SplashScreen = new javax.swing.JDialog();
        jPanel14 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel8 = new javax.swing.JLabel();
        TipoEquipo = new javax.swing.JDialog();
        jPanel17 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel15 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jPanel16 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        Conexion = new javax.swing.JDialog();
        jPanel56 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel46 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jPanel47 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(32767, 0));
        jSeparator9 = new javax.swing.JSeparator();
        jPanel54 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel51 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel36 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        AcercaDe = new javax.swing.JDialog();
        jPanel59 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel61 = new javax.swing.JPanel();
        jPanel62 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel63 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jPanel64 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel65 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel42 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel43 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel44 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        jPanel58 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jPanel67 = new javax.swing.JPanel();
        jPanel66 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel68 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel27 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel35 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel37 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel38 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();

        SplashScreen.setUndecorated(true);

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.setLayout(new java.awt.GridBagLayout());

        jProgressBar1.setMaximumSize(new java.awt.Dimension(32767, 20));
        jProgressBar1.setPreferredSize(new java.awt.Dimension(600, 20));
        jProgressBar1.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        jPanel14.add(jProgressBar1, gridBagConstraints);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/splash screen/splash 0.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel14.add(jLabel8, gridBagConstraints);

        SplashScreen.getContentPane().add(jPanel14, java.awt.BorderLayout.CENTER);

        TipoEquipo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        TipoEquipo.setUndecorated(true);
        TipoEquipo.getContentPane().setLayout(new javax.swing.BoxLayout(TipoEquipo.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel17.setLayout(new java.awt.BorderLayout(10, 20));
        jPanel17.add(jSeparator1, java.awt.BorderLayout.PAGE_START);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel17.add(jSeparator3, java.awt.BorderLayout.LINE_END);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel17.add(jSeparator4, java.awt.BorderLayout.LINE_START);

        jPanel15.setLayout(new java.awt.GridLayout(4, 0, 0, 10));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Computadora");
        jRadioButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel15.add(jRadioButton1);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Telf. móvil");
        jRadioButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel15.add(jRadioButton2);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Router o Switch");
        jRadioButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel15.add(jRadioButton3);

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setText("Telf. VOIP");
        jRadioButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel15.add(jRadioButton5);

        jPanel17.add(jPanel15, java.awt.BorderLayout.CENTER);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/plus.png"))); // NOI18N
        jButton9.setText("Agregar");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setIconTextGap(10);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton9);

        jPanel17.add(jPanel16, java.awt.BorderLayout.PAGE_END);

        TipoEquipo.getContentPane().add(jPanel17);

        Conexion.setTitle("Establecer Conexión");
        Conexion.setIconImage(new ImageIcon(getClass().getResource("/Iconos/logo.png")).getImage()
        );
        Conexion.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        Conexion.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ConexionWindowClosing(evt);
            }
        });

        jPanel53.setLayout(new javax.swing.BoxLayout(jPanel53, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel32.setText("Conexión");
        jPanel49.add(jLabel32);

        jPanel53.add(jPanel49);

        jPanel45.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jLabel29.setText("Nombre de la BD:");
        jPanel45.add(jLabel29);

        jTextField6.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel45.add(jTextField6);

        jPanel53.add(jPanel45);

        jPanel46.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jLabel30.setText("Dirección IP:");
        jPanel46.add(jLabel30);

        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setText("0.0.0.0");
        jTextField7.setPreferredSize(new java.awt.Dimension(120, 30));
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });
        jPanel46.add(jTextField7);

        jPanel53.add(jPanel46);

        jPanel47.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jLabel31.setText("Puerto:");
        jPanel47.add(jLabel31);

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField1.setPreferredSize(new java.awt.Dimension(64, 30));
        jPanel47.add(jFormattedTextField1);
        jPanel47.add(filler5);

        jPanel53.add(jPanel47);

        jPanel56.add(jPanel53);

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setPreferredSize(new java.awt.Dimension(3, 150));
        jPanel56.add(jSeparator9);

        jPanel54.setLayout(new java.awt.GridBagLayout());

        jLabel33.setText("Autentificación");
        jPanel48.add(jLabel33);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel54.add(jPanel48, gridBagConstraints);

        jPanel55.setLayout(new javax.swing.BoxLayout(jPanel55, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel50.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jLabel34.setText("Usuario:");
        jPanel50.add(jLabel34);

        jTextField8.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel50.add(jTextField8);
        jPanel50.add(filler4);

        jPanel55.add(jPanel50);

        jPanel51.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        jLabel35.setText("Contraseña:");
        jPanel51.add(jLabel35);

        jPanel52.setPreferredSize(new java.awt.Dimension(202, 42));
        jPanel52.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        jPasswordField1.setEchoChar('\u2022');
        jPasswordField1.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel52.add(jPasswordField1);

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/eye.png"))); // NOI18N
        jLabel36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });
        jPanel52.add(jLabel36);

        jPanel51.add(jPanel52);

        jPanel55.add(jPanel51);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 25, 0);
        jPanel54.add(jPanel55, gridBagConstraints);

        jPanel56.add(jPanel54);

        Conexion.getContentPane().add(jPanel56, java.awt.BorderLayout.CENTER);

        jPanel57.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel57.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING, 15, 5));

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ok24x24.png"))); // NOI18N
        jButton20.setText("Conectar");
        jButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel57.add(jButton20);

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancelar24x24.png"))); // NOI18N
        jButton21.setText("Cancelar");
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel57.add(jButton21);

        Conexion.getContentPane().add(jPanel57, java.awt.BorderLayout.PAGE_END);

        AcercaDe.setTitle("Acerca de");
        AcercaDe.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel59.setBackground(new java.awt.Color(255, 255, 255));
        jPanel59.setBorder(javax.swing.BorderFactory.createLineBorder(new javax.swing.JPanel().getBackground(), 5));
        jPanel59.setLayout(new java.awt.BorderLayout());

        jPanel60.setOpaque(false);

        jLabel37.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/UCI.png"))); // NOI18N
        jLabel37.setText("Proyecto universitario");
        jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel37.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel60.add(jLabel37);

        jPanel59.add(jPanel60, java.awt.BorderLayout.PAGE_START);

        jPanel61.setOpaque(false);
        jPanel61.setLayout(new javax.swing.BoxLayout(jPanel61, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel62.setOpaque(false);

        jLabel38.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Desarrollador:");
        jPanel62.add(jLabel38);

        jLabel39.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Miguel Angel Leyva Ramirez");
        jPanel62.add(jLabel39);

        jPanel61.add(jPanel62);

        jPanel63.setOpaque(false);

        jLabel44.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("No. Telf: 55379084");
        jPanel63.add(jLabel44);

        jPanel61.add(jPanel63);

        jPanel64.setOpaque(false);

        jLabel40.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Especialista de petroempleo:");
        jPanel64.add(jLabel40);

        jLabel41.setFont(new java.awt.Font("Monotype Corsiva", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Ing. Diana");
        jPanel64.add(jLabel41);

        jPanel61.add(jPanel64);

        jPanel65.setOpaque(false);

        jLabel43.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/logo_big.png"))); // NOI18N
        jLabel43.setText("Aplicación de escritorio para\n el levantamiento de medios de cómputo\n 2026");
        jLabel43.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel43.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel65.add(jLabel43);

        jPanel61.add(jPanel65);

        jPanel59.add(jPanel61, java.awt.BorderLayout.CENTER);

        AcercaDe.getContentPane().add(jPanel59);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Levantamiento de Medios de Computo v1.0 - Petroempleo");
        setIconImage(new ImageIcon(getClass().getResource("/Iconos/logo.png")).getImage()
        );
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jPanel36.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jPanel41.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
        jPanel41.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/plus.png"))); // NOI18N
        jButton6.setText("Agregar Equipo");
        jButton6.setToolTipText("Registra un nuevo Equipo agregando sus datos de manera Manual");
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/plusNublado.png"))); // NOI18N
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/plusSoleado.png"))); // NOI18N
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton6FocusLost(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel41.add(jButton6);

        jPanel36.add(jPanel41);

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setPreferredSize(new java.awt.Dimension(3, 70));
        jPanel36.add(jSeparator6);

        jPanel42.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
        jPanel42.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/play32x32.png"))); // NOI18N
        jButton4.setText("Iniciar Servidor");
        jButton4.setToolTipText("Inicia un pequeño Servidor que Comienza la Actualización Automática de los datos de las computadoras en la Red Local");
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/playNublado32x32.png"))); // NOI18N
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/playSoleado32x32.png"))); // NOI18N
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton4FocusLost(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel42.add(jButton4);

        jPanel36.add(jPanel42);

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator7.setPreferredSize(new java.awt.Dimension(3, 70));
        jPanel36.add(jSeparator7);

        jPanel43.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
        jPanel43.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mod.png"))); // NOI18N
        jButton3.setText("Configuración");
        jButton3.setToolTipText("Cambiar Configuración del Sistema");
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/modNublado.png"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/modSoleado.png"))); // NOI18N
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton3FocusLost(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel43.add(jButton3);

        jPanel36.add(jPanel43);

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator8.setPreferredSize(new java.awt.Dimension(3, 70));
        jPanel36.add(jSeparator8);

        jPanel44.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
        jPanel44.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar sesión.png"))); // NOI18N
        jButton18.setText("Cerrar Sesión");
        jButton18.setToolTipText("Cierra la sesión y vuelve a autentificar");
        jButton18.setContentAreaFilled(false);
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton18.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar sesión Nublado.png"))); // NOI18N
        jButton18.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar sesión Soleado.png"))); // NOI18N
        jButton18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton18.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton18FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton18FocusLost(evt);
            }
        });
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel44.add(jButton18);

        jPanel36.add(jPanel44);

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator10.setPreferredSize(new java.awt.Dimension(3, 70));
        jPanel36.add(jSeparator10);

        jPanel58.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/acercaDe.png"))); // NOI18N
        jButton22.setText("Acerca de");
        jButton22.setToolTipText("Cierra la sesión y vuelve a autentificar");
        jButton22.setContentAreaFilled(false);
        jButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton22.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/acercaDeNublado.png"))); // NOI18N
        jButton22.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/acercaDeSoleado.png"))); // NOI18N
        jButton22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton22.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton22FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton22FocusLost(evt);
            }
        });
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel58.add(jButton22);

        jPanel36.add(jPanel58);

        jPanel2.add(jPanel36);

        jPanel67.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel67.setMaximumSize(new java.awt.Dimension(20, 100));
        jPanel67.setMinimumSize(new java.awt.Dimension(2, 2));
        jPanel67.setPreferredSize(new java.awt.Dimension(5, 14));
        jPanel2.add(jPanel67);

        jLabel27.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/BD.png"))); // NOI18N
        jLabel27.setIconTextGap(15);
        jPanel66.add(jLabel27);

        jPanel2.add(jPanel66);

        jPanel68.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel68.setMaximumSize(new java.awt.Dimension(20, 100));
        jPanel68.setMinimumSize(new java.awt.Dimension(2, 2));
        jPanel68.setPreferredSize(new java.awt.Dimension(5, 14));
        jPanel2.add(jPanel68);

        jPanel40.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        jLabel42.setFont(new java.awt.Font("Monotype Corsiva", 1, 28)); // NOI18N
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cuenta_g_big.png"))); // NOI18N
        jLabel42.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel42.setIconTextGap(15);
        jPanel40.add(jLabel42);

        jPanel2.add(jPanel40);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jLabel1.setText("Status Bar");
        jPanel3.add(jLabel1);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setPreferredSize(new java.awt.Dimension(15, 16));
        jPanel3.add(jSeparator2);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/loading-red-16x16.gif"))); // NOI18N
        jLabel4.setText("Servidor Parado");
        jLabel4.setIconTextGap(10);
        jPanel3.add(jLabel4);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jSplitPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSplitPane1.setDividerLocation(210);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Funciones");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Inventario");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Computadoras");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Teléfonos móviles");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Routers y Switchs");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Teléfonos VOIP");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("LAN");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Reportes");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Plan de mantenimiento");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Historial de eventos");
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        jPanel1.add(jScrollPane1);

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel4.setLayout(new java.awt.CardLayout());

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/PC.png"))); // NOI18N
        jLabel2.setText("Computadoras");
        jLabel2.setIconTextGap(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel5.add(jLabel2, gridBagConstraints);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/admin.png"))); // NOI18N
        jButton1.setText("Administrar");
        jButton1.setToolTipText("Ver y Modificar las propiedades del equipo");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel5.add(jButton1, gridBagConstraints);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/borrar.png"))); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.setToolTipText("Eliminar el equipo seleccionado de la BD");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel5.add(jButton2, gridBagConstraints);

        jTextField1.setForeground(Color.GRAY);
        jTextField1.setText("Filtrar resultados");
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField1.setName(""); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(150, 30));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel5.add(jTextField1, gridBagConstraints);

        jPanel6.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel9.setLayout(new java.awt.CardLayout());

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pos.", "Online", "Nombre del equipo (RED)", "Tipo", "Estado", "IP", "SO", "Departamento", "Usuario", "CPU", "RAM", "No. Sello"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setRowHeight(25);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowGrid(false);
        jScrollPane2.setViewportView(jTable1);

        jPanel9.add(jScrollPane2, "card3");

        jLabel5.setFont(new java.awt.Font("Monotype Corsiva", 0, 30)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Lista vacia.png"))); // NOI18N
        jLabel5.setText("Lista Vacia");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel9.add(jLabel5, "card2");

        jPanel6.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel6, "card2");

        jPanel18.setLayout(new java.awt.BorderLayout());

        jPanel20.setLayout(new java.awt.GridBagLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/MMCel.png"))); // NOI18N
        jLabel9.setText("Teléfonos móviles");
        jLabel9.setIconTextGap(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel20.add(jLabel9, gridBagConstraints);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/admin.png"))); // NOI18N
        jButton10.setText("Administrar");
        jButton10.setToolTipText("Ver y Modificar las propiedades del equipo");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel20.add(jButton10, gridBagConstraints);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/borrar.png"))); // NOI18N
        jButton11.setText("Eliminar");
        jButton11.setToolTipText("Eliminar el equipo seleccionado de la BD");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel20.add(jButton11, gridBagConstraints);

        jTextField2.setForeground(Color.GRAY);
        jTextField2.setText("Filtrar resultados");
        jTextField2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField2.setName(""); // NOI18N
        jTextField2.setPreferredSize(new java.awt.Dimension(150, 30));
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel20.add(jTextField2, gridBagConstraints);

        jPanel18.add(jPanel20, java.awt.BorderLayout.PAGE_START);

        jPanel19.setLayout(new java.awt.CardLayout());

        jTable4.setAutoCreateRowSorter(true);
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pos.", "Número Telf.", "email", "Usuario", "PIN", "Marca"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setRowHeight(25);
        jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(jTable4);

        jPanel19.add(jScrollPane4, "card3");

        jLabel10.setFont(new java.awt.Font("Monotype Corsiva", 0, 30)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Lista vacia.png"))); // NOI18N
        jLabel10.setText("Lista Vacia");
        jLabel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel19.add(jLabel10, "card2");

        jPanel18.add(jPanel19, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel18, "card5");

        jPanel21.setLayout(new java.awt.BorderLayout());

        jPanel22.setLayout(new java.awt.GridBagLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/telf VOIP.png"))); // NOI18N
        jLabel11.setText("Teléfonos VOIP");
        jLabel11.setIconTextGap(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel22.add(jLabel11, gridBagConstraints);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/admin.png"))); // NOI18N
        jButton12.setText("Administrar");
        jButton12.setToolTipText("Ver y Modificar las propiedades del equipo");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel22.add(jButton12, gridBagConstraints);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/borrar.png"))); // NOI18N
        jButton13.setText("Eliminar");
        jButton13.setToolTipText("Eliminar el equipo seleccionado de la BD");
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel22.add(jButton13, gridBagConstraints);

        jTextField3.setForeground(Color.GRAY);
        jTextField3.setText("Filtrar resultados");
        jTextField3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField3.setName(""); // NOI18N
        jTextField3.setPreferredSize(new java.awt.Dimension(150, 30));
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel22.add(jTextField3, gridBagConstraints);

        jPanel21.add(jPanel22, java.awt.BorderLayout.PAGE_START);

        jPanel23.setLayout(new java.awt.CardLayout());

        jTable5.setAutoCreateRowSorter(true);
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pos.", "Código", "IP", "Usuario", "Departamento", "No. Inventario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.setRowHeight(25);
        jTable5.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane6.setViewportView(jTable5);

        jPanel23.add(jScrollPane6, "card3");

        jLabel12.setFont(new java.awt.Font("Monotype Corsiva", 0, 30)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Lista vacia.png"))); // NOI18N
        jLabel12.setText("Lista Vacia");
        jLabel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel23.add(jLabel12, "card2");

        jPanel21.add(jPanel23, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel21, "card6");

        jPanel24.setLayout(new java.awt.BorderLayout());

        jPanel25.setLayout(new java.awt.GridBagLayout());

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/router.png"))); // NOI18N
        jLabel13.setText("Dispositivos de RED");
        jLabel13.setIconTextGap(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel25.add(jLabel13, gridBagConstraints);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/admin.png"))); // NOI18N
        jButton14.setText("Administrar");
        jButton14.setToolTipText("Ver y Modificar las propiedades del equipo");
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel25.add(jButton14, gridBagConstraints);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/borrar.png"))); // NOI18N
        jButton15.setText("Eliminar");
        jButton15.setToolTipText("Eliminar el equipo seleccionado de la BD");
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel25.add(jButton15, gridBagConstraints);

        jTextField4.setForeground(Color.GRAY);
        jTextField4.setText("Filtrar resultados");
        jTextField4.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField4.setName(""); // NOI18N
        jTextField4.setPreferredSize(new java.awt.Dimension(150, 30));
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel25.add(jTextField4, gridBagConstraints);

        jPanel24.add(jPanel25, java.awt.BorderLayout.PAGE_START);

        jPanel26.setLayout(new java.awt.CardLayout());

        jTable6.setAutoCreateRowSorter(true);
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pos.", "IP", "Tipo", "Marca", "Modelo", "No. Serie"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable6.setRowHeight(25);
        jTable6.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane7.setViewportView(jTable6);

        jPanel26.add(jScrollPane7, "card3");

        jLabel14.setFont(new java.awt.Font("Monotype Corsiva", 0, 30)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Lista vacia.png"))); // NOI18N
        jLabel14.setText("Lista Vacia");
        jLabel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel26.add(jLabel14, "card2");

        jPanel24.add(jPanel26, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel24, "card7");

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/lan.png"))); // NOI18N
        jLabel3.setText("Red LAN");
        jLabel3.setIconTextGap(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel8.add(jLabel3, gridBagConstraints);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/BD32x32.png"))); // NOI18N
        jButton5.setText("Registrar");
        jButton5.setToolTipText("Guardar los datos de todos los Equipos No registrados en la BD");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel8.add(jButton5, gridBagConstraints);

        jPanel7.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel10.setLayout(new java.awt.CardLayout());

        jLabel6.setFont(new java.awt.Font("Monotype Corsiva", 0, 30)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Lista vacia.png"))); // NOI18N
        jLabel6.setText("Lista Vacía");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel10.add(jLabel6, "card3");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pos.", "Nombre del Host", "Ip del Host", "Registro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(35);
        jTable2.setRowSelectionAllowed(false);
        jScrollPane3.setViewportView(jTable2);

        jPanel10.add(jScrollPane3, "card2");

        jPanel7.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel7, "card3");

        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Reportes.png"))); // NOI18N
        jLabel7.setText("Reportes");
        jLabel7.setIconTextGap(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel12.add(jLabel7, gridBagConstraints);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/generar32x32.png"))); // NOI18N
        jButton7.setText("Generar");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel12.add(jButton7, gridBagConstraints);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/verYExportar.png"))); // NOI18N
        jButton8.setText("Ver y Exportar");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel12.add(jButton8, gridBagConstraints);

        jPanel11.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.LINE_AXIS));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Resumen_compu", "Una tabla con un resumen de los datos de todas las computadoras", ""},
                {"Fichas_técnicas_compu", "Fichas técnicas de todos las computadoras con todos sus datos", ""},
                {"Fichas_técnicas_dispRED", "Fichas técnicas de todos los switchs y routers con todos sus datos", ""},
                {"Fichas_técnicas_móviles", "Fichas técnicas de todos los teléfonos móviles con todos sus datos", ""},
                {"Fichas_técnicas_voip", "Fichas técnicas de todos los teléfonos VOIP con todos sus datos", ""},
                {"Cant_computadorasXdepartamento", "Gráfico de torta que muestra la cantidad de computadoras por departamento", ""},
                {"Plan_mantenimiento", "Tabla que muestra el Plan de Mantenimiento de los Equipos", ""},
                {"Registro_cambios", "Tabla que muestra el registro de cambios de las computadoras", ""},
                {"Registro_incidencias", "Tabla que muestra el registro de incidencias de las computadoras", ""},
                {"Registro_logins", "Tabla que muestra el registro de inicio de sesiones de las computadoras", ""},
                {"Software_intalado", "Tabla que muestra las aplicaciones instaladas en las computadoras", ""},
                {"Cant_equiposXAño", "Gráfico de barras que muestra la cantidad de equipos existentes en los últimos 5 años por tipo y total", ""}
            },
            new String [] {
                "Nombre", "Descripción", "Generado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable3.setRowHeight(25);
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jTable3);

        jPanel13.add(jScrollPane5);

        jPanel11.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel11, "card4");

        jPanel27.setLayout(new java.awt.BorderLayout());

        jPanel29.setLayout(new java.awt.GridBagLayout());

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mantenimiento.png"))); // NOI18N
        jLabel15.setText("Plan de mantenimiento");
        jLabel15.setIconTextGap(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel29.add(jLabel15, gridBagConstraints);

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actualización.png"))); // NOI18N
        jButton17.setText("Actualizar");
        jButton17.setToolTipText("Actualizar el Plan de mantenimiento en la BD o Crear uno si no existe");
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel29.add(jButton17, gridBagConstraints);

        jTextField5.setForeground(Color.GRAY);
        jTextField5.setText("Filtrar resultados");
        jTextField5.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField5.setName(""); // NOI18N
        jTextField5.setPreferredSize(new java.awt.Dimension(150, 30));
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel29.add(jTextField5, gridBagConstraints);

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/limpiar.png"))); // NOI18N
        jButton16.setText("Limpiar");
        jButton16.setToolTipText("Eliminar el Plan realizado");
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel29.add(jButton16, gridBagConstraints);

        jPanel27.add(jPanel29, java.awt.BorderLayout.PAGE_START);

        jPanel28.setLayout(new java.awt.CardLayout());

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Equipo", "No. Inventario", "Área", "Mant. I General", "Mant. I Parcial", "Fecha I Sem. Plan", "Fecha I Sem. Real", "Mant. II General", "Mant. II Parcial", "Fecha II Sem. Plan", "Fecha II Sem. Real"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable7.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable7.setRowHeight(25);
        jTable7.setRowSelectionAllowed(false);
        jTable7.getTableHeader().setReorderingAllowed(false);
        jTable7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTable7MouseMoved(evt);
            }
        });
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable7MousePressed(evt);
            }
        });
        jScrollPane8.setViewportView(jTable7);

        jPanel28.add(jScrollPane8, "card3");

        jLabel16.setFont(new java.awt.Font("Monotype Corsiva", 0, 30)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Lista vacia.png"))); // NOI18N
        jLabel16.setText("Lista Vacia");
        jLabel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel28.add(jLabel16, "card2");

        jPanel27.add(jPanel28, java.awt.BorderLayout.CENTER);

        jPanel30.setLayout(new javax.swing.BoxLayout(jPanel30, javax.swing.BoxLayout.LINE_AXIS));

        jPanel31.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 25, 10));

        jLabel17.setText("Índice de cumplimiento:");
        jPanel31.add(jLabel17);

        jLabel18.setText("Bien = 100 % = 5");
        jPanel31.add(jLabel18);

        jLabel19.setText("Regular ≤ 99 y hasta 81 % = 3");
        jPanel31.add(jLabel19);

        jLabel20.setText("Mal ≤ 80 % = 0");
        jPanel31.add(jLabel20);

        jPanel30.add(jPanel31);

        jPanel32.setBackground(new java.awt.Color(102, 102, 102));
        jPanel32.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel32.setToolTipText("No disponible hasta que se realice el mantenimiento de todo el año");
        jPanel32.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 10));

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Índice:");
        jPanel32.add(jLabel21);

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("No disponible");
        jPanel32.add(jLabel22);

        jPanel30.add(jPanel32);

        jPanel27.add(jPanel30, java.awt.BorderLayout.PAGE_END);

        jPanel4.add(jPanel27, "card8");

        jPanel33.setLayout(new java.awt.BorderLayout());

        jPanel34.setLayout(new java.awt.GridBagLayout());

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/eventos.png"))); // NOI18N
        jLabel23.setText("Eventos");
        jLabel23.setIconTextGap(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel34.add(jLabel23, gridBagConstraints);

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/limpiar.png"))); // NOI18N
        jButton19.setText("Limpiar");
        jButton19.setToolTipText("Eliminar Todo el Historial de Eventos");
        jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel34.add(jButton19, gridBagConstraints);

        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.setPreferredSize(new java.awt.Dimension(250, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel34.add(jComboBox1, gridBagConstraints);

        jPanel33.add(jPanel34, java.awt.BorderLayout.PAGE_START);

        jPanel35.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel35.setLayout(new java.awt.CardLayout());

        jScrollPane9.setBorder(null);

        jPanel37.setPreferredSize(new java.awt.Dimension(800, 10));
        jPanel37.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/fecha.png"))); // NOI18N
        jLabel28.setText("Domingo, 28 de Enero de 2026");
        jLabel28.setIconTextGap(15);
        jLabel28.setPreferredSize(new java.awt.Dimension(600, 36));
        jPanel37.add(jLabel28);

        jPanel39.setOpaque(false);
        jPanel39.setPreferredSize(new java.awt.Dimension(600, 130));
        jPanel39.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 10, 0));

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/hora.png"))); // NOI18N
        jLabel24.setText("23:58:58");
        jLabel24.setIconTextGap(10);
        jPanel39.add(jLabel24);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setPreferredSize(new java.awt.Dimension(3, 130));
        jPanel39.add(jSeparator5);

        jPanel38.setOpaque(false);
        jPanel38.setPreferredSize(new java.awt.Dimension(465, 115));
        jPanel38.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING, 0, 5));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar.png"))); // NOI18N
        jLabel25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel38.add(jLabel25);

        jScrollPane10.setBorder(null);
        jScrollPane10.setPreferredSize(new java.awt.Dimension(465, 70));
        jScrollPane10.setViewportView(null);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setLineWrap(true);
        jTextArea1.setText("casa\noijojo\n\noiuhiohh");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane10.setViewportView(jTextArea1);

        jPanel38.add(jScrollPane10);

        jPanel39.add(jPanel38);

        jPanel37.add(jPanel39);

        jScrollPane9.setViewportView(jPanel37);

        jPanel35.add(jScrollPane9, "card2");

        jLabel26.setFont(new java.awt.Font("Monotype Corsiva", 0, 30)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Lista vacia.png"))); // NOI18N
        jLabel26.setText("Historial vacio");
        jPanel35.add(jLabel26, "card3");

        jPanel33.add(jPanel35, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel33, "card9");

        jSplitPane1.setRightComponent(jPanel4);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    /*verifica si la conexión a la base de datos con la configuración guardada
    en el archivo local "config.cfg" es posible, sino muestra el mensaje de error
    y pide una URL y usuario válidos para establecer la conexión*/
    private void checkConnection(){
        while(true){
            String urlBD= OpcionesConfig.LocalConfig.getURL();
            String usuarioBD= OpcionesConfig.LocalConfig.getCredenciales()[0];
            String passwordBD= OpcionesConfig.LocalConfig.getCredenciales()[1];
            Connection conn;
            try{
                conn= DriverManager.getConnection(urlBD, usuarioBD, passwordBD);
                conn.close();
                break;
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                Conexion.pack();
                Conexion.setLocationRelativeTo(this);
                Conexion.setVisible(true);
            }
        }
    }
    
    //muestra el splash screen antes de cualquier otra ventana
    private Thread mostrarSplashScreen(){
        SplashScreen.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        SplashScreen.pack();
        SwingUtilities.invokeLater(()->{
            SplashScreen.setLocationRelativeTo(null);
            SplashScreen.setVisible(true);
        });
        Thread hiloSplash= new Thread(()->{
            //muestra el progreso y cambia la imagen de fondo según progreso
            try{
                for(int i= 0, valor= 0; i < 20; i++){
                    Thread.sleep(1000);
                    jProgressBar1.setValue(valor+= 5);
                    jProgressBar1.setString(valor + " %");
                    if(i == 7)
                        jLabel8.setIcon(new ImageIcon(getClass().getResource("/splash screen/splash 1.jpg")));
                    else if(i== 14)
                        jLabel8.setIcon(new ImageIcon(getClass().getResource("/splash screen/splash 2.jpg")));
                    if(Thread.currentThread().isInterrupted()) break;
                }
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });
        hiloSplash.start();
        return hiloSplash;
    }
    
    //cierra el splash screen al final del constructor y antes de mostrar la ventana principal
    private void cerrarSplashScreen(Thread hiloSplash){
        hiloSplash.interrupt();
        jProgressBar1.setValue(100);
        jProgressBar1.setString("100 %");
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        SplashScreen.dispose();
    }
    
    //autentificación de administradores
    public void autentificar(){
        Autentificacion Auth= new Autentificacion(this, true);
        Auth.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                superAdmin= Auth.getSuperAdmin();
                nombreAdmin= Auth.getNombreAdmin();
                reportesAdmin.setNombreAdmin(nombreAdmin);
                if(!superAdmin){
                    jButton3.setEnabled(false);
                    jButton3.setToolTipText("Solo los Superadministradores pueden acceder a la configuración de la app");
                    jButton19.setEnabled(false);
                    jButton19.setToolTipText("Solo los Superadministradores pueden borrar el historial de eventos");
                }
                else{
                    jButton3.setEnabled(true);
                    jButton3.setToolTipText("Cambiar Configuración del Sistema");
                    jButton19.setEnabled(true);
                    jButton19.setToolTipText("Eliminar Todo el Historial de Eventos");
                }
                /*establece el nombre de la cuenta y el icono con distintos colores 
                (oro si es superadministrador o verde si es administrador) en la ventana principal*/
                establecerIconoYNombreCuenta();
                /*se puso aqui este método y no en el constructor porque si el admin no es superadmin
                no puede eliminar los eventos individualmente en el historial de eventos*/
                actualizarHistorialDeEventos();
            }
        });
        Auth.setVisible(true);
    }
    
    /*resetea la GUI (vuelve al estado que estaba la ventana cuando se muestra por
    primera vez) y vuelve a la ventana de autentificación*/
    private void cerrarSesion(){
        //elimina nombre de la cuenta
        superAdmin= false;
        nombreAdmin= null;
        //<editor-fold defaultstate="collapsed" desc="resetea la GUI">
        /*cambia icono y nombre de la cuenta a su valor por defecto y elimina
        cualquier mouseListener*/
        jLabel42.setText("");
        jLabel42.setIcon(new ImageIcon(getClass().getResource("/Iconos/cuenta_g_big.png")));
        jLabel42.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jLabel42.setToolTipText(null);
        for(MouseListener L : jLabel42.getMouseListeners())
            if(L instanceof MouseAdapter)
                jLabel42.removeMouseListener(L);
        /*actualiza las tablas de los equipos y mantenimiento
        y limpia la búsqueda de cada una*/
        actualizarListaEquipos("Computadoras");
        jTextField1.setText("Filtrar resultados");
        jTextField1.setForeground(Color.GRAY);
        actualizarListaEquipos("Moviles");
        jTextField2.setText("Filtrar resultados");
        jTextField2.setForeground(Color.GRAY);
        actualizarListaEquipos("VOIP");
        jTextField3.setText("Filtrar resultados");
        jTextField3.setForeground(Color.GRAY);
        actualizarListaEquipos("DispRED");
        jTextField4.setText("Filtrar resultados");
        jTextField4.setForeground(Color.GRAY);
        actualizarPlanMantenimiento();
        jTextField5.setText("Filtrar resultados");
        jTextField5.setForeground(Color.GRAY);
        //muestra la lista de las computadoras y contrae los nodos
        javax.swing.tree.TreeNode rootNode= (javax.swing.tree.TreeNode)jTree1.getModel().getRoot();
        javax.swing.tree.TreeNode invNode= rootNode.getChildAt(0);
        javax.swing.tree.TreeNode compuNode= invNode.getChildAt(0);
        jTree1.setSelectionPath(new TreePath(((DefaultTreeModel)jTree1.getModel()).getPathToRoot(compuNode)));
        jTree1.setSelectionPath(new TreePath(((DefaultTreeModel)jTree1.getModel()).getPathToRoot(invNode)));
        jTree1.collapsePath(jTree1.getSelectionPath());
        //limpia el icono de generado en la tabla de reportes
        for(int i= 0; i < jTable3.getRowCount(); i++)
            jTable3.setValueAt("", i, 2);
        //para el servidor si esta corriendo
        if(server.estaIniciado()){
            bloqueoThreadPrincipal();
            Block= true;
            server.stop();
            jButton4.setIcon(new ImageIcon(getClass().getResource("/Iconos/play32x32.png")));
            jButton4.setPressedIcon(new ImageIcon(getClass().getResource("/Iconos/playNublado32x32.png")));
            jButton4.setRolloverIcon(new ImageIcon(getClass().getResource("/Iconos/playSoleado32x32.png")));
            jButton4.setText("Iniciar Servidor");
            ((DefaultTableModel)jTable2.getModel()).setRowCount(0);
            hilo.interrupt();
            Block= false;
        }
        CardLayout cardlayout= (CardLayout)jPanel10.getLayout();
        cardlayout.show(jPanel10, "card3");
        //el botón "Agregar Equipo" toma el foco
        jButton6.requestFocusInWindow();
        //</editor-fold>
        //vuelve a autentificar
        autentificar();
    }
    
    //para que aparezca el placeholder "Filtrar resultados" cuando
    //se da click fuera del textfield de búsqueda del registro de todas las computadoras
    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if(jTextField1.getText().equals("")){
            jTextField1.setText("Filtrar resultados");
            jTextField1.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jTextField1FocusLost

    //para que desaparezca el placeholder "Filtrar resultados" cuando
    //se da click en el textfield de búsqueda del registro de todos las computadoras
    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        if(jTextField1.getForeground()== Color.GRAY){
            jTextField1.setText("");
            jTextField1.setForeground(new JTextField().getForeground());
        }
    }//GEN-LAST:event_jTextField1FocusGained

    //acción del botón "Agregar Equipo"
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        bloqueoThreadPrincipal();
        Block= true;
        
        //<editor-fold defaultstate="collapsed" desc="Se escoge entre computadoras, móviles, telf voip, router y switch">
        TipoEquipo.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        TipoEquipo.pack();
        TipoEquipo.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        java.awt.Point p= jButton6.getLocationOnScreen();
        TipoEquipo.setLocation(p.x + 10, p.y + 45);
        TipoEquipo.setVisible(true);
        
        if(equipoType.equals("Computadora")){
            nuevaComputadora registro= new nuevaComputadora(this, true);
            registro.setLocationRelativeTo(this);
            registro.setVisible(true);
            actualizarListaEquipos("Computadoras");
        }
        else if(equipoType.equals("Telf. móvil")){
            NuevoTelfMovil registro= new NuevoTelfMovil(this, true);
            registro.setLocationRelativeTo(this);
            registro.setVisible(true);
            actualizarListaEquipos("Moviles");
        }
        else if(equipoType.equals("Telf. VOIP")){
            NuevoTelfVOIP registro= new NuevoTelfVOIP(this, true);
            registro.setLocationRelativeTo(this);
            registro.setVisible(true);
            actualizarListaEquipos("VOIP");
        }
        else if(equipoType.equals("Router o Switch")){
            NuevoDispRED registro= new NuevoDispRED(this, true);
            registro.setLocationRelativeTo(this);
            registro.setVisible(true);
            actualizarListaEquipos("DispRED");
        }
        //</editor-fold>
        
        actualizarHistorialDeEventos();
        Block= false;
        synchronized (controladorHilos) {
            controladorHilos.notify();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    //acción del botón "eliminar" de la lista de computadoras registradas
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        bloqueoThreadPrincipal();
        Block= true;
        int fila= jTable1.getSelectedRow();
        if(fila == -1)
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un Equipo");
        else{
            fila= jTable1.getRowSorter().convertRowIndexToModel(fila);
            int opcion= JOptionPane.showConfirmDialog(this, "¿Esta seguro/a de querer eliminar este equipo"+
                    " de la BD? \n Esta operación no puede ser revertida", "Advertencia", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(opcion == 0){
                Object IP= jTable1.getModel().getValueAt(fila, 5);
                Object nombre= jTable1.getModel().getValueAt(fila, 2);
                String mensaje= controladorEq.borrarComputadora((String)IP);
                JOptionPane.showMessageDialog(this, mensaje, "Eliminación", JOptionPane.INFORMATION_MESSAGE);
                actualizarListaEquipos("Computadoras");
                if(server.estaIniciado()) mostrarRedLAN();
                if(mensaje.equals("Equipo eliminado")){
                    gestionEventos.agregarEvento("Responsable: " + nombreAdmin + "\n" + 
                            "Lugar: Sistema\n" + "El administrador eliminó la computadora " + 
                            "con el nombre " + (String)nombre);
                    actualizarHistorialDeEventos();
                }
            }
        }
        Block= false;
        synchronized (controladorHilos) {
            controladorHilos.notify();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    //acción del botón "administrar" de la lista de computadoras registradas
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int fila= jTable1.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un Equipo");
            return;
        }
        fila= jTable1.getRowSorter().convertRowIndexToModel(fila);
        bloqueoThreadPrincipal();
        Block= true;
        Object ip= jTable1.getModel().getValueAt(fila, 5);
        AdminComputadora admin= new AdminComputadora(this, true, (String)ip, server);
        admin.setLocationRelativeTo(this);
        admin.setVisible(true);
        actualizarListaEquipos("Computadoras");
        actualizarHistorialDeEventos();
        if(server.estaIniciado()) mostrarRedLAN();
        Block= false;
        synchronized (controladorHilos) {
            controladorHilos.notify();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    //accion cuando se toca "Enter" en la búsqueda de las computadoras
    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            busqueda= jTextField1.getText();
            actualizarListaEquipos("Computadoras");
            if(server.estaIniciado()) mostrarRedLAN();
            busqueda= null;
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    //acción del botón "Iniciar Servidor"
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(server.estaIniciado()){
            bloqueoThreadPrincipal();
            Block= true;
            server.stop();
            jButton4.setIcon(new ImageIcon(getClass().getResource("/Iconos/play32x32.png")));
            jButton4.setPressedIcon(new ImageIcon(getClass().getResource("/Iconos/playNublado32x32.png")));
            jButton4.setRolloverIcon(new ImageIcon(getClass().getResource("/Iconos/playSoleado32x32.png")));
            jButton4.setText("Iniciar Servidor");
            jButton4.setToolTipText("Inicia un pequeño Servidor que Comienza la "+
                    "Actualización Automática de los datos de las computadoras en la Red Local");
            ((DefaultTableModel)jTable2.getModel()).setRowCount(0);
            hilo.interrupt();
            Block= false;
        }
        else{
            try{
                server.configurarSSL("servidor.keystore", "Seraphim#1");
                server.start(8000);
            }catch(Exception e){
                String msg= e.getMessage();
                if(msg.contains("El sistema no puede encontrar el archivo especificado"))
                    msg+= "\n\nVuelva a generar un nuevo keystore en Configuración -> Servidor";
                JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            jButton4.setIcon(new ImageIcon(getClass().getResource("/Iconos/stop32x32.png")));
            jButton4.setPressedIcon(new ImageIcon(getClass().getResource("/Iconos/stopNublado32x32.png")));
            jButton4.setRolloverIcon(new ImageIcon(getClass().getResource("/Iconos/stopSoleado32x32.png")));
            jButton4.setText("Parar Servidor");
            jButton4.setToolTipText("Detiene el Servidor y la Actualización Automática");
            jLabel4.setText("Servidor Corriendo");
            jLabel4.setIcon(new ImageIcon(getClass().getResource("/Iconos/loading-green-16x16.gif")));
            Loading loading= new Loading(this, Dialog.ModalityType.APPLICATION_MODAL);
            loading.execMetodo(this::mostrarRedLAN);
            javax.swing.tree.TreeNode lanNode= ((javax.swing.tree.TreeNode)jTree1.getModel().getRoot()).
                getChildAt(1);
            jTree1.setSelectionPath(new TreePath(lanNode)); //con esto se selecciona la pantalla de LAN
            crearThreadActualizacion(); //actualiza todos las computadoras registradas en la BD de manera automática
            hilo.start();
        }
        CardLayout cardlayout= (CardLayout)jPanel10.getLayout();
        if(jTable2.getRowCount() == 0) cardlayout.show(jPanel10, "card3");
        else cardlayout.show(jPanel10, "card2");
    }//GEN-LAST:event_jButton4ActionPerformed

    //cuando se da click en el árbol para cambiar las distintas pantallas
    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        CardLayout cardLayout= (CardLayout)jPanel4.getLayout();
        TreePath path= jTree1.getSelectionPath();
        if(path == null) return;
        String nodo= path.getLastPathComponent().toString();
        if(nodo.contains("Computadoras"))
            cardLayout.show(jPanel4, "card2");
        else if(nodo.equals("LAN"))
            cardLayout.show(jPanel4, "card3");
        else if(nodo.equals("Reportes"))
            cardLayout.show(jPanel4, "card4");
        else if(nodo.equals("Teléfonos móviles"))
            cardLayout.show(jPanel4, "card5");
        else if(nodo.equals("Teléfonos VOIP"))
            cardLayout.show(jPanel4, "card6");
        else if(nodo.equals("Routers y Switchs"))
            cardLayout.show(jPanel4, "card7");
        else if(nodo.equals("Plan de mantenimiento"))
            cardLayout.show(jPanel4, "card8");
        else if(nodo.equals("Historial de eventos"))
            cardLayout.show(jPanel4, "card9");
    }//GEN-LAST:event_jTree1ValueChanged

    //acción del botón registrar del listado de la Red Local
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        bloqueoThreadPrincipal();
        Block= true;
        if(!server.estaIniciado())
            JOptionPane.showMessageDialog(this, "Debe iniciar el Servidor LAN para escanear equipos en la red", 
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        else{
            JOptionPane.showMessageDialog(this, "Se registrarán en la BD solo las computadoras que no están registradas", 
                    "Registro", JOptionPane.INFORMATION_MESSAGE);
            Loading loading= new Loading(this, Dialog.ModalityType.APPLICATION_MODAL);
            loading.execMetodo(()->{
                server.registrarTodos(nombreAdmin);
                actualizarListaEquipos("Computadoras");
                actualizarHistorialDeEventos();
                mostrarRedLAN();
            });
        }
        Block= false;
        synchronized (controladorHilos) {
           controladorHilos.notify();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    //generar el reporte: solo se puede generar un reporte a la vez, si se quiere ver y exportar un reporte hay que generarlo primero
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int reporteSeleccionado= jTable3.getSelectedRow();
        if(reporteSeleccionado == -1){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Reporte", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        DefaultTableModel tabla= (DefaultTableModel)jTable3.getModel();
        String nombreReporte= tabla.getValueAt(reporteSeleccionado, 0).toString();
        Loading loading= new Loading(this, Dialog.ModalityType.APPLICATION_MODAL);
        loading.execMetodo(()->{
           reportesAdmin.generar(nombreReporte); 
        });
        //asigna "generado" a la columna Generado de la fila del reporte seleccionado, donde se mostrará un icono
        for(int i= 0; i < jTable3.getRowCount(); i++){
            if(i == reporteSeleccionado){
                tabla.setValueAt("generado", i, 2);
                continue;
            }
            tabla.setValueAt("", i, 2);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    //para visualizar y exportar los reportes
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Inicio inicio= this;
        addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                actualizarHistorialDeEventos();
                inicio.removePropertyChangeListener(this);
            }
        });
        int estado= reportesAdmin.viewAndExport(inicio);
        if(estado == -1)
          JOptionPane.showMessageDialog(this, "Debe generar un Reporte primero",
                  "Información", JOptionPane.INFORMATION_MESSAGE);
        else if(estado == 0)
            JOptionPane.showMessageDialog(this, "La Base de Datos está vacia",
                  "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButton8ActionPerformed

    //acción del botón "Configuración" para acceder a la configuración de la app
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Configuracion config= new Configuracion(this, true);
        config.setLocationRelativeTo(this);
        config.setVisible(true);
        actualizarHistorialDeEventos();
        /*si se escoge un nuevo tema en "Configuración" se finaliza esta ventana, 
        se establece un nuevo LookAndFeel y se crea una nueva ventana principal
        con el nuevo LookAndFeel aplicadoÑ; de igual manera si se establece 
        una nueva URL y/o usuario para la conexión a una BD cuando se cierra
        "Configuración", se crea una nueva ventana principal*/
        if(config.getCambioTema() || config.getCambioURL() || config.getCambioROL() || config.isReset()){
            dispose();
            hibernateSessionFactory.reOpen();
            new Thread(()->{
                setLaf(OpcionesConfig.LocalConfig.getTema());
                Inicio app= new Inicio();
                app.setSuperAdminDefaults(nombreAdmin);
                SwingUtilities.invokeLater(()->{
                    app.setLocationRelativeTo(null);
                    app.setVisible(true);
                });
            }).start();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    //acción del botón "Agregar" en la selección del nuevo tipo de equipo a agregar
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Enumeration<AbstractButton> radioButtons= buttonGroup1.getElements();
        while(radioButtons.hasMoreElements()){
            AbstractButton radioButton= radioButtons.nextElement();
            if(radioButton.isSelected()){
                equipoType= radioButton.getText();
                break;
            }
        }
        TipoEquipo.setVisible(false);
    }//GEN-LAST:event_jButton9ActionPerformed

    //para que desaparezca el placeholder "Filtrar resultados" cuando
    //se da click en el textfield de búsqueda del registro de todos los móviles
    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained
        if(jTextField2.getForeground()== Color.GRAY){
            jTextField2.setText("");
            jTextField2.setForeground(new JTextField().getForeground());
        }
    }//GEN-LAST:event_jTextField2FocusGained

    //para que aparezca el placeholder "Filtrar resultados" cuando
    //se da click fuera del textfield de búsqueda del registro de todos los móviles
    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        if(jTextField2.getText().equals("")){
            jTextField2.setText("Filtrar resultados");
            jTextField2.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jTextField2FocusLost

    //accion cuando se toca "Enter" en la búsqueda de los telf móviles
    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            busqueda= jTextField2.getText();
            actualizarListaEquipos("Moviles");
            busqueda= null;
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    //acción del botón "eliminar" en la lista de móviles
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int fila= jTable4.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún teléfono", "Advertencia", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int opcion= JOptionPane.showConfirmDialog(this, "¿Esta seguro/a de querer eliminar este teléfono"+
                " de la BD? \n Esta operación no puede ser revertida", "Advertencia", JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
        if(opcion == 0){
            fila= jTable4.getRowSorter().convertRowIndexToModel(fila);
            DefaultTableModel tabla= (DefaultTableModel)jTable4.getModel();
            String numTelf= tabla.getValueAt(fila, 1).toString();
            String estado= controladorEq.borrarMovil(numTelf);
            if(estado.equals("Equipo eliminado de la BD")){
                JOptionPane.showMessageDialog(this, estado, "Información", JOptionPane.INFORMATION_MESSAGE);
                gestionEventos.agregarEvento("Responsable: " + nombreAdmin + "\n" + 
                        "Lugar: Sistema\n" + "El administrador eliminó el teléfono móvil " + 
                        "con el número " + numTelf);
                actualizarHistorialDeEventos();
            }
            actualizarListaEquipos("Moviles");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    //acción del botón "Administrar" de la lista de móviles
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int fila= jTable4.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un Teléfono móvil");
            return;
        }
        fila= jTable4.getRowSorter().convertRowIndexToModel(fila);
        String numTelf= jTable4.getModel().getValueAt(fila, 1).toString();
        NuevoTelfMovil mod= new NuevoTelfMovil(this, true, numTelf);
        mod.setLocationRelativeTo(this);
        mod.setVisible(true);
        actualizarListaEquipos("Moviles");
        actualizarHistorialDeEventos();
    }//GEN-LAST:event_jButton10ActionPerformed

    //acción del botón "eliminar" en la lista de teléfonos VOIP
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        int fila= jTable5.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún teléfono VOIP", "Advertencia", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int opcion= JOptionPane.showConfirmDialog(this, "¿Esta seguro/a de querer eliminar este teléfono VOIP"+
                " de la BD? \n Esta operación no puede ser revertida", "Advertencia", JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
        if(opcion == 0){
            fila= jTable5.getRowSorter().convertRowIndexToModel(fila);
            DefaultTableModel tabla= (DefaultTableModel)jTable5.getModel();
            Integer codigo= (int)tabla.getValueAt(fila, 1);
            String estado= controladorEq.borrarVOIP(codigo);
            if(estado.equals("Equipo eliminado de la BD")){
                JOptionPane.showMessageDialog(this, estado, "Información", JOptionPane.INFORMATION_MESSAGE);
                gestionEventos.agregarEvento("Responsable: " + nombreAdmin + "\n" + 
                        "Lugar: Sistema\n" + "El administrador eliminó el teléfono VOIP " + 
                        "con el código " + codigo);
                actualizarHistorialDeEventos();
            }
            actualizarListaEquipos("VOIP");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    //acción del botón "Administrar" de la lista de teléfonos VOIP
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        int fila= jTable5.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un Teléfono VOIP");
            return;
        }
        fila= jTable5.getRowSorter().convertRowIndexToModel(fila);
        Integer codigo= (int)jTable5.getModel().getValueAt(fila, 1);
        NuevoTelfVOIP mod= new NuevoTelfVOIP(this, true, codigo);
        mod.setLocationRelativeTo(this);
        mod.setVisible(true);
        actualizarListaEquipos("VOIP");
        actualizarHistorialDeEventos();
    }//GEN-LAST:event_jButton12ActionPerformed

    //para que desaparezca el placeholder "Filtrar resultados" cuando
    //se da click en el textfield de búsqueda del registro de todos los teléfonos VOIP
    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained
        if(jTextField3.getForeground()== Color.GRAY){
            jTextField3.setText("");
            jTextField3.setForeground(new JTextField().getForeground());
        }
    }//GEN-LAST:event_jTextField3FocusGained

    //para que aparezca el placeholder "Filtrar resultados" cuando
    //se da click fuera del textfield de búsqueda del registro de todos los teléfonos VOIP
    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        if(jTextField3.getText().equals("")){
            jTextField3.setText("Filtrar resultados");
            jTextField3.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jTextField3FocusLost

    //accion cuando se toca "Enter" en la búsqueda de los telf VOIP
    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            busqueda= jTextField3.getText();
            actualizarListaEquipos("VOIP");
            busqueda= null;
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    //para que desaparezca el placeholder "Filtrar resultados" cuando
    //se da click en el textfield de búsqueda del registro de todos los dispositivos de RED
    private void jTextField4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusGained
        if(jTextField4.getForeground()== Color.GRAY){
            jTextField4.setText("");
            jTextField4.setForeground(new JTextField().getForeground());
        }
    }//GEN-LAST:event_jTextField4FocusGained

    //para que aparezca el placeholder "Filtrar resultados" cuando
    //se da click fuera del textfield de búsqueda del registro de todos los dispositivos de RED
    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        if(jTextField4.getText().equals("")){
            jTextField4.setText("Filtrar resultados");
            jTextField4.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jTextField4FocusLost

    //accion cuando se toca "Enter" en la búsqueda de los dispositivos de RED
    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            busqueda= jTextField4.getText();
            actualizarListaEquipos("DispRED");
            busqueda= null;
        }
    }//GEN-LAST:event_jTextField4KeyReleased

    //acción del botón "Administrar" de la lista de dispositivos de RED
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        int fila= jTable6.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un Dispositivo de RED");
            return;
        }
        fila= jTable6.getRowSorter().convertRowIndexToModel(fila);
        String ip= jTable6.getModel().getValueAt(fila, 1).toString();
        NuevoDispRED mod= new NuevoDispRED(this, true, ip);
        mod.setLocationRelativeTo(this);
        mod.setVisible(true);
        actualizarListaEquipos("DispRED");
        actualizarHistorialDeEventos();
    }//GEN-LAST:event_jButton14ActionPerformed

    //acción del botón "eliminar" en la lista de disposivos de RED
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        int fila= jTable6.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún dispositivo de RED", "Advertencia", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int opcion= JOptionPane.showConfirmDialog(this, "¿Esta seguro/a de querer eliminar este dispositivo de RED"+
                " de la BD? \n Esta operación no puede ser revertida", "Advertencia", JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
        if(opcion == 0){
            fila= jTable6.getRowSorter().convertRowIndexToModel(fila);
            DefaultTableModel tabla= (DefaultTableModel)jTable6.getModel();
            String ip= tabla.getValueAt(fila, 1).toString();
            String tipo= tabla.getValueAt(fila, 2).toString();
            String estado= controladorEq.borrarDispRED(ip);
            if(estado.equals("Equipo eliminado de la BD")){
                JOptionPane.showMessageDialog(this, estado, "Información", JOptionPane.INFORMATION_MESSAGE);
                gestionEventos.agregarEvento("Responsable: " + nombreAdmin + "\n" + 
                        "Lugar: Sistema\n" + "El administrador eliminó el " +
                        tipo + " con el ip: " + ip);
                actualizarHistorialDeEventos();
            }
            actualizarListaEquipos("DispRED");
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    /*para que desaparezca el placeholder "Filtrar resultados" cuando
    se da click en el textfield de búsqueda del plan de mantenimiento*/
    private void jTextField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusGained
        if(jTextField5.getForeground()== Color.GRAY){
            jTextField5.setText("");
            jTextField5.setForeground(new JTextField().getForeground());
        }
    }//GEN-LAST:event_jTextField5FocusGained

    //para que aparezca el placeholder "Filtrar resultados" cuando
    //se da click fuera del textfield de búsqueda del plan de mantenimiento
    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        if(jTextField5.getText().equals("")){
            jTextField5.setText("Filtrar resultados");
            jTextField5.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jTextField5FocusLost

    /*accion cuando se toca "Enter" en la búsqueda del Plan de mantenimiento,
    solo busca por equipo, área y número de inventario*/
    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            busqueda= jTextField5.getText();
            actualizarPlanMantenimiento();
            busqueda= null;
        }
    }//GEN-LAST:event_jTextField5KeyReleased

    /*acción que ocurre cuando se da click en las celdas de mantenimiento o de las fechas
    en el Plan de mantenimiento*/
    private void jTable7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MousePressed
        //<editor-fold defaultstate="collapsed" desc="código">
        Point MouseCoord= evt.getPoint();
        for(int row= 0; row < jTable7.getRowCount(); row++){
            for(int column= 3; column < 11; column++){
                Rectangle celda= jTable7.getCellRect(row, column, false);
                String nombreColumna= jTable7.getColumnName(column);
                int margenIni, margenFin;
                if(nombreColumna.contains("Mant.")){
                    margenIni= 30;
                    margenFin= 30;
                }
                else {
                    margenIni= 20;
                    margenFin= 20;
                }
                int anchoCelda= jTable7.getColumnModel().getColumn(column).getPreferredWidth();
                int coordCeldaX1= celda.x + margenIni;
                int coordCeldaX2= celda.x + anchoCelda - margenFin;
                int coordCeldaY1= celda.y;
                int coordCeldaY2= celda.y + 25;
                if(MouseCoord.x > coordCeldaX1 && MouseCoord.x < coordCeldaX2 && 
                        MouseCoord.y > coordCeldaY1 && MouseCoord.y < coordCeldaY2){
                    if(jTable7.getValueAt(row, 0).toString().equals("Total"))
                        return;
                    switch(nombreColumna){
                        case "Mant. I General" -> {
                            String valor= jTable7.getValueAt(row, column).toString();
                            if(valor.equals("X"))
                                jTable7.setValueAt("", row, column);
                            else{
                                jTable7.setValueAt("X", row, column);
                                jTable7.setValueAt("", row, column + 1);
                            }
                        }
                        case "Mant. I Parcial" -> {
                            String valor= jTable7.getValueAt(row, column).toString();
                            if(valor.equals("X"))
                                jTable7.setValueAt("", row, column);
                            else{
                                jTable7.setValueAt("X", row, column);
                                jTable7.setValueAt("", row, column - 1);
                            }
                        }
                        case "Mant. II General" -> {
                            String valor= jTable7.getValueAt(row, column).toString();
                            if(valor.equals("X"))
                                jTable7.setValueAt("", row, column);
                            else{
                                jTable7.setValueAt("X", row, column);
                                jTable7.setValueAt("", row, column + 1);
                            }
                        }
                        case "Mant. II Parcial" -> {
                            String valor= jTable7.getValueAt(row, column).toString();
                            if(valor.equals("X"))
                                jTable7.setValueAt("", row, column);
                            else{
                                jTable7.setValueAt("X", row, column);
                                jTable7.setValueAt("", row, column - 1);
                            }
                        }
                        case "Fecha I Sem. Plan" -> {
                            escogerFecha(evt.getLocationOnScreen(), jTable7, row, column);
                            /*lo siguiente asigna a "Fecha II Sem. Plan" la fecha 6 meses después
                            de "Fecha I Sem. Plan" sin pasarse de diciembre*/
                            String valor= jTable7.getValueAt(row, column).toString();
                            if(valor.equals("")) return;
                            String dia= valor.split("/")[0];
                            Integer mes= Integer.valueOf(valor.split("/")[1]);
                            mes= mes + 6 > 12? 12 : mes + 6;//evita que se pase del mes 12
                            String anno= valor.split("/")[2];
                            valor= dia + "/" + mes + "/" + anno;
                            jTable7.setValueAt(valor, row, 9);
                        }
                        default -> escogerFecha(evt.getLocationOnScreen(), jTable7, row, column);
                    }
                    return;
                }
            }
        }
        //</editor-fold>
    }//GEN-LAST:event_jTable7MousePressed

    /*en la tabla del plan de mantenimiento cuando se mueve el mouse por las celdas
    de Fechas y mantenimiento cambia el cursor a handcursor y en las demás celdas 
    al cursor flecha por defecto*/
    private void jTable7MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseMoved
        //<editor-fold defaultstate="collapsed" desc="código">
        Point MouseCoord= evt.getPoint();
        for(int row= 0; row < jTable7.getRowCount(); row++){
            for(int column= 3; column < 11; column++){
                Rectangle celda= jTable7.getCellRect(row, column, false);
                String nombreColumna= jTable7.getColumnName(column);
                int margenIni, margenFin;
                if(nombreColumna.contains("Mant.")){
                    margenIni= 30;
                    margenFin= 30;
                }
                else {
                    margenIni= 20;
                    margenFin= 20;
                }
                int anchoCelda= jTable7.getColumnModel().getColumn(column).getPreferredWidth();
                int coordCeldaX1= celda.x + margenIni;
                int coordCeldaX2= celda.x + anchoCelda - margenFin;
                int coordCeldaY1= celda.y;
                int coordCeldaY2= celda.y + 25;
                if(MouseCoord.x > coordCeldaX1 && MouseCoord.x < coordCeldaX2 && 
                        MouseCoord.y > coordCeldaY1 && MouseCoord.y < coordCeldaY2){
                    if(jTable7.getValueAt(row, 0).toString().equals("Total"))
                        jTable7.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    else
                        jTable7.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    return;
                }
                else if(jTable7.getCursor().getType() == Cursor.HAND_CURSOR) 
                    jTable7.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
        //</editor-fold>
    }//GEN-LAST:event_jTable7MouseMoved

    /*acción del botón "Actualizar" del plan de mantenimiento,
    el cual actualiza los valores del plan en la BD o crea uno si no existe ninguno*/
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        List<Object[]> plan= controladorMant.obtenerPlan(null);
        
        //para que puedan actualizarse equipos con tipo de equipo, no. inv. y área similares
        List<Object[]> actualizados= new LinkedList<>();
        
        for(int row= 0; row < jTable7.getRowCount(); row++){
            Object[] fila= new Object[11];
            for(int column= 0; column < 11; column++)
                fila[column]= jTable7.getValueAt(row, column);
            for(Object[] equipo : plan){
                if(equipo[0].equals(fila[0]) && equipo[1].equals(fila[1]) &&
                        equipo[2].equals(fila[2])){
                    if(actualizados.contains(equipo)) continue;
                    System.arraycopy(fila, 3, equipo, 3, 8);
                    actualizados.add(equipo);
                    break;
                }
            }
        }
        String msg= controladorMant.actualizarPlan();
        JOptionPane.showMessageDialog(this, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
        actualizarPlanMantenimiento();
        if(msg.equals("Plan actualizado"))
            gestionEventos.agregarEvento("Responsable: " + nombreAdmin + "\n" + 
                    "Lugar: Sistema\n" + "El administrador actualizó el Plan de mantenimiento");
        else if(msg.equals("Plan de mantenimiento creado"))
            gestionEventos.agregarEvento("Responsable: " + nombreAdmin + "\n" + 
                    "Lugar: Sistema\n" + "El administrador creó un nuevo Plan de mantenimiento");
    }//GEN-LAST:event_jButton17ActionPerformed

    /*acción del botón "Limpiar" del Plan de mantenimiento,
    elimina todo el plan de la BD*/
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        int opcion= JOptionPane.showConfirmDialog(this, "¿Está seguro/a que desea Eliminar el Plan completo de la BD?\n" + 
                "Esta acción es irreversible", "Advertencia", JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
        if(opcion == 0){
            String msg= controladorMant.borrarPlan();
            JOptionPane.showMessageDialog(this, msg, "Información", 
                    JOptionPane.INFORMATION_MESSAGE);
            actualizarPlanMantenimiento();
            if(msg.equals("Plan de mantenimiento eliminado")){
                gestionEventos.agregarEvento("Responsable: " + nombreAdmin + "\n" + 
                        "Lugar: Sistema\n" + "El administrador borró todo el Plan de mantenimiento");
                actualizarHistorialDeEventos();
            }
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    //borra todo el historial de eventos
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        int opcion= JOptionPane.showConfirmDialog(this, "¿Está seguro/a que desea borrar todo el historial de eventos?\n"+
                "Esta operación es irreversible", "Alerta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(opcion == 0){
            String estado= controladorEventos.borrarAll();
            if(estado.equals("Todos los eventos borrados")){
                JOptionPane.showMessageDialog(this, estado, "Información", JOptionPane.INFORMATION_MESSAGE);
                actualizarHistorialDeEventos();
            }
            else
                JOptionPane.showMessageDialog(this, estado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    //cierra la sesión
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        cerrarSesion();
    }//GEN-LAST:event_jButton18ActionPerformed

    //pinta el borde del botón "Agregar Equipo" cuando se selecciona
    private void jButton6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton6FocusGained
        jPanel41.setBorder(new LineBorder(new Color(0, 102, 255), 2));
    }//GEN-LAST:event_jButton6FocusGained

    //esconde el borde cuando no está seleccionado el botón "Agregar Equipo"
    private void jButton6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton6FocusLost
        jPanel41.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
    }//GEN-LAST:event_jButton6FocusLost

    //pinta el borde del botón "Iniciar Servidor" cuando se selecciona
    private void jButton4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton4FocusGained
        jPanel42.setBorder(new LineBorder(new Color(0, 102, 255), 2));
    }//GEN-LAST:event_jButton4FocusGained

    //esconde el borde cuando no está seleccionado el botón "Iniciar Servidor"
    private void jButton4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton4FocusLost
        jPanel42.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
    }//GEN-LAST:event_jButton4FocusLost

    //pinta el borde del botón "Configuración" cuando se selecciona
    private void jButton3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton3FocusGained
        jPanel43.setBorder(new LineBorder(new Color(0, 102, 255), 2));
    }//GEN-LAST:event_jButton3FocusGained

    //esconde el borde cuando no está seleccionado el botón "Configuración"
    private void jButton3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton3FocusLost
        jPanel43.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
    }//GEN-LAST:event_jButton3FocusLost

    //pinta el borde del botón "Cerrar Sesión" cuando se selecciona
    private void jButton18FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton18FocusGained
        jPanel44.setBorder(new LineBorder(new Color(0, 102, 255), 2));
    }//GEN-LAST:event_jButton18FocusGained

    //esconde el borde cuando no está seleccionado el botón "Cerrar Sesión"
    private void jButton18FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton18FocusLost
        jPanel44.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
    }//GEN-LAST:event_jButton18FocusLost

    /*oculta o muestra la contraseña de la ventana "Establecer Conexión"*/
    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        char echo= jPasswordField1.getEchoChar();
        String tema= OpcionesConfig.LocalConfig.getTema();
        if(echo == '\u2022'){
            jPasswordField1.setEchoChar((char)0);
            if(tema.contains("Dar") || tema.contains("HiFi") || tema.contains("Noire"))
            jLabel36.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye2_b.png")));
            else jLabel36.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye2.png")));
        }
        else {
            jPasswordField1.setEchoChar('\u2022');
            if(tema.contains("Dar") || tema.contains("HiFi") || tema.contains("Noire"))
            jLabel36.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye_b.png")));
            else jLabel36.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye.png")));
        }
    }//GEN-LAST:event_jLabel36MouseClicked

    //acción cuando se borra algún carácter en la dirección IP
    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
        formatJTextFieldForIP(jTextField7, evt);
    }//GEN-LAST:event_jTextField7KeyPressed

    //acción cuando se escribe en la dirección IP
    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        formatJTextFieldForIP(jTextField7, evt);
    }//GEN-LAST:event_jTextField7KeyTyped

    /*Se cierra la App cuando se da click en el botón "Cancelar" en la 
    ventana "Establecer Conexión"*/
    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton21ActionPerformed

    /*se recoge información referente a la url y autentificación para
    la conexión a la base de datos y se guarda en el archivo local "config.cfg"*/
    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        String nombreBD= jTextField6.getText();
        String ipAdress= jTextField7.getText();
        if(ipAdress.equals("127.0.0.1")) ipAdress= "localhost";
        String puerto= jFormattedTextField1.getText();
        String usuario= jTextField8.getText();
        String password= String.valueOf(jPasswordField1.getPassword());
        String msg;
        if(nombreBD.equals(""))
            msg= "El nombre de la base de datos no debe estar vacio";
        else if(ipAdress.equals(""))
            msg= "La dirección IP no debe estar vacia";
        else if(puerto.equals(""))
            msg= "El puerto no debe estar vacio";
        else if(usuario.equals(""))
            msg= "El nombre del usuario no debe estar vacio";
        else if(password.equals(""))
            msg= "La contraseña no debe estar vacia";
        else msg= "";
        if(!msg.equals("")){
            JOptionPane.showMessageDialog(this, msg, "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        OpcionesConfig.LocalConfig.setURL(String.format("jdbc:postgresql://%s:%s/%s",
                ipAdress, puerto, nombreBD));
        OpcionesConfig.LocalConfig.setCredenciales(new String[]{usuario, password});
        Conexion.dispose();
    }//GEN-LAST:event_jButton20ActionPerformed

    /*Se cierra la App cuando se decide cerrar también
    la ventana "Establecer Conexión"*/
    private void ConexionWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ConexionWindowClosing
        System.exit(0);
    }//GEN-LAST:event_ConexionWindowClosing

    //se cierra el sessionfactory para liberar recursos cuando se cierra la ventana
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        hibernateSessionFactory.shutDown();
    }//GEN-LAST:event_formWindowClosing

    //pinta el borde del botón "Acerca de" cuando se selecciona
    private void jButton22FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton22FocusGained
        jPanel58.setBorder(new LineBorder(new Color(0, 102, 255), 2));
    }//GEN-LAST:event_jButton22FocusGained

    //esconde el borde cuando no está seleccionado el botón "Acerca de"
    private void jButton22FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton22FocusLost
        jPanel58.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
    }//GEN-LAST:event_jButton22FocusLost

    //muestra ventana con info del desarrollador y la Universidad
    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        AcercaDe.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        AcercaDe.setIconImage(new ImageIcon(getClass().
                getResource("/Iconos/logo.png")).getImage());
        AcercaDe.pack();
        AcercaDe.setLocationRelativeTo(this);
        AcercaDe.setVisible(true);
    }//GEN-LAST:event_jButton22ActionPerformed
    
    /*se evita que se procese el evento de manera normal, o sea, se evita
    que se introduzcan carácteres en el jTextField como se haría normalmente
    y solo se hace de la manera que se define en este método,
    este método se debe usar en los eventos KeyPressed y KeyTyped para que
    funcione bien, no en el evento KeyReleased donde da un comportamiento raro*/
    private void formatJTextFieldForIP(javax.swing.JTextField textoField, java.awt.event.KeyEvent evt){
        //<editor-fold defaultstate="collapsed" desc="código">
        
        /*aqui se procesa el evento cuando se presiona el botón "delete"
        o "backspace" del teclado para borrar carácteres del jtexfield*/
        if(evt.getID() == java.awt.event.KeyEvent.KEY_PRESSED){
            
            //se obtiene el código de tecla presionada
            int code= evt.getKeyCode();
            
            /*si se selecciona un texto y se presiona delete o backspace
            se evita el borrado y se sale de la función*/
            if(code == 8 || code == 127){
                String cadSeleccionada= textoField.getSelectedText();
                if(cadSeleccionada!= null){
                    evt.consume();
                    return;
                }
            }
            
            /*/pos/ permite saber en que lugar del texto
            se va a borrar el carácter*/
            int pos= textoField.getCaretPosition();
            
            //se obtiene el texto del jtextfield
            String ip= textoField.getText();
        
            /*Lista que va a aceptar todas las letras de /ip/
            esta lista sirve para quitar el carácter señalado por /pos/*/
            List<Character> charList= new ArrayList<>();
            
            //Se asigna a /charList/ los carácteres de /ip/
            for(char caracter : ip.toCharArray())
                charList.add(caracter);
            
            /*si se presiona backspace se elimina el carácter señalado por
            /pos/ en /charList/*/
            if(code == java.awt.event.KeyEvent.VK_BACK_SPACE){
                //si esta /pos/ al principio de /ip/ se sale y no continua
                if(pos == 0) return;
                //elimina el carácter señalado
                charList.remove(--pos);
            }
            /*si se presiona delete se elimina el carácter señalado por
            /pos/ en /charList/*/
            else if(code == java.awt.event.KeyEvent.VK_DELETE){
                //si esta /pos/ al final de /ip/ se sale y no continua
                if(pos == ip.length()) return;
                //elimina el carácter señalado
                charList.remove(pos);
            }
            
            /*se asigna a/ip/ el /charList/ modificado*/
            ip= "";
            for(Character caracter : charList)
                ip+= caracter;
                
            /*si se borra un punto de /ip/ o un posible único número entre
            2 puntos se evita que sev procese el evento*/
            String[] ipArray= ip.split("\\.");
            for(String fragmento : ipArray){
                if(fragmento.equals("") || ipArray.length < 4)
                    evt.consume();
            }
            
            return;
        }
        
        //a partir de aqui se procesa el evento cuando se introducen carácteres
        
        /*se evita la introducción de carácteres como lo haría
        el listener por defecto*/
        evt.consume();
        
        char key= evt.getKeyChar(); //se obtiene el carácter introducido
        try{
            /*se trata de convertir el carácter a número, si no es un número
            se lanza la excepción y se sale de la función, asi se evita que
            se introduzcan otros carácteres que no sean números*/
            Integer.valueOf(String.valueOf(key));
        }catch(NumberFormatException e){
            return;
        }
        
        /*si se selecciona un texto y se escribe y este contiene algún punto
        se sale de la función, esto es para evitar quitar los puntos*/
        String cadSeleccionada= textoField.getSelectedText();
        if(cadSeleccionada!= null && cadSeleccionada.contains("."))
            return;
        
        //se obtiene el texto del jtextfield
        String ip= textoField.getText();
        
        /*Lista que va a aceptar todas las letras de /ip/
        esta lista sirve para agregar el carácter introducido /key/*/
        List<Character> charList= new ArrayList<>();
        
        /*esta lista contenfrá los índices de los puntos*/
        List<Integer> puntosIndex= new ArrayList<>();
        
        /*Se asigna a /charList/ los carácteres de /ip/ y a /puntosIndex/
        los índices de los puntos*/
        int index= 0;
        for(char caracter : ip.toCharArray()){
            charList.add(caracter);
            if(caracter == '.')
                puntosIndex.add(index);
            index++;
        }
        
        /*/pos/ permite saber en que lugar del texto
        se va a introducir el carácter*/
        int pos= textoField.getCaretPosition();
        
        String num= ""; //contendrá un número de los 4 que hay en /ip/
        int lugarDigito= 0; //permite saber qué dígito se va a cambiar del número
        
        /*De los 4 números que hay en /ip/ se obtendrá el número donde apunte
        /pos/ y también se obtendrá la posición del dígito que se va a cambiar*/
        if(pos <= puntosIndex.get(0)){
            num= ip.split("\\.")[0];
            lugarDigito= pos;
        }
        else if(pos <= puntosIndex.get(1)){
            num= ip.split("\\.")[1];
            lugarDigito= pos - puntosIndex.get(0) - 1;
        }
        else if(pos <= puntosIndex.get(2)){
            num= ip.split("\\.")[2];
            lugarDigito= pos - puntosIndex.get(1) - 1;
        }
        else if(pos <= ip.length()){
            num= ip.split("\\.")[3];
            lugarDigito= pos - puntosIndex.get(2) - 1;
        }
        
        //si el número tiene 3 dígitos:
        if(num.length() == 3){
            /*si /pos/ apunta al final de /ip/ sale de la función,
            como el número tiene 3 dígitos no se puede introducir más*/
            if(pos == ip.length())
                return;
            /*si /pos/ apunta a un punto ('.') se incrementa en 1 /pos/,
            esto significa que se va a saltar ese carácter*/
            else if(charList.get(pos).equals('.'))
                pos++;
            //si se está en cualquier posición distinta a un punto ('.'):
            else{
                /*se obtiene un array del número para modificar el dígito
                en la posición señalada por /lugarDigito/*/
                char[] numArray= num.toCharArray();
                //se modifica el dígito señalado
                numArray[lugarDigito]= key;
                /*se convierte el número modificado de tipo cadena
                por un número real*/
                int real= Integer.parseInt(String.valueOf(numArray));
                /*si el número real es mayor que 255 se sale de la función,
                esto significa que solo se aceptaran en el ip
                números de 0 a 255*/
                if(real > 255) return;
            }
            /*se modifica /charList/ intercambiando el dígito
            señalado por /pos/ por el carácter o número introducido*/
            charList.set(pos, key);
        }
        //si el número tiene 1 o 2 dígitos:
        else{
            /*si /pos/ apunta al final de /ip/ o a un punto ('.')
            se obtiene el número real(número señalado por /pos/ mas el número
            introducido) y si es menor o igual que 255 se adiciona a /charList/
            (no se intercambia el dígito señalado), sino se sale de la función*/
            if(pos == ip.length() || charList.get(pos).equals('.')){
                int real= Integer.parseInt(String.valueOf(num + key));
                if(real > 255) return;
                charList.add(pos, key);
            }
            /*si se está en cualquier otra posición se modifica /charList/
            intercambiando el dígito señalado por /pos/ por el carácter o
            número introducido*/
            else charList.set(pos, key);
        }
        
        /*se asigna a/ip/ el /charList/ modificado*/
        ip= "";
        for(Character caracter : charList)
            ip+= caracter;
        
        //se quitan los ceros que hay delante de los números como 06, 008
        String[] ipArray= ip.split("\\.");
        ip= "";
        for(String fragmento : ipArray){
            int real= Integer.parseInt(fragmento);
            ip+= real + ".";
        }
        //se quita el último carácter que es '.'
        ip= ip.substring(0, ip.length() - 1);
        
        //se asigna al jtextfield el nuevo ip
        textoField.setText(ip);
        
        //se posiciona el caret un lugar después de donde /pos/ señala
        if(pos < ip.length()) pos++;
        textoField.setCaretPosition(pos);
        
        //</editor-fold>
    }
    
    public static void setLaf(String Laf){
        //<editor-fold defaultstate="collapsed" desc="Establecer el LookAndFeel escogido">
        try{
            switch(Laf){
                case "Nimbus (Por defecto)" -> UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                case "Metal" -> UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                case "Motif/CDE" -> UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                case "FlatLaf Darcula" -> com.formdev.flatlaf.FlatDarculaLaf.setup();
                case "FlatLaf Dark" -> com.formdev.flatlaf.FlatDarkLaf.setup();
                case "FlatLaf Light" -> com.formdev.flatlaf.FlatLightLaf.setup();
                case "FlatLaf IntelliJ" -> com.formdev.flatlaf.FlatIntelliJLaf.setup();
                case "FlatLaf MacDark" -> com.formdev.flatlaf.themes.FlatMacDarkLaf.setup();
                case "FlatLaf MacLight" -> com.formdev.flatlaf.themes.FlatMacLightLaf.setup();
                case "JTattoo Luna" -> UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
                case "JTattoo Acryl" -> UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                case "JTattoo Aero" -> UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
                case "JTattoo Aluminium" -> UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
                case "JTattoo Bernstein" -> UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
                case "JTattoo Fast" -> UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
                case "JTattoo Graphite" -> UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
                case "JTattoo HiFi" -> UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
                case "JTattoo McWin" -> UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                case "JTattoo Mint" -> UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
                case "JTattoo Noire" -> UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
                case "JTattoo Smart" -> UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
                case "JTattoo Texture" -> UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
                case "Material UI" -> {
                    UIManager.setLookAndFeel("mdlaf.MaterialLookAndFeel");
                    mdlaf.MaterialLookAndFeel.changeTheme(new mdlaf.themes.MaterialLiteTheme());
                }
            }
        }catch(Exception e){
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, e);
        }
        //</editor-fold>
    }
    
    //agrega a la tabla de los equipos según sea el caso, los datos resumidos de los equipos
    //también maneja la búsqueda y define el tamaño de las columnas
    private void actualizarListaEquipos(String tipoTabla){
        JTable table;
        JPanel panel;
        List<Object[]> filas;
        
        //<editor-fold defaultstate="collapsed" desc="Se escoge que tabla y que tipo de equipos se van a usar">
        switch(tipoTabla){
            case "Computadoras" -> {
                table= jTable1;
                panel= jPanel9;
                filas= controladorEq.resumenComputadoras(busqueda);
            }
            case "Moviles" -> {
                table= jTable4;
                panel= jPanel19;
                filas= controladorEq.resumenMoviles(busqueda);
            }
            case "VOIP" -> {
                table= jTable5;
                panel= jPanel23;
                filas= controladorEq.resumenVOIP(busqueda);
            }
            default -> {
                table= jTable6;
                panel= jPanel26;
                filas= controladorEq.resumenDispRED(busqueda);
            }
        }
        //</editor-fold>
        
        DefaultTableModel tabla= (DefaultTableModel)table.getModel();
        int filaSelecionada= table.getSelectedRow();
        tabla.setRowCount(0);
        for(Object[] fila : filas)
            tabla.addRow(fila);
        CardLayout cardlayout= (CardLayout)panel.getLayout();
        if(tabla.getRowCount() == 0) cardlayout.show(panel, "card2"); //muestra la imagen de Lista vacia
        else {
            try{
                table.setRowSelectionInterval(filaSelecionada, filaSelecionada);
            }
            catch(IllegalArgumentException e){
                table.setRowSelectionInterval(0, 0);
            }
            cardlayout.show(panel, "card3"); //muestra la tabla
        }
        
        switch(tipoTabla){
            case "Computadoras" -> setTamColumnasCompu();
        }
    }
    
    private void setTamColumnasCompu(){
        //esto es para establecer el tamaño de las columnas de la tabla de computadoras
        for(int i= 0; i < 12; i++){
            String columnName= jTable1.getColumnName(i);
            int columnWidth= columnName.length() * 8;
            switch(columnName){
                case "Pos."-> jTable1.getColumnModel().getColumn(i).setPreferredWidth(50);
                case "Online"-> jTable1.getColumnModel().getColumn(i).setPreferredWidth(50);
                case "Tipo"-> jTable1.getColumnModel().getColumn(i).setPreferredWidth(60);
                case "Estado"-> jTable1.getColumnModel().getColumn(i).setPreferredWidth(90);
                default->{
                    for(int fila= 0; fila < jTable1.getRowCount(); fila++){
                        int rowColumnWidth= jTable1.getValueAt(fila, i).toString().length() * 8;
                        columnWidth= rowColumnWidth > columnWidth? rowColumnWidth : columnWidth;
                    }
                    jTable1.getColumnModel().getColumn(i).setPreferredWidth(columnWidth);
                }
            }
        }
    }
    
    private void mostrarRedLAN(){
        DefaultTableModel tabla= (DefaultTableModel)jTable2.getModel();
        List<Object[]> scanLan= server.ScanLAN();
        List<String> ips= new LinkedList<>();
        tabla.setRowCount(0);
        int id= 1;
        for(Object[] dataConID : scanLan) {
            int ID= Integer.parseInt(dataConID[2].toString());
            Object[] data= new Object[]{id++, dataConID[0], dataConID[1], 
                (controladorEq.estaRegistrada(ID))?"registrado":"no registrado"};
            tabla.addRow(data);
            ips.add(dataConID[1].toString());
        }
        //para el valor de la columna Online de la tabla de computadoras
        //pinta en verde los que están conectados
        for(int i= 0; i < jTable1.getModel().getRowCount(); i++){
            String ip= jTable1.getModel().getValueAt(i, 5).toString();
            if(ips.contains(ip)) jTable1.getModel().setValueAt("si", i, 1);
        }
    }
    
    private void bloqueoThreadPrincipal(){
        if(Block){
            Loading loading= new Loading(this, Dialog.ModalityType.APPLICATION_MODAL);
            loading.execMetodo(()->{
                try{
                    synchronized (controladorHilos) {
                        controladorHilos.wait();
                    }
                }
                catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });
        }
    }
    
    private void crearThreadActualizacion(){
        Integer tiempoEspera= OpcionesConfig.LocalConfig.getTiempoEspera();
        hilo= new Thread(()->{
            try{
                while(true){
                    if(Block){
                        synchronized (controladorHilos) {
                            controladorHilos.wait();
                        }
                    }
                    Block= true;
                    server.actualizarTodos();
                    actualizarListaEquipos("Computadoras");
                    actualizarHistorialDeEventos();
                    mostrarRedLAN();
                    Block= false;
                    synchronized (controladorHilos) {
                        controladorHilos.notify();
                    }
                    Thread.sleep(tiempoEspera);
                    if(Thread.currentThread().isInterrupted()) break;
                }
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
            finally{
                //pinta en rojo el valor de la columna Online de la tabla de computadoras cuando se interrumpe
                for(int i= 0; i < jTable1.getModel().getRowCount(); i++)
                    jTable1.getModel().setValueAt("no", i, 1);
                //cambia el icono y etiqueta en la barra de estado que indica si el servidor está corriendo o no
                jLabel4.setText("Servidor Parado");
                jLabel4.setIcon(new ImageIcon(getClass().getResource("/Iconos/loading-red-16x16.gif")));
            }
        });
    }
    
    //pinta un circulo verde o rojo según el estado Online en la tabla de lista de todas los computadoras
    private void pintarValorOnlineDeTabla(){
        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component celda= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(isSelected)
                    ((JLabel)celda).setForeground(table.getSelectionForeground());
                else
                    ((JLabel)celda).setForeground(table.getForeground());
                if(column == 1){
                    if(value.toString().equals("no"))
                        ((JLabel)celda).setForeground(Color.red);
                    else
                        ((JLabel)celda).setForeground(Color.green);
                    ((JLabel)celda).setText("\u2B24");
                }
                return celda;
            }
        });
    }
    
    //pinta un icono verde de paloma o cruz roja en la tabla de la lista de equipos conectados en la red LAN
    private void pintarValorRegistroDeTabla(){
        jTable2.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component celda= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel)celda).setIcon(null);
                ((JLabel)celda).setHorizontalAlignment(LEFT);
                if(column == 3){
                    if(value.toString().equals("no registrado"))
                        ((JLabel)celda).setIcon(new ImageIcon(getClass().getResource("/Iconos/no registrado32x32.png")));
                    else
                        ((JLabel)celda).setIcon(new ImageIcon(getClass().getResource("/Iconos/registrado32x32.png")));
                    ((JLabel)celda).setHorizontalAlignment(CENTER);
                    ((JLabel)celda).setText("");
                }
                return celda;
            }
        });
    }
   
    //pinta un icono de reporte verde en la fila del reporte generado en la tabla de reportes
    private void pintarValorGeneradoDeTabla(){
        jTable3.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component celda= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel)celda).setIcon(null);
                ((JLabel)celda).setHorizontalAlignment(LEFT);
                if(column == 2){
                    if(value.toString().equals("generado"))
                        ((JLabel)celda).setIcon(new ImageIcon(getClass().getResource("/Iconos/generado.png")));
                    ((JLabel)celda).setHorizontalAlignment(CENTER);
                    ((JLabel)celda).setText("");
                }
                return celda;
            }
        });
    }
    
    private void resizeTablaReportes(){
        jTable3.getColumnModel().getColumn(0).
                setPreferredWidth(jPanel13.getWidth() - 640 - 10);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(570);
        jTable3.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable3.addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
            @Override
            public void ancestorResized(HierarchyEvent e) {
                super.ancestorResized(e);
                jTable3.getColumnModel().getColumn(0).
                        setPreferredWidth(jPanel13.getWidth() - 640 - 10);
            }
        });
    }
    
    private void pintarIconosDelArbolDeFunciones(){
        jTree1.setCellRenderer(new DefaultTreeCellRenderer(){
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                Component celda= super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                switch(value.toString()){
                    //<editor-fold defaultstate="collapsed" desc="asignación de iconos por nodo">
                    case "Funciones"-> ((JLabel)celda).setIcon(new ImageIcon(getClass().
                            getResource("/Iconos/módulos24x24.png")));
                    case "Inventario"-> ((JLabel)celda).setIcon(new ImageIcon(getClass().
                            getResource("/Iconos/inventario24x24.png")));
                    case "Computadoras"-> ((JLabel)celda).setIcon(new ImageIcon(getClass().
                            getResource("/Iconos/compu24x24.png")));
                    case "Teléfonos móviles"-> ((JLabel)celda).setIcon(new ImageIcon(getClass().
                            getResource("/Iconos/MMCel24x24.png")));
                    case "Routers y Switchs"-> ((JLabel)celda).setIcon(new ImageIcon(getClass().
                            getResource("/Iconos/dispRED24x24.png")));
                    case "Teléfonos VOIP"-> ((JLabel)celda).setIcon(new ImageIcon(getClass().
                            getResource("/Iconos/telf VOIP24x24.png")));
                    case "LAN"-> ((JLabel)celda).setIcon(new ImageIcon(getClass().
                            getResource("/Iconos/lan24x24.png")));
                    case "Reportes"-> ((JLabel)celda).setIcon(new ImageIcon(getClass().
                            getResource("/Iconos/Reportes24x24.png")));
                    case "Plan de mantenimiento"-> ((JLabel)celda).setIcon(new ImageIcon(getClass().
                            getResource("/Iconos/mantenimiento24x24.png")));
                    case "Historial de eventos"-> ((JLabel)celda).setIcon(new ImageIcon(getClass().
                            getResource("/Iconos/eventos24x24.png")));
                    //</editor-fold>
                }
                return celda;
            }
        });
    }
    
    private void cargarIconosBParaTemasN(){
        String tema= OpcionesConfig.LocalConfig.getTema();
        if(tema.contains("Dar") || tema.contains("HiFi") || tema.contains("Noire")){
            jLabel2.setIcon(new ImageIcon(getClass().getResource("/Iconos/PC_b.png")));
            if(tema.contains("Noire"))
                jButton1.setPressedIcon(new ImageIcon(getClass().getResource("/Iconos/admin_b.png")));
            else
                jButton1.setIcon(new ImageIcon(getClass().getResource("/Iconos/admin_b.png")));
            jLabel3.setIcon(new ImageIcon(getClass().getResource("/Iconos/lan_b.png")));
            jLabel7.setIcon(new ImageIcon(getClass().getResource("/Iconos/Reportes_b.png")));
        }
    }
    
    private void establecerIconoYNombreCuenta(){
        if(superAdmin)
            jLabel42.setIcon(new ImageIcon(getClass().getResource("/Iconos/cuenta_o_big.png")));
        else
            jLabel42.setIcon(new ImageIcon(getClass().getResource("/Iconos/cuenta_v_big.png")));
        jLabel42.setText(nombreAdmin);
        jLabel42.setToolTipText("Cambiar Contraseña");
        if(nombreAdmin.equals("cupet")) return;
        jLabel42.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /*cambia la contraseña del administrador activo*/
        jLabel42.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cambiarPassword();
            }
        });
    }
    
    private void cambiarPassword(){
        String[] passwords= new String[3];
        String tema= OpcionesConfig.LocalConfig.getTema();
        
        //<editor-fold defaultstate="collapsed" desc="creación de la ventana para poner los nombres">
        JDialog changePassword= new JDialog(this, "Cambiar contraseña", true);
        LayoutManager layout= new BoxLayout(changePassword.getContentPane(), BoxLayout.PAGE_AXIS);
        changePassword.getContentPane().setLayout(layout);
        
        JPanel panel1= new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 5));
        JLabel etiqueta_1= new JLabel("Introduzca la contraseña actual:");
        JPasswordField password1= new JPasswordField();
        password1.setPreferredSize(new Dimension(150, 30));
        password1.setEchoChar('\u2022');
        JLabel showOrHidePassword1= new JLabel();
        if(tema.contains("Dar") || tema.contains("HiFi") || tema.contains("Noire"))
            showOrHidePassword1.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye_b.png")));
        else showOrHidePassword1.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye.png")));
        showOrHidePassword1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showOrHidePassword1.setToolTipText("Mostrar u ocultar contraseña");
        JPanel panel2= new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel2.add(password1);
        panel2.add(showOrHidePassword1);
        panel1.add(etiqueta_1);
        panel1.add(panel2);

        JPanel panel3= new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 5));
        JLabel etiqueta_2= new JLabel("Introduzca la nueva contraseña:");
        JPasswordField password2= new JPasswordField();
        password2.setPreferredSize(new Dimension(150, 30));
        password2.setEchoChar('\u2022');
        JLabel showOrHidePassword2y3= new JLabel();
        if(tema.contains("Dar") || tema.contains("HiFi") || tema.contains("Noire"))
            showOrHidePassword2y3.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye_b.png")));
        else showOrHidePassword2y3.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye.png")));
        showOrHidePassword2y3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showOrHidePassword2y3.setToolTipText("Mostrar u ocultar contraseña");
        JPanel panel4= new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel4.add(password2);
        panel4.add(showOrHidePassword2y3);
        panel3.add(etiqueta_2);
        panel3.add(panel4);
        
        JPanel panel5= new JPanel(new FlowLayout(FlowLayout.LEADING, 33, 5));
        JLabel etiqueta_3= new JLabel("Repita la nueva contraseña:");
        JPasswordField password3= new JPasswordField();
        password3.setPreferredSize(new Dimension(150, 30));
        password3.setEchoChar('\u2022');
        panel5.add(etiqueta_3);
        panel5.add(password3);
        
        JPanel panel6= new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JButton aceptar= new JButton("Aceptar");
        aceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton cancelar= new JButton("Cancelar");
        cancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel panel7= new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel7.add(aceptar);
        panel7.add(cancelar);
        panel6.add(panel7);
        
        changePassword.getContentPane().add(panel1);
        changePassword.getContentPane().add(panel3);
        changePassword.getContentPane().add(panel5);
        changePassword.getContentPane().add(panel6);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="acciones de los botones "aceptar", "cancelar" y los de "mostrar u ocultar contraseña"">
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword.dispose();
            }
        });
        
        Inicio estaVentana= this;
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwords[0]= String.valueOf(password1.getPassword());
                passwords[1]= String.valueOf(password2.getPassword());
                passwords[2]= String.valueOf(password3.getPassword());
                for(String password : passwords){
                    if(password.equals("")){
                        JOptionPane.showMessageDialog(estaVentana, "Los campos de las contraseñas"+
                                " no deben estar vacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                if(!new inicioSesion().esAdmin(nombreAdmin, passwords[0])){
                    mostrarMSG(changePassword, "La contraseña actual no es la correcta");
                    return;
                }
                else if(!passwords[1].equals(passwords[2])){
                    mostrarMSG(changePassword, "La contraseña nueva no coincide en ambos campos");
                    return;
                }
                if(passwords[1].matches(".*\\d.*") && 
                        passwords[1].matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")){
                    //cambia la contraseña
                    OpcionesConfig.CuentasAdmin adminCuentas= new OpcionesConfig().cuentasService();
                    adminCuentas.adminCuentas(OpcionesConfig.CuentasAdmin.GET_CUENTAS_DB, null);
                    adminCuentas.adminCuentas(OpcionesConfig.CuentasAdmin.UPDATE_CUENTA, 
                            new Object[]{nombreAdmin, nombreAdmin, passwords[1], superAdmin});
                    adminCuentas.adminCuentas(OpcionesConfig.CuentasAdmin.UPDATE_DB, null);
                    mostrarMSG(changePassword, "Contraseña cambiada con éxito");
                    changePassword.dispose();
                }
                else
                    mostrarMSG(changePassword, "Debe escribir una contraseña que contenga letras en mayúsculas o minúsculas,\n"+
                        "con números, caracteres especiales y con una extensión con más de 8 letras");
            }
        });
        
        showOrHidePassword1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                char echo= password1.getEchoChar();
                if(echo == '\u2022'){
                    password1.setEchoChar((char)0);
                    if(tema.contains("Dar") || tema.contains("HiFi") || tema.contains("Noire"))
                        showOrHidePassword1.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye2_b.png")));
                    else showOrHidePassword1.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye2.png")));
                }
                else {
                    password1.setEchoChar('\u2022');
                    if(tema.contains("Dar") || tema.contains("HiFi") || tema.contains("Noire"))
                        showOrHidePassword1.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye_b.png")));
                    else showOrHidePassword1.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye.png")));
                }
            }
        });
        
        showOrHidePassword2y3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                char echo= password2.getEchoChar();
                if(echo == '\u2022'){
                    password2.setEchoChar((char)0);
                    password3.setEchoChar((char)0);
                    if(tema.contains("Dar") || tema.contains("HiFi") || tema.contains("Noire"))
                        showOrHidePassword2y3.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye2_b.png")));
                    else showOrHidePassword2y3.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye2.png")));
                }
                else {
                    password2.setEchoChar('\u2022');
                    password3.setEchoChar('\u2022');
                    if(tema.contains("Dar") || tema.contains("HiFi") || tema.contains("Noire"))
                        showOrHidePassword2y3.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye_b.png")));
                    else showOrHidePassword2y3.setIcon(new ImageIcon(getClass().getResource("/Iconos/eye.png")));
                }
            }
        });
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="muestra la ventana">
        changePassword.pack();
        changePassword.setLocationRelativeTo(jLabel42);
        changePassword.setVisible(true);
        //</editor-fold>
        
    }
    
    private void mostrarMSG(java.awt.Component padre, String mensaje){
        //<editor-fold defaultstate="collapsed" desc="muestra cuadro de mensaje como un tooltip que desaparece a los 3 segundos">
        JDialog msg= new JDialog();
        msg.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        msg.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        String[] msgSplit= mensaje.split("\n");
        for(int i= 0, max= 0, y= 0; i < msgSplit.length; i++){
            JLabel texto= new JLabel();
            texto.setForeground(Color.BLACK);
            texto.setText(msgSplit[i]);
            max= texto.getText().length() > max? texto.getText().length() : max;
            msg.setPreferredSize(new Dimension(max * 7, y += 30));
            msg.getContentPane().add(texto);
        }
        msg.setUndecorated(true);
        msg.getContentPane().setBackground(Color.GREEN);
        msg.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        msg.pack();
        msg.setLocationRelativeTo(padre);
        new Thread(()->{
            try{
                Thread.sleep(3000);
                //esto es para mostrar un efecto fade off antes de desaparecer msg.
                float[] fadeValues= new float[]{0.8f, 0.6f, 0.4f, 0.2f};
                for(int i= 0; i < 4; i++){
                    msg.setOpacity(fadeValues[i]);
                    Thread.sleep(100);
                }
                msg.setOpacity(0);
            }
            catch(InterruptedException e){e.printStackTrace();}
            msg.dispose();
        }).start();
        msg.setVisible(true);
        //</editor-fold>
    }
    
    private void actualizarPlanMantenimiento(){
        //<editor-fold defaultstate="collapsed" desc="actualiza los datos del Plan de Mantenimiento en la vista">
        
        //elimina el RowSorter personalizado para evitar conflictos con sorterChanged del RowSorter
        jTable7.setRowSorter(null);
        
        //asigna a la tabla la planificación de los equipos
        DefaultTableModel tabla= (DefaultTableModel)jTable7.getModel();
        tabla.setRowCount(0);
        for(Object[] rowData : controladorMant.obtenerPlan(busqueda)){
            Object[] fila= new Object[]{rowData[0], rowData[1], rowData[2], 
                rowData[3], rowData[4], rowData[5], rowData[6], rowData[7], 
                rowData[8], rowData[9], rowData[10]};
            tabla.addRow(fila);
        }
        
        /*obtiene el total de la suma de los valores por columna de todos los equipos
        y lo agrega al final de la tabla*/
        Object[] Total= controladorMant.obtenerTotal();
        if(Total != null) tabla.addRow(Total);
        
        //obtiene el índice de cumplimiento y lo agrega a la vista con un color para mostrar el estado
        Integer indice= controladorMant.obtenerIndiceCump();
        switch(indice){
            case -1 -> {
                jLabel22.setText("No disponible");
                jPanel32.setToolTipText("No disponible hasta que se realice el mantenimiento de todo el año");
                jPanel32.setBackground(new Color(102, 102, 102));
            }
            case 0 -> {
                jLabel22.setText(indice.toString());
                jPanel32.setToolTipText(null);
                jPanel32.setBackground(new Color(204, 0, 0));
            }
            case 3 -> {
                jLabel22.setText(indice.toString());
                jPanel32.setToolTipText(null);
                jPanel32.setBackground(new Color(255, 102, 0));
            }
            case 5 -> {
                jLabel22.setText(indice.toString());
                jPanel32.setToolTipText(null);
                jPanel32.setBackground(new Color(0, 153, 0));
            }
        }
        
        //muestra el icono de lista vacia si la tabla no tiene filas, sino muestra la tabla
        CardLayout cardlayout= (CardLayout)jPanel28.getLayout();
        if(tabla.getRowCount() == 0){
            cardlayout.show(jPanel28, "card2"); //muestra la imagen de Lista vacia
            return;
        }
        else cardlayout.show(jPanel28, "card3"); //muestra la tabla
        
        //establece el tamaño de las columnas
        jTable7.getColumnModel().getColumn(3).setPreferredWidth(95);
        jTable7.getColumnModel().getColumn(4).setPreferredWidth(90);
        jTable7.getColumnModel().getColumn(5).setPreferredWidth(110);
        jTable7.getColumnModel().getColumn(6).setPreferredWidth(115);
        jTable7.getColumnModel().getColumn(7).setPreferredWidth(95);
        jTable7.getColumnModel().getColumn(8).setPreferredWidth(90);
        jTable7.getColumnModel().getColumn(9).setPreferredWidth(115);
        jTable7.getColumnModel().getColumn(10).setPreferredWidth(115);
        for(int column= 0, maxEq= "Equipo".length()*9, maxInv= "No. Inventario".length()*7, maxArea= "Área".length()*9;
                column < 3; column++){
            for(int row= 0; row < jTable7.getRowCount(); row++){
                int tamCelda= jTable7.getModel().getValueAt(row, column).toString().length() * 7;
                switch(column){
                    case 0 -> maxEq= tamCelda > maxEq? tamCelda : maxEq;
                    case 1 -> maxInv= tamCelda > maxInv? tamCelda : maxInv;
                    default -> maxArea= tamCelda > maxArea? tamCelda : maxArea;
                }
            }
            jTable7.getColumnModel().getColumn(column).setPreferredWidth(
                switch(column){
                    case 0 -> maxEq;
                    case 1 -> maxInv;
                    default -> maxArea;
                }
            );
        }
        
        /*Borra cualquier HierarchyBoundsListener que exista y agrega uno nuevo
        para evitar conflitos*/
        for(HierarchyBoundsListener L : jTable7.getHierarchyBoundsListeners())
            jTable7.removeHierarchyBoundsListener(L);
        //si la tabla es menos ancha que su contenedor la agranda al tamño del contenedor
        jTable7.addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
            @Override
            public void ancestorResized(HierarchyEvent e) {
                super.ancestorResized(e);
                int anchoPadre= jTable7.getParent().getWidth();
                int anchoTabla= jTable7.getColumnModel().getColumn(0).getPreferredWidth() + 
                        jTable7.getColumnModel().getColumn(1).getPreferredWidth() + 
                        jTable7.getColumnModel().getColumn(2).getPreferredWidth() + 
                        95 + 90 + 110 + 115 + 95 + 90 + 115 + 115;
                if(anchoPadre > anchoTabla){
                    int extra= anchoPadre - anchoTabla;
                    int numInv= jTable7.getColumnModel().getColumn(1).getPreferredWidth();
                    jTable7.getColumnModel().getColumn(1).setPreferredWidth(numInv + extra);
                }
            }
        });
        
        //establece un renderer para poner el texto de las celdas en el medio
        jTable7.setDefaultRenderer(Object.class, new DefaultTableRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component celda= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel)celda).setHorizontalAlignment(JLabel.CENTER);
                return celda;
            }
        });
        
        /*establece un sorter personalizado para ordenar bien cuando se quiere ordenar por
        no. inventario y fechas, y también excluye siempre la última fila, la cual es la fila del Total*/
        TableRowSorter<TableModel> sorter= new TableRowSorter<>(jTable7.getModel());
        sorter.setComparator(1, (o1, o2)->{
            if(o1.toString().equals("ZZZ") || 
                    o2.toString().equals("")) return 1;
            else if(o2.toString().equals("ZZZ") || 
                    o1.toString().equals("")) return -1;
            Integer valor1= Integer.valueOf(o1.toString());
            Integer valor2= Integer.valueOf(o2.toString());
            if(valor1 < valor2) return -1;
            else if(valor1 > valor2) return 1;
            return 0;
        });
        for(int i= 5; i < 11; i++){
            if(i == 5 || i == 6 || i == 9 || i == 10)
                sorter.setComparator(i, (o1, o2)->{
                    if(o1.toString().equals("ZZZ")) return 1;
                    else if(o2.toString().equals("ZZZ")) return -1;
                    String fecha1= "";
                    if(!o1.toString().equals("")){
                        fecha1+= o1.toString().split("/")[2] + "-";
                        fecha1+= o1.toString().split("/")[1] + "-";
                        fecha1+= o1.toString().split("/")[0]; 
                    }
                    String fecha2= "";
                    if(!o2.toString().equals("")){
                        fecha2+= o2.toString().split("/")[2] + "-";
                        fecha2+= o2.toString().split("/")[1] + "-";
                        fecha2+= o2.toString().split("/")[0];
                    }
                    return fecha1.compareTo(fecha2);
                });
        }
        Object[] total= new Object[11];
        for(int column= 0; column < 11; column++)
            total[column]= tabla.getValueAt(tabla.getRowCount() - 1, column);
        sorter.addRowSorterListener(new RowSorterListener() {
            @Override
            public void sorterChanged(RowSorterEvent e) {
                //ocurre antes del ordenamiento
                if(e.getType() == RowSorterEvent.Type.SORT_ORDER_CHANGED){
                    /*si es ascendente el orden añade "ZZZ" a todas las columnas de total
                    para quedar siempre al final de la tabla*/
                    if(sorter.getSortKeys().getFirst().getSortOrder() == SortOrder.ASCENDING)
                        for(int column= 0; column < 11; column++)
                            tabla.setValueAt("ZZZ", tabla.getRowCount() - 1, column);
                    /*si es descendente el orden añade "" a todas las columnas de total
                    para quedar siempre al final de la tabla*/
                    else
                        for(int column= 0; column < 11; column++)
                            tabla.setValueAt("", tabla.getRowCount() - 1, column);
                }
                //ocurre después del ordenamiento
                else {
                    for(int column= 0; column < 11; column++)
                        tabla.setValueAt(total[column], tabla.getRowCount() - 1, column);
                }
            }
        });
        sorter.setMaxSortKeys(1); //siempre se considera una columna para el ordenamiento
        jTable7.setRowSorter(sorter);
        
        //</editor-fold>
    }
    
    private void escogerFecha(Point p, JTable tabla, int row, int column){
        //<editor-fold defaultstate="collapsed" desc="esto es para mostrar una ventana para escoger una fecha">
        JDialog calendario= new JDialog(this, true); //esto representa la ventana que se muestra para escoger la fecha
        calendario.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //crear el modelo de fecha
        UtilDateModel modelo= new UtilDateModel(new Date());
        //listener para asignar la fecha escogida a la tabla y cerrar la ventana
        modelo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(modelo.getValue() == null){
                    calendario.setVisible(false);
                    calendario.dispose();
                }
                else {
                    Object fecha= (modelo.getDay() < 10? "0" + modelo.getDay() : modelo.getDay()) + "/" +
                            ((modelo.getMonth() + 1) < 10? "0" + (modelo.getMonth() + 1) : (modelo.getMonth() + 1)) + "/" +
                            modelo.getYear();
                    tabla.setValueAt(fecha, row, column);
                }
                calendario.requestFocusInWindow();
            }
        });
        Properties hoy= new Properties();
        hoy.put("text.today", "Hoy");
        //crear el panel de fecha
        JDatePanelImpl datePanel= new JDatePanelImpl(modelo, hoy);
        datePanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        //crear un panel que contendrá al panel de Fecha
        JPanel panel= new JPanel();
        panel.add(datePanel);
        //se realiza toda la configuración del JFrame "Calendario" y el JPanel "Panel"
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        calendario.add(panel);
        calendario.setUndecorated(true);
        calendario.setPreferredSize(new Dimension(300, 200));
        calendario.pack();
        calendario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    calendario.setVisible(false);
                    calendario.dispose();
                }
            }
        });
        /*esto es para obtener un rectangulo con el tamaño total del o los monitores que se esten usando,
        esto me sirve para controlar que la ventana para escoger Fecha que se muestra no se salga
        fuera de la pantalla del monitor*/
        GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
        //se obtienen todos los monitores
        GraphicsDevice[] screens= ge.getScreenDevices();
        //rectangulo con el tamaño total del o los monitores
        Rectangle windowScreen= new Rectangle();
        for(GraphicsDevice screen : screens){
            Rectangle bounds= screen.getDefaultConfiguration().getBounds();
            windowScreen= windowScreen.union(bounds);
        }
        /*estas variables mueven (izq, arriba o ambas) la ventana de escoger Fecha para dentro de la pantalla del o los monitores 
        si se sale hacia fuera de esta o en caso contrario no la mueve*/
        int rollLeft= 0, rollUp= 0;
        if(p.x + calendario.getWidth() > (int)windowScreen.getWidth()){ 
            rollLeft= p.x + calendario.getWidth() - (int)windowScreen.getWidth() + 50;
        }
        if(p.y + calendario.getHeight() > (int)windowScreen.getHeight()){
            rollUp= p.y + calendario.getHeight() - (int)windowScreen.getHeight() + 50;
        }
        calendario.setLocation(p.x - rollLeft, p.y - rollUp);
        calendario.setVisible(true);
        //</editor-fold>
    }
    
    private void establecerEsquinasRedondasEnEventos(){
        String tema= OpcionesConfig.LocalConfig.getTema();
        for(Component c : jPanel37.getComponents()){
            if(c instanceof JLabel fecha && 
                    (tema.contains("Dar") || tema.contains("HiFi") || tema.contains("Noire")))
                fecha.setIcon(new ImageIcon(getClass().getResource("/Iconos/fecha_b.png")));
            else if(c instanceof JPanel evento){
                evento.setBorder(new javax.swing.border.AbstractBorder() {
                    @Override
                    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                        super.paintBorder(c, g, x, y, width, height);
                        Graphics2D g2= (Graphics2D)g.create();
                        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                        java.awt.Shape forma= new RoundRectangle2D.Float(x, y, 
                                width-1, height-1, 50, 50);
                        g2.setColor(Color.WHITE);
                        g2.fill(forma);
                        g2.setColor(Color.BLACK);
                        g2.draw(forma);
                        g2.dispose();
                    }
                });
            }
        }
    }
    
    private void actualizarHistorialDeEventos(){
        jPanel37.removeAll();
        jPanel37.setPreferredSize(new Dimension(800, 10));
        DefaultComboBoxModel<String> comboBoxModelo= (DefaultComboBoxModel)jComboBox1.getModel();
        comboBoxModelo.removeAllElements();
        String ultimaFecha= "";
        for(Object[] evento : controladorEventos.obtenerEventos()){
            //<editor-fold defaultstate="collapsed" desc="crea y muestra los eventos en la vista">
            //esto agrega las fechas en el historial de eventos
            if(!evento[0].toString().equals(ultimaFecha)){
                ultimaFecha= evento[0].toString();
                JLabel fecha= new JLabel(ultimaFecha, 
                        new ImageIcon(getClass().getResource("/Iconos/fecha.png")),
                        JLabel.CENTER);
                fecha.setIconTextGap(15);
                fecha.setPreferredSize(new Dimension(600, 36));
                jPanel37.add(fecha);
                jPanel37.setPreferredSize(new Dimension(800, 
                        jPanel37.getPreferredSize().height + 36 + 10));
                comboBoxModelo.addElement(ultimaFecha);
            }
            
            //esto agrega los eventos después de la fecha correspondiente
            JPanel eventoView= new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 0));
            eventoView.setOpaque(false);
            eventoView.setPreferredSize(new Dimension(600, 130));
            JLabel hora= new JLabel(evento[1].toString());
            hora.setIcon(new ImageIcon(getClass().getResource("/Iconos/hora.png")));
            hora.setForeground(Color.BLACK);
            hora.setIconTextGap(10);
            eventoView.add(hora);
            JSeparator separador= new JSeparator(JSeparator.VERTICAL);
            separador.setPreferredSize(new Dimension(3, 130));
            eventoView.add(separador);
            JPanel descripcion= new JPanel(new FlowLayout(FlowLayout.TRAILING, 0, 5));
            descripcion.setOpaque(false);
            descripcion.setPreferredSize(new Dimension(465, 115));
            JLabel cerrar= new JLabel(new ImageIcon(getClass().getResource("/Iconos/cerrar.png")));
            cerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            if(!superAdmin)
                cerrar.setEnabled(false);
            else
                cerrar.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        DefaultComboBoxModel<String> comboBoxModelo= (DefaultComboBoxModel)jComboBox1.getModel();
                        String estado= controladorEventos.borrarEvento(evento);
                        if(estado.equals("Evento borrado")){
                            JOptionPane.showMessageDialog(eventoView, estado, "Información",
                                    JOptionPane.INFORMATION_MESSAGE);
                            int index= jPanel37.getComponentZOrder(eventoView);
                            if((jPanel37.getComponent(index - 1) instanceof JLabel fecha) 
                                    && (jPanel37.getComponentCount() == (index + 1)
                                    || (jPanel37.getComponent(index + 1) instanceof JLabel))){
                                jPanel37.remove(fecha);
                                comboBoxModelo.removeElement(fecha.getText());
                            }
                            jPanel37.remove(eventoView);
                            jPanel37.repaint();
                            jPanel37.setPreferredSize(new Dimension(800,
                                    jPanel37.getPreferredSize().height - 130 - 10));
                            CardLayout cardlayout= (CardLayout)jPanel35.getLayout();
                            if(jPanel37.getComponentCount() == 0){
                                cardlayout.show(jPanel35, "card3");
                                comboBoxModelo.addElement("Fecha");
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(eventoView, estado, "Error",
                                    JOptionPane.ERROR_MESSAGE);
                    }
                });
            descripcion.add(cerrar);
            JTextArea msg= new JTextArea();
            msg.setBackground(Color.WHITE);
            msg.setForeground(Color.BLACK);
            msg.setLineWrap(true);
            msg.setWrapStyleWord(true);
            msg.setEditable(false);
            msg.setText(evento[2].toString());
            JScrollPane scroll= new JScrollPane(msg);
            scroll.setBorder(null);
            scroll.setPreferredSize(new Dimension(465, 70));
            descripcion.add(scroll);
            eventoView.add(descripcion);
            jPanel37.add(eventoView);
            jPanel37.setPreferredSize(new Dimension(800, 
                        jPanel37.getPreferredSize().height + 130 + 10));
            //</editor-fold>
        }
        establecerEsquinasRedondasEnEventos();
        CardLayout cardlayout= (CardLayout)jPanel35.getLayout();
        if(jPanel37.getComponentCount() == 0){
            cardlayout.show(jPanel35, "card3");
            comboBoxModelo.addElement("Fecha");
        }
        else cardlayout.show(jPanel35, "card2");
        //<editor-fold defaultstate="collapsed" desc="en "Historial de eventos" cuando se escoge una fecha el historial se desplaza hacia los eventos de esa fecha">
        for(ActionListener L : jComboBox1.getActionListeners())
            jComboBox1.removeActionListener(L);
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object item= jComboBox1.getSelectedItem();
                if(item == null) return;
                String fechaEscogida= item.toString();
                for(Component hijo : jPanel37.getComponents()){
                    if(hijo instanceof JLabel fecha && fecha.getText().equals(fechaEscogida))
                        jPanel37.scrollRectToVisible(fecha.getBounds());
                }
            }
        });
        //</editor-fold>
    }
    
    public String getNombreAdmin(){return nombreAdmin;}
    
    public void setSuperAdminDefaults(String admin){
        superAdmin= true;
        nombreAdmin= admin;
        reportesAdmin.setNombreAdmin(admin);
        establecerIconoYNombreCuenta();
        actualizarHistorialDeEventos();
    }
    
    private gestionEquipo controladorEq;
    private PlanDeMantenimiento controladorMant;
    private gestionEventos controladorEventos;
    private final ServidorLAN server;
    
    //para controlar el bloqueo o desbloqueo de los hilos de la actualización automática y demás
    private final Object controladorHilos= new Object(); 
    //permite que no haya bloqueos en operaciones concurrentes a la BD
    private boolean Block= false; 
    
    private String busqueda;
    private Thread hilo;
    private final Reportes reportesAdmin;
    private boolean superAdmin;
    private String nombreAdmin;
    private String equipoType;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AcercaDe;
    private javax.swing.JDialog Conexion;
    private javax.swing.JDialog SplashScreen;
    private javax.swing.JDialog TipoEquipo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
