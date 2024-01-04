/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.TDAs.CircularLinkedList;
import ec.edu.espol.model.Jugador;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.CheckBox;
/**
 * FXML Controller class
 *
 * @author ASUS VIVOBOOK PRO
 */
public class Modos_de_competenciaController implements Initializable {

    private ImageView tradicional;
    private int indiceImg=0;
    private Map<String, String> mapa = new HashMap<>();
    private String[] imagesTempTrad ={"j_vs_cpu.png","cpu_vs_cpu.png", "j_vs_j.png"};
    private CircularLinkedList<String> imagenesTrad = new CircularLinkedList<>(imagesTempTrad);
    private ListIterator<String> it = imagenesTrad.listIterator();
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
    private CheckBox fichas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapa.put("j_vs_cpu.png", "J vs CPU");
        mapa.put("cpu_vs_cpu.png", "CPU vs CPU");
        mapa.put("j_vs_j.png", "J vs J");
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
    private void cambiarImgR(MouseEvent event){
        String name = it.next();
        Image img = new Image("ec/edu/espol/images/"+name);
        modes.setImage(img);
        labelmodes.setText(mapa.get(name));
    }
    
    @FXML
    private void cambiarImgL(MouseEvent event){
        String name = it.previous();
        Image img = new Image("ec/edu/espol/images/"+name);
        modes.setImage(img);
        labelmodes.setText(mapa.get(name));
    }
    
    @FXML
    private void elegido(MouseEvent event) throws IOException {
        if(!fichas.isSelected()){
            ArrayList<Jugador> players = Jugador.randomizarSimbolos();
            Jugador j1 = players.get(0);
            Jugador j2 = players.get(1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/tres_en_raya/Tipo_fichas.fxml"));
            Parent fichasParent = loader.load();
            Scene fichasScene = new Scene(fichasParent,680,480);
            Tipo_fichasController tipoFichasController = loader.getController();
            tipoFichasController.inicializar(j1, j2);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(fichasScene);
            window.show();    
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/tres_en_raya/Seleccionar_Simbolo.fxml"));
            Parent fichasParent = loader.load();
            Scene fichasScene = new Scene(fichasParent,680,480);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(fichasScene);
            window.show(); 
        }
    }
    
    
}
