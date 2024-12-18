/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author raulp
 */
public class Voraces {

    private static int cont = 0;
    private static double solucion = 0.0;
    private static ArrayList<Double> rayas = new ArrayList<>();

    public static void setSolucion() {
        Voraces.solucion = 0;
    }

    public static double getSolucion() {
        return solucion;
    }

    public static void setCont() {
        Voraces.cont = 0;
    }

    public static int getCont() {
        return cont;
    }

    public ArrayList<Double> getRayas() {
        return rayas;
    }

    public static double distancia(Punto a, Punto b) {
        double x = Math.abs(a.getX() - b.getX());
        double y = Math.abs(a.getY() - b.getY());

        return (double) Math.sqrt((x * x) + (y * y));
    }

    public static ArrayList<Punto> vorazUnidireccional(ArrayList<Punto> ciudades, int ini) {
        setCont();
        setSolucion();
        rayas.clear();
        ArrayList<Punto> ruta = new ArrayList<>();
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);

        Punto ciudadActual = ciudades.get(ini);
        visitadas[ini] = true;
        ruta.add(ciudadActual);

        while (ruta.size() < ciudades.size()) {
            int posicion = ciudadMasCercana(ciudadActual, ciudades, visitadas);
            double dist = distancia(ciudadActual, ciudades.get(posicion));
            cont++;
            solucion += dist;
            rayas.add(dist);
            visitadas[posicion] = true;
            ruta.add(ciudades.get(posicion));
            ciudadActual = ciudades.get(posicion);
        }
        double distanciafinal = distancia(ciudadActual, ciudades.get(ini));
        cont++;
        solucion += distanciafinal;
        rayas.add(distanciafinal);
        ruta.add(ciudades.get(ini));

