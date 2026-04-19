package Practica1_2_Manual;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Servidor {

    public static ArrayList<ClienteHandler> clientes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Servidor arrancado.");
        System.out.println("Esperando a que se conecten clientes...");
        System.out.println("¡Escribe 'START' y pulsa Enter para comenzar la partida!");

        try {
            ServerSocket server = new ServerSocket(5000);

            HiloAceptador aceptador = new HiloAceptador(server, clientes);
            aceptador.start();

            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
            boolean esperandoStart = true;

            while (esperandoStart) {
                String comando = sc.readLine();

                if (comando != null && comando.equalsIgnoreCase("START")) {
                    if (clientes.size() > 0) {
                        esperandoStart = false;

                        aceptador.detener();
                        System.out.println("Cerrando la entrada a nuevos clientes...");
                        server.close();

                        System.out.println("¡Iniciando partida con " + clientes.size() + " jugadores!");
                        new GameManager(clientes).iniciarPartida();
                    } else {
                        System.out.println("No puedes empezar. Aún no hay ningún cliente conectado.");
                    }
                } else {
                    System.out.println("Comando no reconocido. Escribe 'START' para empezar.");
                }
            }

        } catch (Exception e) {
            System.err.println("Error general del servidor: " + e.getMessage());
        }
    }
}