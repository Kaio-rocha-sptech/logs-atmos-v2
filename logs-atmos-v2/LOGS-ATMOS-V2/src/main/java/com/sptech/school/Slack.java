package com.sptech.school;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Slack {
    // SLACK ===================================================== |
    public static HttpClient client = HttpClient.newHttpClient();
    public static String urlSlack = "https://hooks.slack.com/services/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";

    public static void enviarNotSlack(String tipo, String componente, String data, String responsavel, String idAlerta) throws IOException, InterruptedException {

        String mensagem = criarMensagem(
                tipo,
                componente,
                data,
                responsavel,
                idAlerta
        );
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(urlSlack))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mensagem.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(String.format("Status: %s", response.statusCode()));

        System.out.println(String.format("Response: %s", response.body()));
    }

    public static String criarMensagem(String tipo,String componente, String data, String responsavel, String idAlerta){
        String slackMessage = """
{
  "blocks": [
    {
      "type": "header",
      "text": {
        "type": "plain_text",
        "text": "🚨 Alerta do Sistema",
        "emoji": true
      }
    },
    {
      "type": "section",
      "text": {
        "type": "mrkdwn",
        "text": "*Um alerta foi gerado automaticamente pelo sistema.*"
      }
    },
    {
      "type": "divider"
    },
    {
      "type": "section",
      "fields": [
        {
          "type": "mrkdwn",
          "text": "*📌 Tipo:*\\n%s"
        },
        {
          "type": "mrkdwn",
          "text": "*🧩 Componente:*\\n%s"
        },
        {
          "type": "mrkdwn",
          "text": "*📅 Data:*\\n%s"
        },
        {
          "type": "mrkdwn",
          "text": "*🤖 Responsável:*\\n%s"
        }
      ]
    },
    {
      "type": "context",
      "elements": [
        {
          "type": "mrkdwn",
          "text": "ID do alerta: `%s`"
        }
      ]
    }
  ]
}
""".formatted(
                tipo,
                componente,
                data,
                responsavel,
                idAlerta
        );
        return slackMessage;
    }
}
