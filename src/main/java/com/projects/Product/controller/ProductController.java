package com.projects.Product.controller;

import com.projects.Product.dbmodel.Product;
import com.projects.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> gelAllProducts(){
        List<Product> allProducts= productService.getAllProducts();
        return new ResponseEntity<List<Product>>(allProducts,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name="id")Long product_Id){
        Product product= productService.getProductById(product_Id);
        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,@PathVariable(name="id")Long product_Id)
    {
        product.setId(product_Id);
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<Product>(updatedProduct,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long product_id){
        productService.deleteProduct(product_id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @PostMapping ("/increase")
    public ResponseEntity<List<Product>> increasePrice(@RequestParam double percentage){

        List<Product> updatedProducts = productService.increaseAllProductsPriceByPercentage(percentage);
        return new ResponseEntity<List<Product>>(updatedProducts,HttpStatus.OK);

    }
}
