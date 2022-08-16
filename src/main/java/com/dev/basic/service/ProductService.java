package com.dev.basic.service;

import com.dev.basic.dto.ProductDto;
import com.dev.basic.entity.Product;
import com.dev.basic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Value("azure-blob://<your-container-name>/<your-blob-name>")
    private Resource blobFile;
    @Autowired
    MyBlobService myBlobService;

    public ProductDto addProduct (ProductDto productDto, MultipartFile multipartFile) throws Exception {
        Product product = toEntity(productDto);
        product = productRepository.save(product);
        productDto.filePath = myBlobService.storeFile(product.getProductId()+ multipartFile.getOriginalFilename(),multipartFile.getInputStream(), multipartFile.getSize());
        product.setFilePath(productDto.filePath);
        product = productRepository.save(product);
        return toResource(product);
    }

    public Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.productId);
        product.setDescription(productDto.desc);
        product.setAmount(productDto.amount);
        product.setName(productDto.name);
        product.setStock(productDto.stock);
        product.setFilePath(productDto.filePath);
        return product;
    }
    public ProductDto toResource(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.amount = product.getAmount();
        productDto.productId = product.getProductId();
        productDto.desc = product.getDescription();
        productDto.name = product.getName();
        productDto.stock = product.getStock();
        productDto.filePath = product.getFilePath();
        return productDto;
    }
    public List<ProductDto> toResourceList(List<Product> productList) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            productDtoList.add(toResource(productList.get(i)));
        }
        return productDtoList;
    }
    public List<ProductDto> findAll () {
        List<ProductDto> productDto = toResourceList(productRepository.findAll());
        return productDto;
    }
    public ProductDto findProductByName(String productName) {
        Product product = productRepository.findProductByName(productName);
        return toResource(product);
    }
   /* public String  saveFile(MultipartFile multipartFile, long id) throws Exception {
        String destination = "C:\\Users\\cemal\\Pictures/" + id  + multipartFile.getOriginalFilename();
        File file = new File(destination);
        multipartFile.transferTo(file);
        return destination;
    }
      public String writeBlobFile(MultipartFile file) throws IOException {
        try (OutputStream os = ((WritableResource) this.blobFile).getOutputStream()) {
            os.write(file.getBytes());
        }
        return "file was updated";
    }
*/

}
