package org.example;

import java.util.List;
import java.util.Objects;

public class Book {
    public String description;
    public List<String> authors;
    public int year;

    public Book(String description, List<String> authors, int year) {
        this.description = description;
        this.authors = authors;
        this.year = year;
    }
    String getTitle() {
        return description;
    }
}