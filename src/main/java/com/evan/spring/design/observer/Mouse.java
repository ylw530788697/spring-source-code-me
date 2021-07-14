package com.evan.spring.design.observer;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/14 11:41
 */
public class Mouse extends EventLisenter{

    public void click(){
        System.out.println("调用单击方法");
        this.trigger(MouseEventType.ON_CLICK);
    }

    public void doubleClick(){
        System.out.println("调用双击方法");
        this.trigger(MouseEventType.ON_DOUBLE_CLICK);
    }

    public void up(){
        System.out.println("调用弹起方法");
        this.trigger(MouseEventType.ON_UP);
    }
}
