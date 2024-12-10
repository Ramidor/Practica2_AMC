/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author raulp
 */
public class Voraces {

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

    public static ArrayList<Punto> vorazUnidireccionalPoda(ArrayList<Punto> ciudades) {
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
                if (distancia < minima) {
                    minima = distancia;
                    posicion = i;
                }
            }
        }
        return posicion;
    }

}
