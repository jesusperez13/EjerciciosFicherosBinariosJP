package Ejercicio5.Ej7;

import java.io.*;

public class Ejercicio7 {
    public static void main(String[] args) {
        String archivoOrigen = "src/Ejercicio5/Ej7/muchosdatos.bin";
        try (DataInputStream dis = new DataInputStream(new FileInputStream(archivoOrigen));
             DataOutputStream dosMenores = new DataOutputStream(new FileOutputStream("src/Ejercicio5/Ej7/menores.dat"));
             DataOutputStream dosAdultos = new DataOutputStream(new FileOutputStream("src/Ejercicio5/Ej7/adultos.dat"));
             DataOutputStream dosMayores = new DataOutputStream(new FileOutputStream("src/Ejercicio5/Ej7/mayores.dat"))) {
            while (true) {
                String nombre = dis.readUTF();
                String apellidos = dis.readUTF();
                int edad = dis.readInt();
                String telefono = dis.readUTF();
                String email = dis.readUTF();
                String ciudad = dis.readUTF();
                String nacionalidad = dis.readUTF();
                String profesion = dis.readUTF();
                DataOutputStream destino;
                if (edad < 18) {
                    destino = dosMenores;
                } else if (edad <= 65) {
                    destino = dosAdultos;
                } else {
                    destino = dosMayores;
                }
                destino.writeUTF(nombre);
                destino.writeUTF(apellidos);
                destino.writeInt(edad);
                destino.writeUTF(telefono);
                destino.writeUTF(email);
                destino.writeUTF(ciudad);
                destino.writeUTF(nacionalidad);
                destino.writeUTF(profesion);
            }
        } catch (EOFException e) {
            System.out.println("Clasificación de registros");
        } catch (IOException e) {
            System.err.println("Error al procesar los ficheros: " + e.getMessage());
        }
        mostrarFichero("src/Ejercicio5/Ej7/menores.dat", "menores.dat");
        mostrarFichero("src/Ejercicio5/Ej7/adultos.dat", "adultos.dat");
        mostrarFichero("src/Ejercicio5/Ej7/mayores.dat", "mayores.dat");
    }
    private static void mostrarFichero(String nombreFichero, String titulo) {
        System.out.println(titulo);
        File archivo = new File(nombreFichero);

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("El fichero está vacío o no contiene registros");
            return;
        }
        try (DataInputStream dis = new DataInputStream(new FileInputStream(nombreFichero))) {
            while (true) {
                String nombre = dis.readUTF();
                String apellidos = dis.readUTF();
                int edad = dis.readInt();
                String telefono = dis.readUTF();
                String email = dis.readUTF();
                String ciudad = dis.readUTF();
                String nacionalidad = dis.readUTF();               String profesion = dis.readUTF();
                System.out.println("- " + nombre + " " + apellidos + " | Edad: " + edad + " | Teléfono: " +
                                    telefono + " | Email: " + email + " | Ciudad: " + ciudad + " | Nacionalidad: " +
                                    nacionalidad + " | Profesión: " + profesion);
            }
        } catch (EOFException e) {
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}