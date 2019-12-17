package com.example.globalmart_teama.models;

public class ProductsModel {

    private int productID;
    private String productName;
    private String productDesc;
    private int productPrice;
    private String productImageID;
    private String productCountryName;
    private String productCategoryName;
    private String productCode;
    private int productCartQuantity = 0;

    public ProductsModel(int id, String productName, String productDesc, int productPrice,
                         String productImageID, String productCountryName, String  productCategoryName,
                         String productCode) {
        this.productID = id;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productImageID = productImageID;
        this.productCountryName = productCountryName;
        this.productCategoryName = productCategoryName;
        this.productCode = productCode;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImageID() {
        return productImageID;
    }

    public void setProductImageID(String productImageID) {
        this.productImageID = productImageID;
    }

    public String getProductCountryName() {
        return productCountryName;
    }

    public void setProductCountryName(String productCountryName) {
        this.productCountryName = productCountryName;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public int getProductCartQuantity() {
        return productCartQuantity;
    }

    public void setProductCartQuantity(int productCartQuantity) {
        this.productCartQuantity = productCartQuantity;
    }
}
