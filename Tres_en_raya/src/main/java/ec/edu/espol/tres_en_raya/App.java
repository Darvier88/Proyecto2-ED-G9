package ec.edu.espol.tres_en_raya;

import ec.edu.espol.TDAs.CircularLinkedList;
import ec.edu.espol.model.Jugador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Menu_Principal"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
//        Jugador j1 = new Jugador(1, "Apolo", "X");
//        Jugador j2 = new Jugador(2, "Fernando", "O");
//        Jugador j3 = new Jugador(3, "Hera", "X");
//        Jugador j4 = new Jugador(4, "Zeus", "O");
//        Jugador j5 = new Jugador(5, "Atenea", "X");
//        Jugador j6 = new Jugador(6, "Poseidón", "O");
//        Jugador j7 = new Jugador(7, "Afrodita", "X");
//        Jugador j8 = new Jugador(8, "Ares", "O");
//        Jugador j9 = new Jugador(9, "Cronos", "O");
//        Comparator<Jugador> cmp = new Comparator<>() {
//            @Override
//            public int compare(Jugador o1, Jugador o2) {
//                return o1.getId() - o2.getId();
//            }
//        };
//        CircularLinkedList<Jugador> listJ = new CircularLinkedList<>();
//        listJ.addLast(j1);
//        listJ.addLast(j2);
//        listJ.addLast(j3);
//        listJ.addLast(j4);
//        listJ.addLast(j5);
//        listJ.addLast(j6);
//        listJ.addLast(j7);
//        listJ.addLast(j8);
//        Iterator<Jugador> it = listJ.iteratorDo();
//        int c = 0;
//        System.out.println(listJ.set(4, j9));
//        System.out.println("---------");
//       for(Jugador j: listJ){
//           System.out.println(j.toString());
//       }
       launch();
         
    }

}