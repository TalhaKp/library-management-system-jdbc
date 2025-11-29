package com.library.dao;

import com.library.model.Book;
import com.library.util.DatabaseUtil;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class BookDAOImpl implements IBookDAO {

    private static final String INSERT_SQL = "INSERT INTO books (title, author, isbn, available) VALUES (?,?,?,?)";

    @Override
    public void add(Book book) {
        try (Connection conn = DatabaseUtil.getConnection();

             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getIsbn());
            pstmt.setBoolean(4, book.isAvailable());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        book.setId(newId);
                        System.out.println("Added to the database. New ID: "+ newId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occured while adding a book.",e);
        }


    }
    @Override public void update(Book book) {}
    @Override public void delete(int id) {}
    @Override public Book getById(int id) { return null; }
    @Override public List<Book> getAll() { return Collections.emptyList(); }
}
