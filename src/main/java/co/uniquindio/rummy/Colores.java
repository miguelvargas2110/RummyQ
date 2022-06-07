package co.uniquindio.rummy;

public enum Colores {
    ROJO((char)9632, "\u001B[31m"), //(char)9632 en teoria es un cuadrado mientra que \u001B[31m es rojo
    AZUL((char)9632, "\u001B[34m"), //(char)9632 en teoria es un cuadrado mientra que \u001B[31m es azul
    AMARILLO((char)9632,"\u001B[33m"), //(char)9632 en teoria es un cuadrado mientra que \u001B[31m es amarillo
    NEGRO((char)9632, "\u001B[30m"); //(char)9632 en teoria es un cuadrado mientra que \u001B[31m es negro

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
