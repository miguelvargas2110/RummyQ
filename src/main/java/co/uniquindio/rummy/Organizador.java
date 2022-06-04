package co.uniquindio.rummy;

import java.util.ArrayList;

public class Organizador {
    private ArrayList<Pieza> piezas;

    public Organizador() {
        this.piezas = new ArrayList<Pieza>();
    }

    public ArrayList<Pieza> getPiezas() {
        return this.piezas;
    }

    public Pieza getPieza(int index) {
        return this.piezas.get(index);
    }

    public void anadirPieza(Pieza pieza) {
        this.piezas.add(pieza);
    }
    public void Remove(Pieza pieza) {
        piezas.remove(pieza);
    }

    public void setPiezas(ArrayList<Pieza> piezas){
        this.piezas = piezas;
    }
}
