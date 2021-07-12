package com.evan.spring.design.decorator;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 17:44
 */
public class BattercakeTest {
    public static void main(String[] args) {
        Battercake battercake;
        //路边摊买一个煎饼
        battercake=new BaseBattercake();
        //煎饼有点小，想在加个鸡蛋
        battercake=new EggDecorator(battercake);
        //想在加个鸡蛋
        battercake=new EggDecorator(battercake);
        //想在加个香肠
        battercake=new SausageDecorator(battercake);
        //和静态代理最大的区别就是职责不同
        //静态代理不一定要满足is-a的关系
        //静态代理会做功能增强，同一个职责变得不一样
        System.out.println(battercake.getMsg()+"总价："+battercake.getPrice());
    }
}
