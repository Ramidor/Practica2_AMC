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

    public Voraces() {

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

        return ruta;
    }

    public static ArrayList<Punto> vorazBidireccional(ArrayList<Punto> ciudades) {
        ArrayList<Punto> ruta = new ArrayList<>();
        Random r = new Random();
        int ciudadInicio = r.nextInt(ciudades.size());
        Punto inicio = ciudades.get(ciudadInicio);
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);
        visitadas[ciudadInicio] = true;
        ruta.add(inicio);
        Punto extremoInicio = inicio;
        Punto extremoFinal = inicio;
        while (ruta.size() <= ciudades.size()) {
            int cercanaInicioIdx = ciudadMasCercana(extremoInicio, ciudades, visitadas);
            int cercanaFinalIdx = ciudadMasCercana(extremoFinal, ciudades, visitadas);
            
            double distanciaInicio = distancia(extremoInicio, ciudades.get(cercanaInicioIdx));
            double distanciaFinal = distancia(extremoFinal, ciudades.get(cercanaFinalIdx));
            
            if (distanciaInicio < distanciaFinal) {
                // Añadir al inicio
                Punto cercanaInicio = ciudades.get(cercanaInicioIdx);
                ruta.add(0, cercanaInicio);
                visitadas[cercanaInicioIdx] = true;
                extremoInicio = cercanaInicio;
            } else {
                // Añadir al final
                Punto cercanaFinal = ciudades.get(cercanaFinalIdx);
                ruta.add(cercanaFinal);
                visitadas[cercanaFinalIdx] = true;
                extremoFinal = cercanaFinal;
            }
        }
        ruta.add(inicio);

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

        return ruta;
    }

    public static ArrayList<Punto> vorazBidireccionalPoda(ArrayList<Punto> ciudades) {
        ArrayList<Punto> ruta = new ArrayList<>();
        Random r = new Random();
        int posAlea = r.nextInt(ciudades.size()), posIzq, posDer, extremoIzq, extremoDer;
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);
        visitadas[posAlea] = true;
        ruta.add(ciudades.get(posAlea));
        ruta.add(ciudades.get(posAlea));
        posIzq = 0;
        posDer = ruta.size() - 1;
        while (ruta.size() <= ciudades.size()) {
            extremoDer = ciudadMasCercana(ruta.get(posDer), ciudades,
                    visitadas);
            if (ruta.get(posIzq) != ruta.get(posDer)) {
                extremoIzq = ciudadMasCercana(ruta.get(posIzq), ciudades,
                        visitadas);
                if (distancia(ciudades.get(extremoIzq), ruta.get(posIzq))
                        < distancia(ciudades.get(extremoDer), ruta.get(posDer))) {
                    visitadas[extremoIzq] = true;
                    posIzq++;
                    posDer++;
                    ruta.add(posIzq, ciudades.get(extremoIzq));
                } else {
                    visitadas[extremoDer] = true;
                    ruta.add(posDer, ciudades.get(extremoDer));
                }
            } else {
                visitadas[extremoDer] = true;
                ruta.add(posDer, ciudades.get(extremoDer));
            }
        }
        return ruta;
    }

    private static int ciudadMasCercana(Punto origen, ArrayList<Punto> ciudades, boolean[] visitadas) {
        int posicion = -1;
        double minima = Double.MAX_VALUE;
        for (int i = 0; i < ciudades.size(); i++) {
            if (!visitadas[i]) {
                double distancia = distancia(origen, ciudades.get(i));
                System.out.println("DISTANCIA BOY " + distancia);
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
                System.out.println("DISTANCIA BOY poda " + distancia);
                if (distancia < minima) {
                    minima = distancia;
                    posicion = i;
                } else {
                    i = ciudades.size();
                }
            }
        }
        System.out.println("Soy la distancia minima poda: " + minima);
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
