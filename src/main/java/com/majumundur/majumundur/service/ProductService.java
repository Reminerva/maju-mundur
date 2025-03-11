package com.majumundur.majumundur.service;

import java.util.List;

import com.majumundur.majumundur.entity.Product;
import com.majumundur.majumundur.model.request.NewProductRequest;
import com.majumundur.majumundur.model.request.SearchProductRequest;

public interface ProductService {
    Product create(NewProductRequest ProductRequest);
    Product getById(String id);
    List<Product> getAll(SearchProductRequest request);
    Product update(Product Product);
    void delete(String id);
}
