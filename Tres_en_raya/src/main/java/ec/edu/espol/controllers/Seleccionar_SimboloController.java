/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Jugador;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS VIVOBOOK PRO
 */
public class Seleccionar_SimboloController implements Initializable {

    @FXML
    private StackPane sp1;
    @FXML
    private StackPane sp2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Image im1 = new Image("ec/edu/espol/images/papel_simbolo1.png");
        Image im2 = new Image("ec/edu/espol/images/X.png");
        ImageView iv1 = new ImageView(im1);
        iv1.setFitHeight(350);
        iv1.setFitWidth(350);
        ImageView iv2 = new ImageView(im2);
        iv2.setFitHeight(175);
        iv2.setFitWidth(175);
        iv2.setUserData("X");
        iv2.setOnMouseClicked(event -> {
            try {
                fichaElegida(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        sp1.getChildren().addAll(iv1,iv2);
        Image im3 = new Image("ec/edu/espol/images/papel_simbolo2.png");
        Image im4 = new Image("ec/edu/espol/images/O.png");
        ImageView iv3 = new ImageView(im3);
        iv3.setFitHeight(350);
        iv3.setFitWidth(350);
        ImageView iv4 = new ImageView(im4);
        iv4.setFitHeight(175);
        iv4.setFitWidth(175);
        iv4.setUserData("O");
        sp2.getChildren().addAll(iv3,iv4);
    }    
    private void fichaElegida(MouseEvent event) throws IOException{
        ImageView imageViewClickeado = (ImageView) event.getSource();
        String tipoFicha = (String) imageViewClickeado.getUserData();
        Jugador j1;
        Jugador j2;
        if(tipoFicha.equals("X")){
            j1 = new Jugador(1,tipoFicha);
            j2= new Jugador(2,"O");
        }
        else{
            j1 = new Jugador(1,tipoFicha);
            j2= new Jugador(2,"X");
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/tres_en_raya/Tipo_fichas.fxml"));
        Parent fichasParent = loader.load();
        Scene fichasScene = new Scene(fichasParent,680,480);
        Tipo_fichasController tipoFichasController = loader.getController();
        tipoFichasController.inicializar(j1, j2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fichasScene);
        window.show(); 
    }
}
