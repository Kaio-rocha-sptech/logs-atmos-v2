package com.sptech.school.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Relatorio {

    public String criarRelatorio(List<String> csv){
        Date dataAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd-HH");
        String dataFormatada = formato.format(dataAtual);

        String diretorio = "arquivoTemp/relatorios/";
        String arquivo = "incidentes-"+dataFormatada.trim()+".csv";

        String path = diretorio + arquivo;
        Path caminho = Path.of(path);

        try{
           Files.write(caminho, csv);
           System.out.println("Arquivo criado");
           return arquivo;
       } catch (IOException e) {
           System.out.println("Erro ao criar csv: "+e.getMessage());
            return null;
       }
    }
}
