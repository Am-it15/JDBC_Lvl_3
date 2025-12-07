# 🏫 Teaching Staff Management System

A **console-based Java application** for managing teaching staff records in a MySQL database.  
This system provides CRUD (Create, Read, Update, Delete) operations with a clean **menu-driven interface**.

---

## 🌟 Features

| Feature | Description |
|---------|-------------|
| ➕ **Add Staff** | Add a new teaching staff with Name, Email, Phone, City, State, Department, and Salary. |
| 🔍 **Retrieve Staff** | Retrieve individual staff by ID or list all staff in a table format. |
| ✏️ **Update Staff** | Update staff's Email, Phone, or Salary. |
| 🗑️ **Delete Staff** | Remove staff by ID safely. |
| 🎯 **User-friendly Console** | Simple menu with input validation and clear messages. |
| 🔗 **Database Connectivity** | JDBC connection to MySQL for persistent data storage. |
| 🆔 **Auto ID Retrieval** | Automatically displays generated Staff ID after adding new staff. |

---

## 🛠 Technologies Used

- ☕ **Java (JDK 8+)**
- 🐬 **MySQL**
- 🔌 **JDBC (Java Database Connectivity)**
- 💻 **Console-based UI**

---

## 🗄 Database Setup

### 1️⃣ Create Database

```sql
CREATE DATABASE test;
USE test;
```

### 2️⃣ Create Table

```sql
CREATE TABLE techstaff (
    tid INT PRIMARY KEY AUTO_INCREMENT,
    tsName VARCHAR(100) NOT NULL,
    tsEmail VARCHAR(100) NOT NULL,
    tsPhone BIGINT NOT NULL,
    tsCity VARCHAR(50),
    tsState VARCHAR(50),
    tsDept VARCHAR(50),
    tsSalary INT
);
```

**📊 Table Structure:**

| Column | Data Type | Constraints | Description |
|--------|-----------|-------------|-------------|
| `tid` | INT | PRIMARY KEY, AUTO_INCREMENT | Unique staff identifier |
| `tsName` | VARCHAR(100) | NOT NULL | Staff member's full name |
| `tsEmail` | VARCHAR(100) | NOT NULL | Email address |
| `tsPhone` | BIGINT | NOT NULL | Contact phone number |
| `tsCity` | VARCHAR(50) | - | City of residence |
| `tsState` | VARCHAR(50) | - | State of residence |
| `tsDept` | VARCHAR(50) | - | Department name |
| `tsSalary` | INT | - | Monthly/Annual salary |

---

## 📁 Project Structure

```
📦 Teaching-Staff-Management
├── 📄 Main.java              # Entry point with menu system
├── 📄 DbConnection.java      # Database connection handler
├── 📄 AddStaff.java          # Add new staff functionality
├── 📄 RetrieveStaff.java     # View staff records
├── 📄 UpdateStaff.java       # Update staff information
└── 📄 RemoveStaff.java       # Delete staff records
```

---

## 💻 How The System Works

### 🏠 **Main.java** - Application Entry Point

Displays a continuous menu loop where users select operations (1-5). Uses switch-case to call respective class methods based on user choice.

**Menu Options:**
```
=================== Teaching Staff Manage ===================
1 : Add Staff
2 : Retrieve Staff
3 : Update Staff
4 : Delete Staff
5 : Exit
```

**Exit Function:**
```java
private static void exit() {
    System.out.println("Bye bye 👋👋");
    System.exit(0);
}
```

---

### 🔌 **DbConnection.java** - Database Connection

Provides a centralized method to get MySQL connection. All other classes call `DbConnection.getConnection()` to connect to the database.

**Connection Details:**
- 🌐 URL: `jdbc:mysql://localhost:3306/test`
- 👤 User: `root`
- 🔑 Password: `` (empty)

```java
Connection con = DbConnection.getConnection();
```

---

### ➕ **AddStaff.java** - Add New Staff

