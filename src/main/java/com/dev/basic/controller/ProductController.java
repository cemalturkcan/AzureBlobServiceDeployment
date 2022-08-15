package com.dev.basic.controller;

import com.dev.basic.dto.File;
import com.dev.basic.dto.ProductDto;
import com.dev.basic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/addProduct")
    public ProductDto register(@RequestBody ProductDto productDto) throws Exception {
        return productService.addProduct(productDto);
    }

    @GetMapping("/listProduct")
    public List<ProductDto> list() {
        return productService.findAll();
    }

    @GetMapping("/findProduct/{productName}")
    public ProductDto findProduct(@PathVariable String productName) {
        return productService.findProductByName(productName);
    }



}