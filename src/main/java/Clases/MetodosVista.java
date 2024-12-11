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
   
    
    public ArrayList<String[]> opcion3(int tipo, ArrayList<Punto> ciudades,int ini) {
        ArrayList<String[]> resultados = new ArrayList<>();
         ArrayList<String> strats = new ArrayList<>();
        v.setCont();
        inicio = System.nanoTime();
        if (tipo == 0) {
            
            ruta=v.vorazUnidireccional(ciudades,ini);
        }
        if (tipo == 1) {
            ruta=v.vorazBidireccional(ciudades,ini);
        }
        if (tipo == 2) {
           ruta=v.vorazUnidireccionalPoda(ciudades,ini);
        }
        if (tipo == 3) {
            ruta=v.vorazBidireccionalPoda(ciudades,ini);
        }
        fin = System.nanoTime();
        Tejecucion = fin - inicio;
        strats=getEstrategias();
        String[] fila = new String[]{
           strats.get(tipo), // Estrategia
            String.valueOf(v.getSolucion()), // ID del primer punto
            String.valueOf(v.getCont()), // Calculo de operaciones
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
