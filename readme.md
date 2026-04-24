# 📡 ATMOS Agent — Simulador de Monitoramento com Integração Jira & Slack

## 📖 Descrição

O **ATMOS Agent** é uma aplicação em Java que simula um sistema de monitoramento de infraestrutura em tempo real.

Ele gera eventos automáticos (logs) representando o comportamento de um ambiente computacional e, ao identificar situações críticas, realiza:

* 🎫 Abertura automática de chamados no Jira
* 💬 Envio de alertas estruturados para o Slack

---

## ⚙️ Funcionalidades

* 🔄 Geração contínua de logs simulados
* 📊 Monitoramento fictício de:

  * CPU
  * Memória RAM
  * Disco
  * Rede
  * Serviços
* 🚨 Detecção de eventos críticos
* 🔗 Integração com APIs externas:

  * Jira (criação de issues)
  * Slack (webhook de alertas)
* ⏱ Intervalo de execução configurável (com aleatoriedade)

---

## 🧠 Como funciona

1. O sistema gera mensagens aleatórias simulando eventos
2. Caso a mensagem represente um alerta crítico:

   * Um chamado é criado no Jira
   * Um alerta é enviado ao Slack com o ID do chamado
3. O processo roda continuamente em loop

---

## 🏗️ Estrutura do Projeto

```bash
com.sptech.school
│
├── Main.java              # Loop principal e execução
├── LogService.java        # Geração de mensagens e dados
├── AlertDispatcher.java   # Regra de decisão (disparo de alerta)
├── Jira.java              # Integração com API do Jira
└── Slack.java             # Integração com Webhook do Slack
```

---

## 🚀 Tecnologias Utilizadas

* ☕ Java (JDK 11+)
* 🌐 HTTP Client (java.net.http)
* 🔗 API REST (Jira)
* 🔔 Webhook (Slack)

---

## 🔐 Configuração

Antes de executar o projeto, configure suas credenciais:

### 📌 Jira

No arquivo `Jira.java`:

```java
public static String emailJira = "seu-email@dominio.com";
public static String tokenJira = "seu-token-api";
```

👉 Gere seu token em:
https://id.atlassian.com/manage-profile/security/api-tokens

---

### 📌 Slack

No arquivo `Slack.java`:

```java
public static String urlSlack = "sua-url-webhook";
```

👉 Crie um webhook em:
https://api.slack.com/messaging/webhooks

---

## ▶️ Como executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/atmos-agent-java.git
```

2. Abra o projeto em sua IDE (IntelliJ, Eclipse, etc)

3. Execute a classe:

```bash
Main.java
```

---

## ⏱ Configuração de Intervalo

No `LogService.java` você pode ajustar o tempo entre execuções:

```java
public static int getIntervalo() {
    return new Random().nextInt(60000) + 60000; // entre 1 e 2 minutos
}
```

---

## 📌 Exemplo de Fluxo

```text
[LOG]
Uso de CPU elevado detectado (acima de 85%)

↓

[ALERTA]
Chamado criado no Jira

↓

[SLACK]
🚨 Alerta do Sistema
ID do alerta: 123456
```

---

## 🧪 Exemplos de Eventos Gerados

* Uso de CPU elevado
* Consumo crítico de memória RAM
* Espaço em disco insuficiente
* Falhas em serviços
* Instabilidade de rede
* Tentativas de acesso não autorizado

---

## 💡 Melhorias Futuras

* 📊 Classificação de logs por nível (INFO, WARN, ERROR)
* 🔁 Retry automático em falhas de API
* ⚙️ Configuração via `.env`
* 🧵 Uso de `ScheduledExecutorService`
* 📈 Integração com ferramentas reais de monitoramento

---

## 👥 Equipe

### 2º Sprint

* Kaio Rodrigues Rocha
* Aurora de Carvalho de Inacio
* Anderson Augusto Lopes
* Matheus Campos de Menezes
* Luiz Vittor Marques Ribeiro
* Luana Gomes de Araujo

---

### 1º Sprint

* Guilherme Souto de Souza
* Guilherme Ornaghi
* Geovanna Victorianna de Oliveira
* Marley de Souza Santos
* Samara Vicky da Silva Mendonça
* Renan Minosso Silva

---

## 📄 Licença

Este projeto é de uso acadêmico e educacional.
