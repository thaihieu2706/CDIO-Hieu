package com.example.BookSaleProject.Model.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookSaleProject.Model.Entity.Book;
import com.example.BookSaleProject.Model.Repository.BookRepository;

@Service
public class BookService implements IBookService{
    ArrayList<Book> bookList = new ArrayList<>();
    
    @Autowired
    BookRepository bookRepository = new BookRepository();


    @Override
    public boolean update(Book book) {
        if (bookRepository.update(book)) {
            return true;
        }
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


    @Override
    public Book getByID(int id) {
        bookList.clear();
        getAll();
        for (Book book : bookList) {
            if (book.getId()==id) {
                return bookRepository.getByID(id);
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'getByID'");
    }

    @Override
    public boolean addNew(Book book) {
        if (bookRepository.addNew(book)) {
            return true;
        }
        throw new UnsupportedOperationException("Unimplemented method 'addNew'");
    }

    @Override
    public ArrayList<Book> getAllByPage(int currentPage) {
        this.bookList = bookRepository.getAllByPage(currentPage);
        if (!(bookList.isEmpty())) {
            return bookList;
        }
        return null;
    }


    @Override
    public ArrayList<Book> getAll() {
        bookList.clear();
        this.bookList = bookRepository.getAll();
        if (!(bookList.isEmpty())) {
            return bookList;
        }
        return null;
    }
}
