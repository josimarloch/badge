/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge;

import br.net.loch.badge.beans.Carteirinha;
import br.net.loch.badge.dao.CarteirinhaDaoSQLite;
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
import util.ImageResizerService;

/**
 *
 * @author josimar
 */
public class CadastroCarteirinha extends Application {

    private Stage stage;
    byte[] byteFoto;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;

        /*
         StackPane root = new StackPane();

         Scene scene = new Scene(root, 600, 400);
         TextField txtNome = new TextField();
         Label lbNome= new Label("Nome");
         TextField txtSaudacao = new TextField();
         Label lblNome = new Label();
         //lblNome.textProperty().bind(txtNome.textProperty().concat(", ")
         //       .concat(txtSaudacao.textProperty()
         //       ));
         root.getChildren().add(lbNome);
         root.getChildren().add(lblNome);
         //root.getChildren().add(btn);

         stage.setTitle("Cadastrar Carteirinha");
         stage.setScene(scene);
         stage.show();*/
        AnchorPane pane = new AnchorPane();
        pane.setPrefSize(600, 400);
        Label lbNome = new Label("Nome");
        lbNome.setLayoutX(50);
        lbNome.setLayoutY(10);
        TextField txNome = new TextField();
        txNome.setLayoutX(100);
        txNome.setLayoutY(10);
        Label lbIdade = new Label("Idade");
        lbIdade.setLayoutX(50);
        lbIdade.setLayoutY(50);

        TextField txIdade = new TextField();
        txIdade.setMaxHeight(2);
        txIdade.setLayoutX(100);
        txIdade.setLayoutY(50);
        Label lbFoto = new Label("Foto");
        lbFoto.setLayoutX(50);
        lbFoto.setLayoutY(90);
        Button btFoto = new Button("Carregar Foto...");

        btFoto.setLayoutX(100);
        btFoto.setLayoutY(90);

        Button btSalvar = new Button("Salvar");
        btSalvar.setLayoutX(100);
        btSalvar.setLayoutY(200);
        Button btSair = new Button("Sair");
        btSair.setLayoutX(250);
        btSair.setLayoutY(200);
        Image img = new Image("/br/net/loch/badge/img/semfoto.jpg");
        ImageView imgView = new ImageView(img);
        imgView.setLayoutX(350);
        imgView.setLayoutY(20);
        imgView.maxHeight(150);
        imgView.maxWidth(220);

        pane.getChildren().addAll(txNome,
                txIdade,
                lbIdade,
                lbNome,
                lbFoto,
                btSalvar,
                btFoto,
                imgView,
                btSair);

        Scene scene = new Scene(pane);
        btFoto.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                File foto = carregaFoto();
                if(foto==null){
                    
                }else{
                    String path = foto.getAbsolutePath().replaceAll("\\\\", "/");
                    try {
                        File temp = new File("src/br/net/loch/badge/img/temp.jpg");
                        File temp2 = new File("src/br/net/loch/badge/img/temp2.jpg");
                      //  copyFile(foto, temp);
                        ImageResizerService irs = new ImageResizerService(foto);
                       byteFoto =irs.getNormal(200);
                       irs.converterArayByteEmArquivo(temp2,byteFoto);
                    } catch (IOException ex) {
                        Logger.getLogger(CadastroCarteirinha.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(path);
                    Image novafoto = new Image("/br/net/loch/badge/img/temp2.jpg");
                    imgView.setImage(novafoto);
                    
                }
                
            }
        });
        btSalvar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                salvar(txNome.getText(), Integer.parseInt(txIdade.getText()), byteFoto);
             }});
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
    private void salvar(String nome, int idade, byte[] foto) {
        Carteirinha c = new Carteirinha();
        c.setNome(nome);
        c.setIdade(idade);
        c.setFoto(foto);
        CarteirinhaDaoSQLite cd = new CarteirinhaDaoSQLite();
        cd.save(c);
        System.out.println("Salvo");
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
        public static void copyFile(File source, File destination) throws IOException {
        if (destination.exists())
            destination.delete();
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen())
                sourceChannel.close();
            if (destinationChannel != null && destinationChannel.isOpen())
                destinationChannel.close();
       }
   }

}
