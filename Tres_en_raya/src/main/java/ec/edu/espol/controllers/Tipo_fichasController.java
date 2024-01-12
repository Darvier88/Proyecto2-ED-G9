/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Jugador;
import ec.edu.espol.model.Resultado;
import ec.edu.espol.model.TipoResul;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS VIVOBOOK PRO
 */
public class Tipo_fichasController implements Initializable {

    private Label j1;
    private Label j2;
    private Resultado r;
    
    private Jugador p1;
    private Jugador p2;
    @FXML
    private StackPane sp1;
    @FXML
    private StackPane sp2;
    @FXML
    private BorderPane bp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    public void inicializar(Jugador player1,Jugador player2, Resultado r){
        this.r=r;
        Image im1 = new Image("ec/edu/espol/images/trozoJ1.png");
        Image im2 = new Image("ec/edu/espol/images/trozoJ2.png");
        ImageView iv1 = new ImageView(im1);
        ImageView iv2 = new ImageView(im1);
        p1=player1;
        p2=player2;
        j1=new Label("Jugador "+p1.getId()+" "+p1.getTipoSimbolo());
        j1.setFont(new javafx.scene.text.Font("Arial", 36));
        j1.setPrefWidth(200);
        j1.setPrefHeight(54);
        j2=new Label("Jugador "+p2.getId()+" "+p2.getTipoSimbolo());
        j2.setFont(new javafx.scene.text.Font("Arial", 36));
        j2.setPrefWidth(200);
        j2.setPrefHeight(54);
        sp1.getChildren().addAll(iv1,j1);
        sp1.setTranslateY(50);
        sp2.getChildren().addAll(iv2,j2);
        sp2.setTranslateY(-50);
    }

    @FXML
    private void cambiarPag(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/tres_en_raya/Lanzada_dados.fxml"));
        Parent dadosParent = loader.load();
        Scene dadosScene = new Scene(dadosParent,680,480);
        Lanzada_dadosController lanzadaDadosController = loader.getController();
        lanzadaDadosController.inicializar(p1, p2,r);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dadosScene);
        window.show();
    }
    
    
}
