package com.sptech.school;
import com.fasterxml.jackson.databind.JsonNode;
import com.sptech.school.service.DisparadorCamado;
import com.sptech.school.service.LogService;
import com.sptech.school.service.Relatorio;
import com.sptech.school.service.S3Service;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Logs do sistema em tempo real");
        S3Service s3Service = new S3Service();
        DisparadorCamado disparadorCamado = new DisparadorCamado();

        Relatorio relatorio = new Relatorio();

        JsonNode incidentes =  s3Service.lerArquivoBucket("client/empresaX/incidentes/clientIncidente.json");
        List<String> csv = disparadorCamado.enviarIncidentes(incidentes);
        String arquivo = relatorio.criarRelatorio(csv);
        s3Service.uploadArquivosLocalBucket(arquivo, "client/empresaX/relatorios/"+arquivo);
    }
}
