package com.majumundur.majumundur.controller;

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
import com.majumundur.majumundur.entity.AppUser;
import com.majumundur.majumundur.entity.Merchant;
import com.majumundur.majumundur.model.request.NewMerchantRequest;
import com.majumundur.majumundur.model.response.CommonResponse;
import com.majumundur.majumundur.service.AppUserService;
import com.majumundur.majumundur.service.MerchantService;
import com.majumundur.majumundur.util.DateUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiBash.MERCHANT)
public class MerchantController {

    private final MerchantService merchantService;
    private final AppUserService appUserService;

    @PostMapping("/{user_id}")
    public ResponseEntity<CommonResponse<Merchant>> createMerchant(
        @RequestBody NewMerchantRequest newMerchantRequest,
        @PathVariable String user_id
    ){
        
            AppUser appUser = appUserService.getById(user_id);

            NewMerchantRequest merchant = NewMerchantRequest.builder()
                                                            .merchantName(newMerchantRequest.getMerchantName())
                                                            .location(newMerchantRequest.getLocation())
                                                            .phone(newMerchantRequest.getPhone())
                                                            .user(appUser)
                                                            .build();

            Merchant newMerchant = merchantService.create(merchant);
            CommonResponse<Merchant> response = CommonResponse.<Merchant>builder()
                                                                    .message(ApiBash.CREATE_MERCHANT_SUCCESS)
                                                                    .data(newMerchant)
                                                                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(ApiBash.GET_BY_ID)
    public ResponseEntity<CommonResponse<Merchant>> getMerchantById(@PathVariable String id){

        Merchant merchant = merchantService.getById(id);

        CommonResponse<Merchant> response = CommonResponse.<Merchant>builder()
                                                                    .message(ApiBash.FOUND_MERCHANT_SUCCESS)
                                                                    .data(merchant)
                                                                    .build();

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Merchant>> updateMerchant(@RequestBody Merchant merchant){

        Merchant updatedMerchant = merchantService.update(merchant);

        CommonResponse<Merchant> response = CommonResponse.<Merchant>builder()
                                                                    .message(ApiBash.UPDATE_MERCHANT_SUCCESS)
                                                                    .data(updatedMerchant)
                                                                    .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(ApiBash.GET_BY_ID)
    public ResponseEntity<CommonResponse<Merchant>> deleteMerchant(@PathVariable String id){
        merchantService.delete(id);

        CommonResponse<Merchant> response = CommonResponse.<Merchant>builder()
                                                                    .message(ApiBash.DELETE_MERCHANT_SUCCESS)
                                                                    .data(null)
                                                                    .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
