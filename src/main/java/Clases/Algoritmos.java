package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author raulp
 */
public class Algoritmos {

    private PuntosMin p = new PuntosMin();
    private static int cont = 0, op = 0;
    private static double Dmin = Double.MAX_VALUE;

    public static double distancia(Punto a, Punto b) {
        double x = Math.abs(a.getX() - b.getX());
        double y = Math.abs(a.getY() - b.getY());

        return (double) Math.sqrt((x * x) + (y * y));
    }

    public PuntosMin getPuntosMin() {
        return p;
    }

    public int getCont() {
        return cont;
    }

    public void setCont() {
        cont = 0;
    }

    public void setOp() {
        op = 0;
    }

    public int getOp() {
        return op;

    }

    public PuntosMin BusquedaExahustiva(List<Punto> puntos, int inicio, int fin) {

        p.punt(puntos.get(0), puntos.get(1));
        op += 3;
        double dmin = p.getDistancia();
        op += 14;
        for (int i = inicio; i <= fin; i++) {
            op += 5;
            for (int j = i + 1; j <= fin; j++) {
                double d = distancia(puntos.get(i), puntos.get(j));
                op += 10;
                cont++;
                op += 1;
                if (d < dmin) {
                    dmin = d;
                    p.punt(puntos.get(i), puntos.get(j));
                    op += 4;
                }
            }
            op += 4;
        }

        return p;
    }

    public PuntosMin BusquedaExahustiva11(List<Punto> puntos, int inicio, int fin) {

        PuntosMin p = new PuntosMin(puntos.get(0), puntos.get(1));
        double dmin = p.getDistancia();
        cont++;
        op += 9;
        for (int i = inicio; i < fin; i++) {
            op += 8;
            for (int j = i + 1; j < fin; j++) {
                op += 4;
                if ((puntos.get(j).getX() - puntos.get(i).getX()) < dmin && j <= i + 11) {
                    double d = distancia(puntos.get(i), puntos.get(j));
                    cont++;
                    op += 3;
                    if (d < dmin) {
                        dmin = d;
                        p.punt(puntos.get(i), puntos.get(j));
                        op += 2;
                    }
                } else {
                    j = fin; //o ponemos break y al carajo
                    op++;
                }
                op += 8;
            }
            op += 4;
        }
        return p;
    }

    //MODIFICACION EN CLASE
    public static PuntosMin BusquedaPoda(List<Punto> puntos, int inicio, int fin) {
        quicksort(puntos, inicio, fin); //ordenamos el fichero por la cordenada x

        PuntosMin p = new PuntosMin(puntos.get(0), puntos.get(1));
        double dmin = p.getDistancia();
        cont++;
        op += 9;
        for (int i = inicio; i < fin; i++) {
            op += 8;
            for (int j = i + 1; j < fin; j++) {
                op += 4;
                if ((puntos.get(j).getX() - puntos.get(i).getX()) < dmin) {
                    double d = distancia(puntos.get(i), puntos.get(j));
                    cont++;
                    op += 3;
                    if (d < dmin) {
                        dmin = d;
                        p.punt(puntos.get(i), puntos.get(j));
                        op += 2;
                    }
                } else {
                    j = fin; //o ponemos break y al carajo
                    op++;
                }
                op += 8;
            }
            op += 4;
        }
        return p;
    }

    public static PuntosMin DyVe(List<Punto> puntos) {
        quicksort(puntos, 0, puntos.size() - 1);
        op = +3;
        return DyVe(puntos, 0, puntos.size() - 1);
    }

    public static PuntosMin DyVe(List<Punto> puntos, int izq, int der) {
        PuntosMin rec = new PuntosMin();
        op += 3;
        if (der - izq <= 2) {
            op += 2;
            return BusquedaPoda(puntos, izq, der);
        }

        int medio = (izq + der) / 2;

        Punto puntoMedio = puntos.get(medio);
        op += 6;

        PuntosMin Pi = DyVe(puntos, izq, medio);
        PuntosMin Pd = DyVe(puntos, medio + 1, der);

        double Di = Pi.getDistancia();
        cont++;

        double Dd = Pd.getDistancia();
        cont++;
        Dmin = Math.min(Di, Dd);
        op += 11;
        if (Dmin == Di) {
            rec = new PuntosMin(Pi.getA(), Pi.getB());
            op += 3;
        } else {
            rec = new PuntosMin(Pd.getA(), Pd.getB());
            op += 3;
        }

        ArrayList<Punto> puntosEnRango = new ArrayList<>();
        op += 6;
        for (int i = izq; i <= der; i++) {
            op += 4;
            if (Math.abs(puntos.get(i).getX() - puntoMedio.getX()) < Dmin) {
                puntosEnRango.add(puntos.get(i));
                op++;
            }
            op += 5;
        }
        op += 5;
        for (int i = 0; i < puntosEnRango.size(); i++) {
            op += 6;
            for (int j = i + 1; j < puntosEnRango.size(); j++) {
                double distancia = distancia(puntosEnRango.get(i), puntosEnRango.get(j));

                cont++;
                op += 5;
                if (distancia < Dmin) {
                    Dmin = distancia;
                    rec = new PuntosMin(puntosEnRango.get(i), puntosEnRango.get(j));
                    op += 5;
                }
                op += 6;
            }
            op += 5;
        }

        return rec;
    }

    public PuntosMin DyVeMejorado(List<Punto> puntos) {
        quicksort(puntos, 0, puntos.size() - 1);
        op += 2;
        return DyVeMejorado(puntos, 0, puntos.size() - 1);
    }

