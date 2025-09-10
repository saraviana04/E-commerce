# E-commerce

# 🛒 Descomplicommerce

Um projeto de **e-commerce fullstack** desenvolvido para estudos e prática de arquitetura moderna de sistemas, integrando **Spring Boot (Java 17)** no backend com **Angular 14+** no frontend, banco de dados **PostgreSQL** e comunicação via **REST API**.

---

## 📌 Tecnologias Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3+**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **H2 Database** (para testes)
- **Maven**

### Frontend
- **Angular 14+**
- **RxJS**
- **Bootstrap / CSS customizado**
- **TypeScript**

---

## ⚙️ Funcionalidades

### 🖥️ Frontend (Angular)
- Listagem de produtos disponíveis.
- Adição de produtos ao carrinho.
- Atualização de quantidade de itens.
- Remoção de itens individuais.
- Limpeza total do carrinho.
- Cálculo automático do valor total.
- Finalização da compra com exibição de **toast de confirmação**.

### 🛠️ Backend (Spring Boot)
- API RESTful com endpoints:
  - `GET /api/produtos` → lista de produtos
  - `GET /api/carrinho` → listar itens do carrinho
  - `POST /api/carrinho/adicionar` → adicionar item
  - `PATCH /api/carrinho/{produtoId}?quantidade=X` → atualizar quantidade
  - `DELETE /api/carrinho/{produtoId}` → remover item
  - `DELETE /api/carrinho` → limpar carrinho
  - `GET /api/carrinho/total` → obter valor total
  - `POST /api/carrinho/finalizar` → finalizar compra (retorna recibo)

- Persistência dos dados via **Spring Data JPA**.
- Seed inicial de produtos através do `DataSeeder`.

---

## 🚀 Como Rodar o Projeto

### 📦 Backend
1. Acesse a pasta `descomplicommerce-api`.
2. Compile e execute com Maven:
   ```bash
   mvn spring-boot:run

