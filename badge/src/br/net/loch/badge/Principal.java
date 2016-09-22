/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author josimar
 */
public class Principal extends Application {

    @Override
    public void start(Stage primaryStage) {
    //  public BackgroundImage(Image image,
        //   BackgroundRepeat repeatX,
        //   BackgroundRepeat repeatY,
        //   BackgroundPosition position,
        //  BackgroundSize size);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600, Color.WHITE);

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        // File menu - new, save, exit
        Menu carteirinhaMenu = new Menu("Carteirinha");
        MenuItem newMenuItem = new MenuItem("Nova");
        Stage cadastroCarteirinha = new Stage();
      //  cadastroCarteirinha.isAlwaysOnTop();
        newMenuItem.setOnAction(ActionEvent
                -> new CadastroCarteirinha().start(cadastroCarteirinha));
        //newMenuItem.se
        MenuItem pesquisaMenuItem = new MenuItem("Pesquisar");
      
                     Stage pesquisaCarteirinha = new Stage();
        try {
            pesquisaMenuItem.setOnAction(ActionEvent
                    -> new PesquisaCarteirinhaView().start(pesquisaCarteirinha));
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MenuItem exitMenuItem = new MenuItem("Exit");
        // exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        carteirinhaMenu.getItems().addAll(newMenuItem, pesquisaMenuItem,
                new SeparatorMenuItem(), exitMenuItem);

        Menu configurarMenu = new Menu("Configurar");
        MenuItem mysqlMenuItem = new MenuItem("Mysql");
       Image  img = new Image("/br/net/loch/badge/img/badge.png");
        ImageView imgView = new ImageView(img);
        imgView.setLayoutX(168);
        imgView.setLayoutY(152);

        configurarMenu.getItems().addAll(mysqlMenuItem);

        menuBar.getMenus().addAll(carteirinhaMenu, configurarMenu);
        primaryStage.setTitle("Badge - Sistema de gestão de Carterinhas");
    
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
