# Inventory Management System

A Spring Boot REST API for managing an inventory operation across products, warehouses, purchasing, sales, invoicing, payments, and stock movements. The application persists data in MySQL, exposes interactive OpenAPI documentation, and protects its business API with JWT bearer authentication.

## Contents

- [Capabilities](#capabilities)
- [Technology](#technology)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Getting started](#getting-started)
- [Authentication](#authentication)
- [API reference](#api-reference)
- [Data model](#data-model)
- [Project layout](#project-layout)
- [Testing](#testing)
- [Security notes](#security-notes)
- [Contributing](#contributing)

## Capabilities

- JWT-based registration and login with BCrypt password hashing
- Data management for brands, categories, units, suppliers, customers, products, and warehouses
- Warehouse locations and stock records by product and warehouse
- Purchase orders and purchase returns
- Sales orders, invoices, payments, and sales returns
- Stock-in, stock-out, transfers, adjustments, and searchable movement history
- A dashboard with total, inventory value, low-stock count, and five most recent invoices
- Swagger UI documentation

## Technology

| Area | Implementation |
| --- | --- |
| Language | Java 21 |
| Framework | Spring Boot 4.1.0, Spring MVC |
| Persistence | Spring Data JPA / Hibernate |
| Database | MySQL |
| Security | Spring Security, JWT, BCrypt |
| API documentation | Swagger UI |
| Build | Maven Wrapper |

## Architecture

```text
HTTP client / API consumer
          |
          v
Spring MVC controllers --> services --> repositories --> MySQL
          |
          +--> JWT authentication filter
```

The codebase follows a conventional layered structure. Controllers accept request DTOs, services hold application logic, repositories access JPA entities, and mappers produce response DTOs.

## Prerequisites

- JDK 21
- MySQL 8.0 or compatible server
- A MySQL account with permission to create and update the application schema

## Getting started

### 1. Clone the repository

```bash
git clone https://github.com/CharithKalhara/Inventory-Management-System.git
cd Inventory-Management-System
```

### 2. Create the database

```sql
CREATE DATABASE inventory_management
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
```

### 3. Configure the datasource

Update `src/main/resources/application.properties` with your local MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_management
spring.datasource.username=root
spring.datasource.password=change-me
```

The default configuration uses `spring.jpa.hibernate.ddl-auto=update`, so Hibernate creates and evolves the schema when the API starts. Use a dedicated database account and review this setting before using the application in production.

### 4. Configure the JWT secret

Replace the development secret in `application.properties` with a unique, Base64-encoded secret. For example:

```properties
jwt.secret=<a-long-unique-base64-secret>
jwt.expiration=86400000
```

`jwt.expiration` is measured in milliseconds; the configured value is 24 hours.

### 5. Run the API

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

On macOS/Linux:

```bash
./mvnw spring-boot:run
```

The API listens on `http://localhost:8080` by default.

### 6. Open the API documentation

Browse to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html). The OpenAPI JSON is available at [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs).

## Authentication

Only `/auth/**`, Swagger UI, and OpenAPI routes are public. All `/api/**` routes require a JWT.

### Register a user

```http
POST /auth/register
Content-Type: application/json

{
  "username": "admin",
  "password": "use-a-strong-password",
  "role": "ADMIN"
}
```

Supported roles are `ADMIN`, `MANAGER`, and `STAFF`.

### Log in

```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "use-a-strong-password"
}
```

The response contains a token:

```json
{ "token": "<jwt>" }
```

Send it with protected requests:

```http
Authorization: Bearer <jwt>
```

Example:

```bash
curl http://localhost:8080/api/dashboard \
  -H "Authorization: Bearer <jwt>"
```

## API reference

Swagger UI is the authoritative source for request and response schemas. The table below summarizes the implemented route groups; resource groups generally support create, list, retrieve by ID, update, and delete unless noted.

| Area | Base path | Operations |
| --- | --- | --- |
| Authentication | `/auth` | `POST /register`, `POST /login` |
| Dashboard | `/api/dashboard` | `GET` |
| Products | `/api/products` | CRUD |
| Categories | `/api/categories` | CRUD |
| Brands | `/api/brands` | CRUD |
| Units | `/api/units` | CRUD |
| Suppliers | `/api/suppliers` | CRUD |
| Customers | `/api/customers` | CRUD |
| Warehouses | `/api/warehouses` | CRUD |
| Current stock | `/api/stocks` | CRUD |
| Stock-in records | `/api/stock-ins` | CRUD |
| Stock-out records | `/api/stock-outs` | CRUD |
| Stock adjustments | `/api/stock-adjustments` | Create, list, get, delete |
| Stock transfers | `/api/stock-transfers` | Create, list, get, delete |
| Stock movement history | `/api/stock-movements` | `GET`, `GET /product/{productId}`, `GET /warehouse/{warehouseId}` |
| Purchase orders | `/api/purchase-orders` | CRUD |
| Goods-received notes | `/api/grns` | CRUD |
| Purchase returns | `/api/purchase-returns` | CRUD |
| Sales orders | `/api/sales-orders` | CRUD |
| Invoices | `/api/invoices` | CRUD |
| Payments | `/api/payments` | CRUD |
| Sales returns | `/api/sales-returns` | CRUD |

All endpoints except the two authentication routes require `Authorization: Bearer <jwt>`.

## Data model

The main domain entities are grouped as follows:

| Group | Entities |
| --- | --- |
| Identity | User, Role |
| Catalog | Product, Category, Brand, Unit |
| Parties and locations | Supplier, Customer, Warehouse, WarehouseLocation |
| Inventory | Stock, StockIn, StockOut, StockAdjustment, StockTransfer, StockMovement |
| Purchasing | PurchaseOrder, PurchaseOrderItem, GoodsReceivedNote, GoodsReceivedNoteItem, PurchaseReturn, PurchaseReturnItem |
| Sales | SalesOrder, SalesOrderItem, Invoice, InvoiceItem, Payment, SalesReturn, SalesReturnItem |

Stock movements use the following event types: `STOCK_IN`, `STOCK_OUT`, `PURCHASE`, `SALE`, `TRANSFER_IN`, `TRANSFER_OUT`, and `ADJUSTMENT`. Purchase order status values are `PENDING`, `CONFIRMED`, `SHIPPED`, and `DELIVERED`.

## Project layout

```text
src/main/java/org/example/inventorymanagementsystem/
├── config/        # Security and application configuration
├── controller/    # REST endpoints
├── dto/           # Request and response contracts
├── entity/        # JPA entities and enums
├── exception/     # Domain exceptions and exception advice
├── mapper/        # Entity-to-response mapping
├── repository/    # Spring Data repositories
├── security/      # JWT service and authentication filter
└── service/       # Service interfaces and implementations

src/main/resources/
└── application.properties
```

## Testing

Run the automated test suite with:

```bash
./mvnw test
```

On Windows:

```powershell
.\mvnw.cmd test
```

## Security notes

- Passwords are stored with BCrypt.
- The API is stateless and uses a JWT authentication filter.
- CORS is currently limited to `http://localhost:5173`, `http://localhost:5174`, and `http://192.168.1.3:5173`. Update `SecurityConfig` for other clients or deployment domains.
- Authentication is required for every business endpoint. Roles are stored on users and embedded in the authentication flow; no controller-level role restrictions are currently configured.
- Do not reuse the committed development JWT secret in a deployed environment. Move database credentials and JWT secrets into environment-specific configuration before deployment.


## License

No license file is currently included in this repository. Contact the repository owner before reusing or distributing the code.

## Author

Charith Kalhara — [GitHub](https://github.com/CharithKalhara)
