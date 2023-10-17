package com.projects.Product.service;

import com.projects.Product.dbmodel.Product;
import com.projects.Product.exception.RunTimeBusinessException;
import com.projects.Product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long productId){
        return productRepository.findById(productId).get();
    }

    public Product updateProduct(Product product){
        return productRepository.save(product);
    }
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }
    public List<Product> increaseAllProductsPriceByPercentage(double percentage) {

        if (percentage>50){
            throw new RunTimeBusinessException("You can not raise more than 50 percent!");
        }
        else if (percentage<0){
            throw new RunTimeBusinessException("Increase percentage should be more than zero!");
        }

        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            double currentPrice = product.getPrice();
            double newPrice = currentPrice * (1 + (percentage / 100));
            product.setPrice(newPrice);

            productRepository.save(product);
        }
        return products;
    }
}
