package com.evan.spring.design.delegate;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 10:49
 */
public class EmployeeA implements IEmployee{
    public void doing(String command) {
        System.out.println("员工A，我现在开始干：{} 工作"+command);
    }
}
