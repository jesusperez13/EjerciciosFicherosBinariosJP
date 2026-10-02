package Ejercicio5.Ej3;

import java.io.*;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre y apellidos: ");
        String nombre = sc.nextLine();
        System.out.print("Sexo (H/M): ");
        char sexo = sc.nextLine().toUpperCase().charAt(0);
        System.out.print("Edad (20-60): ");
        int edad = sc.nextInt();
        System.out.print("Número de suspensos del curso anterior (0-4): ");
        int suspensos = sc.nextInt();
        sc.nextLine();
        System.out.print("¿Residencia familiar? (SI/NO): ");
        String residencia = sc.nextLine().toUpperCase();
        System.out.print("Ingresos anuales de la familia: ");
        double ingresos = sc.nextDouble();
        sc.nextLine();
        System.out.print("¿Tiene beca? (SI/NO): ");
        String tieneBeca = sc.nextLine().toUpperCase();
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/Ejercicio5/Ej3/datosbeca.bin"))) {
            dos.writeUTF(nombre);
            dos.writeChar(sexo);
            dos.writeInt(edad);
            dos.writeInt(suspensos);
            dos.writeUTF(residencia);
            dos.writeDouble(ingresos);
            dos.writeUTF(tieneBeca);
            System.out.println("Datos guardados con éxito en 'datosbeca.bin'");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }
}
