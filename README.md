# API2 - Gerenciar Pacientes

API 2 Gerenciar Pacientes

Este repositório contém um projeto API REST simples, construído com Java Spring & MongoDB.
O objetivo deste repositório é gerenciar o registro de paciente através de CRUD Java 

Este projeto foi desenvolvido durante a disciplina de Programação para Web II
do curso de Bacharelado em Sitemas de Informação da UNIME(União Metropolitana de Educação e Cultura)
no 2º semestre de 2023.

## Índice de Conteúdo

- [Instalação](#instalação)
- [Como Usar](#como-usar)
- [API Endpoints](#api-endpoints)
- [Base de Dados](#base-de-dados)

## Instalação

1. Clone o repositório:

```bash
$ git clone https://github.com/ulissesr/api2.git
```

2. Instale as dependências com o Maven

## Como Usar

1. Inicie o aplicativo com Maven
2. A API estará acessível em http://localhost:8080

## API Endpoints

A API fornece os seguintes endpoints:

```markdown
GET /api/pacientes - listar todos pacientes.

GET /api/pacientes/nome/sobrenome - obter paciente pelo nome e sobrenome.

DELETE /api/pacientes/remover/{id} - remover pelo id do paciente

PUT /api/pacientes/editar/{id} - editar um paciente pelo id
{
"nome": "John",
"sobrenome": "Wilson",
"cpf": "18929954502",
"idade": 1,
"sexo": "Masculino",
"contato": "(489)707-7607",
"logradouro": "569 Amanda Summit Apt. 189",
"numero": "44",
"bairro": "West Latashaburgh",
"cep": "41299-150",
"municipio": "Port Tamarahaven",
"estado": "BA"
}
POST /api/pacientes/cadastrar - cadastra um paciente
exemplo do corpo:
{
"nome": "bruno",
"sobrenome": "fraga",
"cpf": "999999999", - deve ser um cpf valido.
"idade": 15,
"cep": "41299-150",
}

```

## Base de Dados

O projeto usa o MongoDB como banco de dados.

Para instalar o MongoDB localmente [clique aqui](https://www.mongodb.com/try/download/community).

OU

Experimente um cluster gratuito e altamente disponível de 512
MB. [clique aqui](https://www.mongodb.com/cloud/atlas/register).

