package com.majumundur.majumundur.constant;

public class ApiBash {

    public static final String AUTH = "/api/auth";
    public static final String CUSTOMER = "/api/customers";
    public static final String MERCHANT = "/api/merchants";
    public static final String PRODUCT = "/api/products";
    public static final String TRANSACTION = "/api/transactions";

    public static final String MERCHANT_SIGNUP = "/merchant/signup";
    public static final String CUSTOMER_SIGNUP = "/customer/signup";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String GET_BY_ID = "/{id}";
    public static final String APPROVE = "/{adminId}/approve";

    public static final String SIGNUP_SUCCESS = "Sign Up Success!";
    
    public static final String CREATE_CUSTOMER_SUCCESS = "Customer created successfully!";
    public static final String FOUND_CUSTOMER_SUCCESS = "Customer found successfully!";
    public static final String DELETE_CUSTOMER_SUCCESS = "Customer deleted successfully!";
    public static final String UPDATE_CUSTOMER_SUCCESS = "Customer updated successfully!";
    public static final String GET_ALL_CUSTOMER_SUCCESS = "Get all customer success!";
    
    public static final String CREATE_MERCHANT_SUCCESS = "Merchant created successfully!";
    public static final String FOUND_MERCHANT_SUCCESS = "Merchant found successfully!";
    public static final String DELETE_MERCHANT_SUCCESS = "Merchant deleted successfully!";
    public static final String UPDATE_MERCHANT_SUCCESS = "Merchant updated successfully!";
    public static final String GET_ALL_MERCHANT_SUCCESS = "Get all merchant success!";

    public static final String CREATE_PRODUCT_SUCCESS = "Product created successfully!";
    public static final String FOUND_PRODUCT_SUCCESS = "Product found successfully!";
    public static final String DELETE_PRODUCT_SUCCESS = "Product deleted successfully!";
    public static final String UPDATE_PRODUCT_SUCCESS = "Product updated successfully!";
    public static final String GET_ALL_PRODUCT_SUCCESS = "Get all product success!";
    
    public static final String CREATE_TRANSACTION_SUCCESS = "Transaction created successfully!";
    public static final String FOUND_TRANSACTION_SUCCESS = "Transaction found successfully!";
    public static final String DELETE_TRANSACTION_SUCCESS = "Transaction deleted successfully!";
    public static final String UPDATE_TRANSACTION_SUCCESS = "Transaction updated successfully!";
    public static final String GET_ALL_TRANSACTION_SUCCESS = "Get all transaction success!";

    public static final String LOGIN_SUCCESS = "Login success!";
    public static final String LOGOUT_SUCCESS = "Logout success!";

    public static final String HAS_ROLE_MERCHANT = "hasRole('MERCHANT')";
    public static final String HAS_ROLE_CUSTOMER = "hasRole('CUSTOMER')";
    
    private ApiBash() {
                throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
            }
}

// package com.majumundur.majumundur.constant;

// /**
//  * Class untuk menyimpan konstanta yang digunakan dalam API, termasuk endpoint dan pesan response.
//  */
// public class ApiBash {

//     /*** Endpoint Authentication ***/
//     public static final String AUTH = "/api/auth";
//     public static final String LOGIN = "/login";
//     public static final String LOGOUT = "/logout";

//     /*** Endpoint Customer ***/
//     public static final String CUSTOMER = "/api/customers";
//     public static final String CUSTOMER_SIGNUP = "/customer/signup";
//     public static final String GET_CUSTOMER_BY_ID = "/{id}";

//     /*** Endpoint Merchant ***/
//     public static final String MERCHANT = "/api/merchants";
//     public static final String MERCHANT_SIGNUP = "/merchant/signup";

//     /*** Endpoint Product ***/
//     public static final String PRODUCT = "/api/products";

//     /*** Endpoint Transaction ***/
//     public static final String TRANSACTION = "/api/transactions";

//     /*** Endpoint Approval ***/
//     public static final String APPROVE = "/{adminId}/approve";

//     /*** Pesan Sukses ***/
//     public static final String SIGNUP_SUCCESS = "Sign Up Success!";
//     public static final String LOGIN_SUCCESS = "Login success!";
//     public static final String LOGOUT_SUCCESS = "Logout success!";

//     public static final String CREATE_CUSTOMER_SUCCESS = "Customer created successfully!";
//     public static final String FOUND_CUSTOMER_SUCCESS = "Customer found successfully!";
//     public static final String UPDATE_CUSTOMER_SUCCESS = "Customer updated successfully!";
//     public static final String DELETE_CUSTOMER_SUCCESS = "Customer deleted successfully!";
//     public static final String GET_ALL_CUSTOMER_SUCCESS = "Get all customers successfully!";

//     public static final String CREATE_MERCHANT_SUCCESS = "Merchant created successfully!";
//     public static final String FOUND_MERCHANT_SUCCESS = "Merchant found successfully!";
//     public static final String UPDATE_MERCHANT_SUCCESS = "Merchant updated successfully!";
//     public static final String DELETE_MERCHANT_SUCCESS = "Merchant deleted successfully!";
//     public static final String GET_ALL_MERCHANT_SUCCESS = "Get all merchants successfully!";

//     public static final String CREATE_PRODUCT_SUCCESS = "Product created successfully!";
//     public static final String FOUND_PRODUCT_SUCCESS = "Product found successfully!";
//     public static final String UPDATE_PRODUCT_SUCCESS = "Product updated successfully!";
//     public static final String DELETE_PRODUCT_SUCCESS = "Product deleted successfully!";
//     public static final String GET_ALL_PRODUCT_SUCCESS = "Get all products successfully!";

//     public static final String CREATE_TRANSACTION_SUCCESS = "Transaction created successfully!";
//     public static final String FOUND_TRANSACTION_SUCCESS = "Transaction found successfully!";
//     public static final String UPDATE_TRANSACTION_SUCCESS = "Transaction updated successfully!";
//     public static final String DELETE_TRANSACTION_SUCCESS = "Transaction deleted successfully!";
//     public static final String GET_ALL_TRANSACTION_SUCCESS = "Get all transactions successfully!";

//     /*** Konstanta Role untuk Spring Security ***/
//     public static final String HAS_ROLE_MERCHANT = "hasRole('MERCHANT')";
//     public static final String HAS_ROLE_CUSTOMER = "hasRole('CUSTOMER')";

//     /** 
//      * Private constructor untuk mencegah instansiasi kelas ini.
//      */
//     private ApiBash() {
//         throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
//     }
// }
