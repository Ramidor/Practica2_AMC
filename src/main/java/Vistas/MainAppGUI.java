package Vistas;

import Clases.Punto;
import Clases.PuntosMin;
import Clases.Voraces;
import javax.swing.*;
import java.util.ArrayList;

public class MainAppGUI extends JFrame {

    /*
    private int talla;
    private boolean peorcaso = false;
    private ArrayList<Punto> puntos = new ArrayList<>();
    private ArrayList<Punto> puntosAux = new ArrayList<>();
    private final Mostrar mo = new Mostrar();
    private final Lectura lec1 = new Lectura();
    private final Menus menu = new Menus();
    private final Graficas g = new Graficas();

    private JLabel peorCasoLabel;
    private JTabbedPane tabbedPane;

    public MainAppGUI() {
        setTitle("Interfaz Gráfica de la Aplicación");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal para botones de opciones
        JPanel panelOpciones = new JPanel(new GridLayout(0, 1));
        JPanel panelEstado = new JPanel(new BorderLayout());

        // Etiqueta para el estado de "Peor Caso"
        peorCasoLabel = new JLabel("Peor Caso: Desactivado", SwingConstants.CENTER);
        panelEstado.add(peorCasoLabel, BorderLayout.CENTER);
        add(panelEstado, BorderLayout.NORTH);

        // Pestañas para cada tabla de ficheros
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        // Crear y añadir botones al panel de opciones
        JButton btnOpcion1 = new JButton("1. Cargar y mostrar ficheros en tabla");
        JButton btnOpcion2 = new JButton("2. Crear puntos y mostrar estrategias");
        JButton btnOpcion3 = new JButton("3. Generar gráfica de estrategia");
        JButton btnOpcion4 = new JButton("4. Comparar estrategias");
        JButton btnOpcion5 = new JButton("5. Ejecutar todas las estrategias");
        JButton btnOpcion6 = new JButton("6. Cambiar a peor caso");
        JButton btnOpcion7 = new JButton("7. Generar archivo TSP");
        JButton btnOpcion8 = new JButton("8. Cargar archivo TSP");
        JButton btnSalir = new JButton("Salir");

        panelOpciones.add(btnOpcion1);
        panelOpciones.add(btnOpcion2);
        panelOpciones.add(btnOpcion3);
        panelOpciones.add(btnOpcion4);
        panelOpciones.add(btnOpcion5);
        panelOpciones.add(btnOpcion6);
        panelOpciones.add(btnOpcion7);
        panelOpciones.add(btnOpcion8);
        panelOpciones.add(btnSalir);

        add(panelOpciones, BorderLayout.WEST);

        // Acciones de los botones
        btnOpcion1.addActionListener(e -> ejecutarOpcion1());
        btnOpcion2.addActionListener(e -> ejecutarOpcion2());
        btnOpcion3.addActionListener(e -> ejecutarOpcion3());
        btnOpcion4.addActionListener(e -> ejecutarOpcion4());
        btnOpcion5.addActionListener(e -> ejecutarOpcion5());
        btnOpcion6.addActionListener(e -> cambiarPeorCaso());
        btnOpcion7.addActionListener(e -> ejecutarOpcion7());
        btnOpcion8.addActionListener(e -> ejecutarOpcion8());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void ejecutarOpcion1() {
        try {
            // Limpiar pestañas previas
            tabbedPane.removeAll();

            ArrayList<String> ficheros = mo.getFicheros();
            for (String fichero : ficheros) {
                // Crear un nuevo panel para cada fichero
                JPanel panelFichero = new JPanel(new BorderLayout());

                // Crear etiqueta con el nombre del fichero
                JLabel labelFichero = new JLabel("Nombre del fichero: " + fichero, SwingConstants.CENTER);
                panelFichero.add(labelFichero, BorderLayout.NORTH);

                // Configurar tabla para mostrar datos
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Estrategia");
                model.addColumn("Punto1");
                model.addColumn("Punto2");
                model.addColumn("Distancia");
                model.addColumn("Calculadas");
                model.addColumn("Tiempo");

                Lectura lec = new Lectura(fichero);
                puntos = lec.getPuntos();
                puntosAux = (ArrayList<Punto>) puntos.clone();

                for (int i = 1; i < 5; i++) {
                    puntos = (ArrayList<Punto>) puntosAux.clone();
                    ArrayList<String[]> resultados = mo.MostrarExhaustivaTabla(puntos, i);

                    // Agregar resultados al modelo de la tabla
                    for (String[] fila : resultados) {
                        model.addRow(fila);
                    }
                }

                // Crear la tabla y añadirla al panel
                JTable table = new JTable(model);
                panelFichero.add(new JScrollPane(table), BorderLayout.CENTER);

                // Añadir el panel del fichero como una nueva pestaña
                tabbedPane.addTab(fichero, panelFichero);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 1: " + e.getMessage());
        }
    }

    private void ejecutarOpcion2() {
        try {
            tabbedPane.removeAll();
            String tallaStr = JOptionPane.showInputDialog(this, "Introduce la talla deseada:");
            JPanel panelFichero = new JPanel(new BorderLayout());
            talla = Integer.parseInt(tallaStr);
            puntos.clear();
            Punto p = new Punto();
            p.rellenarPuntos(puntos, talla, peorcaso);
            puntosAux = (ArrayList<Punto>) puntos.clone();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Estrategia");
            model.addColumn("Punto1");
            model.addColumn("Punto2");
            model.addColumn("Distancia");
            model.addColumn("Calculadas");
            model.addColumn("Tiempo");
            for (int i = 1; i < 5; i++) {
                puntos = (ArrayList<Punto>) puntosAux.clone();
                ArrayList<String[]> resultados = mo.MostrarExhaustivaTabla(puntos, i);

                // Agregar resultados al modelo de la tabla
                for (String[] fila : resultados) {
                    model.addRow(fila);
                }
            }
            // Crear la tabla y añadirla al panel
            JTable table = new JTable(model);
            panelFichero.add(new JScrollPane(table), BorderLayout.CENTER);
            tabbedPane.addTab("Talla: " + talla, panelFichero);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 2: " + e.getMessage());
        }
    }

    private void ejecutarOpcion3() {
        try {
            tabbedPane.removeAll();
            puntos.clear();
            String[] opciones = {"1 - Exhaustivo", "2 - Exhaustivo con poda", "3 - Divide y vencerás", "4 - DyVe Mejorado"};
            String opcionStr = (String) JOptionPane.showInputDialog(this, "Selecciona una estrategia:", "Opciones", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            int opcion = Integer.parseInt(opcionStr.split(" ")[0]);
            JPanel panelFichero = new JPanel(new BorderLayout());
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Talla");
            model.addColumn("Tiempo");
            model.addColumn("Distancias Calculadas");
            ArrayList<String[]> resultados = mo.Caso3Tabla(puntos, peorcaso, opcion);

            // Agregar resultados al modelo de la tabla
            for (String[] fila : resultados) {
                model.addRow(fila);
            }
            // Crear la tabla y añadirla al panel
            JTable table = new JTable(model);
            panelFichero.add(new JScrollPane(table), BorderLayout.CENTER);
            tabbedPane.addTab("Estrategia: " + opcion, panelFichero);

            g.CrearGrafica(opcion);
            try {
                ImageIcon imagen = new ImageIcon(mo.getEstrategias().get(opcion - 1) + ".png");
                Image imagenEscalada = imagen.getImage().getScaledInstance(450, 333, Image.SCALE_SMOOTH);
                ImageIcon imagenRedimensionada = new ImageIcon(imagenEscalada);
                JLabel labelImagen = new JLabel(imagenRedimensionada);
                panelFichero.add(labelImagen, BorderLayout.SOUTH); // Añadir la imagen en la parte inferior
            } catch (Exception imgEx) {
                System.err.println("Error al cargar la imagen: " + imgEx.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 3: " + e.getMessage());
        }
    }

    private void ejecutarOpcion4() {
        try {
            tabbedPane.removeAll();

            // Paso 1: Seleccionar estrategias
            String[] opciones = {"1 - Exhaustivo", "2 - Exhaustivo con poda", "3 - Divide y vencerás", "4 - DyVe Mejorado"};
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
            ArrayList<String[]> resultados = mo.Caso4(puntos, peorcaso, est1, est2);

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

    private void ejecutarOpcion5() {
        try {
            tabbedPane.removeAll();

            JPanel panelFichero = new JPanel(new BorderLayout());
            // Paso 2: Crear modelo para la tabla
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Talla");
            model.addColumn("Exahustivo");
            model.addColumn("Poda");
            model.addColumn("DyV");
            model.addColumn("DyV Meejorado");

            ArrayList<String[]> resultados = mo.TodasStratTabla(peorcaso, puntos);
            for (String[] fila : resultados) {
                model.addRow(fila);
            }

            JTable table = new JTable(model);
            panelFichero.add(new JScrollPane(table), BorderLayout.CENTER);
            tabbedPane.addTab("Todas las Strats", panelFichero);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 5: " + e.getMessage());
        }
    }

    private void cambiarPeorCaso() {
        peorcaso = !peorcaso;
        peorCasoLabel.setText("Peor Caso: " + (peorcaso ? "Activado" : "Desactivado"));
    }

    private void ejecutarOpcion7() {
        try {
            tabbedPane.removeAll();
            String tallaStr = JOptionPane.showInputDialog(this, "Introduce la talla del TSP:");
            talla = Integer.parseInt(tallaStr);
            puntos.clear();
            Punto p = new Punto();
            p.rellenarPuntos(puntos, talla, peorcaso);
            lec1.EscribirTSP(puntos, "Dataset(" + talla + ")");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 7: " + e.getMessage());
        }
    }

    private void ejecutarOpcion8() {
        try {
            tabbedPane.removeAll();
            String fichero = JOptionPane.showInputDialog(this, "Introduce el nombre del archivo:");

            // Crear un nuevo panel para cada fichero
            JPanel panelFichero = new JPanel(new BorderLayout());

            // Crear etiqueta con el nombre del fichero
            JLabel labelFichero = new JLabel("Nombre del fichero: " + fichero, SwingConstants.CENTER);
            panelFichero.add(labelFichero, BorderLayout.NORTH);

            // Configurar tabla para mostrar datos
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Estrategia");
            model.addColumn("Punto1");
            model.addColumn("Punto2");
            model.addColumn("Distancia");
            model.addColumn("Calculadas");
            model.addColumn("Tiempo");

            Lectura lec = new Lectura(fichero + ".tsp");
            puntos = lec.getPuntos();
            puntosAux = (ArrayList<Punto>) puntos.clone();

            for (int i = 1; i < 5; i++) {
                puntos = (ArrayList<Punto>) puntosAux.clone();
                ArrayList<String[]> resultados = mo.MostrarExhaustivaTabla(puntos, i);

                // Agregar resultados al modelo de la tabla
                for (String[] fila : resultados) {
                    model.addRow(fila);
                }
            }

            // Crear la tabla y añadirla al panel
            JTable table = new JTable(model);
            panelFichero.add(new JScrollPane(table), BorderLayout.CENTER);

            // Añadir el panel del fichero como una nueva pestaña
            tabbedPane.addTab(fichero, panelFichero);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la opción 8: " + e.getMessage());
        }
    }
     */
    public static void main(String[] args) {
        Voraces v = new Voraces();
        ArrayList<Punto> l = new ArrayList<>();
        ArrayList<Punto> b;

        Punto p = new Punto(1, 0.375, 0.951);
        l.add(p);
        Punto s = new Punto(2, 0.732, 0.599);
        l.add(s);

        Punto d = new Punto(3, 0.156, 0.156);
        l.add(d);

        Punto q = new Punto(4, 0.058, 0.866);
        l.add(q);
        PuntosMin pue = new PuntosMin(p, s);
        PuntosMin pue2 = new PuntosMin(p, d);
        PuntosMin pue3 = new PuntosMin(p, q);

        PuntosMin puer = new PuntosMin(s, d);
        PuntosMin puer2 = new PuntosMin(s, q);

        PuntosMin puert = new PuntosMin(d, q);

        System.out.println(pue.getA() + "     " + pue.getB() + "      " + pue.getDistancia());
        System.out.println(pue2.getA() + "     " + pue2.getB() + "      " + pue2.getDistancia());
        System.out.println(pue3.getA() + "     " + pue3.getB() + "      " + pue3.getDistancia());
        System.out.println(puer.getA() + "     " + puer.getB() + "      " + puer.getDistancia());
        System.out.println(puer2.getA() + "     " + puer2.getB() + "      " + puer2.getDistancia());
        System.out.println(puert.getA() + "     " + puert.getB() + "      " + puert.getDistancia());

        v.quicksort(l, 0, l.size() - 1);
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        System.out.println("Ruta Unidirecional");
        b = v.vorazUnidireccionalPoda(l);
        for (int i = 0; i < b.size(); i++) {
            System.out.println(b.get(i));
        }
        // 
        /* 
          for (int i = 0; i < 4; i++) {
            System.out.println(b.get(i).getX()+"            "+b.get(i).getY());
        
        }
          System.out.println("Ruta UnidirecionalPoda");
          b=v.vorazUnidireccionalPoda(l);
       
       // v.quicksort(l, 0, l.size()-1);
       
          for (int i = 0; i < 4; i++) {
            System.out.println(b.get(i).getX()+"            "+b.get(i).getY());
        
        } 
          
          
          
         */
    }
}
