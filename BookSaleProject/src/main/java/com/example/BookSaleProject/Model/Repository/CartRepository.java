package com.example.BookSaleProject.Model.Repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import com.example.BookSaleProject.Model.Entity.Cart;

@Repository
public class CartRepository {
    ArrayList<Cart> cartList = new ArrayList<>();
}