    public PuntosMin DyVeMejorado(List<Punto> puntos, int i, int d) {
    int n = (d - i + 1);
    op += 4;
    if (n >= 3) {
        PuntosMin izq = new PuntosMin(), der = new PuntosMin();
        int mitad = (i + d) / 2;
        izq = DyVeMejorado(puntos, i, mitad);
        der = DyVeMejorado(puntos, mitad + 1, d);

        double di = izq.getDistancia();
        double dd = der.getDistancia();
        cont += 2;
        double dmin, dis;
        op += 16;
        if (di <= dd) {
            dmin = di;
            p = izq;
            op += 2;
        } else {
            dmin = dd;
            p = der;
            op += 2;
        }

        List<Punto> puntosAux = new ArrayList<>();
        op += 6;
        for (int k = i; k <= d; k++) {
            op += 4;
            if (Math.abs(puntos.get(k).getX() - puntos.get(mitad).getX()) < dmin) {
                puntosAux.add(puntos.get(k));
                op += 2;
            }
            op += 3;
        }

        quicksorty(puntosAux, 0, puntosAux.size() - 1);
        op += 6;
        for (int w = 0; w < puntosAux.size(); w++) {
            op += 11;
            // Solo comprobamos los 12 siguientes puntos en la lista ordenada
            for (int j = w + 1; j < puntosAux.size() && j < w + 12; j++) {
                if ((puntosAux.get(j).getY() - puntosAux.get(w).getY()) < dmin) {
                    double distancia = distancia(puntosAux.get(w), puntosAux.get(j));
                    cont++;
                    op++;
                    if (distancia < dmin) {
                        dmin = distancia;
                        p = new PuntosMin(puntosAux.get(w), puntosAux.get(j));
                        op += 4;
                    }
                }
                op += 11;
            }
            op += 6;
        }

    } else {
        op += 2;
        p = BusquedaPoda(puntos, i, d);
    }

    return p;
}

    public static void quicksort(List<Punto> puntos, int izq, int der) {

        // tomamos primer elemento como pivote
        Punto pivote = puntos.get(izq);
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        Punto aux;
        op += 5;
        while (i < j) {
            op += 5;// mientras no se crucen las búsquedas                                   
            while ((puntos.get(i).getX() <= pivote.getX()) && (i < j)) {

                i++;// busca elemento mayor que pivote
                op += 7;
            }
            op += 3;
            while (puntos.get(j).getX() > pivote.getX()) {
                j--;// busca elemento menor que pivote
                op += 5;
            }
            op += 1;
            if (i < j) {                        // si no se han cruzado                      
                aux = puntos.get(i);                      // los intercambia
                puntos.set(i, puntos.get(j));
                puntos.set(j, aux);
                op += 5;
            }
            op++;
        }

        puntos.set(izq, puntos.get(j));      // se coloca el pivote en su lugar de forma que tendremos                                    
        puntos.set(j, pivote);      // los menores a su izquierda y los mayores a su derecha
        op += 4;
        if (izq < j - 1) {
            quicksort(puntos, izq, j - 1);
            op++;// ordenamos subarray izquierdo
        }
        op += 2;
        if (j + 1 < der) {
            quicksort(puntos, j + 1, der);
            op++;// ordenamos subarray derecho
        }
    }

    public static void quicksorty(List<Punto> puntos, int izq, int der) {

        // tomamos primer elemento como pivote
        Punto pivote = puntos.get(izq);
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        Punto aux;
        op += 5;
        while (i < j) {
            op += 5;// mientras no se crucen las búsquedas                                   
            while ((puntos.get(i).getY() <= pivote.getY()) && (i < j)) {
                i++; // busca elemento mayor que pivote
                op += 6;
            }
            op += 3;
            while (puntos.get(j).getY() > pivote.getY()) {
                j--;
                op += 4;
                // busca elemento menor que pivote
            }
            op++;
            if (i < j) {                        // si no se han cruzado                      
                aux = puntos.get(i);                      // los intercambia
                puntos.set(i, puntos.get(j));
                puntos.set(j, aux);
                op += 4;
            }
        }

        puntos.set(izq, puntos.get(j));      // se coloca el pivote en su lugar de forma que tendremos                                    
        puntos.set(j, pivote);      // los menores a su izquierda y los mayores a su derecha
        op += 5;
        if (izq < j - 1) {
            quicksorty(puntos, izq, j - 1);
            op++;// ordenamos subarray izquierdo
        }
        op += 2;
        if (j + 1 < der) {
            quicksorty(puntos, j + 1, der);
            op++;// ordenamos subarray derecho
        }
    }

    public void rellenarPuntos(ArrayList<Punto> p, int n, boolean mismax) {
        int num, den;
        double x, y, aux1;
        Random r = new Random(System.currentTimeMillis());
        if (mismax) {
            for (int i = 0; i < n; i++) {
                aux1 = r.nextDouble(7, 1007);
                y = aux1 / ((double) i + 1 + i * 0.100); //aux2; //+(i/3.0);
                num = r.nextInt(0, 3);
                y += ((i % 500) - num * (r.nextInt(0, 100)));
                //y = Math.abs(y);//por si queremos que todos los puntos salgan positivos
                x = 1;
                p.add(new Punto(i + 1, x, y));
            }
        } else { 
            for (int i = 0; i < n; i++) {
                num = r.nextInt(1, 4000); //genera un número aleatorio entre 1 y 4000
                den = r.nextInt(17) + 7; //genera un aleatorio entre 7 y 17
                x = num / ((double) den + 0.37); //division con decimales
                y = (r.nextInt(1, 4000)) / ((double) (r.nextInt(7, 17)) + 0.37);
                p.add(new Punto(i + 1, x, y));
            }
        }
    }

}
