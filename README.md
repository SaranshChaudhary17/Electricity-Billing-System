# ⚡ Electricity Billing System

A modern, high-performance desktop application built using **Java Swing** and **MySQL** that automates the generation, calculation, and payment processing of electricity bills for consumers. 

This project features a fully modernized interface using the **FlatLaf** look-and-feel library and consolidates all application windows into a unified **Multiple Document Interface (MDI)** for a clean, professional user experience.

---

## 🎨 Design & UI Architecture

* **FlatLaf Light Theme Integration:** The user interface has been completely redesigned using the modern `FlatLightLaf` theme engine, replacing the legacy Java look-and-feel with a clean, high-DPI scaling design.
* **Consolidated MDI (Multiple Document Interface):** All sub-windows (e.g. *New Customer*, *Calculate Bill*) are configured as `JInternalFrame` containers that open and center dynamically inside the parent `JDesktopPane` rather than opening as floating OS-level frames.
* **Modernized Assets:** Integrated custom futuristic, minimalist illustrations for the login side-panel and home background.

---

## 🚀 Key Features

### 👤 Admin Dashboard
* **Customer Registration:** Add new customers to the system and automatically generate unique meter IDs.
* **Meter Configuration:** Set up meter locations (Inside/Outside), meter types (Electric/Solar/Smart), phase codes, and billing types.
* **Dynamic Bill Calculation:** Calculate electricity bills automatically based on unit consumption and standard tax rates (cost per unit, service tax, meter rent, etc.).
* **Records Management:** Search and view comprehensive customer profiles and deposit transactions in styled data tables.

### 👥 Customer Dashboard
* **Profile Management:** View and update personal information (Address, City, State, Phone, Email).
* **Bill Payments:** Select a billing month, view consumption, calculate totals, and process mock payments.
* **Bill Generator:** Generate clean, print-ready digital invoice summaries for any billing month.

---

## 📁 Repository Structure

```text
├── electricity/
│   └── billing/
│       └── system/             # Core Java Source Code (.java files)
├── net/
│   └── proteanit/
│       └── sql/
│           └── DbUtils.java    # Embedded JTable helper (eliminates external dependency)
├── icon/                       # Modernized UI image and icon assets
├── database.sql                # Complete MySQL Schema & default seed data
├── flatlaf-3.4.1.jar           # Modern Swing theme engine library
├── mysql-connector-j-8.0.33.jar# JDBC database connector jar
├── .gitignore                  # Prevents compilation class binaries from being tracked
└── README.md                   # Setup and execution guide
```

---

## 💾 Database Schema

The database relies on five primary relational tables under the `ebs` schema:

1. **`login`:** Stores account credentials and authorization levels (Admin / Customer).
2. **`customer`:** Holds customer profiles linked via a unique `meter_no`.
3. **`meter_info`:** Stores physical meter specifications.
4. **`tax`:** Configures tax slabs, service charges, and cost per unit for dynamic bill calculation.
5. **`bill`:** Tracks unit consumption, total cost, month, and payment status (`Paid` / `Not Paid`).

---

## 💻 Local Setup & Execution Guide

### Prerequisite 1: MySQL Database Setup
1. Open your MySQL client (Command Line Client, MySQL Workbench, etc.).
2. Import the database and seed the default tables using the SQL script:
   ```sql
   source /path/to/database.sql;
   ```
3. By default, the application connects to MySQL on port `3306` with username `root` and password `Jaat@9412`. If you wish to change these credentials, update them in [Conn.java](file:///d:/Electricity-Billing-System-main/electricity/billing/system/Conn.java#L16).

### Prerequisite 2: Java Development Kit (JDK)
Ensure you have **JDK 8 or higher** installed. Check your version by running:
```bash
java -version
```

---

## ⚡ Compile and Run

Open your terminal or PowerShell window in the root directory (`d:\Electricity-Billing-System-main`) and execute the following commands:

### 1. Compile the Project
Build the project using the FlatLaf classpath compiler flag:
```bash
javac -cp ".;flatlaf-3.4.1.jar" net/proteanit/sql/DbUtils.java electricity/billing/system/*.java
```

### 2. Run the Application
Start the application launcher with both FlatLaf and MySQL JDBC libraries in the classpath:
```bash
java -cp ".;flatlaf-3.4.1.jar;mysql-connector-j-8.0.33.jar" electricity.billing.system.Login
```

> **Default Admin Credentials:**
> * **Username:** `admin`
> * **Password:** `admin`
