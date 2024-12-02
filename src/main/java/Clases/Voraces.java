/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author raulp
 */
public class Voraces {

    /*public int[] vorazuni(puntos p[], int ini){
        boolean visitados = new boolean[p.lenght()];//inicialiazar con false todas las celdas con un for
        int ruta = new int[p.lenght+1];
        ruta[0] = ini;
        visitados[ini] = true;
        for(int i = 1; i<p.lenght; i++){
            ini = ciudadMasCercana(p, visitados, ini);//ini ciudad mas cercana
            ruta[i] = ini;
            visitados[ini] = true;
        }
        ruta[i] = ruta[0];
        
        return ruta;
    }
    
        public int[] vorazuniPoda(puntos p[], int ini){
        Punto pini = p[ini];
        //ordenar p por eje x
        ini = buscar(p, pini);
        boolean visitados = new boolean[p.lenght()];//inicialiazar con false todas las celdas con un for
        int ruta = new int[p.lenght+1];
        ruta[0] = ini;
        visitados[ini] = true;
        for(int i = 1; i<p.lenght; i++){
            ini = ciudadMasCercanaPoda(p, visitados, ini);//ini ciudad mas cercana
            ruta[i] = ini;
            visitados[ini] = true;
        }
        ruta[i] = ruta[0];
        
        return ruta;
    }
    
    public int ciudadMasCercana(puntos p[], boolean v[], int ini){
        double dist ,dmin = Double.MAX_VALUE;
        int d = -1;
        for(int i = 0; i<p.length(); i++){
            if(i != ini && v[i] == false){
               dist =  distancia(p[ini], p[i]);
               if(dist<dmin){
                   dmin=dist;
                   d = i;
               }
            }
        }
        return d;
    }
    
    
    //debe buscar a derecha e izquierda, adaptar el poda que tenemos
    public int ciudadMasCercanaPoda(puntos p[], boolean v[], int ini){
        double dist ,dmin = Double.MAX_VALUE;
        int d = -1;
        for(int i = 0; i<p.length(); i++){
            if(i != ini && v[i] == false){
               dist =  distancia(p[ini], p[i]);
               if(dist<dmin){
                   dmin=dist;
                   d = i;
               }
            }
        }
        return d;
    }*/
}
