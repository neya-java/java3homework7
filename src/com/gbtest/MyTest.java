package com.gbtest;

public class MyTest {

    @com.gbtest.BeforeSuite
    public static void test2() {
        System.out.println("test2 BeforeSuite");
    }

    @com.gbtest.Test
    public static void test8() {
        System.out.println("test8");
    }

    @com.gbtest.Test
    public static void test1() {
        System.out.println("test1");
    }

    @com.gbtest.Test(priority = 2)
    public static void test3() {
        System.out.println("test3 priority = 2 ");
    }

    @com.gbtest.Test(priority = 7)
    public static void test4() {
        System.out.println("test4 priority = 7 ");
    }

    @com.gbtest.AfterSuite
    public static void test5() {
        System.out.println("test5 AfterSuite");
    }
}
