package Practica1_2_Manual;

import java.util.ArrayList;

public class GameManager {
    private ArrayList<ClienteHandler> clientes=new ArrayList<>();
    private static boolean rondaAbierta=false;
    private ArrayList<Pregunta> preguntas=new ArrayList<>();

    

    public GameManager(ArrayList<ClienteHandler> clientes) {
        this.clientes = clientes;
        this.preguntas.add(new Pregunta("Que hace Oscar cuando se aburre ?", "Llama a Jaime", "Manda solicitudes por Linkedin masivamente", "Contacta con recruiters", "Hace un ejercicio totalmente innecesario", "b"));
        this.preguntas.add(new Pregunta("Que hace Oscar cuando no se aburre ?", "Llama a Jaime", "Manda solicitudes por Linkedin masivamente", "Contacta con recruiters", "Hace un ejercicio totalmente innecesario", "d"));
        this.preguntas.add(new Pregunta("Que hace Jaime cuando se aburre ?", "Llama a Oscar", "Manda solicitudes por Linkedin masivamente", "Contacta con recruiters", "Hace un ejercicio totalmente innecesario", "c"));
        this.preguntas.add(new Pregunta("Que hace Jaime cuando se aburre ?", "Llama a Oscar", "Manda solicitudes por Linkedin masivamente", "Contacta con recruiters", "Hace un ejercicio totalmente innecesario", "a"));
        this.preguntas.add(new Pregunta("Que hace Adrian cuando se aburre ?", "Llama a Javi", "Fuma Vaper", "Compra Vapers", "Duerme", "b"));
    }

    public void iniciarPartida(){
        try {
            System.out.println("Partida iniciada");
            for (Pregunta p:preguntas){
                enviarTodos(extraerPregunta(p));
                rondaAbierta=true;
                Thread.sleep(15000);
                rondaAbierta=false;
                corregirRespuestas(p.getRespuestaCorrecta());
                limpiarRespuestas();
            }
            // Ha finalizado la partida.
            mostrarRanking();
        }catch (Exception e){
            System.out.println("Error en iniciar partida"+e.getMessage());
        }
    }

    public void mostrarRanking(){
        String total="";
        for (ClienteHandler cl : clientes){
            total+=cl.mostrarNota();
        }
        System.out.println(total);
        
    }

    public void limpiarRespuestas(){
        for (ClienteHandler cl : clientes){
            cl.limpiaRespuesta();
        }
    }

    public void enviarTodos(String msg){
        //System.out.println("Enviar todos");
        for (ClienteHandler cl : clientes){
            cl.enviarMensaje(msg);
        }
    }

    public void corregirRespuestas(String sol){
        // actualiza el campo puntos de ClienteHandler 
        // para los que tengan respuesta correcta

        for (ClienteHandler cl : clientes){
            cl.corregirRespuesta(sol);
        }


    }

    public String extraerPregunta(Pregunta p){
        return p.getEnunciado()+" | A: "+p.getRespuestaA()
        +" | B: "+p.getRespuestaB()
        +" | C: "+p.getRespuestaC()
        +" | D: "+p.getRespuestaD();
    }

    

    public ArrayList<ClienteHandler> getClientes() {
        return clientes;
    }

    public static boolean isRondaAbierta() {
        return rondaAbierta;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setClientes(ArrayList<ClienteHandler> clientes) {
        this.clientes = clientes;
    }

    public void setRondaAbierta(boolean rondaAbierta) {
        this.rondaAbierta = rondaAbierta;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
