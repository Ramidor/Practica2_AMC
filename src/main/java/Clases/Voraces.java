/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;

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
    private Punto p;
    private double dmin = Integer.MAX_VALUE;

    public ArrayList<Punto> vorazuni(ArrayList<Punto> puntos, int ini) {
        ArrayList<Punto> visitados = new ArrayList<Punto>();
        ArrayList<Punto> novisitados = (ArrayList<Punto>) puntos.clone();
        visitados.add(novisitados.get(ini));
        
        for (int i = 0; i < novisitados.size(); i++) {
            p=ciudadCercana(novisitados, ini);
            visitados.add(p);
            ini=novisitados.
        }
        
      
        return ruta;
    }

    public Punto ciudadCercana(ArrayList<Punto> novisitados, int ini){
         double distanciapuntos;
         Punto puntos;
         for (int i = 0; i < novisitados.size(); i++) {
             distanciapuntos= distancia(novisitados.get(ini), novisitados.get(i));
            if (distanciapuntos <= dmin) {
                dmin=distanciapuntos;
                 puntos=novisitados.get(i);
            }
        }
         novisitados.remove(ini);
        
        return p;
    
    }/*
    
        public int[] vorazuniPoda(puntos p[], int ini){
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
    }*/
}
