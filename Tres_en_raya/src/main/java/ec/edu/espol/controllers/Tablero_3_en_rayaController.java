/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.GamePhase;
import static ec.edu.espol.model.GamePhase.STANDBY;
import ec.edu.espol.model.Jugador;
import ec.edu.espol.model.Simbolo;
import ec.edu.espol.model.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author ASUS VIVOBOOK PRO
 */
public class Tablero_3_en_rayaController implements Initializable {

    private Jugador j1;
    private Jugador j2;
    private Label mensaje;
    private Jugador primero;
    private Jugador segundo;
    private Jugador actual;
    private Simbolo fichaActual;
    @FXML
    private StackPane sp1;
    private int turno=1;
    private ImageView[][] imageViews = new ImageView[3][3];
    private Button[][] buttons = new Button[3][3];
    private int currentRow, currentCol;
    private GamePhase currentPhase = STANDBY;
    private Simbolo ficha;
    private int anchoIm = 80;
    private int altoIm = 80;
    @FXML
    private GridPane gp;
    private Simbolo sbGanador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void inicializar( Jugador p1,Jugador p2){
        j1=p1;
        j2=p2;
        this.compararNum();
        turno++;
        visualizarTurno(turno);
        inicializarTablero();
    }
    private void compararNum(){
        if(j1.getDado()>=j2.getDado()){
            primero=j1;
            segundo=j2;
        }
        else if(j1.getDado()<j2.getDado()){
            primero=j2;
            segundo=j1;
        }
    }
    private void visualizarTurno(int t){
        mensaje= new Label();
        sp1.getChildren().clear();
        if(t%2==1){
           mensaje.setText("Turno de jugador "+primero.getId()); 
        }
        else{
            mensaje.setText("Turno de jugador "+segundo.getId());
        }
        mensaje.setFont(new javafx.scene.text.Font("Arial", 36));
        mensaje.setPrefWidth(325);
        mensaje.setPrefHeight(54);
        mensaje.setTextFill(Color.WHITE);
        sp1.getChildren().addAll(mensaje);
    }
    private void inicializarTablero(){
        gp.getChildren().clear();
        for(int row=0; row<3;row++){
            for(int col=0;col<3;col++){
                Button b = new Button();
                b.setPrefWidth(100);
                b.setPrefHeight(86);
                ImageView iv = new ImageView();
                imageViews[row][col] = iv;
                iv.setUserData(new Simbolo(row,col));
                b.setGraphic(iv);
                b.setStyle("-fx-base: transparent;-fx-focus-color: transparent;-fx-padding: 0px;-fx-margin: 0px");
                if((row==0 && col==0)||(row==0 && col==1)||(row==1 && col==0)||(row==1 && col==1)){
                    b.setStyle("-fx-base: transparent;-fx-border-width: 0 3 3 0; -fx-border-color: transparent white white transparent;-fx-focus-color: transparent;-fx-padding: 0px;-fx-margin: 0px");
                }
                else if((row==0&&col==2)||(row==1 && col==2)){
                    b.setStyle("-fx-base: transparent;-fx-border-width: 0 3 0 0; -fx-border-color: transparent white transparent transparent;-fx-focus-color: transparent;-fx-padding: 0px;-fx-margin: 0px");
                }
                else if((row==2 && col==0)||(row==2 && col==1)){
                    b.setStyle("-fx-base: transparent;-fx-border-width: 0 0 3 0; -fx-border-color: transparent transparent white transparent;-fx-focus-color: transparent;-fx-padding: 0px;-fx-margin: 0px");
                }
                buttons[row][col]=b;
                b.setMinSize(Button.USE_COMPUTED_SIZE, Button.USE_COMPUTED_SIZE);
                b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                b.setOnMouseClicked(this::ponerSimbolo);
                gp.add(b, row, col);
                
            }
        }
    }

    private void ponerSimbolo(MouseEvent event) {
        Button b = (Button) event.getSource();
        ImageView iv = (ImageView) b.getGraphic();
        switch(currentPhase){
            case STANDBY:
                visualizarTurno(turno);
                asignarJActual(turno);
                fichaActual = asignarSimbolo(actual);
                currentPhase=GamePhase.PUT;
            case PUT:
                ponerFicha(iv);
                iniciarNuevoTurno();
        }
        if(tresEnRaya()){
            Util.mostrarMensaje("Ganador", "Ganador");
        }
    }
    private void asignarJActual(int t){
        if(t%2==1){
            this.actual=primero;
        }
        else{
            this.actual=segundo;
        }
    }
    private Simbolo asignarSimbolo(Jugador actual) {
        return new Simbolo("ec/edu/espol/images/"+actual.getTipoSimbolo()+".png",actual);
    }
    private void ponerFicha(ImageView iv){
        Simbolo s = (Simbolo) iv.getUserData();
        if(s.getImagen()!=null && s.getJ()!=null){
            Util.mostrarMensaje("Esta casilla ya está ocupada. Por favor, elige otra.", "Casilla Ocupada");
        }
        else{
            s.setImagen(fichaActual.getImagen());
            s.setJ(fichaActual.getJ());
            iv.setImage(new Image(fichaActual.getImagen()));
            iv.setFitWidth(anchoIm);
            iv.setFitHeight(altoIm);
            iv.setUserData(s);
        }
    }
    private void iniciarNuevoTurno(){
        turno++;
        currentPhase= GamePhase.STANDBY;
    }
    
