package Ejercicio5.Ej10;

import java.io.*;
import java.util.ArrayList;

public class Ejercicio10 {
    static class Empleado {
        String nombre;
        int diasBaja;
        double nomina;
        public Empleado(String nombre, int diasBaja, double nomina) {
            this.nombre = nombre;
            this.diasBaja = diasBaja;
            this.nomina = nomina;
        }
    }
    public static void main(String[] args) {
        File fichero = new File("src/Ejercicio5/Ej10/nominas.bin");
        if (!fichero.exists()) {
            System.out.println("El fichero no existe");
            return;
        }
        ArrayList<Empleado> empleados = new ArrayList<>();
        int dadosDeBaja = 0;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
            while (true) {
                String nombre = dis.readUTF();
                int diasBaja = dis.readInt();
                double nomina = dis.readDouble();
                empleados.add(new Empleado(nombre, diasBaja, nomina));
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Empleado> actualizados = new ArrayList<>();
        for (Empleado emp : empleados) {
            if (emp.diasBaja == 0) {
                emp.nomina *= 1.05;
                actualizados.add(emp);
            } else if (emp.diasBaja >= 1 && emp.diasBaja <= 3) {
                actualizados.add(emp);
            } else if (emp.diasBaja >= 4 && emp.diasBaja <= 10) {
                emp.nomina *= 0.90;
                actualizados.add(emp);
            } else {
                dadosDeBaja++;
            }
        }
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero, false))) {
            for (Empleado emp : actualizados) {
                dos.writeUTF(emp.nombre);
                dos.writeInt(emp.diasBaja);
                dos.writeDouble(emp.nomina);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Nominas.dat actualizado");
        System.out.println("Empleados dados de baja eliminados: " + dadosDeBaja);
        System.out.println("Lista de empleados restantes:");
        for (Empleado emp : actualizados) {
            System.out.println("Nombre: " + emp.nombre + " | Días baja: " + emp.diasBaja + " | Nómina: " + String.format("%.2f", emp.nomina) + "€");
        }
    }
}