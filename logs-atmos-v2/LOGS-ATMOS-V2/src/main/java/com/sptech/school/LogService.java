package com.sptech.school;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LogService {

    public static String getData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public static String getMensagens() {
        String espaco = "       ";

        List<String> mensagens = Arrays.asList(


                "Conexão com o banco de dados bem sucedida!",
                "Requisição ao servidor respondida com sucesso",
                "Serviço de monitoramento ativo e operando normalmente",


                "Uso de CPU elevado detectado (acima de 85%)",
                "CPU normalizada após pico de uso",
                "Processo consumindo CPU acima do esperado",


                "Consumo de memória RAM acima de 90%",
                "Memória RAM próxima do limite crítico",
                "Liberação de memória realizada com sucesso",
                "Possível vazamento de memória detectado",


                "Espaço em disco abaixo de 10%",
                "Disco atingindo limite crítico de armazenamento",
                "Limpeza de arquivos temporários executada",
                "Falha ao escrever dados em disco",


                "Latência de rede elevada detectada",
                "Perda de pacotes identificada na rede",
                "Conexão com servidor externo instável",
                "Reconexão com servidor realizada com sucesso",


                "Serviço principal reiniciado automaticamente",
                "Falha ao iniciar serviço do sistema",
                "Serviço de autenticação indisponível",
                "Serviço restaurado após falha",


                "Falha ao executar query no banco de dados",
                "Timeout na conexão com banco de dados",
                "Número elevado de conexões simultâneas no banco",


                "Erro ao executar processo agendado",
                "Processo travado detectado e finalizado",
                "Fila de processamento com alto tempo de espera",


                "Tentativa de acesso não autorizado detectada",
                "Múltiplas falhas de login identificadas",
                "Token inválido recebido em requisição",


                "Chamado aberto em jira! Codigo: " + getNumeroAleatorio()
                        + "\n" + espaco + "| Empresa: " + getEmpresa()
        );

        return mensagens.get(new Random().nextInt(mensagens.size()));
    }

    public static int getNumeroAleatorio() {
        return new Random().nextInt(999999999);
    }

    public static int getIntervalo() {
        return new Random().nextInt(60000) + 60000; // Entre 1 e 2 minutos
    }

    public static String getEmpresa() {
        List<String> empresas = Arrays.asList("CPTM", "Vale", "MRS Logística", "VLI");
        return empresas.get(new Random().nextInt(empresas.size()));
    }
}
