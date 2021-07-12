package com.evan.spring.design.decorator;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 17:19
 */
public class EggDecorator extends BattercakeDectator{
    public EggDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    protected void doSomething() {

    }

    @Override
    protected String getMsg(){
        return super.getMsg()+"加一个鸡蛋";
    }

    @Override
    protected int getPrice(){
        return super.getPrice()+1;
    }
}
