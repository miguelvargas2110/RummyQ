package co.uniquindio.rummy;

public class JugadorHumano {
    private Organizador organizador;
    public JugadorHumano(Organizador organizador){
        this.organizador = organizador;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }
}