    private boolean tresEnRaya(){
        //verifica si ha ocurrido un ganador
        return verificarFila() || verificarColumna() || verificarDiagonalPrincipal() || verificarDiagonalSecundaria();
    }
    
    private boolean verificarSimbolosIguales(Simbolo sb1, Simbolo sb2,Simbolo sb3){
        if(sb1 != null && sb2 != null && sb3 != null){
            if(sb1.getImagen() != null && sb2.getImagen() != null && sb3.getImagen() != null)
            return (sb1.getImagen().compareTo(sb2.getImagen()) == 0) && (sb1.getImagen().compareTo(sb3.getImagen()) == 0);
        }
        return false;
    }
    
    private Simbolo obtenerSimbolo(Button btn) {
        return (btn != null) ? (Simbolo) ((ImageView) btn.getGraphic()).getUserData() : null;
    }
    
    private boolean verificarFila(){
        //verifica si las tres fichas son iguales en la fila
        for(int i = 0; i<buttons.length;i++){
            Button btn1 = buttons[i][0];
            Button btn2 = buttons[i][1];
            Button btn3 = buttons[i][2];

            Simbolo sb1 = obtenerSimbolo(btn1);
            Simbolo sb2 = obtenerSimbolo(btn2);
            Simbolo sb3 = obtenerSimbolo(btn3);

            if (verificarSimbolosIguales(sb1, sb2, sb3)) {
                System.out.println(sb1.getImagen());
                System.out.println(sb2.getImagen());
                System.out.println(sb3.getImagen());
                sbGanador = sb1;
                return true;
            }
        }
        return false;
    }
    
    private boolean verificarDiagonalPrincipal(){
        //verifica si las tres fichas son iguales en la diagonal
        Button btn1 = buttons[0][0];
        Button btn2 = buttons[1][1];
        Button btn3 = buttons[2][2];

        Simbolo sb1 = obtenerSimbolo(btn1);
        Simbolo sb2 = obtenerSimbolo(btn2);
        Simbolo sb3 = obtenerSimbolo(btn3);

        if (verificarSimbolosIguales(sb1, sb2, sb3)) {
            sbGanador = sb1;
            return true;
        }
        return false;
    }
    
    private boolean verificarDiagonalSecundaria(){
        //verifica si las tres fichas son iguales en la diagonal
        Button btn1 = buttons[0][2];
        Button btn2 = buttons[1][1];
        Button btn3 = buttons[2][0];

        Simbolo sb1 = obtenerSimbolo(btn1);
        Simbolo sb2 = obtenerSimbolo(btn2);
        Simbolo sb3 = obtenerSimbolo(btn3);

        if (verificarSimbolosIguales(sb1, sb2, sb3)) {
            sbGanador = sb1;
            return true;
        }
        return false;
    }
    
    private boolean verificarColumna(){
        //verifica si las tres fichas son iguales en la columna
        for(int i = 0; i<buttons.length;i++){
            Button btn1 = buttons[0][i];
            Button btn2 = buttons[1][i];
            Button btn3 = buttons[2][i];

            Simbolo sb1 = obtenerSimbolo(btn1);
            Simbolo sb2 = obtenerSimbolo(btn2);
            Simbolo sb3 = obtenerSimbolo(btn3);

            if (verificarSimbolosIguales(sb1, sb2, sb3)) {
                sbGanador = sb1;
                return true;
            }
        }
        return false;
    }
    //Crear la funcion tresEnRaya que verifica si ha ocurrido un ganador
    //Crear la funcion verificarFila,verificarDiagonal,verificarColumna, estas se usaran en la funcion tresEnRaya para ver si alguien gano
    //Crear la funcion empate, la cual ocurre cuando el tablero se ha llenado sin que haya un ganador
    //Poner tanto empate como tresEnRaya antes del switch, se pone de ejemplo la funcion jaqueMate del Ajedrez:
//    if(jaqueMate(imageViews,colorEnemigo)){
//            return;
//        }
//        switch(currentPhase)
}
