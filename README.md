# Projeto Mensal – 3º Semestre  
## Sistema de Gerenciamento de Concessionária

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Acad%C3%AAmico-blue?style=for-the-badge)

---

## Descrição

Este projeto consiste em uma aplicação desenvolvida em **Java** com o objetivo de simular o gerenciamento de uma concessionária de veículos.

O sistema permite o cadastro e autenticação de usuários, além de operações relacionadas aos veículos disponíveis, como cadastro, listagem, busca, compra e remoção de veículos. O controle de acesso é realizado com base no tipo de usuário (Administrador ou Cliente).

Este trabalho foi desenvolvido como atividade avaliativa do **3º semestre**.

---

## Objetivo

Desenvolver um sistema em Java capaz de simular o gerenciamento de uma concessionária de veículos, aplicando conceitos de programação orientada a objetos, organização de classes e controle de acesso de usuários.

---

## Funcionalidades

- **Sistema de Login:** autenticação de usuários utilizando email e senha.  
- **Cadastro de Veículos:** apenas administradores podem cadastrar novos veículos.  
- **Listagem de Veículos:** exibe todos os veículos disponíveis no sistema.  
- **Busca de Veículos:** permite pesquisar veículos por critérios específicos.  
- **Compra de Veículos:** apenas clientes podem realizar a compra de veículos.  
- **Remoção de Veículos:** apenas administradores podem remover veículos do sistema.

---

## Requisitos

Para executar o projeto é necessário possuir instalado:

- **Java 8** ou superior  
- **Maven** para gerenciamento de dependências  
- IDE Java (recomendado: IntelliJ)

---

## Instalação e Execução

1. Clone o repositório:

```
git clone https://github.com/Vinnyda23/Projeto-Mensal-3-Semestre.git
```

2. Acesse a pasta do projeto:

```
cd Projeto-Mensal-3-Semestre
```

3. Compile o projeto com Maven:

```
mvn compile
```

4. Execute a aplicação:

```
mvn exec:java -Dexec.mainClass="com.github.vinnyda23.Main"
```

---

## Estrutura do Projeto

O projeto está organizado em diferentes classes responsáveis pelas funcionalidades do sistema:

- `Main.java` – classe principal responsável pela execução do sistema.  
- `SistemaUsuarios` – gerenciamento de cadastro e autenticação de usuários.  
- `Usuario` – representação dos usuários do sistema.  
- `Garagem` – gerenciamento dos veículos disponíveis.  
- `Veiculo` – representação dos veículos cadastrados.  
- `Carrinho` – responsável pelas compras realizadas.  
- `Opcao1` a `Opcao6` – classes responsáveis pelas opções do menu.

---

## Autores

Projeto desenvolvido por:

- **João Victor Alves de Oliveira Assunção**  
- **Lucas Marques Machado**  
- **Vinicius Leonel da Silva**

---

Projeto acadêmico desenvolvido para fins educacionais.
