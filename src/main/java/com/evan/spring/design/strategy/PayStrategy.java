package com.evan.spring.design.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 12:00
 */
public class PayStrategy {
    public static final String ALI_PAY="Alipay";
    public static final String JD_PAY = "JdPay";
    public static final String UNION_PAY = "UnionPay";
    public static final String WECHAT_PAY = "WechatPay";
    public static final String DEFAULT_PAY = "Alipay";

    private static Map<String,Payment> paymentMap=new HashMap<String, Payment>();
    static {
        paymentMap.put(ALI_PAY,new AliPay());
        paymentMap.put(JD_PAY,new JDPay());
        paymentMap.put(WECHAT_PAY,new WechatPay());

    }

    public static Payment get(String payKey){
        if (!paymentMap.containsKey(payKey)){
            return paymentMap.get(DEFAULT_PAY);
        }
        return paymentMap.get(payKey);
    }
}
