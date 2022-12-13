package org.example;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        Library library = new Library();
// Добавляем несколько книг в библиотеку
        library.addBook(new Book("Алхимик", Arrays.asList("Пауло Коэльо"), 2015));
        library.addBook(new Book("Мы", Arrays.asList("Евгений Замятин"), 2015));
        library.addBook(new Book("Идиот", Arrays.asList("Федор Достоевский"), 2015));
        library.addBook(new Book("Мцыри", Arrays.asList("Михаил Лермонтов"), 2015));

// Читаем команды из консоли
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();
            String[] commandParts = command.split(" ");
            if (commandParts[0].equals("get")) {
                // Команда "get <имя книги>"
                if (commandParts.length < 2) {
                    System.out.println("Неправильно указано имя книги");
                } else {
                    String description = commandParts[1];
                    Optional<Book> book = library.getBook(description);
                    if (book.isPresent()) {
                        System.out.println("Книга " + book.get().description + " взята");
                    } else {
                        System.out.println("Книги с названием " + description + " нет в библиотеке");
                    }
                }
            } else if (commandParts[0].equals("put")) {
                // Команда "put <имя книги>"
                if (commandParts.length < 2) {
                    System.out.println("Неправильно указано имя книги");
                } else {
                    String description = commandParts[1];
                    if (library.isBookCheckedOut(description)) {
                        library.putBook(new Book(description, null, 0));
                        System.out.println("Книга " + description + " возвращена");
                    } else {
                        System.out.println("Вы не можете вернуть книгу, которую не брали");
                    }
                }
            } else if (commandParts[0].equals("list")) {
// Команда "list"
                List<String> checkedOutBookDescriptions = library.getCheckedOutBooks();
                if (checkedOutBookDescriptions.isEmpty()) {
                    System.out.println("У Вас нет взятых книг");
                } else {
                    System.out.println("Взятые Вами книги:");
                    for (String description : checkedOutBookDescriptions) {
                        System.out.println(description);
                    }
                }
            } else if (commandParts[0].equals("all")) {
// Команда "all"
                List<String> bookDescriptions = library.getAllBooks();
                if (bookDescriptions.isEmpty()) {
                    System.out.println("В библиотеке нет книг");
                } else {
                    System.out.println("Все книги в библиотеке:");
                    for (String description : bookDescriptions) {
                        System.out.println(description + " (" + library.bookCounts.get(description) + " экземпляра(ов))");
                    }
                }
            }
        }
    }
}