package com.example.BookSaleProject.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BookSaleProject.Model.Entity.CartProBox;
import com.example.BookSaleProject.Model.Entity.User;
import com.example.BookSaleProject.Model.Service.BookService;
import com.example.BookSaleProject.Model.Service.CartProBoxService;
import com.example.BookSaleProject.Model.Service.CartService;
import com.example.BookSaleProject.Model.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {"/cart"})
public class CartController {
      
    @Autowired
    CartService cartService = new CartService();
    CartProBoxService cartProBoxService = new CartProBoxService();
    UserService userService = new UserService();
    BookService bookService = new BookService();
    
    HashMap<CartProBox, Double> cartBookList = new HashMap<>();
    ArrayList<CartProBox> cartProBoxs = new ArrayList<>();

    @GetMapping(value = "/viewCart")
    public String viewCart(Model model, HttpServletRequest request){
        cartBookList.clear();
        cartProBoxs.clear();
        HttpSession session = request.getSession();
        ArrayList<CartProBox> cartProBoxAll = cartProBoxService.getAll();
        for (CartProBox cartProBox : cartProBoxAll) {
            int userIdFromSession = userService.getUserByEmail(session.getAttribute("userEmail").toString()).getId();
            if (cartProBox.getCart().getUser().getId() == userIdFromSession) {
                int bookId = cartProBox.getBook().getId();
                System.out.println(cartProBox.getId()+": "+cartProBox.getSL()+": "+cartProBox.getBook().getPrice());
                boolean bookExists = false;
                for (CartProBox existingCartProBox : cartProBoxs) {
                    if (existingCartProBox.getBook().getId() == bookId) {
                        // Tăng số lượng lên nếu sách đã tồn tại trong cartProBoxs
                        existingCartProBox.setSL(existingCartProBox.getSL() + cartProBox.getSL());
                        bookExists = true;
                        break;
                    }
                }
                if (!bookExists) {
                    cartProBoxs.add(cartProBox);
                }
            }
        }
        for (CartProBox cartProBox : cartProBoxs) {
            System.out.println(cartProBox.getSL());
            cartBookList.put(cartProBox, cartProBox.getSL()*cartProBox.getBook().getPrice());
        }
        model.addAttribute("cartBookList", cartBookList);
        model.addAttribute("title", "Giỏ hàng");
        return "Cart";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteBook(Model model, HttpServletRequest request, @PathVariable(value = "id")String id){
        for (CartProBox cartProBox : cartProBoxs) {
            if (cartProBox.getId()==Integer.parseInt(id)) {
                cartProBoxService.delete(Integer.parseInt(id));
                break;
            }
        }
        return viewCart(model, request);
    }

    @PostMapping(value = "/add/{id}")
    public String addBook(Model model,@PathVariable(value = "id")String id, HttpServletRequest request,@RequestParam("SL")String SL){
        int idBook = Integer.parseInt(id);
        for (CartProBox cartProBox : cartProBoxs) {
            if (cartProBox.getBook().getId()==idBook) {
                cartProBox.getBook().setId(idBook);
                return "foward:/"+ request.getRequestURI();
            }
        }
        HttpSession session = request.getSession();
        User userSession = userService.getUserByEmail(session.getAttribute("userEmail").toString());
        CartProBox cartProBox = new CartProBox(0,cartService.getByIdUser(userSession),bookService.getByID(idBook),Integer.parseInt(SL));
        cartProBoxs.add(cartProBox);
        return "foward:/"+ request.getRequestURI();
    }
}
