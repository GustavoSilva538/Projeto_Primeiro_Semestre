# 📦 Projeto Semestre 1 - Sistema de Gerenciamento Estruturado (Terminal)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

Este repositório contém o projeto final desenvolvido durante o 1º semestre do curso de Análise e Desenvolvimento de Sistemas (ADS). 

O sistema é um **CRUD (Create, Read, Update, Delete)** de gerenciamento executado inteiramente via linha de comando (Terminal), incluindo autenticação de usuários e controle de acesso. O grande diferencial acadêmico deste código é a **ausência total de Orientação a Objetos (POO)**. Toda a lógica de armazenamento e manipulação de dados foi construída utilizando os fundamentos primários da programação procedural.

## 🔑 Autenticação e Perfis de Acesso

O sistema conta com uma tela de login inicial que define as permissões de uso, utilizando credenciais fixas no código para fins de demonstração acadêmica:

* **Administrador:** Acesso total a todas as funcionalidades do sistema (Cadastrar, Listar, Atualizar e Excluir).
  * *Usuário:* `admin`
  * *Senha:* `admin123`
* **Funcionário:** Acesso restrito para operação diária (Permissão apenas para Listar e Atualizar estoque). As funções de criar e excluir produtos ficam bloqueadas no menu.
  * *Usuário:* `func`
  * *Senha:* `func123`

## 🎯 Conceitos Algorítmicos Aplicados

Para simular um banco de dados sem utilizar instâncias de objetos ou coleções dinâmicas (como `ArrayList`), o sistema emprega:
* **Arrays Paralelos:** Três vetores distintos (`ids`, `nomes`, `quantidades`) controlados por um índice comum para manter a relação dos dados.
* **Modularização:** Uso intenso de funções (`métodos estáticos`) para isolar responsabilidades (login, cadastrar, listar, atualizar, excluir).
* **Controle de Fluxo (Flags):** Implementação de uma variável de sessão (`nivelAcesso`) que interage dinamicamente com o `switch-case` para exibir ou bloquear módulos do menu.
* **Deslocamento de Vetor (Shift):** Lógica matemática e estrutural em laços `for` para reordenar o array ao excluir um item, evitando espaços nulos (`null`) ou buracos na memória.

