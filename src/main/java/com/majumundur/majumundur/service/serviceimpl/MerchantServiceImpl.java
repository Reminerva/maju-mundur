package com.majumundur.majumundur.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.majumundur.majumundur.constant.DbBash;
import com.majumundur.majumundur.entity.Merchant;
import com.majumundur.majumundur.entity.Product;
import com.majumundur.majumundur.model.request.NewMerchantRequest;
import com.majumundur.majumundur.repository.MerchantRepository;
import com.majumundur.majumundur.service.MerchantService;
import com.majumundur.majumundur.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService{

    private final MerchantRepository merchantRepository;

    @Override
    public Merchant create(NewMerchantRequest merchantRequest) {

        Merchant merchant = Merchant.builder()
            .merchantName(merchantRequest.getMerchantName())
            .location(merchantRequest.getLocation())
            .phone(merchantRequest.getPhone())
            .user(merchantRequest.getUser())
            .product(new ArrayList<>())
            .build();

        return merchantRepository.saveAndFlush(merchant);
    }

    @Override
    public Merchant getById(String id) {
        Optional<Merchant> merchant = merchantRepository.findById(id);
        if (merchant.isEmpty()) throw new RuntimeException(DbBash.CUSTOMER_NOT_FOUND);
        return merchant.get();
    }

    @Override
    public Merchant update(Merchant merchant) {
        getById(merchant.getId());
        return merchantRepository.saveAndFlush(merchant);
    }

    @Override
    public void delete(String id) {
        Merchant merchant = getById(id);
        merchantRepository.delete(merchant);
    }
}
