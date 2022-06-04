package co.uniquindio.rummy;

public class Pieza implements Comparable<Pieza> {

    private Colores color;
    private Valores valor;

    public Pieza(Colores color, Valores valor) {
        this.color = color;
        this.valor = valor;
    }

    public Colores getColor() {
        return color;
    }

    public Valores getValor() {
        return valor;
    }

    public String toString(){
        String shortRankString = this.valor.getShortString();
        char suit = this.color.getSimbolo();
        String color = this.color.getColor();
        String defaultColor = "\u001B[0m";

        return color + shortRankString + suit + defaultColor;
    }
    public int compareTo(Pieza otraPieza) {
        if (this.valor.getValor() < otraPieza.valor.getValor()) {
            //lower ranks at the front
            return -1;
        } else if (this.valor.getValor() > otraPieza.valor.getValor()) {
            //higher ranks at the back
            return 1;
        } else {
            return 0;
        }
    }
}
