package co.uniquindio.rummy;

import java.util.ArrayList;
import java.util.Collections;

public class Tablero {
    private ArrayList<ArrayList<Pieza>> piezasEnTablero = new ArrayList<ArrayList<Pieza>>();

    public ArrayList<ArrayList<Pieza>> getPiezasEnTablero() {
        return piezasEnTablero;
    }

    public void nuevBajacion(ArrayList<Pieza> bajar){
        Collections.sort(bajar);
        piezasEnTablero.add(bajar);
    }

    public void añadirABajacion(ArrayList<Pieza> alQueAñade, int posicion){
        ArrayList<Pieza> añadir = piezasEnTablero.remove(posicion);
        for (Pieza p : alQueAñade) {
            añadir.add(p);
        }
        Collections.sort(añadir);
        piezasEnTablero.add(añadir);
    }
}
