# E-Commerce Database Project
This project provides a set of SQL scripts to set up and manage a database for an e-commerce application. The database schema is designed to handle customers, products, orders, and order items. Additionally, it includes stored procedures and triggers to facilitate data operations and maintain integrity.

## Project Overview
The e-commerce database is structured to support the core functionalities of an online shopping platform. It includes tables for managing customer information, product inventory, order processing, and itemized order details.

## Getting Started
### Prerequisites
PostgreSQL or any compatible SQL database system.
### Installation
1- Clone this repository to your local machine.

2- Execute the SQL scripts in order:

#### psql -U your_username -d your_database -f createSchema.sql
#### psql -U your_username -d your_database -f createStoredProcedure.sql/
#### psql -U your_username -d your_database -f createTriggerFunction.sql/
## Database Schema
#### The schema is established through createSchema.sql and includes:

##### customers: Records customer information.
##### products: Contains product details.
##### orders: Manages order records.
##### order_items: Tracks individual items in each order.
Stored Procedures
The AddOrder stored procedure in createStoredProcedure.sql:

Inserts new orders.
Adds items to order_items.
Updates product stock levels.
Triggers
Implemented in createTriggerFunction.sql, the triggers:

UpdateStockQuantity: Ensures stock levels are current after order item insertion.
Usage
Use this database setup to explore SQL database management within an e-commerce context, focusing on the integration of procedures and triggers for data operations.
