package com.evan.spring.design.strategy;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 11:44
 */
public abstract class Payment {

    //支付类型
    public abstract String getName();

    //查询余额
    protected  abstract double queryBalance(String uid);

    /**
     * 扣款支付
     * @param uid
     * @param amount
     * @return
     */
    public MsgResult pay(String uid,double amount){
        if (queryBalance(uid)<amount){
            return new MsgResult(500,"支付失败","余额不足");
        }
        return new MsgResult(200,"支付成功","支付金额：" + amount);
    }
}
