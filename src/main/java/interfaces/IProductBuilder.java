package interfaces;

public interface IProductBuilder {
    void setProductId(String productId);
    void setProductName(String productName);
    void setProductPrice(double productPrice);
    void setProductCategory(String productCategory);
    void setSubCategory(String subCategory);
    void setProductDescription(String productDescription);
    void setBrand(String productBrand);
    void setColor(String productColor);
    void setQuantity(int productQuantity);
    void setDiscount(double productDiscount);
    IProduct getProduct();
}
