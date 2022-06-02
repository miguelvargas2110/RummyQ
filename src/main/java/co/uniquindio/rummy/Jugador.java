package co.uniquindio.rummy;

public class Jugador {
    private TipoJugador tipo;
    private Organizador organizador;

    public Jugador(TipoJugador tipo, Organizador organizador) {
        this.tipo = tipo;
        this.organizador = organizador;
    }

    public TipoJugador getTipo() {
        return tipo;
    }

    public void setTipo(TipoJugador tipo) {
        this.tipo = tipo;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }
}
