package com.majumundur.majumundur.constant;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PAID("Paid"),
    UNPAID("Unpaid");

    private final String description;

    PaymentStatus (String description){
        this.description = description;
    }

    public static PaymentStatus findByDescription(String description){
        for (PaymentStatus role : values()){
            if (role.description.equalsIgnoreCase(description)){
                return role;
            }
        }
        return null;
    }
}
