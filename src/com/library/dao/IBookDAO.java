package com.library.dao;

import com.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {

    boolean add(Book book);

    List<Book> getAll(int limit, int offset);

    Optional<Book> getById(int id);

    boolean update(Book book);

    boolean delete(int id);
}
