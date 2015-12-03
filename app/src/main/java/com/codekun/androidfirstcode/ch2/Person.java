package com.codekun.androidfirstcode.ch2;

import java.io.Serializable;

/**
 * ���
 * Created by kun on 2015/11/13.
 */
public class Person implements Serializable {


    //����
    private String name;
    //����
    private int age;


    public Person(){

    }


    public String toString(){
        return "Person->[name:" + this.name + ", age:" + this.age + "]";
    }

    /**
     * ���� person ��������
     * @return
     */
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ��������
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     * ��������
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }


}
