package org.example;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @auther: huangzm
 * @date: 2023/10/18 0018 06:02
 * @description: 购物车
 */
public class ShoppingCart {
    /**
     * 购物车列表
     */
    private static final CopyOnWriteArrayList<CartItem> shoppingCart = new CopyOnWriteArrayList<>();

    /**
     * 折扣列表
     */
    private static final CopyOnWriteArrayList<CartDiscountRule> discountRules = new CopyOnWriteArrayList<>();

    /**
     * 计算总价
     * @return
     */
    public static BigDecimal calculateTotalPrice(){
        BigDecimal total = new BigDecimal(0);
        // 计算购物车总金额
        for (CartItem item : shoppingCart) {
            total =total.add(item.calculatePrice());
        }

        // 应用购物车折扣规则
        total = applyDiscount(total);

        return total;
    }

    /**
     * 添加商品到购物车
     * @param item
     */
    public static void addItem(CartItem item) {
        shoppingCart.add(item);
    }

    /**
     * 添加购物车折扣
     * @param item
     */
    public static void addDiscount(CartDiscountRule item) {
        discountRules.add(item);
        if (discountRules.size() < 2){
            return;
        }
        // 排序，满足金额高的在前
        CartDiscountRule[] array = discountRules.toArray(new CartDiscountRule[]{});
        Arrays.sort(array, (o1, o2) -> o2.getThreshold().compareTo(o1.getThreshold()));
        for (int i = 0; i < array.length; i++) {
            discountRules.set(i, array[i]);
        }
    }
    /**
     * 应用购物车折扣规则
     * @param totalAmount
     * @return
     */
    private static BigDecimal applyDiscount(BigDecimal totalAmount) {
        // 根据折扣规则来计算折扣金额
        // 这里需要遍历折扣规则列表，查找满足条件的折扣规则,折扣规则列表需排序好，价格高的排前
        for (CartDiscountRule discountRule : discountRules){
            int comparisonResult  = totalAmount.compareTo(discountRule.getThreshold());
            if (comparisonResult >= 0){
                // 应用相应的折扣金额
                totalAmount = totalAmount.subtract(discountRule.getDiscount());
                // 满足第一个折扣即可
                break;
            }
        }
        return totalAmount;
    }

    /**
     * 清空购物车
     */
    public static void clearCart(){
        shoppingCart.clear();
    }
    /**
     * 清空购物车折扣规则列表
     */
    public static void clearDiscountRules(){
        discountRules.clear();
    }
}
