package com.sptech.school;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;
public class Main {
    public static void main(String[] args) {
        abrirChamado("RAM acima de 75%");
    }

    public static void abrirChamado(String mensagem) {
        String email = "COLOQUE O EMAIL DA ATMOS AQUI";
        String apiToken = "COLOQUE O TOKEN DA CONTA ATMOS AQUI";
        String auth = Base64.getEncoder().encodeToString((email + ":" + apiToken).getBytes());

        try {
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
                              "id": "idAccount"
                            }
                          }
                        }
                """.formatted(mensagem);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("URLBASErest/api/3/issue"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Basic " + auth)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .timeout(Duration.ofSeconds(10))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
