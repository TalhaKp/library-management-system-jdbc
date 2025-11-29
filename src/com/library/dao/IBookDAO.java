package com.library.dao;

import com.library.model.Book;

import java.util.List;

public interface IBookDAO {

    void add(Book book);

    void update(Book book);

    void delete(int id);

    Book getById(int id);

    List<Book> getAll();
}
