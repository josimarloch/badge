/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.net.loch.badge;

import br.net.loch.badge.beans.Carteirinha;
import br.net.loch.badge.dao.DaoCarteirinha;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author josimar
 */
public class CadastroCarteirinhaController {

     @FXML
    TextField txNome;
     @FXML
    TextField txDate;
     @FXML
    TextField txCpf;
     @FXML
    TextField txRg;
     @FXML
      Image imgFoto;
    ImageView imgvFoto;
    
         public void resetComponents() {
        txNome.setText("");
        txDate.setText("");
        imgFoto = new Image("/br/net/loch/badge/img/semfoto.jpg");
        imgvFoto.setImage(imgFoto);       

    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

     public void carregaFoto() {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        fileChooser.setTitle("Escolher Foto");
        //return fileChooser.showOpenDialog(null);
    }

    private void salvar(String nome, String idade, byte[] foto, Label lbInfo) {
        if (validaCampos(nome, idade, foto, lbInfo)) {

            Carteirinha c = new Carteirinha();
            c.setNome(nome);
            c.setIdade(Integer.parseInt(idade));
            c.setFoto(foto);
            DaoCarteirinha dc = new DaoCarteirinha();
            dc.save(c);
            JOptionPane.showMessageDialog(null, "Carteitinha de " + nome + " salva com sucesso.");
            lbInfo.setText("Carteitinha de " + nome + " salva com sucesso.");

            System.out.println("Salvo");
            resetComponents();
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Campos não preenchidos.");
        }
    }

    boolean validaCampos(String nome, String idade, byte[] foto, Label lbInfo) {
        return !nome.trim().equals("") && !idade.trim().equals("") && foto != null;
    }
}
