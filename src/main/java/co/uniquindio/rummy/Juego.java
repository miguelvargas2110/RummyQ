package co.uniquindio.rummy;

import java.util.ArrayList;

public class Juego {
    private Tablero tablero;
    private ArrayList<Organizador> organizadores;
    private ArrayList<Jugador> jugadores;

    public Juego(Tablero tablero) {
        this.tablero = tablero;
        organizadores = new ArrayList<>();
        jugadores = new ArrayList<>();
    }

    public Tablero getTablero() {
        return tablero;
    }

    public ArrayList<Organizador> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(ArrayList<Organizador> organizadores) {
        this.organizadores = organizadores;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