        return ruta;
    }

    public static ArrayList<Punto> vorazBidireccional(ArrayList<Punto> ciudades, int ini) {
        rayas.clear();
        ArrayList<Punto> ruta = new ArrayList<>();
        setCont();
        setSolucion();
        ArrayList<Punto> rutaIzquierda = new ArrayList<>();
        ArrayList<Punto> rutaDerecha = new ArrayList<>();
        double distanciaIzquierda = 0, distanciaDerecha = 0, distanciaFinal = 0;
        Punto inicio = ciudades.get(ini);
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);
        visitadas[ini] = true;

        rutaIzquierda.add(inicio);
        rutaDerecha.add(inicio);
        Punto extremoIzq = inicio;
        Punto extremoDer = inicio;

        int cercanaIzquierda = -1;
        int cercanaDerecha = -1;

        while (rutaIzquierda.size() + rutaDerecha.size() <= ciudades.size()) {
            if (cercanaIzquierda == -1 || visitadas[cercanaIzquierda]) {
                cercanaIzquierda = ciudadMasCercana(extremoIzq, ciudades, visitadas);
                distanciaIzquierda = distancia(extremoIzq, ciudades.get(cercanaIzquierda));
                cont++;
            }

            if (cercanaDerecha == -1 || visitadas[cercanaDerecha]) {
                cercanaDerecha = ciudadMasCercana(extremoDer, ciudades, visitadas);
                distanciaDerecha = distancia(extremoDer, ciudades.get(cercanaDerecha));
                cont++;
            }

            if (distanciaIzquierda <= distanciaDerecha) {
                Punto izquierda = ciudades.get(cercanaIzquierda);
                rutaIzquierda.add(izquierda);
                visitadas[cercanaIzquierda] = true;
                extremoIzq = izquierda;
                solucion += distanciaIzquierda;
                rayas.add(distanciaIzquierda);

                cercanaIzquierda = -1;
            } else {
                Punto derecha = ciudades.get(cercanaDerecha);
                rutaDerecha.add(derecha);
                visitadas[cercanaDerecha] = true;
                extremoDer = derecha;
                solucion += distanciaDerecha;
                rayas.add(distanciaDerecha);

                // Reinicia el cálculo para el siguiente más cercano a la derecha
                cercanaDerecha = -1;
            }
        }

        // Calcula la distancia final desde el último extremo al inicio
        if (rutaIzquierda.size() > rutaDerecha.size()) {
            distanciaFinal = distancia(inicio, extremoIzq);
        } else {
            distanciaFinal = distancia(inicio, extremoDer);
        }

        rayas.add(distanciaFinal);
        solucion += distanciaFinal;

        // Construye la ruta final
        ruta.addAll(rutaIzquierda);
        for (int i = rutaDerecha.size() - 1; i >= 0; i--) {
            ruta.add(rutaDerecha.get(i));
        }

        return ruta;
    }

    public static ArrayList<Punto> vorazUnidireccionalPoda(ArrayList<Punto> ciudades, int ini) {
        ArrayList<Punto> ruta = new ArrayList<>();
        setCont();
        setSolucion();
        rayas.clear();
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);

        Punto puntoIni = ciudades.get(ini);
        quicksort(ciudades, 0, ciudades.size() - 1);

        int ini2 = ciudades.indexOf(puntoIni);

        Punto ciudadActual = ciudades.get(ini2);
        visitadas[ini2] = true;
        ruta.add(ciudadActual);
        while (ruta.size() < ciudades.size()) {
            int posicion = ciudadMasCercanaPoda(ciudadActual, ciudades, visitadas);
            // Verificar si ciudadMasCercanaPoda encontró una ciudad válida
            if (posicion == -1) {
                throw new RuntimeException("Error: No se encontró una ciudad válida para continuar la ruta.");
            }
            double dist = distancia(ciudadActual, ciudades.get(posicion));
            cont++;
            solucion += dist;
            rayas.add(dist);
            visitadas[posicion] = true;
            ciudadActual = ciudades.get(posicion);
            ruta.add(ciudadActual);
        }
        double distanciafinal = distancia(ciudadActual, ciudades.get(ini2));
        cont++;
        solucion += distanciafinal;
        rayas.add(distanciafinal);
        ruta.add(ciudades.get(ini2));
        // Cerrar el ciclo
        return ruta;
    }

    public static ArrayList<Punto> vorazBidireccionalPoda(ArrayList<Punto> ciudades, int ini) {
        ArrayList<Punto> ruta = new ArrayList<>();
        setCont();
        setSolucion();
        rayas.clear();
        double distanciaIzquierda = 0, distanciaDerecha = 0, distanciaFinal = 0;
        Punto puntoIni = ciudades.get(ini);
        quicksort(ciudades, 0, ciudades.size() - 1);

        int ini2 = ciudades.indexOf(puntoIni);

        ArrayList<Punto> rutaIzquierda = new ArrayList<>();
        ArrayList<Punto> rutaDerecha = new ArrayList<>();

        Punto inicio = ciudades.get(ini2);
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);
        visitadas[ini2] = true;

        rutaIzquierda.add(inicio);
        rutaDerecha.add(inicio);
        Punto extremoIzq = inicio;
        Punto extremoDer = inicio;

        // Variables para almacenar las ciudades más cercanas calculadas
        int cercanaIzquierda = -1;
        int cercanaDerecha = -1;

        while (rutaIzquierda.size() + rutaDerecha.size() <= ciudades.size()) {
            if (cercanaIzquierda == -1 || visitadas[cercanaIzquierda]) {
                cercanaIzquierda = ciudadMasCercanaPoda(extremoIzq, ciudades, visitadas);
                distanciaIzquierda = distancia(extremoIzq, ciudades.get(cercanaIzquierda));
                cont++;
            }

            if (cercanaDerecha == -1 || visitadas[cercanaDerecha]) {
                cercanaDerecha = ciudadMasCercanaPoda(extremoDer, ciudades, visitadas);
                distanciaDerecha = distancia(extremoDer, ciudades.get(cercanaDerecha));
                cont++;
            }

            if (distanciaIzquierda <= distanciaDerecha) {
                // Añadir al inicio
                Punto izquierda = ciudades.get(cercanaIzquierda);
                rutaIzquierda.add(izquierda);
                visitadas[cercanaIzquierda] = true;
                extremoIzq = izquierda;
                solucion += distanciaIzquierda;
                rayas.add(distanciaIzquierda);

                // Reinicia el cálculo para la siguiente iteración
                cercanaIzquierda = -1;
            } else {
                // Añadir al final
                Punto derecha = ciudades.get(cercanaDerecha);
                rutaDerecha.add(derecha);
                visitadas[cercanaDerecha] = true;
                extremoDer = derecha;
                solucion += distanciaDerecha;
                rayas.add(distanciaDerecha);

                // Reinicia el cálculo para la siguiente iteración
                cercanaDerecha = -1;
            }
        }

        // Calcula la distancia final desde el último extremo al inicio
        if (rutaIzquierda.size() > rutaDerecha.size()) {
            distanciaFinal = distancia(inicio, extremoIzq);
        } else {
            distanciaFinal = distancia(inicio, extremoDer);
        }
        cont++;
        rayas.add(distanciaFinal);
        solucion += distanciaFinal;

        // Construye la ruta final
        ruta.addAll(rutaIzquierda);
        for (int i = rutaDerecha.size() - 1; i >= 0; i--) {
            ruta.add(rutaDerecha.get(i));
        }

        return ruta;
    }

    private static int ciudadMasCercana(Punto origen, ArrayList<Punto> ciudades, boolean[] visitadas) {
        int posicion = -1;
        double minima = Double.MAX_VALUE;
        for (int i = 0; i < ciudades.size(); i++) {
            if (!visitadas[i]) {
                double distancia = distancia(origen, ciudades.get(i));
                cont++;

                if (distancia < minima) {
                    minima = distancia;
                    posicion = i;
                }
            }
        }
        return posicion;
    }

