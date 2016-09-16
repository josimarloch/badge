/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge;

import br.net.loch.badge.beans.Carteirinha;
import br.net.loch.badge.dao.DaoCarteirinha;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import util.ImageResizerService;

/**
 *
 * @author josimar
 */
public class CadastroCarteirinha extends Application {

    private Stage stage;
    byte[] byteFoto;
    Label lbNome;
    TextField txNome;
    Label lbIdade;
    TextField txIdade;
    Label lbFoto;
    Button btFoto;
    Button btSalvar;
    Button btSair;
    Image img;
    ImageView imgView;
    Label lbInfo;
      AnchorPane pane;
  Scene scene;
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        initComponents();
        btFoto.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        File foto = carregaFoto();
                        if (foto != null) {
                            String path = foto.getAbsolutePath().replaceAll("\\\\", "/");
                            File temp2 = null;
                            try {
                                File temp = new File("src/br/net/loch/badge/img/temp.jpg");
                                temp2 = new File("src/temp2.jpg");
                                //  copyFile(foto, temp);
                                ImageResizerService irs = new ImageResizerService(foto);
                                byteFoto = irs.getNormal(200);
                                irs.converterArayByteEmArquivo(temp2, byteFoto);
                            System.out.println(path);
                            Image novafoto = new Image("file:"+temp2.getCanonicalPath());
                            imgView.setImage(novafoto);
                            } catch (IOException ex) {
                                Logger.getLogger(CadastroCarteirinha.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } else {
                            
                        }

                    }
                }
        );
        btSalvar.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event
                    ) {
                        salvar(txNome.getText(), txIdade.getText(), byteFoto, lbInfo);
                    }
                }
        );
        btSair.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event
                    ) {
                       stage.close();
                     //  primaryStage.show();
                    }
                }
        );
       

    }

    public void initComponents() {
            pane = new AnchorPane();
        pane.setPrefSize(600, 400);

        lbNome = new Label("Nome");
        txNome = new TextField();
        lbIdade = new Label("Idade");
        txIdade = new TextField();
        lbFoto = new Label("Foto");
        btFoto = new Button("Carregar Foto...");
        btSalvar = new Button("Salvar");
        btSair = new Button("Sair");
        img = new Image("/br/net/loch/badge/img/semfoto.jpg");
        imgView = new ImageView(img);
        lbInfo = new Label("");
        lbNome = new Label("Nome");
        lbNome.setLayoutX(50);
        lbNome.setLayoutY(10);
        txNome = new TextField();
        txNome.setLayoutX(100);
        txNome.setLayoutY(10);
        lbIdade.setLayoutX(50);
        lbIdade.setLayoutY(50);
        txIdade.setMaxHeight(2);
        txIdade.setLayoutX(100);
        txIdade.setLayoutY(50);
        lbFoto.setLayoutX(50);
        lbFoto.setLayoutY(90);
        btFoto.setLayoutX(100);
        btFoto.setLayoutY(90);
        btSalvar.setLayoutX(100);
        btSalvar.setLayoutY(300);
        btSair.setLayoutX(250);
        btSair.setLayoutY(300);
        imgView.setLayoutX(350);
        imgView.setLayoutY(20);
        imgView.maxHeight(150);
        imgView.maxWidth(220);
        lbInfo.setLayoutX(50);
        lbInfo.setLayoutY(350);
         pane.getChildren().addAll(txNome,
                txIdade,
                lbIdade,
                lbNome,
                lbFoto,
                btSalvar,
                btFoto,
                imgView,
                btSair);
        scene = new Scene(pane);
         stage.setScene(scene);

        stage.show();
        

    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    private File carregaFoto() {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        fileChooser.setTitle("Escolher Foto");
        return fileChooser.showOpenDialog(stage);
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
        initComponents();
        }else
            JOptionPane.showMessageDialog(null, "Erro: Campos não preenchidos.");
    }
    boolean validaCampos(String nome, String idade, byte[] foto, Label lbInfo) {
        return !nome.trim().equals("")&& !idade.trim().equals("") && foto!=null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void copyFile(File source, File destination) throws IOException {
        if (destination.exists()) {
            destination.delete();
        }
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen()) {
                sourceChannel.close();
            }
            if (destinationChannel != null && destinationChannel.isOpen()) {
                destinationChannel.close();
            }
        }
    }

}
