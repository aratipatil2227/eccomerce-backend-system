package com.example.project.ecommerce_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.ecommerce_backend.entity.Product;
import com.example.project.ecommerce_backend.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	 @Autowired
	    private ProductService productService;

	    @PostMapping("/add")
	    public Product addProduct(@RequestBody Product product) {
	        return productService.addProduct(product);
	    }

	    @GetMapping("/all")
	    public List<Product> getAllProducts() {
	        return productService.getAllProducts();
	    }

	    @GetMapping("/{id}")
	    public Optional<Product> getProduct(@PathVariable Long id) {
	        return productService.getProductById(id);
	    }

	    @DeleteMapping("/{id}")
	    public String deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return "Product deleted successfully!";
	    }
}
