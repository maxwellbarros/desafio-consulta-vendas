# Desafio Consulta Vendas 📊

Projeto desenvolvido como parte do desafio **Consulta Vendas** da [DevSuperior](https://github.com/devsuperior).

## 🚀 Tecnologias utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven

## 📂 Estrutura do projeto
- **Entidades**
  - `Seller`: representa o vendedor.
  - `Sale`: representa a venda realizada.
- **DTOs**
  - `SaleMinDTO`: dados mínimos de uma venda.
  - `SaleReportDTO`: relatório detalhado de vendas.
  - `SaleSummaryDTO`: sumário de vendas por vendedor.
- **Camadas**
  - `Controller`: expõe os endpoints REST.
  - `Service`: contém regras de negócio e tratamento de datas.
  - `Repository`: consultas JPQL para relatório e sumário.

## 📑 Endpoints
### Relatório de vendas
- `GET /sales/report`
  - Parâmetros opcionais: `minDate`, `maxDate`, `name`
  - Retorna listagem paginada com id, data, valor e nome do vendedor.

### Sumário de vendas por vendedor
- `GET /sales/summary`
  - Parâmetros opcionais: `minDate`, `maxDate`
  - Retorna soma das vendas agrupadas por vendedor no período informado.

## 📅 Regras de negócio
- Se `maxDate` não for informado → considerar a data atual.
- Se `minDate` não for informado → considerar 1 ano antes de `maxDate`.
- Se `name` não for informado → considerar texto vazio.

## 🛠️ Como executar
1. Clone o repositório:
   ```bash
   git clone https://github.com/maxwellbarros/desafio-consulta-vendas.git
