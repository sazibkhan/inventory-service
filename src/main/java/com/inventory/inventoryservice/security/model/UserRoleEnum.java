package com.inventory.inventoryservice.security.model;

public enum UserRoleEnum{




  ROLE_USER_CREATE,
  ROLE_USER_UPDATE,
  ROLE_USER_DELETE,

  ROLE_ADMIN_CREATE,
  ROLE_Super_ADMIN,


  ROLE_BRAND_CREATE,
  ROLE_BRAND_GET,
  ROLE_BRAND_UPDATE,
  ROLE_BRAND_DELETE,
  ROLE_BRAND_SEARCH,

  ROLE_CATEGORY_CREATE,
  ROLE_CATEGORY_GET,
  ROLE_CATEGORY_UPDATE,
  ROLE_CATEGORY_DELETE,
  ROLE_CATEGORY_SEARCH,

  ROLE_CUSTOMER_CREATE, // GROUP -> CREATE -> ROLE_BRAND_CREATE, ROLE_PRODUCT_CREATE
  ROLE_CUSTOMER_GET,
  ROLE_CUSTOMER_UPDATE,
  ROLE_CUSTOMER_DELETE,
  ROLE_CUSTOMER_SEARCH,


  ROLE_PRODUCT_CREATE, // GROUP -> CREATE -> ROLE_BRAND_CREATE, ROLE_PRODUCT_CREATE
  ROLE_PRODUCT_GET,
  ROLE_PRODUCT_UPDATE,
  ROLE_PRODUCT_DELETE,
  ROLE_PRODUCT_SEARCH,


  ROLE_PURCHASE_CREATE, // GROUP -> CREATE -> ROLE_BRAND_CREATE, ROLE_PRODUCT_CREATE
  ROLE_PURCHASE_GET,
  ROLE_PURCHASE_UPDATE,
  ROLE_PURCHASE_DELETE,
  ROLE_PURCHASE_SEARCH,


  ROLE_STOCK_CREATE,


  ROLE_SALES_CREATE, // GROUP -> CREATE -> ROLE_SALES_CREATE, ROLE_PRODUCT_CREATE
  ROLE_SALES_GET,
  ROLE_SALES_UPDATE,
  ROLE_SALES_DELETE,
  ROLE_SALES_SEARCH,




  ROLE_RECONCILIATION_CREATE, // GROUP -> CREATE -> ROLE_RECONCILIATION_CREATE, ROLE_RECONCILIATION_CREATE
  ROLE_RECONCILIATION_GET,
  ROLE_RECONCILIATION_UPDATE,
  ROLE_RECONCILIATION_DELETE,
  ROLE_RECONCILIATION_SEARCH,



  ROLE_SUPPLIER_CREATE, // GROUP -> CREATE -> ROLE_BRAND_CREATE, ROLE_PRODUCT_CREATE
  ROLE_SUPPLIER_GET,
  ROLE_SUPPLIER_UPDATE,
  ROLE_SUPPLIER_DELETE,
  ROLE_SUPPLIER_SEARCH,


}
