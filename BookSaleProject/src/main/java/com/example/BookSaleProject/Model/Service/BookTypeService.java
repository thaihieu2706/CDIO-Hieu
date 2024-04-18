package com.example.BookSaleProject.Model.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookSaleProject.Model.Entity.BookType;
import com.example.BookSaleProject.Model.Repository.BookTypeRepository;

@Service
public class BookTypeService implements IBookTypeService {

    ArrayList<BookType> bookTypeList = new ArrayList<>();

    @Autowired
    BookTypeRepository bookTypeRepository = new BookTypeRepository();

    @Override
    public ArrayList<BookType> getAll() {
        this.bookTypeList = bookTypeRepository.getAll();
        if (!(bookTypeList.isEmpty())) {
            return bookTypeList;
        }
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public BookType getByID(int id) {
        getAll();
        for(BookType bookType:bookTypeList){
            if(bookType.getId()==id)
                return bookTypeRepository.getByID(id);
        }
        throw new UnsupportedOperationException("Unimplemented method 'getByID'");
    }

    @Override
    public boolean addNew(BookType bookType) {
        if (bookTypeRepository.addNew(bookType)) {
            return true;
        }
        throw new UnsupportedOperationException("Unimplemented method 'addNew'");
    }

    @Override
    public boolean update(BookType bookType) {
        if (bookTypeRepository.update(bookType)) {
            return true;
        }
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
