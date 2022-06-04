package co.uniquindio.rummy;

public enum Colores {
    ROJO((char)9632, "\u001B[31m"),
    AZUL((char)9632, "\u001B[34m"),
    AMARILLO((char)9632,"\u001B[33m"),
    NEGRO((char)9632, "\u001B[30m");

    private char Simbolo;
    private String Color;

    private Colores(char simbolo, String color) {
        this.Simbolo = simbolo;
        this.Color = color;
    }

    public char getSimbolo() {
        return this.Simbolo;
    }

    public String getColor() {
        return this.Color;
    }

}