**How it works:**
1. Gets database connection
2. Takes user input for 7 fields (Name, Email, Phone, City, State, Department, Salary)
3. Uses PreparedStatement with INSERT query
4. Executes the insert operation
5. Retrieves and displays the auto-generated Staff ID by calling `RetrieveStaff.indId()`

**Key Feature - Buffer Clearing:**
```java
scan.nextLine(); // Clears buffer before reading strings
long sPhone = scan.nextLong();
scan.nextLine(); // Clears buffer after reading long
```

**Sample Output:**
```
Enter Name :: John Doe
Enter Email :: john@example.com
Enter Phone no. :: 9876543210
Enter City Name :: Mumbai
Enter State Name :: Maharashtra
Enter Department :: Computer Science
Enter Salary :: 50000

John Doe Added Successfully!
John Doe's Staff id :: 1
```

---

### 🔍 **RetrieveStaff.java** - View Staff Records

**How it works:**
Offers two retrieval options:
1. **Individual Staff** - Search by Staff ID
2. **All Staff** - Display all records in formatted table

**Sub-Menu:**
```
1: Individual staff
2: All Staff
Enter you choice :: 
```

#### 👤 **Individual Staff (`indStaff` method):**
- Takes Staff ID as input
- Executes SELECT query with WHERE clause
- Displays all fields if record found

**Sample Output:**
```
Enter Staff id :: 1

ID : 1
Name : John Doe
Email : john@example.com
Phone : 9876543210
City : Mumbai
State : Maharashtra
Dept : Computer Science
Salary : 50000
```

#### 📊 **All Staff (`allStaff` method):**
- Executes SELECT * query
- Uses `printf` for formatted table output
- Displays all records in tabular format

**Sample Output:**
```
--- All TechStaff ---
ID              Name                 Email                     Phone        City            State           Dept       Salary    
----------------------------------------------------------------------------------------------------------------------------
1               John Doe             john@example.com          9876543210   Mumbai          Maharashtra     CS         50000     
2               Jane Smith           jane@example.com          9123456789   Delhi           Delhi           Math       55000     
```

#### 🆔 **Helper Method (`indId`):**
Used by AddStaff to retrieve the auto-generated ID after insertion. Searches by Name, Email, and Phone combination.

```java
RetrieveStaff.indId(addCon, sName, sEmail, sPhone);
```

---

### ✏️ **UpdateStaff.java** - Update Staff Information

**How it works:**
1. Takes Staff ID as input
2. Shows update options menu (Email/Phone/Salary)
3. User selects which field to update
4. Takes new value as input
5. Executes UPDATE query with dynamic field name

**Update Menu:**
```
Enter Staff ID: 1

1: Update Email
2: Update Phone
3: Update Salary
Choose field to update: 
```

**Dynamic Query Construction:**
```java
String updQuery = "UPDATE techstaff SET " + field + "=? WHERE tid=?";
```

The field variable changes based on user choice:
- Choice 1 → `field = "tsEmail"`
- Choice 2 → `field = "tsPhone"`
- Choice 3 → `field = "tsSalary"`

**Type-Safe Parameter Setting:**
```java
if (field.equals("tsEmail")) {
    updPs.setString(1, newValue);
} else if (field.equals("tsPhone")) {
    updPs.setLong(1, Long.parseLong(newValue));
} else {
    updPs.setInt(1, Integer.parseInt(newValue));
}
```

**Sample Output:**
```
Enter Staff ID: 1
1: Update Email
2: Update Phone
3: Update Salary
Choose field to update: 3
Enter new value: 60000

Staff updated successfully ✅
```

---

### 🗑️ **RemoveStaff.java** - Delete Staff

**How it works:**
1. Takes Staff ID as input
2. Executes DELETE query with WHERE clause
3. Displays success/failure message based on affected rows

**DELETE Query:**
```java
String rmQuery = "DELETE FROM techstaff where tid=?";
```

**Sample Output:**
```
Enter Staff id :: 1

1 Deleted Successfully!
```

If Staff ID doesn't exist:
```
Enter Staff id :: 999

999 Not Deleted!
```

---

## 🎯 Key Technical Features

