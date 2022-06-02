package co.uniquindio.rummy;

import java.util.ArrayList;

public class Terna {
    private ArrayList<Pieza> piezas;

    public Terna() {
        piezas = new ArrayList<>();
    }

    public ArrayList<Pieza> getPiezas() {
        return piezas;
    }

    public void setPiezas(ArrayList<Pieza> piezas) {
        this.piezas = piezas;
    }
}
