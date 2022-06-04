package co.uniquindio.rummy;

public enum Valores {
    UNO,
    DOS,
    TRES,
    CUATRO,
    CINCO,
    SEIS,
    SIETE,
    OCHO,
    NUEVE,
    DIEZ,
    ONCE,
    DOCE,
    TRECE;

    private final int VALOR;

    private Valores() {
        this.VALOR = this.ordinal() + 1;

    }

    public int getValor() {
        return this.VALOR;
    }

    public String getShortString() {
        return "" + this.VALOR;
    }
}
