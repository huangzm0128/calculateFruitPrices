package org.example;

/**
 * @auther: huangzm
 * @date: 2023/10/18 0018 06:14
 * @description:
 */

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 购物车项
 */
@Data
@AllArgsConstructor
public class CartItem {
    /**
     * 水果
     */
    private Fruit fruit;
    /**
     * 购买斤数
     */
    private BigDecimal purchaseWeightInPounds;

    /**
     * 计算价格
     * @return
     */
    public BigDecimal calculatePrice(){
        return fruit.calculate().multiply(purchaseWeightInPounds);
    }
}
