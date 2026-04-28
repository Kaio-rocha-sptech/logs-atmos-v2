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
        List<String> mensagens = Arrays.asList(
                "CPU em alerta!",
                "CPU em alerta critico!",

                "Memoria RAM em alerta",
                "Memoria RAM em alerta critico!",

                "Disco em alerta",
                "Disco em alerta critico!",

                "Armazenamento em alerta",
                "Armazenamento em alerta critico!"
        );

        return mensagens.get(new Random().nextInt(mensagens.size()));
    }

    public static int getIntervalo() {
        return new Random().nextInt(60000) + 60000; // Entre 1 e 2 minutos
    }

}
