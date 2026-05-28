package com.sptech.school.config;

import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

public class S3ClientConfig {
    public static S3Client criarCliente(){
        // Juntando as crendeciais
        AwsSessionCredentials credenciais =
                AwsSessionCredentials.create(
                        AwsConfig.getAccessKey(),
                        AwsConfig.getSecretKey(),
                        AwsConfig.getSessionToken()
                );

        // criando o cliente S3
        return S3Client.builder()
                .region(
                        Region.of(AwsConfig.getRegion())
                )
                .credentialsProvider(
                        StaticCredentialsProvider.create(credenciais)
                )
                .build();
    }
}
