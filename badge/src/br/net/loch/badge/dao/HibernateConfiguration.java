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
public class HibernateConfiguration {
    public static final String userName = "root";
    public static final String password = "grupoaion";
    public static final String dataBase = "scp";
    
    // configuracões somente uma vez 
    // build  valida somente uma vez
    // build  valida somente uma vez
    // Session uma sessão para cada transação ou um conjunto de transações

    private static Configuration cfg = null;
    private static SessionFactory factory = null;

    public static SessionFactory getSessionFactory() {
        if (cfg == null) {
            cfg = new Configuration();
       /*      <property name="hibernate.hbm2ddl.auto" value="update"/>
      <property name="hibernate.show_sql" value="true"/>
      <property name="hibernate.format_sql" value="true"/>
      <property name="javax.persistence.jdbc.driver" value="org.sqlite.JDBC"/>
      <property name="javax.persistence.jdbc.url" value="jdbc:sqlite:badge.db"/>
      <property name="hibernate.dialect" value="br.net.loch.badge.dao.SQLiteDialect"/>
      <property name="javax.persistence.schema-generation.database.action" value="none"/> */
            cfg.setProperty("hibernate.connection.driver", "org.sqlite.JDBC");
          //  cfg.setProperty("hibernate.connection.username", userName);
          //  cfg.setProperty("hibernate.connection.password", password);
            //cfg.setProperty("hibernate.connection.url", "jdbc:mysql://ec2-23-21-211-172.compute-1.amazonaws.com:3306/"+dataBase);
            cfg.setProperty("hibernate.connection.url", "jdbc:sqlite:badge.db");
            cfg.setProperty("hibernate.show_sql", "true");
            cfg.setProperty("hibernate.format_sql", "true");
            cfg.setProperty("hibernate.dialect", "br.net.loch.badge.dao.SQLiteDialect");
            cfg.setProperty("hibernate.connection.autocommit", "true");
            cfg.setProperty("hibernate.current_session_context_class", "thread");
             cfg.setProperty("hibernate.hbm2ddl.auto", "update");
         
            cfg.addAnnotatedClass(Usuario.class); // classes para serem mapeadas
            cfg.addAnnotatedClass(Carteirinha.class); // classes para serem mapeadas
           

            factory = cfg.buildSessionFactory();// construindo uma fabrica de sessão
        }
        return factory;
    }
    //podemos setar o autocomit como true e nao precisa usar begi tansaction

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
            HibernateConfiguration.createSchema();
        }
    }
    
    public static void main(String[] args) {
        HibernateConfiguration.createSchema();
    }
}
