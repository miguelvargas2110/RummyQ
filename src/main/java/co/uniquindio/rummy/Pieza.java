package co.uniquindio.rummy;

public class Pieza {

    private TipoColor color;
    private int valor;

    public Pieza(TipoColor color, int valor) {
        this.color = color;
        this.valor = valor;
    }

    public TipoColor getColor() {
        return color;
    }

    public void setColor(TipoColor color) {
        this.color = color;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
