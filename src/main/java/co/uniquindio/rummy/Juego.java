package co.uniquindio.rummy;

import java.util.ArrayList;

public class Juego {
    private Tablero tablero;
    private ArrayList<Organizador> organizadores;

    private ArrayList<Pieza> piezas;

    private ArrayList<Pieza> piezasJugador;

    private ArrayList<Pieza> piezasMaquina;

    private Pieza pieza;

    public Juego() {
        organizadores = new ArrayList<>();
        crearPiezas();
        crearOrganizadores();
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

    public ArrayList<Organizador> crearOrganizadores(){
        Organizador organizador;
        piezasJugador = new ArrayList<Pieza>();
        piezasMaquina = new ArrayList<Pieza>();
        for(int i = 0; i < 2; i++){
            if(i == 0){
                for (int j=0; j<=13; j++) {
                    int piezaEscogida = (int) (Math.random() * piezas.size());
                    piezasJugador.add(piezas.get(piezaEscogida));
                    piezas.remove(piezaEscogida);
                }
                organizador = new Organizador();
                organizador.setPiezas(piezasJugador);
                organizadores.add(organizador);
            }else{
                for (int j=0; j<=13; j++) {
                    int piezaEscogida = (int) (Math.random() * piezas.size());
                    piezasMaquina.add(piezas.get(piezaEscogida));
                    piezas.remove(piezaEscogida);
                }
                organizador = new Organizador();
                organizador.setPiezas(piezasMaquina);
                organizadores.add(organizador);
            }
        }
        return organizadores;
    }

    public ArrayList<Pieza> crearPiezas() {
        this.piezas = new ArrayList<Pieza>();
        for(int i = 0; i < 2; i++){
            for(Colores c : Colores.values()){
                for(Valores v : Valores.values()){
                    this.pieza = new Pieza(c, v);
                    this.piezas.add(pieza);
                }
            }
        }
        return this.piezas;
    }

    public void tomarPieza (ArrayList<Pieza> piezasJugador, ArrayList<Pieza> piezas){
        int piezaEscogida= (int)(Math.random()*piezas.size());
        piezasJugador.add (piezas.get(piezaEscogida));
        piezas.remove(piezaEscogida);
    }

    public ArrayList<Pieza> getPiezas(){
        return this.piezas;
    }
}
