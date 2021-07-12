package com.evan.spring.design.strategy;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 11:58
 */
public class JDPay extends Payment {
    @Override
    public String getName() {
        return "京东白条";
    }

    @Override
    protected double queryBalance(String uid) {
        return 500;
    }
}
