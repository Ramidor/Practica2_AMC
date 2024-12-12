package Vistas;

import Clases.Lectura;
import Clases.MetodosVista;
import Clases.Punto;
import Clases.PuntosMin;
import Clases.Voraces;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

public class MainAppGUI extends JFrame {

    private int talla, inicio;
    private ArrayList<Punto> puntos = new ArrayList<>();

    private ArrayList<Punto> puntosDataset = new ArrayList<>();
    private Voraces v = new Voraces();
    private MetodosVista mv = new MetodosVista();
    private String fichero;
    private Random r = new Random();
    private final Lectura lec1 = new Lectura();
    //private final Graficas g = new Graficas();

    private JTabbedPane tabbedPane;
    private JLabel nombreFichero;

    public MainAppGUI() {
        setTitle("Interfaz Gráfica de la Aplicación");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal para botones de opciones
        JPanel panelOpciones = new JPanel(new GridLayout(0, 1));
        JPanel panelEstado = new JPanel(new BorderLayout());
        // Pestañas para cada tabla de ficheros
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
        nombreFichero = new JLabel("Fichero cargado: Ninguno", SwingConstants.CENTER);
        panelEstado.add(nombreFichero, BorderLayout.CENTER);
        add(panelEstado, BorderLayout.NORTH);

        // Crear y añadir botones al panel de opciones
        JButton btnOpcion1 = new JButton("1. Crear un fichero .tsp aleatorio");
        JButton btnOpcion2 = new JButton("2. Cargar un dataset en memoria");
        JButton btnOpcion3 = new JButton("3. Comprobar estrategias");
        JButton btnOpcion4 = new JButton("4. Comparar todas las estrategias");
        JButton btnOpcion5 = new JButton("5. Comparar dos estrategias");
        JButton btnOpcion6 = new JButton("6. Comparar unidireccional vs bidireccional");
        JButton btnSalir = new JButton("Salir");

        panelOpciones.add(btnOpcion1);
        panelOpciones.add(btnOpcion2);
        panelOpciones.add(btnOpcion3);
        panelOpciones.add(btnOpcion4);
        panelOpciones.add(btnOpcion5);
        panelOpciones.add(btnOpcion6);

        panelOpciones.add(btnSalir);

        add(panelOpciones, BorderLayout.WEST);

        // Acciones de los botones
        btnOpcion1.addActionListener(e -> ejecutarOpcion1());
        btnOpcion2.addActionListener(e -> ejecutarOpcion2());
        btnOpcion3.addActionListener(e -> ejecutarOpcion3());
        btnOpcion4.addActionListener(e -> ejecutarOpcion4());
        btnOpcion5.addActionListener(e -> ejecutarOpcion5());
        btnOpcion6.addActionListener(e -> ejecutarOpcion6());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void ejecutarOpcion1() {
        try {
            tabbedPane.removeAll();
            String tallaStr = JOptionPane.showInputDialog(this, "Introduce la talla del TSP:");
            talla = Integer.parseInt(tallaStr);
            puntosDataset.clear();
            Punto p = new Punto();
            v.rellenarPuntos(puntosDataset, talla);
            lec1.EscribirTSP(puntosDataset, "Dataset(" + talla + ")");
            fichero = "Dataset(" + talla + ")";
            nombreFichero.setText("Fichero cargado: " + fichero);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 1: " + e.getMessage());
        }
    }

    private void ejecutarOpcion2() {
        try {
            tabbedPane.removeAll();
            fichero = JOptionPane.showInputDialog(this, "Introduce el nombre del archivo:");
            Lectura lec = new Lectura(fichero + ".tsp");
            puntosDataset = lec.getPuntos();
            nombreFichero.setText("Fichero cargado: " + fichero);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 2: " + e.getMessage());
        }

    }

    private void ejecutarOpcion3() {
        try {
            // Limpiar las pestañas anteriores
            tabbedPane.removeAll();

            // Crear panel principal para la pestaña
            JPanel panelFichero = new JPanel(new BorderLayout());

            // Crear etiqueta con el nombre del fichero
            JLabel labelFichero = new JLabel("Nombre del fichero: " + fichero, SwingConstants.CENTER);
            panelFichero.add(labelFichero, BorderLayout.NORTH);

            // Configurar tabla para mostrar datos
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Estrategia");
            model.addColumn("Solución");
            model.addColumn("Calculadas");
            model.addColumn("Tiempo (ms)");

            // Procesar datos y llenar la tabla
            puntos = (ArrayList<Punto>) puntosDataset.clone();
            inicio = r.nextInt(puntos.size() - 1);
            for (int i = 0; i < 4; i++) {
                ArrayList<String[]> resultados = mv.opcion3(i, puntos, inicio);
                puntos = (ArrayList<Punto>) puntosDataset.clone();

                // Agregar resultados al modelo de la tabla
                for (String[] fila : resultados) {
                    model.addRow(fila);
                }
            }

            // Crear la tabla y añadirla al panel
            JTable table = new JTable(model);
            panelFichero.add(new JScrollPane(table), BorderLayout.CENTER);

            // Añadir el panel como una nueva pestaña
            tabbedPane.addTab("Resultados de Opción 3", panelFichero);

            // Actualizar la interfaz gráfica
            revalidate();
            repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 3: " + e.getMessage());
            e.printStackTrace(); // Mostrar detalles del error en la consola
        }
    }

    private void ejecutarOpcion4() {
        try {
            tabbedPane.removeAll();

            JPanel panelFichero = new JPanel(new BorderLayout());
           
            
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Talla");
            model.addColumn("UniDireccional Exhaustivo");
            model.addColumn("Bidireccional Exhaustivo");
            model.addColumn("Unidireccional Poda");
            model.addColumn("Bidireccional Poda");

            ArrayList<String[]> resultados = mv.opcion4();
            for (String[] fila : resultados) {
                model.addRow(fila);
            }

            JTable table = new JTable(model);
            panelFichero.add(new JScrollPane(table), BorderLayout.CENTER);
            tabbedPane.addTab("Todas las Strats", panelFichero);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 4: " + e.getMessage());
        }
    }

    private void ejecutarOpcion5() {       
        
          try {
            tabbedPane.removeAll();

            // Paso 1: Seleccionar estrategias
            String[] opciones = {"1 - Unidirecccional Exhaustivo", "2 - Bidireccional Exhaustivo", "3 - Unidireccional Poda", "4 - Bidireccional Poda"};
            String opcionStr1 = (String) JOptionPane.showInputDialog(this, "Selecciona la primera estrategia:", "Comparación de Estrategias", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            String opcionStr2 = (String) JOptionPane.showInputDialog(this, "Selecciona la segunda estrategia:", "Comparación de Estrategias", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);

            // Validar que el usuario seleccionó ambas estrategias
            if (opcionStr1 == null || opcionStr2 == null || opcionStr1.equals(opcionStr2)) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar dos estrategias diferentes.");
                return;
            }

            // Convertir las opciones seleccionadas a números de estrategia
            int est1 = Integer.parseInt(opcionStr1.split(" ")[0]);
            int est2 = Integer.parseInt(opcionStr2.split(" ")[0]);

            JPanel panelFichero = new JPanel(new BorderLayout());
            // Paso 2: Crear modelo para la tabla
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Talla");
            model.addColumn(opcionStr1 + " - Tiempo (ms)");
            model.addColumn("Distancias Calculadas");
            model.addColumn(opcionStr2 + " - Tiempo (ms)");
            model.addColumn("Distancias Calculadas");

            // Paso 3: Ejecutar comparación para tallas de 500 a 5000
            ArrayList<String[]> resultados = mv.opcion4();

            for (String[] fila : resultados) {
                model.addRow(fila);
            }

            // Mostrar resultados en un nuevo JFrame con JTable
            JTable table = new JTable(model);
            panelFichero.add(new JScrollPane(table), BorderLayout.CENTER);
            tabbedPane.addTab("Estrategia: " + opcionStr1 + " - " + opcionStr2, panelFichero);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 4: " + e.getMessage());
        }
        
    }

    private void ejecutarOpcion6() {

    }

    public static void main(String[] args) throws FileNotFoundException {
        SwingUtilities.invokeLater(() -> {
            MainAppGUI frame = new MainAppGUI();
            frame.setVisible(true);
        });
    }
}
