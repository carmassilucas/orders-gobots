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
