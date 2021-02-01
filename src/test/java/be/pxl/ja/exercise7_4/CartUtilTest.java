package be.pxl.ja.exercise7_4;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartUtilTest {

    @Test
    public void checkoutSimpleShouldReturnSumOfAllItemPrices() {

        List<CartItem> cartItems = Arrays.asList(
                new CartItem("hat", new BigDecimal(5)),
                new CartItem("shoes", new BigDecimal(10))
        );

        assertEquals(BigDecimal.valueOf(15), CartUtil.checkoutSimple(cartItems));
    }

    @Test
    public void checkoutShouldReturnSumOfAllItemPricesWithDiscount() {

        List<CartItem> cartItems = Arrays.asList(
                new CartItem("shoes", new BigDecimal(10)),
                new CartItem("shoes", new BigDecimal(30)),
                new CartItem("shoes", new BigDecimal(60)),
                new CartItem("hat", new BigDecimal(45)),
                new CartItem("hat", new BigDecimal(55)),
                new CartItem("dress", new BigDecimal(30))
        );

        //shoes: 10+30+60 = 100 - 20 = 80
        //hat: 45 + 55 = 100 - 10 = 90
        //dress = 30

        assertEquals(BigDecimal.valueOf(200.0), CartUtil.checkout(cartItems));
    }
}