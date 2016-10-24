/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge;

import br.net.loch.badge.dao.Config;
import br.net.loch.badge.dao.DaoCarteirinha;
import br.net.loch.badge.dao.DaoCarteirinhaRemota;
import br.net.loch.badge.dao.DbConfig;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javax.swing.JOptionPane;
import org.hibernate.exception.SQLGrammarException;

/**
 * FXML Controller class
 *
 * @author josimar
 */
public class ConfigController implements Initializable {

    @FXML
    TextField txHost;
    @FXML
    TextField txUser;
    @FXML
    PasswordField txPass;
    @FXML
    TextField txDB;
    @FXML
    Label lbRemoto;

    /**
     * Initializes the controller class.
     */
    private DbConfig db;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.db = Config.getConfig();
            txHost.setText(db.getHost());
            txUser.setText(db.getUser());
            txPass.setText(db.getPassword());
            txDB.setText(db.getDb());
        } catch (IOException ex) {
            // JOptionPane.showMessageDialog(null, "Sincronização concluida, haviam " + ns + " carteirinhas Não Sincronizadas");
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvaConfig() {
        try {
            Config.setConfig(new DbConfig(txHost.getText(), txUser.getText(), txPass.getText(), txDB.getText()));
           // JOptionPane.showMessageDialog(null, "Configuração Salva Com Sucesso!");
           lbRemoto.setText("Conf salva com sucesso!\n Reinicie o programa para aplicar a config");
        } catch (IOException ex) {
           lbRemoto.setText("\"Erro Ao Salvar: \" + ex");
            //JOptionPane.showMessageDialog(null, "Erro Ao Salvar: " + ex);
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void checaStstus() {
        DaoCarteirinhaRemota dcr = new DaoCarteirinhaRemota();
        DaoCarteirinha dcl = new DaoCarteirinha();
        Long totalL;
        Long totalR;
        String status = "";
        try {
            totalR = dcr.getTotal();
        //    lbRemoto.setText("" + totalR);
            status += "Servidor Remoto Ok";
     //   } catch (SQLGrammarException e) {
       //     lbRemoto.setText("Erro : " + e.getLocalizedMessage());
      //      System.out.println("SQLGrammarException Tabelas nao criadas#######################" + e);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getLocalizedMessage());
            lbRemoto.setText("Erro: " + e);
            status += " - Erro No servidor Remoto: " + e;

        }
        lbRemoto.setText(status);
    }

}