### 🛡️ **Security - PreparedStatement Usage**

All classes use PreparedStatement instead of regular Statement to prevent SQL injection:

```java
// Used in all operations
String query = "INSERT INTO techstaff (...) VALUES (?, ?, ?, ?, ?, ?, ?)";
PreparedStatement prep = con.prepareStatement(query);
prep.setString(1, value);
```

### 🔄 **Scanner Buffer Management**

Proper buffer clearing to avoid input reading issues:

```java
scan.nextLine(); // Clear buffer
long phone = scan.nextLong();
scan.nextLine(); // Clear buffer after nextLong()
```

### 📊 **Formatted Output**

Uses `printf` for aligned table display in RetrieveStaff:

```java
System.out.printf("%-15s %-20s %-25s %-12s %-15s %-15s %-10s %-10s\n",
    "ID", "Name", "Email", "Phone", "City", "State", "Dept", "Salary");
```

### ⚠️ **Exception Handling**

Every method wrapped in try-catch to handle SQL exceptions gracefully:

```java
try {
    // Database operations
} catch (Exception e) {
    System.out.println("Error :: " + e.getMessage());
}
```

---

## 🚀 How to Run

### 📥 Prerequisites
1. Install Java JDK 8+
2. Install MySQL Server
3. Download MySQL Connector/J JAR file

### ⚙️ Setup Steps

1. **Create Database and Table** (Run SQL commands above)

2. **Update Database Credentials** in `DbConnection.java`:
```java
String user = "your_username";
String pswd = "your_password";
```

3. **Compile:**
```bash
javac -cp .:mysql-connector-java-x.x.x.jar *.java
```

4. **Run:**
```bash
java -cp .:mysql-connector-java-x.x.x.jar Main
```

---

## 📸 Complete Workflow Example

```
=================== Teaching Staff Manage ===================
1 : Add Staff
2 : Retrieve Staff
3 : Update Staff
4 : Delete Staff
5 : Exit

Please enter your choice :: 1

Enter Name :: Alice Johnson
Enter Email :: alice.j@university.edu
Enter Phone no. :: 9988776655
Enter City Name :: Bangalore
Enter State Name :: Karnataka
Enter Department :: Physics
Enter Salary :: 65000

Alice Johnson Added Successfully!
Alice Johnson's Staff id :: 3

=================== Teaching Staff Manage ===================
1 : Add Staff
2 : Retrieve Staff
3 : Update Staff
4 : Delete Staff
5 : Exit

Please enter your choice :: 2

1: Individual staff
2: All Staff
Enter you choice :: 2

--- All TechStaff ---
ID              Name                 Email                        Phone        City            State           Dept       Salary    
------------------------------------------------------------------------------------------------------------------------------------
1               John Doe             john@example.com             9876543210   Mumbai          Maharashtra     CS         50000     
2               Jane Smith           jane@example.com             9123456789   Delhi           Delhi           Math       55000     
3               Alice Johnson        alice.j@university.edu       9988776655   Bangalore       Karnataka       Physics    65000     

=================== Teaching Staff Manage ===================
1 : Add Staff
2 : Retrieve Staff
3 : Update Staff
4 : Delete Staff
5 : Exit

Please enter your choice :: 3

Enter Staff ID: 3
1: Update Email
2: Update Phone
3: Update Salary
Choose field to update: 3
Enter new value: 70000

Staff updated successfully ✅

=================== Teaching Staff Manage ===================
1 : Add Staff
2 : Retrieve Staff
3 : Update Staff
4 : Delete Staff
5 : Exit

Please enter your choice :: 5

Bye bye 👋👋
```

---

## 🎓 Learning Outcomes

This project demonstrates:
- ☕ Java fundamentals (Scanner, Switch, Static methods)
- 🗄️ SQL operations (INSERT, SELECT, UPDATE, DELETE)
- 🔌 JDBC connectivity and PreparedStatement
- 🛡️ Basic security practices (SQL injection prevention)
- 💻 Console-based user interface design
- 🔄 Exception handling and error management

---
