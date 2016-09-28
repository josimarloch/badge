/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge;

import br.net.loch.badge.beans.Carteirinha;
import br.net.loch.badge.dao.DaoCarteirinha;
//import eu.schudt.javafx.controls.calendar.DatePicker;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import util.ImageResizerService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import util.Seriais;

/**
 * FXML Controller class
 *
 * @author josimar
 */
public class CadastroCarteirinhaController {

    @FXML
    TextField txNome;
   // @FXML
   // TextField txDate;
    @FXML
    TextField txCpf;
    @FXML
    TextField txRG;
    @FXML
    Image imgFoto;
    @FXML
    ImageView imgvFoto;
    @FXML
    Button btSelectImg;
    @FXML
    Button btSalvar;
    @FXML
    private DatePicker birthdayDatePicker;

        byte[] byteFoto;
      private int altura;
      private int largura;
        /*
    @FXML
    private void initialize() {
        // Initialize the DatePicker for birthday
        birthdayDatePicker = new DatePicker(new Locale("pt", "BR"));
        birthdayDatePicker.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        birthdayDatePicker.getCalendarView().todayButtonTextProperty().set("Today");
        birthdayDatePicker.getCalendarView().setShowWeeks(false);
        birthdayDatePicker.getStylesheets().add("DatePicker.css");

  // Add DatePicker to grid
    }
        */

    File foto;

    public void resetComponents() {
        btSalvar.setDisable(true);
        txNome.setText("");
    //  birthdayDatePicker.add
        txCpf.setText("");
        txRG.setText("");
        imgFoto = new Image("/br/net/loch/badge/img/semfoto.jpg");
        imgvFoto.setImage(imgFoto);
       

    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    public void carregaFoto() {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        fileChooser.setTitle("Escolher Foto");
        //return fileChooser.showOpenDialog(null);
        foto = fileChooser.showOpenDialog(null);

        if (foto != null) {
            String path = foto.getAbsolutePath().replaceAll("\\\\", "/");
            File temp2 = null;
            try {
                File temp = new File("src/br/net/loch/badge/img/temp.jpg");
                temp2 = new File("src/temp2.jpg");
                //  copyFile(foto, temp);
                ImageResizerService irs = new ImageResizerService(foto);
                this.byteFoto = irs.getNormal(200);
                
                irs.converterArayByteEmArquivo(temp2, byteFoto);
                this.altura =irs.getAltura(temp2);
                this.largura = irs.getLargura(temp2);
                Image novafoto = new Image("file:" + temp2.getCanonicalPath());
                this.imgvFoto.setImage(novafoto);
                //   Bindings.and
                btSalvar.setDisable(false);
            } catch (IOException ex) {
                Logger.getLogger(CadastroCarteirinha.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

        }
    }

    public void salvar() {
            String nome = txNome.getText();
            String cpf = txCpf.getText();
            String rg = txRG.getText();
           // birthdayDatePicker.getValue().
            Date date = new Date(birthdayDatePicker.getValue().toEpochDay());
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
          //  String dateNiver=birthdayDatePicker.getPromptText() ;
            
        if (validaCampos(nome, cpf,rg,byteFoto, null)) {
            Carteirinha c = new Carteirinha();
            c.setNome(txNome.getText());
            c.setDataNacimento(converteData(sd.format(date)));
            c.setCpf(cpf);
            c.setRg(rg);
            c.setAltura(altura);
            c.setLargura(largura);
           // c.setIdade(Integer.parseInt(idade));
            c.setFoto(byteFoto);
                String cpuSerial = Seriais.getCPUSerial();
                c.setCliente(cpuSerial);
            DaoCarteirinha dc = new DaoCarteirinha();
            dc.save(c);
            JOptionPane.showMessageDialog(null, "Carteitinha de " + nome + " salva com sucesso.");
           // lbInfo.setText("Carteitinha de " + nome + " salva com sucesso.");

            System.out.println("Salvo");
            resetComponents();
        } else {
            JOptionPane.showMessageDialog(null, "Erro: Campos não preenchidos.");
        }
    }

    boolean validaCampos(String nome, String cpf,String rg, byte[] foto, Label lbInfo) {
      
        return !nome.trim().equals("") &&foto != null;
    }
    public static Calendar converteData(String data) {
        System.out.println("Formatando data: "+data);
        String[] s = data.split("/");
        try {
            if (s.length == 3) {
                String dia = s[0];
                String mes = s[1];
                String ano = s[2];
                return new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes)-1, Integer.parseInt(dia));
            }
        } catch (NumberFormatException e) {
           return null;
        }
        return null;
    }
}
