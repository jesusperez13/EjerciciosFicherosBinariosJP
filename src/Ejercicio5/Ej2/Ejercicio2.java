package Ejercicio5.Ej2;

import java.io.*;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número de vehículos a introducir: ");
        int numVehiculos = sc.nextInt();
        sc.nextLine();
        File archivo = new File("src/Ejercicio5/Ej2/vehiculos.bin");
        try (FileOutputStream fos = new FileOutputStream(archivo, true);
             DataOutputStream dos = new DataOutputStream(fos)) {
            for (int i = 0; i < numVehiculos; i++) {
                System.out.println("Vehículo " + (i + 1) + ":");
                System.out.print("Matrícula: ");
                String matricula = sc.nextLine();
                System.out.print("Marca: ");
                String marca = sc.nextLine();
                System.out.print("Tamaño del depósito (litros): ");
                double deposito = sc.nextDouble();
                sc.nextLine();
                System.out.print("Modelo: ");
                String modelo = sc.nextLine();
                dos.writeUTF(matricula);
                dos.writeUTF(marca);
                dos.writeDouble(deposito);
                dos.writeUTF(modelo);
            }
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
        System.out.println("Listado de vehículos");
        try (FileInputStream fis = new FileInputStream(archivo);
             DataInputStream dis = new DataInputStream(fis)) {
            while (dis.available() > 0) {
                String matricula = dis.readUTF();
                String marca = dis.readUTF();
                double deposito = dis.readDouble();
                String modelo = dis.readUTF();
                System.out.println("La matrícula es: " + matricula + " su marca es: " + marca + " tiene de depósito: " + deposito + " litros y el modelo es: " + modelo);
            }
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
    }
}