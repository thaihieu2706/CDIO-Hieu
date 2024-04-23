package com.example.BookSaleProject.Controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BookSaleProject.Model.Entity.Book;
import com.example.BookSaleProject.Model.Service.BookService;
import com.example.BookSaleProject.Model.Service.RateService;

@Controller
@RequestMapping(value = "/")
public class ClientController {
    @Autowired
    BookService bookService = new BookService();
    RateService rateService = new RateService();


    @GetMapping(value = "/")   
    public String viewHomePage(Model model){
        ArrayList<Book> bookList = bookService.getAll();
        bookList.sort(Comparator.comparing(Book::getDate).reversed());
        ArrayList<Book> newBookList  = new ArrayList<>();
        for (Book book : bookList) {
            newBookList .add(book);
            if (newBookList .size()==4) {
                break;
            }
        }

        HashMap<Book, Double> bookRate = new HashMap<Book, Double>();
        for (Book book : newBookList ) {
            bookRate.put(book, rateService.getScoreByIdBook(book));
        }
        
        HashMap<Book, Double> bookRateList = new HashMap<Book, Double>();
        
        for (Book book : bookList) {
            bookRateList.put(book, rateService.getScoreByIdBook(book));
        }

        HashMap<Book, Double> topRatedBooks = new HashMap<Book, Double>();
        while (topRatedBooks.size()<4 && !bookRateList.isEmpty()) {
            Book topBook = null;
            double maxRating = Double.MIN_VALUE;
            for (Map.Entry<Book, Double> entry : bookRateList.entrySet()) {
                if (entry.getValue() > maxRating) {
                    maxRating = entry.getValue();
                    topBook = entry.getKey();
                }
            }
            if (topBook != null) {
                topRatedBooks.put(topBook, maxRating);
                bookRateList.remove(topBook);
            }
        }
        model.addAttribute("newBookList", bookRate);
        model.addAttribute("favouriteBookList", topRatedBooks);
        return "index";
    }

    
}
