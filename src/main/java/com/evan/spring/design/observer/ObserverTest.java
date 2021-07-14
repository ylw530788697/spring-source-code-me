package com.evan.spring.design.observer;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/13 17:46
 */
public class ObserverTest {
    public static void main(String[] args) {
        Per per=Per.getInstance();
        Teacher tom = new Teacher("tom");
        Teacher mic = new Teacher("mic");
        per.addObserver(tom);
        per.addObserver(mic);
        Question question = new Question();
        question.setUserName("evan");
        question.setContent("观察者模式适用于哪些场景");
        per.publishQuestion(question);
    }
}
