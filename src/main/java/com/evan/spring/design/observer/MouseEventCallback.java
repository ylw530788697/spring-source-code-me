package com.evan.spring.design.observer;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/14 12:02
 */
public class MouseEventCallback {
    public void onClick(Event e){
        System.out.println("=============触发鼠标单击事件==============="+e);
    }

    public void onDoubleClick(Event event){
        System.out.println("===============触发鼠标双击事件=================="+event);
    }

    public void onUp(Event event){
        System.out.println("=========触发鼠标弹起事件======"+event);
    }
}
