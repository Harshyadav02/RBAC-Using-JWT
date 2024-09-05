# JWT-Based Spring Security Application

This project demonstrates how to implement JWT (JSON Web Token) based authentication and authorization using Spring Security. It includes different user roles (Admin and Employee) and public endpoints for accessing secured resources.

## Project Structure

``` sh 
.
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── harsh
    │   │           └── JWTLearnings
    │   │               ├── Controller
    │   │               │   ├── AdminController.java
    │   │               │   ├── EmployeeController.java
    │   │               │   └── PublicController.java
    │   │               ├── Entity
    │   │               │   ├── Admin.java
    │   │               │   └── Employee.java
    │   │               ├── JwtBasedSpringSecurityApplication.java
    │   │               ├── Repository
    │   │               │   ├── AdminRepo.java
    │   │               │   └── EmployeeRepo.java
    │   │               ├── Security
    │   │               │   ├── AuthEntryPointJwt.java
    │   │               │   ├── AuthTokenFilter.java
    │   │               │   ├── JWTUtils.java
    │   │               │   ├── SecurityConfig.java
    │   │               │   └── UserDetailsServiceImp.java
    │   │               └── Service
    │   │                   ├── AdminService.java
    │   │                   └── EmployeeService.java
    │   └── resources
    │       ├── application.yml
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── harsh
                    └── JWTLearnings
                        └── JwtBasedSpringSecurityApplicationTests.java


```


## Key Components

### 1. **Controllers**
- `AdminController.java`: Handles requests related to admin functionalities.
- `EmployeeController.java`: Manages employee-related operations.
- `PublicController.java`: Provides public endpoints that don't require authentication.

### 2. **Entities**
- `Admin.java`: Represents the admin user entity.
- `Employee.java`: Represents the employee user entity.

### 3. **Security**
- `AuthEntryPointJwt.java`: Handles unauthorized access attempts.
- `AuthTokenFilter.java`: Intercepts requests and checks for valid JWT tokens.
- `JWTUtils.java`: Utility class for generating and validating JWT tokens.
- `SecurityConfig.java`: Configures Spring Security and sets up JWT authentication.
- `UserDetailsServiceImp.java`: Implements `UserDetailsService` for loading user-specific data during authentication.

### 4. **Repositories**
- `AdminRepo.java`: Data access layer for admin users.
- `EmployeeRepo.java`: Data access layer for employees.

### 5. **Services**
- `AdminService.java`: Provides admin-specific business logic.
- `EmployeeService.java`: Contains employee-specific business logic.

## Configuration

The application configuration is handled in the `application.yml` file, which contains:
- Database connection details
- Security related data

## Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/Harshyadav02/JWT-Based-Spring-Security.git
   cd JWT-Based-Spring-Security
   ```

2. Update the database configuration in ``src/main/resources/application.yml``
3. Build and run the application using Maven: `` ./mvnw spring-boot:run``
4. The application will start on ``http://localhost:8080``.

## **Endpoints**

The following endpoints are accessible without authentication:

- `/admin/signup/**`: Allows unauthenticated users to sign up as an admin.
- `/admin/login/**`: Allows unauthenticated users to log in as an admin.
- `/emp/login/**`: Allows unauthenticated users to log in as an employee.
- `/emp/signup/**`: Allows unauthenticated users to sign up as an employee.
Rest of the endpoints are secured i.e need **JWT** to access them

## Contribution

Feel free to fork the repository and submit a pull request with your changes. For major updates, please open an issue to discuss before implementing.
