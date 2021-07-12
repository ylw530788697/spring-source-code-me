package com.evan.spring.design.delegate;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 11:01
 */
public class DeleGateTest {
    public static void main(String[] args) {
        new Boss().command("登陆",new Leader());
    }
}
