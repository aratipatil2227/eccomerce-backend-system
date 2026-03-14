package com.example.project.ecommerce_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.ecommerce_backend.entity.Order;
import com.example.project.ecommerce_backend.entity.Product;
import com.example.project.ecommerce_backend.entity.User;
import com.example.project.ecommerce_backend.services.OrderService;
import com.example.project.ecommerce_backend.services.ProductService;
import com.example.project.ecommerce_backend.services.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping("/create/{email}")
    public Order createOrder(@PathVariable String email, @RequestBody List<Long> productIds) {
        //find user by email
    	Optional<User> userOpt=userService.findByEmail(email);
    	if (userOpt.isEmpty()) {
			throw new RuntimeException("user not found with email:"+ email);
		}
           //fetch product based on ids 
        List<Product> products = productService.getAllProducts().stream()
                .filter(p -> productIds.contains(p.getId())).toList();
         if (products.isEmpty()) {
			throw new RuntimeException("no valid products found for given id");
		}
      //create order
        return orderService.createOrder(userOpt.get(), products);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
