# Event Ticketing System Admin CLI

## Project Overview
This Command-Line Interface (CLI) application provides administrative management tools for the Event Ticketing System API. It allows administrators to perform advanced management tasks with the backend system through a terminal interface.

## Technologies Used
* Java 17
* Spring Boot 3.x
* Jackson for JSON Processing

## Prerequisites
* JDK 17
* Maven 3.8+
* Running Event Ticketing System API Backend

## Setup and Installation

### 1. Clone the Repository
```bash
git clone https://github.com/haneeshaseef/EventTicketingSystemCLI.git
cd EventTicketingSystemCLI
```

### 3. Build and Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

## Project Structure
```
src/
├── main/
│   ├── org/coursework/eventticketingsystemadmincli/
│   │   ├── controller/     # CLI command controllers
│   │   ├── model/          # Data models
│   │   ├── service/        # Service layer for API interactions
│   │   └── utils/          # Utility classes
│   └── resources/
│       ├── application.properties
└── test/                   # Unit and integration tests
```

## Troubleshooting
* Verify backend API connectivity
* Ensure correct credentials and permissions

## API Integration
This CLI tool directly communicates with the Event Ticketing System API:
* Base API Endpoint: `http://localhost:8080/api`
* API Documentation: https://haneeshaseef.github.io/EventTicketingSystemAPI/

