package com.dev.basic.controller;

import com.dev.basic.dto.File;
import com.dev.basic.dto.ProductDto;
import com.dev.basic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping(value = "/addProduct", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ProductDto register( @RequestPart("Product") ProductDto productDto , @RequestPart("file") MultipartFile multipartFile) throws Exception {
        return productService.addProduct(productDto, multipartFile);
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