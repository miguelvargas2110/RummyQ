package co.uniquindio.rummy;

import java.util.ArrayList;

public class Organizador {
    private int tamano;
    private ArrayList<Pieza> piezas;
    private ArrayList<Escalera> escaleras;
    private ArrayList<Terna> ternas;

    public Organizador(int tamano) {
        this.tamano = tamano;
        piezas = new ArrayList<>();
        escaleras = new ArrayList<>();
        ternas = new ArrayList<>();
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public ArrayList<Pieza> getPiezas() {
        return piezas;
    }

    public void setPiezas(ArrayList<Pieza> piezas) {
        this.piezas = piezas;
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
