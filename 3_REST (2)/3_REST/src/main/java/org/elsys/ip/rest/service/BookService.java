package org.elsys.ip.rest.service;

import org.elsys.ip.rest.model.Book;
import org.elsys.ip.rest.repository.BookRepository;

import java.util.List;

public class BookService {
    private BookRepository bookRepository = new BookRepository();

    public List<Book> getBookList() {
        return bookRepository.getBookList();
    }

    public Book getBookById(Integer id) {
        return bookRepository.getBookById(id).orElse(null);
    }

    public Book saveBook(Book book) {
        return bookRepository.saveBook(book);
    }

    public Book updateBook(Integer id, Book book) {
        return bookRepository.updateBook(id, book);
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteBook(id);
    }
}
