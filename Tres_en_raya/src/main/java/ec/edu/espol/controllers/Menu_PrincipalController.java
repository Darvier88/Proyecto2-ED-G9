/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author ASUS VIVOBOOK PRO
 */
public class Menu_PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void jugar(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/tres_en_raya/Modos_de_juego.fxml"));
        Parent Modos_de_juegoParent = loader.load();
        Scene Modos_de_juegoScene = new Scene(Modos_de_juegoParent,680,480);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Modos_de_juegoScene);
        window.show();
    }

    @FXML
    public void exitApp(MouseEvent event) {
        Platform.exit();
    }

}
