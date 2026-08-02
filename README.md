# 📦 Inventory Management System

A modern **Enterprise Inventory Management System** built using **Spring Boot, React, Material UI, MySQL, and REST APIs**. The system provides complete inventory, warehouse, purchasing, sales, supplier, customer, and stock management for small and medium-sized businesses.

---

# 📖 Table of Contents

- Overview
- Features
- Technology Stack
- System Architecture
- Database Structure
- Modules
- Business Workflow
- Project Structure
- Installation
- API Documentation
- Security
- Reports
- Future Enhancements
- License

---

# 🚀 Overview

The Inventory Management System is a full-stack web application designed to digitize inventory operations. It enables businesses to efficiently manage products, warehouses, suppliers, customers, purchases, sales, invoices, payments, and inventory movements through a centralized platform.

The project follows modern enterprise software development principles using a layered architecture, RESTful APIs, and responsive user interfaces.

---

# 🎯 Objectives

- Digitize inventory operations
- Reduce manual work
- Improve inventory accuracy
- Track stock in real time
- Manage purchasing workflows
- Manage sales workflows
- Support multiple warehouses
- Generate business reports
- Improve operational efficiency
- Build a scalable enterprise application

---

# ✨ Features

## Dashboard

- Business Overview
- Inventory Summary
- Total Products
- Total Categories
- Total Suppliers
- Total Customers
- Total Warehouses
- Current Stock Value
- Monthly Purchases
- Monthly Sales
- Low Stock Alerts
- Recent Transactions
- Charts & Analytics

---

## Product Management

- Product CRUD
- Product Images
- SKU Management
- Barcode Ready
- QR Code Ready
- Product Description
- Cost Price
- Selling Price
- Product Status
- Category Assignment
- Brand Assignment
- Unit Assignment
- Warehouse Stock

---

## Category Management

- Create Categories
- Update Categories
- Delete Categories
- Search Categories

---

## Brand Management

- Brand CRUD
- Product Association

---

## Unit Management

Examples

- Piece
- Box
- Carton
- Kilogram
- Gram
- Liter
- Meter

---

## Supplier Management

- Supplier Registration
- Contact Details
- Purchase History
- Outstanding Balance
- Active / Inactive Status

---

## Customer Management

- Customer Registration
- Sales History
- Payment History
- Customer Balance

---

## Warehouse Management

- Warehouse CRUD
- Warehouse Locations
- Warehouse Capacity
- Warehouse Transfers
- Stock by Warehouse

---

## Purchase Management

### Purchase Orders

- Create Purchase Order
- Edit Purchase Order
- Approve Purchase Order
- Cancel Purchase Order

### Goods Received Notes

- Receive Goods
- Partial Receive
- Complete Receive
- Update Inventory

### Purchase Returns

- Return Products
- Supplier Credit Notes

---

## Sales Management

### Sales Orders

- Create Sales Order
- Edit Sales Order
- Cancel Sales Order

### Invoices

- Invoice Generation
- Invoice Items
- Discounts
- Taxes

### Payments

- Cash
- Credit Card
- Bank Transfer
- Credit Payments

### Sales Returns

- Customer Returns
- Refund Processing
- Stock Updates

---

## Inventory Management

### Stock

- Current Stock
- Stock Value
- Warehouse Stock

### Stock In

- Purchases
- Manual Adjustments
- Initial Stock

### Stock Out

- Sales
- Damaged Goods
- Manual Issue

### Stock Transfer

- Warehouse Transfers

### Stock Adjustment

- Increase Stock
- Reduce Stock

### Stock Movement

- Complete Inventory History
- Transaction Tracking

---

# 🏗 Technology Stack

## Frontend

- React
- JavaScript (ES6+)
- Material UI (MUI)
- React Router
- Axios
- HTML5
- CSS3

---

## Backend

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate ORM
- Bean Validation
- REST APIs
- Spring Security (Planned)

---

## Database

- MySQL
- Foreign Keys
- Constraints
- Indexes
- Transactions

---

## Development Tools

- IntelliJ IDEA
- VS Code
- Maven
- npm
- Git
- GitHub

---

## API Development

- Swagger / OpenAPI
- Postman

---

# 🏛 System Architecture

```
                Client Browser
                      │
                      ▼
          React + Material UI Frontend
                      │
             Axios REST API Requests
                      │
                      ▼
          Spring Boot REST API Server
                      │
          Spring Data JPA / Hibernate
                      │
                      ▼
               MySQL Database
```

