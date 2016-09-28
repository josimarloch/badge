/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge;

import br.net.loch.badge.dao.Config;
import br.net.loch.badge.dao.DbConfig;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javax.swing.JOptionPane;

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
    public void salvaConfig(){
        try {
            Config.setConfig(new DbConfig(txHost.getText(), txUser.getText(), txPass.getText(), txDB.getText()));
             JOptionPane.showMessageDialog(null, "Configuração Salva Com Sucesso!");
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, "Erro Ao Salvar: "+ex);
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
