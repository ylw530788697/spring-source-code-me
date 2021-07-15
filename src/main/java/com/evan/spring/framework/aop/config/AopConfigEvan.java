package com.evan.spring.framework.aop.config;

import lombok.Data;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/15 10:10
 */
@Data
public class AopConfigEvan {
    private String pointCut;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectClass;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;

}