private static int ciudadMasCercanaPoda(Punto origen, ArrayList<Punto> ciudades, boolean[] visitadas) {
    int posicion = -1;
    double minima = Double.MAX_VALUE;

    for (int i = 0; i < ciudades.size(); i++) {
        if (!visitadas[i]) {
            // Poda respecto al eje X
            if (Math.abs(origen.getX() - ciudades.get(i).getX()) < minima) {
                // Calcula la distancia solo si pasa la poda
                double distancia = distancia(origen, ciudades.get(i));
                cont++;

                if (distancia < minima) {
                    minima = distancia;
                    posicion = i;
                }
            }
        }
    }

    return posicion;
}


    public void rellenarPuntos(ArrayList<Punto> p, int n) {
        int num, den;
        double x, y, aux1;
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            num = r.nextInt(1, 10000); //genera un número aleatorio entre 1 y 4000
            den = r.nextInt(17) + 7; //genera un aleatorio entre 7 y 17
            x = num / ((double) den + 0.37); //division con decimales
            y = (r.nextInt(1, 10000)) / ((double) (r.nextInt(7, 17)) + 0.37);
            p.add(new Punto(i + 1, x, y));
        }

    }

    public static void quicksort(List<Punto> puntos, int izq, int der) {

        // tomamos primer elemento como pivote
        Punto pivote = puntos.get(izq);
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        Punto aux;

        while (i < j) {

            while ((puntos.get(i).getX() <= pivote.getX()) && (i < j)) {

                i++;// busca elemento mayor que pivote

            }

            while (puntos.get(j).getX() > pivote.getX()) {
                j--;// busca elemento menor que pivote

            }

            if (i < j) {                        // si no se han cruzado                      
                aux = puntos.get(i);                      // los intercambia
                puntos.set(i, puntos.get(j));
                puntos.set(j, aux);

            }

        }

        puntos.set(izq, puntos.get(j));      // se coloca el pivote en su lugar de forma que tendremos                                    
        puntos.set(j, pivote);      // los menores a su izquierda y los mayores a su derecha

        if (izq < j - 1) {
            quicksort(puntos, izq, j - 1);
        }

        if (j + 1 < der) {
            quicksort(puntos, j + 1, der);
        }
    }
}
