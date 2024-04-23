package com.example.BookSaleProject.Model.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BookSaleProject.Model.Entity.Book;
import com.example.BookSaleProject.Model.Repository.RateRepository;

public class RateService implements IRateService {

    @Autowired
    RateRepository rateRepository = new RateRepository();

    @Override
    public double getScoreByIdBook(Book book) {
        if(rateRepository.getScoreByIdBook(book)!=0)
            return rateRepository.getScoreByIdBook(book);
        else 
            return 0;
    }

}
