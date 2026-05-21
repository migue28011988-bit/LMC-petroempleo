/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Controlador.OpcionesConfig;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author PC
 */
public class hibernateSessionFactory {
    
    private static SessionFactory factory= CrearSessionFactory();
    
    private static SessionFactory CrearSessionFactory(){
        String urlBD= OpcionesConfig.LocalConfig.getURL();
        String usuarioBD= OpcionesConfig.LocalConfig.getCredenciales()[0];
        String passwordBD= OpcionesConfig.LocalConfig.getCredenciales()[1];
        
        Map<String, Object> conexionConfig= new HashMap<>();
        conexionConfig.put("hibernate.connection.url", urlBD);
        conexionConfig.put("hibernate.connection.username", usuarioBD);
        conexionConfig.put("hibernate.connection.password", passwordBD);
        StandardServiceRegistry ssr= new StandardServiceRegistryBuilder().
                configure("hibernate.cfg.xml").applySettings(conexionConfig).build();
        Metadata meta= new MetadataSources(ssr).buildMetadata();
        return meta.buildSessionFactory();
    }
    
    public static SessionFactory getSessionFactory(){
        return factory;
    }
    
    public static void shutDown(){
        if(factory != null) factory.close();
    }
    
    public static void reOpen(){
        factory= CrearSessionFactory();
    }
}
