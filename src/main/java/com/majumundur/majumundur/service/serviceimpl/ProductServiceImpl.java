package com.majumundur.majumundur.service.serviceimpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.majumundur.majumundur.constant.DbBash;
import com.majumundur.majumundur.entity.Merchant;
import com.majumundur.majumundur.entity.Product;
import com.majumundur.majumundur.model.request.NewProductRequest;
import com.majumundur.majumundur.model.request.SearchProductRequest;
import com.majumundur.majumundur.repository.MerchantRepository;
import com.majumundur.majumundur.repository.ProductRepository;
import com.majumundur.majumundur.service.MerchantService;
import com.majumundur.majumundur.service.ProductService;
import com.majumundur.majumundur.spesification.ProductSpesification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;
    private final MerchantService merchantService;

    @Override
    public Product create(NewProductRequest productRequest) {
        try {
            Merchant merchant = merchantService.getById(productRequest.getMerchant());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate parsedDate = LocalDate.parse(productRequest.getPriceDate(), formatter);

            Product product = Product.builder()
                .productName(productRequest.getProductName())
                .priceDate(parsedDate)
                .price(productRequest.getPrice())
                .merchant(merchant)
                .isPriceActive(productRequest.getIsPriceActive())
                .stock(productRequest.getStock())
                .build();

            merchantRepository.saveAndFlush(merchant);
            return productRepository.saveAndFlush(product);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
    }

    @Override
    public Product getById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) throw new RuntimeException(DbBash.PRODUCT_NOT_FOUND);
        return product.get();
    }

    @Override
    public List<Product> getAll(SearchProductRequest request) {
        Specification<Product> specification = ProductSpesification.getSpecification(request);
        return productRepository.findAll(specification);
    }

    @Override
    public Product update(Product product) {
        getById(product.getId());
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void delete(String id) {
        Product product = getById(id);
        productRepository.delete(product);
    }
}
