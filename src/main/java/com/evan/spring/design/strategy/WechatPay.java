package com.evan.spring.design.strategy;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 11:59
 */
public class WechatPay extends Payment{
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 256;
    }
}
