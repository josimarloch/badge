/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge;

import br.net.loch.badge.beans.Carteirinha;
import br.net.loch.badge.temp.Dados;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import java.text.SimpleDateFormat;

/**
 * FXML Controller class
 *
 * @author josimar
 */
public class CarteirinhaController implements Initializable {

    @FXML
    ImageView imgvFoto;
    @FXML
    Label lbNome;
    @FXML
    Label lbID;
    @FXML
    Label lbCPF;
    @FXML
    Label lbRG;
    @FXML
    Label lbDT;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Carteirinha c = (Carteirinha) Dados.get("Carteitinha_foto");
        atualizaImagem(c);
        lbNome.setText(c.getNome());
        lbID.setText(c.getId()+"");
        lbCPF.setText(c.getCpf());
        lbRG.setText(c.getRg());
        lbDT.setText(new SimpleDateFormat("dd/MM/yyyy").format(c.getDataNacimento().getTime()));
    }

    public void atualizaImagem(Carteirinha c) {
        this.imgvFoto.setFitWidth(110);
        this.imgvFoto.setFitHeight(140);
        this.imgvFoto.setImage(convertToJavaFXImage(c.getFoto(), c.getLargura(), c.getAltura()));
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

