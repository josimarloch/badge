/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge;

import br.net.loch.badge.sincronizacao.Sincronizacao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author josimar
 */
public class SincronizeController implements Initializable {

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
        Sincronizacao sinc = new Sincronizacao();
        try {

            int ns = sinc.geraSincronizacaoClienteServidor();
            JOptionPane.showMessageDialog(null, "Sincronização concluida, haviam " + ns + " carteirinhas Não Sincronizadas");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de Sincronização? "+e);

        }
    }

    public void sincronizaClienteServidorCliente() {
        Sincronizacao sinc = new Sincronizacao();
        sinc.geraSincronizacaoServidorCliente();
         try {

            int ns =  sinc.geraSincronizacaoServidorCliente();
            JOptionPane.showMessageDialog(null, "Sincronização concluida, " + ns + " carteirinhas Resincronizadas Para o banco Local");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de Sincronização? "+e);

        }
    }

}
