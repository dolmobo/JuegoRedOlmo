package JuegoRed;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTachi {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 4444);
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner teclado = new Scanner(System.in);

            String peticion;
            while ((peticion = entrada.readLine()) != null) {
                System.out.println("SERVIDOR: " + peticion);

                if (peticion.equals("AHORA!!!!!")) {
                    System.out.println("¡PULSA ENTER RÁPIDO!");
                    teclado.nextLine();
                    salida.println("YA");
                }

                if (peticion.startsWith("GANASTE") || peticion.startsWith("DERROTA")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Conexión perdida.");
        }
    }
}