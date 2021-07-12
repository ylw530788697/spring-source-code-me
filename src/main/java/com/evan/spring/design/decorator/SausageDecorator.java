package com.evan.spring.design.decorator;

import org.jcp.xml.dsig.internal.dom.DOMTransform;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 17:23
 */
public class SausageDecorator extends BattercakeDectator{
    public SausageDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    protected void doSomething() {

    }

    @Override
    protected String getMsg(){
        return super.getMsg()+"+1根香肠";
    }

    @Override
    protected int getPrice(){
        return super.getPrice()+2;
    }
}
