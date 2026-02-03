package JuegoRed;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTachi {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4444);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner teclado = new Scanner(System.in)) {

            String msg;
            while ((msg = entrada.readLine()) != null) {
                System.out.println("SERVIDOR: " + msg);

                if (msg.equals("¡AHORA!")) {
                    System.out.println("¡PULSA ENTER RÁPIDO!");
                    teclado.nextLine();
                    salida.println("YA");
                }

                if (msg.startsWith("GANASTE") || msg.startsWith("DERROTA")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Conexión perdida.");
        }
    }
}