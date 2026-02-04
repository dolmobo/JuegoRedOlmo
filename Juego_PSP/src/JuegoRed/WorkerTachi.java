package JuegoRed;

import java.io.*;
import java.net.*;

public class WorkerTachi extends Thread {
    private Socket socket;

    public WorkerTachi(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            salida.println("Pulsa ENTER rapido cuando veas este mensaje: 'AHORA!!!!'.");

            Thread.sleep((long) (Math.random() * 3000) + 2000);

            salida.println("AHORA!!!!!");
            long tiempoInicio = System.currentTimeMillis();

            String respuesta = entrada.readLine();
            long tiempoFin = System.currentTimeMillis();

            long reaccion = tiempoFin - tiempoInicio;
            if (reaccion < 500) {
                salida.println("GANASTE: Tardaste " + reaccion + "ms.");
            } else {
                salida.println("DERROTA: El Tachi no se dio la vuelta (" + reaccion + "ms).");
            }

            salida.close();
            entrada.close();
            socket.close();

        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}