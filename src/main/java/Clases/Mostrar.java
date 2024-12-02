/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adria
 */
public class Mostrar {

    List<Punto> puntos;
    long inicio, fin, Tejecucion;
    Punto p = new Punto();
    Algoritmos a = new Algoritmos();
    PuntosMin pmin = new PuntosMin();
    ArrayList<String> estrategias = new ArrayList<>();
    ArrayList<String> ficheros = new ArrayList<>();
    Lectura lec = new Lectura();

    public Mostrar() {
        RellenarArrayFicheros();
        RellenarArrayEstrategias();
    }

    public void RellenarArrayFicheros() {
        ficheros.add("berlin52.tsp");
        ficheros.add("ch130.tsp");
        ficheros.add("ch150.tsp");
        ficheros.add("d493.tsp");
        ficheros.add("d657.tsp");
    }

    public ArrayList<String> getEstrategias() {
        return estrategias;
    }

    public ArrayList<String> getFicheros() {
        return ficheros;
    }

    public void RellenarArrayEstrategias() {
        estrategias.add("Exhaustivo");
        estrategias.add("ExhaustivoPoda");
        estrategias.add("DyVe");
        estrategias.add("DyVeMejorado");
    }

    public void MostrarExhaustiva(ArrayList<Punto> puntos2, int tipo) throws FileNotFoundException {

        a.setCont();
        a.setOp();

        inicio = System.nanoTime();

        if (tipo == 1) {
            pmin = a.BusquedaExahustiva(puntos2, 0, puntos2.size() - 1);
        }
        if (tipo == 2) {
            pmin = a.BusquedaPoda(puntos2, 0, puntos2.size() - 1);
        }
        if (tipo == 3) {
            pmin = a.DyVe(puntos2);
        }
        if (tipo == 4) {
            pmin = a.DyVeMejorado(puntos2);
        }
        fin = System.nanoTime();
        Tejecucion = fin - inicio;
        System.out.println(estrategias.get(tipo - 1) + "        " + pmin.getA().getId() + "              " + pmin.getB().getId() + "               " + pmin.getDistancia()
                + "               " + a.getCont() + "                 " + Tejecucion / 1000000.0);

    }

    public void Apartado3(List<Punto> puntos, int estrategia) throws IOException {
        a.setCont();
        a.setOp();
        if (estrategia == 1) {
            a.BusquedaExahustiva(puntos, 0, puntos.size() - 1);
        } else if (estrategia == 2) {
            a.BusquedaPoda(puntos, 0, puntos.size() - 1);
        } else if (estrategia == 3) {
            a.DyVe(puntos);
        } else if (estrategia == 4) {
            a.DyVeMejorado(puntos);
        }
    }

    public long CompararStrats(List<Punto> puntos, int estrategia) throws IOException {
        inicio = System.nanoTime();
        Apartado3(puntos, estrategia);
        fin = System.nanoTime();
        Tejecucion = fin - inicio;
        return Tejecucion;

    }

    public void TodasStrat(boolean peorcaso, List<Punto> puntos, int talla) throws IOException {

        long Tejecucion1, Tejecucion2, Tejecucion3, Tejecucion4;

        Tejecucion1 = CompararStrats(puntos, 1);
        lec.EscribirDat(1, talla, (long) (Tejecucion1 / 1000000.0), a.getCont(), true);
        Tejecucion2 = CompararStrats(puntos, 2);
        lec.EscribirDat(2, talla, (long) (Tejecucion2 / 1000000.0), a.getCont(), true);
        Tejecucion3 = CompararStrats(puntos, 3);
        lec.EscribirDat(3, talla, (long) (Tejecucion3 / 1000000.0), a.getCont(), true);
        Tejecucion4 = CompararStrats(puntos, 4);
        lec.EscribirDat(4, talla, (long) (Tejecucion4 / 1000000.0), a.getCont(), true);
        System.out.printf("%d              %.4f          %.4f       %.4f        %.4f%n", talla, Tejecucion1 / 1000000.0, Tejecucion2 / 1000000.0, Tejecucion3 / 1000000.0, Tejecucion4 / 1000000.0);
    }

    public void Caso3(List<Punto> puntos, boolean peorcaso, int estrategia) throws IOException {
        int i = 500;
        lec.borrarFichero(estrategia);
        lec.borrarFicheroGrafica(estrategia);
        System.out.println("Talla           Tiempo          Distancias");
        while (i <= 5000) {
            a.setOp();
            puntos.clear();
            p.rellenarPuntos(puntos, i, peorcaso);
            Tejecucion = CompararStrats(puntos, estrategia);
            System.out.printf("%d          %.4f         %d %n", i, Tejecucion / 1000000.0, a.getCont());
            lec.EscribirDat(estrategia, i, (long) (Tejecucion / 1000000.0), a.getCont(), true);
            lec.EscribirDatops(estrategia, i, (long) (Tejecucion / 1000000.0), a.getOp(), true);
            i += 500;
        }
    }

