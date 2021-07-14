package com.evan.spring.design.observer;

import lombok.Data;

import java.util.Observable;

/**
 * JDK提供的一种观察者的实现方式，被观察者
 * @author evanYang
 * @version 1.0
 * @date 2021/7/13 17:21
 */
@Data
public class Per extends Observable {
    private String name="腾讯课堂生态圈";
    private static Per per = null;
    private Per(){

    }

    public static Per getInstance(){
        if (null==per){
            per=new Per();
        }
        return per;
    }

    public void publishQuestion(Question question){
        System.out.println(question.getUserName()+"在"+this.name+"提交了一个问题");
        setChanged();
        notifyObservers(question);
    }

}
