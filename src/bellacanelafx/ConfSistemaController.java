/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bellacanelafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author joao
 */
public class ConfSistemaController implements Initializable {

    @FXML
    private SplitPane painel;
    @FXML
    private JFXButton btsalvar;
    @FXML
    private JFXTextField txcod;
    @FXML
    private JFXTextField txnome;
    @FXML
    private JFXTextField txcep;
    @FXML
    private JFXTextField txendereco;
    @FXML
    private JFXTextField txcidade;
    @FXML
    private JFXTextField txuf;
    @FXML
    private JFXTextField txcnpj;
    @FXML
    private JFXTextField txrazao;
    @FXML
    private JFXTextField txfone;
    @FXML
    private JFXTextField txemail;
    @FXML
    private ImageView imgIcone;
    @FXML
    private JFXColorPicker cpcor1;
    @FXML
    private JFXColorPicker cpcor2;

    /**
     * Initializes the controller class.
     */
    
    private void fadeout(){
        FadeTransition ft = new FadeTransition(Duration.millis(1000), painel);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play(); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fadeout();
    }    

    @FXML
    private void clkBtSalvar(ActionEvent event) {
    }

    @FXML
    private void dgtNome(KeyEvent event) {
    }

    @FXML
    private void dgtEndereco(KeyEvent event) {
    }

    @FXML
    private void dgtCidade(KeyEvent event) {
    }

    @FXML
    private void dgtUF(KeyEvent event) {
    }

    @FXML
    private void dgtRazao(KeyEvent event) {
    }

    @FXML
    private void dgtEmail(KeyEvent event) {
    }

    @FXML
    private void mcEscolherIcone(MouseEvent event) {
    }
    
}
