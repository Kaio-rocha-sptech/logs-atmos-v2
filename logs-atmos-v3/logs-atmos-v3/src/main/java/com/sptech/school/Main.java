package com.sptech.school;

import com.sptech.school.service.DisparadorCamado;
import com.sptech.school.service.LogService;
import com.sptech.school.service.S3Service;

public class Main {
    public static void main(String[] args) {
        System.out.println("Logs do sistema em tempo real");

        while (true) {
            String data = LogService.getData();
            String mensagem = LogService.getMensagens();

            DisparadorCamado.processarEvento(mensagem, data);
            System.out.println("======================================");
            System.out.println("Data: " + data);
            System.out.println("Mensagem: " + mensagem);
            System.out.println("======================================\n");

            try {
                Thread.sleep(LogService.getIntervalo());
            } catch (InterruptedException e) {
                System.out.println("Thread interrompida");
            }
            System.out.println("fim");
            break;
        }
    }
}
