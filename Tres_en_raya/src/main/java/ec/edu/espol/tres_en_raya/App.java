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
          String[] imagesTempTrad ={"tradicional1.png","tradicional2.png","tradicional3.png","tradicional4.png","tradicional5.png","tradicional6.png"};
         CircularLinkedList<String> imagenesTrad = new CircularLinkedList<>(imagesTempTrad);
        System.out.println("get");
        System.out.println(imagenesTrad.size());
        System.out.println(imagesTempTrad.length);
        for(int i = 0; i<imagenesTrad.size(); i++){
            
            System.out.println(imagenesTrad.get(i));
        }
        launch();
         
    }

}