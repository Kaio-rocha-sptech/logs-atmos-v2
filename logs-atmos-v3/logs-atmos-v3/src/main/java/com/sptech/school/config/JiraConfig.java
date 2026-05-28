package com.sptech.school.config;

import io.github.cdimascio.dotenv.Dotenv;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

public class JiraConfig {
    // pega as crendenciais do arquivo do .env
    private static final Dotenv dotenv = Dotenv.configure().filename(".env").load();

    private static final String JIRA_URL = dotenv.get("JIRA_URL");
    private static final String JIRA_EMAIL = dotenv.get("JIRA_EMAIL");
    private static final String JIRA_TOKEN = dotenv.get("JIRA_TOKEN");

    public static String getJiraUrl(){return JIRA_URL;}
    public static String getJiraEmail(){return JIRA_EMAIL;}
    public static String getJiraToken(){return JIRA_TOKEN;}
}
