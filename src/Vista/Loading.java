/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRootPane;

/**
 *
 * @author PC
 */
public class Loading extends javax.swing.JDialog {
    
    public Loading(Frame parent, boolean modal){
        super(parent, modal);
        JLabel label= new JLabel(new ImageIcon(getClass().getResource("/Iconos/loadingBig.gif")));
        add(label);
        setSize(new Dimension(300, 300));
        setLocationRelativeTo(parent);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
    }
    
    public void execMetodo(Runnable metodo){
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        new Thread(()->{
            metodo.run();
            try{
                while(true){
                    if(isShowing()){
                       dispose(); 
                       break;
                    }
                    else Thread.sleep(1000);
                }
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }).start();
        setVisible(true);
    }
}
