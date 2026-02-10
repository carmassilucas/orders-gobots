# 🚀 Order GoBots — Gerenciamento de Pedidos

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://kotlinlang.org/docs/home.html)
[![Language: Kotlin](https://img.shields.io/badge/language-kotlin-green.svg)](https://kotlinlang.org/)
[![Version: 1.0.0](https://img.shields.io/badge/version-1.0.0-yellowgreen.svg)](https://github.com/carmassilucas/orders-gobots)

O **Order GoBots** é um MVP construído com uma arquitetura de **microsserviços**, voltado ao processamento de pedidos e ao registro de seus respectivos status.

O domínio é composto por dois serviços principais:

- **Marketplace API**  
  Responsável pelo gerenciamento dos pedidos e pela atualização de seus status.  
  Desenvolvido com **Spring Boot**, **Kotlin** e **PostgreSQL**.

- **Receiver API**  
  Responsável por armazenar um *snapshot* de cada status do pedido, desde a sua criação até a sua conclusão.  
  Desenvolvido com **Spring Boot**, **Kotlin** e **MongoDB**.

A comunicação entre os serviços é realizada via **Feign Client**, e todo o ambiente é **orquestrado com Docker**.

---

## 🛠️ Principais Tecnologias

- **Linguagem:** Kotlin  
- **Framework:** Spring Boot 4.x  
- **Bancos de dados:** PostgreSQL 18 & MongoDB 8  
- **Orquestração:** Docker & Docker Compose  

---

## 📦 Como Subir o Projeto

Para subir todo o ecossistema com um único comando, certifique-se de que:

- O **Docker** esteja em execução em sua máquina
- As portas **8081, 8082, 5432 e 27017** estejam disponíveis

### 1️⃣ Clonando o repositório

```bash
# Clona o repositório
$ git clone https://github.com/carmassilucas/orders-gobots.git
```

### 2️⃣ Subindo os serviços

Com o projeto clonado, certifique-se de estar na pasta raiz do projeto, pois os comandos a seguir serão executados a partir dela.

```bash
# Constrói as imagens e sobe os containers em modo background
$ docker-compose -f docker/docker-compose.yml up --build -d
```

### 3️⃣ Verificando a saúde dos serviços

Aguarde até que os serviços estejam com o status *healthy* antes de realizar as requisições.

```bash
# Lista os containers em execução
$ docker ps
```

---

## ☕ Como Usar os Endpoints

> [!NOTE]
> É possível encontrar uma collection pronta na pasta `collection`, a partir da raiz do projeto. Caso o import não funcione, você pode importar os endpoints manualmente via cURL.

<details>

<summary>Endpoints da Marketplace API para gerenciamento de pedidos</summary>

### 1️⃣ Cadastrar Webhook

**Finalidade:** Cadastrar um novo webhook relacionado às lojas já cadastradas

```bash
# Os IDs das lojas foram inseridos hardcoded via SQL no banco de dados, não os altere
curl -X POST "http://localhost:8081/webhooks" \
  -H "Content-Type: application/json" \
  -d '{
    "storeIds": [
      "894678dd-1a6d-4222-bec7-58c710a6ee07",
      "8ad3072a-242b-42ff-b7a7-23e1a986abe2"
    ],
    "callbackUrl": "http://receiver-api:8082/events"
  }'
```

### 2️⃣ Cadastrar Pedido

**Finalidade:** Cadastrar um novo pedido para uma loja

```bash
# Substitua VALUE pelo valor desejado
curl -X POST "http://localhost:8081/orders" \
  -H "Content-Type: application/json" \
  -d '{
    "storeId": "894678dd-1a6d-4222-bec7-58c710a6ee07",
    "amount": "VALUE"
  }'
```

### 3️⃣ Atualizar Stataus do Pedido

**Finalidade:** Atualizar o status de um pedido

```bash
# Substitua ORDER_ID pelo id do pedido e STATUS pelo nome do status que deseja atualizar
curl -X PATCH "http://localhost:8081/orders/ORDER_ID/STATUS"
```

</details>

---

## 🍃 Consultar MongoDB

Se você quiser verificar os snapshots salvos no MongoDB diretamente pelo terminal, sem precisar instalar nenhum client ou entrar no shell interativo, use o seguinte comando:

> [!NOTE]
> Esse comando faz a autenticação no servidor de banco de dados do MongoDB, seleciona o banco de dados *receiver-db*, busca todos os documentos na coleção *order_events* e formata a saída de forma amigável

```bash
$ docker exec -it receiver-db mongosh -u receiver-db-user -p receiver-db-pass --authenticationDatabase admin --eval "db.getSiblingDB('receiver-db').order_events.find().pretty()"
```

---

## 🔍 Monitoramento e Saúde

As APIs possuem um endpoint do Spring Actuator configurado para o monitoramento da saúde de cada aplicação:

- **Marletplace API:** http://localhost:8081/actuator/health
- **Receiver API:** http://localhost:8082/actuator/health

---

## 📊 Ciclo de Vida do Pedido

O diagrama a seguir ilustra os possíveis fluxos de estado de um pedido e como ele pode evoluir ao longo do seu ciclo de vida.
Ele serve para facilitar o entendimento das transições, mostrando quais status podem ser alcançados a partir de cada etapa.

![Ciclo de Vida do Pedido](./image/ciclo-vida-pedido.png)
