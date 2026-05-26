# Electricity Billing System

A modern desktop application built using **Java Swing** and **MySQL** that automates the generation, calculation, and payment processing of electricity bills for consumers. 

This project is fully reconstructed from source, self-contained, and optimized for quick local setup.

---

## 🚀 Key Features

* **Admin Portal:**
  * Add new customers and generate meter information.
  * Calculate electricity bills dynamically based on unit consumption and tax rates.
  * View complete list of customer profiles and deposit/payment details.
* **Customer Portal:**
  * View and update personal profile information.
  * View current bills and calculate totals.
  * Process mock payments for outstanding bills.
  * Generate print-friendly PDF-like billing summaries.

---

## 🛠️ Tech Stack

* **Front-End:** Java AWT & Swing (Desktop GUI)
* **Back-End:** Java Core (JDK 8+)
* **Database:** MySQL
* **Driver:** MySQL Connector/J

---

## 📁 Repository Structure

```text
├── electricity/
│   └── billing/
│       └── system/             # Core Java Source Code (.java files)
├── net/
│   └── proteanit/
│       └── sql/
│           └── DbUtils.java    # Embedded JTable helper (no external JAR needed)
├── icon/                       # UI Assets & Graphical Icons (.png, .jpg)
├── database.sql                # Complete MySQL Schema & Default Seed Data
└── README.md                   # Setup and execution guide
```

---

## 💻 Local Setup & Execution Guide

### Prerequisite 1: Database Setup
1. Open your MySQL client (Command Line, Workbench, or phpMyAdmin).
2. Create the database and seed the default tables by executing:
   ```sql
   source /path/to/database.sql;
   ```
3. Ensure MySQL is running on port `3306` with the default user `root` and password `9211` (or update these details in [Conn.java](file:///d:/Electricity-Billing-System-main/electricity/billing/system/Conn.java)).

### Prerequisite 2: Dependencies (JARs)
Place these two JAR files in your root directory:
* **MySQL Connector/J:** [Download mysql-connector-j-8.0.33.jar](https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar)
* **FlatLaf Modern Look & Feel:** [Download flatlaf-3.4.1.jar](https://repo1.maven.org/maven2/com/formdev/flatlaf/3.4.1/flatlaf-3.4.1.jar)

---

## ⚡ Compile and Run

Open your terminal or command prompt inside the project root directory:

### 1. Compile the Project
```bash
javac -cp ".;flatlaf-3.4.1.jar" net/proteanit/sql/DbUtils.java electricity/billing/system/*.java
```

### 2. Run the Application
```bash
java -cp ".;flatlaf-3.4.1.jar;mysql-connector-j-8.0.33.jar" electricity.billing.system.Login
```

> **Default Admin Credentials:**
> * **Username:** `admin`
> * **Password:** `admin`
