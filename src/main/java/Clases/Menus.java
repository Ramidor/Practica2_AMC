/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Scanner;

/**
 *
 * @author raulp
 */
public class Menus {

    int op;
    boolean peorcaso;

    public int menuPrincipal(boolean peorcaso) {
        if (peorcaso) {
            System.out.println("Peor Caso: Si");
        } else {
            System.out.println("Peor Caso: No");
        }
        System.out.println("1. Comprobar todos los dataset (Ficheros).");//easy
        System.out.println("2. Comprobar todas las estrategias.");
        System.out.println("3. Estudiar una estrategia.");
        System.out.println("4. Comparar dos estrategias.");
        System.out.println("5. Comparar todas las estrategias.");
        System.out.println("6. Activar/dasactivar peor caso (Todos los puntos en la misma vertical).");//easy
        System.out.println("7. Crear fichero tsp aleatorio.");
        System.out.println("8. Comparar todas las estrategias de un fichero tsp concreto.");
        System.out.println("0. Salir.");
        System.out.println("-----------------");
        System.out.println("Elige opcion: ");

        Scanner cap = new Scanner(System.in);
        op = cap.nextInt();
        while (op < 0 || op > 8) {
            System.out.println("La opcion debe estar entre 0 y 8, intentalo de nuevo");
            op = cap.nextInt();
        }

        return op;
    }

    public int menu3() {

        System.out.println("*** Estrategia a estudiar para el caso medio***");
        System.out.println("");
        System.out.println("1.- Exhaustivo");
        System.out.println("2.- ExhaustivoPoda");
        System.out.println("3.- DyVe");
        System.out.println("4.- DyVeMejorado");
        System.out.println("0.- Volver al menu principal");
        System.out.println("");
        System.out.println("----------");
        System.out.print("Elige una opcion: ");
        Scanner cap = new Scanner(System.in);
        op = cap.nextInt();
        while (op < 0 || op > 4) {
            System.out.print("Elige una opcion: ");
            op = cap.nextInt();
        }

        return op;
    }
}
