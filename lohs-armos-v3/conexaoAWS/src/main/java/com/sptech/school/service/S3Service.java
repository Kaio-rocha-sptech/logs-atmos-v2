package com.sptech.school.service;

import com.sptech.school.config.AwsConfig;
import com.sptech.school.config.S3ClientConfig;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class S3Service {
    private static final S3Client CLIENT = S3ClientConfig.criarCliente();
    private static final String BUCKET = AwsConfig.getBucketName();

    public boolean arquivoExisteBucket(String arquivoS3){
        // Uso:
        // s3Service.arquivoExisteBucket("empresax/clients.json")
        // retorna verdadeiro ou falso para ajudar em outra funções
        try{
            HeadObjectRequest request = HeadObjectRequest.builder()
                    .bucket(BUCKET)
                    .key(arquivoS3)
                    .build();
            CLIENT.headObject(request);
            System.out.println("Arquivo encontrado!");
            return true;
        }catch (NoSuchKeyException e){
            System.out.println("Arquivo não encontrado: "+e.getMessage());
        }
        catch (Exception e){
            System.out.println("Erro ao buscar Arquivo: "+e.getMessage());
        }
        return false;
    }

    public void baixarArquivoBucket(String arquivoS3, String diretorioDestino){
        // Uso:
        // s3service.baixarArquivoBucket("empresax/client.json", "arquivosTemp/client.json");
        // O diretorio tem que existir se não da erro na hora de baixar
        try {
            GetObjectRequest request =GetObjectRequest.builder().
                bucket(BUCKET). // Nome do bucket
                    key(arquivoS3). // nome do arquivo que vai bucar tipo empresax/client.json
                    build();
            CLIENT.getObject(request, Paths.get(diretorioDestino)); // pega a resposta e salva no diretorio
            System.out.println("Arquivo baixado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao baixar o arquivo: "+e.getMessage());
        }
    }

    public void listarArquivosBucket(){
        // Uso
        // s3Service.listarArquivosBucket();
        // Lista todos os arquivos do bucket, mostrando o arquivo e outras informções, mostrando tanto arquivos quanto diretorios
        // Criado uma requisição para o bucket
        ListObjectsV2Request request =
                ListObjectsV2Request.builder().
                        bucket(AwsConfig.getBucketName()).
                        build();

        // Pega a resposta
        ListObjectsV2Response response =
                CLIENT.listObjectsV2(request);

        // Lista todos os itens da resposta
        for (S3Object arquivo : response.contents()){
            System.out.println("Arquivo: "+arquivo.key());
        }
        // resposta sem especificar o campo:
        // Arquivo: S3Object(Key=empresax/, LastModified=2026-05-23T12:55:44Z, ETag="d41d8cd98f00b204e9800998ecf8427e", ChecksumAlgorithm=[CRC64NVME], ChecksumType=FULL_OBJECT, Size=0, StorageClass=STANDARD)
    }

    public void uploadArquivosBucket(String arquivoLocal, String diretorioS3){
        // uso:
        // s3Service.uploadArquivosBucket("arquivosTemp/trusted_empresaX.csv", "empresax/relatorios/trusted_empresaX.csv");
        // Obs: se não tiver o diretorio no bucket ele cria
        try{
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(BUCKET)
                    .key(diretorioS3)
                    .build();

            CLIENT.putObject(
                    request, RequestBody.fromFile(Path.of(arquivoLocal))
            );
            System.out.println("Upload realizado!");
            apagarArquivoLocal(arquivoLocal);
        } catch (Exception e) {
            System.out.println("Erro ao fazer upload: "+e.getMessage());
        }
    }


    public void apagarArquivoLocal(String arquivoLocal){
        try{
            Files.delete(Path.of(arquivoLocal));
            System.out.println("Arquivo apagado com sucesso");
        } catch (Exception e) {
            System.out.println("Erro ao apagar arquivo: "+e.getMessage());
        }
    }

}
