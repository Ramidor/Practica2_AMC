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
        ArrayList<Punto> ciudadesordenadas = (ArrayList<Punto>) ciudades.clone();

        boolean[] visitadas = new boolean[ciudadesordenadas.size()];
        Arrays.fill(visitadas, false);
        Random r = new Random();
        int posAlea = r.nextInt(ciudades.size());

        Punto ciudadActual = ciudades.get(posAlea);
        visitadas[posAlea] = true;
        ruta.add(ciudadActual);

        while (ruta.size() < ciudades.size()) {
            int posicion = ciudadMasCercana(ciudadActual, ciudades, visitadas);
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

}
