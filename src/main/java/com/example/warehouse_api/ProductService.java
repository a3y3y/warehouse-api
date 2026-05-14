package com.example.warehouse_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductByArticle(Long article) {
        return productRepository.findById(article);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long article, Product productDetails) {
        Optional<Product> optionalProduct = productRepository.findById(article);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(productDetails.getName());
            existingProduct.setAmount(productDetails.getAmount());
            existingProduct.setPrice(productDetails.getPrice());
            return Optional.of(productRepository.save(existingProduct));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteProduct(Long article) {
        if (productRepository.existsById(article)) {
            productRepository.deleteById(article);
            return true;
        } else {
            return false;
        }
    }
}