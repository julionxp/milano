# 🏪 Milano — E-commerce com Spring Boot e Thymeleaf

Milano é um projeto de **e-commerce**, desenvolvido com **Java + Spring Boot**, que permite o cadastro e autenticação de usuários, exibição dinâmica de produtos e gerenciamento de um carrinho de compras. O sistema também permite **criar e deletar produtos via API** (testado com o Insomnia) e integra-se com um banco **MySQL** para persistência dos dados.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **Thymeleaf**
- **MySQL**
- **Maven**

---

## ⚙️ Funcionalidades

✅ Cadastro e login de usuários  
✅ Exibição de produtos por categoria (Camisas Brasileiras, Internacionais e Seleções)  
✅ Adição de produtos ao carrinho  
✅ Edição de perfil com endereço vinculado ao usuário  
✅ Cadastro e exclusão de produtos via API (Insomnia)  
✅ Integração total com banco de dados MySQL  

---

## 🧩 Estrutura do Projeto

src/
├── main/
│ ├── java/com/milano/api/
│ │ ├── controller/ # Controladores MVC
│ │ ├── model/ # Entidades (Usuario, Endereco, Produto, Carrinho)
│ │ ├── repository/ # Interfaces JPA
│ │ └── service/ # Regras de negócio
│ └── resources/
│ ├── static/ # CSS, imagens e ícones
│ ├── templates/ # Páginas Thymeleaf (home, perfil, carrinho, etc)
│ └── application.properties

---

## 🗄️ Configuração do Banco de Dados

No arquivo `application.yaml`:

CREATE DATABASE milano;

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/milano_db?useSSL=false&serverTimezone=UTC
    username: root
    password: 
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect

---

bash
Copiar código
git clone https://github.com/seuusuario/milano.git
Entre na pasta do projeto:

bash
ApiApplication.java -> Run

Acesse no navegador:
http://localhost:8080/home

bash
➕ Criar um produto:
Crie um Request Post no Insomnia, cole o servidor e teste usando o código abaixo selecionando o formato JSON:
POST http://localhost:8080/api/produtos

json
Copiar código
{
  "nome": "Camisa Fluminense 2025",
  "preco": 199.90,
  "imagemUrl": "https://exemplo.com/camisa.jpg",
  "categoria": "Brasileiras"
}

❌ Excluir um produto
Troque o Request para DELETE e poem o id do produto, ex: http://localhost:8080/api/produtos/22
DELETE http://localhost:8080/api/produtos/{id}