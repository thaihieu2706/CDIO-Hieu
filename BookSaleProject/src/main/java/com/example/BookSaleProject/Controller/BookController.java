package com.example.BookSaleProject.Controller;

import java.util.ArrayList;

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
import com.example.BookSaleProject.Model.Service.BookService;


@Controller
@RequestMapping(value = {"/book",""})
public class BookController {
   
    @Autowired
    BookService bookService = new BookService();

    @RequestMapping(value = "/")   
    public String viewHomePage(Model model){
        return "index";
    }
    
    @RequestMapping(value = "/getAll")
        public String viewAllBook(Model model) {
            ArrayList<Book> bookListAll = bookService.getAll();
            int numPage=bookListAll.size();
            if(numPage%12!=0)
            {
                numPage=numPage/12+1;
            }
            else
            {
                numPage=numPage/12;
            }
            model.addAttribute("NumOfPage", numPage);
            return getAllBook(model, "1");
    }

    @GetMapping(value = {"/getAllBook/{pageNum}"})
    public String getAllBook(Model model, @PathVariable(name = "pageNum")String currentPage){
        ArrayList<Book> bookListPage = bookService.getAllByPage(Integer.parseInt(currentPage));
        model.addAttribute("Previous", Integer.parseInt(currentPage)-1);
        model.addAttribute("Next", Integer.parseInt(currentPage)+1);
        model.addAttribute("bookList", bookListPage);
        return "getAllBookForCus";
    }
    
    @GetMapping(value = {"/getBookById"})
    public String getBookById(Model model, @RequestParam(name = "id")String id){
        Book book = bookService.getByID(Integer.parseInt(id));
        model.addAttribute("book", book);
        return "productDetail";
    }

    @PostMapping(value = {"/updateBook"})
    public String updateBook(Model model, @ModelAttribute("bookFinded")Book book){
        bookService.update(book);
        
        return "";
    }
}
