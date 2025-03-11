package com.majumundur.majumundur.spesification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.majumundur.majumundur.entity.Product;
import com.majumundur.majumundur.model.request.SearchProductRequest;

import jakarta.persistence.criteria.Predicate;

public class ProductSpesification {
    public static Specification<Product> getSpecification(SearchProductRequest request){
            return (root, cq, cb) -> {
                List<Predicate> predicates = new ArrayList<>();

                if (request.getProductName() != null){
                    Predicate namePredicate = cb.like(cb.lower(root.get("productName")), "%" + request.getProductName().toLowerCase() + "%");
                    predicates.add(namePredicate);
                }

                if (request.getPrice() != null){
                    Predicate pricePredicate = cb.equal(root.get("price"), request.getPrice());
                    predicates.add(pricePredicate);
                }
    
                if (request.getPriceDate() != null){
                    LocalDate tempDate = LocalDate.parse(request.getPriceDate());
                    Predicate datePredicate = cb.equal(root.get("priceDate"), tempDate);
                    predicates.add(datePredicate);
                }    

                assert cq != null;
                return cq.where(predicates.toArray(new Predicate[]{})).getRestriction();
            };
        }
}
