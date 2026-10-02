package Ejercicio5.Ej1;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cantidad de números aleatorios a generar: ");
        int cantidad = sc.nextInt();
        System.out.print("Límite inferior del rango: ");
        int min = sc.nextInt();
        System.out.print("Límite superior del rango: ");
        int max = sc.nextInt();

        File archivo = new File("src/Ejercicio5/Ej1/num_aleat.bin");
        Random rand = new Random();

        try (FileOutputStream fos = new FileOutputStream(archivo, true);
             DataOutputStream dos = new DataOutputStream(fos)) {
            for (int i = 0; i < cantidad; i++) {
                int num = rand.nextInt(max - min + 1) + min;
                dos.writeInt(num);
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
        }
        System.out.println("\nContenido del fichero 'num_aleat.bin':");
        try (FileInputStream fis = new FileInputStream(archivo);
             DataInputStream dis = new DataInputStream(fis)) {
            while (dis.available() > 0) {
                System.out.println(dis.readInt());
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}