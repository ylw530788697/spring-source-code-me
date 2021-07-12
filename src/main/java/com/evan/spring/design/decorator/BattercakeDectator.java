package com.evan.spring.design.decorator;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 17:03
 */
public abstract class BattercakeDectator extends Battercake{
    // 静态代理、委派
    private Battercake battercake;

    protected abstract void doSomething();

    public BattercakeDectator(Battercake battercake) {
        this.battercake=battercake;
    }

    @Override
    protected String getMsg() {
        return this.battercake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.battercake.getPrice();
    }
}
