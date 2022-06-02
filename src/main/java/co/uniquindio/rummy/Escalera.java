package co.uniquindio.rummy;

import java.util.ArrayList;

public class Escalera {
    private ArrayList<Pieza> piezas;

    public Escalera() {
        piezas = new ArrayList<>();
    }

    public ArrayList<Pieza> getPiezas() {
        return piezas;
    }

    public void setPiezas(ArrayList<Pieza> piezas) {
        this.piezas = piezas;
    }
}
