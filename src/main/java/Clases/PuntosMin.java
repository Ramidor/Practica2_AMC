/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author raulp
 */
public class PuntosMin {
    private Punto a, b;
    
    public PuntosMin(){
    }
    
    public PuntosMin(Punto a, Punto b){
        this.a = a;
        this.b = b;
    }
    
    public void punt(Punto a, Punto b){
        this.a = a;
        this.b = b;
    }
    
    public Punto getA(){
        return a;
    }
    
    public Punto getB(){
        return b;
    }

    public void setA(Punto a) {
        this.a = a;
    }

    public void setB(Punto b) {
        this.b = b;
    }
    
    public double getDistancia() {
        double x = Math.abs(a.getX() - b.getX());
        double y = Math.abs(a.getY() - b.getY());

        return (double) Math.sqrt((x * x) + (y * y));
    }
}