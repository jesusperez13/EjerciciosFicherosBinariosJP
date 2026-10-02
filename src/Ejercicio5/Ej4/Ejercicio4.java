package Ejercicio5.Ej4;

import java.io.*;
import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("¿Cuántos becarios vas a introducir?: ");
        int num = sc.nextInt();
        sc.nextLine();
        File archivo = new File("src/Ejercicio5/Ej4/datosbecabecarios.bin");
        try (FileOutputStream fos = new FileOutputStream(archivo, true);
             DataOutputStream dos = new DataOutputStream(fos)) {

            for (int i = 0; i < num; i++) {
                System.out.println("Becario " + (i + 1));
                System.out.print("Nombre y apellidos: ");
                String nombre = sc.nextLine();
                System.out.print("Sexo (H/M): ");
                char sexo = sc.nextLine().toUpperCase().charAt(0);
                System.out.print("Edad (20-60): ");
                int edad = sc.nextInt();
                System.out.print("Número de suspensos (0-4): ");
                int suspensos = sc.nextInt();
                sc.nextLine();
                System.out.print("¿Residencia familiar? (SI/NO): ");
                String residencia = sc.nextLine().toUpperCase();
                System.out.print("Ingresos anuales de la familia: ");
                double ingresos = sc.nextDouble();
                sc.nextLine();
                System.out.print("¿Tiene beca? (SI/NO): ");
                String tieneBeca = sc.nextLine().toUpperCase();
                dos.writeUTF(nombre);
                dos.writeChar(sexo);
                dos.writeInt(edad);
                dos.writeInt(suspensos);
                dos.writeUTF(residencia);
                dos.writeDouble(ingresos);
                dos.writeUTF(tieneBeca);
            }
        } catch (IOException e) {
            System.err.println("Error de escritura: " + e.getMessage());
        }
        System.out.println("Datos del fichero");
        try (FileInputStream fis = new FileInputStream(archivo);
             DataInputStream dis = new DataInputStream(fis)) {
            while (dis.available() > 0) {
                System.out.println("Su nombre es: " + dis.readUTF() + " su sexo es: " + dis.readChar() + " su edad: " +
                                    dis.readInt() + " años tiene suspensos: " + dis.readInt() + " su residencia familiar es: " +
                                    dis.readUTF() + " sus ingresos son: " + dis.readDouble() + "€" + " y tiene beca: " + dis.readUTF());
            }
        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }
}