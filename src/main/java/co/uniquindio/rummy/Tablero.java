package co.uniquindio.rummy;

import java.util.ArrayList;

public class Tablero {
    private int tamano;
    private ArrayList<Escalera> escaleras;
    private ArrayList<Terna> ternas;

    public Tablero(int tamano) {
        this.tamano = tamano;
        escaleras = new ArrayList<>();
        ternas = new ArrayList<>();
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public ArrayList<Escalera> getEscaleras() {
        return escaleras;
    }

    public void setEscaleras(ArrayList<Escalera> escaleras) {
        this.escaleras = escaleras;
    }

    public ArrayList<Terna> getTernas() {
        return ternas;
    }

    public void setTernas(ArrayList<Terna> ternas) {
        this.ternas = ternas;
    }
}
