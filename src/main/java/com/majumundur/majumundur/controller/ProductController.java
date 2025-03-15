package com.majumundur.majumundur.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.majumundur.majumundur.constant.ApiBash;
import com.majumundur.majumundur.entity.Product;
import com.majumundur.majumundur.model.request.NewProductRequest;
import com.majumundur.majumundur.model.request.SearchProductRequest;
import com.majumundur.majumundur.model.response.CommonResponse;
import com.majumundur.majumundur.service.ProductService;
import com.majumundur.majumundur.util.DateUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Product API", description =  "API untuk mengelola product")
@RequestMapping(ApiBash.PRODUCT)
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    public ResponseEntity<CommonResponse<Product>> createProduct(@RequestBody NewProductRequest newProductRequest){

            NewProductRequest product = NewProductRequest.builder()
                                                            .productName(newProductRequest.getProductName())
                                                            .price(newProductRequest.getPrice())
                                                            .isPriceActive(newProductRequest.getIsPriceActive())
                                                            .priceDate(DateUtil.parseDate(newProductRequest.getPriceDate().toString()))
                                                            .merchant(newProductRequest.getMerchant())
                                                            .stock(newProductRequest.getStock())
                                                            .build();

            Product newProduct = productService.create(product);
            CommonResponse<Product> response = CommonResponse.<Product>builder()
                                                                    .message(ApiBash.CREATE_PRODUCT_SUCCESS)
                                                                    .data(newProduct)
                                                                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Product>>> getAllProduct(
            @RequestParam(name = "productName", required=false) String productName,
            @RequestParam(name = "price", required=false) Integer price,
            @RequestParam(name = "priceDate", required=false) String priceDate,
            @RequestParam(name = "isPriceActive", required=false) String isPriceActive
        ) {
            SearchProductRequest request = SearchProductRequest.builder()
                                                            .productName(productName)
                                                            .price(price)
                                                            .isPriceActive(Boolean.parseBoolean(isPriceActive))
                                                            .priceDate(priceDate)
                                                            .build();

            List<Product> products = productService.getAll(request); 
            
            CommonResponse<List<Product>> response = CommonResponse.<List<Product>>builder()
                                                            .message(ApiBash.GET_ALL_CUSTOMER_SUCCESS)
                                                            .data(products)
                                                            .build();
        
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping(ApiBash.GET_BY_ID)
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    public ResponseEntity<CommonResponse<Product>> getProductById(@PathVariable String id){

        Product product = productService.getById(id);

        CommonResponse<Product> response = CommonResponse.<Product>builder()
                                                                    .message(ApiBash.FOUND_PRODUCT_SUCCESS)
                                                                    .data(product)
                                                                    .build();

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    public ResponseEntity<CommonResponse<Product>> updateProduct(@RequestBody Product product){

        Product updatedProduct = productService.update(product);

        CommonResponse<Product> response = CommonResponse.<Product>builder()
                                                                    .message(ApiBash.UPDATE_PRODUCT_SUCCESS)
                                                                    .data(updatedProduct)
                                                                    .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(ApiBash.GET_BY_ID)
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    public ResponseEntity<CommonResponse<Product>> deleteProduct(@PathVariable String id){
        productService.delete(id);

        CommonResponse<Product> response = CommonResponse.<Product>builder()
                                                                    .message(ApiBash.DELETE_PRODUCT_SUCCESS)
                                                                    .data(null)
                                                                    .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
