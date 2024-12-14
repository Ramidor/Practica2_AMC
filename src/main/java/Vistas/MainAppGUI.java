package Vistas;

import Clases.Lectura;
import Clases.MetodosVista;
import Clases.Punto;
import Clases.Voraces;
import java.awt.BorderLayout;
import java.awt.GridLayout;

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
    private Random r = new Random(System.currentTimeMillis());
    private final Lectura lec1 = new Lectura();
    //private final Graficas g = new Graficas();

    private JTabbedPane tabbedPane;
    private JLabel nombreFichero;

    public MainAppGUI() {
        setTitle("Interfaz Gráfica de la Aplicación");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

       
        JPanel panelOpciones = new JPanel(new GridLayout(0, 1));
        JPanel panelEstado = new JPanel(new BorderLayout());
        
        
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
        nombreFichero = new JLabel("Fichero cargado: Ninguno", SwingConstants.CENTER);
        panelEstado.add(nombreFichero, BorderLayout.CENTER);
        add(panelEstado, BorderLayout.NORTH);

       
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
            
            tabbedPane.removeAll();

            
            JPanel panelFichero = new JPanel(new BorderLayout());

           
            JLabel labelFichero = new JLabel("Nombre del fichero: " + fichero, SwingConstants.CENTER);
            panelFichero.add(labelFichero, BorderLayout.NORTH);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Estrategia");
            model.addColumn("Solución");
            model.addColumn("Calculadas");
            model.addColumn("Tiempo (ms)");

           
            
            puntos = (ArrayList<Punto>) puntosDataset.clone();
            inicio = r.nextInt(puntos.size() - 1);
            for (int i = 0; i < 4; i++) {
                ArrayList<String[]> resultados = mv.opcion3(i, puntos, inicio);
                puntos = (ArrayList<Punto>) puntosDataset.clone();

                
                for (String[] fila : resultados) {
                    model.addRow(fila);
                }
            }

            
            JTable table = new JTable(model);
            panelFichero.add(new JScrollPane(table), BorderLayout.CENTER);

            
            tabbedPane.addTab("Resultados de Opción 3", panelFichero);

            
            revalidate();
            repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 3: " + e.getMessage());
            e.printStackTrace();  
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

            
            String[] opciones = {"1 - Unidirecccional Exhaustivo", "2 - Bidireccional Exhaustivo", "3 - Unidireccional Poda", "4 - Bidireccional Poda"};
            String opcionStr1 = (String) JOptionPane.showInputDialog(this, "Selecciona la primera estrategia:", "Comparación de Estrategias", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            String opcionStr2 = (String) JOptionPane.showInputDialog(this, "Selecciona la segunda estrategia:", "Comparación de Estrategias", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);

       
            if (opcionStr1 == null || opcionStr2 == null || opcionStr1.equals(opcionStr2)) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar dos estrategias diferentes.");
                return;
            }

           
            int est1 = Integer.parseInt(opcionStr1.split(" ")[0]);
            int est2 = Integer.parseInt(opcionStr2.split(" ")[0]);

            JPanel panelFichero = new JPanel(new BorderLayout());
           
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Talla");
            model.addColumn(opcionStr1 + " - Tiempo (ms)");
            model.addColumn("Distancias Calculadas");
            model.addColumn(opcionStr2 + " - Tiempo (ms)");
            model.addColumn("Distancias Calculadas");

           
            ArrayList<String[]> resultados = mv.opcion5(est1, est2);

            for (String[] fila : resultados) {
                model.addRow(fila);
            }

           
            JTable table = new JTable(model);
            panelFichero.add(new JScrollPane(table), BorderLayout.CENTER);
            tabbedPane.addTab("Estrategia: " + opcionStr1 + " - " + opcionStr2, panelFichero);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 5: " + e.getMessage());
        }

    }

    private void ejecutarOpcion6() {
        try {
            tabbedPane.removeAll();

            for (int i = 0; i < 2; i++) {
                JPanel panelFichero = new JPanel(new BorderLayout());
               
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Talla");
                model.addColumn("Veces mejor Unidireccional");
                model.addColumn("Tiempo (ms)");
                model.addColumn("Veces mejor Bidireccional");
                model.addColumn("Tiempo (ms)");

               
                ArrayList<String[]> resultados = mv.opcion6(i);

                for (String[] fila : resultados) {
                    model.addRow(fila);
                }

                
                JTable table = new JTable(model);
                panelFichero.add(new JScrollPane(table), BorderLayout.CENTER);
                if(i==0)
                    tabbedPane.addTab("Estrategia: Exhaustivo", panelFichero);
                else
                   tabbedPane.addTab("Estrategia: Poda", panelFichero); 
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 6: " + e.getMessage());
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        SwingUtilities.invokeLater(() -> {
            MainAppGUI frame = new MainAppGUI();
            frame.setVisible(true);
        });
    }
}
