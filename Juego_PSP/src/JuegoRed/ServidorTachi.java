package ActividadJuego;

import java.io.*;
import java.net.*;

public class ServidorTachi {
    public static void main(String[] args) {
        int puerto = 4444;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor Tachi iniciado...");

            while (true) {
                try (Socket cliente = servidor.accept();
                     PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
                     BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()))) {

                    salida.println("Dale la vuelta al Tachi cuando veas el texto: 'AHORA!!!!'.");

                    Thread.sleep((long) (Math.random() * 3000) + 2000);

                    salida.println("AHORA!!!!!");
                    long tiempoInicio = System.currentTimeMillis();

                    String respuesta = entrada.readLine();
                    long tiempoFin = System.currentTimeMillis();

                    if (respuesta != null) {
                        long reaccion = tiempoFin - tiempoInicio;

                        if (reaccion < 500) {
                            salida.println("GANASTE: Tardaste " + reaccion + "ms.");
                        } else {
                            salida.println("DERROTA: El Tachi no se dio la vuelta (" + reaccion + "ms).");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Partida acabada.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}