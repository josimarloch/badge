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
import java.io.IOException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import javax.swing.JOptionPane;

/**
 *
 * @author josimar
 */
public class HibernateConfigurationRemoto {

//    private static String userName = "lochn162_badge";
//    private static String password = "lochn162_badge";
//    private static String dataBase = "lochn162_badge";
//    private static String host = "loch.net.br";
    private static Configuration cfg = null;
    private static SessionFactory factory = null;

    public static SessionFactory getSessionFactory() {
        if (cfg == null) {
            DbConfig config = null;
            try {
            config = Config.getConfig();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERRO ao ler configurações do banco de dados remoto: "+e);
            }

        cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        cfg.setProperty("hibernate.connection.username", config.getUser());
        cfg.setProperty("hibernate.connection.password", config.getPassword());
        cfg.setProperty("hibernate.connection.url", "jdbc:mysql://" + config.getHost() + ":3306/" + config.getDb());
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
    return factory ;
}

public static void createSchema() throws IOException {
        getSessionFactory().close();
        org.hibernate.tool.hbm2ddl.SchemaExport schemaEx = new SchemaExport(cfg);
        schemaEx.create(true, true);
    }

    public static void checkAndCreateSchema() throws IOException {
        try {
            //  new DaoCidade().listar();
        } catch (Exception e) {
            // as tabelas nao existem, tenta criar
            HibernateConfigurationRemoto.createSchema();
        }
    }

    public static void main(String[] args) throws IOException {
        HibernateConfigurationRemoto.createSchema();
    }
}