---

# 🗄 Database Tables

## Master Tables

- users
- products
- categories
- brands
- suppliers
- customers
- units
- warehouses
- warehouse_locations

---

## Purchase Tables

- purchase_orders
- purchase_order_items
- goods_received_notes
- goods_received_note_items
- purchase_returns
- purchase_return_items

---

## Sales Tables

- sales_orders
- sales_order_items
- invoices
- invoice_items
- payments
- sales_returns
- sales_return_items

---

## Inventory Tables

- stocks
- stock_ins
- stock_outs
- stock_movements
- stock_adjustments
- stock_adjustment_items
- stock_transfers
- stock_transfer_items

---

# 🔄 Business Workflow

## Purchasing

```
Purchase Order
      │
      ▼
Approval
      │
      ▼
Goods Received Note
      │
      ▼
Inventory Updated
      │
      ▼
Supplier Payment
```

---

## Sales

```
Sales Order
      │
      ▼
Approval
      │
      ▼
Invoice
      │
      ▼
Payment
      │
      ▼
Stock Out
```

---

## Returns

```
Customer Return
      │
      ▼
Sales Return
      │
      ▼
Stock Updated
```

---

# 📂 Recommended Project Structure

```
inventory-management/

├── backend/
│   ├── config/
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── exception/
│   ├── mapper/
│   ├── repository/
│   ├── security/
│   ├── service/
│   ├── validation/
│   └── resources/
│
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── assets/
│   │   ├── components/
│   │   ├── context/
│   │   ├── hooks/
│   │   ├── layouts/
│   │   ├── pages/
│   │   ├── routes/
│   │   ├── services/
│   │   └── utils/
│   └── package.json
│
├── database/
│   └── inventory_management.sql
│
├── docs/
│   ├── api/
│   ├── architecture/
│   ├── screenshots/
│   └── database/
│
└── README.md
```

---

# ⚙ Installation

## Clone Repository

```bash
git clone https://github.com/your-username/inventory-management.git
```

---

## Backend

```bash
cd backend

mvn clean install

mvn spring-boot:run
```

Backend URL

```
http://localhost:8080
```

---

## Frontend

```bash
cd frontend

npm install

npm run dev
```

Frontend URL

```
http://localhost:5173
```

---

## Database

Create a database named:

```sql
CREATE DATABASE inventory_management;
```

Import the SQL dump and configure `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_management
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

---

# 📚 API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

# 🔐 Security Features

- User Authentication
- Password Encryption (BCrypt)
- JWT Authentication (Planned)
- Role-Based Access Control (RBAC)
- Input Validation
- SQL Injection Protection (JPA/Hibernate)
- Global Exception Handling
- CORS Configuration

---

# 📊 Reports

- Inventory Report
- Sales Report
- Purchase Report
- Supplier Report
- Customer Report
- Warehouse Report
- Low Stock Report
- Stock Movement Report
- Revenue Report
- Profit Analysis (Planned)

---

# 🚀 Future Enhancements

- Barcode Generation
- QR Code Support
- PDF Invoice Generation
- Excel Export
- CSV Export
- Email Notifications
- SMS Notifications
- Mobile Application
- Progressive Web App (PWA)
- Multi-Company Support
- Multi-Currency
- Multi-Language
- Approval Workflows
- Audit Logs
- Dashboard Analytics
- AI Sales Forecasting
- AI Inventory Prediction
- Docker Support
- Kubernetes Deployment
- CI/CD Pipeline
- Cloud Deployment (AWS, Azure, OCI)
- Redis Caching
- Elasticsearch
- RabbitMQ
- Microservices Architecture

---

# 🧪 Testing

- Unit Testing
- Integration Testing
- API Testing
- UI Testing
- Performance Testing
- Security Testing
- User Acceptance Testing (UAT)

---

# 📈 Performance Optimizations

- Database Indexing
- Pagination
- Lazy Loading
- Optimized SQL Queries
- API Response Caching
- Batch Processing
- Image Optimization
- Responsive UI

---

# 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to your branch
5. Open a Pull Request

---

# 📄 License

This project is licensed under the MIT License.

---

# 👨‍💻 Author

**Charith Kalhara**

Computer Science Student

Built with ❤️ using Java, Spring Boot, React, Material UI, and MySQL.
