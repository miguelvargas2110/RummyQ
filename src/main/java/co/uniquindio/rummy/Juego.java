package co.uniquindio.rummy;

import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.ArrayList;
import java.util.Collections;
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

    private boolean seBajoPC = false;

    private boolean turno = true;

    private boolean segundoTurno = false;

    private boolean segundoTurnoPC = false;

    private boolean yaAñadio = false;

    private boolean añadio = false;

    private int tOE;

    private String mensaje;

    private int indice = 0;

    private ArrayList<Pieza> bajar;
    private String mensaje2;

    private int computadora = 0;

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
                Collections.sort(organizadores.get(1).getPiezas());
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
        if(turno == false){
            Collections.sort(organizadores.get(1).getPiezas());
        }
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
                this.seBajo = false;
                this.añadio = false;
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
                        this.yaAñadio = true;
                    }
                }
                while(seBajo == true || añadio == true){
                    if(seBajo == true && segundoTurno==false) {
                        System.out.println("Piezas en el tablero: " + tablero.getPiezasEnTablero());
                        System.out.println("Tus piezas: " + organizadores.get(0).getPiezas().toString());
                        bajar = new ArrayList<Pieza>();
                        System.out.println("Cual sera su siguiente movimiento? \n1- Bajarse\n2- Finalizar Turno");
                        eleccion = comprobarEleccion(3);
                        this.yaAñadio = false;
                        if (eleccion == 1) {
                            while (yaAñadio == false) {
                                bajarse(bajar);
                            }
                        } else {
                            turno = false;
                            segundoTurno = true;
                            seBajo = false;
                        }
                    }else if (segundoTurno==true) {
                        System.out.println("Piezas en el tablero: " + tablero.getPiezasEnTablero());
                        System.out.println("Tus piezas: " + organizadores.get(0).getPiezas().toString());
                        bajar = new ArrayList<Pieza>();
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
                            añadio = false;
                            break;
                        }
                    }
                }

            }
            while(turno == false){
                bajar = new ArrayList<Pieza>();
                this.seBajoPC = false;
                if (this.seBajoPC == false && segundoTurnoPC ==false) {
                    this.yaAñadio = false;
                    bajarse(bajar);
                    if(seBajoPC == true){
                        this.segundoTurnoPC = true;
                        break;
                    }else{
                        tomarPieza(organizadores.get(1).getPiezas(), piezas);
                        break;
                    }
                } else if(segundoTurnoPC==true && seBajoPC==false){
                    this.yaAñadio = false;
                    ArrayList<Pieza> añadirA = new ArrayList<Pieza>();
                    añadirAPiezasTablero(añadirA);
                    bajarse(bajar);
                    if(seBajoPC == true){
                        this.segundoTurnoPC = true;
                        break;
                    }else{
                        tomarPieza(organizadores.get(1).getPiezas(), piezas);
                        break;
                    }
                }
            }
        }
    }

    public void bajarse(ArrayList<Pieza> bajar){
        if (turno == true) {
            int cancelar = organizadores.get(0).getPiezas().size() + 1;
            if (this.tOE == 0) {
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
            System.out.println("La " + mensaje2 + " que esta formando: " + this.bajar);
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
            } else {
                añadirToE(organizadores.get(0).getPieza(eleccion - 1), bajar, tOE, organizadores.get(0));
            }
        }
        else{
            for(int i = 0; i < organizadores.get(1).getPiezas().size(); i++){
                bajar = new ArrayList<Pieza>();
                añadirToE(organizadores.get(1).getPieza(i), bajar, 2, organizadores.get(1));
                int cantidadAñadio = this.bajar.size();
                this.yaAñadio = false;
                for(int j = i; j < organizadores.get(1).getPiezas().size(); j++){
                    if(organizadores.get(1).getPieza(j).getValor().getValor() == bajar.get(indice).getValor().getValor()){
                        //No hace nada :D
                    }else{
                        if(organizadores.get(1).getPieza(j).getValor().getValor() - bajar.get(indice).getValor().getValor() >= 2){
                            if (!bajar.isEmpty()) {
                                for (Pieza p : bajar) {
                                    organizadores.get(1).anadirPieza(p);
                                }
                                Collections.sort(organizadores.get(1).getPiezas());
                                bajar.clear();
                            }
                            break;
                        }
                        añadirToE(organizadores.get(1).getPieza(j), bajar, 2, organizadores.get(1));
                        if(yaAñadio == true){
                            break;
                        }
                        if((j+1) == organizadores.get(1).getPiezas().size()){
                            if (!bajar.isEmpty()) {
                                for (Pieza p : bajar) {
                                    organizadores.get(1).anadirPieza(p);
                                }
                                Collections.sort(organizadores.get(1).getPiezas());
                                bajar.clear();
                            }
                            break;
                        }
                        if(cantidadAñadio != (indice+1)){
                            cantidadAñadio += 1;
                            j--;
                        }
                    }
                }
                if(yaAñadio == false){
                    if (!bajar.isEmpty()) {
                        for (Pieza p : bajar) {
                            organizadores.get(1).anadirPieza(p);
                        }
                        Collections.sort(organizadores.get(1).getPiezas());
                        bajar.clear();
                    }
                }
            }
            if (!bajar.isEmpty()) {
                for (Pieza p : bajar) {
                    organizadores.get(1).anadirPieza(p);
                }
                Collections.sort(organizadores.get(1).getPiezas());
                bajar.clear();
            }
            for(int i = 0; i < organizadores.get(1).getPiezas().size(); i++){
                bajar = new ArrayList<Pieza>();
                añadirToE(organizadores.get(1).getPieza(i), bajar, 1, organizadores.get(1));
                int cantidadAñadio = this.bajar.size();
                this.yaAñadio = false;
                for(int j = i; j < organizadores.get(1).getPiezas().size(); j++){
                    if(organizadores.get(1).getPieza(j).getValor().getValor() != bajar.get(0).getValor().getValor()){
                        if(yaAñadio == true){
                            i--;
                            break;
                        }else{
                            i += bajar.size() - 1;
                            if (!bajar.isEmpty()) {
                                for (Pieza p : bajar) {
                                    organizadores.get(1).anadirPieza(p);
                                }
                                Collections.sort(organizadores.get(1).getPiezas());
                                bajar.clear();
                            }
                            break;
                        }

                    }
                    añadirToE(organizadores.get(1).getPieza(j), bajar, 1, organizadores.get(1));
                    if(cantidadAñadio != (indice+1)){
                        cantidadAñadio += 1;
                        j--;
                    }
                }
            }
        }
    }

    public void añadirToE(Pieza p, ArrayList<Pieza> añadiendo, int tOE, Organizador jugador) {
        if (añadiendo.isEmpty()) {
            añadiendo.add(p);
            jugador.Remove(p);
            this.indice = 0;
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
                    jugador.Remove(p);
                    this.indice += 1;
                }
            }
            if (añadiendo.size() == 3) {
                this.yaAñadio = true;
                tablero.nuevBajacion(añadiendo);
                this.seBajo = true;
                this.seBajoPC = true;
                this.tOE = 0;
            }
        } else if (tOE == 2) {
            if (p.getValor().getValor() - añadiendo.get(indice).getValor().getValor() == -1
                || p.getValor().getValor() - añadiendo.get(indice).getValor().getValor() == 1
                && p.getColor().getColor() == añadiendo.get(0).getColor().getColor()){
                añadiendo.add(p);
                jugador.Remove(p);
                this.indice += 1;
            }
            if (añadiendo.size() == 4) {
                this.yaAñadio = true;
                this.seBajoPC = true;
                tablero.nuevBajacion(añadiendo);
                this.seBajo = true;
                this.tOE = 0;
                this.indice = 0;
            }
        }
    }

    public void añadirAPiezasTablero(ArrayList<Pieza> añadirA){
        if(turno == true) {
            int cancelar = 0;
            int cancelar2 = 0;
            String input = "";

            for (int i = 0; i < tablero.getPiezasEnTablero().size(); i++) {
                input += (i + 1) + "- " + tablero.getPiezasEnTablero().get(i) + ", \n";
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

            if (eleccion2 == cancelar) {

            } else {
                System.out.println("Escoja la pieza que quiere añadir");
                System.out.println("Si quiere cancelar digite la opcion " + cancelar);
                System.out.println(tablero.getPiezasEnTablero().get(eleccion2 - 1));
                System.out.println(input);
                eleccion = comprobarEleccion(organizadores.get(0).getPiezas().size() + 1);
                añadirA.add(organizadores.get(0).getPieza(eleccion - 1));
                organizadores.get(0).Remove(añadirA.get(0));
                if (eleccion == cancelar2) {

                } else {
                    if (añadirA.get(0).getValor().getValor() == tablero.getPiezasEnTablero().get(eleccion2 - 1).get(0).getValor().getValor()
                        && añadirA.get(0).getValor().getValor() == tablero.getPiezasEnTablero().get(eleccion2 - 1).get(tablero.getPiezasEnTablero().get(eleccion2 - 1).size() - 1).getValor().getValor()
                        && añadirA.get(0).getColor().getColor() != tablero.getPiezasEnTablero().get(eleccion2 - 1).get(0).getColor().getColor() && añadirA.get(0).getColor().getColor() == tablero.getPiezasEnTablero().get(eleccion2 - 1).get(tablero.getPiezasEnTablero().get(eleccion2 - 1).size() - 1).getColor().getColor()) {
                        tablero.añadirABajacion(añadirA, eleccion2 - 1);
                        this.añadio = true;
                    } else if (añadirA.get(0).getValor().getValor() - tablero.getPiezasEnTablero().get(eleccion2 - 1).get(0).getValor().getValor() == -1
                            || añadirA.get(0).getValor().getValor() - tablero.getPiezasEnTablero().get(eleccion2 - 1).get(tablero.getPiezasEnTablero().get(eleccion2 - 1).size() - 1).getValor().getValor() == 1
                            && añadirA.get(0).getColor().getColor() == tablero.getPiezasEnTablero().get(eleccion2 - 1).get(0).getColor().getColor()) {
                        tablero.añadirABajacion(añadirA, eleccion2 - 1);
                        this.añadio = true;
                    }
                }
            }
        }else{
            for(int i = 0; i < tablero.getPiezasEnTablero().size(); i++){
                for(int j = 0; j < organizadores.get(1).getPiezas().size(); j++){
                    añadirA.add(organizadores.get(1).getPieza(j));
                    if (añadirA.get(0).getValor().getValor() == tablero.getPiezasEnTablero().get(i).get(0).getValor().getValor()
                            && añadirA.get(0).getValor().getValor() == tablero.getPiezasEnTablero().get(i).get(tablero.getPiezasEnTablero().get(i).size() - 1).getValor().getValor()
                        && añadirA.get(0).getColor().getColor() != tablero.getPiezasEnTablero().get(i).get(0).getColor().getColor()) {
                        tablero.añadirABajacion(añadirA, i);
                        j--;
                        //this.añadio = true;
                    } else if ((añadirA.get(0).getColor() == tablero.getPiezasEnTablero().get(i).get(0).getColor()
                            && añadirA.get(0).getColor() == tablero.getPiezasEnTablero().get(i).get(tablero.getPiezasEnTablero().get(i).size() - 1).getColor())
                            && (añadirA.get(0).getValor().getValor() - tablero.getPiezasEnTablero().get(i).get(0).getValor().getValor() == -1
                            || añadirA.get(0).getValor().getValor() - tablero.getPiezasEnTablero().get(i).get(tablero.getPiezasEnTablero().get(i).size() - 1).getValor().getValor() == 1)
                            ) {
                        tablero.añadirABajacion(añadirA, i);
                        j--;
                        //this.añadio = true;
                    }
                    if (!añadirA.isEmpty()) {
                        añadirA.clear();
                    }
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
