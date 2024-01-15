/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.TDAs.Tree;
import ec.edu.espol.model.GamePhase;
import static ec.edu.espol.model.GamePhase.STANDBY;
import ec.edu.espol.model.Jugador;
import ec.edu.espol.model.Resultado;
import ec.edu.espol.model.Simbolo;
import ec.edu.espol.model.TipoResul;
import ec.edu.espol.model.Util;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

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
    private int turno=0;
    private ImageView[][] imageViews = new ImageView[3][3];
    private Button[][] buttons = new Button[3][3];
    private int currentRow, currentCol;
    private GamePhase currentPhase = STANDBY;
    private Simbolo ficha;
    private int anchoIm = 80;
    private int altoIm = 80;
    private Resultado r;
    private boolean victory;
    private boolean empate;
    @FXML
    private GridPane gp;
    @FXML
    private VBox p1;
    @FXML
    private VBox p2;
    @FXML
    private Button bTerminarJ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            int s=1;
            int s2=0;
            if(s==1){
                s2=2;
            }
            else{
                s2=1;
            }
            int[][] matIni= new int[3][3]; //supongamos ta esta vacio
            
            
            Tree<int[][]> juego= new Tree(matIni);
            
//            juego.toString_();
            int[][] copia=matIni.clone();
            
            llenarTree_Nivel_1(juego, s);
            juego.toString_();
            for (int i = 0; i < juego.getRootNode().getChildren().size(); i++) {
                int[][] copia2= juego.getRootNode().getChildren().get(i).getRoot().clone(); // suelta matriz
                llenarTree_Nivel_2(juego.getRootNode().getChildren().get(i), copia2, s2); // por cada arbol de matriz se va llenando el nivel 2
                System.out.println("matriz: " + i);
                juego.getRootNode().getChildren().get(i).toString_();
                System.out.println("******************************");
            }
            
