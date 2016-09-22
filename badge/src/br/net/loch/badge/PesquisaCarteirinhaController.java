/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge;

import br.net.loch.badge.beans.Carteirinha;
import br.net.loch.badge.dao.DaoCarteirinha;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author josimar
 */
public class PesquisaCarteirinhaController {

    // @() xmlns:fx="http://javafx.com/fxml/1"
    @FXML
    Label lbNome;
    @FXML
    TextField txNome;
    @FXML
    ListView lsView;
    @FXML
    Button btSearch;

    public void geraPesquisa() {
        // lsView = new ListView();
        DaoCarteirinha dc = new DaoCarteirinha();
        ObservableList<String> items = FXCollections.observableArrayList();
        List<Carteirinha> byName = dc.getByName(txNome.getText());
        byName.stream().forEach((carteirinha) -> {
            items.add(carteirinha.getNome());
        });
        lsView.setItems(items);
        System.out.println("Aguarde... pesquisando por " + txNome.getText());
    }
}
