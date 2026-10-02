package Ejercicio5.Ej5;

import java.io.*;

public class Ejercicio5 {
    public static void main(String[] args) {
        String archivoBeca = "src/Ejercicio5/Ej3/datosbeca.bin";
        File fichero = new File(archivoBeca);
        if (!fichero.exists()) {
            System.out.println("El fichero no existe, primero crealo en el Ej3");
            return;
        }
        System.out.println("Listado de becarios y cuantías concedidas ");
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
            while (true) {
                String nombreCompleto = dis.readUTF();
                char sexo = dis.readChar();
                int edad = dis.readInt();
                int numSuspensos = dis.readInt();
                String residenciaFamiliar = dis.readUTF();
                double ingresosAnuales = dis.readDouble();
                String tieneBecaSolicitada = dis.readUTF();
                if (!tieneBecaSolicitada.equalsIgnoreCase("SI")) {
                    continue;
                }
                if (numSuspensos >= 2) {
                    continue;
                }
                double cuantiaBeca = 1500.0;
                if (ingresosAnuales <= 12000.0) {
                    cuantiaBeca += 500.0;
                }
                if (edad < 23) {
                    cuantiaBeca += 200.0;
                }
                if (numSuspensos == 0) {
                    cuantiaBeca += 500.0;
                } else if (numSuspensos == 1) {
                    cuantiaBeca += 200.0;
                }
                if (residenciaFamiliar.equalsIgnoreCase("NO")) {
                    cuantiaBeca += 1000.0;
                }
                System.out.println("Nombre: " + nombreCompleto + " | Cuantía total: " + String.format("%.2f", cuantiaBeca) + "€");
            }

        } catch (EOFException e) {
        } catch (IOException e) {
            System.err.println("Error al leer el fichero binario: " + e.getMessage());
        }
    }
}