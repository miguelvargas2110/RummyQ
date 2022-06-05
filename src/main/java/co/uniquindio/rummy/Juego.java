package co.uniquindio.rummy;

import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
    private Tablero tablero = new Tablero();
    private ArrayList<Organizador> organizadores;

    private ArrayList<Pieza> piezas;

    private ArrayList<Pieza> piezasJugador;

    private ArrayList<Pieza> piezasMaquina;

    private Pieza pieza;

    private boolean isGanador = false;

    private int eleccion;

    private Scanner sc = new Scanner(System.in);

    private boolean seBajo = false;

    private boolean turno = true;

    private boolean segundoTurno = false;

    private boolean yaAñadio = false;

    private int tOE;

    private String mensaje;

    private int indice = 0;

    private ArrayList<Pieza> bajar;
    private String mensaje2;

    public Juego() {
        organizadores = new ArrayList<>();
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

    //Metodo para crear los organizadores tanto para la maquina como para el jugador junto con las 14 fichas
    public ArrayList<Organizador> crearOrganizadores() {
        Organizador organizador;
        piezasJugador = new ArrayList<Pieza>();
        piezasMaquina = new ArrayList<Pieza>();
        for (int i = 0; i < 2; i++) {
            //Si es 0 se le agregan las fichas al jugador, de lo contrario se le añaden a la maquina
            if (i == 0) {
                for (int j = 0; j <= 13; j++) {
                    int piezaEscogida = (int) (Math.random() * piezas.size());
                    piezasJugador.add(piezas.get(piezaEscogida));
                    piezas.remove(piezaEscogida);
                }
                organizador = new Organizador();
                organizador.setPiezas(piezasJugador);
                organizadores.add(organizador);
            } else {
                for (int j = 0; j <= 13; j++) {
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

    //metodo para crear todas las 104 piezas
    public ArrayList<Pieza> crearPiezas() {
        this.piezas = new ArrayList<Pieza>();
        for (int i = 0; i < 2; i++) {
            for (Colores c : Colores.values()) {
                for (Valores v : Valores.values()) {
                    this.pieza = new Pieza(c, v);
                    this.piezas.add(pieza);
                }
            }
        }
        return this.piezas;
    }

    //metodo para tomar una pieza cuando el jugador lo necesite
    public void tomarPieza(ArrayList<Pieza> piezasJugador, ArrayList<Pieza> piezas) {
        int piezaEscogida = (int) (Math.random() * piezas.size());
        piezasJugador.add(piezas.get(piezaEscogida));
        piezas.remove(piezaEscogida);
    }

    public ArrayList<Pieza> getPiezas() {
        return this.piezas;
    }

    public void jugar() {
        crearPiezas();
        crearOrganizadores();
        while (this.isGanador == false) {
            turno = true;
            while (turno) {
                System.out.println("Piezas en el tablero: " + tablero.getPiezasEnTablero());
                System.out.println("Tus piezas: " + organizadores.get(0).getPiezas().toString());
                bajar = new ArrayList<Pieza>();
                if (this.seBajo == false && segundoTurno==false) {
                    this.yaAñadio = false;
                    System.out.println("Cual sera su siguiente movimiento? \n1-Tomar Pieza\n2-Bajarse");
                    eleccion = comprobarEleccion(2);
                    if (eleccion == 1) {
                        tomarPieza(organizadores.get(0).getPiezas(), piezas);
                        turno = false;
                        this.tOE = 0;
                    } else {
                        while (yaAñadio == false){
                            bajarse(bajar);
                        }
                    }
                } else if(seBajo == true && segundoTurno==false){
                    System.out.println("Cual sera su siguiente movimiento? \n1- Bajarse\n2- Finalizar Turno");
                    eleccion = comprobarEleccion(3);
                    this.yaAñadio = false;
                    if(eleccion == 1) {
                        while (yaAñadio == false) {
                            bajarse(bajar);
                        }
                    }else{
                        turno = false;
                        segundoTurno= true;
                        seBajo =false;
                    }
                } else if(segundoTurno==true && seBajo==false){
                    this.yaAñadio = false;
                    System.out.println("Cual sera su siguiente movimiento? \n1- Tomar Pieza\n2- Bajarse\n3- Añadir pieza");
                    eleccion = comprobarEleccion(3);
                    if (eleccion == 1) {
                        tomarPieza(organizadores.get(0).getPiezas(), piezas);
                        turno = false;
                        this.tOE = 0;
                    } else if (eleccion==2) {
                        while (yaAñadio == false){
                            bajarse(bajar);
                        }
                    } else {
                        ArrayList<Pieza> añadirA = new ArrayList<Pieza>();
                        añadirAPiezasTablero(añadirA);
                    }
                } else if (segundoTurno==true && seBajo==true) {
                    this.yaAñadio = false;
                    System.out.println("Cual sera su siguiente movimiento? \n1- Bajarse\n2- Añadir pieza\n3- Finalizar Turno");
                    eleccion = comprobarEleccion(3);
                    if (eleccion==1) {
                        while (yaAñadio == false){
                            bajarse(bajar);
                        }
                    } else if(eleccion == 2) {
                        ArrayList<Pieza> añadirA = new ArrayList<Pieza>();
                        añadirAPiezasTablero(añadirA);
                    }else{
                        turno = false;
                        seBajo = false;
                    }
                }
            }
        }
    }

    public void bajarse(ArrayList<Pieza> bajar){
        int cancelar = organizadores.get(0).getPiezas().size() + 1;
        if(this.tOE == 0) {
            System.out.println("1- Terna \n2- Escalera");
            eleccion = comprobarEleccion(2);
            if (eleccion == 1) {
                this.tOE = 1;
                this.mensaje = "Escoja como minimo 3 piezas para bajar su terna: ";
                this.mensaje2 = "terna";
            } else {
                this.tOE = 2;
                this.mensaje2 = "escalera";
                this.mensaje = "Escoja como minimo 4 piezas para bajar su escalera: ";
            }
        }
        System.out.println("La "+ mensaje2 + " que esta formando: " + this.bajar);
        System.out.println(mensaje);
        System.out.println("Si quiere cancelar digite la opcion " + cancelar);
        String input = " ";
        for (int i = 1; i <= organizadores.get(0).getPiezas().size(); i++) {
            input += i + "-" + organizadores.get(0).getPieza(i - 1).toString() + ", ";
        }
        System.out.println(input);
        eleccion = comprobarEleccion(organizadores.get(0).getPiezas().size() + 1);
        if (eleccion == cancelar) {
            if (!bajar.isEmpty()) {
                for (Pieza p : bajar) {
                    organizadores.get(0).anadirPieza(p);
                }
                bajar.clear();
            }
            this.yaAñadio = true;
            this.tOE = 0;
        }
        else{
            añadirToE(organizadores.get(0).getPieza(eleccion-1), bajar, tOE);
        }
    }

    public void añadirToE(Pieza p, ArrayList<Pieza> añadiendo, int tOE) {
        if (añadiendo.isEmpty()) {
            añadiendo.add(p);
            organizadores.get(0).Remove(p);
        } else if (tOE == 1) {
            if (p.getValor() == añadiendo.get(0).getValor()) {
                boolean coincidencias = false;
                for (int i = 0; i < añadiendo.size() && coincidencias == false; i++) {
                    if (añadiendo.get(i).getColor() == p.getColor()) {
                        coincidencias = true;
                    }
                }
                if (coincidencias == false) {
                    añadiendo.add(p);
                    organizadores.get(0).Remove(p);
                }
            }
            if (añadiendo.size() == 3) {
                this.yaAñadio = true;
                tablero.nuevBajacion(añadiendo);
                this.seBajo = true;
                this.tOE = 0;
            }
        } else if (tOE == 2) {
            if (p.getValor().getValor() - añadiendo.get(indice).getValor().getValor() == -1
                || p.getValor().getValor() - añadiendo.get(indice).getValor().getValor() == 1
                && p.getColor().getColor() == añadiendo.get(0).getColor().getColor()){
                añadiendo.add(p);
                organizadores.get(0).Remove(p);
                this.indice += 1;
            }
            if (añadiendo.size() == 4) {
                this.yaAñadio = true;
                tablero.nuevBajacion(añadiendo);
                this.seBajo = true;
                this.tOE = 0;
                this.indice = 0;
            }
        }
    }

    public void añadirAPiezasTablero(ArrayList<Pieza> añadirA){
        int cancelar = 0;
        int cancelar2 = 0;
        String input = "";

        for (int i = 0; i < tablero.getPiezasEnTablero().size() ; i++) {
            input += (i +1) + "- " + tablero.getPiezasEnTablero().get(i) + ", \n";
        }
        cancelar = tablero.getPiezasEnTablero().size() + 1;
        System.out.println("Escoja a que terna o escalera desea añadir la pieza: ");
        System.out.println("Si quiere cancelar digite la opcion " + cancelar);
        System.out.println(input);
        eleccion = comprobarEleccion(tablero.getPiezasEnTablero().size() + 1);
        int eleccion2 = eleccion;
        input = "";
        for (int i = 1; i <= organizadores.get(0).getPiezas().size(); i++) {
            input += i + "-" + organizadores.get(0).getPieza(i - 1).toString() + ", ";
        }
        cancelar2 = organizadores.get(0).getPiezas().size() + 1;

        if(eleccion2 == cancelar){

        }else {
            System.out.println("Escoja la pieza que quiere añadir");
            System.out.println("Si quiere cancelar digite la opcion " + cancelar);
            System.out.println(tablero.getPiezasEnTablero().get(eleccion2-1));
            System.out.println(input);
            eleccion = comprobarEleccion(organizadores.get(0).getPiezas().size() + 1);
            añadirA.add(organizadores.get(0).getPieza(eleccion-1));
            organizadores.get(0).Remove(añadirA.get(0));
            if (eleccion == cancelar2) {

            } else {
                if (añadirA.get(0).getValor() == tablero.getPiezasEnTablero().get(eleccion2 - 1).get(0).getValor()) {
                    tablero.añadirABajacion(añadirA, eleccion2 - 1);
                } else if (añadirA.get(0).getValor().getValor() - tablero.getPiezasEnTablero().get(eleccion2 - 1).get(0).getValor().getValor() == -1
                        || añadirA.get(0).getValor().getValor() - tablero.getPiezasEnTablero().get(eleccion2 - 1).get(tablero.getPiezasEnTablero().get(eleccion2 - 1).size() - 1).getValor().getValor() == 1
                        && añadirA.get(0).getColor().getColor() == tablero.getPiezasEnTablero().get(eleccion2 - 1).get(0).getColor().getColor()) {
                    tablero.añadirABajacion(añadirA, eleccion2 - 1);
                }
            }
        }
    }

    public int comprobarEleccion(int max) {
        while (true) {
            try {
                String opcionEscogida = sc.nextLine();
                int opcionEscogidaInt = Integer.parseInt(opcionEscogida);
                if (opcionEscogidaInt >= 1 && opcionEscogidaInt <= max) {
                    return opcionEscogidaInt;
                } else {
                    System.out.println("El numero ingresado no es valido, por favor ingrese un numero que sea valido");
                }
            } catch (NumberFormatException ime) {
                System.out.println("Ingrese un numero positivo");
            }
        }
    }
}
