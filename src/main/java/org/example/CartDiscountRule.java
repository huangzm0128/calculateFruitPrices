package org.example;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @auther: huangzm
 * @date: 2023/10/18 0018 06:21
 * @description: 购物车折扣规则
 */
@Data
public class CartDiscountRule {
    /**
     * 满足折扣条件的金额阈值
     */
    private BigDecimal threshold;
    /**
     * 折扣金额
     */
    private BigDecimal discount;

    public CartDiscountRule(BigDecimal threshold, BigDecimal discount) {
        this.threshold = threshold;
        this.discount = discount;
    }
}
