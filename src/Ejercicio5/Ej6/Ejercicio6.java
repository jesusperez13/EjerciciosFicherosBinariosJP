package Ejercicio5.Ej6;

import java.io.*;
import java.util.Scanner;

public class Ejercicio6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el número de personas a registrar: ");
        int n = Integer.parseInt(sc.nextLine());
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/Ejercicio5/Ej6/datospersonas.dat", true))) {
            for (int i = 0; i < n; i++) {
                System.out.print("Nombre: "); dos.writeUTF(sc.nextLine());
                System.out.print("Apellidos: "); dos.writeUTF(sc.nextLine());
                System.out.print("Edad: "); dos.writeInt(Integer.parseInt(sc.nextLine()));
                System.out.print("Teléfono: "); dos.writeUTF(sc.nextLine());
                System.out.print("Email: "); dos.writeUTF(sc.nextLine());
                System.out.print("Ciudad de residencia: "); dos.writeUTF(sc.nextLine());
                System.out.print("Nacionalidad: "); dos.writeUTF(sc.nextLine());
                System.out.print("Profesión: "); dos.writeUTF(sc.nextLine());
            }
            System.out.println("Datos guardados correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Contenio del fichero");
        try (DataInputStream dis = new DataInputStream(new FileInputStream("src/Ejercicio5/Ej6/datospersonas.dat"))) {
            while (true) {
                String nombre = dis.readUTF();
                String apellidos = dis.readUTF();
                int edad = dis.readInt();
                String telefono = dis.readUTF();
                String email = dis.readUTF();
                String ciudad = dis.readUTF();
                String nacionalidad = dis.readUTF();
                String profesion = dis.readUTF();
                System.out.println(nombre + " " + apellidos + ", Edad: " + edad + ", Teléfono: " + telefono +
                                    ", Email: " + email + ", Ciudad: " + ciudad + ", Nacionalidad: " + nacionalidad + ", Profesión: " + profesion);
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}