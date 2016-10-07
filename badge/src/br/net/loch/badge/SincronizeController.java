/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge;

import br.net.loch.badge.dao.DaoCarteirinha;
import br.net.loch.badge.dao.DaoCarteirinhaRemota;
import br.net.loch.badge.sincronizacao.Sincronizacao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;
import javafx.scene.control.Label;

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
    @FXML
    Label lbStatus;
    @FXML
    Label lbLocal;
    @FXML
    Label lbRemoto;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // checaStstus();
    }

    public void checaStstus() {
        DaoCarteirinhaRemota dcr = new DaoCarteirinhaRemota();
        DaoCarteirinha dcl = new DaoCarteirinha();
        Long totalL;
        Long totalR;
        String status = "";
        try {
            totalR = dcr.getTotal();
            lbRemoto.setText(""+totalR);
            status+="Servidor Remoto Ok";
        } catch (Exception e) {
            lbRemoto.setText("Erro Conexão Servidor remoto");
            status+=" - Erro No servisor Remoto";

        }
        try {
            totalL = dcl.getTotal();
            lbLocal.setText(""+totalL);
            status+=" - Servidor Local Ok";
        } catch (Exception e) {
            status+=" - Erro No servisor local";
            lbLocal.setText("Erro AO OBTER DADOS LOCAIS");

        }
        lbStatus.setText(status);
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

        try {
            sinc.geraSincronizacaoServidorCliente();
            int ns = sinc.geraSincronizacaoServidorCliente();
            JOptionPane.showMessageDialog(null, "Sincronização concluida, " + ns + " carteirinhas Resincronizadas Para o banco Local");
            btSCVC.setDisable(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de Sincronização? " + e);
            btSCVC.setDisable(false);

        }
    }

}
