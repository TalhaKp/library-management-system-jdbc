package com.library.model;

import java.time.LocalDate;

public class Checkout {
    private int id, bookId, userId;
    private LocalDate issueDate, returnDate, dueDate;
    private static final int DEFAULT_LOAN_PERIOD_DAYS = 14;

    public Checkout() {
        this.id = 0;
        this.bookId = 0;
        this.userId = 0;
        this.issueDate = null;
        this.returnDate = null;
        this.dueDate = null;
    }

    public Checkout(int id, int bookId, int userId, LocalDate issueDate, LocalDate returnDate, LocalDate dueDate) {
        setId(id);
        setBookId(bookId);
        setUserId(userId);
        setIssueDate(issueDate); //here for dao.
        setReturnDate(returnDate);
        setDueDate(dueDate);
    }

    public Checkout(int bookId, int userId) {
        setBookId(bookId);
        setUserId(userId);
        setIssueDate(LocalDate.now());
        setDueDate(LocalDate.now().plusDays(DEFAULT_LOAN_PERIOD_DAYS));
        this.returnDate = null;
    }

    public int getId() {
        return this.id;
    }

    public int getBookId() {
        return this.bookId;
    }

    public int getUserId() {
        return this.userId;
    }

    public LocalDate getIssueDate() {
        return this.issueDate;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }
    }

    public void setBookId(int bid) {
        if (bid > 0) {
            this.bookId = bid;
        } else {
            throw new IllegalArgumentException("Book id must be greater than zero.");
        }
    }

    public void setUserId(int userId) {
        if (userId > 0) {
            this.userId = userId;
        } else {
            throw new IllegalArgumentException("User id must be greater than zero.");
        }
    }

    public void setIssueDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Issue Date can't be null.");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Issue date can't be in the future.");
        }
        this.issueDate = date;
    }

    public void setReturnDate(LocalDate returnDate) {
        if (returnDate == null) {
            this.returnDate = null;
            return;
        }
        if (issueDate == null) {
            throw new IllegalArgumentException("There can't be a return date without issue date!");
        }
        if (returnDate.isBefore(issueDate)) {
            throw new IllegalArgumentException("Return date can't be before than issue date.");
        }
        if (returnDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Return date can't be in future. It must be actual return date!");
        }
        this.returnDate = returnDate;


    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate == null) {
            throw new IllegalArgumentException("Due date can't be null.");
        }
        if (this.issueDate != null && dueDate.isBefore(this.issueDate)) {
            throw new IllegalArgumentException("Due date cannot be before the issue date.");
        }
        this.dueDate = dueDate;
    }
}