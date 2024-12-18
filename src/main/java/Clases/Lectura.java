package Clases;

import java.io.*;
import java.util.*;

/**
 *
 * @author raulp
 */
public class Lectura {

    BufferedReader reader;
    BufferedWriter writer;
    ArrayList<Punto> puntos;
    ArrayList<Integer> ops;
    ArrayList<Integer> array;

    public Lectura(String file) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
        puntos = new ArrayList<Punto>();
        ops = new ArrayList<Integer>();
        array = new ArrayList<Integer>();

        try {
            Leer();
            Cerrar();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e);
        }
    }

    public void Lectura2(String file) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
        puntos = new ArrayList<Punto>();
        ops = new ArrayList<Integer>();
        array = new ArrayList<Integer>();

        try {
            Leer2();
            Cerrar();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e);
        }
    }

    public Lectura() {
    }

    public ArrayList<Punto> getPuntos() {
        return puntos;
    }

    public ArrayList<Integer> getOps() {
        return ops;
    }

    void Leer() throws IOException {
        String line = reader.readLine();

        while (!line.contains("NODE_COORD_SECTION")) {
            line = reader.readLine();
        }

        while (!"EOF".equals(line = reader.readLine())) {
            String[] data = line.split(" ");
            puntos.add(new Punto((int) Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2])));
        }
    }

    void Leer2() throws IOException {
        try {
            String datos;
            while ((datos = reader.readLine()) != null) {
                // Saltar la primera línea si es el encabezado
                if (datos.startsWith("Talla")) {
                    continue;
                }

                // Comprobar si la línea está vacía
                if (datos.trim().isEmpty()) {
                    continue; // Pasar a la siguiente línea
                }

                // Dividir la línea en partes usando el separador "-----"
                String[] partes = datos.split("-----");

                // Verificar que la línea esté bien formateada (debe tener 3 partes)
                if (partes.length != 3) {
                    System.err.println("Formato incorrecto en la línea: " + datos);
                    continue; // Saltar esta línea y seguir con la siguiente
                }

                try {
                    // Leer el valor de Tejecucion (long) y el valor de ops (int)
                    int operaciones = Integer.parseInt(partes[2].trim());
                    ops.add(operaciones);

                } catch (NumberFormatException e) {
                    System.err.println("Error al convertir un número en la línea: " + datos + ". Detalle: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer la línea: " + e.getMessage());
        }
    }

    void Cerrar() throws IOException {
        reader.close();
    }

    public void Escrito(List<Punto> puntos) {
        for (int i = 0; i < puntos.size(); i++) {
            System.out.println("x: " + puntos.get(i).getX()
                    + "   y: " + puntos.get(i).getY());
        }
    }

    public void EscribirTSP(List<Punto> puntos, String file) throws IOException {

        File archivo = new File(file + ".tsp");

        FileWriter escribir = new FileWriter(archivo, false);

        escribir.write("NAME: " + file + "\n");
        escribir.write("COMMENT: " + "\n");
        escribir.write("TYPE: TSP" + "\n");
        escribir.write("DIMENSION: " + puntos.size() + "\n");
        escribir.write("EDGE_WEIGHT_TYPE: EUC_2D" + "\n");
        escribir.write("NODE_COORD_SECTION" + "\n");

        for (int i = 0; i < puntos.size(); i++) {
            escribir.write(puntos.get(i).getId() + " " + puntos.get(i).getX() + " " + puntos.get(i).getY() + "\n");
        }
        escribir.write("EOF");

        escribir.close();
    }

    public void escribirTspRuta(ArrayList<Punto> ruta, String file, ArrayList<Double> distancias) throws IOException {

        File archivo = new File(file + ".tsp");
        double total = 0;
        FileWriter escribir = new FileWriter(archivo, false);
        for (int i = 0; i < distancias.size(); i++) {
            total += distancias.get(i);
        }
        escribir.write("NAME: " + file + "\n");
        escribir.write("TYPE: TSP" + "\n");
        escribir.write("DIMENSION: " + (ruta.size()-1) + "\n");
        escribir.write("SOLUTION: " + total + "\n");
        escribir.write("TOUR_SECTION" + "\n");
        
        for (int i = 0; i < ruta.size(); i++) {
            escribir.write(ruta.get(i).getId() + ", ");
        }
        System.out.println(ruta.size());
        System.out.println(distancias.size()+ "\n");
        escribir.write("\n");

        for (int i = 0; i < distancias.size(); i++) {
            escribir.write(distancias.get(i) + " - " + ruta.get(i).getId() + ", " + ruta.get(i+1).getId() + "\n");
        }
        escribir.write("EOF");

        escribir.close();
    }

}
