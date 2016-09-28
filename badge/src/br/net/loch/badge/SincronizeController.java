/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge;

import br.net.loch.badge.sincronizacao.Sincronizacao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author josimar
 */
public class SincronizeController implements Initializable {

    @FXML
    Button btSCV;
    @FXML
    Button btSCVC;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void sincronizaClienteServidor() {
        btSCV.setDisable(true);
        Sincronizacao sinc = new Sincronizacao();
        try {

            int ns = sinc.geraSincronizacaoClienteServidor();
            JOptionPane.showMessageDialog(null, "Sincronização concluida, haviam " + ns + " carteirinhas Não Sincronizadas");
            btSCV.setDisable(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de Sincronização? " + e);

        }
    }

    public void sincronizaClienteServidorCliente() {
        btSCVC.setDisable(true);
        Sincronizacao sinc = new Sincronizacao();
        sinc.geraSincronizacaoServidorCliente();
        try {

            int ns = sinc.geraSincronizacaoServidorCliente();
            JOptionPane.showMessageDialog(null, "Sincronização concluida, " + ns + " carteirinhas Resincronizadas Para o banco Local");
        btSCVC.setDisable(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de Sincronização? " + e);
        btSCVC.setDisable(false);

        }
    }

}
