package com.library.model;
import com.library.util.ValidationUtil;

public class Book {
    private int id;
    private String title, author, isbn; //isbn is international standart book number
    private boolean available;


    public Book() {
        this.id = 0;
        this.title = null;
        this.author = null;
        this.isbn = null;
        this.available = false;
    }

    public Book(int id, String title, String author, String isbn, boolean available) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setAvailable(available);
    }

    public Book(String title, String author, String isbn, boolean available) {
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setAvailable(available);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID can't be lower than zero.");
        }

    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (!title.isBlank()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title can't be empty!");
        }
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        if (author.length() >= 3 && !author.isBlank()) {
            this.author = author;
        } else if (author.isBlank()) {
            throw new IllegalArgumentException("Author name can't be blank.");
        } else {
            throw new IllegalArgumentException("Author name can't consist of less than 3 letters.");
        }
    }

    public String getIsbn() {
        return this.isbn;

    }

    public void setIsbn(String nfIsbn) {
        if (nfIsbn == null || nfIsbn.isBlank()) {
            throw new IllegalArgumentException("ISBN can't be blank.");
        }
        String fIsbn = nfIsbn.replaceAll("-", "");
        if (ValidationUtil.ISBN_REGEX_PATTERN.matcher(fIsbn).matches()) {
            this.isbn = fIsbn;
        } else {
            throw new IllegalArgumentException("ERROR: Invalid ISBN format. " + fIsbn);
        }
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
