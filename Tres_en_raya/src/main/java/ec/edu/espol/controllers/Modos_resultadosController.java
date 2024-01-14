/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.TDAs.CircularLinkedList;
import ec.edu.espol.model.Resultado;
import ec.edu.espol.model.TipoResul;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Modos_resultadosController implements Initializable {
    private String[] imgResultados = {"corazon.png","puntaje.png"};
    private Map<String, String> mapa = new HashMap<>();
    private CircularLinkedList<String> resultados = new CircularLinkedList<>(imgResultados);
    private String[] cantidades= {"1","3","5"} ;
    private CircularLinkedList<String> resultados2 = new CircularLinkedList<>(cantidades);
    private ListIterator<String> it2 = resultados2.listIterator();
    private ListIterator<String> it = resultados.listIterator();
    private ScaleTransition scaleTransition;
    @FXML
    private ImageView leftarrow;
    @FXML
    private ImageView modes;
    @FXML
    private ImageView rightarrow;
    @FXML
    private Label labelmodes;
    @FXML
    private ImageView cantidadL;
    @FXML
    private Label cantidad;
    @FXML
    private ImageView cantidadR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapa.put("corazon.png", "Por vidas");
        mapa.put("puntaje.png", "Por puntos");
    }    

    @FXML
    private void cambiarImgL(MouseEvent event) {
        //System.out.println(it.previous());
        String name = it.previous();
        
        System.out.println(name);
        Image img = new Image("ec/edu/espol/images/"+name);
        modes.setImage(img);
        labelmodes.setText(mapa.get(name));
        System.out.println("    que LLLLL");
    }

    @FXML
    private void restaurarImg(MouseEvent event) {
        scaleTransition.stop(); // Detener la transición actual si está en progreso
        modes.setScaleX(1); // Restablecer la escala en el eje X
        modes.setScaleY(1); // Restablecer la escala en el eje Y
    }

    @FXML
    private void agrandarImg(MouseEvent event) {
        // Escalar la imagen
        scaleTransition = new ScaleTransition(Duration.millis(300), modes);
        scaleTransition.setToX(1.25);
        scaleTransition.setToY(1.25);

        // Reproducir la animación
        scaleTransition.play();
    }

    @FXML
    private void elegido(MouseEvent event) throws IOException {
        String elegido = labelmodes.getText();
        if(elegido.equals("Por vidas")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/tres_en_raya/Modos_de_competencia.fxml"));
            Parent competenciaParent = loader.load();
            Scene competenciaScene = new Scene(competenciaParent,680,480);
            Modos_de_competenciaController competenciasController = loader.getController();
            int c = Integer.parseInt(cantidad.getText());
            Resultado r = new Resultado(TipoResul.PorVidas,c);
            competenciasController.inicializar(r);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(competenciaScene);
            window.show();    
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/tres_en_raya/Modos_de_competencia.fxml"));
            Parent competenciaParent = loader.load();
            Scene competenciaScene = new Scene(competenciaParent,680,480);
            Modos_de_competenciaController competenciasController = loader.getController();
            int c = Integer.parseInt(cantidad.getText());
            Resultado r = new Resultado(TipoResul.PorPuntaje,c);
            competenciasController.inicializar(r);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(competenciaScene);
            window.show();
        }
    }

    @FXML
    private void cambiarImgR(MouseEvent event) {
        String name = it.next();
        Image img = new Image("ec/edu/espol/images/"+name);
        modes.setImage(img);
        labelmodes.setText(mapa.get(name));
    }

    @FXML
    private void cambiarCantL(MouseEvent event) {
        
        String name = it2.previous();
        cantidad.setText(name);
    }

    @FXML
    private void cambiarCantR(MouseEvent event) {
       
        String name = it2.next();
        cantidad.setText(name);
    }

    @FXML
    private void handCambiarImgL(MouseEvent event) {
        leftarrow.setCursor(Cursor.HAND);
        
    }

    @FXML
    private void handCambiarImgR(MouseEvent event) {
        rightarrow.setCursor(Cursor.HAND);
    }

    @FXML
    private void handCambiarCantL(MouseEvent event) {
        this.cantidadL.setCursor(Cursor.HAND);
    }

    @FXML
    private void handCambiarCantR(MouseEvent event) {
        this.cantidadR.setCursor(Cursor.HAND);
    }
    
    
}
