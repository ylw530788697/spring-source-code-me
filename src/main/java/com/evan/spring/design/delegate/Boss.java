package com.evan.spring.design.delegate;

import sun.misc.PostVMInitHook;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 11:00
 */
public class Boss {
    public void command(String command,Leader leader){
        leader.doing(command);
    }
}
