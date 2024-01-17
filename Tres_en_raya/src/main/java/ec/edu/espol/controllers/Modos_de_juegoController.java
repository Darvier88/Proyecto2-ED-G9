/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.TDAs.CircularLinkedList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author ASUS VIVOBOOK PRO
 */
public class Modos_de_juegoController implements Initializable {

    @FXML
    private ImageView tradicional;
    private int indiceImg=0;
    private String[] imagesTempTrad ={"tradicional1.png","tradicional2.png","tradicional3.png","tradicional4.png","tradicional5.png","tradicional6.png"};
    private CircularLinkedList<String> imagenesTrad = new CircularLinkedList<>(imagesTempTrad);
    private ScaleTransition scaleTransition;
    @FXML
    private ImageView tradicional1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    
    private void cambiarImgT(){
        indiceImg=(indiceImg+1)%imagenesTrad.size();
        String nombreImg = imagenesTrad.get(indiceImg);
        Image img = new Image("ec/edu/espol/images/"+nombreImg);
        tradicional.setImage(img);
    }
    private void restaurarOgT(){
        indiceImg=0;
        Image img = new Image("ec/edu/espol/images/"+imagenesTrad.get(indiceImg));
        tradicional.setImage(img);
    }

    @FXML
    private void restaurarImg(MouseEvent event) {
        scaleTransition.stop(); // Detener la transición actual si está en progreso
        restaurarOgT();
        tradicional.setScaleX(1); // Restablecer la escala en el eje X
        tradicional.setScaleY(1); // Restablecer la escala en el eje Y
    }

    @FXML
    private void agrandarImg(MouseEvent event) {
        // Escalar la imagen
        scaleTransition = new ScaleTransition(Duration.millis(300), tradicional);
        scaleTransition.setToX(1.25);
        scaleTransition.setToY(1.25);

       
        // Cambiar de imagen durante la transición de opacidad
        scaleTransition.setOnFinished(e -> cambiarImagenYReproducirAnimacion(scaleTransition));


        // Reproducir la animación
        scaleTransition.play();
    }

    private void cambiarImagenYReproducirAnimacion(ScaleTransition scaleTransition) {
                
        // Cambiar a la siguiente imagen
        cambiarImgT();

        // Reiniciar las transiciones
        scaleTransition.setRate(0.75);

        // Reproducir la animación nuevamente
        scaleTransition.play();
    }
    
    

    @FXML
    private void elegidoT(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/tres_en_raya/Modos_resultados.fxml"));
        Parent jugadoresTParent = loader.load();
        Scene jugadoresTScene = new Scene(jugadoresTParent,680,480);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(jugadoresTScene);
        window.show();
    }
    
    
}
