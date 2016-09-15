/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.net.loch.badge;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author josimar
 */
public class TestesTela extends Application {
 /* 
 
 private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Inicializa o root layout (layout base).
     */
  /*  public void initRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TestesTela.class.getResource("FXML.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showPersonOverview() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TestesTela.class.getResource("FXML.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define o person overview dentro do root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    /**
     * Retorna o palco principal.
     * @return
     */
  //  public Stage getPrimaryStage() {
   //     return primaryStage;
   // }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

    //    stage.getIcons().add(new Image(getClass().getResourceAsStream("/IMG/logo_atlx.png")));
        Parent parent = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("fxm.css");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
  //      MENUstage = stage;


    }
}
