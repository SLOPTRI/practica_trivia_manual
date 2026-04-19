package Practica1_2_Manual;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class HiloAceptador extends Thread {

    private ServerSocket server;
    private ArrayList<ClienteHandler> clientes;
    private boolean aceptandoClientes;

    public HiloAceptador(ServerSocket server, ArrayList<ClienteHandler> clientes) {
        this.server = server;
        this.clientes = clientes;
        this.aceptandoClientes = true;
    }

    public void detener() {
        this.aceptandoClientes = false;
    }

    @Override
    public void run() {
        try {
            while (aceptandoClientes) {
                Socket cliente = server.accept();
                ClienteHandler cl = new ClienteHandler(cliente);
                cl.start();
                clientes.add(cl);
                System.out.println("-> Nuevo cliente conectado. Total: " + clientes.size());
            }
        } catch (SocketException se) {
            if (aceptandoClientes) {
                System.err.println("Error en el socket: " + se.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error de conexión al aceptar cliente.");
        }
    }
}