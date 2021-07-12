package com.evan.spring.design.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 10:54
 */
public class Leader implements IEmployee {

    private Map<String,IEmployee> targets=new HashMap<String, IEmployee>();

    public Leader(){
        targets.put("加密",new EmployeeA());
        targets.put("登陆",new EmployeeB());
    }
    public void doing(String command) {
        targets.get(command).doing(command);
    }
}
