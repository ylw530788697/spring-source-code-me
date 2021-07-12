package com.evan.spring.design.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/12 11:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MsgResult {
    private int code;
    private Object data;
    private String msg;
}
