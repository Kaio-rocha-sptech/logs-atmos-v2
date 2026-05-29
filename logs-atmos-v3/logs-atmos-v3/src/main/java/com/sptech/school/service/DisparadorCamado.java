package com.sptech.school.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.sptech.school.integration.Jira;
import com.sptech.school.integration.Slack;

import java.util.ArrayList;
import java.util.List;

public class DisparadorCamado {
//    public static void processarEvento(String mensagem, String data) {
//        try {
//            String id = Jira.abrirChamadoJira(mensagem);
//
//
//            if(mensagem.toUpperCase().contains("RAM")){
//                Slack.enviarNotSlack(
//                        "Alerta",
//                        "RAM",
//                        data,
//                        "ATMOS",
//                        id
//                );
//            } else if (mensagem.toUpperCase().contains("CPU")) {
//                Slack.enviarNotSlack(
//                        "Alerta",
//                        "CPU",
//                        data,
//                        "ATMOS",
//                        id
//                );
//            } else if (mensagem.toUpperCase().contains("Disco") || mensagem.toUpperCase().contains("Armazenamento")) {
//                Slack.enviarNotSlack(
//                        "Alerta",
//                        "Disco",
//                        data,
//                        "ATMOS",
//                        id
//                );
//            }else{
//                Slack.enviarNotSlack(
//                        "Alerta",
//                        "Sistema!",
//                        data,
//                        "ATMOS",
//                        id
//                );
//            }
//
//        } catch (Exception e) {
//            System.out.println("Erro ao enviar alerta: " + e.getMessage());
//        }
//    }

    public static List<String> enviarIncidentes(JsonNode incidentes){
        try{
            int numIncidente = 1;
            List<String> linha = new ArrayList<>();
            for (JsonNode incidente : incidentes) {
                String dataIncidente = incidente.get("timestamp").asText();
                String hostIncidente = incidente.get("host_id").asText();
                String hostNameIncidente = incidente.get("hostname").asText();
                String componenteIncidente = incidente.get("componente").asText();
                Double valorIncidente = incidente.get("valor").asDouble();
                String tipoIncidente = incidente.get("tipo").asText();

                String mensagem = valorIncidente+"% de "+componenteIncidente.toUpperCase()+" - ";

                if(componenteIncidente.equalsIgnoreCase("RAM")){
                    mensagem += "";
                } else if (componenteIncidente.equalsIgnoreCase("CPU")) {
                    mensagem += "";
                } else if (componenteIncidente.equalsIgnoreCase("Disco")
                        || componenteIncidente.equalsIgnoreCase("Armazenamento")) {
                    mensagem += "";
                }else{
                    mensagem += "Não indentificado";
                }

                String id = Jira.abrirChamadoJira(tipoIncidente, componenteIncidente, hostNameIncidente,
                        valorIncidente, mensagem);
                if(id != null ){
                    Slack.enviarNotSlack(
                            tipoIncidente,
                            componenteIncidente,
                            dataIncidente,
                            "ATMOS",
                            id
                    );
                    System.out.println("Incidente "+numIncidente+" aberto - ID: "+id);
                    numIncidente++;

                    linha.add(dataIncidente+"," +
                            hostIncidente+"," +
                            hostNameIncidente+","+
                            componenteIncidente+","+
                            valorIncidente+","+
                            tipoIncidente+",");
                }else{
                    System.out.println("Não foi possivel abrir o chamado");
                }
            }
            return linha;
        } catch (Exception e){
            System.out.println("Erro no disparador de chamado: "+e.getMessage());
        }
        return null;
    }
}
