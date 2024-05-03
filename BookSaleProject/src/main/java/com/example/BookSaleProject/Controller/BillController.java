package com.example.BookSaleProject.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BookSaleProject.Model.Entity.Bill;
import com.example.BookSaleProject.Model.Entity.BillProBox;
import com.example.BookSaleProject.Model.Entity.CartProBox;
import com.example.BookSaleProject.Model.Entity.History;
import com.example.BookSaleProject.Model.Entity.User;
import com.example.BookSaleProject.Model.Service.BillProBoxService;
import com.example.BookSaleProject.Model.Service.BillService;
import com.example.BookSaleProject.Model.Service.BookService;
import com.example.BookSaleProject.Model.Service.BookTypeService;
import com.example.BookSaleProject.Model.Service.CartProBoxService;
import com.example.BookSaleProject.Model.Service.HistoryService;
import com.example.BookSaleProject.Model.Service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = { "/bill" })
public class BillController {

    @Autowired
    BillService billService = new BillService();
    BillProBoxService billProBoxService = new BillProBoxService();
    UserService userService = new UserService();
    BookService bookService = new BookService();
    CartProBoxService cartProBoxService = new CartProBoxService();
    BookTypeService bookTypeService = new BookTypeService();
    HistoryService historyService = new HistoryService();

    
    ArrayList<BillProBox> billProBoxs = new ArrayList<>();
    HashMap<BillProBox, Double> biHashMap = new HashMap<>();
    History history;
    Bill bill;
    User user;
    double total = 0;

    @GetMapping(value = "/viewPayment")
    public String viewPayment(Model model, @RequestParam(value = "selectedIds") String ids,
            HttpServletRequest request) {

        String idCartPro[] = ids.split(",");
        System.out.println(idCartPro.toString());
        HttpSession session = request.getSession();
        user = userService.getUserByEmail(session.getAttribute("userEmail").toString());
        bill = new Bill(0, user, LocalDateTime.now().withNano(0), "Chưa thanh toán");
        billService.addNew(bill);
        bill = billService.getAll().get(billService.getAll().size() - 1);
        for (String id : idCartPro) {
            CartProBox cartProBox = cartProBoxService.getById(Integer.parseInt(id));
            BillProBox billProBox = new BillProBox(0, bill, cartProBox.getBook(), cartProBox.getSL());
            // history = new History(0, bill, "");
            billProBoxs.add(billProBox);
            billProBoxService.addNew(billProBox);
            cartProBoxService.delete(Integer.parseInt(id));
            // historyService.addNew(history);
        }
        for (BillProBox billProBox : billProBoxs) {
            total += billProBox.getSL() * billProBox.getBook().getPrice();
            biHashMap.put(billProBox, billProBox.getSL() * billProBox.getBook().getPrice());
        }
        model.addAttribute("total", total);
        model.addAttribute("bill", bill);
        model.addAttribute("bookTypeList", bookTypeService.getAll());
        model.addAttribute("billProBoxs", biHashMap);
        return "Payment";
    }

    @GetMapping(value = "/showOrder")
    public String showOrder(Model model) {
        if(payment()){
                
        }
        bill.setStatus("Đã thanh toán");
        billService.update(bill);
        model.addAttribute("total", total);
        model.addAttribute("user", bill.getUser());
        model.addAttribute("bill", bill);
        model.addAttribute("bookTypeList", bookTypeService.getAll());
        model.addAttribute("billProBoxs", biHashMap);
        return "OrderDetail";
    }

    public boolean payment() {
        Stripe.apiKey = "sk_test_51PCLEW04f7xtY1EEz7iu04F269Zl82jzK2aYA8kK4Lpnf8v82XhWKpPHxo6gdq7xOxqZVf754m0yHGuCuSbYCfKO00ji33h87L";
        int value = (int) total;
        try {
            Map<String, Object> customerParameter = new HashMap<String, Object>();
            customerParameter.put("email", user.getEmail());
            customerParameter.put("name", user.getUsername());
            Customer newCustomer = Customer.create(customerParameter);
            String customerId = newCustomer.getId();
            Map<String, Object> retrieveParams = new HashMap<>();
            List<String> expandList = new ArrayList<>();
            expandList.add("sources");
            retrieveParams.put("expand", expandList);
            Customer customer = Customer.retrieve(
                    customerId,
                    retrieveParams,
                    null);
            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("source", "tok_visa");
            @SuppressWarnings("unused")
            Card card = (Card) customer.getSources().create(cardParams);
            Map<String, Object> chargeParam = new HashMap<String, Object>();
            chargeParam.put("amount", value);
            chargeParam.put("currency", "usd");
            chargeParam.put("customer", customer.getId());
            @SuppressWarnings("unused")
            Charge charge = Charge.create(chargeParam);

        } catch (StripeException ex) {

        }
        return true;
    }
}
