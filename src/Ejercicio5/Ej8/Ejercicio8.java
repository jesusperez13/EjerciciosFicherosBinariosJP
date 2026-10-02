package Ejercicio5.Ej8;

import java.io.*;
import java.util.Scanner;

public class Ejercicio8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el día de septiembre: ");
        int diaBuscado = sc.nextInt();
        try (BufferedReader br = new BufferedReader(new FileReader("src/Ejercicio5/Ej8/temperaturas.txt"));
             DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/Ejercicio5/Ej8/Septemp.dat"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.replaceAll("[^0-9]+", " ").trim().split("\\s+");
                if (partes.length >= 4) {
                    int dia = Integer.parseInt(partes[0]);
                    if (dia == diaBuscado) {
                        int hora = Integer.parseInt(partes[1]);
                        double temperatura = Double.parseDouble(partes[3]);
                        dos.writeInt(dia);
                        dos.writeInt(hora);
                        dos.writeDouble(temperatura);
                    }
                }
            }
            System.out.println("Fichero generado correctamente para el día " + diaBuscado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}