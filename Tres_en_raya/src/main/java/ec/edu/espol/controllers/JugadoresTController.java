/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.TDAs.CircularLinkedList;
import ec.edu.espol.model.Jugador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author ASUS VIVOBOOK PRO
 */
public class JugadoresTController implements Initializable {
    private CircularLinkedList<Jugador> jugadores = new CircularLinkedList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public void randomizarSimbolo(){
        
    }
}
