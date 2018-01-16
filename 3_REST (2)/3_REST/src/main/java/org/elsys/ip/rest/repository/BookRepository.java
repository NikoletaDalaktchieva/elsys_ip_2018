package org.elsys.ip.rest.repository;

import org.elsys.ip.rest.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BookRepository {
    private static List<Book> bookList = new ArrayList<>(
            Arrays.asList(
                    new Book(1, "Тhe Fault in our Stars", "romance", "John Green ", "The Fault in our Stars is...",
                        "EGMONT", 9.90 , 288, 2009 , false, "Bulgarian"),
                    new Book(2, "What Happened", "autobiography", "Hillary Rodham Clinton", "Now I'm letting my guard down.",
                            "Simon & Schuster Ltd", 18.58, 512, 2017, true, "English"),
                    new Book(3, "Harry Potter and the Philosopher’s stone", "fiction", "J. K. Rowling", "Harry Potter is a wizard...",
                            "Bloomsbury Publishing PLC", 8.71, 352, 2014, false, "English" ),
                    new Book(4, "Тhe Lightning Thief", "fiction", "Rick Riordan", "Percy Jackson and the Lightning Thief is the first bestselling book in Rick Riordan's phenomenally successful Percy Jackson series.",
                            "EGMONT", 12.90, 352, 2013, false, "Bulgarian")
            ));


    public List<Book> getBookList() {
        return bookList;
    }

    public Optional<Book> getBookById(Integer id) {
        return bookList.stream().filter(book -> book.getId() == id).findFirst();
    }

    public Book saveBook(Book book) {
        bookList.add(book);
        return book;
    }

    public Book updateBook(Integer id, Book book) {
        int realId = id - 1;
        bookList.set(realId, book);
        return book;
    }

    public void deleteBook(Integer id) {
        bookList.removeIf(it -> it.getId() == id);
    }
}
