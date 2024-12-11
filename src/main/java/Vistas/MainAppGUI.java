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
import javax.swing.table.DefaultTableModel;

public class MainAppGUI extends JFrame {

    private int talla;
    private ArrayList<Punto> puntos = new ArrayList<>();
   
    private ArrayList<Punto> puntosDataset = new ArrayList<>();
    private Voraces v = new Voraces();
    private MetodosVista mv =new MetodosVista();
    private String fichero;

    private final Lectura lec1 = new Lectura();
    //private final Graficas g = new Graficas();

    private JTabbedPane tabbedPane;

    public MainAppGUI() {
        setTitle("Interfaz Gráfica de la Aplicación");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal para botones de opciones
        JPanel panelOpciones = new JPanel(new GridLayout(0, 1));
        // Pestañas para cada tabla de ficheros
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        // Crear y añadir botones al panel de opciones
        JButton btnOpcion1 = new JButton("1. Crear un fichero .tsp aleatorio");
        JButton btnOpcion2 = new JButton("2. Cargar un dataset en memoria");
        JButton btnOpcion3 = new JButton("3. Comprobar estrategias");
        JButton btnOpcion4 = new JButton("4. Comparar todas lasestrategias");
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
            puntos.clear();
            Punto p = new Punto();
            v.rellenarPuntos(puntos, talla);
            lec1.EscribirTSP(puntos, "Dataset(" + talla + ")");
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
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 2: " + e.getMessage());
        }

    }

private void ejecutarOpcion3() {
    try {
        
         tabbedPane.removeAll();
        // Crear panel principal
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
        for (int i = 0; i < 4; i++) {
            ArrayList<String[]> resultados = mv.opcion3(i, puntos);
            puntos = (ArrayList<Punto>) puntosDataset.clone();

            // Agregar resultados al modelo de la tabla
            for (String[] fila : resultados) {
                model.addRow(fila);
            }
        }

        // Crear la tabla y añadirla al panel
        JTable table = new JTable(model);
        panelFichero.add(new JScrollPane(table), BorderLayout.CENTER);


        // Añadir el panel al contenedor principal
        this.add(panelFichero, BorderLayout.CENTER);
        this.revalidate(); // Actualizar la interfaz gráfica
        this.repaint();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error en la opción 3: " + e.getMessage());
        e.printStackTrace(); // Mostrar detalles del error en la consola
    }
}


    private void ejecutarOpcion4() {

    }

    private void ejecutarOpcion5() {

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
