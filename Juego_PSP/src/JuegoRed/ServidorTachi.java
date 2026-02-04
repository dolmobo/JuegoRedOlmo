package JuegoRed;

import java.io.*;
import java.net.*;

public class ServidorTachi {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(4444);
            System.out.println("Servidor iniciado");

            while (true) {
                // Acepta la conexión
                Socket cliente = servidor.accept();
                System.out.println("Conexion establecida.");

                WorkerTachi w = new WorkerTachi(cliente);
                w.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}