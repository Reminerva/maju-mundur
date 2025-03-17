package com.majumundur.majumundur.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.majumundur.majumundur.constant.ApiBash;
import com.majumundur.majumundur.entity.Product;
import com.majumundur.majumundur.model.request.NewProductRequest;
import com.majumundur.majumundur.model.request.SearchProductRequest;
import com.majumundur.majumundur.model.response.CommonResponse;
import com.majumundur.majumundur.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Product API", description = "API untuk mengelola produk")
@RequestMapping(ApiBash.PRODUCT)
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    @Operation(
        summary = "Create product", 
        description = "Merchant dapat menambahkan produk baru.",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = ApiBash.CREATE_PRODUCT_SUCCESS
            )
        })
    public ResponseEntity<CommonResponse<Product>> createProduct(
            @RequestBody NewProductRequest newProductRequest) {

        Product newProduct = productService.create(newProductRequest);
        CommonResponse<Product> response = CommonResponse.<Product>builder()
                .message(ApiBash.CREATE_PRODUCT_SUCCESS)
                .data(newProduct)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(
        summary = "Get all product", 
        description = "Mengambil daftar produk dengan opsi filter.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = ApiBash.GET_ALL_PRODUCT_SUCCESS
            )
        })
    public ResponseEntity<CommonResponse<List<Product>>> getAllProduct(
            @RequestParam(name = "productName", required = false) String productName,
            @RequestParam(name = "price", required = false) Integer price,
            @RequestParam(name = "priceDate", required = false) String priceDate,
            @RequestParam(name = "isPriceActive", required = false) String isPriceActive) {

        SearchProductRequest request = SearchProductRequest.builder()
                .productName(productName)
                .price(price)
                .isPriceActive(Boolean.parseBoolean(isPriceActive))
                .priceDate(priceDate)
                .build();

        List<Product> products = productService.getAll(request);
        CommonResponse<List<Product>> response = CommonResponse.<List<Product>>builder()
                .message(ApiBash.GET_ALL_PRODUCT_SUCCESS)
                .data(products)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(ApiBash.GET_BY_ID)
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    @Operation(
        summary = "Get product by ID", 
        description = "Merchant dapat mengambil detail produk berdasarkan ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = ApiBash.FOUND_PRODUCT_SUCCESS
            )
        })
    public ResponseEntity<CommonResponse<Product>> getProductById(
            @Parameter(description = "ID produk", required = true) @PathVariable String id) {

        Product product = productService.getById(id);
        CommonResponse<Product> response = CommonResponse.<Product>builder()
                .message(ApiBash.FOUND_PRODUCT_SUCCESS)
                .data(product)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    @Operation(
        summary = "Update product by ID", 
        description = "Merchant dapat memperbarui informasi produk.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = ApiBash.UPDATE_PRODUCT_SUCCESS
            )
        })
    public ResponseEntity<CommonResponse<Product>> updateProduct(@RequestBody Product product) {

        Product updatedProduct = productService.update(product);
        CommonResponse<Product> response = CommonResponse.<Product>builder()
                .message(ApiBash.UPDATE_PRODUCT_SUCCESS)
                .data(updatedProduct)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(ApiBash.GET_BY_ID)
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    @Operation(
        summary = "Delete product by ID", 
        description = "Merchant dapat menghapus produk berdasarkan ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = ApiBash.DELETE_PRODUCT_SUCCESS
            )
        })
    public ResponseEntity<CommonResponse<Product>> deleteProduct(
            @Parameter(description = "ID produk", required = true) @PathVariable String id) {

        productService.delete(id);
        CommonResponse<Product> response = CommonResponse.<Product>builder()
                .message(ApiBash.DELETE_PRODUCT_SUCCESS)
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
