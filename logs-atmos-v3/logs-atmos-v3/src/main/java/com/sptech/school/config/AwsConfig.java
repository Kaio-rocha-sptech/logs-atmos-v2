package com.sptech.school.config;

import io.github.cdimascio.dotenv.Dotenv;

public class AwsConfig {
    // pega as crendenciais do arquivo do .env
    private static final Dotenv dotenv = Dotenv.configure().filename(".env").load();

    // Peegando as credenciais do .env
    private static final String ACCESS_KEY = dotenv.get("AWS_ACCESS_KEY_ID");
    private static final String SECRET_ACCESS_KEY = dotenv.get("AWS_SECRET_ACCESS_KEY");
    private static final String SESSION_TOKEN = dotenv.get("AWS_SESSION_TOKEN");
    private static final String REGION = dotenv.get("AWS_REGION");
    private static final String BUCKET_NAME = dotenv.get("AWS_BUCKET_NAME");

    // Criando metodos publicos para conseguir acessar as crendenciais no projeto
    public static String getAccessKey(){return ACCESS_KEY;}
    public static String getSecretKey(){return SECRET_ACCESS_KEY;}
    public static String getSessionToken(){return SESSION_TOKEN;}
    public static String getRegion(){return REGION;}
    public static String getBucketName(){return BUCKET_NAME;}

}
