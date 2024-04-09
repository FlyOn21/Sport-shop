package builders;

import entity.Product;

public class ProductBuilder implements interfaces.IProductBuilder {
    private final Product product;

    public ProductBuilder() {
        this.product = new Product();
    }

    public void setProductId(String productId) {
        this.product.setId(productId);
    }

    public void setProductName(String productName) {
        this.product.setName(productName);
    }

    public void setProductPrice(double productPrice) {
        this.product.setPrice(productPrice);
    }

    public void setProductCategory(String productCategory) {
        this.product.setCategory(productCategory);
    }

    public void setSubCategory(String subCategory) {
        this.product.setSubCategory(subCategory);
    }

    public void setProductDescription(String productDescription) {
        this.product.setDescription(productDescription);
    }

    public void setBrand(String productBrand) {
        this.product.setBrand(productBrand);
    }

    public void setColor(String productColor) {
        this.product.setColor(productColor);
    }

    public void setQuantity(int productQuantity) {
        this.product.setQuantity(productQuantity);
    }

    public void setDiscount(double productDiscount) {
        this.product.setDiscount(productDiscount);
    }

    public Product getProduct() {
        return this.product;
    }
}
