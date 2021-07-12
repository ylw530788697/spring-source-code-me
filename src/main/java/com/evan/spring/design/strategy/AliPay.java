package com.evan.spring.design.strategy;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 11:57
 */
public class AliPay extends Payment {
    @Override
    public String getName() {
        return "支付宝";
    }

    @Override
    protected double queryBalance(String uid) {
        return 900;
    }
}
