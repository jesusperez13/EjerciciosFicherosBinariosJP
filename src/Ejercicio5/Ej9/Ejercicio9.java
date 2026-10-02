package Ejercicio5.Ej9;

import java.io.*;

public class Ejercicio9 {
    public static void main(String[] args) {
        File fichero = new File("src/Ejercicio5/Ej8/Septemp.dat");
        if (!fichero.exists()) {
            System.out.println("El fichero no existe, tienes que crearlo primero en el Ej8");
            return;
        }
        double maxTemp = Double.MIN_VALUE;
        double minTemp = Double.MAX_VALUE;
        double sumaTemp = 0;
        int contador = 0;
        int horaFria = -1;
        int horaCalurosa = -1;
        int dia = -1;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
            while (true) {
                dia = dis.readInt();
                int hora = dis.readInt();
                double temp = dis.readDouble();
                sumaTemp += temp;
                contador++;
                if (temp > maxTemp) {
                    maxTemp = temp;
                    horaCalurosa = hora;
                }
                if (temp < minTemp) {
                    minTemp = temp;
                    horaFria = hora;
                }
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (contador > 0) {
            double media = sumaTemp / contador;
            System.out.println("Estadísticas del día " + dia + " de septiembre");
            System.out.println("Temperatura Máxima: " + maxTemp + "°C (A las " + horaCalurosa + ":00 h)");
            System.out.println("Temperatura Mínima: " + minTemp + "°C (A las " + horaFria + ":00 h)");
            System.out.println("Temperatura Media: " + String.format("%.2f", media) + "°C");
        } else {
            System.out.println("El fichero está vacío");
        }
    }
}