    public ArrayList<String[]> Caso3Tabla(List<Punto> puntos, boolean peorcaso, int estrategia) throws IOException {
        ArrayList<String[]> resultados = new ArrayList<>();
        int i = 500;
        lec.borrarFichero(estrategia);
        lec.borrarFicheroGrafica(estrategia);
        while (i <= 5000) {
            a.setOp();
            puntos.clear();
            p.rellenarPuntos(puntos, i, peorcaso);
            Tejecucion = CompararStrats(puntos, estrategia);
            System.out.println(a.getOp());
            String[] fila = new String[]{
                String.valueOf(i), // Talla
                String.format("%.4f", Tejecucion / 1000000.0), // Tiempo
                String.valueOf(a.getCont()), // Distancias
            };
            resultados.add(fila);
            lec.EscribirDat(estrategia, i, Tejecucion, a.getCont(), true);
            lec.EscribirDatops(estrategia, i, Tejecucion, a.getOp(), true);
            i += 500;
        }

        return resultados;
    }

    public ArrayList<String[]> MostrarExhaustivaTabla(ArrayList<Punto> puntos2, int tipo) throws FileNotFoundException {
        ArrayList<String[]> resultados = new ArrayList<>();
        a.setCont();
        a.setOp();

        inicio = System.nanoTime();

        if (tipo == 1) {
            pmin = a.BusquedaExahustiva(puntos2, 0, puntos2.size() - 1);
        }
        if (tipo == 2) {
            pmin = a.BusquedaPoda(puntos2, 0, puntos2.size() - 1);
        }
        if (tipo == 3) {
            pmin = a.DyVe(puntos2);
        }
        if (tipo == 4) {
            pmin = a.DyVeMejorado(puntos2);
        }
        fin = System.nanoTime();
        Tejecucion = fin - inicio;
        String[] fila = new String[]{
            estrategias.get(tipo - 1), // Estrategia
            String.valueOf(pmin.getA().getId()), // ID del primer punto
            String.valueOf(pmin.getB().getId()), // ID del segundo punto
            String.valueOf(pmin.getDistancia()), // Distancia entre los puntos
            String.valueOf(a.getCont()), // Contador de operaciones
            String.format("%.4f", Tejecucion / 1000000.0) // Tiempo de ejecución en ms
        };
        // Añadir la fila a la lista de resultados
        resultados.add(fila);

        return resultados;
    }

    public ArrayList<String[]> Caso4(ArrayList<Punto> puntos, boolean peorcaso, int est1, int est2) throws IOException {
        a.setCont();
        a.setOp();

        int talla = 500;
        Punto p = new Punto();
        Algoritmos a1 = new Algoritmos();
        Algoritmos a2 = new Algoritmos();
        ArrayList<String[]> resultados = new ArrayList<>();

        while (talla <= 5000) {
            puntos.clear();
            p.rellenarPuntos(puntos, talla, peorcaso);

            // Ejecutar y medir tiempo para la primera estrategia
            long tiempoInicio1 = System.nanoTime();
            CompararStrats(puntos, est1);
            long tiempoFin1 = System.nanoTime();
            long tiempoEjecucion1 = (tiempoFin1 - tiempoInicio1); // Convertir a ms
            int distanciasCalculadas1 = a1.getCont();

            // Ejecutar y medir tiempo para la segunda estrategia
            long tiempoInicio2 = System.nanoTime();
            CompararStrats(puntos, est2);
            long tiempoFin2 = System.nanoTime();
            long tiempoEjecucion2 = (long)(tiempoFin2 - tiempoInicio2); // Convertir a ms
            int distanciasCalculadas2 = a2.getCont();

            // Agregar datos a la tabla
            String[] fila = new String[]{
                String.valueOf(talla), // Talla
                String.format("%.4f", tiempoEjecucion1 / 1000000.0), // Tiempo
                String.valueOf(distanciasCalculadas1), // Distancias
                String.format("%.4f", tiempoEjecucion2 / 1000000.0), // Tiempo
                String.valueOf(distanciasCalculadas2), // Distancias
            };
            resultados.add(fila);

            talla += 500; // Incrementar talla
        }

        return resultados;
    }

    public ArrayList<String[]> TodasStratTabla(boolean peorcaso, List<Punto> puntos) throws IOException {

        int talla = 500;
        long Tejecucion1, Tejecucion2, Tejecucion3, Tejecucion4;
        Punto p = new Punto();
        ArrayList<String[]> resultados = new ArrayList<>();

        while (talla <= 5000) {
            puntos.clear();
            p.rellenarPuntos(puntos, talla, peorcaso);
            Tejecucion1 = CompararStrats(puntos, 1);
            lec.EscribirDat(1, talla, (long) (Tejecucion1 / 1000000.0), a.getCont(), true);
            Tejecucion2 = CompararStrats(puntos, 2);
            lec.EscribirDat(2, talla, (long) (Tejecucion2 / 1000000.0), a.getCont(), true);
            Tejecucion3 = CompararStrats(puntos, 3);
            lec.EscribirDat(3, talla, (long) (Tejecucion3 / 1000000.0), a.getCont(), true);
            Tejecucion4 = CompararStrats(puntos, 4);
            lec.EscribirDat(4, talla, (long) (Tejecucion4 / 1000000.0), a.getCont(), true);
            String[] fila = new String[]{
                String.valueOf(talla), // Talla
                String.format("%.4f", Tejecucion1 / 1000000.0), // Tiempo
                String.format("%.4f", Tejecucion2 / 1000000.0), // Tiempo
                String.format("%.4f", Tejecucion3 / 1000000.0), // Tiempo
                String.format("%.4f", Tejecucion4 / 1000000.0), // Tiempo
            };

            resultados.add(fila);

            talla += 500; // Incrementar talla
        }
        
        return resultados;
    }

}
