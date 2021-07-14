package com.evan.spring.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/13 17:29
 */
public class Teacher implements Observer {
    private String name;
    public Teacher(String name){
        this.name=name;
    }
    public void update(Observable o, Object arg) {
        Per per=(Per) o;
        Question question=(Question)arg;
        System.out.println("begin-----------------------------");
        System.out.println(name+"老师你好：,您收到了一个来自"+per.getName()+"的提问，希望您解答，问题内容如下"+question.getContent());
    }
}
