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
            String email = "atmos-v2.system@outlook.com";
            String apiToken = "ATATT3xFfGF00dzQV_IZf53Ti8BKYEO4-SDD_a3zpcTzOGta53iANInnA68sJTCKgrkXPaumVNERW4ueJBA1NOHYYKsq_itMcqwr68TsxHvWVjzwCkfTiadTFyPsO1qB-qoPyUgezXNY6AD5_lFn1Xnu7eAi4FYn-hGKBO7St1z-7EJCDzKm8kU=F1D44244";
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
                              "id": "712020:ca1ff9f9-1ce8-4595-8c5f-b138f083eef4"
                            }
                          }
                        }
                """.formatted(mensagem);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://sptech13.atlassian.net/rest/api/3/issue"))
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
