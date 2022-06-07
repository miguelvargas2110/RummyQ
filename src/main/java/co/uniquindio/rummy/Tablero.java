package co.uniquindio.rummy;

import java.util.ArrayList;
import java.util.Collections;

public class Tablero {
    private ArrayList<ArrayList<Pieza>> piezasEnTablero = new ArrayList<ArrayList<Pieza>>();

    public ArrayList<ArrayList<Pieza>> getPiezasEnTablero() {
        return piezasEnTablero;
    }

    //Metodo que organiza y añade al tablero la terna o escalera que el jugador bajo
    public void nuevBajacion(ArrayList<Pieza> bajar){
        Collections.sort(bajar);                   //Colecctions.sort sirve para organizar una lista
        piezasEnTablero.add(bajar);
    }

    //Metodo que añade una pieza del jugador a alguna de las ternas o escaleras que haya en el tablero
    public void añadirABajacion(ArrayList<Pieza> alQueAñade, int posicion){
        ArrayList<Pieza> añadir = piezasEnTablero.remove(posicion);
        for (Pieza p : alQueAñade) {
            añadir.add(p);
        }
        Collections.sort(añadir);               //Colecctions.sort sirve para organizar una lista
        piezasEnTablero.add(añadir);
    }
}
