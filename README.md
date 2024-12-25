# BookDatabase
A book database that a user can acess and alter using a java-based application.

## Project Overview
This project allows a user to insert information regarding any number of books in an SQL database. SQL queries can be made which add and change the entered information through a java based UI-application. The user can interact with the database through this interface and can extend it as needed. This project uses the MySQL Command Line Client to accept the user queries. 

## Project Quickstart
Follow the steps below to start the project;
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
