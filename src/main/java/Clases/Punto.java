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

    public Punto() {
        x = 0;
        y = 0;
    }

    public Punto(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return x;
    }

    public double getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Punto{Id= " + id + ", x=" + x + ", y=" + y + '}';
    }

    void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
