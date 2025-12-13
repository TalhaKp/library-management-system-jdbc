package com.library.dao;

import com.library.model.Book;

import java.util.List;

public interface IBookDAO {

    void add(Book book);

    List<Book> getAll(int limit, int offset);

    Book getById(int id);

    void update(Book book);

    void delete(int id);
}
