package com.sptech.school;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Jira {

    // JIRA ====================================================== |
    public static String urlJira = "https://sptech13.atlassian.net/rest/api/3/issue";
    public static String emailJira = "Adicione aqui o email";
    public static String tokenJira = "Adicione aqui o token";

    public static String abrirChamadoJira(String mensagem) throws IOException, InterruptedException {

        String jsonBody = """
                {
                  "fields": {
                    "project": {
                      "key": "ALT"
                    },
                    "summary": "Alerta gerado pelo sistema",
                    "description": {
                      "type": "doc",
                      "version": 1,
                      "content": [
                        {
                          "type": "paragraph",
                          "content": [
                            {
                              "type": "text",
                              "text": "%s"
                            }
                          ]
                        }
                      ]
                    },
                    "issuetype": {
                      "name": "Alerta"
                    },
                    "assignee": {
                      "id": "712020:ca1ff9f9-1ce8-4595-8c5f-b138f083eef4"
                    }
                  }
                }
                """.formatted(mensagem);

        HttpClient client = HttpClient.newHttpClient();

        String auth = Base64.getEncoder().encodeToString(
                (emailJira + ":" + tokenJira).getBytes(StandardCharsets.UTF_8)
        );

        HttpRequest request = HttpRequest.newBuilder(URI.create(urlJira))
                .header("accept", "application/json")
                .header("Authorization", "Basic " + auth)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();

        System.out.println("Status: " + response.statusCode());
        System.out.println("Response: " + responseBody);

        String id = responseBody.split("\"id\":\"")[1].split("\"")[0];

        return id;
    }
}
