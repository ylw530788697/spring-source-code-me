package com.evan.spring.design.observer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 监听器，观察者的桥梁
 * @author evanYang
 * @version 1.0
 * @date 2021/7/13 18:00
 */
public class EventLisenter {
    //
    protected Map<String,Event> eventMap=new HashMap<String, Event>();

    public void addLisenter(String eventType,Object target){
        try {
            this.addLisenter(
                    eventType,
                    target,
                    target.getClass().getMethod("on" + toUpperFirstCase(eventType),Event.class));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addLisenter(String evenType, Object target, Method callback){
        //注册事件
        eventMap.put(evenType,new Event(target,callback));
    }

    //触发，只要有动作就触发
    private void trigger(Event event){
        event.setSource(this);
        event.setTime(System.currentTimeMillis());
        try {
            //发起回调
            if (event.getCallback()!=null){
                //用反射调用他的回调函数
                event.getCallback().invoke(event.getTarget(),event);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //事件名称触发
    protected void trigger(String trigger){
        if(!this.eventMap.containsKey(trigger)){return;}
        //trigger(this.eventMap.get(trigger).setTrigger(trigger));
    }

    private String toUpperFirstCase(String str){
        char[] chars=str.toCharArray();
        chars[0]-=32;
        return String.valueOf(chars);
    }
}
