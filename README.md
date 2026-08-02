# Inventory Management System

A full-stack web-based **Inventory Management System** built using **React**, **Spring Boot**, and **MySQL**. The application helps businesses efficiently manage products, categories, suppliers, brands, and inventory through a modern, responsive interface.

## Features

- Secure user authentication
- Interactive dashboard with inventory statistics
- Product management (Create, Read, Update, Delete)
- Category management
- Brand management
- Supplier management
- Unit management
- Product search and filtering
- Pagination
- Stock quantity management
- Responsive Material UI interface
- RESTful API integration

---

## Tech Stack

### Frontend
- React
- Material UI (MUI)
- Axios
- React Router

### Backend
- Spring Boot
- Spring Data JPA
- Spring Security
- REST API

### Database
- MySQL

### Development Tools
- IntelliJ IDEA
- Visual Studio Code
- Postman
- Git
- Maven

---

## Project Structure

```text
inventory-management-system/
├── frontend/
│   ├── src/
│   ├── public/
│   └── package.json
│
├── backend/
│   ├── src/
│   ├── pom.xml
│   └── application.properties
│
└── README.md
```

---

## System Architecture

```text
React Frontend
       │
       ▼
Spring Boot REST API
       │
       ▼
      MySQL Database
```

---

## Modules

### Dashboard
- Displays inventory statistics
- Shows key business metrics

### Products
- Add, edit, delete, and view products
- Manage SKU, pricing, stock quantity, and descriptions

### Categories
- Manage product categories

### Brands
- Manage product brands

### Suppliers
- Store supplier information

### Units
- Manage measurement units

---

## Installation

### Clone the Repository

```bash
git clone https://github.com/your-username/inventory-management-system.git
```

### Backend

```bash
cd backend
mvn spring-boot:run
```

> Configure your MySQL database in `application.properties` before running the backend.

### Frontend

```bash
cd frontend
npm install
npm run dev
```

---

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/products` | Get all products |
| GET | `/products/{id}` | Get product by ID |
| POST | `/products` | Create a product |
| PUT | `/products/{id}` | Update a product |
| DELETE | `/products/{id}` | Delete a product |
| GET | `/categories` | Get all categories |
| GET | `/brands` | Get all brands |
| GET | `/suppliers` | Get all suppliers |
| GET | `/units` | Get all units |

---

## Future Enhancements

- Role-based access control
- Purchase management
- Sales management
- Inventory reports
- Barcode scanning
- Export to Excel and PDF
- Low-stock alerts
- Docker deployment
- Dashboard analytics
- Audit logs

---

## Learning Outcomes

This project demonstrates practical experience with:

- Full-stack web development
- React and Material UI
- Spring Boot REST APIs
- MySQL database design
- CRUD operations
- API integration
- Responsive UI development
- Git version control

---

## Author

**Charith Kalhara**

Computer Science Undergraduate

---

## 📄 License

This project is licensed under the MIT License.
