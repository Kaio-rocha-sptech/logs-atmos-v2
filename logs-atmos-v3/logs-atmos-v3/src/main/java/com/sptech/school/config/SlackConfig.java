package com.sptech.school.config;

import io.github.cdimascio.dotenv.Dotenv;

public class SlackConfig {
    private static final Dotenv dotenv = Dotenv.configure().filename(".env").load();

    private static final String SLACK_URL = dotenv.get("SLACK_URL");

    public static String getSlackUrl(){return SLACK_URL;}
}
