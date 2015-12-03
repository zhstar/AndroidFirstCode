package com.codekun.androidfirstcode.ch7;

/**
 * Created by kun on 2015/11/27.
 */
public class TestInterface {

    public TestInterface(){

    }

    public static final class Test implements ITest{

    }

    protected interface ITest{
        public static final String TEST = "test";
    }
}
