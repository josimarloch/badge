/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.net.loch.badge.dao;

/**
 *
 * @author Josimar
 */


import br.net.loch.badge.beans.Carteirinha;
import br.net.loch.badge.beans.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.sqlite.JDBC;



/**
 *
 * @author josimar
 */
public class HibernateConfigurationRemoto {
         private static final String userName = "lochn162_badge";
    private static final String password = "lochn162_badge";
    private static final String dataBase = "lochn162_badge";
    private static final String host = "loch.net.br";
    

    private static Configuration cfg = null;
    private static SessionFactory factory = null;

    public static SessionFactory getSessionFactory() {
        if (cfg == null) {
            cfg = new Configuration();
               cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            cfg.setProperty("hibernate.connection.username", userName);
            cfg.setProperty("hibernate.connection.password", password);
            cfg.setProperty("hibernate.connection.url", "jdbc:mysql://"+host+":3306/"+dataBase);
            //cfg.setProperty("hibernate.connection.url", "jdbc:mysql://ec2-23-21-211-172.compute-1.amazonaws.com:3306/"+dataBase);
            cfg.setProperty("hibernate.show_sql", "true");
            cfg.setProperty("hibernate.format_sql", "true");
            cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
            cfg.setProperty("hibernate.connection.autocommit", "true");
            // cfg.setProperty("hibernate.hbm2ddl.auto", "create");
         
            cfg.addAnnotatedClass(Usuario.class); // classes para serem mapeadas
            cfg.addAnnotatedClass(Carteirinha.class); // classes para serem mapeadas
           

            factory = cfg.buildSessionFactory();// construindo uma fabrica de sessão
        }
        return factory;
    }

    public static void createSchema() {
        getSessionFactory().close();
        org.hibernate.tool.hbm2ddl.SchemaExport schemaEx = new SchemaExport(cfg);
        schemaEx.create(true, true);
    }
    
    public static void checkAndCreateSchema() {
        try {
          //  new DaoCidade().listar();
        } catch (Exception e) {
            // as tabelas nao existem, tenta criar
            HibernateConfigurationRemoto.createSchema();
        }
    }
    
    public static void main(String[] args) {
        HibernateConfigurationRemoto.createSchema();
    }
}
