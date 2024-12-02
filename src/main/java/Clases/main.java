package Clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author raulp
 */
public class main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int opcion, talla;

        boolean peorcaso = false;
        Scanner sc = new Scanner(System.in);
        Punto p = new Punto();
        ArrayList<Punto> puntos = new ArrayList<>();
        ArrayList<Punto> puntosAux = new ArrayList<>();
        ArrayList<String> ficheros;
        Mostrar mo = new Mostrar();
        Lectura lec1 = new Lectura();
        Menus menu = new Menus();
        Graficas g = new Graficas();

        do {

            opcion = menu.menuPrincipal(peorcaso);
            switch (opcion) {
                case 1 -> {
                    ficheros = mo.getFicheros();
                    for (int j = 0; j < ficheros.size(); j++) {
                        System.out.println("");
                        System.out.println("Nombre de fichero: " + ficheros.get(j));
                        System.out.println("Estrategia        Punto1          Punto2          distancia                          calculadas          tiempo");
                        Lectura lec = new Lectura(ficheros.get(j));
                        puntos = lec.getPuntos();
                        puntosAux = (ArrayList<Punto>) puntos.clone();
                        for (int i = 1; i < 5; i++) {
                            puntos = (ArrayList<Punto>) puntosAux.clone();
                            mo.MostrarExhaustiva(puntos, i);
                        }

                    }
                }
                case 2 -> {
                    System.out.println("Introduce la talla deseada: ");
                    talla = sc.nextInt();
                    puntos.clear();
                    p.rellenarPuntos(puntos, talla, peorcaso);
                    puntosAux = (ArrayList<Punto>) puntos.clone();
                    System.out.println("Estrategia        Punto1          Punto2          distancia                          calculadas          tiempo");
                    for (int i = 1; i < 5; i++) {
                        puntos = (ArrayList<Punto>) puntosAux.clone();
                        mo.MostrarExhaustiva(puntos, i);
                    }

                }
                case 3 -> {
                    int opcion3 = -1, i;
                    while (opcion3 != 0) {
                        opcion3 = menu.menu3();

                        switch (opcion3) {
                            case 1:
                                mo.Caso3(puntos, peorcaso, 1);
                                g.CrearGrafica(1);
                                break;
                            case 2:
                                mo.Caso3(puntos, peorcaso, 2);
                                g.CrearGrafica(2);
                                break;
                            case 3:
                                mo.Caso3(puntos, peorcaso, 3);
                                g.CrearGrafica(3);
                                break;
                            case 4:
                                mo.Caso3(puntos, peorcaso, 4);
                                g.CrearGrafica(4);
                                break;
                            case 0:
                                break;
                            default:
                                throw new AssertionError();
                        }
                    }
                }
                case 4 -> {
                    int est1 = -1, est2 = -1, i = 500;
                    long Tejecucion1, Tejecucion2;
                    Algoritmos a1 = new Algoritmos();
                    Algoritmos a2 = new Algoritmos();
                    while (est1 != 0 || est2 != 0) {
                        est1 = menu.menu3();
                        est2 = menu.menu3();

                        if (est1 != 0 || est2 != 0) {
                            lec1.borrarFichero(est1);
                            lec1.borrarFichero(est2);
                            System.out.println("                Exhaustivo          ExhaustivoPoda          Exhaustivo          ExhaustivoPoda");
                            System.out.println("Talla           Tiempo              Tiempo                 Distancias           Distancias");
                            while (i <= 5000) {
                                puntos.clear();
                                p.rellenarPuntos(puntos, i, peorcaso);
                                Tejecucion1 = mo.CompararStrats(puntos, est1);
                                lec1.EscribirDat(est1, i, (long) (Tejecucion1 / 1000000.0), a1.getCont(), true);
                                Tejecucion2 = mo.CompararStrats(puntos, est2);
                                lec1.EscribirDat(est2, i, (long) (Tejecucion2 / 1000000.0), a2.getCont(), true);
                                System.out.printf("%d              %.9f          %.9f          %d           %d%n", i, Tejecucion1 / 1000000.0, Tejecucion2 / 1000000.0, a1.getCont(), a2.getCont());
                                i += 500;

                            }
                        }
                    }
                }
                case 5 -> {
                    int i = 500;
                    for (int j = 1; j <= mo.estrategias.size(); j++) {
                        lec1.borrarFichero(j);
                    }
                    System.out.println("                Exhaustivo          ExhaustivoPoda         DyVe                DyVe Mejorado");
                    System.out.println("Talla           Tiempo              Tiempo                 Tiempo              Tiempo");
                    while (i <= 5000) {
                        puntos.clear();
                        p.rellenarPuntos(puntos, i, peorcaso);
                        mo.TodasStrat(peorcaso, puntos, i);
                        i += 500;
                    }
                }
                case 6 -> {
                    if (peorcaso) {
                        peorcaso = false;
                    } else {
                        peorcaso = true;
                    }
                }
                case 7 -> {

                    System.out.println("Introduce la talla del tsp");
                    talla = sc.nextInt();
                    puntos.clear();
                    p.rellenarPuntos(puntos, talla, peorcaso);

                    lec1.EscribirTSP(puntos, "Dataset(" + talla + ")");
                }
                case 8 -> {
                    String archivo;
                    int i = 500;
                    System.out.println("Introduce el archivo: ");
                    archivo = sc.next();
                    Lectura lecturatsp = new Lectura(archivo + ".tsp");
                    puntos = lecturatsp.getPuntos();
                    System.out.println("Nombre de fichero: " + archivo + ".tsp");
                    System.out.println("Estrategia        Punto1          Punto2          distancia                          calculadas          tiempo");
                    for (int x = 1; x < 5; x++) {
                        mo.MostrarExhaustiva(puntos, x);
                    }
                }
                case 0 -> {
                }

                default ->
                    throw new AssertionError();
            }
        } while (opcion != 0);

    }
}
