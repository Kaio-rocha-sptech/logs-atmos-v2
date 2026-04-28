package com.sptech.school;

public class Main {
    public static void main(String[] args) {

        System.out.println("Logs do sistema em tempo real");

        while (true) {
            String data = LogService.getData();
            String mensagem = LogService.getMensagens();

            Disparador.processarEvento(mensagem, data);
            System.out.println("======================================");
            System.out.println("Data: " + data);
            System.out.println("Mensagem: " + mensagem);
            System.out.println("======================================\n");

            try {
                Thread.sleep(LogService.getIntervalo());
            } catch (InterruptedException e) {
                System.out.println("Thread interrompida");
            }
        }
    }
}
