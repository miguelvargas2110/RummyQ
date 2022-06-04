package co.uniquindio.rummy;

import java.util.ArrayList;
import java.util.Collections;

public class Tablero {
    private static ArrayList<ArrayList<Pieza>> piezasEnTablero = new ArrayList<ArrayList<Pieza>>();

    public static ArrayList<ArrayList<Pieza>> getPiezasEnTablero() {
        return piezasEnTablero;
    }

    public static void nuevaFusion(ArrayList<Pieza> fusion){
        Collections.sort(fusion);
        piezasEnTablero.add(fusion);

    }

    public static void addToMeld(ArrayList<Pieza> toAdd, int tableCardIndex){
        ArrayList<Pieza> addCardsHere = piezasEnTablero.remove(tableCardIndex);
        for (Pieza p : toAdd) {
            addCardsHere.add(p);
        }
        Collections.sort(addCardsHere);
        piezasEnTablero.add(addCardsHere);
    }
}
