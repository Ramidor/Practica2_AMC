/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Adria
 */
public class MetodosVista {

    private long inicio, fin, Tejecucion;
    private Voraces v = new Voraces();
    private ArrayList<Punto> ruta = new ArrayList<>();
    private Random r = new Random(System.currentTimeMillis());

    public ArrayList<String[]> opcion3(int tipo, ArrayList<Punto> ciudades, int ini) {
        ArrayList<String[]> resultados = new ArrayList<>();
        ArrayList<String> strats = new ArrayList<>();
        v.setCont();
        inicio = System.nanoTime();
        if (tipo == 0) {
            ruta = v.vorazUnidireccional(ciudades, ini);
        }
        if (tipo == 1) {
            ruta = v.vorazBidireccional(ciudades, ini);
        }
        if (tipo == 2) {
            ruta = v.vorazUnidireccionalPoda(ciudades, ini);
        }
        if (tipo == 3) {
            ruta = v.vorazBidireccionalPoda(ciudades, ini);
        }
        fin = System.nanoTime();
        Tejecucion = fin - inicio;
        strats = getEstrategias();
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

    public ArrayList<String[]> opcion4() throws IOException {
        ArrayList<Punto> puntos=new ArrayList<>();
        int talla = 1000;
        double experimentos=10.0;
        long Tejecucion1 = 0, Tejecucion2 = 0, Tejecucion3 = 0, Tejecucion4 = 0;
      
        ArrayList<String[]> resultados = new ArrayList<>();

        while (talla <= 5000) {  
            for (int i = 0; i < experimentos; i++) {
                v.rellenarPuntos(puntos, talla);
                ArrayList<Punto> puntosCopia = (ArrayList<Punto>) puntos.clone();
                int ini = r.nextInt(puntos.size() - 1);
                inicio = System.currentTimeMillis();

                v.vorazUnidireccional(puntos, ini);
                fin = System.currentTimeMillis();

                Tejecucion1 += fin - inicio;

                inicio = System.currentTimeMillis();
                 v.vorazBidireccional(puntos, ini);
                fin = System.currentTimeMillis();

                Tejecucion2 += fin - inicio;
                inicio = System.currentTimeMillis();
                v.vorazUnidireccionalPoda(puntos, ini);
                fin = System.currentTimeMillis();

                Tejecucion3 += fin - inicio;

                puntos = (ArrayList<Punto>) puntosCopia.clone();
                inicio = System.currentTimeMillis();
                v.vorazBidireccionalPoda(puntos, ini);
                fin = System.currentTimeMillis();

                Tejecucion4 += fin - inicio;
                  puntos.clear();
            }

            String[] fila = new String[]{
                String.valueOf(talla), // Talla
                String.format("%.4f", Tejecucion1 / experimentos), // Tiempo
                String.format("%.4f", Tejecucion2 / experimentos), // Tiempo
                String.format("%.4f", Tejecucion3 / experimentos), // Tiempo
                String.format("%.4f", Tejecucion4 / experimentos), // Tiempo
            };
            resultados.add(fila);

            talla += 1000; // Incrementar talla
        }

        return resultados;
    }

}
