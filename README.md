
# Finance Management System

## Overview
The Finance Management System is a core Java application designed to manage financial transactions. It allows users to add, view, and persist transactions. The system uses file-based storage for simplicity and demonstrates basic concepts of Java concurrency and file I/O operations.

## Features
- **Add Transaction:** Add new financial transactions with amount, category, and description.
- **View Transactions:** Display all stored transactions.
- **Data Persistence:** Transactions are stored and retrieved from a text file.

## Project Structure
- `src/main/java/com/finance/model/Transaction.java` - Defines the Transaction model.
- `src/main/java/com/finance/util/DataStorage.java` - Handles data persistence using file I/O.
- `src/main/java/com/finance/service/FinanceService.java` - Manages transactions and interacts with DataStorage.
- `src/main/java/com/finance/Main.java` - Entry point of the application.

## Prerequisites
- JDK 8 or higher

## Installation
1. Clone the repository:
   ```sh
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```sh
   cd finance-management-system
   ```
3. Compile the project:
   ```sh
   javac -d bin src/main/java/com/finance/*.java
   ```
4. Run the application:
   ```sh
   java -cp bin com.finance.Main
   ```

## Usage
- **Adding a Transaction:**
  - Create a `Transaction` object with the desired amount, category, and description.
  - Use `FinanceService.addTransaction(transaction)` to add the transaction.

- **Viewing Transactions:**
  - Use `FinanceService.viewTransactions()` to print all transactions to the console.

## Example
```java
public class Main {
    public static void main(String[] args) {
        DataStorage dataStorage = new DataStorage();
        FinanceService financeService = new FinanceService(dataStorage);

        // Adding a transaction
        Transaction transaction = new Transaction(100.00, "Food", "Lunch at cafe");
        financeService.addTransaction(transaction);

        // Viewing transactions
        financeService.viewTransactions();
    }
}
```

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact
For questions or feedback, please contact:
- **Name:** Prajwal Kuchewar
- **Email:** [prajwalkuchewar3@gmail.com](mailto:prajwalkuchewar3@gmail.com)


### **Software Requirements Specification (SRS)**

#### **1. Introduction**

**1.1 Purpose**
The purpose of the Finance Management System is to provide a simple application to manage financial transactions by allowing users to add, view, and store transaction records. The system demonstrates core Java concepts including file I/O and concurrency.

**1.2 Scope**
This system will be implemented as a core Java application. It will include functionalities for:
- Adding new transactions.
- Viewing all transactions.
- Persisting transaction data to a file.

**1.3 Definitions, Acronyms, and Abbreviations**
- **Transaction:** A financial record including amount, category, and description.
- **DataStorage:** A class responsible for file-based persistence of transactions.
- **FinanceService:** A class that provides methods for adding and viewing transactions.

#### **2. Functional Requirements**

**2.1 Add Transaction**
- **Description:** Users should be able to add a new transaction to the system.
- **Input:** Transaction details including amount, category, and description.
- **Output:** The transaction is stored in the file and is available for viewing.
- **Processing:** The transaction data is written to `transactions.txt`.

**2.2 View Transactions**
- **Description:** Users should be able to view all transactions.
- **Input:** None.
- **Output:** A list of all transactions is printed to the console.
- **Processing:** The application reads from `transactions.txt` and displays the data.

#### **3. Non-Functional Requirements**

**3.1 Performance**
- The application should handle a reasonable number of transactions efficiently.

**3.2 Reliability**
- The application should handle file I/O errors gracefully and ensure data consistency.

**3.3 Usability**
- The application should be easy to use with a clear console output for viewing transactions.

**3.4 Maintainability**
- The code should be well-documented and modular to facilitate future enhancements.

#### **4. System Design**

**4.1 Architecture**
- **Model:** `Transaction` class to represent financial transactions.
- **Storage:** `DataStorage` class for file-based persistence.
- **Service:** `FinanceService` class for business logic.

**4.2 Data Storage**
- Transactions are stored in a text file named `transactions.txt` with each transaction on a new line in the format: `amount,category,description`.

**4.3 User Interface**
- Console-based user interface for adding and viewing transactions.
