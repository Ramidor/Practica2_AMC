/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author raulp
 */
public class Voraces {

    public ArrayList<Punto> vorazuni(ArrayList<Punto> p, int ini) {
        ArrayList<Punto> visitados = new ArrayList<>();//inicialiazar con false todas las celdas con un for
        ArrayList<Integer> ruta = new ArrayList<>();
        visitados.sort((p1, p2) -> Double.compare(p1.getX(), p2.getX()));
        visitados.
        ruta[0] = ini;
        visitados[ini] = true;
        for (int i = 1; i < p.length; i++) {
            ini = ciudadMasCercana(p, visitados, ini);//ini ciudad mas cercana
            ruta[i] = ini;
            visitados[ini] = true;
        }
        ruta[i] = ruta[0];

        return ruta;
    }

    public int[] vorazuniPoda(puntos p[], int ini) {
        Punto pini = p[ini];
        //ordenar p por eje x
        ini = buscar(p, pini);
        boolean visitados = new boolean[p.lenght()];//inicialiazar con false todas las celdas con un for
        int ruta = new int[p.lenght + 1];
        ruta[0] = ini;
        visitados[ini] = true;
        for (int i = 1; i < p.lenght; i++) {
            ini = ciudadMasCercanaPoda(p, visitados, ini);//ini ciudad mas cercana
            ruta[i] = ini;
            visitados[ini] = true;
        }
        ruta[i] = ruta[0];

        return ruta;
    }

    public int ciudadMasCercana(puntos p[], boolean v[], int ini) {
        double dist, dmin = Double.MAX_VALUE;
        int d = -1;
        for (int i = 0; i < p.length(); i++) {
            if (i != ini && v[i] == false) {
                dist = distancia(p[ini], p[i]);
                if (dist < dmin) {
                    dmin = dist;
                    d = i;
                }
            }
        }
        return d;
    }

    //debe buscar a derecha e izquierda, adaptar el poda que tenemos
    public int ciudadMasCercanaPoda(puntos p[], boolean v[], int ini) {
        double dist, dmin = Double.MAX_VALUE;
        int d = -1;
        for (int i = 0; i < p.length(); i++) {
            if (i != ini && v[i] == false) {
                dist = distancia(p[ini], p[i]);
                if (dist < dmin) {
                    dmin = dist;
                    d = i;
                }
            }
        }
        return d;
    }

    public ArrayList<Punto> rellenarPuntos(int n) {
        ArrayList<Punto> p = new ArrayList<>();
        int num, den;
        double x, y, aux1;
        Random r = new Random();
        r.setSeed(System.currentTimeMillis()); // initialize random seed:

        for (int i = 0; i < n; i++) {
            num = r.nextInt(4000) + 1; //genera un número aleatorio entre 1 y 4000
            den = r.nextInt(11) + 7; //genera un aleatorio entre 7 y 17
            x = num / ((double) den + 0.37); //division con decimales
            y = (r.nextInt(4000) + 1) / ((double) (r.nextInt(11) + 7) + 0.37);
            Punto punto = new Punto(i + 1, x, y);
            p.add(punto);
        }

        return p;
    }
}
