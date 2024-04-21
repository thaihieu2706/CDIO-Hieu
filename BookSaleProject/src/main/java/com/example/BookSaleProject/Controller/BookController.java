package com.example.BookSaleProject.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BookSaleProject.Model.Entity.Book;
import com.example.BookSaleProject.Model.Service.BookService;


@Controller
@RequestMapping(value = {"/book",""})
public class BookController {
   
    @Autowired
    BookService bookService = new BookService();

    @GetMapping(value = {"/getAllBook"})
    public String getAllBook(Model model){
        ArrayList<Book> bookList = bookService.getAll();
    
        model.addAttribute("bookList", bookList);
        return "getAllBookForCus";
    }
    
    @GetMapping(value = {"/getBookById"})
    public String getBookById(Model model, @RequestParam(name = "id")String id){
        Book book = bookService.getByID(Integer.parseInt(id));
        System.out.println("hello");
        model.addAttribute("book", book);
        return "productDetail";
    }
}
