package io.atmos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args)  {
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd-MM-yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        String dataHora = data + " " + hora;

        while (true){
            GerarNumero numero = new GerarNumero();

            int numeroGerado = numero.getNumero();

            if  (numeroGerado == 1){
                System.out.printf("[INFO] - [%s] Sessão de monitoramento iniciada com sucesso\n", dataHora);
            } else if (numeroGerado == 2){
                System.out.printf("[WARN] - [%s] Capacidade de disco em 65 porcento\n", dataHora);
            } else if (numeroGerado == 3) {
                System.out.printf("[ERROR] - [%s] Conexão com servidor-035 perdida\n", dataHora);
            } else if (numeroGerado == 4) {
                System.out.printf("[FATAL] - [%s] Gargalo de CPU detectado\n", dataHora);
            } else {
                System.out.printf("[INFO] - [%s] Nova componente adicionado - HD - server-02 \n", dataHora);
            }

            try {
                TimeUnit.SECONDS.sleep(numeroGerado);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
