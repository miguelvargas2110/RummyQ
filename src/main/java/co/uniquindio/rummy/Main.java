package co.uniquindio.rummy;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.jugar();
    }
}