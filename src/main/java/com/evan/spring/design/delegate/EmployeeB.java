package com.evan.spring.design.delegate;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 10:51
 */
public class EmployeeB implements IEmployee{
    public void doing(String command) {
        System.out.println("我是员工B，我现在开始干{} 工作"+command);
    }
}
