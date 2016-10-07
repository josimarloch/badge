/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge;

import br.net.loch.badge.beans.Carteirinha;
import br.net.loch.badge.dao.DaoCarteirinha;
import br.net.loch.badge.temp.Dados;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author josimar
 */
public class PesquisaCarteirinhaController {

    // @() xmlns:fx="http://javafx.com/fxml/1"
    @FXML
    AnchorPane apPC;
    @FXML
    Label lbNome;
    @FXML
    TextField txNome;
    @FXML
    ListView lsView;
    @FXML
    Button btSearch;
    @FXML
    ImageView imgvPreview;
    List<Carteirinha> carteirinhas;
    Stage carteirinha;

    public void geraPesquisa() {
        // lsView = new ListView();
        if (txNome.getText().length() > 2) {

            DaoCarteirinha dc = new DaoCarteirinha();
            ObservableList<String> items = FXCollections.observableArrayList();
            carteirinhas = dc.getByName(txNome.getText());
            carteirinhas.stream().forEach((carteirinha) -> {
                items.add(carteirinha.getNome());
            });
            lsView.setItems(items);

            System.out.println("Aguarde... pesquisando por " + txNome.getText());
        }
    }

    public void geraPesquisa2() {
        // lsView = new ListView();
        if (txNome.getText().length() > 0) {

            DaoCarteirinha dc = new DaoCarteirinha();
            ObservableList<String> items = FXCollections.observableArrayList();
            carteirinhas = dc.getByName(txNome.getText());
            carteirinhas.stream().forEach((carteirinha) -> {
                items.add(carteirinha.getNome());
            });
            lsView.setItems(items);

            System.out.println("Aguarde... pesquisando por " + txNome.getText());
        }
    }

    public void atualizaImagem() {
        int index = lsView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Carteirinha c = carteirinhas.get(index);
            this.imgvPreview.setFitWidth(c.getLargura());
            this.imgvPreview.setFitHeight(c.getAltura());
            imgvPreview.setImage(convertToJavaFXImage(c.getFoto(), c.getLargura(), c.getAltura()));
        }
    }

    public void abreCarteirinha() {
        int index = lsView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Carteirinha c = carteirinhas.get(index);
            Dados.put("Carteitinha_foto", c);
            carteirinha = new Stage();
            carteirinha.setTitle("Carteirinha View");
            new CarteirinhaView().start(carteirinha);
        }
    }

    private static Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
        WritableImage image = new WritableImage(width, height);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(raw);
            BufferedImage read = ImageIO.read(bis);
            image = SwingFXUtils.toFXImage(read, null);
        } catch (IOException ex) {
            System.out.println("Deu pau..." + ex);
        }
        return image;
    }

    public void close() {
        // apPC.getParent().getc
    }
}