//            // llenar con min y max
//            int[] ind= this.llenarMinimax(juego);
            
            // TODO
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
    public void inicializar( Jugador p1,Jugador p2,Resultado r){
        this.r=r;
        j1=p1;
        j2=p2;
        System.out.println("SIMBOLO J1: " + j1.getTipoSimbolo());
        System.out.println("SIMBOLO J2: " + j2.getTipoSimbolo());
        this.compararNum();
        turno++;
        visualizarTurno(turno);
        inicializarTablero();
        inicializarResultado(j1.getPuntuacion(),j2.getPuntuacion());
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
    
    private boolean verificarJ1Primero(){
        //retorna true cuando el valor del dado de j1 es mayor a j2, verificando que j1 es primero
        //retorna false cuando el valor del dado de j1 es menor a j2, verificando que j2 es primero
        if(j1.getDado()>=j2.getDado()){
            return true;
        }
            return false;
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
    private void reiniciarTablero(int pJ1,int pJ2){
        inicializarTablero();
        inicializarResultado(pJ1,pJ2);
    }
    private void inicializarResultado(int puntJ1,int puntJ2){
        p1.getChildren().clear();
        p2.getChildren().clear();
        TipoResul resul = r.getTipoResul();
        if(resul.equals(TipoResul.PorVidas)){
            Label lb1 = new Label();
            lb1.setFont(new Font(24));
            lb1.setTextFill(Color.WHITE);
            lb1.setText("J1");
            StackPane root = new StackPane();
            root.getChildren().add(lb1);
            StackPane.setAlignment(lb1, javafx.geometry.Pos.CENTER);
            Label lb2 = new Label();
            lb2.setFont(new Font(24));
            lb2.setTextFill(Color.WHITE);
            lb2.setText("J2");
            StackPane root2 = new StackPane();
            root2.getChildren().add(lb2);
            StackPane.setAlignment(lb2, javafx.geometry.Pos.CENTER);
            p1.getChildren().add(root);
            p2.getChildren().add(root2);
            for(int i=0; i<puntJ1;i++){
                StackPane sp = new StackPane();
                Image im = new Image("ec/edu/espol/images/corazon.png");
                ImageView iv =new ImageView(im);
                iv.setFitHeight(63);
                iv.setFitWidth(54);
                sp.getChildren().add(iv);
                p1.getChildren().add(sp);
            }
            for(int i=0;i<puntJ2;i++){
                StackPane sp2=new StackPane();
                Image im = new Image("ec/edu/espol/images/corazon.png");
                ImageView iv2 = new ImageView(im);
                iv2.setFitHeight(63);
                iv2.setFitWidth(54);
                sp2.getChildren().add(iv2);
                p2.getChildren().add(sp2);
            }
        }
        else{
           Label lb1 = new Label();
           lb1.setPrefHeight(115);
           Label lb2 = new Label();
           lb2.setPrefHeight(115);
           p1.getChildren().add(lb1);
           p2.getChildren().add(lb2);
           StackPane root = new StackPane();
           StackPane root2 = new StackPane();
           Label lb3 = new Label();
           lb3.setFont(new Font(36));
           lb3.setTextFill(Color.WHITE);
           lb3.setText("J1");
           root.getChildren().add(lb3);
           StackPane.setAlignment(lb3, javafx.geometry.Pos.CENTER);
           Label lb4 = new Label();
           lb4.setFont(new Font(36));
           lb4.setTextFill(Color.WHITE);
           lb4.setText("J2");
           root2.getChildren().add(lb4);
           StackPane.setAlignment(lb4, javafx.geometry.Pos.CENTER);
           p1.getChildren().add(root);
           p2.getChildren().add(root2);
           Label puntuacion = new Label();
           puntuacion.setFont(new Font(36));
           puntuacion.setTextFill(Color.WHITE);
           puntuacion.setText(""+puntJ1);
           StackPane root3 = new StackPane();
           root3.getChildren().add(puntuacion);
           StackPane.setAlignment(puntuacion, Pos.CENTER);
           p1.getChildren().add(root3);
           Label puntuacion2 = new Label();
           puntuacion2.setFont(new Font(36));
           puntuacion2.setTextFill(Color.WHITE);
           puntuacion2.setText(""+puntJ2);
           StackPane root4 = new StackPane();
           root4.getChildren().add(puntuacion2);
           StackPane.setAlignment(puntuacion2, Pos.CENTER);
           p2.getChildren().add(root4);
        }
    }
    private void ponerSimbolo(MouseEvent event) {
        Button b = (Button) event.getSource();
        ImageView iv = (ImageView) b.getGraphic();
        switch(currentPhase){
            case STANDBY:
                asignarJActual(turno);
                fichaActual = asignarSimbolo(actual);
                currentPhase=GamePhase.PUT;
            case PUT:
                boolean estado = ponerFicha(iv);
                System.out.println(estado);
                if(estado){
                    currentPhase=GamePhase.END;
                }
                else{
                    break;
                }
                victory=tresEnRaya();
                empate=empate();
            case END:
                if(!victory&&!empate){
                    this.iniciarNuevoTurno();
                    visualizarTurno(turno);
                    currentPhase= GamePhase.STANDBY;
                }
                else if(empate){
                    Util.mostrarMensaje("El resultado del set es: Empate", "Empate","Empate");
                    empatePunt();
                    if(emp()){
                        break;
                    }
                    else if(victoria()){
                        break;
                    }
                    currentPhase= GamePhase.STANDBY;
                }
                else if(victory){
                    modificarPuntuacion(actual,cmp);
                    Util.mostrarMensaje("El resultado del set es: Victoria para " + actual.getId() + ", has ganado la partida.", "Ganador","Ganador");
                    if(victoria()){
                        break;
                    }
                    currentPhase= GamePhase.STANDBY;
                }
        }
        
    }
    public Comparator<Jugador> cmp = new Comparator<>() {
        @Override
        public int compare(Jugador o1, Jugador o2) {
            return o1.getId()-o2.getId();
        }
    };
    private void empatePunt(){
        if(r.getTipoResul().equals(TipoResul.PorPuntaje)){
            primero.sumarPuntuacion(1);
            segundo.sumarPuntuacion(1);
            if(verificarJ1Primero()){
                reiniciarTablero(primero.getPuntuacion(), segundo.getPuntuacion());
            }
            else{
                reiniciarTablero(segundo.getPuntuacion(), primero.getPuntuacion());
            }
        }
        else{
            if(verificarJ1Primero()){
                reiniciarTablero(primero.getPuntuacion(), segundo.getPuntuacion());
            }
            else{
                reiniciarTablero(segundo.getPuntuacion(), primero.getPuntuacion());
            }
        }
    }
    private boolean victoria(){
        if(r.getTipoResul().equals(TipoResul.PorVidas)){
            if(primero.getPuntuacion()==0 || segundo.getPuntuacion()==0){
                Util.mostrarMensaje("Jugador "+actual.getId()+" has ganado el juego","Victoria","Victoria");
                return true;
            }
        }
        else{
            if(primero.getPuntuacion()==r.getCantidad()||segundo.getPuntuacion()==r.getCantidad()){
                Util.mostrarMensaje("Jugador "+actual.getId()+" has ganado el juego","Victoria","Victoria");
                String msj1="El jugador "+primero.getId()+" ha obtenido: "+primero.getPuntuacion();
                String msj2 ="El jugador "+segundo.getId()+" ha obtenido: "+segundo.getPuntuacion();
                Util.mostrarMensaje(msj1+"\n"+msj2,"Puntuación","Puntuación");
                return true;
            }
        }
        return false;
    }
    private boolean emp(){
        if(r.getTipoResul().equals(TipoResul.PorPuntaje)){
           if(primero.getPuntuacion()==r.getCantidad()&& segundo.getPuntuacion()==r.getCantidad()){
               Util.mostrarMensaje("Habeis empatado","Empate","Empate");
               return true;
           }
        }
       return false;
    }
    private void modificarPuntuacion(Jugador j,Comparator cmp){
        if(cmp.compare(primero, j)==0 && primero.getId()==1){
            if(r.getTipoResul().equals(TipoResul.PorVidas)){
                segundo.restarPuntuacion(1);
                reiniciarTablero(primero.getPuntuacion(), segundo.getPuntuacion());
            }
            else if(r.getTipoResul().equals(TipoResul.PorPuntaje)){
                primero.sumarPuntuacion(1);
                reiniciarTablero(primero.getPuntuacion(), segundo.getPuntuacion());
            }
        }
        else if(cmp.compare(primero, j)==0 && primero.getId()==2){
            if(r.getTipoResul().equals(TipoResul.PorVidas)){
                segundo.restarPuntuacion(1);
                reiniciarTablero(segundo.getPuntuacion(), primero.getPuntuacion());
            }
            else if(r.getTipoResul().equals(TipoResul.PorPuntaje)){
                primero.sumarPuntuacion(1);
                reiniciarTablero(segundo.getPuntuacion(), primero.getPuntuacion());
            }
        }
        else if(cmp.compare(segundo, j)==0 && segundo.getId()==1 ){
            if(r.getTipoResul().equals(TipoResul.PorVidas)){
                primero.restarPuntuacion(1);
                reiniciarTablero(segundo.getPuntuacion(), primero.getPuntuacion());
            }
            else if(r.getTipoResul().equals(TipoResul.PorPuntaje)){
                segundo.sumarPuntuacion(1);
                reiniciarTablero(segundo.getPuntuacion(), primero.getPuntuacion());
            }
        }
        else if(cmp.compare(segundo, j)==0 && segundo.getId()==2){
            if(r.getTipoResul().equals(TipoResul.PorVidas)){
                primero.restarPuntuacion(1);
                reiniciarTablero(primero.getPuntuacion(), segundo.getPuntuacion());
            }
            else if(r.getTipoResul().equals(TipoResul.PorPuntaje)){
                segundo.sumarPuntuacion(1);
                reiniciarTablero(primero.getPuntuacion(), segundo.getPuntuacion());
            }
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
    private boolean ponerFicha(ImageView iv){
        //devuelve false cuando la casilla esta ocupada
        boolean estado= false;
        Simbolo s = (Simbolo) iv.getUserData();
        if(s.getImagen()!=null && s.getJ()!=null){
            System.out.println("Hola");
            Util.mostrarMensaje("Esta casilla ya está ocupada. Por favor, elige otra.", "Casilla Ocupada");
            System.out.println("Hola2");
            estado=false;
        }
        else{
            s.setImagen(fichaActual.getImagen());
            s.setJ(fichaActual.getJ());
            iv.setImage(new Image(fichaActual.getImagen()));
            iv.setFitWidth(anchoIm);
            iv.setFitHeight(altoIm);
            iv.setUserData(s);
            estado=true;
        }
        return estado;
        }
    private void iniciarNuevoTurno(){
        turno++;
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
    
    
    private boolean verificarColumna(){
        //verifica si las tres fichas son iguales en la fila
        for(int i = 0; i<imageViews.length;i++){
            ImageView iv1 = imageViews[i][0];
            ImageView iv2 = imageViews[i][1];
            ImageView iv3 = imageViews[i][2];

            Simbolo s1 = (Simbolo) iv1.getUserData();
            Simbolo s2 = (Simbolo) iv2.getUserData();
            Simbolo s3 = (Simbolo) iv3.getUserData();

            if (verificarSimbolosIguales(s1, s2, s3)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean verificarDiagonalPrincipal(){
        //verifica si las tres fichas son iguales en la diagonal
        ImageView iv1 = imageViews[0][0];
        ImageView iv2 = imageViews[1][1];
        ImageView iv3 = imageViews[2][2];

        Simbolo s1 = (Simbolo) iv1.getUserData();
        Simbolo s2 = (Simbolo) iv2.getUserData();
        Simbolo s3 = (Simbolo) iv3.getUserData();

        if (verificarSimbolosIguales(s1, s2, s3)) {
            return true;
        }
        return false;
    }
    
    private boolean verificarDiagonalSecundaria(){
        //verifica si las tres fichas son iguales en la diagonal
        ImageView iv1 = imageViews[0][2];
        ImageView iv2 = imageViews[1][1];
        ImageView iv3 = imageViews[2][0];

        Simbolo s1 = (Simbolo) iv1.getUserData();
        Simbolo s2 = (Simbolo) iv2.getUserData();
        Simbolo s3 = (Simbolo) iv3.getUserData();

        if (verificarSimbolosIguales(s1, s2, s3)) {
            return true;
        }
        return false;
    }
    
    private boolean verificarFila(){
        //verifica si las tres fichas son iguales en la columna
        for(int i = 0; i<imageViews.length;i++){
            ImageView iv1 = imageViews[0][i];
            ImageView iv2 = imageViews[1][i];
            ImageView iv3 = imageViews[2][i];

            Simbolo s1 = (Simbolo) iv1.getUserData();
            Simbolo s2 = (Simbolo) iv2.getUserData();
            Simbolo s3 = (Simbolo) iv3.getUserData();

            if (verificarSimbolosIguales(s1, s2, s3)) {
                return true;
            }
        }
        return false;
    }
    
    public void inicio() {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/tres_en_raya/Menu_Principal.fxml"));
        Parent menuDeInicioParent = loader.load();
        Scene menuDeInicioScene = new Scene(menuDeInicioParent, 680, 480);
        // Obtiene el Stage desde cualquier nodo de la escena actual
        Stage currentStage = (Stage) sp1.getScene().getWindow();
        // Cerrar la ventana actual
        currentStage.close();
        Stage window = new Stage();
        window.setTitle("Ventana de Inicio");
        window.setScene(menuDeInicioScene);
        window.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de excepciones en caso de error al cargar el FXML
        }
    }
    
    private boolean empate(){
        //verifica si el tablero se ha llenado y ninguno de los dos jugadores ha hecho tres en raya, declarando un empate
        for (int i = 0; i < imageViews.length; i++) {
            for (int j = 0; j < imageViews.length; j++) {
                ImageView iv = imageViews[i][j];
                Simbolo sb = (Simbolo) iv.getUserData();

                if (sb == null || sb.getImagen() == null) {
                    return false; // Si alguna celda no está ocupada, no hay empate
                }
            }
    }
    return true;
    }
    
    public void IA_inicio(Jugador j) throws Exception{
        //x=1 o=2
        //CPU empieza;
        int s=j.getIntsimbolo();
        int s2=0;
        if(s==1){
        s2=2;
        }
        else{
        s2=1;
        }
        int[][] matIni= new int[3][3]; //supongamos ta esta vacio
       
            
        Tree<int[][]> juego= new Tree(matIni);
        
        
        int[][] copia=matIni.clone();

        llenarTree_Nivel_1(juego, s);
        for (int i = 0; i < juego.getRootNode().getChildren().size(); i++) {
            int[][] copia2= juego.getRootNode().getChildren().get(i).getRoot().clone(); // suelta matriz
            llenarTree_Nivel_2(juego.getRootNode().getChildren().get(i), copia2, s2); // por cada arbol de matriz se va llenando el nivel 2
        }
        
        // llenar con min y max
        int[] ind= this.llenarMinimax(juego);
        
        // pasos a seguir se dan en los indices



 
    }
    
    public void IA(Jugador j, int[][] tableroActual) throws Exception{
        int s=j.getIntsimbolo();
        int s2=0;
        if(s==1){
        s2=2;
        }
        else{
        s2=1;
        }
        
        int[][] copia= tableroActual.clone();
        Tree<int[][]> juego= new Tree(tableroActual);
        this.llenarTree_Nivel_1(juego, s);
         for (int i = 0; i < juego.getRootNode().getChildren().size(); i++) {
            int[][] copia2= juego.getRootNode().getChildren().get(i).getRoot().clone(); // suelta matriz
            llenarTree_Nivel_2(juego.getRootNode().getChildren().get(i), copia2, s2); // por cada arbol de matriz se va llenando el nivel 2
        }
        
        int[] ind= this.llenarMinimax(juego);
    }
    
    
    public void llenarTree_Nivel_1(Tree juego, int s){
        int[][] copia = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if(copia[i][k]==0){
                  copia[i][k]=s;
                  int[][] agregar = new int[3][3];
                  agregar[i][k]=s;
                  juego.addChildren(new Tree(agregar));
                }
            }
            
        }
    }
    public void llenarTree_Nivel_2(Tree t, int[][] copia, int s){
        // analiza jug 2
        
        for (int i = 0; i < 3; i++){
            for (int k = 0; k < 3; k++) {
                if(copia[i][k]==0){
//                  copia[i][k]=s;
                  int [][] ag=t.getRoot().clone();
                  ag[i][k]=s;
                  Tree<int[][]> ag2= new Tree(ag);
                  int s2=0;
                  if(s==1){
                    s2=2;
                  }
                  else{
                    s2=1;
                  }
//                  ag2.getRootNode().setUtilidad(getUtility(ag, s2)-getUtility(ag, s));
                  t.addChildren(ag2);
                  
            } }  } 
        
    }
//    public void llenarTree_Nivel_N(Tree t, int[][] copia, int s){
//        for (int i = 0; i < 3; i++){
//            for (int k = 0; k < 3; k++) {
//                if(copia[i][k]==0){
//                  copia[i][k]=s;
//                  int [][] ag=t.getRoot().clone();
//                  ag[i][k]=s;
//                  Tree<int[][]> ag2= new Tree(ag);
//                  int s2=0;
//                  if(s==1){
//                    s2=2;
//                  }
//                  else{
//                    s2=1;
//                  }
//                  ag2.getRootNode().setUtilidad(getUtility(ag, s2)-getUtility(ag, s));
//                  t.addChildren(ag2);
//
//            } }  }
//    }

    public int getUtility(int[][] m, int s){
        
        return this.calcColsU(s, m)+this.calcRowsU(s, m)+this.calcDiagsU(s, m);
        
    
    }

    public int calcRowsU(int s, int[][] m){
        int ret=0;
        
        int iter=0;
        for (int i = 0; i < 3; i++) {
            iter=0;
            for (int j = 0; j < 3; j++) {
                if(m[i][j]==s || m[i][j]==0 )
                    iter++;
            }
            if (iter==3)
                ret++;
            
        }
        return ret;
    }
    
    public int calcColsU(int s, int[][] m){
        int ret=0;
        
        int iter=0;
        for (int i = 0; i < 3; i++) {
            iter=0;
            for (int j = 0; j < 3; j++) {
                if(m[j][i]==s || m[j][i]==0 )
                    iter++;
            }
            if (iter==3)
                ret++;
            
        }
        return ret;
    }
    
    public int calcDiagsU(int s, int[][] m){
        int ret=0;
        
        int iter=0;
        for (int i = 0; i < 3; i++) {
            if(m[i][i]==s || m[i][i]==0 )
                iter++; 
        }
        if (iter==3)
                ret++;
        
        int j=2;
        iter=0;
        for (int i = 0; i < 3; i++) {
            if(m[i][j]==s || m[i][j]==0 )
                iter++; 
                j--;
        }
        if (iter==3)
                ret++;
        return ret;
    }

    public int[] llenarMinimax(Tree<int[][]> juego) throws Exception{
        int[] indices= new int[2];
        if(juego==null || juego.isEmpty()){
            throw new Exception();
        }
        LinkedList<Integer> utilidades1= new LinkedList<>();
        
        for(int i=0; i<juego.getRootNode().getChildren().size();i++){// iterando nivel 1
            LinkedList<Integer> utilidades2= new LinkedList<>();
            for(int k=0; k<juego.getRootNode().getChildren().get(i).getRootNode().getChildren().size();k++){ //iterando nivel 2
                int u=0;
                try {
                    u = juego.getRootNode().getChildren().get(i).getRootNode().getChildren().get(k).getRootNode().getUtilidad();
                } catch (NullPointerException e) {
                    throw new Exception();
                }                            
                utilidades2.add(u);
                if(k==juego.getRootNode().getChildren().get(i).getRootNode().getChildren().size()-1){
                    int min=0;
                    for (int l = 0; l < utilidades2.size(); l++) {
                        if(utilidades2.get(l)<min){
                            min=utilidades2.get(l);
                            indices[1]=l;
                        }
                    }
                    utilidades1.add(min);
                }
            }
            
            
            if(i==juego.getRootNode().getChildren().size()-1){
                int max=0;
                for (int l = 0; l < utilidades1.size(); l++) {
                    if(utilidades1.get(l)>max){
                        max=utilidades1.get(l);
                        indices[0]=l;
                    }
                }
                juego.getRootNode().setUtilidad(max);
                
            }
        }
        return indices;
    }

    @FXML
    public void terminarJuego(MouseEvent event) throws IOException {
        Util.mostrarMensaje("Ha terminado el juego", "Juego terminado");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/tres_en_raya/Modos_de_juego.fxml"));
        Parent Modos_de_juegoParent = loader.load();
        Scene Modos_de_juegoScene = new Scene(Modos_de_juegoParent,680,480);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Modos_de_juegoScene);
        window.show(); 
    }

}

   

