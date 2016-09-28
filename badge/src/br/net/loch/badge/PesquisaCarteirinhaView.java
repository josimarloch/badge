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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author josimar
 */
public class PesquisaCarteirinhaView extends Application{

   
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage)  {
        URL url = getClass().getResource("PesquisaCarteirinha.fxml");
        System.out.println(url.getFile());
        Parent fxmlParent;
        try {
            fxmlParent = (Parent) FXMLLoader.load(url);
        primaryStage.setScene(new Scene(fxmlParent,660,440));
        primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(PesquisaCarteirinhaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
