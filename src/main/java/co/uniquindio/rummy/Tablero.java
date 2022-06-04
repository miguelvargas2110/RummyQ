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

    public ArrayList<Pieza> crearPiezas(){
        ArrayList<Pieza> piezas = new ArrayList<>();
        int color = 0;
        int j = 0;
        Pieza pieza;
        for(int i = 0; i < 13; i++){
            if(color == 0){
                pieza = new Pieza(TipoColor.ROJO, i + 1);
            } else if (color == 1) {
                pieza = new Pieza(TipoColor.AMARILLO, i + 1);
            } else if (color == 2) {
                pieza = new Pieza(TipoColor.AZUL, i + 1);
            }
            else{
                pieza = new Pieza(TipoColor.NEGRO, i + 1);
            }

            if(i == 12){
                i = 0;
                color += 1;
            }
            piezas.set(j, pieza);
            j++;
        }
        return piezas;
    }
}
