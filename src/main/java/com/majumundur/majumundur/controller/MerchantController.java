package com.majumundur.majumundur.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.majumundur.majumundur.constant.ApiBash;
import com.majumundur.majumundur.entity.AppUser;
import com.majumundur.majumundur.entity.Merchant;
import com.majumundur.majumundur.model.request.NewMerchantRequest;
import com.majumundur.majumundur.model.response.CommonResponse;
import com.majumundur.majumundur.service.AppUserService;
import com.majumundur.majumundur.service.MerchantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Merchant API", description = "API untuk mengelola merchant")
@RequestMapping(ApiBash.MERCHANT)
public class MerchantController {

    private final MerchantService merchantService;
    private final AppUserService appUserService;

    @Operation(
        summary = "Membuat Merchant Baru",
        description = "Endpoint ini digunakan untuk membuat merchant baru dengan memberikan user_id.",
        responses = {
            @ApiResponse(responseCode = "201", description = ApiBash.CREATE_MERCHANT_SUCCESS, 
                        content = @Content(schema = @Schema(implementation = CommonResponse.class)))
        }
    )
    @PostMapping("/{user_id}")
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    public ResponseEntity<CommonResponse<Merchant>> createMerchant(
        @Parameter(description = "Request body untuk membuat merchant", required = true)
        @RequestBody NewMerchantRequest newMerchantRequest,
        
        @Parameter(description = "ID dari pengguna yang akan menjadi merchant", required = true)
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

    @Operation(
        summary = "Mendapatkan Merchant berdasarkan ID",
        description = "Endpoint ini mengembalikan informasi merchant berdasarkan ID yang diberikan.",
        responses = {
            @ApiResponse(responseCode = "302", description = ApiBash.FOUND_MERCHANT_SUCCESS,
                        content = @Content(schema = @Schema(implementation = CommonResponse.class)))
        }
    )
    @GetMapping(ApiBash.GET_BY_ID)
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    public ResponseEntity<CommonResponse<Merchant>> getMerchantById(
        @Parameter(description = "ID dari merchant yang akan dicari", required = true)
        @PathVariable String id
    ){
        Merchant merchant = merchantService.getById(id);
        CommonResponse<Merchant> response = CommonResponse.<Merchant>builder()
                                                        .message(ApiBash.FOUND_MERCHANT_SUCCESS)
                                                        .data(merchant)
                                                        .build();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @Operation(
        summary = "Memperbarui Merchant",
        description = "Endpoint ini memperbarui informasi merchant berdasarkan data yang diberikan dalam request body.",
        responses = {
            @ApiResponse(responseCode = "200", description = ApiBash.UPDATE_MERCHANT_SUCCESS,
                        content = @Content(schema = @Schema(implementation = CommonResponse.class)))
        }
    )
    @PutMapping
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    public ResponseEntity<CommonResponse<Merchant>> updateMerchant(
        @Parameter(description = "Merchant yang akan diperbarui", required = true)
        @RequestBody Merchant merchant
    ){
        Merchant updatedMerchant = merchantService.update(merchant);
        CommonResponse<Merchant> response = CommonResponse.<Merchant>builder()
                                                        .message(ApiBash.UPDATE_MERCHANT_SUCCESS)
                                                        .data(updatedMerchant)
                                                        .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
        summary = "Menghapus Merchant",
        description = "Endpoint ini menghapus merchant berdasarkan ID yang diberikan.",
        responses = {
            @ApiResponse(responseCode = "200", description = ApiBash.DELETE_MERCHANT_SUCCESS,
                        content = @Content(schema = @Schema(implementation = CommonResponse.class)))
        }
    )
    @DeleteMapping(ApiBash.GET_BY_ID)
    @PreAuthorize(ApiBash.HAS_ROLE_MERCHANT)
    public ResponseEntity<CommonResponse<Merchant>> deleteMerchant(
        @Parameter(description = "ID dari merchant yang akan dihapus", required = true)
        @PathVariable String id
    ){
        merchantService.delete(id);
        CommonResponse<Merchant> response = CommonResponse.<Merchant>builder()
                                                        .message(ApiBash.DELETE_MERCHANT_SUCCESS)
                                                        .data(null)
                                                        .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
