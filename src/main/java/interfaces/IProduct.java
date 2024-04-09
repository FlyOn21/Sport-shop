package interfaces;


public interface IProduct {
     String getId();
     void setId(String id);
     String getName();
     void setName(String name);
     double getPrice();
     void setPrice(double price);
     int getQuantity();
     void setQuantity(int quantity);
     String getColor();
     void setColor(String color);
     String getDescription();
     void setDescription(String description);
     String getBrand();
     void setBrand(String brand);
     String getCategory();
     void setCategory(String category);
     String getSubCategory();
     void setSubCategory(String subCategory);
     String toString();
     boolean isAvailable();
     void setAvailable();
     void setDiscount(double discount);
     double getDiscount();
}
