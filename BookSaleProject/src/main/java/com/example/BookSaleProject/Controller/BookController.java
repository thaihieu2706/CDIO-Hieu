package com.example.BookSaleProject.Controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

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
import com.example.BookSaleProject.Model.Entity.User;
import com.example.BookSaleProject.Model.Service.BookService;
import com.example.BookSaleProject.Model.Service.BookTypeService;
import com.example.BookSaleProject.Model.Service.RateService;


@Controller
@RequestMapping(value = { "/book", ""})
public class BookController {

    @Autowired
    private BookService bookService = new BookService();
    private BookTypeService bookTypeService = new BookTypeService();
    private RateService rateService = new RateService();
    
    static User user1 = new User();
    private HashMap<Book, Double> bookRate = new HashMap<Book, Double>();
    private ArrayList<Book> bookList = new ArrayList<>();
    private ArrayList<Book> bookListAll = bookService.getAll();
    private ArrayList<BookType> bookTypeList = bookTypeService.getAll();
    private String title;

    @GetMapping(value = "/index")   
    public String index(Model model){
        bookRate.clear();
        bookListAll.sort(Comparator.comparing(Book::getDate).reversed());
        ArrayList<Book> newBookList  = new ArrayList<>();
        for (Book book : bookListAll) {
            newBookList .add(book);
            if (newBookList .size()==3) {
                break;
            }
        }
        for (Book book : newBookList) {
            bookRate.put(book, rateService.getScoreByIdBook(book));
        }
        
        HashMap<Book, Double> bookRateList = new HashMap<Book, Double>();
        
        for (Book book : bookListAll) {
            bookRateList.put(book, rateService.getScoreByIdBook(book));
        }

        HashMap<Book, Double> topRatedBooks = new HashMap<Book, Double>();
        while (topRatedBooks.size()<3 && !bookRateList.isEmpty()) {
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
        model.addAttribute("user", user1);
        model.addAttribute("bookTypeList", bookTypeList);
        model.addAttribute("newBookList", bookRate);
        model.addAttribute("favouriteBookList", topRatedBooks);
        return "index";
    }

    @GetMapping(value = "/")
    public String viewHomePage(Model model,User user){
        user1 = user;
        return index(model);
    }

    @GetMapping(value = { "/getBookList/{pageNum}"})
    public String getBookList(Model model, @PathVariable(name = "pageNum") String currentPage) {
        int numPages = (int) Math.ceil((double) bookList.size() / 12);
        int[] numPage = new int[numPages];
        for (int i = 0; i < numPages; i++) {
            numPage[i] = i + 1;
        }
        ArrayList<Book> bookListPage = new ArrayList<>();
        for(int i=(Integer.parseInt(currentPage)-1)*12;i<Integer.parseInt(currentPage)*12;i++){
            if(bookList.size()<=i )
                break;
            bookListPage.add(bookList.get(i));
        }
        bookRate.clear();
        for (Book book : bookListPage) {
            bookRate.put(book, rateService.getScoreByIdBook(book));
        }
        model.addAttribute("user", user1);
        model.addAttribute("bookTypeList", bookTypeList);
        model.addAttribute("BookRate", bookRate);
        model.addAttribute("NumOfPage", numPage);
        model.addAttribute("title", title);
        model.addAttribute("currentPage", Integer.parseInt(currentPage));
        model.addAttribute("Previous", Integer.parseInt(currentPage) - 1);
        model.addAttribute("Next", Integer.parseInt(currentPage) + 1);
        return "GetBookListForCus";
    }

    @GetMapping(value = { "/getBookById" })
    public String getBookById(Model model, @RequestParam(name = "id") String id) {
        Book book = bookService.getByID(Integer.parseInt(id));
        BookType bookType = book.getBookType();
        ArrayList<Book> bookListSame = new ArrayList<>();
        for (Book book1 : bookListAll) {
            if (book1.getBookType().getId() == bookType.getId() && book1.getId() != book.getId()) {
                bookListSame.add(book1);
            }
            if (bookListSame.size() == 3) {
                break;
            }
        }
        bookRate.clear();
        for (Book book2 : bookListSame) {
            bookRate.put(book2, rateService.getScoreByIdBook(book2));
        }
        model.addAttribute("user", user1);
        model.addAttribute("bookTypeList", bookTypeList);
        model.addAttribute("rate", rateService.getScoreByIdBook(book));
        model.addAttribute("BookRate", bookRate);
        model.addAttribute("booktype", bookType);
        model.addAttribute("book", book);
        return "BookDetail";
    }

    @GetMapping(value = "/getNewestBook")
    public String getNewestBook(Model Model){
        bookList.clear();
        title = "SÁCH MỚI PHÁT HÀNH";
        for (Book book : bookListAll) {
            if(Integer.parseInt(book.getDate())>=2010)
                bookList.add(book);
        }
        return getBookList(Model,"1");
    }

    @GetMapping(value = "/getFavouriteBook")
    public String getFavouriteBook(Model Model){
        bookRate.clear();
        title = "SÁCH ĐƯỢC YÊU THÍCH";
        for (Book book : bookListAll) {
            bookRate.put(book, rateService.getScoreByIdBook(book));
        }
        bookList.clear();
        for (Map.Entry<Book, Double> entry : bookRate.entrySet()) {
            if (entry.getValue() >= 4.0) {
                bookList.add(entry.getKey());
            }
        }
        return getBookList(Model,"1");
    }

    @GetMapping(value = "/getBookByType/{id}")
    public String getBookByType(Model Model,@PathVariable(value = "id")String id){
        bookList.clear();
        title = bookTypeService.getByID(Integer.parseInt(id)).getName();
        for (Book book : bookListAll) {
            if (Integer.parseInt(id)==book.getBookType().getId()) {
                bookList.add(book);
            }
        }
        return getBookList(Model,"1");
    }
    @PostMapping(value = { "/updateBook" })
    public String updateBook(Model model, @ModelAttribute("bookFinded") Book book) {
        bookService.update(book);
        return "";
    }

}
