package org.example;

import java.math.BigDecimal;

/**
 * @auther: huangzm
 * @date: ${DATE} ${HOUR}:${MINUTE}
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        // 初始化水果类型
        Fruit apple = new Fruit("APPLE", new BigDecimal(8), null);
        Fruit strawberry = new Fruit("STRAWBERRY", new BigDecimal(13), null);
        Fruit mango = new Fruit("MANGO", new BigDecimal(20), null);

        // 1. 2斤苹果，2斤草莓, 总价42
        CartItem appleCartItem = new CartItem(apple, new BigDecimal(2));
        CartItem strawberryCartItem = new CartItem(strawberry, new BigDecimal(2));
        ShoppingCart.addItem(appleCartItem);
        ShoppingCart.addItem(strawberryCartItem);
        BigDecimal bigDecimal1 = ShoppingCart.calculateTotalPrice();
        System.out.printf("1.购买了2斤苹果、2斤草莓，需要花费%s元%n", bigDecimal1);
        ShoppingCart.clearCart();

        // 2. 2斤苹果，2斤草莓, 2斤芒果,总价82
        CartItem mangoCartItem2 = new CartItem(mango, new BigDecimal(2));
        CartItem appleCartItem2 = new CartItem(apple, new BigDecimal(2));
        CartItem strawberryCartItem2 = new CartItem(strawberry, new BigDecimal(2));
        ShoppingCart.addItem(mangoCartItem2);
        ShoppingCart.addItem(appleCartItem2);
        ShoppingCart.addItem(strawberryCartItem2);
        BigDecimal bigDecimal2 = ShoppingCart.calculateTotalPrice();
        System.out.printf("2.购买了2斤苹果、2斤草莓、2斤芒果，需要花费%s元%n", bigDecimal2);
        ShoppingCart.clearCart();

        // 3. 2斤苹果，2斤草莓, 2斤芒果, 草莓限时打 8 折,总价76.8元
        CartItem appleCartItem3 = new CartItem(apple, new BigDecimal(2));
        CartItem strawberryCartItem3 = new CartItem(strawberry, new BigDecimal(2));
        CartItem mangoCartItem3 = new CartItem(mango, new BigDecimal(2));
        // 草莓打8折
        strawberry.setDiscount(new BigDecimal("0.8"));
        ShoppingCart.addItem(appleCartItem3);
        ShoppingCart.addItem(strawberryCartItem3);
        ShoppingCart.addItem(mangoCartItem3);
        BigDecimal bigDecimal3 = ShoppingCart.calculateTotalPrice();
        System.out.printf("3.购买了2斤苹果、2斤草莓、2斤芒果，草莓限时打8折，需要花费%s元%n", bigDecimal3);
        ShoppingCart.clearCart();


        // 4. 5斤苹果，5斤草莓, 5斤芒果, 草莓限时打 8 折,总价192元, 购物车折扣满100减10，最终花费182元
        CartItem appleCartItem4 = new CartItem(apple, new BigDecimal(5));
        CartItem strawberryCartItem4 = new CartItem(strawberry, new BigDecimal(5));
        CartItem mangoCartItem4 = new CartItem(mango, new BigDecimal(5));
        // 草莓打8折
        strawberry.setDiscount(new BigDecimal("0.8"));
        ShoppingCart.addItem(appleCartItem4);
        ShoppingCart.addItem(strawberryCartItem4);
        ShoppingCart.addItem(mangoCartItem4);
        // 添加购物车折扣，满100-10
        CartDiscountRule discountRule = new CartDiscountRule(new BigDecimal(100), new BigDecimal(10));
        ShoppingCart.addDiscount(discountRule);
        BigDecimal bigDecimal4 = ShoppingCart.calculateTotalPrice();
        System.out.printf("4.购买了2斤苹果、2斤草莓、2斤芒果，草莓限时打8折，需要花费%s元%n", bigDecimal4);
        ShoppingCart.clearCart();

    }
}