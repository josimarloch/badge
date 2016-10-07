/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.net.loch.badge;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author josimar
 */
public class CarteirinhaView extends Application {
    
    @Override
    public void start(Stage primaryStage) {
         URL url = getClass().getResource("Carteirinha.fxml");
        Parent fxmlParent;
        try {
            fxmlParent = (Parent) FXMLLoader.load(url);
        primaryStage.setScene(new Scene(fxmlParent,220,314));
        primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(CarteirinhaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
