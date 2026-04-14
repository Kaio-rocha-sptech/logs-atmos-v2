package com.sptech.school;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import java.util.Base64;
public class Main {
    public static void main(String[] args) {
        abrirChamado("RAM acima de 75%");
    }

    public static void abrirChamado(String mensagem) {
        String url = "https://sptech13.atlassian.net/rest/api/3/issue";
        String username = "Meu email";
        String password = "Meu token";
        // JSON Body
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

        // 1. Criar a string de autenticação (usuário:senha) e codificar em Base64
        String authString = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(authString.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + encodedAuth;

        // 2. Construir o cliente
        HttpClient client = HttpClient.newHttpClient();

        // 3. Criar a requisição
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", authHeader) // Adiciona Basic Auth
                .header("Content-Type", "application/json") // Define que o corpo é JSON
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            // 4. Enviar a requisição
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 5. Exibir o resultado
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
