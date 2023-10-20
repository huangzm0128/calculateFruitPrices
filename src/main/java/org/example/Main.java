package org.example;

import java.math.BigDecimal;

/**
 * @auther: huangzm
 * @date: ${DATE} ${HOUR}:${MINUTE}
 * @description:
 */

/**
 * 题目
 * 1、有一家超市，出售苹果和草莓。其中苹果 8 元/斤，草莓 13 元/斤。
 * 现在顾客 A 在超市购买了若干斤苹果和草莓，需要计算一共多少钱？
 * 请编写函数，对于 A 购买的水果斤数 (水果斤数为大于等于 0 的整数)，计算并返回所购买商品的总价。
 *
 * 2、超市增加了一种水果芒果，其定价为 20 元/斤。
 * 现在顾客 B 在超市购买了若干斤苹果、 草莓和芒果，需计算一共需要多少钱？
 * 请编写函数，对于 B 购买的水果斤数 (水果斤数为大于等于 0 的整数)，计算并返回所购买商品的总价。
 *
 * 3、超市做促销活动，草莓限时打 8 折。
 * 现在顾客 C 在超市购买了若干斤苹果、 草莓和芒果，需计算一共需要多少钱？
 * 请编写函数，对于 C 购买的水果斤数 (水果斤数为大于等于 0 的整数)，计算并返回所购买商品的总价。
 *
 * 4、促销活动效果明显，超市继续加大促销力度，购物满 100 减 10 块。
 * 现在顾客 D 在超市购买了若干斤苹果、 草莓和芒果，需计算一共需要多少钱？
 * 请编写函数，对于 C 购买的水果斤数 (水果斤数为大于等于 0 的整数)，计算并返回所购买商品的总价。
 *
 * 要求
 * 使用 Java 程序编写，IDE 不限，
 * 请使用面向对象的思路进行程序编写。
 * 需在程序中，验证函数计算结果的正确性。
 *
 * 提示
 * 顾客购买的水果斤数，可自行确定。无论数值为多少，均需验证程序计算结果的正确性。
 * 可以编写多个函数分别实现，也可以只编写一个函数实现，方式不限。
 * 面试时，请准备电脑及 IDE 环境进行现场演示。
 * 若能将代码提交到 github、gitee、gitlab 等代码托管仓库，提供代码仓库地址更佳。
 */
public class Main {
    // 初始化水果类型
    static Fruit apple = new Fruit("APPLE", new BigDecimal(8), null);
    static Fruit strawberry = new Fruit("STRAWBERRY", new BigDecimal(13), null);
    static Fruit mango = new Fruit("MANGO", new BigDecimal(20), null);
    public static void main(String[] args) {

        // 1. 2斤苹果，2斤草莓, 总价42
        test1(new BigDecimal(2), new BigDecimal(2));

        // 2. 2斤苹果，2斤草莓, 2斤芒果,总价82
        test2(new BigDecimal(2), new BigDecimal(2),new BigDecimal(2));

        // 3. 2斤苹果，2斤草莓, 2斤芒果, 草莓限时打 8 折,总价76.8元
        // 草莓打8折
        strawberry.setDiscount(new BigDecimal("0.8"));
        test3(new BigDecimal(2), new BigDecimal(2),new BigDecimal(2));

        // 4. 5斤苹果，5斤草莓, 5斤芒果, 草莓限时打 8 折,总价192元, 购物车折扣满100减10，最终花费182元
        // 添加购物车折扣，满100-10
        CartDiscountRule discountRule = new CartDiscountRule(new BigDecimal(100), new BigDecimal(10));
        test4(new BigDecimal(5), new BigDecimal(5), new BigDecimal(5), discountRule);

    }

    public static void test1(BigDecimal applePounds, BigDecimal strawberryPounds){
        ShoppingCart shoppingCart = new ShoppingCart();
        CartItem appleCartItem = new CartItem(apple, applePounds);
        CartItem strawberryCartItem = new CartItem(strawberry, strawberryPounds);
        shoppingCart.addItem(appleCartItem);
        shoppingCart.addItem(strawberryCartItem);
        BigDecimal bigDecimal1 = shoppingCart.calculateTotalPrice();
        System.out.printf("1.购买了%s斤苹果、%s斤草莓，需要花费%s元%n",applePounds, strawberryPounds, bigDecimal1);
    }

    public static void test2(BigDecimal applePounds, BigDecimal strawberryPounds, BigDecimal mangoPounds){
        ShoppingCart shoppingCart = new ShoppingCart();
        CartItem mangoCartItem = new CartItem(mango, applePounds);
        CartItem appleCartItem = new CartItem(apple, strawberryPounds);
        CartItem strawberryCartItem = new CartItem(strawberry, mangoPounds);
        shoppingCart.addItem(mangoCartItem);
        shoppingCart.addItem(appleCartItem);
        shoppingCart.addItem(strawberryCartItem);
        BigDecimal bigDecimal2 = shoppingCart.calculateTotalPrice();
        System.out.printf("2.购买了%s斤苹果、%s斤草莓、%s斤芒果，需要花费%s元%n", applePounds,strawberryPounds,mangoPounds,bigDecimal2);
    }

    public static void test3(BigDecimal applePounds, BigDecimal strawberryPounds, BigDecimal mangoPounds){
        ShoppingCart shoppingCart = new ShoppingCart();
        CartItem mangoCartItem = new CartItem(mango, applePounds);
        CartItem appleCartItem = new CartItem(apple, strawberryPounds);
        CartItem strawberryCartItem = new CartItem(strawberry, mangoPounds);
        shoppingCart.addItem(mangoCartItem);
        shoppingCart.addItem(appleCartItem);
        shoppingCart.addItem(strawberryCartItem);
        BigDecimal bigDecimal2 = shoppingCart.calculateTotalPrice();
        System.out.printf("3.购买了%s斤苹果、%s斤草莓、%s斤芒果，草莓限时打8折，需要花费%s元%n", applePounds,strawberryPounds,mangoPounds,bigDecimal2);
    }

    public static void test4(BigDecimal applePounds, BigDecimal strawberryPounds, BigDecimal mangoPounds, CartDiscountRule discountRule){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addDiscount(discountRule);
        CartItem mangoCartItem = new CartItem(mango, applePounds);
        CartItem appleCartItem = new CartItem(apple, strawberryPounds);
        CartItem strawberryCartItem = new CartItem(strawberry, mangoPounds);
        shoppingCart.addItem(mangoCartItem);
        shoppingCart.addItem(appleCartItem);
        shoppingCart.addItem(strawberryCartItem);
        BigDecimal bigDecimal2 = shoppingCart.calculateTotalPrice();
        System.out.printf("4.购买了%s斤苹果、%s斤草莓、%s斤芒果，草莓限时打8折，购物车折扣为满%s减%s，需要花费%s元%n", applePounds,strawberryPounds,mangoPounds,discountRule.getThreshold(), discountRule.getDiscount(), bigDecimal2);
    }
}