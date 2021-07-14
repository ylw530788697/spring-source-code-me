package com.evan.spring.design.observer;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/14 12:06
 */
public class MouseEventTest {
    public static void main(String[] args) {
        MouseEventCallback callback = new MouseEventCallback();

        Mouse mouse = new Mouse();
        mouse.addLisenter(MouseEventType.ON_CLICK,callback);
        mouse.addLisenter(MouseEventType.ON_DOUBLE_CLICK,callback);
        mouse.addLisenter(MouseEventType.ON_UP,callback);

        mouse.click();
    }
}
