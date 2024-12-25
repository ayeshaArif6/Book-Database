# BookDatabase
A book database that a user can acess and alter using a java-based application.



## Project Overview
This project allows a user to insert information regarding any number of books in an SQL database. SQL queries can be made which add and change the entered information through a java based UI-application. The user can interact with the database through this interface and can extend it as needed. This project uses the MySQL Command Line Client to accept the user queries along with the workbench driver. Once the database is created, the user is able to enter queries to add, update, or delete books and authors in the database. It can also list specific books from an author in the database and list all reviews regarding a book as well. The idea is similar to a libraray database but is not on as big a scale. 



## Development Stack
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white) ![JDK](https://img.shields.io/badge/jdk-%23007396.svg?style=for-the-badge&logo=java&logoColor=white)



## Database setup
Follow the steps below to set up the database:
1. Open MySQL Command Line Client.
2. Create Database:
     ```bash
      CREATE DATABASE deliverable4;
      USE deliverable4;
    ```
3. Create Table Statements:
     ```bash
      CREATE TABLE AUTHOR(
      AuthorID INT NOT NULL,
      FirstName VARCHAR(20) NOT NULL,
      LastName VARCHAR(20) NOT NULL,
      DOBINT,
      PRIMARY KEY (AuthorID)
      );
    ```
    ```bash
      CREATE TABLE BOOK(
      BookID INT NOT NULL,
      Title VARCHAR(100)NOT NULL,
      Genre VARCHAR(20) NOT NULL,
      PublYear INT NOT NULL,
      AuthorID INT NOT NULL,
      PRIMARY KEY (BookID),
      FOREIGN KEY (AuthorID) REFERENCES AUTHOR (AuthorID)
      ON UPDATE CASCADE ON delete CASCADE
      );
    ```
    ```bash
      CREATE TABLE USER(
      UserID INT NOT NULL,
      FirstName VARCHAR(20) NOT NULL,
      LastName VARCHAR(20) NOT NULL,
      Email VARCHAR(40),
      Phone INT(10),
      PRIMARY KEY (UserID)
      );
    ```
    ```bash
      CREATE TABLE REVIEW (
      ReviewID INT NOT NULL,
      Rating INT NOT NULL,
      Review_Date DATE ,
      ReviewText VARCHAR(255),
      BookID INT NOT NULL,
      UserID INT NOT NULL,
      PRIMARY KEY (ReviewID),
      FOREIGN KEY (BookID) REFERENCES BOOK (BookID),
      FOREIGN KEY (UserID) REFERENCES USER (UserID)
      ON DELETE CASCADE ON UPDATE CASCADE
      );
    ```
    ```bash
      CREATE TABLE BUYS (
      UserID INT NOT NULL,
      BookID INT NOT NULL,
      PRIMARY KEY (UserID, BookID),
      FOREIGN KEY (UserID) REFERENCES USER (UserID),
      FOREIGN KEY (BookID) REFERENCES BOOK (BookID)
      ON DELETE CASCADE
      );
    ```
4. Create Data Insert Statements:
    ```bash
       INSERT INTO BOOK
       VALUES (1001, 'One Hundred Years of Solitude', 'Novel', 1967, 2001),
              (1002, 'Shogun', 'Novel', 1975, 2002),
              (1003, 'Murder on the Orient Express', 'Mystery', 1934, 2003),
              (1004, 'The Three Musketeers', 'Historical Fiction', 1844, 2004),
              (1005, 'The Da Vinci Code', 'Thriller', 2003, 2005);
    ```
    ```bash
      INSERT INTO USER
      VALUES (3001, 'John', 'Doe', 'JohnDoe@email.com', NULL),
             (3002, 'Lance', 'Orion', 'LanceOrion@email.com', NULL),
             (3003, 'Darcy', 'Vega', 'DarcyVega@email.com', NULL),
             (3004, 'Gabriel', 'Nox', 'GabrielNox@email.com', NULL),
             (3005, 'Tory', 'Smith', 'TorySmith@email.com', NULL)
    ```
    ```bash
       INSERT INTO BUYS
       VALUES (3001, 1001),
              (3002, 1002),
              (3003, 1003),
              (3004, 1004),
              (3005, 1005)
    ```
    ```bash
      INSERT INTO REVIEW
      VALUES (4567, 5, '2024-02-10', 'Excellent book!' , 1005, 3005),
             (7543, 3, '2024-01-15', 'Average book.' , 1002, 3002),
             (9876, 4 , '2024-01-2' , 'Thrilling and nail-biting read.' , 1003, 3003),
             (6543, 5, '2023-10-24' , 'Loved reading this book!' , 1004, 3004),
             (0982, 4, '2023-06-30' , 'Deep and thought-provoking!' , 1001, 3001)
    ```
    ```bash
      INSERT INTO AUTHOR
      VALUES (2001, 'Gabriel', 'Marquez', 1927),
             (2002, 'James', 'Clavel', 1921),
             (2003, 'Agatha', 'Christie', 1890),
             (2004, 'Alexandre', 'Dumas', 1802),
             (2005, 'Dan', 'Brown', 1962);
    ```


 
## Project Quickstart:
1. Open the command prompt in the folder of the source code once the repo has been cloned.
2. Compile the code by running:
    ```bash
      javac gui.java
    ```
3. Once the classes are generated, run the following in the prompt:
    ```bash
       java-cp
       ".;D:\Downloads\mysql-connector-j-8.3.0\mysql-connector-j-8.3.0\mysql-connecto
       r-j-8.3.0.jar" gui "jdbc:mysql://localhost:3306/deliverable4" "root" "password"
       "com.mysql.cj.jdbc.Driver"

       (Replace the
       “D:\Downloads\mysql-connector-j-8.3.0\mysql-connector-j-8.3.0\mysql-connector-j-8.3.0.j
       ar” with the path to your actual MySQL JDBC driver jar file. HOWEVER, ensure you
       include the ‘.;’ before the path).
    ```
4. Replace "root" and "password" with your own username and password.



## Example Queries:
1.   ```bash
        Insert Author:
        2006 ‘J.K’ ‘Rowling’ 1965
      ```
2.    ```bash
        Update Book:
        1005 ‘Red Dragon’
      ```
3.    ```bash
        Delete Author:
        2007
      ```
4.    ```bash
        List Books from Specific Author:
        Dumas
      ```
5.    ```bash
        List Reviews From Specific Book:
        One Hundred Years of Solitude
      ```



## Software Requirements
Ensure:
1. Java version 18.0.1.1 or newer is used.
2. JDK 8 or newer is installed.
3. MySQL 5.7 or newer is used. 
