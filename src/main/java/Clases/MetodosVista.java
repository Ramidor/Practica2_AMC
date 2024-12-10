/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author Adria
 */
public class MetodosVista {

    private long inicio, fin, Tejecucion;
    private Voraces v = new Voraces();
    private ArrayList<Punto> ruta = new ArrayList<>();
   
    
    public ArrayList<String[]> opcion3(int tipo, ArrayList<Punto> ciudades) {
        ArrayList<String[]> resultados = new ArrayList<>();
        v.setCont();

        inicio = System.nanoTime();
        if (tipo == 1) {
            ruta=v.vorazUnidireccional(ciudades);
        }
        if (tipo == 2) {
            ruta=v.vorazBidireccional(ciudades);
        }
        if (tipo == 3) {
           ruta=v.vorazUnidireccionalPoda(ciudades);
        }
        if (tipo == 4) {
            ruta=v.vorazBidireccionalPoda(ciudades);
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

    public ArrayList<String> getEstrategias() {
        ArrayList<String> estrategias = new ArrayList<String>();
        estrategias.add("Unidireccional Exhaustivo");
        estrategias.add("Bidireccional Exhaustivo");
        estrategias.add("Unidireccional con Poda");
        estrategias.add("Bidireccional con Poda");

        return estrategias;
    }

}
