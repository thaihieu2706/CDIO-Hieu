package com.example.BookSaleProject.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BookSaleProject.Model.Entity.Book;
import com.example.BookSaleProject.Model.Entity.BookType;
import com.example.BookSaleProject.Model.Service.BookService;
import com.example.BookSaleProject.Model.Service.RateService;

@Controller
@RequestMapping(value = { "/book", "" })
public class BookController {

    @Autowired
    BookService bookService = new BookService();
    RateService rateService = new RateService();

    HashMap<Book, Double> bookRate = new HashMap<Book, Double>();

    @GetMapping(value = { "/getAllBook/{pageNum}" })
    public String getAllBook(Model model, @PathVariable(name = "pageNum") String currentPage) {
        ArrayList<Book> bookListAll = bookService.getAll();
        int numPages = (int) Math.ceil((double) bookListAll.size() / 12);
        int[] numPage = new int[numPages];
        for (int i = 0; i < numPages; i++) {
            numPage[i] = i + 1;
        }
        ArrayList<Book> bookListPage = bookService.getAllByPage(Integer.parseInt(currentPage));
        for (Book book : bookListPage) {
            bookRate.put(book, rateService.getScoreByIdBook(book));
        }
        model.addAttribute("BookRate", bookRate);
        model.addAttribute("NumOfPage", numPage);
        model.addAttribute("currentPage", Integer.parseInt(currentPage));
        model.addAttribute("Previous", Integer.parseInt(currentPage) - 1);
        model.addAttribute("Next", Integer.parseInt(currentPage) + 1);
        return "getAllBookForCus";
    }

    @GetMapping(value = { "/getBookById" })
    public String getBookById(Model model, @RequestParam(name = "id") String id) {
        Book book = bookService.getByID(Integer.parseInt(id));
        BookType bookType = book.getBookType();
        ArrayList<Book> bookList = bookService.getAll();
        ArrayList<Book> bookListSame = new ArrayList<>();
        for (Book book1 : bookList) {
            if (book1.getBookType().getId() == bookType.getId() && book1.getId() != book.getId()) {
                bookListSame.add(book1);
            }
            if (bookListSame.size() == 3) {
                break;
            }
        }
        model.addAttribute("rate", rateService.getScoreByIdBook(book));
        model.addAttribute("bookList", bookListSame);
        model.addAttribute("booktype", bookType);
        model.addAttribute("book", book);
        return "productDetail";
    }

    @PostMapping(value = { "/updateBook" })
    public String updateBook(Model model, @ModelAttribute("bookFinded") Book book) {
        bookService.update(book);
        return "";
    }
}
