/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
    
    public static void mostrarMensaje(String mensaje,String title, String header) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(title);
        alerta.setHeaderText(header);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    
}
