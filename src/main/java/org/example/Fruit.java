package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @auther: huangzm
 * @date: 2023/10/18 0018 05:44
 * @description: 水果类
 */
@Data
@AllArgsConstructor
public class Fruit {
    /**
     * 类型
     */
    private final String type;
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 折扣
     */
    private BigDecimal discount;

    /**
     * 计算单斤价格
     * @param
     * @return
     */
    public BigDecimal calculate(){
        if (discount == null){
            return unitPrice;
        }
        return this.unitPrice.multiply(discount);
    }
}
