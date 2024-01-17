/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

/**
 *
 * @author Jonathan
 */
public class Util {
    public static void mostrarMensaje(String mensaje, String title) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(title);
        alerta.setHeaderText(null); 
        alerta.setContentText(mensaje);
        alerta.show();
    }
    
     public static void mostrarMensajeCPU(String mensaje, String title) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(title);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.show();

        // Configurar un retraso antes de cerrar la alerta
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            alerta.close();
        }));
        timeline.play();
    }
    
    public static void mostrarMensaje(String mensaje,String title, String header) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(title);
        alerta.setHeaderText(header);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    
}
