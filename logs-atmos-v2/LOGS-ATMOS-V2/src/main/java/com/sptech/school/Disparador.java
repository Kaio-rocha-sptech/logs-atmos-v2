package com.sptech.school;

public class Disparador {
    public static void processarEvento(String mensagem, String data) {
            try {

                String id = Jira.abrirChamadoJira(mensagem);
                if(mensagem.toUpperCase().contains("RAM")){
                    Slack.enviarNotSlack(
                            "Alerta",
                            "RAM",
                            data,
                            "ATMOS",
                            id
                    );
                } else if (mensagem.toUpperCase().contains("CPU")) {
                    Slack.enviarNotSlack(
                            "Alerta",
                            "CPU",
                            data,
                            "ATMOS",
                            id
                    );
                } else if (mensagem.toUpperCase().contains("Disco") || mensagem.toUpperCase().contains("Armazenamento")) {
                    Slack.enviarNotSlack(
                            "Alerta",
                            "Disco",
                            data,
                            "ATMOS",
                            id
                    );
                }else{
                    Slack.enviarNotSlack(
                            "Alerta",
                            "Sistema!",
                            data,
                            "ATMOS",
                            id
                    );
                }

            } catch (Exception e) {
                System.out.println("Erro ao enviar alerta: " + e.getMessage());
            }
        }
    }
