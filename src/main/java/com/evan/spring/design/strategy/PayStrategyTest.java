package com.evan.spring.design.strategy;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 15:16
 */
public class PayStrategyTest {
    public static void main(String[] args) {
        Order order = new Order("1", "20210712000", 324.45);
        MsgResult pay = order.pay(PayStrategy.ALI_PAY);
        System.out.println(pay);
    }
}
