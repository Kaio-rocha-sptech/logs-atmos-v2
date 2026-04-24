package com.sptech.school;

public class Disparador {
    public static void processarEvento(String mensagem, String data) {

        if (mensagem.contains("Chamado aberto em jira!")) {

            try {

                String id = Jira.abrirChamadoJira(mensagem);

                Slack.enviarNotSlack(
                        "Alerta",
                        "Sistema",
                        data,
                        "ATMOS",
                        id
                );

            } catch (Exception e) {
                System.out.println("Erro ao enviar alerta: " + e.getMessage());
            }
        }
    }
}
