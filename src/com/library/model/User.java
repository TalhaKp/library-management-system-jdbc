package com.library.model;
import com.library.util.ValidationUtil;

public class User {
    private int id;
    private String name, surname, mail;


    public User() {
        this.id = 0;
        this.name = null;
        this.surname = null;
        this.mail = null;
    }

    public User(int id, String name, String surname, String mail) {
        setId(id);
        setName(name);
        setSurname(surname);
        setMail(mail);
    }

    public User(String name, String surname, String mail) {
        setName(name);
        setSurname(surname);
        setMail(mail);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (!name.isBlank() && name.length() > 2) {
            if (ValidationUtil.VALID_NAME_SURNAME.matcher(name).matches()) {
                this.name = name;
            } else {
                throw new IllegalArgumentException("ERROR: Name must start with letter and consist of letters");
            }
        } else if (name.length() <= 2) {
            throw new IllegalArgumentException("ERROR: Name can't consist of less than two letters or with two letters.");
        } else if (name.isBlank()) {
            throw new IllegalArgumentException("ERROR: Name can't be blank. ");
        }
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        if (!surname.isBlank() && surname.length() > 1) {
            if (ValidationUtil.VALID_NAME_SURNAME.matcher(surname).matches()) {
                this.surname = surname;
            } else {
                throw new IllegalArgumentException("ERROR: Surname must start with letter and consist of letters");
            }
        } else if (surname.length() <= 1) {
            throw new IllegalArgumentException("ERROR: Surname can't consist of less than two letters or with two letters.");
        } else if (surname.isBlank()) {
            throw new IllegalArgumentException("ERROR: Surname can't be blank. ");
        }
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        if (mail == null || mail.isBlank()) {
            throw new IllegalArgumentException("ERROR: Mail can't be null. ");
        }
        if (ValidationUtil.VALID_MAIL.matcher(mail).matches()) {
            this.mail = mail;
        } else {
            throw new IllegalArgumentException("ERROR: Use valid mail adress.");
        }
    }
}
