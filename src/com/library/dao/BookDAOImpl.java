package com.library.dao;

import com.library.model.Book;
import com.library.util.DatabaseUtil;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Collections;
import java.util.List;

public class BookDAOImpl implements IBookDAO {

    private static final String INSERT_SQL = "INSERT INTO books (title, author, isbn, available) VALUES (?,?,?,?)";
    private static final String SELECT_ALL_PAGINATED_SQL = "SELECT * FROM books ORDER BY id ASC LIMIT ? OFFSET ? ";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM books WHERE id = ?";

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
                        System.out.println("Added to the database. New ID: " + newId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occured while adding a book.", e);
        }


    }

    //Helper method in order to DRY
    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setIsbn(rs.getString("isbn"));
        book.setAvailable(rs.getBoolean("available"));

        return book;
    }

    @Override
    public List<Book> getAll(int limit, int offset) {
        List<Book> bookList = new java.util.ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_PAGINATED_SQL)){

            pstmt.setInt(1,limit);
            pstmt.setInt(2,offset);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookList.add(mapResultSetToBook(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }


    @Override
    public Book getById(int id) {
        Book book = null;
        try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt= conn.prepareStatement(SELECT_BY_ID_SQL)) {

            pstmt.setInt(1,id);

            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    book = mapResultSetToBook(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book; //If we can't find the book by id, this return will be null.
    }

    @Override
    public void update(Book book) {
    }

    @Override
    public void delete(int id) {
    }
}
