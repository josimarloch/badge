/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.net.loch.badge;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
    newMenuItem.setOnAction(ActionEvent -> new CadastroCarteirinha().start(primaryStage));
    //newMenuItem.se
    MenuItem saveMenuItem = new MenuItem("Pesquisar");
    MenuItem exitMenuItem = new MenuItem("Exit");
   // exitMenuItem.setOnAction(actionEvent -> Platform.exit());

    carteirinhaMenu.getItems().addAll(newMenuItem, saveMenuItem,
        new SeparatorMenuItem(), exitMenuItem);

    Menu configurarMenu = new Menu("Configurar");
   MenuItem mysqlMenuItem = new MenuItem("Mysql");
    
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
