
# Library Management System

## Overview

This is a Library Management System application that helps manage books, users, and Loan records. It provides functionality for adding, updating, and deleting books, managing member information, and tracking which books are borrowed by which members.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)

## Features

- Manage books (add, update, delete).
- Manage library members.
- Track borrowed books and return dates.
- Simple and intuitive user interface.

## Technologies

- **Backend:** [Java](https://www.java.com/), [Spring Boot](https://spring.io/projects/spring-boot)
- **Database:** [Postgres]
- **Build Tool:** [Maven](https://maven.apache.org/)
- **Testing:** [JUnit](https://junit.org/junit5/)

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/masri2002/Library-Managment-System.git
   cd Library-Managment-System
   ```

2. **Install dependencies:**
   Make sure you have [Maven](https://maven.apache.org/install.html) installed. Run the following command to install all required dependencies:
   ```bash
   mvn clean install
   ```

3. **Set up the database:**
   - Ensure MySQL is installed and running on your system.
   - Create a new database for the project:
     ```sql
     CREATE DATABASE your_db_name;
     ```
   - Update the database connection details in `src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

   The application should now be running at `http://localhost:8080`.

## Configuration

- The main configuration files are located in `src/main/resources/application.properties`.
- Modify these files to configure database connection, server port, etc.

## Usage

Once the application is running, you can use it to:

- Add new books and manage existing ones.
- Register and manage library members.
- Track book borrowing and returns.

### Accessing the Application

- The application can be accessed via your browser at `http://localhost:8080`.
- Use the provided APIs to interact with the system programmatically.

## Running Tests

To run tests, use the following command:

```bash
mvn test
```

This command will execute all the tests defined in the project. Make sure you have the test database set up if your tests interact with the database.

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch with your feature or bug fix:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Make your changes and commit them:
   ```bash
   git commit -m "Add your commit message"
   ```
4. Push your changes to the branch:
   ```bash
   git push origin feature/your-feature-name
   ```
5. Create a Pull Request on GitHub.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
