package be.pxl.ja.exercise7_4;

import java.math.BigDecimal;

public class CartItem {

    String name;
    BigDecimal price;

    public CartItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
