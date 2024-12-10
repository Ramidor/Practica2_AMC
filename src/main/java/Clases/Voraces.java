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

    public Voraces() {

    }

    public static void setCont() {
        Voraces.cont = 0;
    }

    public static double distancia(Punto a, Punto b) {
        double x = Math.abs(a.getX() - b.getX());
        double y = Math.abs(a.getY() - b.getY());

        return (double) Math.sqrt((x * x) + (y * y));
    }

    public static ArrayList<Punto> vorazUnidireccional(ArrayList<Punto> ciudades) {
        ArrayList<Punto> ruta = new ArrayList<>();
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);
        Random r = new Random();
        int posAlea = r.nextInt(ciudades.size());

        Punto ciudadActual = ciudades.get(posAlea);
        visitadas[posAlea] = true;
        ruta.add(ciudadActual);

        while (ruta.size() < ciudades.size()) {
            int posicion = ciudadMasCercana(ciudadActual, ciudades, visitadas);
            visitadas[posicion] = true;
            ruta.add(ciudades.get(posicion));
            ciudadActual = ciudades.get(posicion);
        }

        ruta.add(ciudades.get(posAlea));
        setCont();
        return ruta;
    }

    public static ArrayList<Punto> vorazBidireccional(ArrayList<Punto> ciudades) {
        ArrayList<Punto> ruta = new ArrayList<>();

        ArrayList<Punto> rutaIzquierda = new ArrayList<>();
        ArrayList<Punto> rutaDerecha = new ArrayList<>();
        Random r = new Random();
        int ciudadInicio = r.nextInt(ciudades.size());
        Punto inicio = ciudades.get(ciudadInicio);
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);
        visitadas[ciudadInicio] = true;

        rutaIzquierda.add(inicio);
        rutaDerecha.add(inicio);
        Punto extremoIzq = inicio;
        Punto extremoDer = inicio;
        while (rutaIzquierda.size() + rutaDerecha.size() <= ciudades.size()) {
            int cercanaIzquierda = ciudadMasCercana(extremoIzq, ciudades, visitadas);
            int cercanaDerecha = ciudadMasCercana(extremoDer, ciudades, visitadas);

            double distanciaIzquierda = distancia(extremoIzq, ciudades.get(cercanaIzquierda));
            double distanciaDerecha = distancia(extremoDer, ciudades.get(cercanaDerecha));
            cont+=2;
            if (distanciaIzquierda <= distanciaDerecha) {
                // Añadir al inicio
                Punto izquierda = ciudades.get(cercanaIzquierda);
                rutaIzquierda.add(izquierda);
                visitadas[cercanaIzquierda] = true;
                extremoIzq = izquierda;
            } else {
                // Añadir al final
                Punto derecha = ciudades.get(cercanaDerecha);
                rutaDerecha.add(derecha);
                visitadas[cercanaDerecha] = true;
                extremoDer = derecha;
            }
        }
        System.out.println("Soy izquierda");
        for (int i = 0; i < rutaIzquierda.size(); i++) {
            ruta.add(rutaIzquierda.get(i));
            System.out.println(rutaIzquierda.get(i));
        }
        System.out.println("Soy derecha");
        for (int i = rutaDerecha.size() - 1; i >= 0; i--) {
            ruta.add(rutaDerecha.get(i));
            System.out.println(rutaDerecha.get(i));
        }
        System.out.println("");
        System.out.println("soy las distancias calculadas" + cont);
        setCont();
        return ruta;
    }

    public static ArrayList<Punto> vorazUnidireccionalPoda(ArrayList<Punto> ciudades) {
        ArrayList<Punto> ruta = new ArrayList<>();
        ArrayList<Punto> ciudadesordenadas = (ArrayList<Punto>) ciudades.clone();

        boolean[] visitadas = new boolean[ciudadesordenadas.size()];
        Arrays.fill(visitadas, false);
        Random r = new Random();
        int posAlea = r.nextInt(ciudades.size());

        Punto ciudadActual = ciudades.get(posAlea);
        visitadas[posAlea] = true;
        ruta.add(ciudadActual);

        while (ruta.size() < ciudades.size()) {
            int posicion = ciudadMasCercanaPoda(ciudadActual, ciudades, visitadas);
            visitadas[posicion] = true;
            ruta.add(ciudadesordenadas.get(posicion));
            ciudadActual = ciudadesordenadas.get(posicion);
        }

        ruta.add(ciudades.get(posAlea));
        setCont();
        return ruta;
    }

    public static ArrayList<Punto> vorazBidireccionalPoda(ArrayList<Punto> ciudades) {
        ArrayList<Punto> ruta = new ArrayList<>();

        ArrayList<Punto> rutaIzquierda = new ArrayList<>();
        ArrayList<Punto> rutaDerecha = new ArrayList<>();
        Random r = new Random();
        int ciudadInicio = r.nextInt(ciudades.size());
        Punto inicio = ciudades.get(ciudadInicio);
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);
        visitadas[ciudadInicio] = true;

        rutaIzquierda.add(inicio);
        rutaDerecha.add(inicio);
        Punto extremoIzq = inicio;
        Punto extremoDer = inicio;
        while (rutaIzquierda.size() + rutaDerecha.size() <= ciudades.size()) {
            int cercanaIzquierda = ciudadMasCercanaPoda(extremoIzq, ciudades, visitadas);
            int cercanaDerecha = ciudadMasCercanaPoda(extremoDer, ciudades, visitadas);

            double distanciaIzquierda = distancia(extremoIzq, ciudades.get(cercanaIzquierda));

            double distanciaDerecha = distancia(extremoDer, ciudades.get(cercanaDerecha));
            cont+=2;
            if (distanciaIzquierda <= distanciaDerecha) {
                // Añadir al inicio
                Punto izquierda = ciudades.get(cercanaIzquierda);
                rutaIzquierda.add(izquierda);
                visitadas[cercanaIzquierda] = true;
                extremoIzq = izquierda;
            } else {
                // Añadir al final
                Punto derecha = ciudades.get(cercanaDerecha);
                rutaDerecha.add(derecha);
                visitadas[cercanaDerecha] = true;
                extremoDer = derecha;
            }
        }
        System.out.println("Soy izquierda");
        for (int i = 0; i < rutaIzquierda.size(); i++) {
            ruta.add(rutaIzquierda.get(i));
            System.out.println(rutaIzquierda.get(i));
        }
        System.out.println("Soy derecha");
        for (int i = rutaDerecha.size() - 1; i >= 0; i--) {
            ruta.add(rutaDerecha.get(i));
            System.out.println(rutaDerecha.get(i));
        }
        System.out.println("");
        System.out.println("soy las distancias calculadas" + cont);
        setCont();
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
            if (!visitadas[i] && Math.abs(origen.getX() - ciudades.get(i).getX()) <= minima) {
                double distancia = distancia(origen, ciudades.get(i));
                cont++;
                if (distancia < minima) {
                    minima = distancia;
                    posicion = i;
                } else {
                    i = ciudades.size();
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
            num = r.nextInt(1, 4000); //genera un número aleatorio entre 1 y 4000
            den = r.nextInt(17) + 7; //genera un aleatorio entre 7 y 17
            x = num / ((double) den + 0.37); //division con decimales
            y = (r.nextInt(1, 4000)) / ((double) (r.nextInt(7, 17)) + 0.37);
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
