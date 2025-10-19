# Library_Management
Overview:
This is a Spring Boot REST API designed to manage books, borrowers, and the borrowing process in a small library.
It handles adding books, managing borrowers, borrowing and returning books, applying fines for late returns, and supports filtering, sorting, and pagination as mentioned in the assignment.

Tech Stack  : Java 17,Spring Boot 3.x,Spring Data JPA,H2 Database,Maven,Swagger / OpenAPI

Project Architecture:
Controller    - Handles all API endpoints for Books, Borrowers, and Borrow Records.
Service       -  Contains main business logic and validations.
Repository	  -  Manages database operations using Spring Data JPA and JPQL queries.
ServiceImpl	  -  Implements service interfaces, performs transactional operations.
DTOs	        -  Used for clean data transfer between layers.
Entities	    -  Define database tables with relationships.
Specification	-  Used for dynamic filtering and search.
Config	      -  Contains configuration like Swagger setup

 Entities: 
Book – Title, author, category, copies, and soft-delete flag.
Borrower – Name, email, membership type (BASIC/PREMIUM), borrow limit.
BorrowRecord – Borrow and return details with fine calculation.
FinePolicy – Optional table for category-wise fine-per-day configuration.

Main Features:
1.Book Management
                   Add or update books (POST /books)
                   List books with filters, sorting, and pagination (GET /books)
                   Update book details (PUT /books/{id})
                   Soft delete book if not borrowed (DELETE /books/{id})

2.Borrower Management
                     Register a borrower (POST /borrowers)
                     View borrower’s borrow history (GET /borrowers/{id}/records)
                     View borrowers with overdue books (GET /borrowers/overdue)

3.Borrowing & Returning
                      Borrow a book (POST /borrow)
                      Validates limits, availability, and sets due date (14 days)
                      Return a book (POST /return)
                      Calculates fine for late returns and updates availability
                      View active borrowed records (GET /records/active)

Key Challenges and How I Solved Them  :

1.when multiple users borrow the same book,available copies sometimes became inconsistent.
- I solved this using @Transactional on the borrow or return methods so that all related operations like availability, saving record, updating copies run atomically.

2.Dynamic filtering with pagination and sorting,filter like category and availability with pagination.
- I implemented a BookSpecification class and used JpaSpecificationExecutor in the repository.
This allowed dynamic filters while still handling pagination and sorting through the Pageable object.



Approach Summary:
1.Followed layered architecture with clean separation of concerns.
2.Used @Transactional for safe updates during borrow/return.
3.Implemented pagination and filters at the DB level using JPA Specifications.
4.JPQL queries used for overdue and active record detection.
5.DTOs ensure the APIs are clean, maintainable, and aligned with good design principles.


Note: Lombok annotations (@Getter, @Setter, @AllArgsConstructor, @NoArgsConstructor, @Builder) were intended to generate boilerplate code.
On my laptop, Lombok was not working correctly despite multiple attempts, so I manually included all necessary constructors, getters, setters, and builder methods in the code.








