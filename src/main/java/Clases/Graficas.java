/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Adria
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Graficas {
    public Graficas(){}
    Mostrar mo=new Mostrar();
    ArrayList<Integer> op = new ArrayList<>();
    public void CrearGrafica(int estrategia) throws FileNotFoundException, IOException {
        Lectura lec = new Lectura();
        lec.Lectura2(mo.estrategias.get(estrategia-1) + "Grafica.dat");
        lec.Leer2();
        op=lec.getOps();
        // Crear un conjunto de datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < op.size(); i++) {
            dataset.addValue(op.get(i), "Operaciones elementales", ""+500*(i+1));
        }
       
        // Crear el gráfico de líneas
        JFreeChart chart = ChartFactory.createLineChart(
                "Coste" + mo.estrategias.get(estrategia-1), // Título del gráfico
                "Talla",  // Etiqueta del eje X
                "Operaciones elementales", // Etiqueta del eje Y
                dataset              // Datos
        );

        // Guardar el gráfico como archivo de imagen
        try {
            ChartUtilities.saveChartAsPNG(new File(mo.estrategias.get(estrategia-1) + ".png"), chart, 800, 600);
            System.out.println("Gráfico guardado en " + mo.estrategias.get(estrategia-1)+".png");
        } catch (IOException e) {
            System.err.println("Error al guardar el gráfico: " + e.getMessage());
        }
    }
}