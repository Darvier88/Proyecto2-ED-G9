/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Dado;
import ec.edu.espol.model.Jugador;
import ec.edu.espol.model.Resultado;
import ec.edu.espol.model.ResultadoDado;
import ec.edu.espol.model.TipoResul;
import ec.edu.espol.model.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ASUS VIVOBOOK PRO
 */
public class Lanzada_dadosController implements Initializable {

    @FXML
    private StackPane player;
    @FXML
    private ImageView dado;
    private Dado dice;
    private Jugador p1;
    private Jugador p2;
    private int lanzadas=2;
    @FXML
    private Label mensaje;
    private Resultado r;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    public void inicializar(Jugador j1,Jugador j2,Resultado r){
        this.r=r;
        p1=j1;
        p2=j2;
        dice=new Dado();
    }
    @FXML
    private void lanzar(MouseEvent event) {
        if (lanzadas>0&&!dice.isLanzado()) {
            // Lanzar el dado
            ResultadoDado resultado = dice.lanzar();

            // Establecer el GIF durante 1 segundo
            dado.setImage(new Image(dice.getMov())); // Reemplaza con la ruta correcta
            

            // Configurar un retraso de 1 segundo antes de mostrar el resultado
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                mostrarResultado(resultado.getLado(), resultado.getNumero());
                if(dice.repetirTirada(p1, p2)){
                    lanzadas++;
                    dice.reiniciar();
                    Util.mostrarMensaje("Ha ocurrido un empate, jugador 2 repita su lanzamiento", "Numeros iguales");
                    return;
                }
                dice.reiniciar();
                cambiarMensaje();

                // Verificar si todas las lanzadas han sido realizadas
                if (lanzadas == 0) {
                    // Configurar un retraso antes de cambiar de página
                    PauseTransition cambioDePagina = new PauseTransition(Duration.seconds(1));
                    cambioDePagina.setOnFinished(ev -> {
                        try {
                            jugar(event);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    cambioDePagina.play();
                }
            });
            pause.play();
        }
    }
    private void mostrarResultado(String resultado,int numero) {
        // Mostrar el resultado obtenido después del retraso de 1 segundo
        dado.setImage(new Image(resultado));
        if(lanzadas==2){
            p1.setDado(numero);
        }
        else{
            p2.setDado(numero);
        }
        lanzadas--;
    }
    private void cambiarMensaje(){
        String msj = mensaje.getText();
        char og = msj.charAt(9);
        char nuevo = '2';
        String newMsj = msj.substring(0,8)+nuevo+msj.substring(9);
        mensaje.setText(newMsj);
    }
    private void jugar(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/tres_en_raya/Tablero_3_en_raya.fxml"));
        Parent tableroParent = loader.load();
        Scene tableroScene = new Scene(tableroParent,680,480);
        Tablero_3_en_rayaController tableroController = loader.getController();
        tableroController.inicializar(p1, p2,r);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableroScene);
        window.show();
    }
}
