/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.net.loch.badge;

import br.net.loch.badge.beans.Carteirinha;
import br.net.loch.badge.dao.DaoCarteirinha;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author josimar
 */
public class PrincipalController {

    @FXML
    TableView tbLast;
    @FXML
    TableColumn tbcId; 
    @FXML
    TableColumn tbcNome; 
    @FXML
    TableColumn tbcIdade; 
    @FXML
    TableColumn tbcStatus; 
    Stage novaCarteirinha;
    Stage pesquisa;
   // TableColumn<Integer, "id"> 
    public void popularTabela(){
          DaoCarteirinha dc = new DaoCarteirinha();
         List<Carteirinha> carteirinhas = dc.listar();
           tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
           tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));
           tbcIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
           tbLast.setItems(FXCollections.observableArrayList(carteirinhas));
           tbLast.getColumns().addAll(tbcId,tbcNome,tbcIdade);
       // byName.stream().forEach((carteirinha) -> {
            //tbcNome.
        //});
    }
    public void abreCadastro(){
        novaCarteirinha = new Stage();
        novaCarteirinha.setTitle("Cadastro de Carteirinha");
        new CadastroCarteirinhaView().start(novaCarteirinha);
    }
    public void abrePesquisa(){
        pesquisa = new Stage();
        pesquisa.setTitle("Pesquisar Carteirinha");
        new PesquisaCarteirinhaView().start(pesquisa);
    }
}
