package com.dev.basic.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
    public long productId;
    public String name;
    public String desc;
    public double amount;
    public long stock;
   public MultipartFile file;
    public String filePath;
}
