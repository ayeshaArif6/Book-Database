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
