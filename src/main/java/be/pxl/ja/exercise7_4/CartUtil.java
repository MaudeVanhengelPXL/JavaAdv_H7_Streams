package be.pxl.ja.exercise7_4;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CartUtil {

    public static BigDecimal checkoutSimple(List<CartItem> items) {

        return items.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal checkout(List<CartItem> items) {

        Map<String, Double> itemTotalsMap = items.stream()
                .collect(Collectors.groupingBy(
                        CartItem::getName,
                        Collectors.summingDouble(cartItem -> cartItem.getPrice().doubleValue())
                ));

        //shoes discount
        itemTotalsMap.computeIfPresent("shoes", (key, value) -> {
            if (value >= 100) {
                return value - value * 0.2;
            }
            return value;
        });


        //hat discount
        itemTotalsMap.computeIfPresent("hat", (key, value) -> {
            if (items.stream().filter(cartItem -> cartItem.getName().equals("hat")).count() >= 2) {
                if (value >= 50) {
                    return value - 10;
                }
            }
            return value;
        });


        //som maken
        return BigDecimal.valueOf(itemTotalsMap.values().stream().mapToDouble(Double::doubleValue).sum());
    }
}
