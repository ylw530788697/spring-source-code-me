package com.evan.spring.design.decorator;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 17:00
 */
public class BaseBattercake extends Battercake{
    @Override
    protected String getMsg() {
        return "煎饼";
    }

    @Override
    public int getPrice() {
        return 5;
    }
}
