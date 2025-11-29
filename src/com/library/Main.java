package com.library;

import com.library.dao.BookDAOImpl;
import com.library.dao.IBookDAO;
import com.library.model.Book;

public class Main {

    public static void main(String[] args) {


        IBookDAO bookDAO = new BookDAOImpl();

        System.out.println("--- Library Management System Starting ---");

        try {

            Book newBook = new Book(
                    "Clean Code",
                    "Robert C. Martin",
                    "978-0132350884",
                    true
            );


            System.out.println("Adding new book to database...");
            bookDAO.add(newBook);


            System.out.println("OPERATION SUCCESSFUL!");
            System.out.println("Created Book ID: " + newBook.getId());
            System.out.println("Created Book ISBN (Cleaned): " + newBook.getIsbn());

        } catch (Exception e) {
            System.err.println("CRITICAL ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}