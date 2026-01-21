# API Rate Limiter 
A backend service implementing a rate limiter to control API usage and ensure system stability under high request volumes. It uses the Token Bucket algorithm to enforce request limits and manage traffic flow. The system supports data persistence using a relational database to store denied requests for analysis.

---
## Tech Stack
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white) 
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4479A1?style=for-the-badge&logo=postgresql&logoColor=white)

---
## Key Features
- Token Bucket algorithm for controlling API request rates
- Enforces fixed API request limits per time window
- Persistent storage of denied requests using PostgreSQL
- Clean and maintainable backend design 
---

## How to Run 
Clone the repository:  
```bash
git clone https://github.com/mmed3091/rate-limiter.git
```
Navigate to the project directory
```
cd rate-limiter
```
Build and run the application
```
mvn spring-boot:run
```

---
## Running Tests

Run the following command in the project directory
```
mvn test
```
---

## Future Improvements

- Allow multiple concurrent requests per user
- Configurable limits per user / endpoint
- Replace in-memory HashMap with cache-based storage (Redis)
- Support burst requests per user
- Distributed behaviour across multiple servers
  
---

## Skills demonstrated
- Implemented backend logic using data structures and algorithms  
- Applied system design principles in a project environment  
- Built a scalable, efficient, and maintainable system 
- Developed REST APIs and integrated with a relational database
- Employed unit testing, debugging and clean code principles




