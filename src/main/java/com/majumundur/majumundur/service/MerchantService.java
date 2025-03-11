package com.majumundur.majumundur.service;

import com.majumundur.majumundur.entity.Merchant;
import com.majumundur.majumundur.model.request.NewMerchantRequest;
// import com.majumundur.majumundur.model.request.SearchMerchantRequest;

public interface MerchantService {
    Merchant create(NewMerchantRequest customerRequest);
    Merchant getById(String id);
    // List<Merchant> getAll(SearchMerchantRequest request);
    Merchant update(Merchant customer);
    void delete(String id);
}
