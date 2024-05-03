package com.example.BookSaleProject.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BookSaleProject.Model.Entity.Bill;
import com.example.BookSaleProject.Model.Entity.BillProBox;
import com.example.BookSaleProject.Model.Entity.CartProBox;
import com.example.BookSaleProject.Model.Entity.User;
import com.example.BookSaleProject.Model.Service.BillProBoxService;
import com.example.BookSaleProject.Model.Service.BillService;
import com.example.BookSaleProject.Model.Service.BookService;
import com.example.BookSaleProject.Model.Service.BookTypeService;
import com.example.BookSaleProject.Model.Service.CartProBoxService;
import com.example.BookSaleProject.Model.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {"/bill"})
public class BillController {

    @Autowired
    BillService billService = new BillService();
    BillProBoxService billProBoxService = new BillProBoxService();
    UserService userService = new UserService();
    BookService bookService = new BookService();
    CartProBoxService cartProBoxService = new CartProBoxService();
    BookTypeService bookTypeService = new BookTypeService();

    ArrayList<BillProBox> billProBoxs = new ArrayList<>();
    HashMap<BillProBox,Double> biHashMap = new HashMap<>();
    Bill bill;
    User user;
    double total;
    
    @GetMapping(value = "/viewPayment")
    public String viewPayment(Model model, @RequestParam(value = "selectedIds")String ids, HttpServletRequest request){
        
        String idCartPro[] = ids.split(",");
        System.out.println(idCartPro.toString());
        HttpSession session = request.getSession();
        user =  userService.getUserByEmail(session.getAttribute("userEmail").toString());
        bill = new Bill(0, user, LocalDateTime.now().withNano(0), "chưa thanh toán");
        // billService.addNew(bill);
        for (String id : idCartPro) {
            CartProBox cartProBox = cartProBoxService.getById(Integer.parseInt(id));
            BillProBox billProBox = new BillProBox(0, bill, cartProBox);
            billProBoxs.add(billProBox);
            // billProBoxService.addNew(billProBox);
            // cartProBoxService.delete(Integer.parseInt(id));
        }
        total = 0;
        for (BillProBox billProBox : billProBoxs) {
            total += billProBox.getCartProBox().getSL()*billProBox.getCartProBox().getBook().getPrice();
            biHashMap.put(billProBox, billProBox.getCartProBox().getSL()*billProBox.getCartProBox().getBook().getPrice());
        }
        model.addAttribute("total", total);
        model.addAttribute("bill", bill);
        model.addAttribute("bookTypeList", bookTypeService.getAll());
        model.addAttribute("billProBoxs", biHashMap);
        return "Payment";
    }

    @GetMapping(value = "/showOrder")
    public String showOrder(Model model,HttpServletRequest request){
        
        model.addAttribute("bookTypeList", bookTypeService.getAll());
        return "OrderDetail";
    }
}
