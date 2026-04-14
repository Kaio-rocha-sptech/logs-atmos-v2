package com.sptech.school;
import HelpDesk.Jira;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Jira.abrirChamadoJira("RAM atingiu 100% do seu uso");
    }
}
