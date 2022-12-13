package org.example;

import java.util.*;



public class Library {
    private Map<String, Book> books;
    public Map<String, Integer> bookCounts;
    public Library() {
        this.books = new HashMap<>();
        this.bookCounts = new HashMap<>();
    }

    public void addBook(Book book) {
        this.books.put(book.description, book);
        this.bookCounts.put(book.description, this.bookCounts.getOrDefault(book.description, 0) + 1);
    }
    public Optional<Book> getBook(String description) {
        if (!this.books.containsKey(description)) {
            return Optional.empty();
        }
        Book book = this.books.get(description);
        int count = this.bookCounts.get(description);


        this.bookCounts.put(description, this.bookCounts.get(description) - 1);
        return Optional.of(book);
    }

    public boolean isBookCheckedOut(String description) {
        return this.bookCounts.getOrDefault(description, 0) == 0;
    }

    public void putBook(Book book) {
        if (this.isBookCheckedOut(book.description)) {
            this.bookCounts.put(book.description, this.bookCounts.get(book.description) + 1);
        }
    }

    public List<String> getCheckedOutBooks() {
        List<String> checkedOutBooks = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : this.bookCounts.entrySet()) {
            if (entry.getValue() == 0) {
                checkedOutBooks.add(entry.getKey());
            }
        }
        return checkedOutBooks;
    }

    public List<String> getAllBooks() {
        return new ArrayList<>(this.books.keySet());
    }
}