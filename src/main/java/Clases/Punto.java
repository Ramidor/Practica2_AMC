package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author raulp
 */
public class Punto {

    private double x;
    private double y;
    private int id;
    
    public Punto(){
        x = 0;
        y = 0;
    }
    
    public Punto(int id ,double x, double y){
        this.id = id;
        this.x = x;
        this.y = y;
    }
    
  
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public int getId(){
        return id;
    }
    
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    
    public void rellenarPuntos(List<Punto> p, int n, boolean mismax) {
        int num, den;
        double x, y, aux1;
        Random r = new Random();
        r.setSeed(System.currentTimeMillis()); // initialize random seed:
        if (mismax) { //PEOR CASO
            for (int i = 0; i < n; i++) {
                aux1 = r.nextDouble(1000)+7; //7 y 1007
                y = aux1 / ((double) i + 1 + i * 0.100); //aux2; //+(i/3.0);
                num = r.nextInt(3);
                y += ((i % 500) - num * (r.nextDouble(100)));
                x = 1;
                Punto punto = new Punto(i+1,x,y);
                p.add(punto);
            }
        } else { //CASO MEDIO
            for (int i = 0; i < n; i++) {
                num = r.nextInt(4000)+1; //genera un número aleatorio entre 1 y 4000
                den = r.nextInt(11)+7; //genera un aleatorio entre 7 y 17
                x = num / ((double) den + 0.37); //division con decimales
                y = (r.nextInt(4000)+1) / ((double) (r.nextInt(11)+7) + 0.37);
                Punto punto = new Punto(i+1,x,y);
                p.add(punto);
            }
        }
    }
    
    @Override
    public String toString() {
        return "Punto{Id= " + id + ", x=" + x + ", y=" + y + '}';
    }

    void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
