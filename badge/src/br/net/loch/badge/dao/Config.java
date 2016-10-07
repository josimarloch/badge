/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge.dao;

/**
 *
 * @author josimar
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(
                "src/config/db.properties");
        props.load(file);
        return props;

    }

    public static DbConfig getConfig() throws IOException {
        Properties props = new Properties();
        FileInputStream conf = new FileInputStream(
                "src/config/db.properties");
        System.out.println(props);

        props.load(conf);

        return new DbConfig(props.getProperty("prop.server.host"),
                props.getProperty("prop.server.user"),
                props.getProperty("prop.server.password"),
                props.getProperty("prop.server.db"));

    }

    public static void setConfig(DbConfig config) throws IOException {
        Properties props = new Properties();
        FileOutputStream conf = new FileOutputStream(
                "src/config/db.properties");

        //props.load(conf);
        props.put("prop.server.host", config.getHost());
        props.put("prop.server.user", config.getUser());
        props.put("prop.server.password", config.getPassword());
        props.put("prop.server.db", config.getDb());
        props.store(conf, "Arquivo de config mysql Remoto");

    }

    public static void main(String[] args) throws IOException {
        DbConfig config = getConfig();
        setConfig(config);
    }

}
