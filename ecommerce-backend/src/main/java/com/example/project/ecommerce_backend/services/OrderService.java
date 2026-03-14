package com.example.project.ecommerce_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.ecommerce_backend.entity.Order;
import com.example.project.ecommerce_backend.entity.Product;
import com.example.project.ecommerce_backend.entity.User;
import com.example.project.ecommerce_backend.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
    private OrderRepository orderRepository;

    public Order createOrder(User user, List<Product> products) {
        double totalAmount = products.stream().mapToDouble(Product::getPrice).sum();

        Order order = new Order();
        order.setUser(user);
        order.setProducts(products);
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
